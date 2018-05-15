package a.madalert.madalert.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import a.madalert.madalert.Alertas;
import a.madalert.madalert.R;

import java.util.ArrayList;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<Alertas> mAndroidList;
    private String enlace;
    private Boolean mRadio;

    public DataAdapter(ArrayList<Alertas> androidList, boolean radio) {
        mAndroidList = androidList;
        mRadio = radio;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(mRadio) {
            holder.mTvDistrito.setText(mAndroidList.get(position).getDistrito());
        }
        holder.mTvCategoria.setText(mAndroidList.get(position).getCategoria());
        holder.mTvFecha.setText(mAndroidList.get(position).getFecha());
        holder.mTvAlerta.setText(mAndroidList.get(position).getAlertas());

        //holder.mTvURL.setText(mAndroidList.get(position).getUrl());
        if(mAndroidList.get(position).getUrl() != null) {
            holder.mTvAlerta.setTextColor(Color.parseColor("#FF4081"));
            holder.mTvAlerta.setPaintFlags(holder.mTvAlerta.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

            holder.mTvAlerta.setOnClickListener(v -> {
                enlace = mAndroidList.get(position).getUrl();
                Uri uri = Uri.parse(enlace);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            });
        }

        if(mAndroidList.get(position).getCategoria().equals("Desastres y accidentes")) {
            holder.mIvImagen.setImageResource(R.drawable.accident);
        } else if(mAndroidList.get(position).getCategoria().equals("Transporte público")) {
            holder.mIvImagen.setImageResource(R.drawable.bus);
        } else if(mAndroidList.get(position).getCategoria().equals("Eventos")) {
            holder.mIvImagen.setImageResource(R.drawable.events);
        } else if(mAndroidList.get(position).getCategoria().equals("Criminalidad")) {
            holder.mIvImagen.setImageResource(R.drawable.criminal);
        } else if(mAndroidList.get(position).getCategoria().equals("Terrorismo")) {
            holder.mIvImagen.setImageResource(R.drawable.terrorism);
        } else if(mAndroidList.get(position).getCategoria().equals("Contaminación")) {
            holder.mIvImagen.setImageResource(R.drawable.contamination);
        } else if(mAndroidList.get(position).getCategoria().equals("Tráfico")) {
            holder.mIvImagen.setImageResource(R.drawable.traffic);
        }

        holder.mTvFuente.setText("Fuente: " + mAndroidList.get(position).getFuente());

    }

    @Override
    public int getItemCount() {

        return mAndroidList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvAlerta,mTvFuente,mTvCategoria,mTvFecha, mTvDistrito;
        private ImageView mIvImagen;

        public ViewHolder(View view) {
            super(view);

            if(mRadio) {
                mTvDistrito = view.findViewById(R.id.tv_distrito);
            }
            mTvCategoria = (TextView)view.findViewById(R.id.tv_categoria);
            mTvFecha = (TextView)view.findViewById(R.id.tv_fecha);
            mTvAlerta = (TextView)view.findViewById(R.id.tv_alerta);
            mIvImagen= (ImageView)view.findViewById(R.id.imagenLogo);
            mTvFuente = (TextView)view.findViewById(R.id.tv_fuente);
        }

        public void onClick(View v) {
            Uri uri = Uri.parse(enlace);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            v.getContext().startActivity(intent);
        }
    }
}
