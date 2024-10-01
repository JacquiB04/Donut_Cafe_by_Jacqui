package edu.up.donutcafe;

/**
 * @author: Jacqui Bouchard
 * @date: 09/30/2024
 * @Assignment: Custom Coloring HW
 * @file: DonutModel.java
 * @Description:
 *  This file contains the DonutModel class which initializes certain variables to be used within
 *  other classes of the donutcafe package.
 */

import android.graphics.Paint;

public class DonutModel {
    //For handling touch events in the DonutController class.
    public boolean isPlate = false;
    public boolean isDonut = false;
    public boolean isNapkin = false;
    public boolean isNote = false;
    public boolean isPencil = false;
    public boolean isTable = false;

    //Element Dimensions.
    //Used in DonutController and DonutCanvas Classes.
    public final float plateX = 350.0f;
    public final float plateY = 300.0f;
    public final float plateRadius = 200.0f;
    public final float napkinLeft = 600.0f;
    public final float napkinTop = 520.0f;
    public final float napkinWidth = 250.0f;
    public final float noteLeft = 1100.0f;
    public final float noteTop = 300.0f;
    public final float noteWidth = 350.0f;
    public final float noteLength = 450.0f;
    public final float pencilLeft = 1550.0f;
    public final float pencilTop = 400.0f;
    public final float pencilWidth = 20.0f;
    public final float pencilLength = 250.0f;

    //The paint of each element.
    //Used in DonutController and DonutCanvas Classes.
    public Paint tablePaint = new Paint();
    public Paint platePaint = new Paint();
    public Paint donutPaint = new Paint();
    public Paint napkinPaint = new Paint();
    public Paint notePaint = new Paint();
    public Paint pencilPaint = new Paint();
}
