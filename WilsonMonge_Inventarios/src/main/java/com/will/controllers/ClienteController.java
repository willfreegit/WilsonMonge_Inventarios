package com.will.controllers;

import com.will.models.Cliente;
import com.will.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/getCliente/{identificacion}")
    public ResponseEntity<Cliente> getCliente(@PathVariable("identificacion") String identificacion){
        Optional<Cliente> cliente_optional = clienteRepository.findById(identificacion);
        if(cliente_optional.isPresent()){
            return new ResponseEntity<>(cliente_optional.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addCliente")
    public ResponseEntity<String> addCliente(@RequestBody Cliente cliente){
        try{
            if(cliente.getIdentificacion().isEmpty() || cliente.getNombre().isEmpty()){
                return new ResponseEntity<>("CAMPOS IDENTIFICACION Y NOMBRE OBLIGATORIOS", HttpStatus.OK);
            } else {
                Cliente response = clienteRepository.save(cliente);
                return new ResponseEntity<>("CLIENTE GUARDADO CORRECTAMENTE", HttpStatus.OK);
            }
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateCliente/{identificacion}")
    public ResponseEntity<String> updateCliente(@PathVariable("identificacion") String identificacion, @RequestBody Cliente cliente){
        if(cliente.getNombre().isEmpty()){
            return new ResponseEntity<>("CAMPO NOMBRE OBLIGATORIO", HttpStatus.OK);
        } else {
            Optional<Cliente> cliente_optional = clienteRepository.findById(identificacion);
            if(cliente_optional.isPresent()){
                Cliente cliente_update = cliente_optional.get();
                cliente_update.setNombre(cliente.getNombre());
                cliente_update.setFoto(cliente.getFoto());
                clienteRepository.save(cliente_update);
                return new ResponseEntity<>("CLIENTE ACTUALIZADO CORRECTAMENTE", HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    @DeleteMapping("/deleteCliente/{identificacion}")
    public ResponseEntity<String> deleteCliente(@PathVariable("identificacion") String identificacion){
        try{
            clienteRepository.deleteById(identificacion);
            return new ResponseEntity<>("CLIENTE ELIMINADO CORRECTAMENTE", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
