package com.prodapt.learningspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodapt.learningspring.entity.Post;
import com.prodapt.learningspring.model.PostIdRequest;
import com.prodapt.learningspring.service.PostService;

@RestController
@RequestMapping("/forum/post")
@CrossOrigin(origins = "http://localhost:4200")
public class allPosts {

    @Autowired
    private PostService postService;


    @PostMapping("/user")
    public ResponseEntity<List<Post>> getPostsByUserId(@RequestBody PostIdRequest postIdRequest) {
        int postId = postIdRequest.getPostId();
        var displayPost = postService.getPostsByUserId(postId);
        return ResponseEntity.status(HttpStatus.OK).body(displayPost);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id){
        //int postId = postIdRequest.getPostId();
        var displayPost = postService.getPostsById(id);
        return ResponseEntity.status(HttpStatus.OK).body(displayPost);
    }
    
    
}
