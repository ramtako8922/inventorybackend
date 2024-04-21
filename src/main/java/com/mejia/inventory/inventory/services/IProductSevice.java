package com.mejia.inventory.inventory.services;

import com.mejia.inventory.inventory.models.Product;
import com.mejia.inventory.inventory.response.ProductResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProductSevice {
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryID);
    public ResponseEntity<ProductResponseRest> searchById(Long id);

    public ResponseEntity<ProductResponseRest> searchByName(String name);

    public ResponseEntity<ProductResponseRest> deleteById(Long id);
}
