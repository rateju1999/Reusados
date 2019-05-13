package com.example.reusados;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class CarritoAdaptador extends RecyclerView.Adapter<CarritoAdaptador.CarritoPrendaViewHolder> implements View.OnClickListener{
    private ArrayList<Prenda> prendas;
    private Context context;
    private View.OnClickListener mlistener;

    public CarritoAdaptador(ArrayList<Prenda> prendas, Context context) {
        this.prendas = prendas;
        this.context = context;
    }

    @Override
    public CarritoPrendaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from((parent.getContext()))
                .inflate(R.layout.item_carrito,parent,false);
        CarritoPrendaViewHolder viewHolder = new CarritoPrendaViewHolder(itemView);
        itemView.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CarritoPrendaViewHolder holder, int position) {
        holder.nombre.setText(prendas.get(position).getNombre());
        holder.precio.setText("Precio: " + prendas.get(position).getPrecio() + "€");
        holder.talla.setText("Talla: " + prendas.get(position).getTalla());
        Bitmap imagen = prendas.get(position).getImagenPrenda();
        if(imagen == null) {
            holder.carrito_imagen_prenda.setImageResource(R.drawable.reusado_logo_bueno);
            new CarritoAdaptador.DownloadImageTask(holder.carrito_imagen_prenda, position)
                    .execute(prendas.get(position).getUrlImagenPrenda());
        }else{
            holder.carrito_imagen_prenda.setImageBitmap(imagen);

        }
    }




    @Override
    public int getItemCount() {
        return prendas.size();
    }

    @Override
    public void onClick(View view) {
        if(mlistener != null){
            mlistener.onClick(view);
        }
    }
    public void setOnClickListener(View.OnClickListener listener) {
        mlistener = listener;
    }

    public class CarritoPrendaViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private ImageView carrito_imagen_prenda;
        private TextView precio, nombre, talla;


        public CarritoPrendaViewHolder(View itemView) {
            super(itemView);
            this.context = context;
            this.carrito_imagen_prenda =  itemView.findViewById(R.id.carrito_imagen);
            this.nombre = itemView.findViewById(R.id.carrito_nombre_prenda);
            this.precio = itemView.findViewById(R.id.carrito_precio);
            this.talla = itemView.findViewById(R.id.carrito_talla);

        }


    }
    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage2;
        String urldisplay;
        int position;

        public DownloadImageTask(ImageView Image, int position) {
            this.bmImage2 = Image;
            this.position = position;
        }



        protected Bitmap doInBackground(String... urls) {
            urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage2.setImageBitmap(result);
            prendas.get(position).setImagenPrenda(result);
        }
    }
}
