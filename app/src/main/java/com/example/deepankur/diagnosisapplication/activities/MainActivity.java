package com.example.deepankur.diagnosisapplication.activities;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.deepankur.diagnosisapplication.R;
import com.example.deepankur.diagnosisapplication.fragments.SelectDisorderFragment;
import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    final String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadSelectDisorderFragment();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }



    public void loadSelectDisorderFragment() {
        SelectDisorderFragment fragment = new SelectDisorderFragment();

        Log.d(TAG, " opening SelectDisorderFragment");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_main, fragment, SelectDisorderFragment.class.getSimpleName());
//        fragmentTransaction.addToBackStack(SelectDisorderFragment.class.getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }
}
