package com.example.spart.myweeklymoods.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Prefs {

    private static Prefs instance;
    private static final String historicMoods = "historicMood";
    private static final String userhHistoric = "userhHistoric ";
    private static SharedPreferences prefs;

    //Class Prefs constructor
    private Prefs(Context context) {
        String PREFS_USER_MOOD = "UserMood";
        String PREFS_USER_HISTORIC_MOODS = "UserHistoricMoods";
        prefs = context.getSharedPreferences(PREFS_USER_MOOD, Activity.MODE_PRIVATE);
        prefs = context.getSharedPreferences(PREFS_USER_HISTORIC_MOODS, Activity.MODE_PRIVATE);

    }
    //Prefs.get is called in SaveMoodHelper to create a new instance of Prefs
    public static Prefs get(Context context) {
        if (instance == null)
            instance = new Prefs(context);
        return instance;
    }
    //MoodsToString changes ArrayList into json strings and save it
    public  void MoodsToString(ArrayList<Moods>userMoods) {
        //start writing (open the file)
        SharedPreferences.Editor editor = prefs.edit();
        //put the data
        Gson gson = new Gson();
        String json = gson.toJson(userMoods);
        editor.putString(historicMoods, json);
        //close the file
        editor.apply();
    }

    //stringToMoods recovers json strings and return there in ArrayList
    public  ArrayList<Moods> stringToMoods() {
        Gson gson = new Gson();
        String json = prefs.getString(historicMoods, "");

        ArrayList<Moods> userMoods;

        if (json.length() < 1) {
            userMoods = new ArrayList<>();
        } else {
            Type type = new TypeToken<ArrayList<Moods>>() {
            }.getType();
            userMoods = gson.fromJson(json, type);
        }

        //return the value that was stored under the key

        return userMoods;

    }
    //saveUserMoods changes ArrayList into json strings and save it,we use it for the history
    public  void saveUserMoods(ArrayList<Moods>historicMoods) {
        //start writing (open the file)
        SharedPreferences.Editor editor = prefs.edit();
        //put the data
        Gson gson = new Gson();
        String json = gson.toJson(historicMoods);
        editor.putString(userhHistoric, json);
        //close the file
        editor.apply();
    }
    //loadUserMoods recovers json strings and return there in ArrayList,we use it for the history
    public  ArrayList<Moods> loadUserMoods() {
        Gson gson = new Gson();
        String json = prefs.getString(userhHistoric, "");

        ArrayList<Moods> historicMoods;

        if (json.length() < 1) {
            historicMoods = new ArrayList<>();
        } else {
            Type type = new TypeToken<ArrayList<Moods>>() {
            }.getType();
            historicMoods = gson.fromJson(json, type);
        }

        //return the value that was stored under the key

        return historicMoods;
    }

}






