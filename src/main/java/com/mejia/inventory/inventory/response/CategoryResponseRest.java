package com.mejia.inventory.inventory.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseRest extends ResponseRest
{
    //Trae la respuesta del servicio
    private  CategoryResponse categoryResponse=new CategoryResponse();

}
