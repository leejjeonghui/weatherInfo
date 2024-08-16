package ballpark.weather;

import org.apache.catalina.valves.CrawlerSessionManagerValve;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/weather")
@RestController
public class GetWeatherController {


    private GetWeatherService getWeatherService;

    public GetWeatherController(GetWeatherService getWeatherService) {
        this.getWeatherService = getWeatherService;
    }

    // Body를 어디서 받아야할지
    @GetMapping("/current")
    public WeatherApiResponse getCurrentWeather(@RequestBody RequestStadiumInfo requestStadiumInfo) {
        return getWeatherService.getWeatherInfo(requestStadiumInfo);
    }
    @GetMapping("/weekly")
    public WeatherWeeklyApiResponse getWeeklyWeather(@RequestBody RequestStadium requestStadium) {
        return getWeatherService.getWeeklyWeatherInfo(requestStadium);
    }



//@GetMapping("/currenttest")
//public Map<String,Object> getCurrentWeatherTest(@RequestBody RequestStadiumInfo requestStadiumInfo) {
//    Map<String,Object> response = new Map()<>;
    // response.put("stadium:",stadium)
//    return getWeatherService.getWeatherInfo(requestStadiumInfo);
//}


}
