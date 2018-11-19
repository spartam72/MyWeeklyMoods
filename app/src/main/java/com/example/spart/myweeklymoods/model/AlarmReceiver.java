package com.example.spart.myweeklymoods.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.example.spart.myweeklymoods.R;

//receive an intent from the method alarmMidnight to call SaveMoodHelper
public class AlarmReceiver extends BroadcastReceiver {

    MediaPlayer mp;
    SaveMoodHelper mSaveMoodHelper;

    //when AlarmMidnight start, this method call method SaveMoodMidnight
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {

        mSaveMoodHelper = new SaveMoodHelper(context );
        mSaveMoodHelper.SaveMoodMidnight();
        mp=MediaPlayer.create(context, R.raw.windows_sms);
        mp.start();
        Toast.makeText(context, "Humeur disponible,consultez votre historique", Toast.LENGTH_LONG).show();




    }
}


