package ballpark.weather;

public record RequestStadiumInfo(
        String stadium,
        String home,
        String away,
        String leid
) {
}
