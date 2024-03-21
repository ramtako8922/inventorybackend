package com.mejia.inventory.inventory.controller;

import com.mejia.inventory.inventory.models.Category;
import com.mejia.inventory.inventory.response.CategoryResponseRest;
import com.mejia.inventory.inventory.services.ICategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin (origins = {"http://localhost:4200"})
@RestController
@RequestMapping("api/v1")
public class CategoryRestController {
//Se Inyecta la dependencia para usar los métodos del servicio
    @Autowired
    private ICategoryServices categoryServices;

    /**
     * get all categories
     * @return response
     */
 @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> getCategories(){
        ResponseEntity<CategoryResponseRest> response=categoryServices.search();
        return response;
    }

    /**
     * Get category by id
     * @param id
     * @return respnse
     */
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> getCategoriesById(@PathVariable Long id){
        ResponseEntity<CategoryResponseRest> response=categoryServices.searchById(id);
        return response;

    }

    /**
     *Save categpry
     * @param category
     * @return response
     */
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> saveCategory(@RequestBody Category category) {
        ResponseEntity<CategoryResponseRest> response = categoryServices.saveCategory(category);
        return response;
    }

    /**
     *
     * @param category
     * @param id
     * @return response
     */
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> updateCategory(  @RequestBody Category category,@PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = categoryServices.updateCategory(category,id);
        return response;
    }

    /**
     *
     * @param id
     * @return response
     */
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> updateCategory(  @PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = categoryServices.deleteById(id);
        return response;
    }
}
