package sg.ssf.visa.day17_ssfworkshop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    
    @Value("${weather.api.key}")
    private String API_KEY;
    private final String DEFAULT_CITY = "singapore";

    public void getWeather(String city){
        RestTemplate template = new RestTemplate();
        String url;
        if(city != null) {
            url = "https://api.openweathermap.org/data/2.5/weather?q=" + city.trim().toLowerCase() + "&appid=" + API_KEY;
        } else {
            url = "https://api.openweathermap.org/data/2.5/weather?q=" + DEFAULT_CITY + "&appid=" + API_KEY;
        }
        
        ResponseEntity<String> resp = template.getForEntity(url, String.class);

        System.out.printf("Status code: %d\n", resp.getStatusCode());
        System.out.printf("Payload: %s\n", resp.getBody());
    }

}
