package edu.agh.yait.persistence.repositories;


import edu.agh.yait.persistence.model.ExampleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExampleRepository extends CrudRepository<ExampleEntity, Integer>{
//    ExampleEntity save(ExampleEntity entity);
//    List<ExampleEntity> findAll();
}