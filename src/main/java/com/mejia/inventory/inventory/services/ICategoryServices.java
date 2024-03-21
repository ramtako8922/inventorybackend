package com.mejia.inventory.inventory.services;

import com.mejia.inventory.inventory.models.Category;
import com.mejia.inventory.inventory.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryServices {

//Metodos abstactos del servicio rest
    public ResponseEntity<CategoryResponseRest> search();
    public ResponseEntity<CategoryResponseRest> searchById(Long id);
    public ResponseEntity<CategoryResponseRest> saveCategory(Category category);

    public ResponseEntity<CategoryResponseRest> updateCategory(Category category,Long id);

    public ResponseEntity<CategoryResponseRest> deleteById(Long id);
}
