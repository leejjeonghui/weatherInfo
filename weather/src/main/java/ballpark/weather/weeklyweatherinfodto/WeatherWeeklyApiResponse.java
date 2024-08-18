package ballpark.weather.weeklyweatherinfodto;

import java.util.List;

public record WeatherWeeklyApiResponse(
        String regDt,
        double height,
        List<Weather> weatherList
) {
    public record Weather(
            String day,
            String iconCd,
            String iconName,
            double lowValue,
            double maxValue,
            int tempMax,
            int tempMin,
            int rain) {
    }
}
