package com.zuev.repositories.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zuev.entities.Post;
import com.zuev.entities.Writer;
import com.zuev.repositories.PostRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JsonPostRepositoryImpl implements PostRepository {

    public JsonPostRepositoryImpl(){}

    List<Post> posts = new ArrayList<>();

    private final String jsonPath = "src/main/resources/posts.json";


    @Override
    public Post save(Post post) {
        readListFromJson();
        
        
        if(posts.size() == 0){
            post.setId(1);
            posts.add(post);
            saveListToJson(posts);
        } else if (posts.size() >= 1){
            post.setId(posts.stream()
                    .map(Post::getId)
                    .max(Comparator.naturalOrder())
                    .get()
                    .intValue() + 1);
        }
        posts.add(post);
        saveListToJson(posts);
        
        return post;
    }

    @Override
    public Post getByld(Long aLong) {
       readListFromJson();
       Post result = null;
        for (Post post:posts) {
            if(post.getId() == aLong) {
                result = post;
            }
        }
        
        return result;
        
    }

    @Override
    public List<Post> getAll() {
        readListFromJson();

        return posts;
    }

    @Override
    public Post update(Post updatedPost) {
        readListFromJson();
        for(Post post : posts){
            if(post.getId() == post.getId()) {
                post.setUpdated(new Date());
                post.setContent(updatedPost.getContent());
                post.setLabels(updatedPost.getLabels());
            }
        }
        saveListToJson(posts);
        return updatedPost;
    }

    @Override
    public void deleteByld(Long aLong) {
        readListFromJson();
        List<Post> updatedPosts = posts.stream().filter(p -> p.getId() != aLong)
                .collect(Collectors.toList());
        saveListToJson(updatedPosts);
    }

    public void saveListToJson(List<Post> posts) {
        try(FileWriter fr = new FileWriter(jsonPath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonObject = gson.toJson(posts);
            fr.write(jsonObject);


        } catch(IOException e) {
            e.fillInStackTrace();
        }
    }

    public void readListFromJson() {
        try(BufferedReader inReader = new BufferedReader(new FileReader(jsonPath))){
            String str = inReader.readLine();
            String jsonFile = "";


            while(str != null) {

                jsonFile += str;
                str = inReader.readLine();
            }
            Type collectionType = new TypeToken<List<Writer>>() {}.getType();
            posts = new Gson().fromJson(jsonFile, collectionType);




        } catch (IOException e) {
            e.fillInStackTrace();

        }
    }
}
