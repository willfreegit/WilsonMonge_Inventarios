package com.will.controllers;

import com.will.models.Reporte01;
import com.will.models.Reporte02;
import com.will.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class RegistroController {
    @Autowired
    RegistroRepository registroRepository;

    @GetMapping("/getTransaccionesByTiendaFecha")
    public List<Reporte01> getTransaccionesByTiendaFecha(){
        return (List<Reporte01>) registroRepository.findByTiendaFecha();
    }

    @GetMapping("/getTransaccionesByTiendaProducto")
    public List<Reporte02> getTransaccionesByTiendaProducto(){
        return (List<Reporte02>) registroRepository.findByTiendaProducto();
    }
}
