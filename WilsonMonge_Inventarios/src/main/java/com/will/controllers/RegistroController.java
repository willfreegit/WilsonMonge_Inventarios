package com.will.controllers;

import com.will.models.*;
import com.will.repository.RegistroRepository;
import com.will.util.CSVUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
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

    @GetMapping("/getTransaccionesByCliente/{identificacion}/{finicio}/{ffin}")
    public ResponseEntity<Resource> getTansaccionesByCliente(@PathVariable("identificacion") String identificacion,
                                                             @PathVariable("finicio") String finicio,
                                                             @PathVariable("ffin") String ffin){
        String filename = "reporteTransaccionesCliente.csv";
        InputStreamResource file = new InputStreamResource(load(identificacion, finicio, ffin));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=" + filename).
                contentType(MediaType.parseMediaType("application/csv")).body(file);
    }

    public ByteArrayInputStream load(String identificacion, String finicio, String ffin) {
        Date date_inicio = null;
        Date date_fin = null;
        try {
            java.util.Date inicio =  new SimpleDateFormat("dd-mm-yyyy").parse(finicio);
            java.util.Date fin = new SimpleDateFormat("dd-mm-yyyy").parse(ffin);
            date_inicio = new Date(inicio.getTime());
            date_fin = new Date(fin.getTime());
        } catch (Exception ex){
            ex.printStackTrace();
        }
        List<Reporte03> registros = registroRepository.findByCliente(identificacion, date_inicio, date_fin);
        ByteArrayInputStream in = CSVUtil.registrosToCSV(registros);
        return in;
    }
}
