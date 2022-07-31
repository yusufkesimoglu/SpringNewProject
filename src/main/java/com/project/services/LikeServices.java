package com.project.services;

import com.project.entites.Like;
import com.project.entites.Post;
import com.project.entites.User;
import com.project.repos.LikeRepository;
import com.project.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServices {
    private LikeRepository likeRepository;
    private UserServices userServices;
    private PostServices postServices;

    public LikeServices(LikeRepository likeRepository, UserServices userServices, PostServices postServices) {
        this.likeRepository = likeRepository;
        this.userServices = userServices;
        this.postServices = postServices;
    }

    public List<Like> getAllLikesWithParam(Optional<Long> postId, Optional<Long> userId) {
        if(postId.isPresent()&& userId.isPresent()){
            return likeRepository.findByUserIdAndPostId(postId.get(),userId.get());
        }else if(postId.isPresent()){
            return likeRepository.findByPostId(postId.get());
        }else if(userId.isPresent()){
            return likeRepository.findByUserId(userId.get());
        }else
            return likeRepository.findAll();
    }

    public Like createOneLike(LikeCreateRequest likeCreateRequest) {
        User user = userServices.getOneUserById(likeCreateRequest.getUserId());
        Post post = postServices.getOnePostById(likeCreateRequest.getPostId());
        if(user!=null&&post!=null){
            Like likeToSave = new Like();
            likeToSave.setId(likeCreateRequest.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            likeRepository.save(likeToSave);
            return likeToSave;

        }else
            return null;
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
