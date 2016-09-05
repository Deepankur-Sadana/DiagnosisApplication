package com.example.deepankur.diagnosisapplication.adapters;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepankur.diagnosisapplication.R;
import com.example.deepankur.diagnosisapplication.database.FireBaseHelper;
import com.example.deepankur.diagnosisapplication.models.PastResultsModel;

import java.util.ArrayList;

/**
 * Created by deepankur on 9/5/16.
 */

public class PreviousResultsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<PastResultsModel> pastResultsModels;
    private final int VIEW_TYPE_ITEM = 22;
    private final int VIEW_TYPE_HEADER = 11;
    private FireBaseHelper fireBaseHelper;

    public PreviousResultsAdapter(ArrayList<PastResultsModel> pastResultsModels, FireBaseHelper fireBaseHelper) {
        this.pastResultsModels = pastResultsModels;
        this.fireBaseHelper = fireBaseHelper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ITEM)
            return new VHItem(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_past_results, parent, false));
        if (viewType == VIEW_TYPE_HEADER)
            return new VHHeader(LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_expandable_list_item_1, parent, false));

        throw new RuntimeException("Only header and item view types allowed");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHItem) {
            final PastResultsModel pastResultsModel = pastResultsModels.get(position - 1);
            ((VHItem) holder).resultTv.setText(pastResultsModel.getPercentagePredicted() + " %");
            ((VHItem) holder).conditionNameTV.setText(pastResultsModel.getMedicalCondition().getName());
        }
    }

    @Override
    public int getItemCount() {
        return pastResultsModels.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
    }

    private class VHItem extends RecyclerView.ViewHolder {
        TextView resultTv;
        TextView conditionNameTV;

        VHItem(View itemView) {
            super(itemView);
            resultTv = (TextView) itemView.findViewById(R.id.resultTV);
            conditionNameTV = (TextView) itemView.findViewById(R.id.conditionNameTV);
        }
    }

    private class VHHeader extends RecyclerView.ViewHolder {

        TextView clearResults;

        VHHeader(View itemView) {
            super(itemView);
            clearResults = (TextView) itemView.findViewById(android.R.id.text1);
            clearResults.setText("Clear Previous Results");
            clearResults.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fireBaseHelper.clearAllPreviousResults();
                    if (pastResultsModels != null) {
                        pastResultsModels.clear();
                        notifyDataSetChanged();
                    }
                }
            });

        }
    }
}
