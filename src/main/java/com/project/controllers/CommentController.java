package com.project.controllers;


import com.project.entites.Comment;
import com.project.repos.CommentRepository;
import com.project.requests.CommentCreateRequest;
import com.project.requests.CommentUpdateRequest;
import com.project.services.CommentServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentServices commentServices;

    public CommentController(CommentServices commentServices) {
        this.commentServices = commentServices;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> postId,@RequestParam Optional<Long> userId ){
        return commentServices.getAllPostCommentsWithParam(postId,userId);
    }
    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest newCommentRequest){
        return commentServices.createOneComment(newCommentRequest);
    }
    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentServices.getOneCommentById(commentId);
    }
    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest){
        return commentServices.updateOneCommentById(commentId,commentUpdateRequest);
    }
    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentServices.deleteOneCommentById(commentId);
    }

}
