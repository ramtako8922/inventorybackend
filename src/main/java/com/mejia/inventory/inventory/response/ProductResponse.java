package com.mejia.inventory.inventory.response;

import com.mejia.inventory.inventory.models.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    List<Product> products;

}
