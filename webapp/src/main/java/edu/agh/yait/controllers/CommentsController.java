package edu.agh.yait.controllers;


import edu.agh.yait.dto.CommentDTO;
import edu.agh.yait.persistence.model.Comment;
import edu.agh.yait.persistence.model.Issue;
import edu.agh.yait.persistence.repositories.CommentRepository;
import edu.agh.yait.persistence.repositories.IssueRepository;
import edu.agh.yait.utils.CustomErrorObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/issues/{issueId}/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object getComments(@PathVariable("issueId") String issueId)
    {
        return commentRepository.findAllByIssueId(Integer.parseInt(issueId));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object addComment(@Valid @RequestBody CommentDTO commentDto,
                             Errors result,
                             @PathVariable("issueId") String issueId){

        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Issue issue = issueRepository.findOne(Integer.parseInt(issueId));
        if(issue == null) {
            return ResponseEntity.badRequest().body(new CustomErrorObject("Issue, id: " + issueId +  ", does not exists"));
        }

        Comment comment = new Comment();
        comment.setComment(commentDto.getText());
        comment.setIssueId(Integer.parseInt(issueId));

        return commentRepository.save(comment);
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
    public Object getCommentById(@PathVariable("issueId") String issueId,
                               @PathVariable("commentId") String commentId){

        //List<Comment> comments = commentRepository.findOne(Integer.parseInt(commentId));
        return commentRepository.findOne(Integer.parseInt(commentId));
    }
}
