package com.example.reusados;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;

public class FragmentPrendaDetalle extends Fragment implements View.OnClickListener {

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
        botonCompra.setOnClickListener(this);
        //PIENSA QUE COÑO QUIERES HACER CON EL BOTON DE COMPRA
        //PIENSA QUE COÑO QUIERES HACER CON EL BOTON DE COMPRA
        //PIENSA QUE COÑO QUIERES HACER CON EL BOTON DE COMPRA
        //PIENSA QUE COÑO QUIERES HACER CON EL BOTON DE COMPRA
        new DownloadImageTask(imagen)
                .execute(prendaPasada.getUrlImagenPrenda());
        nombre.setText(prendaPasada.getNombre());
        precio.setText("Precio: " +prendaPasada.getPrecio()+"€");
        talla.setText("Talla: " + prendaPasada.getTalla());

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

    @Override
    public void onClick(View view) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("carrito");
        database.push().setValue(new Prenda (prendaPasada.getUrlImagenPrenda(),prendaPasada.getPrecio(),prendaPasada.getNombre(),prendaPasada.getTalla()));

        Toast.makeText(getActivity(),prendaPasada.getNombre() + " añadido a Compras",Toast.LENGTH_SHORT).show();

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
    //TAREA ASINCRONA
    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage2;
        String urldisplay;

        public DownloadImageTask(ImageView Image) {
            this.bmImage2 = Image;
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
            imagen.setImageBitmap(result);
           // prendas.get(position).setImagenPrenda(result);
        }
    }
}
