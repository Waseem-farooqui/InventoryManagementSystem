package com.was.inventory.Repositories;

import com.was.inventory.model.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    Supplier getById(int supplierId);


}
