package sg.ssf.visa.day17_ssfworkshop.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    
    private final String API_KEY = "6e3207bb3153ea7e5ebc65b3ed512c00";
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
