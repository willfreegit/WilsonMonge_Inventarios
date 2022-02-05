package com.will.models;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private List<Producto> prods;

    public Catalogo(){
        this.prods = new ArrayList<>();
    }

    public List<Producto> getProds() {
        return prods;
    }

    public void setProds(List<Producto> prods) {
        this.prods = prods;
    }
}
