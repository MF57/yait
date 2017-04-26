package edu.agh.yait.controllers;


import edu.agh.yait.persistence.model.Comment;
import edu.agh.yait.persistence.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issue/{issueId}/comment")
public class CommentsController {
    @Autowired
    private CommentRepository repository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object getComments(){
        return repository.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object addComment(Comment comment){
        repository.save(comment);
        return "commented";
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
    public Object getCommentById(@PathVariable("issueId") String issueId,
                               @PathVariable("commentId") String commentId){
        return "issue: " + issueId + ", comment: " + commentId;
    }
}
