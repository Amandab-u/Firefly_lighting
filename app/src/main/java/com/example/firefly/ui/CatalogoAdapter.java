package com.example.firefly.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firefly.R;
import com.example.firefly.database.relations.CatalogoCompleto;

import java.util.List;

public class CatalogoAdapter extends RecyclerView.Adapter<CatalogoAdapter.UIViewHolder>{

    Context c;

    private View.OnClickListener mOnItemClickListener;



    class UIViewHolder extends RecyclerView.ViewHolder {
        private final TextView nombreItemView;
        private final TextView flujoItemView;
        private final TextView potenciaItemView;
        private final TextView dimensionesItemView;
        private final ImageView imagenItemView;

        private UIViewHolder(View itemView) {
            super(itemView);
            nombreItemView = itemView.findViewById(R.id.nombre_descripcion);
            flujoItemView = itemView.findViewById(R.id.flujo_luminsoso);
            potenciaItemView = itemView.findViewById(R.id.potencia);
            dimensionesItemView = itemView.findViewById(R.id.dimensiones_luminaria);
            imagenItemView = itemView.findViewById(R.id.imagen_catalogo);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }



    }

    private final LayoutInflater mInflater;
    private List<CatalogoCompleto> mCatalogoCompleto;// Cached copy of catalogo items


    CatalogoAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public UIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.catalogo_recyclerview_item, parent, false);
        return new UIViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UIViewHolder holder, int position) {

        if (mCatalogoCompleto != null) {
            CatalogoCompleto currentL= mCatalogoCompleto.get(position);
            holder.nombreItemView.setText(currentL.catalogo.getNombre()+ " " + currentL.catalogo.getDescripcion());
            holder.flujoItemView.setText(currentL.catalogo.getFlujoLumTotal() + " lm");
            holder.potenciaItemView.setText(currentL.catalogo.getPotenciaElectrica()+" W");

            holder.imagenItemView.setImageResource(currentL.catalogo.getImagenLumReal());
//            holder.imagenItemView.setBackgroundResource(currentL.catalogo.getImagenLumReal());
            String dimensiones = "Dimensiones: ";

            if(currentL.largos!=null) {
                dimensiones = dimensiones +currentL.largos.getLargo();
            }
            if(currentL.anchos!=null) {
                dimensiones = dimensiones + "x"+ currentL.anchos.getAncho();
            }
            if(currentL.diametro!=null) {
                dimensiones = dimensiones + currentL.diametro.getDiametro();
            }
            if(currentL.altos!=null) {
                dimensiones = dimensiones +"x"+ currentL.altos.getAlto();
            }

            if(currentL.profundidades!=null) {
                dimensiones = dimensiones +"x"+ currentL.profundidades.getProfundidad();
            }

            holder.dimensionesItemView.setText(dimensiones);


        } else {
            // Covers the case of data not being ready yet.
            holder.nombreItemView.setText("No Existen Luminarias");
        }



    }

    void setCatalogo(List<CatalogoCompleto> words){
        mCatalogoCompleto = words;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        if (mCatalogoCompleto != null )
            return mCatalogoCompleto.size();
        else return 0;
    }
}
