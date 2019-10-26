package top.weeki.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.weeki.storage.entity.Person;
import top.weeki.storage.repository.PersonRepository;

import java.util.Calendar;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public Integer savePserson(JSONObject person) {
        Person p = new Person();
        p.setId(person.getInteger("id"));
        p.setName(person.getString("name"));
        p.setAge(person.getInteger("age"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) - p.getAge());
        p.setBirthday(calendar.getTime());
        personRepository.save(p);
        return p.getId();
    }

    public Person getPerson(Integer id) {
        Person p = personRepository.findById(id).orElse(null);
        return p;
    }
}
