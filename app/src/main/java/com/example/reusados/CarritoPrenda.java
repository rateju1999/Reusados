package com.example.reusados;

import android.graphics.Bitmap;

public class CarritoPrenda {
    private String urlImagenPrenda;
    private String precio;
    private String nombre;
    private Bitmap imagenPrenda;
    public CarritoPrenda(){}

    public CarritoPrenda(String urlImagenPrenda, String precio, String nombre) {
        this.urlImagenPrenda = urlImagenPrenda;
        this.precio = precio;
        this.nombre = nombre;
    }

    public CarritoPrenda(String urlImagenPrenda, String precio, String nombre, Bitmap imagenPrenda) {
        this.urlImagenPrenda = urlImagenPrenda;
        this.precio = precio;
        this.nombre = nombre;
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

    public Bitmap getImagenPrenda() {
        return imagenPrenda;
    }

    public void setImagenPrenda(Bitmap imagenPrenda) {
        this.imagenPrenda = imagenPrenda;
    }
}
