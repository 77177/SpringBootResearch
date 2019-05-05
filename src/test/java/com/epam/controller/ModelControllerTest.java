package com.epam.controller;

import com.epam.model.Model;
import com.epam.modeldto.ModelDto;
import com.epam.service.ModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ModelControllerTest {

    @Spy
    @InjectMocks
    private ModelController modelController;

    @Mock
    private ModelService modelService;

    @Test
    public void postModel() {
        Model modelFromService = new Model(1, "text");
        ModelDto modelDtoForService = new ModelDto(0, "text");

        doReturn(modelFromService).when(modelService).post(modelDtoForService);

        Model modelFromPostRequest = modelController.postModel(modelDtoForService);
        assertEquals(modelFromPostRequest, modelFromService);
    }

    @Test
    public void getModel() {
        Model modelFromDatabase = new Model(1, "text");

        doReturn(modelFromDatabase).when(modelService).get(1L);

        Model modelFromService = modelService.get(1L);
        assertEquals(modelFromDatabase, modelFromService);
    }

    @Test
    public void getModels() {
        Model firstModel = new Model(1, "one");
        Model secondModel = new Model(2, "two");

        Iterable<Model> modelsFromService = Arrays.asList(firstModel, secondModel);
        doReturn(modelsFromService).when(modelService).getAll();

        Iterable<Model> modelsFromController = modelController.getModels();

        assertEquals(modelsFromController, modelsFromService);
    }
}