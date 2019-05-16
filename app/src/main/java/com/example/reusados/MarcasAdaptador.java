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

import java.io.InputStream;
import java.util.ArrayList;

public class MarcasAdaptador extends RecyclerView.Adapter<MarcasAdaptador.MarcasViewHolder> implements View.OnClickListener  {


    private ArrayList<Marca> imagenes_marcas;
    private Context mContext;
    private View.OnClickListener mListenr;


    public MarcasAdaptador(ArrayList<Marca> imagenes_marcas, Context mContext) {
        this.imagenes_marcas = new ArrayList<>();
        this.imagenes_marcas = imagenes_marcas;
        this.mContext = mContext;

    }

    @Override
    public MarcasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_marcas,parent,false);
        view.setOnClickListener(this);

        return new MarcasViewHolder (view);
    }

    @Override
    public void onBindViewHolder(MarcasViewHolder holder, int position) {
        Bitmap imagen = imagenes_marcas.get(position).getImgMarca();
        if(imagen == null) {
            holder.img_marcas.setImageResource(R.drawable.reusado_logo_bueno);
            new DownloadImageTask(holder.img_marcas, position)
                    .execute(imagenes_marcas.get(position).getUrl());
        }else{
            holder.img_marcas.setImageBitmap(imagen);
        }
    }

    @Override
    public int getItemCount() {
        return imagenes_marcas.size();
    }
    //PARTE DEL ONCLICK

    @Override
    public void onClick(View view) {
        if(mListenr != null ){
            mListenr.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {mListenr = listener;}

    //LA PARTE DEL VIEW HOLDER
    public class MarcasViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        private ImageView img_marcas;

        public MarcasViewHolder(View itemView) {
            super(itemView);
            this.context = context;
            this.img_marcas = itemView.findViewById(R.id.image_item_marcas);
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
            imagenes_marcas.get(position).setImgMarca(result);
        }
    }
}

