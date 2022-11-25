package com.zuev.services.impl;

import com.zuev.entities.Writer;
import com.zuev.repositories.WriterRepository;
import com.zuev.services.ServiceForWriter;

import java.util.List;

public class ServiceForWritersImpl implements ServiceForWriter {

    private final WriterRepository writerRepository;

    public ServiceForWritersImpl(WriterRepository writerRepository){
        this.writerRepository = writerRepository;
    }






    @Override
    public Writer getByld(Long integer) {
        return writerRepository.getByld(integer);
    }



    @Override
    public List<Writer> getAll() {
        return writerRepository.getAll();
    }

    @Override
    public Writer save(Writer writer) {
        return writerRepository.save(writer);
    }

    @Override
    public Writer update(Writer writer) {
        return writerRepository.update(writer);
    }



    @Override
    public void deleteByld(Long integer) {
        writerRepository.deleteByld(integer);

    }
}
