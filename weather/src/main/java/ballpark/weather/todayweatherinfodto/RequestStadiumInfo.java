package ballpark.weather.todayweatherinfodto;

public record RequestStadiumInfo(
        String stadium,
        String home,
        String away,
        String leid
) {
}
