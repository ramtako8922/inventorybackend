package com.mejia.inventory.inventory.controller;

import com.mejia.inventory.inventory.response.CategoryResponseRest;
import com.mejia.inventory.inventory.services.ICategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class CategoryRestController {
//Se Inyecta la dependencia para usar los métodos del servicio
    @Autowired
    private ICategoryServices categoryServices;
 @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> getCategories(){
        ResponseEntity<CategoryResponseRest> response=categoryServices.search();
        return response;
    }

}
