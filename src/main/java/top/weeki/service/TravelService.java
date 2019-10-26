package top.weeki.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class TravelService {
    @Autowired
    RestTemplate restTemplate;

    public String generateTravelLine(JSONObject boundary) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("boundary", boundary.getString("boundary"));
        map.add("type", boundary.getString("type"));
        map.add("cityid", boundary.getString("cityid"));
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(
                map, headers);
        JSONObject response =
                restTemplate.postForObject("http://localhost:10070/daas/pd/visit/get", request, JSONObject.class);
        JSONArray features = response.getJSONObject("data").getJSONArray("features");

        JSONObject start = getCenterPoint(boundary.getJSONArray("boundary"));

        JSONArray lines = new JSONArray();

        for (int i = 0; i < features.size(); i++) {
            JSONObject line = new JSONObject();
            line.put("start", start);
            JSONObject end = getCenterPoint(features.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates"));
            line.put("end", end);

            lines.add(line);
        }
        return null;
    }

    private JSONObject getCenterPoint(JSONArray boundary) {
        double lng = 0;
        double lat = 0;
        for (int i = 0; i < boundary.size() - 1; i++) {
            lng += boundary.getJSONArray(i).getDouble(0);
            lng += boundary.getJSONArray(i).getDouble(1);
        }
        lng = lng / (boundary.size() - 1);
        lat = lat / (boundary.size() - 1);

        JSONObject point = new JSONObject();
        point.put("lng", lng);
        point.put("lat", lat);
        return point;
    }
}
