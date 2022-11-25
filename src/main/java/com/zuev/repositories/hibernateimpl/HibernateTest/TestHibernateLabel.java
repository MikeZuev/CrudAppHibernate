package com.zuev.repositories.hibernateimpl.HibernateTest;

import com.zuev.entities.Label;
import com.zuev.entities.Post;
import com.zuev.repositories.LabelRepository;
import com.zuev.repositories.hibernateimpl.HibernateLabelRepository;
import com.zuev.repositories.hibernateimpl.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class TestHibernateLabel {

    private HibernateLabelRepository hibernateLabelRepository = new HibernateLabelRepository();
    @Test
    public void TestGetById() {
        Label newLabel = hibernateLabelRepository.getByld(3L);
        System.out.println(newLabel.toString());
        List posts = newLabel.getPosts();
        for(Object post : posts){
            System.out.println(post.toString());

        }
        System.out.println(newLabel.toString());
    }

    @Test
    public void TestHibernateConnecton() {
        HibernateUtil hibernateUtil = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println(session.isConnected());

    }

    @Test
    public void TestHibernateLabelGetList() {

        List<Label> labels = hibernateLabelRepository.getAll();
        labels.forEach(System.out::println);


    }

    @Test
    public void TestHibernateLabelSave(){

        Label label = new Label("newLabeltoAdd-7");
        hibernateLabelRepository.save(label);

    }

    @Test
    public void TestHibernateLabelUpdate(){

        Label testLabel = new Label(5L, "iCouldUpdateIt");
        hibernateLabelRepository.update(testLabel);
    }

    @Test
    public void TestHibernateLabelDelete(){
        hibernateLabelRepository.deleteByld(6L);

    }
}
