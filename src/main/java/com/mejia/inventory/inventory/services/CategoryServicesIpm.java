package com.mejia.inventory.inventory.services;

import com.mejia.inventory.inventory.dao.ICategoryDao;
import com.mejia.inventory.inventory.models.Category;
import com.mejia.inventory.inventory.response.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServicesIpm implements ICategoryServices {

    //Se inyecta la dependencia del Repositirio
    @Autowired
    private ICategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {
        CategoryResponseRest responseRest = new CategoryResponseRest();


        try {
            List<Category> categoryList= (List<Category>) categoryDao.findAll();
            responseRest.getCategoryResponse().setCategories(categoryList);
            responseRest.setMetadata("OK","200","Consulta exitosa");

        }catch (Exception e){
            responseRest.setMetadata("Respuesta no OK","404","Error al consultar");
            e.getStackTrace();

            return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {
        CategoryResponseRest responseRest = new CategoryResponseRest();
        List<Category> categoryList =new ArrayList<>();
        try {

            Optional<Category> category=categoryDao.findById(id);
            if (category.isPresent()) {
                categoryList.add(category.get());
                responseRest.getCategoryResponse().setCategories(categoryList);
                responseRest.setMetadata("OK", "200", "Consulta exitosa");
            }else {
                responseRest.setMetadata("Respuesta no OK","404","Error al consultar por id");
                return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.NOT_FOUND);


            }

        }catch (Exception e){
            responseRest.setMetadata("Respuesta no OK","404","Error al consultar por id");
            e.getStackTrace();

            return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.OK);
    }
}
