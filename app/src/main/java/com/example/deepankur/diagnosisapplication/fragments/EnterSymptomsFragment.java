package com.example.deepankur.diagnosisapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deepankur.diagnosisapplication.R;
import com.example.deepankur.diagnosisapplication.diagnosis.Indicators;
import com.example.deepankur.diagnosisapplication.diagnosis.MedicalConditionIndicatorMapper;
import com.example.deepankur.diagnosisapplication.diagnosis.OfflineDiagnosis;
import com.example.deepankur.diagnosisapplication.models.MedicalConditions;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by deepankur on 9/5/16.
 */

public class EnterSymptomsFragment extends BaseFragment {
    private View rootView;
    private MedicalConditions medicalCondition;
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Indicators> indicatorsForACondition;
    private LayoutInflater layoutInflater;
    private TextView getResultsTv;
    LinearLayout indicatorContainer;

    public void setMedicalCondition(MedicalConditions medicalCondition) {
        this.medicalCondition = medicalCondition;
        indicatorsForACondition = MedicalConditionIndicatorMapper.getIndicatorsForACondition(medicalCondition.getId());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        indicatorViewsForm = new ArrayList<>();
        indicatorsObjectLinkedHashMap = new LinkedHashMap<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_enter_symptoms, container, false);
        this.layoutInflater = inflater;
        initView();
        return rootView;
    }

    void initView() {
        indicatorContainer = (LinearLayout) rootView.findViewById(R.id.indicatorContainerLL);
        for (Indicators indicators : indicatorsForACondition) {
            indicatorContainer.addView(getViewForAnIndicator(indicators));
        }
        getResultsTv = (TextView) rootView.findViewById(R.id.getReultsTV);
        getResultsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indicatorsObjectLinkedHashMap.clear();
                if (validateForm())
                    predictResults();
            }
        });
        //to prevent touch events being passed to bottom layer
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void predictResults() {
        OfflineDiagnosis diagnosis = new OfflineDiagnosis(medicalCondition, indicatorsObjectLinkedHashMap);
        try {
            final int probability = diagnosis.predictResult();
            showShortToastMessage("you have " + probability);
        } catch (Exception e) {
            showShortToastMessage("Some error occured");
            e.printStackTrace();
        }
    }

    private LinkedHashMap<Indicators, Object> indicatorsObjectLinkedHashMap;


    private boolean validateForm() {
        for (View view : indicatorViewsForm) {
            if (view instanceof SwitchCompat) {//switch will always hold a default value false hence form is not empty
                fillKeyValuesInAForm(view);
            } else if (view instanceof EditText) {
                if (((EditText) view).getText().toString().trim().length() == 0) {
                    showShortToastMessage("Please fill " + view.getTag());
                    return false;
                } else {
                    fillKeyValuesInAForm(view);
                }
            }
        }
        return true;
    }

    void fillKeyValuesInAForm(View view) {
        Indicators indicators = (Indicators) view.getTag();
        switch (indicators) {
            case AGE:
                indicatorsObjectLinkedHashMap.put((Indicators) view.getTag(), Integer.parseInt(((EditText) view).getText().toString()));
                break;
            case SEX:
                indicatorsObjectLinkedHashMap.put((Indicators) view.getTag(), ((SwitchCompat) view).isChecked() ? MALE : FEMALE);
                break;
            case HALLUCINOGENS_USAGE:
                indicatorsObjectLinkedHashMap.put(((Indicators) view.getTag()), ((SwitchCompat) view).isChecked());
                break;
            case MIGRAINE:
                indicatorsObjectLinkedHashMap.put(((Indicators) view.getTag()), ((SwitchCompat) view).isChecked());
                break;
        }

    }

    private ArrayList<View> indicatorViewsForm;
    TextView sexTv;

    private View getViewForAnIndicator(Indicators indicator) {
        View itemView;

        switch (indicator) {
            case AGE:
                itemView = layoutInflater.inflate(R.layout.card_input_text, null);
                itemView.findViewById(R.id.forminputET).setTag(indicator);
                indicatorViewsForm.add(itemView.findViewById(R.id.forminputET));
                break;
            case MIGRAINE:
                itemView = layoutInflater.inflate(R.layout.card_input_toggle, null);
                itemView.findViewById(R.id.switch_indicator).setTag(indicator);
                indicatorViewsForm.add(itemView.findViewById(R.id.switch_indicator));
                break;
            case SEX:
                itemView = layoutInflater.inflate(R.layout.card_input_toggle, null);
                itemView.findViewById(R.id.switch_indicator).setTag(indicator);
                ((SwitchCompat) itemView.findViewById(R.id.switch_indicator)).setOnCheckedChangeListener(checkListener);
                indicatorViewsForm.add(itemView.findViewById(R.id.switch_indicator));
                sexTv = (TextView) itemView.findViewById(R.id.indicatorTV);
                break;
            case HALLUCINOGENS_USAGE:
                itemView = layoutInflater.inflate(R.layout.card_input_toggle, null);
                itemView.findViewById(R.id.switch_indicator).setTag(indicator);
                indicatorViewsForm.add(itemView.findViewById(R.id.switch_indicator));
                break;
            default:
                Log.d(TAG, "no item view found for " + indicator);
                itemView = null;

        }
        if (itemView != null)
            ((TextView) itemView.findViewById(R.id.indicatorTV)).setText(getTextForIndicator(indicator));

        return itemView;
    }

    private CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            notifySexChanged(isChecked);
        }
    };

    void notifySexChanged(boolean isChecked) {
        sexTv.setText(isChecked ? "Sex: " + MALE : "Sex: " + FEMALE);
    }

    String getTextForIndicator(Indicators indicators) {
        switch (indicators) {
            case AGE:
                return "Age";
            case SEX:
                return "Enter Sex";
            case HALLUCINOGENS_USAGE:
                return "Do you use Hallucinogens";
            case MIGRAINE:
                return "Are you suffering from migraine";
            default:
                return "";

        }
    }
}
