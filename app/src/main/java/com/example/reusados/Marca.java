package com.example.reusados;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Marca {
    private String url;
    private Bitmap imgMarca;

    public Marca(){}
    public Marca(String url, Bitmap imgMarca) {
        this.url = url;
        this.imgMarca = imgMarca;
    }

    public Marca(String url) {
        this.url = url;
    }

    public Bitmap getImgMarca() {
        return imgMarca;
    }

    public void setImgMarca(Bitmap imgMarca) {
        this.imgMarca = imgMarca;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
