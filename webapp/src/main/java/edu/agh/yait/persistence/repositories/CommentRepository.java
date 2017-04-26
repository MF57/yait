package edu.agh.yait.persistence.repositories;

import edu.agh.yait.persistence.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findByComment(String comment);
}
