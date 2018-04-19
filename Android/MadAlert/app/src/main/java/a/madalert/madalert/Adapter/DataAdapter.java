package a.madalert.madalert.Adapter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import a.madalert.madalert.Alertas;
import a.madalert.madalert.R;

import java.util.ArrayList;


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
            holder.mTvAlerta.setTextColor(Color.parseColor("#FF4081"));
            holder.mTvAlerta.setPaintFlags(holder.mTvAlerta.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

            holder.mTvAlerta.setOnClickListener(v -> {
                enlace = mAndroidList.get(position).getUrl();
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
