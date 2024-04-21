package com.mejia.inventory.inventory.controller;

import com.mejia.inventory.inventory.models.Category;
import com.mejia.inventory.inventory.models.Product;
import com.mejia.inventory.inventory.response.ProductResponseRest;
import com.mejia.inventory.inventory.services.ICategoryServices;
import com.mejia.inventory.inventory.services.IProductSevice;
import com.mejia.inventory.inventory.utill.Utill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
    private ICategoryServices categoryServices;
    private IProductSevice productSevice;

    public ProductRestController(ICategoryServices categoryServices, IProductSevice productSevice) {
        this.categoryServices = categoryServices;
        this.productSevice = productSevice;
    }

    /**
     * save product
     *
     * @param picture
     * @param name
     * @param price
     * @param quantity
     * @param categoryID
     * @return
     * @throws IOException
     */

    @PostMapping("/products")
    public ResponseEntity<ProductResponseRest> save(
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("quantity") int quantity,
            @RequestParam("categoryId") Long categoryID) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setPicture(Utill.compressZLib(picture.getBytes()));

        ResponseEntity<ProductResponseRest> response = productSevice.save(product, categoryID);
        return response;
    }

    /**
     * get product by id
     *
     * @param id
     * @return
     */
    @GetMapping("products/{id}")
    public ResponseEntity<ProductResponseRest> searchByID(@PathVariable Long id) {
        ResponseEntity<ProductResponseRest> response = productSevice.searchById(id);
        return response;


    }

    /**
     * get product by name
     *
     * @param name
     * @return
     */
    @GetMapping("products/filter/{name}")
    public ResponseEntity<ProductResponseRest> searchByName(@PathVariable String name) {
        ResponseEntity<ProductResponseRest> response = productSevice.searchByName(name);
        return response;
    }

    /**
     * delte produc by id
     *
     * @param id
     * @return
     */
    @DeleteMapping("products/{id}")
    public ResponseEntity<ProductResponseRest> deleteByID(@PathVariable Long id) {
        ResponseEntity<ProductResponseRest> response = productSevice.deleteById(id);
        return response;


    }

    /**
     * get products
     *
     * @return
     */
    @GetMapping("/products")
    public ResponseEntity<ProductResponseRest> searchProduct() {
        ResponseEntity<ProductResponseRest> response = productSevice.searchAll();
        return response;


    }

    /**
     * update product
     * @param picture
     * @param name
     * @param price
     * @param quantity
     * @param categoryID
     * @param id
     * @return
     * @throws IOException
     */
    @PutMapping("products/{id}")
    public ResponseEntity<ProductResponseRest> update(
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("quantity") int quantity,
            @RequestParam("categoryId") Long categoryID,
            @PathVariable Long id) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setPicture(Utill.compressZLib(picture.getBytes()));

        ResponseEntity<ProductResponseRest> response = productSevice.updateProduct(product, categoryID, id);
        return response;
    }
}