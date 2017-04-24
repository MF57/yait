package edu.agh.yait;

import edu.agh.yait.persistence.model.ExampleEntity;
import edu.agh.yait.persistence.repositories.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * Created by mf57 on 05.04.2017.
 */
@RestController
@RequestMapping("/api")
public class HelloController {
    @Autowired
    ExampleRepository repository;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        Iterable<ExampleEntity> entities = repository.findAll();
        StringBuilder builder = new StringBuilder();
        for (ExampleEntity entity : entities) {
            builder.append(entity.getDescription());
            builder.append("\n");
        }
        return "Hello World\n" + builder.toString();
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String hello2() {
        ExampleEntity entity = new ExampleEntity();
        entity.setDescription(String.valueOf(new Random().nextInt()));
        repository.save(entity);
        return "Hello World";
    }

}
