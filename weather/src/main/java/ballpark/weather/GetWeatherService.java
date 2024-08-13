package ballpark.weather;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class GetWeatherService {
    private CreateClient createClient;

    public GetWeatherService(CreateClient createClient) {
        this.createClient = createClient;
    }

    public WeatherApiResponse getWeatherInfo(
                                         String base_date,
                                         String base_time,
                                         int nx,
                                         int ny) {
        WeatherApiResponse responseData = createClient.getApi(base_date,base_time, nx, ny);

        return responseData;

    }
}
