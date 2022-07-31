package com.project.services;

import com.project.entites.Comment;
import com.project.entites.Post;
import com.project.entites.User;
import com.project.repos.CommentRepository;
import com.project.requests.CommentCreateRequest;
import com.project.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionException;

@Service
public class CommentServices {

    private CommentRepository commentRepository;
    private UserServices userServices;
    private PostServices postServices;

    public CommentServices(CommentRepository commentRepository, UserServices userServices, PostServices postServices) {
        this.commentRepository = commentRepository;
        this.userServices = userServices;
        this.postServices = postServices;
    }

    public List<Comment> getAllPostCommentsWithParam(Optional<Long> postId, Optional<Long> userId) {
       if(postId.isPresent()&& userId.isPresent()){
           return commentRepository.findByPostIdAndUserId(postId.get(),userId.get());
       }else if(postId.isPresent()){
           return commentRepository.findByPostId(postId.get());
       }else if(userId.isPresent()){
           return commentRepository.findByUserId(userId.get());
       }else{
           return commentRepository.findAll();
       }
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest newCommentRequest) {
        User user = userServices.getOneUserById(newCommentRequest.getUserId());
        Post post =postServices.getOnePostById(newCommentRequest.getPostId());
        if(user!=null&&post!=null){
            Comment commentToSave = new Comment();
            commentToSave.setId(newCommentRequest.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(newCommentRequest.getText());
            commentRepository.save(commentToSave);
            return commentToSave;
        }else
            return null;
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            Comment updateToComment = comment.get();
            updateToComment.setText(commentUpdateRequest.getText());
            commentRepository.save(updateToComment);
            return updateToComment;

        }
        return null;

    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
