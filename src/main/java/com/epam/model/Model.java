package com.epam.model;

import com.epam.modeldto.ModelDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    public Model(ModelDto modelDto) {
        this.id = modelDto.getId();
        this.text = modelDto.getText();
    }
}
