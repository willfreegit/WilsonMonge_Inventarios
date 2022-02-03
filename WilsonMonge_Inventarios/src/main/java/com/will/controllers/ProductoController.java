package com.will.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductoController {

    @RequestMapping(value = "productos")
    public List<String> productos(){
        return List.of("manzana","naranjas","bananas");
    }

}
