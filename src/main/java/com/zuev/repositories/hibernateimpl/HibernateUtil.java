package com.zuev.repositories.hibernateimpl;


import com.zuev.entities.Label;
import com.zuev.entities.Post;
import com.zuev.entities.Writer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;


public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static Properties properties = new Properties();



    private HibernateUtil(){

    }


    public static synchronized SessionFactory getSessionFactory(){
        try{
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));

        } catch(Exception e){

        }

        if(sessionFactory == null){
            sessionFactory = new Configuration().mergeProperties(properties)
                    .addAnnotatedClass(Label.class)
                    .addAnnotatedClass(Post.class)
                    .addAnnotatedClass(Writer.class)
            .buildSessionFactory();

        }
        return sessionFactory;
    }


}
