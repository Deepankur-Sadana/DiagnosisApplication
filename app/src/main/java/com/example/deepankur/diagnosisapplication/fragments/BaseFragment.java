package com.example.deepankur.diagnosisapplication.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.deepankur.diagnosisapplication.R;
import com.example.deepankur.diagnosisapplication.database.FireBaseHelper;
import com.example.deepankur.diagnosisapplication.utils.AppKEYIDS;

import at.grabner.circleprogress.CircleProgressView;

/**
 * Created by deepankur on 9/4/16.
 * <p>
 * Base class for all FragmentFragment
 */

public class BaseFragment extends Fragment implements AppKEYIDS {
    protected Context context;
    protected FireBaseHelper fireBaseHelper;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fireBaseHelper = FireBaseHelper.getInstance(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void showShortToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected void showPopup(final int predictedPercentage) {
        final Dialog popup = new Dialog(context, android.R.style.Theme_Translucent);
        popup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        popup.setContentView(R.layout.dialog_predicted_result);
        popup.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        ((CircleProgressView) popup.findViewById(R.id.circleView)).setValueAnimated(predictedPercentage, 1500);
        popup.findViewById(R.id.circleView).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        popup.findViewById(R.id.tintView).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP)
                    popup.dismiss();
                return true;
            }
        });
        popup.show();

    }

}
