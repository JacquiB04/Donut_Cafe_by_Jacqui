package edu.up.donutcafe;

/**
 * @author: Jacqui Bouchard
 * @date: 09/30/2024
 * @Assignment: Custom Coloring HW
 * @file: MainActivity.java
 * @Description:
 *  This file contains the MainActivity class which sets the content view of the app to the Donut
 *  Cafe layout. This class also references event views from the layout and registers an instance
 *  of the DonutController class as a listener to the views.
 */

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.pm.ActivityInfo;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.donut_cafe);

        //Get a reference to event views.
        SeekBar seekRed = findViewById(R.id.sb_red);
        SeekBar seekGreen = findViewById(R.id.sb_green);
        SeekBar seekBlue = findViewById(R.id.sb_blue);
        TextView redNum = findViewById(R.id.tv_red_num);
        TextView greenNum = findViewById(R.id.tv_green_num);
        TextView blueNum = findViewById(R.id.tv_blue_num);
        TextView elementName = findViewById(R.id.tv_element_name);

        DonutCanvas myCafe = findViewById(R.id.donutcanvas);
        DonutController myControl = new DonutController(myCafe, seekRed, seekGreen, seekBlue,
                redNum, greenNum, blueNum, elementName);

        //Register controller as a listener to the views.
        seekRed.setOnSeekBarChangeListener(myControl);
        seekGreen.setOnSeekBarChangeListener(myControl);
        seekBlue.setOnSeekBarChangeListener(myControl);
        myCafe.setOnTouchListener(myControl);
    }
}