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
    private String mDistrito;
    private Boolean mRadio;
    private Spanned text;
    private Integer mKm;

    public DataAdapter(ArrayList<Alertas> androidList, boolean radio, String distrito, Integer km) {
        mAndroidList = androidList;
        mRadio = radio;
        mDistrito = distrito;
        mKm = km;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Alertas alerta = mAndroidList.get(position);

        if((mRadio && mKm > 0) || mDistrito.equals("Todos")) {
            holder.mTvDistrito.setText(alerta.getDistrito());
        }
        holder.mTvCategoria.setText(alerta.getCategoria());
        holder.mTvFecha.setText(alerta.getFecha());

        if((alerta.getVerificado() != null) && !alerta.getVerificado()) {
            holder.mIvVerificado.setImageResource(R.drawable.blanco);
        } else {
            holder.mIvVerificado.setImageResource(R.drawable.verificado);
        }

        if(alerta.getUrl() != null && alerta.getFuente().equals("Madridiario")) { // para madridiario
            text = Html.fromHtml("<a href=" + alerta.getUrl() + ">" + alerta.getAlertas() + "</a>");
            holder.mTvAlertaWeb.setMovementMethod(LinkMovementMethod.getInstance());
            holder.mTvAlertaOther.setText("");
            holder.mTvAlertaWeb.setText(text);
        }
        else { // para twitter e inserciones de usuario
            holder.mTvAlertaWeb.setText("");
            holder.mTvAlertaOther.setText(alerta.getAlertas());
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvAlertaWeb,mTvAlertaOther,mTvFuente,mTvCategoria,mTvFecha, mTvDistrito;
        private ImageView mIvImagen, mIvVerificado;

        public ViewHolder(View view) {
            super(view);

            if(mRadio || mDistrito.equals("Todos")) {
                mTvDistrito = view.findViewById(R.id.tv_distrito);
            }
            mTvCategoria = (TextView)view.findViewById(R.id.tv_categoria);
            mTvFecha = (TextView)view.findViewById(R.id.tv_fecha);
            mTvFuente = (TextView)view.findViewById(R.id.tv_fuente);
            mTvAlertaWeb = (TextView) view.findViewById(R.id.tv_alertaWeb);
            mTvAlertaOther = (TextView) view.findViewById(R.id.tv_alertaOther);
            mIvImagen= (ImageView)view.findViewById(R.id.imagenLogo);
            mIvVerificado = (ImageView) view.findViewById(R.id.imagenVerificado);
        }

    }
}
