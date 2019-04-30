package com.example.reusados;

import android.widget.ImageView;

public class TipoPrenda {
    private String tipo;
    private int imagen;


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public TipoPrenda(String tipo, int imagen) {
        this.tipo = tipo;
        this.imagen = imagen;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
