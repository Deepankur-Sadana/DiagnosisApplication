package com.example.deepankur.diagnosisapplication.fragments;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deepankur.diagnosisapplication.R;
import com.example.deepankur.diagnosisapplication.utils.AppKEYIDS;

/**
 * Created by deepankur on 9/4/16.
 *
 * Base class for all FragmentFragment
 */

public class BaseFragment extends Fragment implements AppKEYIDS{
    protected Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private void showPopup(final  int predictedPercentage) {
        final Dialog popup = new Dialog(context, android.R.style.Theme_Translucent);
        popup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        popup.setContentView(R.layout.dialog_predicted_result);
        popup.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

    }

}
