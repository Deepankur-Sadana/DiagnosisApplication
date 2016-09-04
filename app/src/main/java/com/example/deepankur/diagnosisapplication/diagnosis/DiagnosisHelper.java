package com.example.deepankur.diagnosisapplication.diagnosis;

/**
 * Created by deepankur on 9/5/16.
 */
public class DiagnosisHelper {
    private static DiagnosisHelper ourInstance = new DiagnosisHelper();

    public static DiagnosisHelper getInstance() {
        return ourInstance;
    }

    private DiagnosisHelper() {
    }
}
