package com.project.controllers;

import com.project.entites.Like;
import com.project.entites.Post;
import com.project.repos.LikeRepository;
import com.project.requests.LikeCreateRequest;
import com.project.services.LikeServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private LikeServices likeServices;

    public LikeController(LikeServices likeServices) {
        this.likeServices = likeServices;
    }

    @GetMapping
    public List<Like> getAllLikes(@RequestParam Optional<Long> postId,@RequestParam Optional<Long> userId){
        return likeServices.getAllLikesWithParam(postId,userId);
    }
    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest likeCreateRequest){
        return likeServices.createOneLike(likeCreateRequest);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeServices.getOneLikeById(likeId);
    }
    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
        likeServices.deleteOneLikeById(likeId);
    }

}
