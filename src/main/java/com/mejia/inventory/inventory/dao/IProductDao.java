package com.mejia.inventory.inventory.dao;

import com.mejia.inventory.inventory.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductDao extends CrudRepository<Product,Long> {
}
