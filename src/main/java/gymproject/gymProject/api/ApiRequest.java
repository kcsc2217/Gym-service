package gymproject.gymProject.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRequest {
    public static String getApiResponse(String apiKey) throws Exception {
        String baseUrl = "https://api.odcloud.kr/api";
        String namespaceVersion = "15077386/v1";
        String endpoint = "uddi:a98f158b-2bd8-45f2-a9c5-847af3adc35b";
        String urlString = String.format("%s/%s/%s?page=1&perPage=100&serviceKey=%s", baseUrl, namespaceVersion, endpoint, apiKey);
        URL url = new URL(urlString);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("accept", "*/*");

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HTTP GET Request Failed with Error code : " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        conn.disconnect();

        return response.toString();
    }
}
