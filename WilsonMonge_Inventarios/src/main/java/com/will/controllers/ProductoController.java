package com.will.controllers;

import com.will.models.Cliente;
import com.will.models.Producto;
import com.will.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoRepository productoRepository;

    @RequestMapping(value = "/getProductos")
    public List<Producto> productos(){
        return productoRepository.findAll();
    }

    @PutMapping("/updateStock/{id}/{cantidad}")
    public ResponseEntity<String> updateStock(@PathVariable("id") long id, @PathVariable int cantidad){
        Optional<Producto> producto_actual = productoRepository.findById(id);
            if(producto_actual.isPresent()){
                Producto producto_nuevo = producto_actual.get();
                if(producto_nuevo.getStock() <= 0){
                    return new ResponseEntity<>("STOCK ACTUAL NO PUEDE SERE MENOR O IGUAL A CERO", HttpStatus.OK);
                } else {
                    producto_nuevo.setStock(cantidad);
                    productoRepository.save(producto_nuevo);
                    return new ResponseEntity<>("CLIENTE ACTUALIZADO CORRECTAMENTE", HttpStatus.OK);
                }
            } else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

    }

}
