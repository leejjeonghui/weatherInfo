package ballpark.weather.todayweatherinfodto;

public record WeatherApiResponse(
        String stadium, String stadiumCode, String date, String castDate, String awayCode,
        String AwayTeam, String homeCode, String homeTeam, String icon, String iconName,
        float temp,
        String dust, String dustIcon, String microDust, String microDustIcon, String rainIcon, String rain,
        String humiIcon, float humi, float windIcon, float wind, String today, String todayTime, String tomorrow,
        String tomorrowTime, String aftertomorrow, String aftertomorrowTime

) {

}