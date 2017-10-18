package com.was.inventory.Repositories;

import com.was.inventory.model.Customer;
import com.was.inventory.model.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    List<Supplier> getAllByInformationNotNull();
//    List<Supplier> getAllByInformation();

    Supplier getById(int supplierId);


}
