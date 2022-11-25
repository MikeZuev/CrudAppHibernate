package com.zuev.repositories.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zuev.entities.Label;
import com.zuev.entities.Post;
import com.zuev.entities.Writer;
import com.zuev.repositories.LabelRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JsonLabelRepositoryImpl implements LabelRepository {
    static final String jsonPath  = "src/main/resources/labels.json";

    List<Label> labels = new ArrayList<>();



    @Override
    public Label getByld(Long aLong) {
        readListFromJson();
        Label result = null;

        for(Label label : labels) {
            if(label.getId() == aLong) {
                result = label;
            }
        }

        return result;
    }

    @Override
    public List<Label> getAll() {
        readListFromJson();

        return labels;
    }

    @Override
    public Label save(Label label) {
        readListFromJson();

        if(labels.size() == 0) {
            label.setId(1);
            labels.add(label);
            saveListToJson(labels);
        } else if (labels.size() >= 1) {
            label.setId(labels.stream()
                    .map(Label::getId)
                    .max(Comparator.naturalOrder())
                    .get().intValue() + 1);
        }
        saveListToJson(labels);

        return label;
    }

    @Override
    public Label update(Label updatedLabel) {
        readListFromJson();
        for(Label label : labels) {
            if(updatedLabel.getId() == label.getId()){
                label.setName(updatedLabel.getName());
            }
        }
        saveListToJson(labels);


        return updatedLabel;
    }

    @Override
    public void deleteByld(Long aLong) {
        readListFromJson();
        List<Label>updatedLabels = labels.stream()
                .filter(l -> l.getId() != aLong).collect(Collectors.toList());
        saveListToJson(updatedLabels);


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
            labels = new Gson().fromJson(jsonFile, collectionType);




        } catch (IOException e) {
            e.fillInStackTrace();

        }
    }
    public void saveListToJson(List<Label> labels) {
        try(FileWriter fr = new FileWriter(jsonPath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonObject = gson.toJson(labels);
            fr.write(jsonObject);


        } catch(IOException e) {
            e.fillInStackTrace();
        }
    }
}
