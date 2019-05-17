package com.example.reusados;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class FragmentMain extends Fragment implements  View.OnClickListener, ChildEventListener {





    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MarcasAdaptador marcasAdaptador;
    private ArrayList<Marca> marcas;
    private ImageView imgTodas, imgSudaderas, imgChaquetas;

    public FragmentMain() {
    }

    public static FragmentMain newInstance() {
        FragmentMain fragment = new FragmentMain();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaLayout = inflater.inflate(R.layout.fragment_main, container, false);
        marcas = new ArrayList<>();
        recyclerView = vistaLayout.findViewById(R.id.recyclerVIewMarcas);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        marcasAdaptador = new MarcasAdaptador(marcas,getActivity());
        marcasAdaptador.setOnClickListener(this);
        recyclerView.setAdapter(marcasAdaptador);
        imgTodas = vistaLayout.findViewById(R.id.main_img_todas);
        imgTodas.setOnClickListener(this);
        imgSudaderas = vistaLayout.findViewById(R.id.main_img_sudaderas);
        imgSudaderas.setOnClickListener(this);
        imgChaquetas = vistaLayout.findViewById(R.id.main_img_chaquetas);
        imgChaquetas.setOnClickListener(this);

        //PARTE DEL FIREBASE
        Query bdNodoReusados = FirebaseDatabase.getInstance().getReference()
                .child("marca");
        bdNodoReusados.addChildEventListener(this);
        return vistaLayout;

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
        String palabra = null;

        if(view.getId() == R.id.main_img_chaquetas){
            palabra = "CHAQUETA";
        } else if(view.getId() == R.id.main_img_todas){
            palabra = "TODAS";
        } else if(view.getId() == R.id.main_img_sudaderas){
            palabra = "SUDADERA";
        }else{
            int position = recyclerView.getChildAdapterPosition(view);
            Marca marcaSeleccionada = marcas.get(position);
            palabra = marcaSeleccionada.getNombre();
        }
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentPrenda fragmentPrenda = FragmentPrenda.newInstance(palabra);
        fragmentTransaction.replace(R.id.fragment_container, fragmentPrenda);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Marca m = dataSnapshot.getValue(Marca.class);
        marcas.add(m);
        marcasAdaptador.notifyDataSetChanged();
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
