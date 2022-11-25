package com.zuev.services.impl;

import com.zuev.entities.Post;
import com.zuev.repositories.PostRepository;
import com.zuev.services.ServiceForPost;

import java.util.List;

public class ServiceForPostsImpl implements ServiceForPost {

    private final PostRepository postRepository;

    public ServiceForPostsImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }





    @Override
    public Post getByld(Long aLong) {
        return postRepository.getByld(aLong);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.getAll();
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post update(Post post) {
        return postRepository.update(post);
    }

    @Override
    public void deleteByld(Long aLong) {
        postRepository.deleteByld(aLong);

    }
}
