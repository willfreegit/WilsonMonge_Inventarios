package com.will.controllers;

import com.will.models.Catalogo;
import com.will.models.Cliente;
import com.will.models.Producto;
import com.will.models.Tienda;
import com.will.repository.ProductoRepository;
import com.will.repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tienda")
public class TiendaController {
    @Autowired
    TiendaRepository tiendaRepository;
    @Autowired
    ProductoRepository productoRepository;

    @PutMapping("/setProductos/{codigo}")
    public ResponseEntity<String> setProducto(@PathVariable long codigo, @RequestBody Catalogo catalogo){
        Optional<Tienda> tienda_optional = tiendaRepository.findById(codigo);
        if(tienda_optional.isPresent()){
            Tienda tienda_update = tienda_optional.get();
            for(Producto producto: catalogo.getProds()){
                Optional<Producto> producto_actual = productoRepository.findById(producto.getId());
                if(producto_actual.isPresent()){
                    Producto producto_nuevo = producto_actual.get();
                    tienda_update.getProductos().add(producto_nuevo);
                    tiendaRepository.save(tienda_update);
                } else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
            return new ResponseEntity<>("PRODUCTOS AGREGADOS CORRECTAMENTE", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
