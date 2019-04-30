package com.example.reusados.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reusados.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentOpc3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentOpc3 extends Fragment {



    public static FragmentOpc3 newInstance() {
        FragmentOpc3 fragment = new FragmentOpc3();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_opc3, container, false);
    }

}
