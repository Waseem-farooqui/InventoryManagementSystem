package com.was.inventory.Repositories;

import com.was.inventory.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findById(int id);



}
