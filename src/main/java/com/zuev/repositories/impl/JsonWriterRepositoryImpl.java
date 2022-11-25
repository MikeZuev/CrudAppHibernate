package com.zuev.repositories.impl;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.zuev.entities.Post;
import com.zuev.entities.Writer;
import com.zuev.repositories.WriterRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JsonWriterRepositoryImpl implements WriterRepository {

    static List<Writer> writers = new ArrayList<>();




    private final String jsonPath = "src/main/resources/writer.json";

    public JsonWriterRepositoryImpl(){

    }


    @Override
    public Writer save(Writer writer) {
        readListFromJson();


        if(writers.size() == 0){
            writer.setId(1L);
            writers.add(writer);
            saveListToJson(writers);
        } else if (writers.size() >= 1){
            writer.setId(writers.stream()
                    .map(Writer::getId)
                    .max(Comparator.naturalOrder())
                    .get()
                    .intValue() + 1);

            writers.add(writer);
            saveListToJson(writers);
        }

        return writer;
    }

    @Override
    public Writer getByld(Long integer) {
        Writer result = null;
        try (BufferedReader bf = new BufferedReader(new FileReader(jsonPath))) {
            String str = bf.readLine();
            String jsonFile = "";
            while(str != null) {
                jsonFile += str;
                str = bf.readLine();

            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(writers);

            Type collectionType = new TypeToken<List<Writer>>() {}.getType();
            List<Writer> writers = new Gson().fromJson(jsonFile, collectionType);


            for (Writer writer : writers) {

                if (integer == writer.getId()) {
                    result = writer;

                }
            }

        } catch (IOException e) {
            e.fillInStackTrace();
        }
        return result;

    }

    @Override
    public List<Writer> getAll() {
        readListFromJson();

        return writers;
    }

    @Override
    public Writer update(Writer updateWriter) {
        readListFromJson();
        for (Writer writer : writers) {
            if(updateWriter.getId() == writer.getId()){
                if(updateWriter.getLastName().equals(writer.getLastName())){
                    writer.setFirstName(updateWriter.getFirstName());

                } else {
                    writer.setLastName(updateWriter.getLastName());
                }
            }
        }
        saveListToJson(writers);
        return updateWriter;

        
    }

    @Override
    public void deleteByld(Long id) {
        readListFromJson();
        List<Writer> updatedWriters = writers.stream().filter(w->w.getId() != id)
                .collect(Collectors.toList());
        saveListToJson(updatedWriters);


    }



    public int newID() {


        return 0;
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
            writers = new Gson().fromJson(jsonFile, collectionType);




        } catch (IOException e) {
            e.fillInStackTrace();

        }
    }

    public void saveListToJson(List<Writer> writers) {
        try(FileWriter fr = new FileWriter(jsonPath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonObject = gson.toJson(writers);
            fr.write(jsonObject);


        } catch(IOException e) {
            e.fillInStackTrace();
        }
    }
}
