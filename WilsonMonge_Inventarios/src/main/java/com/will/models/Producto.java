package com.will.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "cod")
    private String cod;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "stock")
    private int stock;
    @ManyToMany(mappedBy = "productos", fetch = FetchType.LAZY)
    private Set<Tienda> tiendas = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Set<Tienda> getTiendas() {
        return tiendas;
    }

    public void setTiendas(Set<Tienda> tiendas) {
        this.tiendas = tiendas;
    }
}
