package com.example.gestiondecompte.view.adapter;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestiondecompte.R;
import com.example.gestiondecompte.model.Flux;
import com.example.gestiondecompte.model.FluxExceptionnel;
import com.example.gestiondecompte.model.FluxMensuel;

import org.xmlpull.v1.XmlPullParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ListeFluxAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Flux> listeFlux;
    private ClickNoteListener listener;

    private final int VIEWTYPE_FLUX_MENSUEL = 1;
    private final int VIEWTYPE_FLUX_EXCEPTIONNEL = 2;

    public ListeFluxAdapter(List<Flux> listeFlux) {
        this.listeFlux = listeFlux;
    }

    public interface ClickNoteListener {
        void onClickNoteListener(Flux flux);
    }

    public ListeFluxAdapter(List<Flux> listeFlux, ClickNoteListener listener) {
        this.listeFlux = listeFlux;
        this.listener = listener;
    }

    static class FluxMensuelViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNomFlux;
        TextView textViewMontant;
        TextView textViewDateFlux;
        LinearLayout layout;

        public FluxMensuelViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomFlux = itemView.findViewById(R.id.textView_nomFlux);
            textViewMontant = itemView.findViewById(R.id.textView_montantFlux);
            textViewDateFlux = itemView.findViewById(R.id.textView_dateFlux);
            layout = itemView.findViewById(R.id.linearLayout_listeFlux);
        }
    }

    static class FluxExceptionnelViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNomFlux;
        TextView textViewMontant;
        TextView textViewDateFlux;
        LinearLayout layout;

        public FluxExceptionnelViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomFlux = itemView.findViewById(R.id.textView_nomFlux);
            textViewMontant = itemView.findViewById(R.id.textView_montantFlux);
            textViewDateFlux = itemView.findViewById(R.id.textView_dateFlux);
            layout = itemView.findViewById(R.id.linearLayout_listeFlux);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        if(viewType == VIEWTYPE_FLUX_MENSUEL) {
            view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_flux_liste,parent,false);
            return new FluxMensuelViewHolder(view);

        } else {
            view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_flux_liste,parent,false);

            return new FluxExceptionnelViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");

        if(holder.getItemViewType() == VIEWTYPE_FLUX_MENSUEL) {

            FluxMensuel flux = (FluxMensuel) listeFlux.get(position);
            FluxMensuelViewHolder fluxMensuelViewHolder = (FluxMensuelViewHolder) holder;
            fluxMensuelViewHolder.textViewNomFlux.setText(flux.getNom());
            if (flux.getMontant() < 0) {
                fluxMensuelViewHolder.textViewMontant.setTextColor(Color.RED);
            }
            else {
                fluxMensuelViewHolder.textViewMontant.setTextColor(Color.GREEN);
            }
            fluxMensuelViewHolder.textViewMontant.setText(String.valueOf(flux.getMontant()));
            fluxMensuelViewHolder.textViewDateFlux.setText(String.valueOf(formatter.format(flux.getJourDuMois())));

            fluxMensuelViewHolder.layout.setOnClickListener(v -> {
                listener.onClickNoteListener(flux);
            });

        } else {
            FluxExceptionnel flux = (FluxExceptionnel) listeFlux.get(position);
            FluxExceptionnelViewHolder fluxExceptionnelViewHolder = (FluxExceptionnelViewHolder) holder;
            fluxExceptionnelViewHolder.textViewNomFlux.setText(flux.getNom());
            if (flux.getMontant() < 0) {
                fluxExceptionnelViewHolder.textViewMontant.setTextColor(Color.RED);
            }
            else {
                fluxExceptionnelViewHolder.textViewMontant.setTextColor(Color.GREEN);
            }
            fluxExceptionnelViewHolder.textViewMontant.setText(String.valueOf(flux.getMontant()));
            fluxExceptionnelViewHolder.textViewDateFlux.setText(String.valueOf(formatter.format(flux.getDateFlux())));

            fluxExceptionnelViewHolder.layout.setOnClickListener(v -> {
                listener.onClickNoteListener(flux);
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        return listeFlux.get(position) instanceof FluxMensuel ? VIEWTYPE_FLUX_MENSUEL : VIEWTYPE_FLUX_EXCEPTIONNEL;
    }

    @Override
    public int getItemCount() {
        return listeFlux.size();
    }


}
