package ballpark.weather;

import ballpark.weather.todaygamedto.GameApiResponse;
import ballpark.weather.todayweatherinfodto.WeatherApiResponse;
import ballpark.weather.weeklyweatherinfodto.WeatherWeeklyApiResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateClient {

    private static final Logger logger =
            LoggerFactory.getLogger(CreateClient.class);
    public WeatherApiResponse getCurrentWeatherApi(String stadium,
                                                   String home,
                                                   String away,
                                                   String leid) {
        Logger logger = LoggerFactory.getLogger(RestClient.class);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("stadium", stadium);
        formData.add("home", home);
        formData.add("away", away);
        formData.add("leid", leid);

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

    public WeatherWeeklyApiResponse getWeeklyWeatherApi(String stadium) {

        return new WeatherWeeklyApiResponse();
    }

    public GameApiResponse getGameInfoApi(String gameDate, String leId, String srId, String headerCk) {
        try {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("gameDate", gameDate);
            formData.add("leId", leId);
            formData.add("srId", srId);
            formData.add("headerCk", headerCk);

            RestClient restClient = RestClient.create();
            String body = restClient.post()
                    .uri("https://www.koreabaseball.com/ws/Schedule.asmx/GetTodayGames")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(formData)
                    .retrieve()
                    .body(String.class);

            System.out.println("body = " + body);

            JSONObject jsonObject = new JSONObject(body);

            int dateDiff = jsonObject.getInt("dateDiff");
            List<GameApiResponse.Game> gameList = new ArrayList<>();

            JSONArray gamesArray = jsonObject.getJSONArray("gameList");
            for (int i = 0; i < gamesArray.length(); i++) {
                JSONObject gameObject = gamesArray.getJSONObject(i);
                gameList.add(new GameApiResponse.Game(
                        gameObject.getString("stadium"),
                        gameObject.getString("stadiumFullName"),
                        gameObject.getString("homeCode"),
                        gameObject.getString("homeName"),
                        gameObject.getString("awayCode"),
                        gameObject.getString("awayName"),
                        gameObject.getString("gameTime"),
                        gameObject.getInt("gameSc"),
                        gameObject.getInt("cancelSc"),
                        gameObject.getString("icon"),
                        gameObject.getString("iconName"),
                        gameObject.getFloat("temp"),
                        gameObject.getInt("rain"),
                        gameObject.getString("gameIcon"),
                        gameObject.getString("gameIconName"),
                        gameObject.getFloat("gameTemp"),
                        gameObject.getInt("gameRain"),
                        gameObject.getString("gameTimeOver"),
                        gameObject.getString("gameId"),
                        gameObject.getString("dust"),
                        gameObject.getString("dustCode")
                ));
            }

            return new GameApiResponse(dateDiff, gameList);
        } catch (JSONException e) {
            logger.error("JSON parsing error occurred: ", e);
            throw new RuntimeException("JSON parsing error", e);
        }
    }
}

