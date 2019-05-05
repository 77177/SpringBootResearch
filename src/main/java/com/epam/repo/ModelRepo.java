package com.epam.repo;

import com.epam.model.Model;
import org.springframework.data.repository.CrudRepository;

public interface ModelRepo extends CrudRepository<Model,Long> {
}
