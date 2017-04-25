package edu.agh.yait.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/issue/{issueId}/comment")
public class CommentsController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object getComments(){
        return "list of comments";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object addComment(){
        return "commented";
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
    public Object getCommentById(@PathVariable("issueId") String issueId,
                               @PathVariable("commentId") String commentId){
        return "issue: " + issueId + ", comment: " + commentId;
    }
}
