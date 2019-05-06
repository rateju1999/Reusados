package com.example.reusados;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    public FragmentPrenda() {
        // Required empty public constructor
    }

    public static FragmentPrenda newInstance(String param1, String param2) {
        FragmentPrenda fragment = new FragmentPrenda();
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
        View vistaLayout = inflater.inflate(R.layout.fragment_fragment_prenda, container, false);


       prendas = new ArrayList<>();
       recyclerView = vistaLayout.findViewById(R.id.recyclerviewPrendas);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
       myAdapter = new PrendaAdaptador(prendas,getActivity());
       myAdapter.setOnClickListener(this);
       recyclerView.setAdapter(myAdapter);
        //PARTE DEL FIREBASE
        Query bdNodoReusados = FirebaseDatabase.getInstance().getReference()
                .child("articulos");
        bdNodoReusados.addChildEventListener(this);
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

    @Override
    public void onClick(View view) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
