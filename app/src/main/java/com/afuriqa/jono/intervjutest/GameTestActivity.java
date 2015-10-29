package com.afuriqa.jono.intervjutest;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import android.view.View.OnClickListener;


public class GameTestActivity extends Activity {

    //The "x" and "y" position of the "Show Button" on screen.
    Point pointCreateAccountButton;
    Point pointTestBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_test);

        Button btn_show = (Button) findViewById(R.id.createAccountButton);
        btn_show.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                onWindowFocusChanged(true);
                //Open popup window
                if (pointCreateAccountButton != null)
                    showPopup(GameTestActivity.this, pointCreateAccountButton, "");
            }
        });

        Button btn_test = (Button)findViewById(R.id.testBtn);
        btn_test.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onWindowFocusChanged(true);
                if(pointTestBtn!=null)

                    showPopup(GameTestActivity.this,pointTestBtn,"Bug number 2");
            }
        });
    }

    // Get the x and y position after the button is draw on screen
// (It's important to note that we can't get the position in the onCreate(),
// because at that stage most probably the view isn't drawn yet, so it will return (0, 0))
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        int[] location1 = new int[2];
        int[] location2 = new int[2];
        Button createAccountButton = (Button) findViewById(R.id.createAccountButton);
        Button testBtn = (Button) findViewById(R.id.testBtn);

        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.
        createAccountButton.getLocationOnScreen(location1);
        testBtn.getLocationOnScreen(location2);

        //Initialize the Point with x, and y positions
        pointCreateAccountButton = new Point();
        pointCreateAccountButton.x = location1[0];
        pointCreateAccountButton.y = location1[1];

        pointTestBtn = new Point();
        pointTestBtn.x = location2[0];
        pointTestBtn.y = location2[1];

    }

    // The method that displays the popup.
    private void showPopup(final Activity context, Point p, String bug) {
        int popupWidth = 200;
        int popupHeight = 150;

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);


        TextView textView2 = (TextView)layout.findViewById(R.id.textView2);
        textView2.setText(bug);


        //.....
        popup.setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 50;
        int OFFSET_Y = 50;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.close);
        close.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }
}
