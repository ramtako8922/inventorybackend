package com.mejia.inventory.inventory.services;

import com.mejia.inventory.inventory.dao.ICategoryDao;
import com.mejia.inventory.inventory.dao.IProductDao;
import com.mejia.inventory.inventory.models.Category;
import com.mejia.inventory.inventory.models.Product;
import com.mejia.inventory.inventory.response.CategoryResponseRest;
import com.mejia.inventory.inventory.response.ProductResponseRest;
import com.mejia.inventory.inventory.response.ResponseRest;
import com.mejia.inventory.inventory.utill.Utill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceIpm implements IProductSevice {

    private ICategoryDao categoryDao;
    private IProductDao productDao;

    public ProductServiceIpm(ICategoryDao categoryDao, IProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @Override
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryID) {
        ProductResponseRest responseRest = new ProductResponseRest();
        List<Product> listProducts = new ArrayList<>();

        try {
            // Buscar la categoria por id que se le setea al producto
            Optional<Category> category = categoryDao.findById(categoryID);

            if (category.isPresent()) {
                product.setCategory(category.get());

            } else {
                responseRest.setMetadata("Respuesta no OK", "-1", "Categoria no presente en el inventario");
                return new ResponseEntity<ProductResponseRest>(responseRest, HttpStatus.NOT_FOUND);
            }

            //guardar prducto

            Product saveProduct = productDao.save(product);
            if (saveProduct != null) {
                listProducts.add(saveProduct);
                responseRest.getProductResponse().setProducts(listProducts);
                responseRest.setMetadata("Respuesta ok", "200", "Prodcto guardado");

            } else {
                responseRest.setMetadata("Respuesta no OK", "-1", "Error al guardar prducto");
                return new ResponseEntity<ProductResponseRest>(responseRest, HttpStatus.BAD_REQUEST);

            }


        } catch (Exception e) {
            e.getStackTrace();
            responseRest.setMetadata("Respuesta no OK", "-1", "Error al guardar prducto");
            return new ResponseEntity<ProductResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);


        }
        return new ResponseEntity<ProductResponseRest>(responseRest, HttpStatus.OK);


    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<ProductResponseRest> searchById(Long id) {

        ProductResponseRest responseRest = new ProductResponseRest();
        List<Product> listProducts = new ArrayList<>();

        try {
            // Buscar EL PRODUCTO
            Optional<Product> product = productDao.findById(id);


            if (product.isPresent()) {
                byte[] imageDescomprese = Utill.decompressZLib(product.get().getPicture());
                product.get().setPicture(imageDescomprese);
                listProducts.add(product.get());
                responseRest.getProductResponse().setProducts(listProducts);
                responseRest.setMetadata("Respuesta ok", "00", "Producto encontrado");

            } else {
                responseRest.setMetadata("Respuesta no OK", "-1", "Producto no presente en el inventario");
                return new ResponseEntity<ProductResponseRest>(responseRest, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.getStackTrace();
            responseRest.setMetadata("Respuesta no OK", "-1", "Producto no presente en el inventario");
            return new ResponseEntity<ProductResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<ProductResponseRest>(responseRest, HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<ProductResponseRest> searchByName(String name) {
        ProductResponseRest responseRest = new ProductResponseRest();
        List<Product> listProducts = new ArrayList<>();
        List<Product> listAux=new ArrayList<>();

        try {
            // Buscar el producto por nombre
            listAux=productDao.findByNameContainingIgnoreCase(name);




            if (listAux.size()>0) {
                listAux.stream().forEach((p)->{
                    byte[] imageDescomprese = Utill.decompressZLib(p.getPicture());
                    p.setPicture(imageDescomprese);
                    listProducts.add(p);

                });

                responseRest.getProductResponse().setProducts(listProducts);
                responseRest.setMetadata("Respuesta ok", "00", "Producto encontrado por el nombre");

            } else {
                responseRest.setMetadata("Respuesta no OK", "-1", "Producto no presente en el inventario por el nombre");
                return new ResponseEntity<ProductResponseRest>(responseRest, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.getStackTrace();
            responseRest.setMetadata("Respuesta no OK", "-1", "Producto no presente en el inventario");
            return new ResponseEntity<ProductResponseRest>(responseRest, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<ProductResponseRest>(responseRest, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public ResponseEntity<ProductResponseRest> deleteById(Long id) {
        ProductResponseRest responseRest = new ProductResponseRest();
        List<Product> listProducts = new ArrayList<>();

        try {
            // Eliminar producto por id
            productDao.deleteById(id);

            List<Product> productList= (List<Product>) productDao.findAll();
            responseRest.getProductResponse().setProducts(listProducts);

            responseRest.setMetadata("OK","200","producto borrado con éxito");

        }catch (Exception e){
            responseRest.setMetadata("Respuesta no OK","404","Error al borrorar el producto");
            e.getStackTrace();

            return new ResponseEntity<ProductResponseRest>(responseRest, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<ProductResponseRest>(responseRest, HttpStatus.OK);





    }
}