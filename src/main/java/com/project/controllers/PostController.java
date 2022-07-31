package com.project.controllers;

import com.project.entites.Post;
import com.project.requests.PostCreateRequest;
import com.project.requests.PostUpdateRequest;
import com.project.services.PostServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostServices postServices;

    public PostController(PostServices postServices) {
        this.postServices = postServices;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId ){
        return postServices.getAllPosts(userId);

    }
    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return postServices.createOnePost(newPostRequest);
    }
    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postServices.getOnePostById(postId);

    }
    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId,@RequestBody PostUpdateRequest updatePost){
        return postServices.updateOnePostById(postId,updatePost);
    }
    @DeleteMapping("/postId")
    public void deleteOnePost(@PathVariable Long postId){
        postServices.deleteOnePostById(postId);
    }


}
