package com.epam.service;

import com.epam.converter.Converter;
import com.epam.model.Model;
import com.epam.modeldto.ModelDto;
import com.epam.repo.ModelRepo;
import com.epam.service.ModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ModelServiceTest {

    @InjectMocks
    private ModelService modelService;

    @Mock
    private ModelRepo modelRepo;

    @Mock
    private Converter converter;

    @Test
    public void post() {
        ModelDto modelDtoToPersist = new ModelDto();
        modelDtoToPersist.setId(0);
        modelDtoToPersist.setText("text");

        Model modelBeforeDatabaseAlteration = new Model(modelDtoToPersist);

        Model modelAfterDatabaseAlteration = new Model();
        modelAfterDatabaseAlteration.setId(1);
        modelAfterDatabaseAlteration.setText("text");

        doReturn(modelBeforeDatabaseAlteration).when(converter).convertToModel(modelDtoToPersist);
        doReturn(modelAfterDatabaseAlteration).when(modelRepo).save(modelBeforeDatabaseAlteration);

        Model resultModelFromDatabase = modelService.post(modelDtoToPersist);

        assertEquals(resultModelFromDatabase, modelAfterDatabaseAlteration);
    }

    @Test
    public void get() {
        Model savedModelInstance = new Model();
        savedModelInstance.setId(1);
        savedModelInstance.setText("text");

        doReturn(savedModelInstance).when(modelRepo).findOne(1L);

        Model modelGottenFromTheDatabase = modelService.get(1L);

        assertEquals(savedModelInstance, modelGottenFromTheDatabase);
    }

    @Test
    public void getAll() {
        Model firstModel = new Model( 1L, "one");
        Model secondModel = new Model( 2L, "two");

        Iterable<Model> modelsFromRepo = Arrays.asList(firstModel, secondModel);

        doReturn(modelsFromRepo).when(modelRepo).findAll();

        Iterable<Model> modelsFromService = modelService.getAll();

        assertEquals(modelsFromRepo, modelsFromService);
    }
}