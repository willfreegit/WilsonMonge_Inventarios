package com.will.models;

import jdk.jfr.Enabled;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "Registro")
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "id_producto")
    private long id_producto;
    @Column(name = "codigo_tienda")
    private long codigo_tienda;
    @Column(name = "id_cliente")
    private String id_cliente;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "fecha")
    private Timestamp fecha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_producto() {
        return id_producto;
    }

    public void setId_producto(long id_producto) {
        this.id_producto = id_producto;
    }

    public long getCodigo_tienda() {
        return codigo_tienda;
    }

    public void setCodigo_tienda(long codigo_tienda) {
        this.codigo_tienda = codigo_tienda;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
