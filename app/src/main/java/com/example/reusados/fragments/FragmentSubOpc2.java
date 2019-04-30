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
 * Use the {@link FragmentSubOpc2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSubOpc2 extends Fragment {



    public static FragmentSubOpc2 newInstance() {
        FragmentSubOpc2 fragment = new FragmentSubOpc2();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_sub_opc2, container, false);
    }

}
