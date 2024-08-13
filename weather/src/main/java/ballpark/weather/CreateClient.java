package ballpark.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
public class CreateClient {
//    private String baseDate = "20240812";
//    private String baseTime = "0600";
//    private int nx = 37;
//    private int ny = 127;

    public WeatherApiResponse getApi(String baseDate, String baseTime, int nx, int ny) {
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

        WeatherApiResponse body = restClient.get()
                .uri(UriBuilder -> UriBuilder
                        .path("/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst")
                        .queryParam("serviceKey", SERVICE_KEY)
                        .queryParam("numOfRows", 10)
                        .queryParam("pageNo", 1)
                        .queryParam("dataType", "JSON")
                        .queryParam("base_date", baseDate)
                        .queryParam("base_time", baseTime)
                        .queryParam("nx", nx)
                        .queryParam("ny", ny)
                        .build())
                .retrieve()
                .body(WeatherApiResponse.class);

        System.out.println("body = " + body);
        return body;

//
//        return new WeatherApiResponse(
//                new WeatherApiResponse.Response(
//                        new WeatherApiResponse.Header(
//                                body.response().header().resultCode(),
//                                body.response().header().resultMsg()
//                        ),
//                        new WeatherApiResponse.Body(
//                                body.response().body().dataType(),
//                                body.response().body().items().item().stream().map(
//                                        i->i.
//                                )
//                                ,
//                                body.response().body().pageNo(),
//                                body.response().body().numOfRows(),
//                                body.response().body().totalCount()
//                        )
//                )
//        );
//        return new WeatherApiResponse(
//                new WeatherApiResponse.Response(
//                        new WeatherApiResponse.Header(
//                                body.response().header().resultCode(),
//                                body.response().header().resultMsg()
//                        ),
//                        new WeatherApiResponse.Body(
//                                body.response().body().dataType(),
//                                new WeatherApiResponse.Items(
//                                        new WeatherApiResponse.Item(
//                                                body.response().body().items().item()
//                                        )
//                                ) ,
//                                body.response().body().pageNo(),
//                                body.response().body().numOfRows(),
//                                body.response().body().totalCount()
//                        ),
//
//                )
//        );

    }
}
