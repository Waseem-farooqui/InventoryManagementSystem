package com.was.inventory.Repositories;

import com.was.inventory.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
