package com.example.reusados;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;


public class FragmentPrenda extends Fragment implements ChildEventListener,View.OnClickListener {

   private static String busqueda;
    private ArrayList<Prenda> prendas;
    private RecyclerView recyclerView;
    private PrendaAdaptador myAdapter;
    private OnFragmentInteractionListener mListener;

    public static FragmentPrenda newInstance(String PalabraDebusqueda) {
        FragmentPrenda fragment = new FragmentPrenda();
        Bundle args = new Bundle();
        busqueda = PalabraDebusqueda;
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaLayout = inflater.inflate(R.layout.fragment_fragment_prenda, container, false);


       prendas = new ArrayList<>();
       recyclerView = vistaLayout.findViewById(R.id.recyclerviewPrendas);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
       myAdapter = new PrendaAdaptador(prendas,getActivity());
       myAdapter.setOnClickListener(this);
       recyclerView.setAdapter(myAdapter);
        rellenadoDelRecycler(busqueda);
         return vistaLayout;
    }
    @Override
    public void onClick(View view) {
        int position = recyclerView.getChildAdapterPosition(view);
        Prenda prendaSeleccinada = prendas.get(position);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentPrendaDetalle fragmentDetalle = FragmentPrendaDetalle.newInstance(prendaSeleccinada);
        fragmentTransaction.replace(R.id.fragment_container, fragmentDetalle);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public FragmentPrenda() {
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Prenda p = dataSnapshot.getValue(Prenda.class);
        prendas.add(p);
        myAdapter.notifyDataSetChanged();



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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void rellenadoDelRecycler(String palabra) {
        boolean flag = true;
        String busqueda = null;
        Query bdNodoReusados = null;
        switch (palabra) {
            case "ADIDAS":
                flag = false;
                busqueda = "ADIDAS";
                break;
            case "CHAMPION":
                flag = false;
                busqueda = "CHAMPION";
                break;
            case "FILA":
                flag = false;
                busqueda = "FILA";
                break;
            case "KAPPA":
                flag = false;
                busqueda = "KAPPA";
                break;
            case "NIKE":
                flag = false;
                busqueda = "NIKE";
                break;
            case "NORTH FACE":
                flag = false;
                busqueda = "NORTH FACE";
                break;
            case "PUMA":
                flag = false;
                busqueda = "PUMA";
                break;
            case "RALPH LAUREN":
                flag = false;
                busqueda = "RALPH LAUREN";
                break;
            case "REEBOK":
                flag = false;
                busqueda = "REEBOK";
                break;
            case "TOMMY HILFIGER":
                flag = false;
                busqueda = "TOMMY HILFIGER";
                break;
            case "ABRIGO":
                flag = true;
                busqueda = "abrigo";
                break;
            case "BOMBER":
                flag = true;
                busqueda = "bomber";
                break;
            case "CHAQUETA":
                flag = true;
                busqueda = "chaqueta";
                break;
            case "DEPORTE":
                flag = true;
                busqueda = "deporte";
                break;
            case "HAWAIANA":
                flag = true;
                busqueda = "hawaiana";
                break;
            case "POLO":
                flag = true;
                busqueda = "polo";
                break;
            case "SUDADERA":
                flag = true;
                busqueda = "sudadera";
                break;
            case "ZAPATILLAS":
                flag = true;
                busqueda = "zapatilla";
                break;
            case "TODAS":
                bdNodoReusados = FirebaseDatabase.getInstance().getReference()
                    .child("articulos");

                break;
            default:

                break;


        }
        if (palabra.equalsIgnoreCase("todas")){

        }else{
            if (flag == true) {
                bdNodoReusados = FirebaseDatabase.getInstance().getReference()
                        .child("prendas").child(busqueda);
            } else if (flag == false) {
                bdNodoReusados = FirebaseDatabase.getInstance().getReference()
                        .child("articulos").orderByChild("marca").equalTo(busqueda);
            }
        }

        bdNodoReusados.addChildEventListener(this);

    }

    }
