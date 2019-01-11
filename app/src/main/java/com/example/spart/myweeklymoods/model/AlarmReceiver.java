package com.example.spart.myweeklymoods.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


//receive an intent from the method alarmMidnight to call SaveMoodHelper
public class AlarmReceiver extends BroadcastReceiver {


    SaveMoodHelper mSaveMoodHelper;

    //when AlarmMidnight start, this method call method SaveMoodMidnight
    @Override
    public void onReceive(Context context, Intent intent) {

        mSaveMoodHelper = new SaveMoodHelper(context );
        mSaveMoodHelper.SaveMoodMidnight();
        Toast.makeText(context, "Humeur disponible,consultez votre historique", Toast.LENGTH_LONG).show();




    }
}


