package ballpark.weather;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@SpringBootTest
class WeatherApplicationTests {

    @Test
    void contextLoads() {

        Logger logger = LoggerFactory.getLogger(RestClient.class);

        String baseUrl = "http://apis.data.go.kr";
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        RestClient restClient = RestClient.builder()
                .uriBuilderFactory(factory)
                .requestInterceptor((request, body, execution) -> {
                    logger.info("다음 URL로 요청 보냄: " + request.getURI());
                    return execution.execute(request, body);
                })
                .build();

        final String SERVICE_KEY
                = "fan6qvV7uujFYDrKX0KW%2BnHIexl%2FmW29PrscapMn5YsWn4ALH0u9LOd%2BFimSjEi37%2FvPjCPNYXEH6IkfdAHjBw%3D%3D";

        String body = restClient.get()
                .uri(UriBuilder -> UriBuilder
                        .path("/1360000/VilageFcstInfoService_2.0/getVilageFcst")
                        .queryParam("ServiceKey", SERVICE_KEY)
                        .queryParam("pageNo", 1)
                        .queryParam("numOfRows", 10)
                        .queryParam("dataType", "JSON")
                        .queryParam("base_date", "20240814")
                        .queryParam("base_time", "0800")
                        .queryParam("nx", 55)
                        .queryParam("ny", 127)
                        .build())
                .retrieve()
                .body(String.class);

        System.out.println("body = " + body);

    }
//    @Test
//    public void jsonTest(String stadium,
//                         String home,
//                         String away,
//                         String leid) {
//        String result = createClient.getApi(stadium,home,away,leid);
//
//        JSONObject jsonObject = new JSONObject(result);
//
//        System.out.println(jsonObject.getString("stadium"));
//        System.out.println(jsonObject.getString("stadiumCode"));
//        System.out.println(jsonObject.getString("date"));
//        System.out.println(jsonObject.getString("castDate"));
//        System.out.println(jsonObject.getString("awayCode"));
//        System.out.println(jsonObject.getString("awayTeam"));
//        System.out.println(jsonObject.getString("homeCode"));
//        System.out.println(jsonObject.getString("homeTeam"));
//
//
//
//
//    }

}
