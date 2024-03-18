package com.mejia.inventory.inventory.services;

import com.mejia.inventory.inventory.dao.ICategoryDao;
import com.mejia.inventory.inventory.models.Category;
import com.mejia.inventory.inventory.response.CategoryResponseRest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServicesIpm implements ICategoryServices {

    //Se inyecta la dependencia del Repositirio
    @Autowired
    private ICategoryDao categoryDao;

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> search() {
        CategoryResponseRest responseRest = new CategoryResponseRest();


        try {
            List<Category> categoryList= (List<Category>) categoryDao.findAll();
            responseRest.getCategoryResponse().setCategories(categoryList);
            responseRest.setMetadata("OK","200","Consulta exitosa");

        }catch (Exception e){
            responseRest.setMetadata("Respuesta no OK","404","Error al consultar");
            e.getStackTrace();

            return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.OK);
    }
}
