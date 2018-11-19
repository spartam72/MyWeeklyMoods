package com.example.spart.myweeklymoods.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.spart.myweeklymoods.R;
import com.example.spart.myweeklymoods.model.AlarmReceiver;
import com.example.spart.myweeklymoods.model.Moods;
import com.example.spart.myweeklymoods.model.Prefs;
import com.example.spart.myweeklymoods.model.SaveMoodHelper;
import com.example.spart.myweeklymoods.view.MyHistoric;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer vlc;
    private ImageButton note;
    private ImageButton historic;
    private ImageView happy_mood;
    private RelativeLayout myView;
    private int indice;
    String comment;
    Date myDate = new Date();
    SaveMoodHelper mSaveMoodHelper;
    private Prefs prefs;
    private static final String TAG = "activity_main";


    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        prefs = Prefs.get( getBaseContext() );
        mSaveMoodHelper = new SaveMoodHelper(this);
        createMoodsTab();
        AlarmMidnight();



        if (prefs.stringToMoods().size() == 0) {

            indice = 1;
        } else {
            int numMood = prefs.stringToMoods().get( prefs.stringToMoods().size() - 1 ).getNumber();
            int i = 0;
            while (numMood != moodTab.get( i ).getNumber()) {

                i++;
            }
            indice = i;

        }
        myDate = mSaveMoodHelper.getCurrentDate();

        happy_mood = findViewById( R.id.happy_mood );
        myView = findViewById( R.id.myView );
        note = findViewById( R.id.note );
        historic = findViewById( R.id.historic );

        displayDifferentsMoods();

        note.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomPopup customPopup = new CustomPopup( MainActivity.this );
                customPopup.build();
            }
        } );

        historic.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this, MyHistoric.class );
                //if historical don't exist, toast show "You have no history"
                if (Prefs.get( getBaseContext() ).stringToMoods().size() == 0) {
                    Toast.makeText( getBaseContext(), "Vous n'avez pas encore d'historique", Toast.LENGTH_SHORT ).show();
                }
                startActivity( intent );

            }
        } );

        myView.setOnTouchListener( touchListener );

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void AlarmMidnight() {

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Date dat = new Date();
        Calendar calendar = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();
        cal_now.setTime(dat);

        calendar.setTime(dat);
        calendar.set(Calendar.HOUR_OF_DAY,16);
        calendar.set(Calendar.MINUTE,1);
        calendar.set(Calendar.SECOND, 0);

        if(calendar.before(cal_now)){
            calendar.add(Calendar.DATE,1);
        }

        Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);

        if (Build.VERSION.SDK_INT > 19) {
            if (manager != null) {
                manager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 300, pendingIntent);
            }
            Log.i(TAG, "startAlarm: 1 ");
        }

        else {
            if (manager != null) {
                manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 300, pendingIntent);
            }
            Log.i(TAG, "startAlarm: 2");
        }
    }

    public class CustomPopup extends Dialog {

        private EditText writeComment;
        private Button annuler;
        private Button valider;
        private String comment;

        private CustomPopup(final Activity activity) {
            super( activity, R.style.Theme_AppCompat_DayNight_Dialog );
            setContentView( R.layout.my_popup );

            writeComment = findViewById( R.id.writeComment );
            annuler = findViewById( R.id.annuler );
            valider = findViewById( R.id.valider );

            annuler.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            } );

            valider.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Prefs.get(MainActivity.this);
                    comment = writeComment.getText().toString();
                    moodTab.get(indice).setComment(comment);
                    Moods test = moodTab.get(indice);
                    mSaveMoodHelper.SaveCurrentMood(test);
                    Toast.makeText( (activity), "Enregistré ! ",Toast.LENGTH_SHORT).show();

                    dismiss();
                }
            } );
        }

        private void build(){
            show();

        }
    }
    private ArrayList<Moods> moodTab;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void createMoodsTab() {

        Moods mood1 = new Moods( R.drawable.very_happy_mood, R.color.colorYellow,4,comment,myDate,R.raw.riddlesms );
        Moods mood2 = new Moods( R.drawable.happy_mood, R.color.colorGreen, 3,comment,myDate,R.raw.blackberrysms);
        Moods mood3 = new Moods( R.drawable.normal_mood, R.color.colorBlue, 2,comment,myDate,R.raw.google );
        Moods mood4 = new Moods( R.drawable.sad_mood, R.color.colorGrey, 1,comment,myDate,R.raw.iphone );
        Moods mood5 = new Moods( R.drawable.very_sad_mood, R.color.colorRed, 0,comment,myDate,R.raw.sad_sound );

        moodTab = new ArrayList<>();
        moodTab.add( mood1 );
        moodTab.add( mood2 );
        moodTab.add( mood3 );
        moodTab.add( mood4 );
        moodTab.add( mood5 );
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void displayDifferentsMoods (){
        if ((indice >= 0) && (indice < moodTab.size())) {
            happy_mood.setImageDrawable( getResources().getDrawable( moodTab.get(indice).getImage() ) );
            myView.setBackground( getResources().getDrawable( moodTab.get(indice).getBackground() ) );
            historic.setBackground( getResources().getDrawable( moodTab.get(indice).getBackground() ) );
            note.setBackground( getResources().getDrawable( moodTab.get(indice).getBackground() ) );
            playSound( moodTab.get(indice).getSound() );
        }
    }
    public void playSound(int resId){

        if(vlc != null) {
            vlc.stop();
            vlc.release();
        }
        vlc = MediaPlayer.create(this, resId);
        vlc.start();
    }
    public  Date yesterday(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.add(Calendar.DATE, -1);
        Date hier = calendar.getTime();
        return hier;
    }
    @Override
    protected void onRestart() { super.onRestart(); }

    @Override
    protected void onStart() { super.onStart(); }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() { super.onPause(); }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onStop() { super.onStop(); }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    // get the gesture detector
    private GestureDetector mDetector = new GestureDetector( getBaseContext(), new MyGestureListener() );

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        //method called when a touch event is dispatched to a view.
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            v.performClick();
            return mDetector.onTouchEvent( event );

        }
    };

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {


            return true;
        }
        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public boolean onFling(MotionEvent start, MotionEvent finish, float xVelocity, float yVelocity) {
            if (start.getRawY() < finish.getRawY()) {

                if (indice < moodTab.size() - 1) {
                    indice++;
                } else {
                    indice = moodTab.size() - 1;
                }

            } else {
                if (indice > 0) {
                    indice--;
                } else {
                    indice = 0;
                }
            }
            displayDifferentsMoods();
            return true;

        }


    }
}






