package com.example.spart.myweeklymoods.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;


//receive an intent from the method alarmMidnight to call SaveMoodHelper
public class AlarmReceiver extends BroadcastReceiver {


    SaveMoodHelper mSaveMoodHelper;

    //when AlarmMidnight start, this method call method SaveMoodMidnight
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {

        mSaveMoodHelper = new SaveMoodHelper(context );
        mSaveMoodHelper.SaveMoodMidnight();
        Toast.makeText(context, "Humeur disponible,consultez votre historique", Toast.LENGTH_LONG).show();




    }
}


