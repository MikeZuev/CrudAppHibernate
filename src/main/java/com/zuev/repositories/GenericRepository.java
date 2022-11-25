package com.zuev.repositories;

import com.zuev.entities.Writer;

import java.util.List;

public interface GenericRepository<T, ID> {
    T getByld(ID id);

    List<T> getAll();

    T save(T t);

    T update(T t);

    void deleteByld(ID id);

}
