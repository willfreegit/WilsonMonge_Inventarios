package com.will.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Tienda")
public class Tienda {
    @Id
    @Column(name = "codigo")
    private long codigo;
    @Column(name = "nombre")
    private String nombre;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "TiendaProducto",
            joinColumns = { @JoinColumn(name = "codigo", referencedColumnName = "codigo", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name="id", referencedColumnName = "id", nullable = false, updatable = false)})
    private Set<Producto> productos = new HashSet<>();

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }
}
