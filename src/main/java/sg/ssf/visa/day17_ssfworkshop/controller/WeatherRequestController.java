package sg.ssf.visa.day17_ssfworkshop.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import sg.ssf.visa.day17_ssfworkshop.model.WeatherStatus;
import sg.ssf.visa.day17_ssfworkshop.service.WeatherService;

@Controller
public class WeatherRequestController {
    @Autowired
    WeatherService service;

    @GetMapping(path = "/weather")
    public String getWeather(@RequestParam(required = true) String q, Model model) throws IOException{
        Optional<WeatherStatus> wOpt = service.getWeather(q);
        model.addAttribute("weather", wOpt.get());
        return "result";
    }
}
