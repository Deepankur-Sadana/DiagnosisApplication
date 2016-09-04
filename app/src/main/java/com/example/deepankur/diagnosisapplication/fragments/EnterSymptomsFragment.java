package com.example.deepankur.diagnosisapplication.fragments;

import android.os.Bundle;
import android.util.Log;

import com.example.deepankur.diagnosisapplication.models.MedicalConditionsData;

/**
 * Created by deepankur on 9/5/16.
 */

public class EnterSymptomsFragment extends BaseFragment {
    MedicalConditionsData medicalCondition;
    final String TAG = getClass().getSimpleName();
    public void setMedicalCondition(MedicalConditionsData medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }
}
