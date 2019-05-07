package com.example.reusados;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TipoPrendaAdaptador extends RecyclerView.Adapter<TipoPrendaViewHolder> implements View.OnClickListener{
   private ArrayList<TipoPrenda> prendas;
    private Context context;
    private View.OnClickListener mlistener;

    public TipoPrendaAdaptador(ArrayList<TipoPrenda> prendas, Context context) {
        this.prendas = prendas;
        this.context = context;
    }

    @Override
    public TipoPrendaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from((parent.getContext()))
                .inflate(R.layout.item_tipo_prenda,parent,false);
        TipoPrendaViewHolder viewHolder = new TipoPrendaViewHolder(itemView, context);
        itemView.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TipoPrendaViewHolder holder, int position) {
            TipoPrenda prenda = prendas.get(position);
            holder.bindItem(prenda);
    }

    @Override
    public int getItemCount() {
        return prendas.size();
    }

    @Override
    public void onClick(View view) {
      if(mlistener != null){
          mlistener.onClick(view);
      }
    }
    public void setOnClickListener(View.OnClickListener listener) {
        mlistener = listener;
    }

}
