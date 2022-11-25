package com.zuev.services;

import com.zuev.entities.Label;

import java.util.List;

public interface GenericService <T,ID>{

    T getByld(ID id);

    List<T> getAll();

    T save(T t);

    T update(T t);

    void deleteByld(ID id);
}
