package top.weeki.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.weeki.service.PersonService;
import top.weeki.storage.entity.Person;

@RestController
@RequestMapping(path = "person")
public class PersonInfoController {
    Logger logger = LoggerFactory.getLogger(PersonInfoController.class);

    @Autowired
    PersonService personService;

    @PostMapping("save")
    public Integer save(
            @RequestBody JSONObject person
    ) {
        logger.info(person.toJSONString());
        Integer id = personService.savePserson(person);
        return id;
    }

    @GetMapping("get")
    public Person get(
            @RequestParam Integer id
    ) {
        return personService.getPerson(id);
    }
}
