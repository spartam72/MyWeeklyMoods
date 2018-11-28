package com.example.spart.myweeklymoods.model;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.spart.myweeklymoods.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SaveMoodHelper {
    private Context context;

    //SaveMoodHelper builder
    public SaveMoodHelper(Context context ) {

        this.context = context;
    }

    //to recover the current date
    public Date getCurrentDate() {
        Date date;
        DateFormat outputFormatter = new SimpleDateFormat( "MM/dd/yyyy", Locale.getDefault() );
        date = Calendar.getInstance().getTime();
        date.toString();
        return date;
    }

    //this method is called to save current mood
    public void SaveCurrentMood(Moods currentMood) {
        currentMood.setDate( getCurrentDate() );
        Prefs prefs = Prefs.get( context );

        ArrayList<Moods> prefsMoods = prefs.stringToMoods();

        if (prefsMoods == null) {
            prefsMoods = new ArrayList<>();
        }
        if (prefsMoods.size() > 0 && (prefsMoods.get( prefsMoods.size() - 1 ).getDate()).equals( getCurrentDate() )) {
            prefsMoods.remove( prefsMoods.size() - 1 );
        }
        prefsMoods.add( currentMood );

        if (prefsMoods.size() > 8) {
            prefsMoods.remove( 0 );
        }

        prefs.MoodsToString( prefsMoods );
    }

    //method called by AlarmMidnight to save current mood
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void SaveMoodMidnight() {

        Prefs prefs = Prefs.get(context);

        //I create two Arraylists, one that contains the last mood of the day and the other that is always empty
        ArrayList<Moods> userLastChoice = prefs.loadUserMoods();
        ArrayList<Moods> emptyArray = new ArrayList<>( 0 );


        if (userLastChoice.size() == 0) {
            userLastChoice = new ArrayList<>();
        }
        //if the user has not chosen a mood, the default mood is automatically saved
        if (prefs.stringToMoods().size() == 0) {
            Moods defautMood = new Moods(R.drawable.happy_mood,R.color.colorGreen,3,"",getCurrentDate(),R.raw.blackberrysms);
            userLastChoice.add( defautMood );
        }else {
            //otherwise we display the last one of the day
            Moods lastMoodOfTheDay = prefs.stringToMoods().get( prefs.stringToMoods().size() - 1);
            userLastChoice.add( lastMoodOfTheDay );
        }
        //if the size exceeds seven, the first one is deleted
        if(userLastChoice.size() >7){
            userLastChoice.remove( 0 );
        }
        prefs.saveUserMoods( userLastChoice );
        //here we empty MoodToString, which allows to display the default mood  at midnight
        prefs.MoodsToString( emptyArray );
    }
}
















