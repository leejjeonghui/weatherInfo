package ballpark.weather;

import com.fasterxml.jackson.core.JsonParser;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class GetWeatherService {
    private CreateClient createClient;

    public GetWeatherService(CreateClient createClient) {
        this.createClient = createClient;
    }

    //실시간 데이터 추출
    public WeatherApiResponse getWeatherInfo(RequestStadiumInfo requestStadiumInfo) {
        WeatherApiResponse responseData = createClient.getApi(
                requestStadiumInfo.stadium(),
                requestStadiumInfo.home(),
                requestStadiumInfo.away(),
                requestStadiumInfo.leid());
        return responseData;

    }

    public WeatherWeeklyApiResponse getWeeklyWeatherInfo(RequestStadium requestStadium) {
        WeatherWeeklyApiResponse responseWeekdata = createClient.getWeeklyApi(requestStadium.stadium());
        return responseWeekdata;
    }

}
