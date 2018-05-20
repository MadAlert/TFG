package a.madalert.madalert.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import a.madalert.madalert.Alertas;
import a.madalert.madalert.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<Alertas> mAndroidList;
    private ArrayList<String> urls;
    private String enlace, mDistrito;
    private Boolean mRadio;
    private CardView cardView;

    public DataAdapter(ArrayList<Alertas> androidList, boolean radio, String distrito) {
        mAndroidList = androidList;
        urls  = new ArrayList<>();
        for(int i =0; i < mAndroidList.size();i++){
            String m = mAndroidList.get(i).getUrl();
            if(m!=null){
                urls.add(mAndroidList.get(i).getUrl());
            }
            else{
                urls.add("");
            }
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

        holder.mTvUrl.setText(alerta.getUrl());
        if(alerta.getUrl() != null && alerta.getFuente().equals("madridDiario")){
            Linkify.addLinks(holder.mTvUrl, Linkify.WEB_URLS);
        }

        /*if(alerta.getUrl() != null && alerta.getFuente().equals("madridDiario")){
            holder.mTvUrl.setText("Ver enlace");
            holder.mTvUrl.setOnClickListener((View v) -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(alerta.getUrl()));
                v.getContext().startActivity(intent);
            });
        }*/


       /*String urlActual = urls.get(position); //Nuevo
        if(!urlActual.equals("") && alerta.getFuente().equals("madridDiario")){
            holder.mTvUrl.setText("Ver enlace");
            holder.mTvUrl.setOnClickListener((View v) -> {
                Uri uri = Uri.parse(urlActual);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            });
        }*/

        /*if(alerta.getUrl() != null && alerta.getFuente().equals("madridDiario")) {
            //holder.mTvUrl.setText("Ver enlace");
            holder.mTvUrl.setText(alerta.getUrl());

            enlace = alerta.getUrl();
        }*/

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

        private TextView mTvAlerta,mTvFuente,mTvCategoria,mTvFecha, mTvDistrito, mTvUrl;
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
            mTvUrl = (TextView) view.findViewById(R.id.tv_url);
            cardView = (CardView) view.findViewById(R.id.card);

        }

        public void onClick(View v) {
            Uri uri = Uri.parse(enlace);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            v.getContext().startActivity(intent);
        }
    }
}
