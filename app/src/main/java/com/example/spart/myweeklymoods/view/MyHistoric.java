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

        //methods called
        displayHistory( linearLayout1, imageButton1, textView1, userPrefs.size() - 1 );
        displayHistory( linearLayout2, imageButton2, textView2, userPrefs.size() - 2 );
        displayHistory( linearLayout3, imageButton3, textView3, userPrefs.size() - 3 );
        displayHistory( linearLayout4, imageButton4, textView4, userPrefs.size() - 4 );
        displayHistory( linearLayout5, imageButton5, textView5, userPrefs.size() - 5 );
        displayHistory( linearLayout6, imageButton6, textView6, userPrefs.size() - 6 );
        displayHistory( linearLayout7, imageButton7, textView7, userPrefs.size() - 7 );
        imageButtonVisibility();
    }

    //method to change linearLayout's color and widht and shows comment if it exits
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void displayHistory(LinearLayout linearLayout, ImageButton imageButton, TextView textView, final int position) {
        if (userPrefs == null) {
            userPrefs = new ArrayList<>();
        }
        if ((!userPrefs.isEmpty()) && (userPrefs.size() > position) && (position >= 0)) {
            linearLayout.setBackground( getResources().getDrawable( userPrefs.get( position ).getBackground() ) );
            imageButton.setBackground( getResources().getDrawable( userPrefs.get( position ).getBackground() ) );
            textView.setBackground( getResources().getDrawable( userPrefs.get( position ).getBackground() ) );
            linearLayoutWidht( linearLayout, position );
            imageButtonListener( imageButton, position );
        }
    }
    //allows you to calculate the size of the linearLayout according to the mood
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void linearLayoutWidht(LinearLayout linearLayout, final int position) {

        Display display = getWindowManager().getDefaultDisplay();
        int width = 0;
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
    }
    //allows to read a comment or hide imageButton if there is no comment
    public void imageButtonListener(final ImageButton imageButton, final int position) {
        imageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = userPrefs.get( position ).getComment();
                if (userPrefs.get( position ).getComment() != null){
                    imageButton.setVisibility( View.VISIBLE );
                    Toast.makeText( getApplicationContext(), comment, Toast.LENGTH_LONG ).show();
                }
            }
        } );
        String comment = userPrefs.get( position ).getComment();
        //if the comment does not contain any text, hide the icon ,otherwise it will be displayed.
        if (comment != null) {

            if (userPrefs.get( position ).getComment().equals( "" ))
                imageButton.setVisibility( View.INVISIBLE );
        }
    }
    //hide imageButton according to userPrefs.size
    public void imageButtonVisibility(){
        switch (userPrefs.size()){
            case 0:
                imageButton1.setVisibility( View.INVISIBLE );
                imageButton2.setVisibility( View.INVISIBLE );
                imageButton3.setVisibility( View.INVISIBLE );
                imageButton4.setVisibility( View.INVISIBLE );
                imageButton5.setVisibility( View.INVISIBLE );
                imageButton6.setVisibility( View.INVISIBLE );
                imageButton7.setVisibility( View.INVISIBLE );
                break;
            case 1:
                imageButton2.setVisibility( View.INVISIBLE );
                imageButton3.setVisibility( View.INVISIBLE );
                imageButton4.setVisibility( View.INVISIBLE );
                imageButton5.setVisibility( View.INVISIBLE );
                imageButton6.setVisibility( View.INVISIBLE );
                imageButton7.setVisibility( View.INVISIBLE );
                break;
            case 2:
                imageButton3.setVisibility( View.INVISIBLE );
                imageButton4.setVisibility( View.INVISIBLE );
                imageButton5.setVisibility( View.INVISIBLE );
                imageButton6.setVisibility( View.INVISIBLE );
                imageButton7.setVisibility( View.INVISIBLE );
                break;
            case 3:
                imageButton4.setVisibility( View.INVISIBLE );
                imageButton5.setVisibility( View.INVISIBLE );
                imageButton6.setVisibility( View.INVISIBLE );
                imageButton7.setVisibility( View.INVISIBLE );
                break;
            case 4:
                imageButton5.setVisibility( View.INVISIBLE );
                imageButton6.setVisibility( View.INVISIBLE );
                imageButton7.setVisibility( View.INVISIBLE );
                break;
            case 5:
                imageButton6.setVisibility( View.INVISIBLE );
                imageButton7.setVisibility( View.INVISIBLE );
                break;
            case 6:imageButton7.setVisibility( View.INVISIBLE );
                break;
        }
    }
}



















