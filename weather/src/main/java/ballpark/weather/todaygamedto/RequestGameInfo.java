package ballpark.weather.todaygamedto;

public record RequestGameInfo(
        String gameDate,
        String leId,
        String srId,
        String headerCk
) {
}
