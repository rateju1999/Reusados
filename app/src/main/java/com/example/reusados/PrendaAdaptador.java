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

import com.google.android.gms.dynamic.IFragmentWrapper;

import java.io.InputStream;
import java.util.ArrayList;

public class PrendaAdaptador extends RecyclerView.Adapter<PrendaAdaptador.PrendaViewHolder> implements View.OnClickListener {
    private ArrayList<Prenda> prendas;
    private Context context;
    private View.OnClickListener mListener;

    public PrendaAdaptador(ArrayList<Prenda> prendas, Context context) {
        this.prendas = prendas;
        this.context = context;
    }

    @Override
    public PrendaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.penda_item,parent,false);
        view.setOnClickListener(this);
        return new PrendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PrendaViewHolder holder, int position) {
        holder.nombre.setText(prendas.get(position).getNombre());
        holder.precio.setText(prendas.get(position).getPrecio() + "€");
        Bitmap imagen = prendas.get(position).getImagenPrenda();
        if(imagen == null) {
            holder.imagen_prenda.setImageResource(R.drawable.icono_reusados_cargando);
            new DownloadImageTask(holder.imagen_prenda, position)
                    .execute(prendas.get(position).getUrlImagenPrenda());
        }else{
            holder.imagen_prenda.setImageBitmap(imagen);

        }


    }

    @Override
    public int getItemCount() {
        return prendas.size();
    }

    @Override
    public void onClick(View view) {
        if (mListener != null){
            mListener.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {mListener = listener;}


    //VIEW HOLDER
    public class PrendaViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private ImageView imagen_prenda;
        private TextView nombre;
        private TextView precio;


        public PrendaViewHolder(View itemView) {
            super(itemView);
            this.context = context;
            this.imagen_prenda =  itemView.findViewById(R.id.prenda_imagen_item);
            this.nombre = itemView.findViewById(R.id.prenda_titulo_item);
            this.precio = itemView.findViewById(R.id.prenda_precio_item);
        }


    }

    //TAREA ASINCRONA
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
