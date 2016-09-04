package com.example.deepankur.diagnosisapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepankur.diagnosisapplication.R;
import com.example.deepankur.diagnosisapplication.models.PastResultsModel;

import java.util.ArrayList;

/**
 * Created by deepankur on 9/5/16.
 */

public class PreviousResultsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<PastResultsModel> pastResultsModels;


    public PreviousResultsAdapter(ArrayList<PastResultsModel> pastResultsModels) {
        this.pastResultsModels = pastResultsModels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new VHItem(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_past_results, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHItem)
            ((VHItem) holder).result.setText(pastResultsModels.get(position).getPercentagePredicted());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class VHItem extends RecyclerView.ViewHolder {
        TextView result;

        public VHItem(View itemView) {
            super(itemView);
            result = (TextView) itemView.findViewById(R.id.resultTV);
        }
    }
}
