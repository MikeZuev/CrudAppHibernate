package com.zuev.controllers;

import com.zuev.entities.Label;
import com.zuev.repositories.LabelRepository;
import com.zuev.repositories.impl.JsonLabelRepositoryImpl;
import com.zuev.services.ServiceForLabel;

import java.util.List;
import java.util.Scanner;

public class LabelController {



    private final ServiceForLabel serviceForLabel;

    public LabelController(ServiceForLabel serviceForLabel){
        this.serviceForLabel = serviceForLabel;
    }

    Scanner scanner = new Scanner(System.in);

    public Label createLabel(String name) {
        Label label = new Label(name);
        serviceForLabel.save(label);

        return label;

    }

    public Label getLabelById(long id){
        return serviceForLabel.getByld(id);

    }

    public List<Label> getAllLabels(){
        List<Label> all = serviceForLabel.getAll();
        for(Label label : all){
            System.out.println(label.toString());
        }
        return all;
    }

    public void deleteLabelById(Long id) {

        serviceForLabel.deleteByld(id);
    }

    public Label updateLabel(Label label){
       return  serviceForLabel.update(label);



        
    }
}
