package com.example.deepankur.diagnosisapplication.diagnosis;

import com.example.deepankur.diagnosisapplication.models.MedicalConditions;
import com.example.deepankur.diagnosisapplication.utils.AppKEYIDS;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by deepankur on 9/5/16.
 *
 * Class that can be used for client side diagnoses by fetching all the
 * {@link Indicators} and its data types from server
 */
public class OfflineDiagnosis implements AppKEYIDS {


    private MedicalConditions medicalCondition;
    private LinkedHashMap<Indicators, Object> indicatorsObjectLinkedHashMap;

    public OfflineDiagnosis(MedicalConditions medicalCondition, LinkedHashMap<Indicators, Object> indicatorsObjectLinkedHashMap) {
        this.medicalCondition = medicalCondition;
        this.indicatorsObjectLinkedHashMap = indicatorsObjectLinkedHashMap;
        if (!validateInputs(indicatorsObjectLinkedHashMap)) {
            throw new RuntimeException("incorrect values provided for diagnosis");
        }
//        try {
//            predictResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private boolean validateInputs(LinkedHashMap<Indicators, Object> indicatorsObjectLinkedHashMap) {

        for (Map.Entry<Indicators, Object> entry : indicatorsObjectLinkedHashMap.entrySet()) {
            if (!validateDataTypeForIndicator(entry.getKey(), entry.getValue()))
                return false;
        }
        return true;
    }

    /**
     * @param indicator the key of indicators set
     * @param object    the value corresponding to key
     * @return true if the correct data tpe if there for an indicator, false otherwise
     */
    private boolean validateDataTypeForIndicator(Indicators indicator, Object object) {
        switch (indicator) {
            case AGE:
                return object instanceof Integer;
            case SEX:
                return object instanceof String;
            case MARITAL_STATUS:
                return object instanceof String;
            case MIGRAINE:
                return object instanceof Boolean;
            case HALLUCINOGENS_USAGE:
                return object instanceof Boolean;
            default:
                throw new RuntimeException("could not find the indicators itself");
        }
    }

    public int predictResult() throws Exception {
        switch (medicalCondition.getId()) {
            case 2:
                return getToddsSyndromeProbability(indicatorsObjectLinkedHashMap);
            default:
                throw new NullPointerException();
        }
    }

    private int getToddsSyndromeProbability(LinkedHashMap<Indicators, Object> indicatorsObjectLinkedHashMap) {
        int positiveCounts = 0;
        for (Map.Entry<Indicators, Object> entry : indicatorsObjectLinkedHashMap.entrySet()) {
            switch (entry.getKey()) {
                case AGE:
                    final Integer age = (Integer) entry.getValue();
                    if (age <= 15) positiveCounts++;
                    break;
                case MIGRAINE:
                    boolean hasMigrane = (boolean) entry.getValue();
                    if (hasMigrane) positiveCounts++;
                    break;
                case SEX:
                    String sex = (String) entry.getValue();
                    if (sex.equals(MALE)) positiveCounts++;
                    break;
                case HALLUCINOGENS_USAGE:
                    boolean usesHalucenogen = (boolean) entry.getValue();
                    if (usesHalucenogen) positiveCounts++;
                    break;
            }
        }
        if (positiveCounts == 4)
            return 100;
        else if (positiveCounts == 3)
            return 75;
        else if (positiveCounts == 2)
            return 50;
        else if (positiveCounts == 1)
            return 25;
        else return 0;

    }
}
