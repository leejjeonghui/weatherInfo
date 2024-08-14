package ballpark.weather;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

}
