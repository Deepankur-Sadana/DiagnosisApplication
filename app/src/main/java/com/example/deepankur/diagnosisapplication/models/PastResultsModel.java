package com.example.deepankur.diagnosisapplication.models;

/**
 * Created by deepankur on 9/4/16.
 */

public class PastResultsModel {
    boolean migraine;
    int age;
    String sex;
    boolean usesHallucinogenic;
    int weight;
    MedicalConditions medicalCondition;
    int percentagePredicted;

    public int getPercentagePredicted() {
        return percentagePredicted;
    }
}
