package sg.ssf.visa.day17_ssfworkshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.ssf.visa.day17_ssfworkshop.service.WeatherService;

@RestController
@RequestMapping(path="/index.html")
public class WeatherRequestController {
    @Autowired
    WeatherService service;

    @GetMapping("/weather")
    public String getWeather(@RequestParam String q){
        service.getWeather(q);
        return "result";
    }
}
