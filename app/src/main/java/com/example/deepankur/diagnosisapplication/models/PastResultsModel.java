package com.example.deepankur.diagnosisapplication.models;

import com.example.deepankur.diagnosisapplication.utils.AppKEYIDS;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by deepankur on 9/4/16.
 */

@JsonIgnoreProperties(ignoreUnknown = AppKEYIDS.IGNORE_UNRECOGNIZED_FIELD)
public class PastResultsModel {
    private boolean migraine;
    private int age;
    private String sex;
    private boolean usesHallucinogenic;
    private int weight;
    private MedicalConditions medicalCondition;
    private int percentagePredicted;

    public PastResultsModel() {
    }

    public int getPercentagePredicted() {
        return percentagePredicted;
    }

    public boolean isMigraine() {
        return migraine;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public boolean isUsesHallucinogenic() {
        return usesHallucinogenic;
    }

    public int getWeight() {
        return weight;
    }

    public MedicalConditions getMedicalCondition() {
        return medicalCondition;
    }

    public PastResultsModel(boolean migraine, int age, String sex, boolean usesHallucinogenic, MedicalConditions medicalCondition, int percentagePredicted) {
        this.migraine = migraine;
        this.age = age;
        this.sex = sex;
        this.usesHallucinogenic = usesHallucinogenic;
        this.medicalCondition = medicalCondition;
        this.percentagePredicted = percentagePredicted;
    }
}
