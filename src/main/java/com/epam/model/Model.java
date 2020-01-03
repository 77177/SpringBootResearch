package com.epam.model;

import com.epam.modeldto.ModelDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "models")
public class Model {

    public Model(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    @OneToMany(mappedBy = "model")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonManagedReference
    Set<SubModel> subModelSet = new HashSet<>();

    public Model(ModelDto modelDto) {
        this.id = modelDto.getId();
        this.text = modelDto.getText();
    }
}
