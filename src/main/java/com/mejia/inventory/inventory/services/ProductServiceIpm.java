package com.mejia.inventory.inventory.services;

import com.mejia.inventory.inventory.dao.ICategoryDao;
import com.mejia.inventory.inventory.dao.IProductDao;
import com.mejia.inventory.inventory.models.Category;
import com.mejia.inventory.inventory.models.Product;
import com.mejia.inventory.inventory.response.ProductResponseRest;
import com.mejia.inventory.inventory.response.ResponseRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceIpm implements IProductSevice{

    private ICategoryDao categoryDao;
    private IProductDao productDao;

    public ProductServiceIpm(ICategoryDao categoryDao,IProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao=productDao;
    }

    @Override
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryID) {
        ProductResponseRest responseRest=new ProductResponseRest();
        List<Product> listProducts=new ArrayList<>();

        try {
          // Buscar la categoria por id que se le setea al producto
            Optional<Category> category=categoryDao.findById(categoryID);

            if (category.isPresent()) {
                product.setCategory(category.get());

            }else{
                responseRest.setMetadata("Respuesta no OK","-1","Categoria no presente en el inventario");
                return new  ResponseEntity<ProductResponseRest>(responseRest,HttpStatus.NOT_FOUND);
            }

            //guardar prducto

            Product saveProduct=productDao.save(product);
            if (saveProduct != null) {
                listProducts.add(saveProduct);
                responseRest.getProductResponse().setProducts(listProducts);
                responseRest.setMetadata("Respuesta ok","200","Prodcto guardado");
                
            }else {
                responseRest.setMetadata("Respuesta no OK","-1","Error al guardar prducto");
                return new  ResponseEntity<ProductResponseRest>(responseRest,HttpStatus.BAD_REQUEST);

            }




        }catch (Exception e){

        }
        return null;


    }
}
