package com.mejia.inventory.inventory.dao;

import com.mejia.inventory.inventory.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductDao extends CrudRepository<Product,Long> {
    @Query("select p from Product p where p.name like %?1%")
    List<Product> findByNameLike(String name);

    List<Product> findByNameContainingIgnoreCase(String name);
}
