package top.weeki.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
@RequestMapping(path = "/demo")
public class DemoController {

    @GetMapping("get")
    public JSONObject getDemo(
            @RequestParam String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(defaultValue = "1") int page
    ) {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("age", age);
        obj.put("birthday", Calendar.getInstance().get(Calendar.YEAR) - age);
        obj.put("page", page);
//        return "It is a demo. name=" + name + " and age=" + age;
        return obj;
    }
}
