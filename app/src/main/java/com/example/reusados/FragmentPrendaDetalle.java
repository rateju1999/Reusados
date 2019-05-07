package com.example.reusados;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentPrendaDetalle extends Fragment {


    private OnFragmentInteractionListener mListener;

    public FragmentPrendaDetalle() {
    }

    public static FragmentPrendaDetalle newInstance(Prenda prenda) {

        Bundle args = new Bundle();

        FragmentPrendaDetalle fragment = new FragmentPrendaDetalle();
        fragment.setArguments(args);
        return fragment;
    }

    public static FragmentPrendaDetalle newInstance(String param1, String param2) {
        FragmentPrendaDetalle fragment = new FragmentPrendaDetalle();
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
        return inflater.inflate(R.layout.fragment_fragment_prenda_detalle, container, false);
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
}
