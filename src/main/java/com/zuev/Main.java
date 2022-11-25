package com.zuev;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zuev.controllers.WriterController;
import com.zuev.entities.Writer;
import com.zuev.repositories.hibernateimpl.HibernateUtil;
import com.zuev.repositories.impl.JsonWriterRepositoryImpl;
import com.zuev.view.LabelView;
import com.zuev.view.PostView;
import com.zuev.view.WriterView;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
   /*     String str;
        Scanner scanner = new Scanner(System.in);
        WriterView writerView = new WriterView();
        PostView postView = new PostView();
        LabelView labelView = new LabelView();

        System.out.println("Enter entity you want to work with: \n" +
                "Press - 1 if yuo want to work with Writer\n" +
                "Press - 2 if you want to work with Post \n" +
                "Press - 3 if you want to work with Label");

        int number = scanner.nextInt();

        switch(number) {

            case 1:
                writerView.writerViewHandler();
                break;
            case 2:
                postView.postViewHandler();
                break;
            case 3:
                labelView.labelViewHandler();
                break;
        }

        */
        Properties properties = new Properties();
        try{
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        } catch(Exception e){

        }

        SessionFactory sessionFactory = new Configuration().mergeProperties(properties).buildSessionFactory();
        Session session = sessionFactory.openSession();
        System.out.println(session.isConnected());


    }
}