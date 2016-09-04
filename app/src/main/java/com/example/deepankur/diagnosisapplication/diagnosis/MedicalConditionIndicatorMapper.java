package com.example.deepankur.diagnosisapplication.diagnosis;

import com.example.deepankur.diagnosisapplication.models.MedicalConditions;

import java.util.ArrayList;

/**
 * Created by deepankur on 9/5/16.
 * <p>
 * This class will return the desired input indicators for given medical condition
 */

public class MedicalConditionIndicatorMapper {

    /**
     * @param medicalConditionId unique id of a medical condition
     * @return all indicators needed to diagnose a particular condition, null if none is found
     * This function can be used to populate the data at run time
     */
    public static ArrayList<Indicators> getIndicatorsForACondition(int medicalConditionId) {
        MedicalConditions.getConditionByID(medicalConditionId);
        switch (medicalConditionId) {
            /**
             * hardcoded 2 as of now
             * in {@link MedicalConditions.conditionName}
             * Todd’s Syndrome is at position 2 currently
             */
            case 2:
                ArrayList<Indicators> indicatorsArrayList = new ArrayList<>();
                indicatorsArrayList.add(Indicators.AGE);
                indicatorsArrayList.add(Indicators.SEX);
                indicatorsArrayList.add(Indicators.MIGRAINE);
                indicatorsArrayList.add(Indicators.HALLUCINOGENS_USAGE);
                return indicatorsArrayList;
            default:
                return null;
        }
    }
}
