package com.zuev.repositories.hibernateimpl.HibernateTest;

import com.zuev.entities.Label;
import com.zuev.entities.Post;
import com.zuev.entities.Writer;
import com.zuev.repositories.hibernateimpl.HibernateLabelRepository;
import com.zuev.repositories.hibernateimpl.HibernatePostRepository;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestHibernatePost {

    private HibernatePostRepository hibernatePostRepository = new HibernatePostRepository();

    @Test
    public void TestGetPostById(){
        Post newPost = hibernatePostRepository.getByld(1L);
        System.out.println(newPost.toString());

    }

    @Test
    public void TestGetAllPosts(){
        List posts = hibernatePostRepository.getAll();
        for(Object post: posts){
            System.out.println(post.toString());
        }
    }

    @Test
    public void TestSavePost(){

        Writer writer = new Writer(2,"Bob", "Green");
        Post newPost = new Post("Cats are the best", new Date(), new Date(), Arrays.asList(
                new Label("home"),
                new Label("animals")
        ), writer);


        hibernatePostRepository.save(newPost);

    }

    @Test
    public void TestDeletePost(){
        hibernatePostRepository.deleteByld(10L);
    }



}
