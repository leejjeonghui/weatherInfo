package ballpark.weather.todaygamedto;

import java.util.List;

public record GameApiResponse(int dateDiff, List<Game> gameList) {
    public record Game(
            String stadium,
            String stadiumFullName,
            String homeCode,
            String homeName,
            String awayCode,
            String awayName,
            String gameTime,
            int gameSc,
            int cancelSc,
            String icon,
            String iconName,
            float temp,
            int rain,
            String gameIcon,
            String gameIconName,
            float gameTemp,
            int gameRain,
            String gameTimeOver,
            String gameId,
            String dust,
            String dustCode
    ) {}
}

