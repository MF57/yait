package edu.agh.yait.mailing.core.repositories;

/**
 * Created by marcinsendera on 09.05.2017.
 */
import edu.agh.yait.mailing.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
