package ballpark.weather;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;




@Service
public class CreateClient {
    public WeatherApiResponse getApi(String stadium,
                                     String home,
                                     String away,
                                     String leid) {
        Logger logger = LoggerFactory.getLogger(RestClient.class);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("stadium",stadium);
        formData.add("home",home);
        formData.add("away",away);
        formData.add("leid",leid);

        RestClient restClient = RestClient.create();
        String body = restClient.post()
                .uri("https://www.koreabaseball.com/ws/Schedule.asmx/GetStadiumWeather")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(formData)
                .retrieve()
                .body(String.class);

        System.out.println("body = " + body);

        JSONObject jsonObject = new JSONObject(body);

        return new WeatherApiResponse(
                jsonObject.getString("stadium"),
                jsonObject.getString("stadiumCode"),
                jsonObject.getString("date"),
                jsonObject.getString("castDate"),
                jsonObject.getString("awayCode"),
                jsonObject.getString("awayTeam"),
                jsonObject.getString("homeCode"),
                jsonObject.getString("homeTeam"),
                jsonObject.getString("icon"),
                jsonObject.getString("iconName"),
                jsonObject.getFloat("temp"),
                jsonObject.getString("dust"),
                jsonObject.getString("dustIcon"),
                jsonObject.getString("microDust"),
                jsonObject.getString("microDustIcon"),
                jsonObject.getString("rainIcon"),
                jsonObject.getString("rain"),
                jsonObject.getString("humiIcon"),
                jsonObject.getFloat("humi"),
                jsonObject.getFloat("windIcon"),
                jsonObject.getFloat("wind"),
                jsonObject.getString("today"),
                jsonObject.getString("todayTime"),
                jsonObject.getString("tomorrow"),
                jsonObject.getString("tomorrowTime"),
                jsonObject.getString("aftertomorrow"),
                jsonObject.getString("aftertomorrowTime")
        );
    }

    public WeatherWeeklyApiResponse getWeeklyApi(String stadium) {

        return new WeatherWeeklyApiResponse()
                ;
    }
}
