package edu.agh.yait.persistence.repositories;

import edu.agh.yait.persistence.model.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by krzysiek on 26.04.17.
 */
public interface TicketRepository extends CrudRepository<Comment, Integer> {
}
