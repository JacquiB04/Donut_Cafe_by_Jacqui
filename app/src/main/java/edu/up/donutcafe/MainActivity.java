package edu.up.donutcafe;

/**
 * @author: Jacqui Bouchard
 * @Assignment: Custom Coloring HW PartA
 * @file: MainActivity.java
 * @Description:
 *  This file contains the MainActivity class which sets the content view
 *  of the app to the Donut Cafe layout.
 */

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.donut_cafe);
    }
}