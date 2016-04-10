package com.rigot.macavealpha;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rigot.macavealpha.ref.RefCouleur;

import java.util.ArrayList;
import java.util.List;

import fr.rigot.cavavin.backend.vins.model.Vin;

/**
 * Created by Seb on 08/11/13.
 */
public class VinAdapter extends RecyclerView.Adapter<VinAdapter.ViewHolder> {
    private List<Vin> liste;

    public VinAdapter(List<Vin> liste) {
        if (liste == null) {
            this.liste = new ArrayList<Vin>();
        } else {
            this.liste = liste;
        }
    }

    public void setListe(List<Vin> liste) {
        this.liste = liste;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vin, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);

        vh.nom = (TextView) v.findViewById(R.id.nomVin);
        vh.appellation = (TextView) v.findViewById(R.id.appellationVin);
        vh.annee = (TextView) v.findViewById(R.id.anneeVin);
        vh.couleur = (View) v.findViewById(R.id.colorVin);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Vin v = liste.get(position);

        if (v != null) {
            viewHolder.nom.setText(v.getNom());
            if (v.getAppellation() != null) {
                viewHolder.appellation.setText(v.getAppellation().toString());
            }
            if (v.getAnnee() != null) {
                viewHolder.annee.setText(v.getAnnee().toString());
            }
            if (v.getCouleur() != null) {
                viewHolder.couleur.setBackgroundResource(RefCouleur.valueOf(v.getCouleur()).getCouleur());
            }
        }
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView nom;
        public TextView appellation;
        public TextView annee;
        public View couleur;

        public ViewHolder(View v) {
            super(v);
        }
    }
}
