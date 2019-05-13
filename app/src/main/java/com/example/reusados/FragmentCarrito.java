package com.example.reusados;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;


public class FragmentCarrito extends Fragment implements ChildEventListener,View.OnClickListener{

    private ArrayList<Prenda> prendas;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CarritoAdaptador myAdapter;
    private OnFragmentInteractionListener mListener;
    AlertDialog.Builder ventanaFlotante;


    public FragmentCarrito() {
    }

    public static FragmentCarrito newInstance() {
        FragmentCarrito fragment = new FragmentCarrito();
        Bundle args = new Bundle();

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
        View vistaLayout = inflater.inflate(R.layout.fragment_fragment_carrito, container, false);
        prendas = new ArrayList<>();
        recyclerView = vistaLayout.findViewById(R.id.recyclerviewCarrito);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new CarritoAdaptador(prendas,getActivity());
        myAdapter.setOnClickListener(this);
        recyclerView.setAdapter(myAdapter);

        Query bdNodoReusados = FirebaseDatabase.getInstance().getReference()
                .child("carrito");
        bdNodoReusados.addChildEventListener(this);


        return vistaLayout;
    }

    @Override
    public void onClick(View view) {
        int position = recyclerView.getChildAdapterPosition(view);
        final Prenda prendaSeleccinada = prendas.get(position);
        ventanaFlotante = new AlertDialog.Builder(getActivity());
        ventanaFlotante.setTitle("");
        ventanaFlotante.setMessage("¿Esta seguro que desea eliminar la prenda?");
        ventanaFlotante.setCancelable(false);
        ventanaFlotante.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseReference dbCarrito = FirebaseDatabase.getInstance().getReference().child("carrito");
                dbCarrito.child(prendaSeleccinada.getKey()).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if(databaseError == null){
                            Toast.makeText(getActivity(),"Prenda borrada",Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(getActivity(),"Se ha producido un error",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });
        ventanaFlotante.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        ventanaFlotante.show();


    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Prenda p = dataSnapshot.getValue(Prenda.class);
        p.setKey(dataSnapshot.getKey());
        prendas.add(p);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        Prenda prendaBorrada = dataSnapshot.getValue(Prenda.class);
        for(int i = 0; i < prendas.size(); i++){
            if(prendas.get(i).getNombre().equalsIgnoreCase(prendaBorrada.getNombre())){
                prendas.remove(i);
                myAdapter.notifyDataSetChanged();
                break;
            }
        }

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
}
