package com.example.firefly.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firefly.R;
import com.example.firefly.database.relations.ProyectoCompleto;

import java.util.List;

public class ListaProyectosAdapter extends RecyclerView.Adapter<ListaProyectosAdapter.UIViewHolder> {


    private static final String TAG = "com.example.firefly.UI";
    private View.OnClickListener mOnItemClickListener;

    class UIViewHolder extends RecyclerView.ViewHolder {
        private final TextView tituloItemView;
        private final TextView dimensionesItemView;

        private UIViewHolder(View itemView) {
            super(itemView);
            tituloItemView = itemView.findViewById(R.id.titulo_proyecto);
            dimensionesItemView = itemView.findViewById(R.id.dimensiones_proyecto);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }


    }



    private final LayoutInflater mInflater;
    private List<ProyectoCompleto> mProyectos; // Cached copy of proyectos items


    ListaProyectosAdapter(Context context){mInflater = LayoutInflater.from(context);}

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ListaProyectosAdapter.UIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.proyecto_recyclerview_item, parent, false);
        return new UIViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UIViewHolder holder, int position) {

        if (mProyectos!= null){

            ProyectoCompleto current = mProyectos.get(position);
            holder.tituloItemView.setText(current.proyecto.getNombreProyecto());
            if (current.datosLocal !=null){
                holder.dimensionesItemView.setText("Dimensiones: "+ current.local.getAltoLocal() + " "+current.datosLocal.getId_proyectoL());
            }


        }else {

            Log.v(TAG,"No existen Proyectos");
        }
    }

    public void setProyectos(List<ProyectoCompleto> proyecto){
        mProyectos = proyecto;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mProyectos!=null){
            return mProyectos.size();
        } else return 0;
    }


}
