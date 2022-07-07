package com.example.WeatherProject.Service;

import com.example.WeatherProject.Dto.WeatherDto;
import com.example.WeatherProject.Repository.WeatherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@AllArgsConstructor
@Slf4j
@Service
public class WeatherServiceImpl implements IWeatherService {

    //gecoding and air pollution api
    private String apiKey = "4abbfbac0ff3839dbf9a6f799cc7c420";
    private final RestTemplate restTemplate;
    Logger logger = (Logger) LoggerFactory.getLogger(WeatherServiceImpl.class);

    private final WeatherDto weatherDto;
    private final WeatherRepository weatherRepository;
    String error="Not Found";
    boolean isValid;
    @Override
    public String findLatLon(String cityName, WeatherDto weatherDto) {
        String response = getResponse(
                "http://api.openweathermap.org/geo/1.0/direct?q=" + cityName + "&limit=5&appid=" + apiKey);
        if (!(response.startsWith("Error"))) {
            updateData(String.valueOf(response));
            if (isValid) {
                return response;
            }
        }
        return response;
    }

    private String getResponse(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return "Error: " + e.toString();
        }
    }

    private JSONObject getDataObject(String response) {
        JSONObject ob = null;
        try {
            ob = (JSONObject) new JSONParser().parse(response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            if (ob.get("cod").toString().equals("200")) {
                isValid = true;
            } else {
                System.out.println("COD is:" + ob.get("cod").toString());
                error = ob.get("message").toString();
            }
        } catch (Exception e) {
            error = "Error in Fetching the Request";
        }
        return ob;
    }

    private void updateData(String request) {
        isValid = false;
        error = "";
        JSONObject ob = getDataObject(request);
        System.out.println(isValid);
        if (isValid) {
            JSONObject main = (JSONObject) ob.get("main");
            JSONObject coord = (JSONObject) ob.get("coord");
            JSONArray weatherArray = (JSONArray) ob.get("weather");
            JSONObject weatherObj = (JSONObject) weatherArray.get(0);
            weatherDto.getLon() = Double.parseDouble(coord.get("lon").toString());
            weatherDto.getLat() = Double.parseDouble(coord.get("lat").toString());

        }
    }

    public ResponseEntity<String> findAirPollution(String lat, String log, WeatherDto weatherDto) {
        return null;
    }
       public void save(WeatherDto newWeatherDto) {
            weatherRepository.save(newWeatherDto);
            log.info("Weather registration is successful.");
      }

    public ResponseEntity<WeatherDto> deleteWeather(Long id) { //delete data

        Optional<WeatherDto> weatherDtoOptional = weatherRepository.findById(id);
        if (!weatherDtoOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        weatherRepository.delete(weatherDtoOptional.get());

        return ResponseEntity.noContent().build();
    }

    private void validDates(Date startDate, Date endDate) throws Exception { // Date Control
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdformat.parse("2020-11-26");
        if (startDate.before(d1)) {
            logger.info("Error");
        }
    }
}

