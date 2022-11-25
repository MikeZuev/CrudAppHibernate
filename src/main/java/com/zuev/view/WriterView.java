package com.zuev.view;

import com.zuev.controllers.WriterController;
import com.zuev.entities.Writer;
import com.zuev.repositories.databaseimpl.MySqlWriterRepositoryImpl;
import com.zuev.services.impl.ServiceForWritersImpl;

import java.util.Scanner;

public class WriterView {
    private final Scanner scanner = new Scanner(System.in);

    WriterController writerCon;

    public WriterView() {
        writerCon = new WriterController(new ServiceForWritersImpl(new MySqlWriterRepositoryImpl()));
    }


    public void writerViewHandler() {
        System.out.println("Press 1 if you want to add new writer.\n " +
                "Press 2 if yuu want to get a writer by id\n" +
                "Press 3 if you want to get the list of all writers" +
                "Press 4 if you want to update the writer" +
                "press 5 if you want to delete the writer by id");
        int number = new Scanner(System.in).nextInt();
        switch (number = scanner.nextInt()) {
            case 1:
                createWriter();
                break;
            case 2:
                getWriterById();
                break;
            case 3:
                getAllWriters();
                break;
            case 4:
                deleteWriterById();
                break;
            case 5:
                updateWriter();
                break;


        }
    }


    private void createWriter() {
        System.out.println("Enter the writer's firstName: ");
        String firstName = new Scanner(System.in).nextLine();
        System.out.println("Enter writer's lastName: ");
        String lastName = new Scanner(System.in).nextLine();
        writerCon.createWriter(firstName, lastName);

    }

    private void getWriterById() {
        System.out.println("Enter the writer's id you want to get");
        long id = scanner.nextLong();
        System.out.println(writerCon.getWriterById(id));
    }

    private void getAllWriters() {
        System.out.println("Here the list of the writers");
        System.out.println(writerCon.getAllWriters());

    }

    private void deleteWriterById() {
        System.out.println("Enter the writer's id you want to delete");
        long id = scanner.nextLong();
        writerCon.deleteWriterById(id);
    }

    private void updateWriter() {

        System.out.println("Enter writer's id: ");
        Long id = scanner.nextLong();
        Writer writer = writerCon.getWriterById(id);

        System.out.println("Options you want to update: \n" +
                "Press 1 - if you want to update writer's first name \n" +
                "Press 2 - if you want to update writer's last name  \n" +
                "Press 3 - if you want to update writer's first name and last name");
        int number = scanner.nextInt();


        switch (number) {
            case 1:
                System.out.println("Enter name: ");
                String name = new Scanner(System.in).nextLine();
                writer.setFirstName(name);
                writerCon.updateWriter(writer);
                break;
            case 2:
                System.out.println("Enter surname: ");
                String surname = new Scanner(System.in).nextLine();
                writer.setLastName(surname);
                writerCon.updateWriter(writer);
                break;
            case 3:
                System.out.println("Enter name: ");
                String firstName = new Scanner(System.in).nextLine();
                writer.setFirstName(firstName);
                System.out.println("Enter surname: ");
                String lastName = new Scanner(System.in).nextLine();
                writer.setLastName(lastName);
                writerCon.updateWriter(writer);
        }


    }
}
