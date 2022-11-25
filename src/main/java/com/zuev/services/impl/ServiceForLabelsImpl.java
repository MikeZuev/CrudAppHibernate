package com.zuev.services.impl;

import com.zuev.entities.Label;
import com.zuev.repositories.LabelRepository;
import com.zuev.services.ServiceForLabel;

import java.util.List;

public class ServiceForLabelsImpl implements ServiceForLabel {

    private final LabelRepository labelRepository;

    public ServiceForLabelsImpl(LabelRepository labelRepository){
        this.labelRepository = labelRepository;
    }


    @Override
    public Label getByld(Long aLong) {
        return labelRepository.getByld(aLong);
    }

    @Override
    public List<Label> getAll() {
        return labelRepository.getAll();
    }

    @Override
    public Label save(Label label) {
        return labelRepository.save(label);
    }

    @Override
    public Label update(Label label) {
        return labelRepository.update(label);
    }

    @Override
    public void deleteByld(Long aLong) {
        labelRepository.deleteByld(aLong);

    }
}
