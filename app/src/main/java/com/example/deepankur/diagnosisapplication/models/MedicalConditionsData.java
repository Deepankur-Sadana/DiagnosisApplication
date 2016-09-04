package com.example.deepankur.diagnosisapplication.models;

import java.util.ArrayList;

/**
 * Created by deepankur on 9/4/16.
 * <p>
 * Class to represent the possible medical conditions
 */

public class MedicalConditionsData {
    private int id;
    private String name;

    private MedicalConditionsData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * @return all possible medical conditions
     * can be populated in the start itself by local data or after fetching the data from server
     */
   public static ArrayList<MedicalConditionsData> getAllMedicalConditionsData() {
        ArrayList<MedicalConditionsData> list = new ArrayList<>(conditionName.length);
        for (int i = 0; i < conditionName.length; i++) {
            MedicalConditionsData data = new MedicalConditionsData(i, conditionName[i]);
            list.add(data);
        }
        return list;
    }

    private static String[] conditionName = new String[]{
            " Hemophilia (Hemophilia)",
            "A, Hepatitis (Hepatitis A)",
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
