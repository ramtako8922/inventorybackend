package com.mejia.inventory.inventory.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {
    private ArrayList<HashMap<String,String>> metadata=new ArrayList<>();

    public ArrayList<HashMap<String, String>> getMetadata() {

        return metadata;
    }

    public void setMetadata(String type,String code, String data) {
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("type ",type);
        map.put("code ",code);
        map.put("data ",data);

        //Se llena la metadata de la respuesta  de la API
        metadata.add(map);
    }
}
