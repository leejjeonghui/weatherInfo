package ballpark.weather;

import org.apache.catalina.valves.CrawlerSessionManagerValve;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/weather")
@RestController
public class GetWeatherController {


    private GetWeatherService getWeatherService;

    public GetWeatherController(GetWeatherService getWeatherService) {
        this.getWeatherService = getWeatherService;
    }

    @GetMapping()
    public void getWeather(@RequestParam(required = false) String serviceKey,
                                     @RequestParam(required = false) String numOfRows,
                                     @RequestParam(required = false) String pageNo,
                                     @RequestParam(required = false) String dataType,
                                     @RequestParam String base_date,
                                     @RequestParam String base_time,
                                     @RequestParam int nx,
                                     @RequestParam int ny) {
        getWeatherService.getWeatherInfo(base_date, base_time, nx, ny);
    }


}