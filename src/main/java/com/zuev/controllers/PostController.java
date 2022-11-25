package com.zuev.controllers;

import com.zuev.entities.Label;
import com.zuev.entities.Post;
import com.zuev.repositories.PostRepository;
import com.zuev.repositories.impl.JsonPostRepositoryImpl;
import com.zuev.services.ServiceForPost;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PostController {


    private Scanner scanner;

    ServiceForPost serviceForPost;

    public PostController(ServiceForPost serviceForPost){
        this.serviceForPost = serviceForPost;
    }



    public Post getPostById(Long id) {
        return serviceForPost.getByld(id);
    }

    public List<Post> getAllPosts() {
       return serviceForPost.getAll();
    }

    public Post updatePost(Post post) {
       return serviceForPost.update(post);
    }

    public Post savePost(String content, List<Label> labels){
        Post newPost = new Post(content, new Date(), new Date(), labels);
        return serviceForPost.save(newPost);

    }

    public void deletePost(Long id) {

        serviceForPost.deleteByld(id);
    }
}
