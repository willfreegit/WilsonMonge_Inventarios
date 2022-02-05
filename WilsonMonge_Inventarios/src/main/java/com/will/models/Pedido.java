package com.will.models;

import java.util.List;

public class Pedido {
    private List<Registro> registros;

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }
}
