package com.will.repository;

import com.will.models.Registro;
import com.will.models.Reporte01;
import com.will.models.Reporte02;
import com.will.models.Reporte03;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro, Long> {
    @Query("Select count(1) as numero, codigo_tienda as codigo, date(fecha) as fecha from Registro group by codigo_tienda, date(fecha)")
    public List<Reporte01> findByTiendaFecha();
    @Query("Select sum(valor) as monto, codigo_tienda as codigo, id_producto as producto from Registro group by codigo_tienda, id_producto")
    public List<Reporte02> findByTiendaProducto();
    @Query("Select (select name from Producto where id = r.id_producto) as producto, " +
            "(select nombre from Tienda where codigo = r.codigo_tienda) as tienda, " +
            "(select nombre from Cliente where identificacion = r.id_cliente) as cliente, " +
            "r.cantidad as cantidad, r.valor as valor, r.fecha as fecha " +
            "from Registro r where r.id_cliente = :cliente " +
            "and r.fecha between :finicio and :ffin")
    public List<Reporte03> findByCliente(@Param("cliente") String cliente, @Param("finicio") Date finicio, @Param("ffin") Date ffin);
}