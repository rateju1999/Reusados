package com.example.reusados;

import android.graphics.Bitmap;

public class Prenda {
    private String urlImagenPrenda;
    private String precio;
    private String nombre;
    private String talla;
    private Bitmap imagenPrenda;

    public Prenda(String urlPrenda, String precio, String nombre, String talla, Bitmap imagenPrenda) {
        this.urlImagenPrenda = urlPrenda;
        this.precio = precio;
        this.nombre = nombre;
        this.talla = talla;
        this.imagenPrenda = imagenPrenda;
    }

    public Prenda(String urlPrenda, String precio, String nombre) {
        this.urlImagenPrenda = urlPrenda;
        this.precio = precio;
        this.nombre = nombre;
    }

    public Prenda(String urlPrenda, String precio, String nombre, String talla) {
        this.urlImagenPrenda = urlPrenda;
        this.precio = precio;
        this.nombre = nombre;
        this.talla = talla;
    }

    public Bitmap getImagenPrenda() {
        return imagenPrenda;
    }

    public void setImagenPrenda(Bitmap imagenPrenda) {
        this.imagenPrenda = imagenPrenda;
    }

    public String getUrlImagenPrenda() {
        return urlImagenPrenda;
    }

    public void setUrlImagenPrenda(String urlImagenPrenda) {
        this.urlImagenPrenda = urlImagenPrenda;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }
}

