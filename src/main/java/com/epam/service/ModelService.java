package com.epam.service;

import com.epam.converter.Converter;
import com.epam.model.Model;
import com.epam.modeldto.ModelDto;
import com.epam.repo.ModelRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    private final ModelRepo modelRepo;
    private final Converter converter;

    public ModelService(ModelRepo modelRepo, Converter converter) {
        this.modelRepo = modelRepo;
        this.converter = converter;
    }

    public Model post(ModelDto modelDto){
        Model modelToPersist = converter.convertToModel(modelDto);
        return modelRepo.save(modelToPersist);
    }

    public Model get(Long id) {

        return modelRepo.findOne(id);
    }

    public Iterable<Model> getAll() {
        return modelRepo.findAll();
    }
}
