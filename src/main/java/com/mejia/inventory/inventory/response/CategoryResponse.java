package com.mejia.inventory.inventory.response;

import com.mejia.inventory.inventory.models.Category;
import lombok.Data;

import java.util.List;
@Data
public class CategoryResponse {
    //Almacena datos de la respuesta
    private List <Category> categories;
}
