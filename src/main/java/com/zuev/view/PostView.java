package com.zuev.view;

import com.zuev.controllers.PostController;
import com.zuev.entities.Label;
import com.zuev.entities.Post;
import com.zuev.repositories.databaseimpl.MySqlPostRepositoryImpl;
import com.zuev.services.impl.ServiceForPostsImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView {

    private final PostController postCon;

    public PostView(){
        postCon = new PostController(new ServiceForPostsImpl(new MySqlPostRepositoryImpl()));
    }

    private Scanner scanner = new Scanner(System.in);


    public void postViewHandler() {
        System.out.println("Press - 1 if you want to add new post \n" +
                "Press - 2 if you want to get the post by id \n" +
                "Press - 3 if you want to get all posts" +
                "Press - 4 if you want to update the post " +
                "Press - 5 if you want to delete the post");

        int num = scanner.nextInt();

        switch(num) {
            case 1:
                addPost();
                break;
            case 2:
                postById();
                break;
            case 3:
                getFullListOfPosts();
                break;
            case 4:
                updatePost();
                break;
            case 5:
                deletePost();
                break;

        }

    }

    private void addPost() {
        System.out.println("Enter the post's content you want to add: ");
        String content = new Scanner(System.in).nextLine();
        List<Label> labels = new ArrayList<>();
        System.out.println("Enter the label's name for this post");
        while(true){
            String name = new Scanner(System.in).nextLine();
            if(name != null){
                Label newLabel = new Label(name);
                labels.add(newLabel);
            }
            else {
                break;
            }
        }
        postCon.savePost(content, labels);


    }

    private void postById(){
        System.out.println("Enter the post's id: ");
        Long id = new Scanner(System.in).nextLong();
        System.out.println(postCon.getPostById(id));


    }

    private void getFullListOfPosts() {
        System.out.println("Here the list of all posts: ");
        System.out.println(postCon.getAllPosts());

    }

    private void updatePost() {
        System.out.println("Enter the post's id you want to update: ");
        Long id = new Scanner(System.in).nextLong();
        Post newPost = postCon.getPostById(id);
        System.out.println("Press 1 if you want to update content: " +
                "Press 2 if you want to add labels to the list.");
        int num = new Scanner(System.in).nextInt();
        switch(num){
            case (1):
                System.out.println("Enter the text for post's content: ");
                String content = new Scanner(System.in).nextLine();
                newPost.setContent(content);
                break;
            case (2):
                System.out.println("Enter new label");
                while(true){
                    String name = new Scanner(System.in).nextLine();
                    if(name != null){
                        Label newLabel= new Label(name);
                        newPost.getLabels().add(newLabel);
                        postCon.updatePost(newPost);
                    }
                    else {
                        break;
                    }
                }
        }

        System.out.println(postCon.updatePost(newPost));

    }




    private void deletePost() {
        System.out.println("Enter the post's id you want to delete: ");
        Long id = new Scanner(System.in).nextLong();
        postCon.deletePost(id);
    }
}
