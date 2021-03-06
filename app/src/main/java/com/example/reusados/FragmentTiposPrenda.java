package com.example.reusados;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class FragmentTiposPrenda extends Fragment  implements  View.OnClickListener{

    private FragmentMain.OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TipoPrendaAdaptador tipoPrendaAdaptador;
    private ArrayList<TipoPrenda> tipos;

    public FragmentTiposPrenda() {

    }


    public static FragmentTiposPrenda newInstance() {
        FragmentTiposPrenda fragment = new FragmentTiposPrenda();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaLayout = inflater.inflate(R.layout.fragment_fragment_tipos_prenda, container, false);
        tipos = new ArrayList<>();
        recyclerView = vistaLayout.findViewById(R.id.recyclerViewTipoPrenda);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        tipoPrendaAdaptador = new TipoPrendaAdaptador(tipos,getActivity());
        tipoPrendaAdaptador.setOnClickListener(this);
        recyclerView.setAdapter(tipoPrendaAdaptador);
        tipos.add(new TipoPrenda("ABRIGO",R.drawable.abrigo_sf));
        tipos.add(new TipoPrenda("BOMBER",R.drawable.bomber_sf));
        tipos.add(new TipoPrenda("CHAQUETA",R.drawable.chaqueta_sf));
        tipos.add(new TipoPrenda("DEPORTE",R.drawable.deporte_sf));
        tipos.add(new TipoPrenda("HAWAIANA",R.drawable.hawaiana_sf));
        tipos.add(new TipoPrenda("JERSEY",R.drawable.jersey_sf));
        tipos.add(new TipoPrenda("POLO",R.drawable.polo_sf));
        tipos.add(new TipoPrenda("SUDADERA",R.drawable.sudadera_sf));
        tipos.add(new TipoPrenda("ZAPATILLAS",R.drawable.zapatilla_sf));

        tipoPrendaAdaptador.notifyDataSetChanged();

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
        int position = recyclerView.getChildAdapterPosition(view);
        TipoPrenda tipoPrendaSeleccinada = tipos.get(position);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentPrenda fragmentoMain = FragmentPrenda.newInstance(tipoPrendaSeleccinada.getTipo());
        fragmentTransaction.replace(R.id.fragment_container, fragmentoMain);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
