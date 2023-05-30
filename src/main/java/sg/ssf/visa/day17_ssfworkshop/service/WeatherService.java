package sg.ssf.visa.day17_ssfworkshop.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.ssf.visa.day17_ssfworkshop.model.WeatherStatus;

@Service
public class WeatherService {
    
    @Value("${weather.api.key}")
    private String API_KEY;
    private final String DEFAULT_CITY = "singapore";
    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public Optional<WeatherStatus> getWeather(String city) throws IOException{
        RestTemplate template = new RestTemplate();
        String url;
        if(city != null) {
            url = UriComponentsBuilder
                .fromUriString(API_URL)
                .queryParam("q", city.replaceAll(" ", "+"))
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .toUriString();
        } else {
            url = UriComponentsBuilder
                .fromUriString(API_URL)
                .queryParam("q", DEFAULT_CITY)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .toUriString();
        }
        
        ResponseEntity<String> resp = template.getForEntity(url, String.class);

        //System.out.printf("Status code: %d\n", resp.getStatusCode().value());
        //System.out.printf("Payload: %s\n", resp.getBody());
        WeatherStatus w = WeatherStatus.create(resp.getBody());

        if(w != null) {
            return Optional.of(w);
        }
        return Optional.empty();
        
        //Payload: {"coord":{"lon":103.8501,"lat":1.2897},"weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04d"}],"base":"stations","main":{"temp":303.92,"feels_like":310.72,"temp_min":303.2,"temp_max":305.17,"pressure":1011,"humidity":72},"visibility":10000,"wind":{"speed":3.6,"deg":150,"gust":11.32},"clouds":{"all":75},"dt":1685413369,"sys":{"type":1,"id":9470,"country":"SG","sunrise":1685400996,"sunset":1685444871},"timezone":28800,"id":1880252,"name":"Singapore","cod":200}
    }

}
