//package ballpark.weather;
//
//import org.json.JSONObject;
//import org.springframework.boot.json.JacksonJsonParser;
//import org.springframework.boot.json.JsonParser;
//import org.springframework.stereotype.Service;
//
//@Service
//public class JsonConvertTest {
//    private CreateClient createClient;
//
//    public JsonConvertTest(CreateClient createClient) {
//        this.createClient = createClient;
//    }
//
//    public void jsonTest(RequestStadiumInfo request) {
//
//        String result = createClient.getApi(request.stadium(), request.home(), request.away(), request.leid());
//
//        JSONObject jsonObject = new JSONObject(result);
//
//        System.out.println(jsonObject.getString("stadium"));
//        System.out.println(jsonObject.getString("stadiumCode"));
//        System.out.println(jsonObject.getString("date"));
//        System.out.println(jsonObject.getString("castDate"));
//        System.out.println(jsonObject.getString("awayCode"));
//        System.out.println(jsonObject.getString("awayTeam"));
//        System.out.println(jsonObject.getString("homeCode"));
//        System.out.println(jsonObject.getString("homeTeam"));
//
//
//
//
//    }
//
//}
