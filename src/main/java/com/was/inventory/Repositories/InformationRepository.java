package com.was.inventory.Repositories;

import com.was.inventory.model.Information;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface InformationRepository extends CrudRepository<Information, Integer> {

}
