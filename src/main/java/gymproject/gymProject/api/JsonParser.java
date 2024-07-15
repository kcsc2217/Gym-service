package gymproject.gymProject.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import gymproject.gymProject.entity.Gym;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public static List<Gym> parseData(String jsonResponse){
        List<Gym> dataList = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray items = jsonObject.getJSONArray("data");

        for(int i = 0; i < items.length(); i++){
            JSONObject item = items.getJSONObject(i);
            Gym data = new Gym(item.getString("업체명"), item.getString("연락처"), item.getString("주소"));
            dataList.add(data);
        }
        return dataList;
    }
}
