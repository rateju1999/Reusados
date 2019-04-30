package com.example.reusados;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentMain extends Fragment implements  View.OnClickListener{





    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MarcasAdaptador marcasAdaptador;
    private ArrayList<Marca> marcas;

    public FragmentMain() {
    }


    // TODO: Rename and change types and number of parameters
//    public static Fragment newInstance(Activity context, int pos, float scale) {
//        Bundle b = new Bundle();
//        b.putInt("pos", pos);
//        b.putFloat("scale", scale);
//        return Fragment.instantiate(context, FragmentMain.class.getName(), b);
//    }
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

        marcas.add(new Marca("https://firebasestorage.googleapis.com/v0/b/reusados-d56ce.appspot.com/o/adidas.png?alt=media&token=30bb9739-ac76-4a66-80a4-83b4a7d8a10b"));
        marcas.add(new Marca("https://firebasestorage.googleapis.com/v0/b/reusados-d56ce.appspot.com/o/fila.png?alt=media&token=a3dbc9b3-259f-44d3-bcda-58d90421b97e"));
        marcas.add(new Marca("https://firebasestorage.googleapis.com/v0/b/reusados-d56ce.appspot.com/o/tommy.png?alt=media&token=6a8d0497-cc94-46db-a827-e31570706da4"));
        marcas.add(new Marca("https://firebasestorage.googleapis.com/v0/b/reusados-d56ce.appspot.com/o/reebok.png?alt=media&token=6324b308-3d91-4d03-a276-d07608ca84e3"));
        marcas.add(new Marca("https://firebasestorage.googleapis.com/v0/b/reusados-d56ce.appspot.com/o/ralph.png?alt=media&token=bb9ceacd-6eb5-42e5-980c-8af5c94166b5"));
        marcas.add(new Marca("https://firebasestorage.googleapis.com/v0/b/reusados-d56ce.appspot.com/o/puma.png?alt=media&token=7f13a1cd-3605-4b88-ab3a-00a7b5c6ea92"));
        marcas.add(new Marca("https://firebasestorage.googleapis.com/v0/b/reusados-d56ce.appspot.com/o/northface.png?alt=media&token=797812e0-4171-49f9-91af-6d4704bf9eee"));
        marcas.add(new Marca("https://firebasestorage.googleapis.com/v0/b/reusados-d56ce.appspot.com/o/nike.png?alt=media&token=b347c2d0-76cf-4582-8a94-a80ec48f431c"));
        marcas.add(new Marca("https://firebasestorage.googleapis.com/v0/b/reusados-d56ce.appspot.com/o/kappa.png?alt=media&token=bef18a79-aa15-4b43-8159-ecf7b54ba4bb"));
        marcas.add(new Marca("https://firebasestorage.googleapis.com/v0/b/reusados-d56ce.appspot.com/o/hh.png?alt=media&token=d5cd8da7-cc90-4497-a4a2-a957a0c14786"));
        marcas.add(new Marca("https://firebasestorage.googleapis.com/v0/b/reusados-d56ce.appspot.com/o/ellesse.png?alt=media&token=5683be3d-f5aa-4d83-ad82-e321cbfe607c"));
        marcas.add(new Marca("https://firebasestorage.googleapis.com/v0/b/reusados-d56ce.appspot.com/o/champion.png?alt=media&token=c7c5ec75-7f6b-4d20-a7b0-617205cb2b62"));
        marcasAdaptador.notifyDataSetChanged();
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

    }




    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
