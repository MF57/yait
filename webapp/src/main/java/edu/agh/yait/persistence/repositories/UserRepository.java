package edu.agh.yait.persistence.repositories;

import edu.agh.yait.persistence.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Krzysztof Podsiadło on 26.04.17.
 */
public interface UserRepository extends CrudRepository<User, String> {
}
