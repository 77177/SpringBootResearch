package com.epam.controller;

import com.epam.model.Model;
import com.epam.modeldto.ModelDto;
import com.epam.service.ModelService;
import com.fasterxml.jackson.annotation.JacksonAnnotation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController {

    private final ModelService modelService;

    public ModelController(final ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Model postModel(@RequestBody final ModelDto modelDto) {
        return modelService.post(modelDto);
    }

    @GetMapping("/{id}")
    public Model getModel(@PathVariable("id") final long modelId) {
        return modelService.get(modelId);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Model> getModels() {
        return modelService.getAll();
    }
}
