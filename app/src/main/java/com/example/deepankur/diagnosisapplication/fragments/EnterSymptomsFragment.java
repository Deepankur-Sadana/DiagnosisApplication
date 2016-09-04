package com.example.deepankur.diagnosisapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepankur.diagnosisapplication.R;
import com.example.deepankur.diagnosisapplication.diagnosis.Indicators;
import com.example.deepankur.diagnosisapplication.diagnosis.MedicalConditionIndicatorMapper;
import com.example.deepankur.diagnosisapplication.models.MedicalConditions;

import java.util.ArrayList;

/**
 * Created by deepankur on 9/5/16.
 */

public class EnterSymptomsFragment extends BaseFragment {
    private View rootView;
    private MedicalConditions medicalCondition;
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Indicators> indicatorsForACondition;

    public void setMedicalCondition(MedicalConditions medicalCondition) {
        this.medicalCondition = medicalCondition;
     indicatorsForACondition = MedicalConditionIndicatorMapper.getIndicatorsForACondition(medicalCondition.getId());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_enter_symptoms, container, false);

        return rootView;
    }

    public ArrayList<Indicators> getIndicatorsForACondition() {
        return indicatorsForACondition;
    }
}
