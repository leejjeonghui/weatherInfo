package ballpark.weather;

import ballpark.weather.todaygamedto.GameApiResponse;
import ballpark.weather.todaygamedto.RequestGameInfo;
import ballpark.weather.todayweatherinfodto.RequestStadiumInfo;
import ballpark.weather.todayweatherinfodto.WeatherApiResponse;
import ballpark.weather.weeklyweatherinfodto.RequestStadium;
import ballpark.weather.weeklyweatherinfodto.WeatherWeeklyApiResponse;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/weather")
@RestController
public class GetWeatherController {


    private GetWeatherService getWeatherService;

    public GetWeatherController(GetWeatherService getWeatherService) {
        this.getWeatherService = getWeatherService;
    }

    // Body를 어디서 받아야할지
    @PostMapping("/current")
    public WeatherApiResponse getCurrentWeather(@RequestBody RequestStadiumInfo requestStadiumInfo) {
        return getWeatherService.getWeatherInfo(requestStadiumInfo);
    }
    @PostMapping("/weekly")
    public WeatherWeeklyApiResponse getWeeklyWeather(@RequestBody RequestStadium request) {
        return getWeatherService.getWeeklyWeatherInfo(request);
    }
    @PostMapping("/todaygames")
    public GameApiResponse getGameData(@RequestBody RequestGameInfo request) {
        return getWeatherService.getGameInfo(request);
    }



//@GetMapping("/currenttest")
//public Map<String,Object> getCurrentWeatherTest(@RequestBody RequestStadiumInfo requestStadiumInfo) {
//    Map<String,Object> response = new Map()<>;
    // response.put("stadium:",stadium)
//    return getWeatherService.getWeatherInfo(requestStadiumInfo);
//}


}
