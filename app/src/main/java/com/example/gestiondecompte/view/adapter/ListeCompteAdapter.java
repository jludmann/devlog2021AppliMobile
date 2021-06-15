package com.example.gestiondecompte.view.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestiondecompte.R;
import com.example.gestiondecompte.model.Compte;

import java.util.List;

public class ListeCompteAdapter extends RecyclerView.Adapter<ListeCompteAdapter.CompteViewHolder> {


    List<Compte> listeCompte;
    private ClickCompteListener listener;

    public ListeCompteAdapter(List<Compte> listeCompte, ClickCompteListener listener) {
        this.listeCompte = listeCompte;
        this.listener = listener;
    }

    public interface ClickCompteListener {
        void onClickNoteListener(Compte compte);
    }

    @NonNull
    @Override
    public CompteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_compte_liste, parent, false);
        CompteViewHolder compteViewHolder = new CompteViewHolder(view);
            return new CompteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListeCompteAdapter.CompteViewHolder holder, int position) {
        Compte compte = listeCompte.get(position);
        holder.textViewNomCompte.setText(compte.getNom());
        holder.textViewSolde.setText(String.valueOf(compte.getSolde()));
        if (compte.getSeuilAlerte() <= compte.getSolde()) {
            holder.textViewSolde.setTextColor(Color.GREEN);
        }
        else {
            holder.textViewSolde.setTextColor(Color.RED);
        }
        holder.recyclerViewListeFlux.setAdapter(new ListeFluxAdapter(compte.getListeFlux()));


            holder.layout.setOnClickListener(v -> {
                listener.onClickNoteListener(compte);
            });

    }

    @Override
    public int getItemCount() {
        return listeCompte.size();
    }

    public static class CompteViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNomCompte;
        TextView textViewSolde;
        RecyclerView recyclerViewListeFlux;
        LinearLayout layout;

        public CompteViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomCompte = itemView.findViewById(R.id.textView_nomCompte);
            textViewSolde = itemView.findViewById(R.id.textView_solde);
            recyclerViewListeFlux = itemView.findViewById(R.id.recyclerView_listeFlux);
            recyclerViewListeFlux.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            layout = itemView.findViewById(R.id.linearLayout_compteListe);
        }
    }
}
