package com.example.reusados;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TipoPrendaViewHolder extends RecyclerView.ViewHolder{
    private Context context;
    private TextView texto_tipo_prenda;
    private ImageView imagen_tipo_prenda;

    public TipoPrendaViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        texto_tipo_prenda = itemView.findViewById(R.id.texto_tipo_prenda);
        imagen_tipo_prenda = itemView.findViewById(R.id.imagen_tipo_prenda);
    }
    public void bindItem (TipoPrenda prenda){
        texto_tipo_prenda.setText(prenda.getTipo());

//        imagen_tipo_prenda.setImageResource(R.drawable.abrigo_opt);
        imagen_tipo_prenda.setImageResource(prenda.getImagen());
  }
}
