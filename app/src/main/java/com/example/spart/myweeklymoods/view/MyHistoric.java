package com.example.spart.myweeklymoods.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.spart.myweeklymoods.R;
import com.example.spart.myweeklymoods.model.Moods;
import com.example.spart.myweeklymoods.model.Prefs;
import java.util.ArrayList;

public class MyHistoric extends AppCompatActivity {

    //attributes statement
    private LinearLayout linearLayout7;
    private LinearLayout linearLayout6;
    private LinearLayout linearLayout5;
    private LinearLayout linearLayout4;
    private LinearLayout linearLayout3;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout1;
    private ImageButton imageButton7;
    private ImageButton imageButton6;
    private ImageButton imageButton5;
    private ImageButton imageButton4;
    private ImageButton imageButton3;
    private ImageButton imageButton2;
    private ImageButton imageButton1;
    TextView textView7;
    TextView textView6;
    TextView textView5;
    TextView textView4;
    TextView textView3;
    TextView textView2;
    TextView textView1;
    //recovers saved ArrayList to Prefs class
     ArrayList<Moods> userPrefs = Prefs.get( this ).loadUserMoods();


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_my_historic );

        //id statement
        linearLayout7 = findViewById( R.id.layout7 );
        linearLayout6 = findViewById( R.id.layout6 );
        linearLayout5 = findViewById( R.id.layout5 );
        linearLayout4 = findViewById( R.id.layout4 );
        linearLayout3 = findViewById( R.id.layout3 );
        linearLayout2 = findViewById( R.id.layout2 );
        linearLayout1 = findViewById( R.id.layout1 );
        imageButton7 = findViewById( R.id.showComment7 );
        imageButton6 = findViewById( R.id.showComment6 );
        imageButton5 = findViewById( R.id.showComment5 );
        imageButton4 = findViewById( R.id.showComment4 );
        imageButton3 = findViewById( R.id.showComment3 );
        imageButton2 = findViewById( R.id.showComment2 );
        imageButton1 = findViewById( R.id.showComment1 );
        textView1 = findViewById( R.id.textView1 );
        textView2 = findViewById( R.id.textView2 );
        textView3 = findViewById( R.id.textView3 );
        textView4 = findViewById( R.id.textView4 );
        textView5 = findViewById( R.id.textView5 );
        textView6 = findViewById( R.id.textView6 );
        textView7 = findViewById( R.id.textView7 );

        //method called
        adjustHistoricMood();

    }
    //method to change linearLayout and imageButton color and widht
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public  void adjustHistoricMood() {

        if (userPrefs == null) {
            userPrefs = new ArrayList<>();
        }
        //if historical don't exist,hide all ImageButtons, toast show "Vous n'avez pas encore d'historique"
        if (userPrefs.size() < 1 ) {

            imageButton7.setVisibility( View.INVISIBLE );
            imageButton6.setVisibility( View.INVISIBLE );
            imageButton5.setVisibility( View.INVISIBLE );
            imageButton4.setVisibility( View.INVISIBLE );
            imageButton3.setVisibility( View.INVISIBLE );
            imageButton2.setVisibility( View.INVISIBLE );
            imageButton1.setVisibility( View.INVISIBLE );
        }

        switch (userPrefs.size()) {
            case 1:
                displayBackgroundAndComment( linearLayout1, imageButton1, textView1, 0 );
                imageButton7.setVisibility( View.INVISIBLE );
                imageButton6.setVisibility( View.INVISIBLE );
                imageButton5.setVisibility( View.INVISIBLE );
                imageButton4.setVisibility( View.INVISIBLE );
                imageButton3.setVisibility( View.INVISIBLE );
                imageButton2.setVisibility( View.INVISIBLE );
                break;
            case 2:
                displayBackgroundAndComment( linearLayout1, imageButton1, textView1, 1 );
                displayBackgroundAndComment( linearLayout2, imageButton2, textView2, 0 );
                imageButton7.setVisibility( View.INVISIBLE );
                imageButton6.setVisibility( View.INVISIBLE );
                imageButton5.setVisibility( View.INVISIBLE );
                imageButton4.setVisibility( View.INVISIBLE );
                imageButton3.setVisibility( View.INVISIBLE );
                break;
            case 3:
                displayBackgroundAndComment( linearLayout1, imageButton1, textView1, 2 );
                displayBackgroundAndComment( linearLayout2, imageButton2, textView2, 1 );
                displayBackgroundAndComment( linearLayout3, imageButton3, textView3, 0 );
                imageButton7.setVisibility( View.INVISIBLE );
                imageButton6.setVisibility( View.INVISIBLE );
                imageButton5.setVisibility( View.INVISIBLE );
                imageButton4.setVisibility( View.INVISIBLE );
                break;
            case 4:
                displayBackgroundAndComment( linearLayout1, imageButton1, textView1, 3 );
                displayBackgroundAndComment( linearLayout2, imageButton2, textView2, 2 );
                displayBackgroundAndComment( linearLayout3, imageButton3, textView3, 1 );
                displayBackgroundAndComment( linearLayout4, imageButton4, textView4, 0 );
                imageButton7.setVisibility( View.INVISIBLE );
                imageButton6.setVisibility( View.INVISIBLE );
                imageButton5.setVisibility( View.INVISIBLE );
                break;
            case 5:
                displayBackgroundAndComment( linearLayout1, imageButton1, textView1, 4 );
                displayBackgroundAndComment( linearLayout2, imageButton2, textView2, 3 );
                displayBackgroundAndComment( linearLayout3, imageButton3, textView3, 2 );
                displayBackgroundAndComment( linearLayout4, imageButton4, textView4, 1 );
                displayBackgroundAndComment( linearLayout5, imageButton5, textView5, 0 );
                imageButton7.setVisibility( View.INVISIBLE );
                imageButton6.setVisibility( View.INVISIBLE );
                break;
            case 6:
                displayBackgroundAndComment( linearLayout1, imageButton1, textView1, 5 );
                displayBackgroundAndComment( linearLayout2, imageButton2, textView2, 4 );
                displayBackgroundAndComment( linearLayout3, imageButton3, textView3, 3 );
                displayBackgroundAndComment( linearLayout4, imageButton4, textView4, 2 );
                displayBackgroundAndComment( linearLayout5, imageButton5, textView5, 1 );
                displayBackgroundAndComment( linearLayout6, imageButton6, textView6, 0 );
                imageButton7.setVisibility( View.INVISIBLE );
                break;
            case 7:
                displayBackgroundAndComment( linearLayout1, imageButton1, textView1, 6 );
                displayBackgroundAndComment( linearLayout2, imageButton2, textView2, 5 );
                displayBackgroundAndComment( linearLayout3, imageButton3, textView3, 4 );
                displayBackgroundAndComment( linearLayout4, imageButton4, textView4, 3 );
                displayBackgroundAndComment( linearLayout5, imageButton5, textView5, 2 );
                displayBackgroundAndComment( linearLayout6, imageButton6, textView6, 1 );
                displayBackgroundAndComment( linearLayout7, imageButton7, textView7, 0 );
                break;
        }

    }

    //this method manages the color and size of the linearLayout but also what to do if the user has written a comment or not
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private  void displayBackgroundAndComment(LinearLayout linearLayout, ImageButton imageButton, TextView textView, final int position) {

        //if data is present, the color of the saved mood is displayed
        if ((!userPrefs.isEmpty()) && (userPrefs.size() > position)) {
            linearLayout.setBackground( getResources().getDrawable( userPrefs.get( position ).getBackground() ) );
            imageButton.setBackground( getResources().getDrawable( userPrefs.get( position ).getBackground() ) );
            textView.setBackground( getResources().getDrawable( userPrefs.get( position ).getBackground() ) );

            Display display = getWindowManager().getDefaultDisplay();
            int width = 0;
            //we adjust the size of the linearlayout according to the mood, the more joyful it is, the bigger it is
            if ((!userPrefs.isEmpty()) && (userPrefs.size() > position)) {
                switch (userPrefs.get( position ).getNumber()) {
                    case (4):
                        width = (display.getWidth());
                        break;
                    case 3:
                        width = (int) (display.getWidth() * 0.8);
                        break;
                    case 2:
                        width = (int) (display.getWidth() * 0.6);
                        break;
                    case 1:
                        width = (int) (display.getWidth() * 0.4);
                        break;
                    case 0:
                        width = (int) (display.getWidth() * 0.2);
                        break;
                }
                LinearLayout.LayoutParams parms = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                parms.width = width;
                linearLayout.setLayoutParams( parms );
                linearLayout.setVisibility( View.VISIBLE );
            }

            //if a comment has been recorded when choosing the mood, an icon is displayed that allows the user to reread what he/she has written, by clicking on it
            imageButton.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String comment = userPrefs.get( position ).getComment();
                    if (userPrefs.get( position ).getComment() != null) {
                        Toast.makeText( getApplicationContext(), comment, Toast.LENGTH_LONG ).show();

                    }
                }
            } );

            String comment = userPrefs.get( position ).getComment();
            //if the comment does not contain any text, hide the icon ,otherwise it will be displayed.
            if (comment != null) {

                if (userPrefs.get( position ).getComment().equals( "" ))
                    imageButton.setVisibility( View.INVISIBLE );
            }else{
                imageButton.setVisibility( View.INVISIBLE );

            }
        }

    }

}


















