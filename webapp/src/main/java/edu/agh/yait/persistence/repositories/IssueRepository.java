package edu.agh.yait.persistence.repositories;

import edu.agh.yait.persistence.model.Issue;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Krzysztof Podsiadło on 26.04.17.
 */
public interface IssueRepository extends CrudRepository<Issue, Integer> {
}
