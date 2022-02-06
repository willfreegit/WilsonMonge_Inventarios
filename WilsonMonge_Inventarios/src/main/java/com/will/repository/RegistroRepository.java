package com.will.repository;

import com.will.models.Registro;
import com.will.models.Reporte01;
import com.will.models.Reporte02;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro, Long> {
    @Query("Select count(1) as numero, codigo_tienda as codigo, date(fecha) as fecha from Registro group by codigo_tienda, date(fecha)")
    public List<Reporte01> findByTiendaFecha();
    @Query("Select sum(valor) as monto, codigo_tienda as codigo, id_producto as producto from Registro group by codigo_tienda, id_producto")
    public List<Reporte02> findByTiendaProducto();
}