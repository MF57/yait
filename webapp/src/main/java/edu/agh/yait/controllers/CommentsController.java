package edu.agh.yait.controllers;


import edu.agh.yait.dto.CommentDTO;
import edu.agh.yait.persistence.model.Comment;
import edu.agh.yait.persistence.model.Issue;
import edu.agh.yait.persistence.model.User;
import edu.agh.yait.persistence.repositories.CommentRepository;
import edu.agh.yait.persistence.repositories.IssueRepository;
import edu.agh.yait.persistence.repositories.UserRepository;
import edu.agh.yait.security.TokenAuthenticationService;
import edu.agh.yait.utils.CustomErrorObject;
import edu.agh.yait.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUtils userUtils;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object getComments(@PathVariable("issueId") String issueId) {
        List<Comment> comments = commentRepository.findAllByIssueId(Integer.parseInt(issueId));
        comments.forEach(comment -> userUtils.fetchInformation(comment.getAuthor()));
        return comments;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object addComment(@Valid @RequestBody CommentDTO commentDto,
                             @RequestHeader HttpHeaders header,
                             Errors result,
                             @PathVariable("issueId") String issueId) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Issue issue = issueRepository.findOne(Integer.parseInt(issueId));
        if (issue == null) {
            return ResponseEntity.badRequest().body(new CustomErrorObject("Issue, id: " + issueId + ", does not exists"));
        }

        String token = header.get("Authorization").get(0);
        String userLdapLogin = TokenAuthenticationService.parseTokenLdapLogin(token);

        User author = new User();
        author.setLogin(userLdapLogin);
        userUtils.fetchInformation(author);
        if (userRepository.findOne(author.getLdapId()) == null) {
            userRepository.save(author);
        }

        List<Comment> comments = commentRepository.findAllByIssueId(Integer.parseInt(issueId));
        for(Comment commentTemp : comments) {
            if (commentTemp.getAuthor().getLdapId().equals(author.getLdapId())) {
                return ResponseEntity.badRequest().body(new CustomErrorObject("You have already written a comment"));
            }
        }

        comments.forEach(comment -> userUtils.fetchInformation(comment.getAuthor()));

        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setIssueId(Integer.parseInt(issueId));

        comment.setAuthor(author);
        return commentRepository.save(comment);
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
    public Object getCommentById(@PathVariable("issueId") String issueId,
                                 @PathVariable("commentId") String commentId) {

        Comment comment = commentRepository.findOne(Integer.parseInt(commentId));
        userUtils.fetchInformation(comment.getAuthor());
        return comment;
    }
}
