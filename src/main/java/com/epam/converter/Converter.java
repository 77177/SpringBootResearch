package com.epam.converter;

import com.epam.model.Model;
import com.epam.modeldto.ModelDto;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public Model convertToModel(ModelDto modelDto){
        return new Model(modelDto);
    }
}
