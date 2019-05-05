package com.epam.converter;

import com.epam.model.Model;
import com.epam.modeldto.ModelDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ConverterTest {

    @Spy
    private Converter converter;

    @Test
    public void testConvertToModel() {
        ModelDto modelDto = new ModelDto();
        modelDto.setId(0);
        modelDto.setText("text");

        Model modelFromDto = converter.convertToModel(modelDto);

        assertEquals(modelDto.getId(), modelFromDto.getId());
        assertEquals(modelDto.getText(), modelFromDto.getText());
    }
}