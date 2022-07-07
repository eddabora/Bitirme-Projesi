Execute the WeatherProjectApplication.java file
An Tomcat server instance will be started at port: 8080.
Access this server through your browser using localhost:8080.
To use the API enter the following in the url:

Location Name based(Plain String Output): http://localhost:8080/weather/{cityName}
{cityName} gives the latitude and longitude of the city.

Location Name based(Plain String Output): http://localhost:8080/weather/cities
Information about cities is recorded in the database.


Location Name based(JSON Output): http://localhost:8080/weather/lon={lon}&lat={lat}
Caused by air pollution based on latitude and longitude.
