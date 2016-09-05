package com.example.deepankur.diagnosisapplication.models;

import com.example.deepankur.diagnosisapplication.utils.AppKEYIDS;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by deepankur on 9/4/16.
 * <p>
 * Class to represent the possible medical conditions
 */

@JsonIgnoreProperties(ignoreUnknown = AppKEYIDS.IGNORE_UNRECOGNIZED_FIELD)
public class MedicalConditions {
    private int id;
    private String name;

    public MedicalConditions() {
    }

    private MedicalConditions(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    /**
     * @return all possible medical conditions
     * can be populated in the start itself by local data or after fetching the data from server
     */
    static ArrayList<MedicalConditions> list;

    public static ArrayList<MedicalConditions> getAllMedicalConditionsData() {
        if (list != null) return list;
        list = new ArrayList<>(conditionName.length);
        for (int i = 0; i < conditionName.length; i++) {
            MedicalConditions data = new MedicalConditions(i, conditionName[i]);
            list.add(data);
        }
        return list;
    }

    public static String getConditionByID(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (id == i) return list.get(i).getName();
        }
        return null;
    }

    public static String[] conditionName = new String[]{
            " Hemophilia (Hemophilia)",
            "A, Hepatitis (Hepatitis A)",
            "Todd’s Syndrome",
            "AAA (Abdominal Aortic Aneurysm)",
            "AAT (Alpha 1 Antitrypsin Deficiency)",
            " AATD (Alpha 1 Antitrypsin Deficiency)",
            " Abdominal Adhesions (Scar Tissue)",
            " Abdominal Aortic Aneurysm", " Abdominal Cramps (Heat Cramps)",
            " Abdominal Hernia ",
            " Abdominal Migraines in Children and Adults",
            " Abdominal Pain ",
            " Abdominoplasty (Tummy Tuck ( Abdominoplasty))",
            " Ablation Therapy for Arrhythmias",
            " Ablation, Endometrial ",
            " Ablation, Uterus (Endometrial Ablation)",
            "Abnormal Heart Rhythms (Heart Rhythm Disorders)",
            " Abnormal Liver Enzymes(Liver Blood Tests)"
    };

}
