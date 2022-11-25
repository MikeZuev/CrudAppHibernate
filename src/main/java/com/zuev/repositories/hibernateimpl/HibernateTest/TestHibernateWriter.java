package com.zuev.repositories.hibernateimpl.HibernateTest;

import com.zuev.entities.Writer;
import com.zuev.repositories.hibernateimpl.HibernateWriterRepository;
import org.junit.Test;

import java.util.List;

public class TestHibernateWriter {
    HibernateWriterRepository hibernateWriterRepository = new HibernateWriterRepository();

    @Test
    public void TestWriterGetById(){
        Writer writer = hibernateWriterRepository.getByld(1L);
        System.out.println(writer.toString());
        List posts = writer.getPosts();
        for(Object post : posts){
            System.out.println(post.toString());
        }
    }

    @Test
    public void TestWriterGetList(){
        List writers = hibernateWriterRepository.getAll();
        for(Object writer : writers){
            System.out.println(writer.toString());
        }
    }
}
