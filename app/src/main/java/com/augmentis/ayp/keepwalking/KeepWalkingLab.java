package com.augmentis.ayp.keepwalking;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.UUID;

/**
 * Created by Wilailux on 7/27/2016.
 */
public class KeepWalkingLab {

    List<KeepWalking> keepWalkingList;

    private static KeepWalkingLab instance;


    public static KeepWalkingLab getInstance(Context context) {
        if ( instance == null) {
            instance = new KeepWalkingLab();
        }
        return instance;
    }


    public KeepWalkingLab() {
        keepWalkingList = new ArrayList<>();
    }


    public void addKeepWalking(String title){

        KeepWalking keepWalking = new KeepWalking();
        keepWalking.setTitle(title);
        keepWalkingList.add(keepWalking);
    }

    public KeepWalking getKeepWalkingById(UUID uuid){
        for (KeepWalking keepWalking : keepWalkingList) {
            if (keepWalking.getId().equals(uuid)) {
                return keepWalking;
            }
        }
        return null;
    }


    public int getKeepWalkingPositionById (UUID uuid){
        int size = keepWalkingList.size();
        for (int i =0; i < size; i++) {
            if (keepWalkingList.get(i).getId().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }


    public List<KeepWalking> getKeepWalking() {
        return keepWalkingList;
    }


    public static void main (String[] args) {
        KeepWalkingLab crimeLab = KeepWalkingLab.getInstance(null);
        List<KeepWalking> keepWalkingList = crimeLab.getKeepWalking();
        int size = keepWalkingList.size();
        for (int i = 0; i < size; i++) {
            System.out.println(keepWalkingList.get(i));
        }

    }
}
