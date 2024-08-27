package com.debuggeando_ideas.best_travel.domain.repository;

import com.debuggeando_ideas.best_travel.domain.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {
}
