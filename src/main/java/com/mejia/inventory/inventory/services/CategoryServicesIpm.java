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

    /**
     *
     * @param id
     * @return responseRest
     */

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

    /**
     *
     * @param category
     * @return esponseRest
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> saveCategory(Category category) {
        CategoryResponseRest responseRest = new CategoryResponseRest();
        List<Category> categoryList =new ArrayList<>();
        try {
            Category category1=categoryDao.save(category);


            if (category !=null) {
                categoryList.add(category1);
                responseRest.getCategoryResponse().setCategories(categoryList);
                responseRest.setMetadata("OK", "204", "Registro Exitosos");
            }else {
                responseRest.setMetadata("Respuesta no OK","404","Error alagregar registeros");
                return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.BAD_REQUEST);


            }

        }catch (Exception e){
            responseRest.setMetadata("Respuesta no OK","404","Error al consultar por id");
            e.getStackTrace();

            return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> updateCategory(Category category, Long id) {


        CategoryResponseRest responseRest = new CategoryResponseRest();
        List<Category> categoryList =new ArrayList<>();
        try {

            Optional<Category> searchCategory=categoryDao.findById(id);
            if (searchCategory.isPresent()){

                //Se inicia actualización de categoria
                searchCategory.get().setName(category.getName());
                searchCategory.get().setDescription(category.getDescription());

                Category categoryToUpdate=categoryDao.save(searchCategory.get());

                if (categoryToUpdate!=null){
                    categoryList.add(categoryToUpdate);
                    responseRest.getCategoryResponse().setCategories(categoryList);
                    responseRest.setMetadata("Respuesta ok","200","Categoria actualizada");
                }else {
                    responseRest.setMetadata("Categoria no encontrada","404","Errror al consultar por id");
                    return new ResponseEntity<CategoryResponseRest>(responseRest,HttpStatus.BAD_REQUEST);
                }

            }else {
                responseRest.setMetadata("Respuesta no ok","500","Errror del servidor");
                return new ResponseEntity<CategoryResponseRest>(responseRest,HttpStatus.INTERNAL_SERVER_ERROR);

            }



        }catch (Exception e){
            responseRest.setMetadata("Respuesta no OK","404","Error al al actulizar la categoria");
            e.getStackTrace();

            return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
        CategoryResponseRest responseRest = new CategoryResponseRest();

        try {
            categoryDao.deleteById(id);
            List<Category> categoryList= (List<Category>) categoryDao.findAll();
            responseRest.getCategoryResponse().setCategories(categoryList);

            responseRest.setMetadata("OK","200","Categoria borrada con éxito");

        }catch (Exception e){
            responseRest.setMetadata("Respuesta no OK","404","Error al borrorar categoria");
            e.getStackTrace();

            return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<CategoryResponseRest>(responseRest, HttpStatus.OK);

    }



}
