package com.example.reusados;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class FragmentPrendaDetalle extends Fragment {

    private static Prenda prendaPasada;
    private ImageView imagen;
    private TextView nombre, precio,talla;
    private Button botonCompra;


    private OnFragmentInteractionListener mListener;

    public FragmentPrendaDetalle() {
    }



    public static FragmentPrendaDetalle newInstance(Prenda prendaQueMePasan) {
        FragmentPrendaDetalle fragment = new FragmentPrendaDetalle();
        prendaPasada = prendaQueMePasan;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaLayout = inflater.inflate(R.layout.fragment_fragment_prenda_detalle, container, false);
        imagen= vistaLayout.findViewById(R.id.detalle_prenda_imagen);
        nombre= vistaLayout.findViewById(R.id.detalle_nombre);
        precio= vistaLayout.findViewById(R.id.detalle_precio);
        talla= vistaLayout.findViewById(R.id.detalle_talla);
        botonCompra = vistaLayout.findViewById(R.id.detale_boton);
        //APAÑATELAS PARA SETEAR LA IMAGEN DESGRACIAO
        //APAÑATELAS PARA SETEAR LA IMAGEN DESGRACIAO
        //APAÑATELAS PARA SETEAR LA IMAGEN DESGRACIAO
        //APAÑATELAS PARA SETEAR LA IMAGEN DESGRACIAO
        //PIENSA QUE COÑO QUIERES HACER CON EL BOTON DE COMPRA
        //PIENSA QUE COÑO QUIERES HACER CON EL BOTON DE COMPRA
        //PIENSA QUE COÑO QUIERES HACER CON EL BOTON DE COMPRA
        //PIENSA QUE COÑO QUIERES HACER CON EL BOTON DE COMPRA
        //imagen.setImageResource();
        nombre.setText(prendaPasada.getNombre());
        precio.setText(prendaPasada.getPrecio()+"€");
        talla.setText(prendaPasada.getTalla());

        return vistaLayout;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
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
           // prendas.get(position).setImagenPrenda(result);
        }
    }
}
