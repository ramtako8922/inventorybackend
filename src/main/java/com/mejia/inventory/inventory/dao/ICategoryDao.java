package com.mejia.inventory.inventory.dao;

import com.mejia.inventory.inventory.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryDao extends CrudRepository<Category,Long> {
}
