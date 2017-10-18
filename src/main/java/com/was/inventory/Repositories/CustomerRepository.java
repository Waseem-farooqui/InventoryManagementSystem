package com.was.inventory.Repositories;

import com.was.inventory.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    List<Customer> findByInformation_Name(String name);
    List<Customer> getAllByInformationNotNull();
    Customer findById(int id);
    void deleteByInformation_NameAndInformation_ContactNo(String name, String contactNo);



}
