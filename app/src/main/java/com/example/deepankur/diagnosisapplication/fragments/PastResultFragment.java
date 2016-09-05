package com.example.deepankur.diagnosisapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepankur.diagnosisapplication.R;
import com.example.deepankur.diagnosisapplication.adapters.PreviousResultsAdapter;
import com.example.deepankur.diagnosisapplication.models.PastResultsModel;

import java.util.ArrayList;

/**
 * Created by deepankur on 9/5/16.
 *
 */

public class PastResultFragment extends BaseFragment {

    View rootView;
    RecyclerView pastResultRecycler;
    TextView noResultsTv;
    ArrayList<PastResultsModel> pastResultsModels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pastResultsModels = fireBaseHelper.getPastResults();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_past_results, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        //preventing bottom layers from consuming touch
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        pastResultRecycler = (RecyclerView) rootView.findViewById(R.id.pastResultRecycler);
        noResultsTv = (TextView) rootView.findViewById(R.id.noResultTv);

        if (pastResultsModels == null || pastResultsModels.size() == 0)
            noResultsTv.setVisibility(View.VISIBLE);
        else {
            noResultsTv.setVisibility(View.GONE);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            pastResultRecycler.setLayoutManager(layoutManager);
            pastResultRecycler.setAdapter(new PreviousResultsAdapter(pastResultsModels));
            pastResultRecycler.setHasFixedSize(true);
        }
    }
}
