package com.was.inventory.Repositories;

import com.was.inventory.model.Customer;
import com.was.inventory.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long>{

//    Customer findByCustomer_Id(int customer_id);
}
