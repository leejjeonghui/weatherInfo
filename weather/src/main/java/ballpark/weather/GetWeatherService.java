package ballpark.weather;

import ballpark.weather.todaygamedto.GameApiResponse;
import ballpark.weather.todaygamedto.RequestGameInfo;
import ballpark.weather.todayweatherinfodto.RequestStadiumInfo;
import ballpark.weather.todayweatherinfodto.WeatherApiResponse;
import ballpark.weather.weeklyweatherinfodto.RequestStadium;
import ballpark.weather.weeklyweatherinfodto.WeatherWeeklyApiResponse;
import org.springframework.stereotype.Service;

@Service
public class GetWeatherService {
    private CreateClient createClient;

    public GetWeatherService(CreateClient createClient) {
        this.createClient = createClient;
    }

    //실시간 데이터 추출
    public WeatherApiResponse getWeatherInfo(RequestStadiumInfo requestStadiumInfo) {
        WeatherApiResponse responseData = createClient.getCurrentWeatherApi(
                requestStadiumInfo.stadium(),
                requestStadiumInfo.home(),
                requestStadiumInfo.away(),
                requestStadiumInfo.leid());
        return responseData;

    }

    public WeatherWeeklyApiResponse getWeeklyWeatherInfo(RequestStadium request) {
        WeatherWeeklyApiResponse responseWeekdata = createClient.getWeeklyWeatherApi(request.stadium());
        return responseWeekdata;
    }

    public GameApiResponse getGameInfo(RequestGameInfo request) {
        GameApiResponse gameApiResponse = createClient.getGameInfoApi(request.gameDate(),request.leId(),request.srId(), request.headerCk());
        return gameApiResponse;
    }
}
