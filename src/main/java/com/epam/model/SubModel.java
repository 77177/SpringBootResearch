package com.epam.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "submodels")
public class SubModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean lever;

    @ManyToOne
    @JoinColumn(name = "model")
    private Model model;


}
