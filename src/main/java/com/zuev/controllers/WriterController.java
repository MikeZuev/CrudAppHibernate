package com.zuev.controllers;

import com.zuev.entities.Post;
import com.zuev.entities.Writer;
import com.zuev.repositories.WriterRepository;
import com.zuev.repositories.impl.JsonWriterRepositoryImpl;
import com.zuev.services.ServiceForWriter;
import com.zuev.services.impl.ServiceForWritersImpl;

import java.util.List;
import java.util.Scanner;

public class WriterController {
    private Scanner scanner = new Scanner(System.in);

    private final ServiceForWriter serviceForWriter;

    public WriterController(ServiceForWriter serviceFirWriter){
        this.serviceForWriter = serviceFirWriter;
    }






    public Writer createWriter(String firstName, String lastName) {
        Writer newWriter = new Writer(firstName, lastName);


        return serviceForWriter.save(newWriter);
    }

    public Writer getWriterById(Long id) {
        return serviceForWriter.getByld(id);

    }
    
    public List<Writer> getAllWriters() {
        return serviceForWriter.getAll();
    }

    public void deleteWriterById(Long id) {

        serviceForWriter.deleteByld(id);
    }

    public Writer updateWriter(Writer writer) {
       return serviceForWriter.update(writer);

        }
}





