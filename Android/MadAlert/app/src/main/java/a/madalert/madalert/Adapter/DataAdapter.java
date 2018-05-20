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

import java.lang.reflect.Array;
import java.util.ArrayList;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<Alertas> mAndroidList;
    private ArrayList<String> urls;
    private String enlace, mDistrito;
    private Boolean mRadio;

    public DataAdapter(ArrayList<Alertas> androidList, boolean radio, String distrito) {
        mAndroidList = androidList;
        for(int i =0; i < mAndroidList.size();i++){
            urls.add(mAndroidList.get(i).getUrl());
        }
        mRadio = radio;
        mDistrito = distrito;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(mRadio || mDistrito.equals("Todos")) {
            holder.mTvDistrito.setText(mAndroidList.get(position).getDistrito());
        }
        final Alertas alerta = mAndroidList.get(position);

        if(mRadio) {
            holder.mTvDistrito.setText(alerta.getDistrito());
        }
        holder.mTvCategoria.setText(alerta.getCategoria());
        holder.mTvFecha.setText(alerta.getFecha());
        holder.mTvAlerta.setText(alerta.getAlertas());

        if((alerta.getVerificado() != null) && !alerta.getVerificado()) {
            holder.mIvVerificado.setImageResource(R.drawable.blanco);
        } else {
            holder.mIvVerificado.setImageResource(R.drawable.verificado);
        }
        /*String urlActual = urls.get(position); //Nuevo
        if(urlActual!=null){
            holder.mTvAlerta.setTextColor(Color.parseColor("#FF4081"));
            holder.mTvAlerta.setPaintFlags(holder.mTvAlerta.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

            holder.mTvAlerta.setOnClickListener((View v) -> {
                Uri uri = Uri.parse(urlActual);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            });
        }*/
        if(alerta.getUrl() != null) {

            holder.mTvAlerta.setTextColor(Color.parseColor("#FF4081"));
            holder.mTvAlerta.setPaintFlags(holder.mTvAlerta.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

            enlace = alerta.getUrl();

            holder.mTvAlerta.setOnClickListener((View v) -> {
                Uri uri = Uri.parse(enlace);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            });
        }

        if(alerta.getCategoria().equals("Desastres y accidentes")) {
            holder.mIvImagen.setImageResource(R.drawable.accident);
        } else if(alerta.getCategoria().equals("Transporte público")) {
            holder.mIvImagen.setImageResource(R.drawable.bus);
        } else if(alerta.getCategoria().equals("Eventos")) {
            holder.mIvImagen.setImageResource(R.drawable.events);
        } else if(alerta.getCategoria().equals("Criminalidad")) {
            holder.mIvImagen.setImageResource(R.drawable.criminal);
        } else if(alerta.getCategoria().equals("Terrorismo")) {
            holder.mIvImagen.setImageResource(R.drawable.terrorism);
        } else if(alerta.getCategoria().equals("Contaminación")) {
            holder.mIvImagen.setImageResource(R.drawable.contamination);
        } else if(alerta.getCategoria().equals("Tráfico")) {
            holder.mIvImagen.setImageResource(R.drawable.traffic);
        }

        holder.mTvFuente.setText("Fuente: " + mAndroidList.get(position).getFuente());

    }

    @Override
    public int getItemCount() {

        return mAndroidList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTvAlerta,mTvFuente,mTvCategoria,mTvFecha, mTvDistrito;
        private ImageView mIvImagen, mIvVerificado;

        public ViewHolder(View view) {
            super(view);

            if(mRadio || mDistrito.equals("Todos")) {
                mTvDistrito = view.findViewById(R.id.tv_distrito);
            }
            mTvCategoria = (TextView)view.findViewById(R.id.tv_categoria);
            mTvFecha = (TextView)view.findViewById(R.id.tv_fecha);
            mTvAlerta = (TextView)view.findViewById(R.id.tv_alerta);
            mIvImagen= (ImageView)view.findViewById(R.id.imagenLogo);
            mIvVerificado = (ImageView) view.findViewById(R.id.imagenVerificado);
            mTvFuente = (TextView)view.findViewById(R.id.tv_fuente);
            enlace = null;
        }

        public void onClick(View v) {
            Uri uri = Uri.parse(enlace);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            v.getContext().startActivity(intent);
        }
    }
}
