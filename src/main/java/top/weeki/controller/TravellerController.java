package top.weeki.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.weeki.service.TravelService;

@RestController
@RequestMapping("travel")
public class TravellerController {
    @Autowired
    TravelService travelService;

    @PostMapping("generate")
    public String generateTravel(
            @RequestBody JSONObject json
    ) {
        return travelService.generateTravelLine(json);
    }
}
