package com.example.adrianpanaderogonzalez.pruebasbd.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Bundle;

import com.example.adrianpanaderogonzalez.pruebasbd.Alertas;
import com.example.adrianpanaderogonzalez.pruebasbd.R;

import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<Alertas> mAndroidList;
    private String enlace;

    public DataAdapter(ArrayList<Alertas> androidList) {
        mAndroidList = androidList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.mTvCategoria.setText(mAndroidList.get(position).getCategoria());
        holder.mTvAlerta.setText(mAndroidList.get(position).getAlertas());
        holder.mTvFuente.setText("Fuente: " + mAndroidList.get(position).getFuente());

        //holder.mTvURL.setText(mAndroidList.get(position).getUrl());
        if(mAndroidList.get(position).getUrl() != null) {
            enlace = mAndroidList.get(position).getUrl();

            holder.mTvAlerta.setOnClickListener(v -> {
                Uri uri = Uri.parse(enlace);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            });
        }

        holder.mTvFecha.setText(mAndroidList.get(position).getFecha());

    }

    @Override
    public int getItemCount() {

        return mAndroidList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvAlerta,mTvFuente,mTvCategoria,mTvFecha;

        public ViewHolder(View view) {
            super(view);


            mTvCategoria = (TextView)view.findViewById(R.id.tv_categoria);
            mTvAlerta = (TextView)view.findViewById(R.id.tv_alerta);
            mTvFuente = (TextView)view.findViewById(R.id.tv_fuente);
            mTvFecha = (TextView)view.findViewById(R.id.tv_fecha);

        }

        public void onClick(View v) {
            Uri uri = Uri.parse(enlace);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            v.getContext().startActivity(intent);
        }
    }
}
