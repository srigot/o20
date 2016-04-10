package com.rigot.macavealpha;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rigot.macavealpha.metier.GestionCave;
import com.rigot.macavealpha.util.RecyclerItemClickListener;

import java.util.List;

import fr.rigot.cavavin.backend.vins.model.Vin;

/**
 * Auteur : Seb
 * Date de creation : 06/07/2015
 */
public class ListeVinFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initialiserRecyclerView(rootView);

        return rootView;
    }

    private void initialiserRecyclerView(View rootView) {
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerListMain);
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);

            mLayoutManager = new LinearLayoutManager(rootView.getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            mAdapter = new VinAdapter(GestionCave.getInstance().getListeVins());
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    // Ouvrir le detail du vin sélectionne
                    Intent intent = new Intent(getActivity(), DetailVinActivity.class);
                    intent.putExtra(DetailVinActivity.ID, position);
                    startActivity(intent);
                }
            }));

        }
    }

    public void updateListe(List<Vin> liste) {
        ((VinAdapter) mAdapter).setListe(liste);
        mAdapter.notifyDataSetChanged();
    }

}
