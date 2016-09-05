package com.example.deepankur.diagnosisapplication.fragments;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.deepankur.diagnosisapplication.R;
import com.example.deepankur.diagnosisapplication.utils.Utils;
import com.example.deepankur.diagnosisapplication.database.FireBaseHelper;
import com.example.deepankur.diagnosisapplication.diagnosis.MedicalConditionIndicatorMapper;
import com.example.deepankur.diagnosisapplication.explosion.ExplosionField;
import com.example.deepankur.diagnosisapplication.models.MedicalConditions;

import java.util.ArrayList;

/**
 * Created by deepankur on 9/4/16.
 */

public class SelectDisorderFragment extends BaseFragment {

    private LinearLayout suggestionsLayout;
    private EditText conditionToDiagnoseInputEt;
    private final int PROCESS_QUERY_THRESHOLD = 800;//we give user 800ms to finish typing and only then do the  processing
    private Handler queryHandler = new Handler();
    private final String TAG = getClass().getSimpleName();
    private Runnable queryRunnable = new Runnable() {
        @Override
        public void run() {
            if (previousQuery.equals(conditionToDiagnoseInputEt.getText().toString().trim().toLowerCase())) {
                queryHandler.removeCallbacksAndMessages(null);
                Log.d(TAG, "starting search @ " + System.currentTimeMillis());
                startPartialSearch(previousQuery);
                Log.d(TAG, "finished search @ " + System.currentTimeMillis());

            }
        }
    };

    String previousQuery = "";
    View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filteredData = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_select_condition, container, false);
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        scrollView = (HorizontalScrollView) rootView.findViewById(R.id.scrollView);
        suggestionsLayout = (LinearLayout) rootView.findViewById(R.id.suggestionLL);
        conditionToDiagnoseInputEt = (EditText) rootView.findViewById(R.id.disorderInputET);
        conditionToDiagnoseInputEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                previousQuery = s.toString().trim().toLowerCase();
                queryHandler.postDelayed(queryRunnable, PROCESS_QUERY_THRESHOLD);
                if (s.toString().trim().length() > 0) {
                    if (scrollView.getTranslationY() < 0)
                        slideSuggestionsLayout(0);
                } else {
                    slideSuggestionsLayout(-1000);
                }
            }
        });

    }

    ArrayList<MedicalConditions> filteredData;

    private void startPartialSearch(String query) {
        int match = 0;
        Long t1 = System.currentTimeMillis();
        final ArrayList<MedicalConditions> allMedicalConditionsData = MedicalConditions.getAllMedicalConditionsData();
        boolean broken = false;//we break the search at 10 elements per query
        filteredData.clear();
        for (MedicalConditions data : allMedicalConditionsData) {
            if (data.getName().toLowerCase().contains(query)) {
                filteredData.add(data);
                match++;
                if (match == 10) {
                    broken = true;
                    break;
                }
            }
        }
        if (!broken)
            Log.d(TAG, " iterated through " + allMedicalConditionsData.size() + " elements in " + (System.currentTimeMillis() - t1) + " millisecs");

        suggestionsLayout.removeAllViews();

        for (int i = 0; i < filteredData.size(); i++) {
            MedicalConditions data = filteredData.get(i);
            TextView textView = new TextView(context);
            suggestionsLayout.addView(textView);
            suggestionsLayout.setVisibility(View.VISIBLE);
            textView.setBackgroundResource(R.drawable.suggestions_background);
            textView.setTextColor(Color.parseColor("#FFFFFF"));
            ((LinearLayout.LayoutParams) textView.getLayoutParams()).leftMargin = Utils.convertDpToPixels(context, 8);
            textView.setTag(data);
            if (data != null) {
                textView.setText(data.getName());
            } else textView.setText("Unable to find?");
            textView.setOnClickListener(suggestionClickedListener);
        }
    }

    InputMethodManager imm;

    private View.OnClickListener suggestionClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            imm.hideSoftInputFromWindow(conditionToDiagnoseInputEt.getApplicationWindowToken(), 0);
            MedicalConditions data = (MedicalConditions) v.getTag();
            if (data != null) {
                addViewsOnConditionSelected(data);
            }
        }
    };

    private void addViewsOnConditionSelected(final MedicalConditions data) {
        checkAndRemovePreviousViews();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        layoutParams.addRule(RelativeLayout.BELOW,R.id.disorderInputET);
        layoutParams.addRule(RelativeLayout.BELOW, R.id.disorderInputET);
        layoutParams.topMargin = Utils.convertDpToPixels(context, 24);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        ImageView clear = new ImageView(context);
        clear.setImageResource(android.R.drawable.ic_notification_clear_all);
        clear.setBackgroundColor(Color.parseColor("#80FF0000"));
        clear.setOnClickListener(clearSelectedConditionListener);
        clear.setTag(linearLayout);

        ImageView done = new ImageView(context);
        done.setImageResource(android.R.drawable.ic_media_next);
        done.setBackgroundColor(Color.parseColor("#800000FF"));
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MedicalConditionIndicatorMapper.getIndicatorsForACondition(data.getId()) != null)
                    loadEnterSymptomsFragment(data);
                else showShortToastMessage("No results Found for " + data.getName());
            }
        });

        TextView textView = new TextView(context);
        textView.setText(data.getName());
        textView.setGravity(Gravity.CENTER);
        textView.setMaxWidth(Utils.convertDpToPixels(context, 200));

        linearLayout.addView(clear);
        linearLayout.addView(textView);
        linearLayout.addView(done);

        ((RelativeLayout) rootView).addView(linearLayout, layoutParams);

    }

    /**
     * In order to avoid multiple views being added
     */
    private void checkAndRemovePreviousViews() {
        RelativeLayout root = (RelativeLayout) rootView;
        for (int i = 0; i < root.getChildCount(); i++) {
            if (root.getChildAt(i) instanceof LinearLayout) {// we are sure that only one linear layout
                // is there in the root view as of now ,so if can safely do this iteration
                root.removeViewAt(i);
                break;
            }
        }
    }

    private View.OnClickListener clearSelectedConditionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            performExplodeAnimation((View) v.getTag());
        }
    };

    private void performExplodeAnimation(View explodingView) {
        final View viewToDelete = explodingView;
        ExplosionField mExplosionField;
        mExplosionField = ExplosionField.attach2Window(getActivity());
        mExplosionField.explode(viewToDelete);

        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                ((RelativeLayout) rootView).removeView(viewToDelete);
            }
        }, 2000);//// TODO: 9/5/16 add  listeners instead of hardcoding duration
    }

    HorizontalScrollView scrollView;

    private void slideSuggestionsLayout(float target) {
        ObjectAnimator.ofFloat(scrollView, "translationY", scrollView.getTranslationY(), target).setDuration(200).start();
    }

    private void loadEnterSymptomsFragment(MedicalConditions data) {
        EnterSymptomsFragment enterSymptomsFragment = new EnterSymptomsFragment();
        enterSymptomsFragment.setMedicalCondition(data);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main, enterSymptomsFragment, EnterSymptomsFragment.class.getSimpleName());
        fragmentTransaction.addToBackStack(EnterSymptomsFragment.class.getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
        FireBaseHelper.getInstance(context);
    }
}
