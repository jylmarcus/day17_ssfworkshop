package sg.ssf.visa.day17_ssfworkshop.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class WeatherStatus implements Serializable {
    private String city;
    private String main;
    private String description;
    private String icon;
    private Double temp;
    private LocalDateTime sunrise;
    private LocalDateTime sunset;
    private LocalDateTime recordedTime;

    public WeatherStatus() {
        
    }

    public static WeatherStatus create(String json) throws IOException {
        WeatherStatus w = new WeatherStatus();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            w.setCity(o.getString("name"));
            w.setMain(o.getJsonArray("weather").getJsonObject(0).getString("main"));
            w.setDescription(o.getJsonArray("weather").getJsonObject(0).getString("description"));
            w.setIcon("https://openweathermap.org/img/wn/" + 
            o.getJsonArray("weather").getJsonObject(0).getString("icon") +
            "@2x.png");
            w.setTemp((double) o.getJsonObject("main").getInt("temp"));
            w.setSunrise(LocalDateTime.ofEpochSecond(o.getJsonObject("sys").getJsonNumber("sunrise").longValue(),0, ZoneOffset.ofHours(8)));
            w.setSunset(LocalDateTime.ofEpochSecond(o.getJsonObject("sys").getJsonNumber("sunset").longValue(),0, ZoneOffset.ofHours(8)));
            w.setRecordedTime(LocalDateTime.ofEpochSecond(o.getJsonNumber("dt").longValue(),0, ZoneOffset.ofHours(8)));

        }

        return w;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getMain() {
        return main;
    }
    public void setMain(String main) {
        this.main = main;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Double getTemp() {
        return temp;
    }
    public void setTemp(Double temp) {
        this.temp = temp;
    }
    public LocalDateTime getSunrise() {
        return sunrise;
    }
    public void setSunrise(LocalDateTime sunrise) {
        this.sunrise = sunrise;
    }
    public LocalDateTime getSunset() {
        return sunset;
    }
    public void setSunset(LocalDateTime sunset) {
        this.sunset = sunset;
    }
    public LocalDateTime getRecordedTime() {
        return recordedTime;
    }
    public void setRecordedTime(LocalDateTime recordedTime) {
        this.recordedTime = recordedTime;
    }

    
    
}
