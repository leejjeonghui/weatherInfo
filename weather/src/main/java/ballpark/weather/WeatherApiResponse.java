package ballpark.weather;

import java.util.List;

public record WeatherApiResponse(
        Response response) {

    public record Response(
//            Header header,
            Body body) {}
//
//    public record Header(
//            String resultCode,
//            String resultMsg) {}

    public record Body(
            String dataType,
            Items items,
            int pageNo,
            int numOfRows,
            int totalCount) {}

    public record Items(
            List<Item> item) {}

    public record Item(
            String baseDate,
            String baseTime,
            String category,
            int nx,
            int ny,
            String obsrValue
    ) {}
}