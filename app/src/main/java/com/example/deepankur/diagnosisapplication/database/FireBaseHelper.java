package com.example.deepankur.diagnosisapplication.database;

import android.content.Context;

import com.example.deepankur.diagnosisapplication.models.PastResultsModel;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

/**
 * Created by deepankur on 9/4/16.
 * <p>
 * Singleton for all com.example.deepankur.diagnosisapplication.database operations
 */

public class FireBaseHelper implements FireBaseKEYIDS {

    private static FireBaseHelper sFireBaseHelper;

    public static FireBaseHelper getInstance(Context context) {
        if (sFireBaseHelper == null) sFireBaseHelper = new FireBaseHelper(context);
        return sFireBaseHelper;
    }

    private FireBaseHelper(Context context) {
//        getNewFireBase("ads", null).setValue("Hello");
        loadPastResults();
    }


    /**
     * @param anchor   the outer  FireBase node ie. socials,moments,users,media
     * @param children subsequent nodes to query upon; supply null If you need the anchor nodes.
     * instead of children
     * @return the resolved FireBase
     */
    private Firebase mFireBase;

    public Firebase getNewFireBase(String anchor, String[] children) {
        if (mFireBase == null)
            mFireBase = new Firebase(FireBaseKEYIDS.FireBaseUrl);
        if (anchor == null) throw new NullPointerException("anchor cannot be null");
        Firebase fireBase = this.mFireBase.child(anchor);
        if (children != null && children.length > 0) {
            for (int i = 0; i < children.length; i++) {
                fireBase = fireBase.child(children[i]);
            }
        }
        return fireBase;
    }

    public void writePastResultsToFireBase(PastResultsModel pastResultsModel) {
        getNewFireBase(ANCHOR_PAST_RESULTS, null).push().setValue(pastResultsModel);
    }


    private ArrayList<PastResultsModel> pastResultsModels = new ArrayList<>();

    private void loadPastResults() {
        getNewFireBase(ANCHOR_PAST_RESULTS, null).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PastResultsModel pastResultsModel = dataSnapshot.getValue(PastResultsModel.class);
                pastResultsModels.add(pastResultsModel);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public ArrayList<PastResultsModel> getPastResults() {
        return pastResultsModels;
    }
}
