package com.zuev.view;

import com.zuev.controllers.LabelController;
import com.zuev.entities.Label;
import com.zuev.repositories.databaseimpl.MySqlLabelRepositoryImpl;
import com.zuev.services.impl.ServiceForLabelsImpl;

import java.util.Scanner;

public class LabelView {
    private final LabelController labelCon;

    public LabelView(){
        labelCon = new LabelController(new ServiceForLabelsImpl(new MySqlLabelRepositoryImpl()));
    }
    private Scanner scanner = new Scanner(System.in);

    public void labelViewHandler() {
        System.out.println("Press - 1 if you want to add a new label \n" +
                "Press - 2 if you want to get a label by id \n" +
                "Press - 3 if you want to get all labels \n" +
                "Press - 4 if you want to update the label \n" +
                "Press - 5 if you want to delete the label");
        int number = scanner.nextInt();

        switch(number) {
            case 1:
                addLabel();
                break;
            case 2:
                getLabelById();
                break;
            case 3:
                getAllLabels();

                break;
            case 4:
               updateLabel();
                break;
            case 5:
                deleteLabel();
                break;
        }
    }



    private void addLabel() {
        System.out.println("Enter label's name which you want to add: ");
        String name = new Scanner(System.in).nextLine();
        labelCon.createLabel(name);

    }

    private void getLabelById(){
        System.out.println("Enter the label's id you want to get: ");
        Long id = new Scanner(System.in).nextLong();
        labelCon.getLabelById(id);
    }

    private void getAllLabels(){
        System.out.println("Here the list of all labels: ");
        System.out.println(labelCon.getAllLabels());
    }

    private void updateLabel(){
        System.out.println("Enter the label's id you want to update: ");
        Long id = new Scanner(System.in).nextLong();
        System.out.println("Enter the label's new name: ");
        String name = new Scanner(System.in).nextLine();
        Label newLabel = new Label(name);
        newLabel.setId(id);
        System.out.println(labelCon.updateLabel(newLabel));

    }


    private void deleteLabel(){
        System.out.println("Enter the label's id you want to delete: ");
        Long id = new Scanner(System.in).nextLong();
        labelCon.deleteLabelById(id);

    }
}
