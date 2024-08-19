package ballpark.weather.todaygamedto;

import java.util.List;

public record GameApiResponse(
        int dateDiff,
        List<GameList> gameList
) {
    public record GameList(

    ) {

    }
}
