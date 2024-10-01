package edu.up.donutcafe;

/**
 * @author: Jacqui Bouchard
 * @date: 09/30/2024
 * @Assignment: Custom Coloring HW
 * @file: DonutController.java
 * @Description:
 *  This file contains the DonutController class which implements the necessary listener methods to
 *  handle app-specific events.
 */

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class DonutController implements SeekBar.OnSeekBarChangeListener,
                                        SurfaceView.OnTouchListener{
    private DonutCanvas myDonut;
    private DonutModel myModel;
    private SeekBar seekRed;
    private SeekBar seekGreen;
    private SeekBar seekBlue;
    private TextView redNum;
    private TextView greenNum;
    private TextView blueNum;
    private TextView elementName;

    /**
     * Constructor - initializes event views from their references. Gets a reference to the
     * GUI DonutCanvas as well.
     *
     * @param passDonut - the canvas we drew the objects on
     * @param passSeekRed - the seekbar for red values
     * @param passSeekGreen - the seekbar for green values
     * @param passSeekBlue - the seekbar for blue values
     * @param passRedNum - the textview that displays the red seekbar value
     * @param passGreenNum - the textview that displays the green seekbar value
     * @param passBlueNum - the textview that displays the blue seekbar value
     * @param passElement - the textview that displays the current drawing element
     */
    DonutController(DonutCanvas passDonut, SeekBar passSeekRed,
                    SeekBar passSeekGreen, SeekBar passSeekBlue,
                    TextView passRedNum, TextView passGreenNum,
                    TextView passBlueNum, TextView passElement) {
        myDonut = passDonut;
        myModel = myDonut.getModel();
        this.seekRed = passSeekRed;
        this.seekGreen = passSeekGreen;
        this.seekBlue = passSeekBlue;
        this.redNum = passRedNum;
        this.greenNum = passGreenNum;
        this.blueNum = passBlueNum;
        this.elementName = passElement;
    }

    /**
     * This method handles seekbar change events.
     *
     * @param seekBar - the seekbar experiencing the event
     * @param progress - where the seekbar is currently set
     * @param fromUser - is true if the event was caused by the user and not another method.
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar.equals(seekRed)) {
            redNum.setText("" + progress);
        }
        if(seekBar.equals(seekGreen)) {
            greenNum.setText("" + progress);
        }
        if(seekBar.equals(seekBlue)) {
            blueNum.setText("" + progress);
        }

        //Get new RGB values from the seekbar change.
        int R = seekRed.getProgress();
        int G = seekGreen.getProgress();
        int B = seekBlue.getProgress();

        //Reset Paints based on the seekbar change.
        if(myModel.isPlate) {
            myModel.platePaint.setARGB(255, R, G, B);
        }
        if(myModel.isDonut) {
            myModel.donutPaint.setARGB(255, R, G, B);
        }
        if(myModel.isNapkin) {
            myModel.napkinPaint.setARGB(255, R, G, B);
        }
        if(myModel.isNote) {
            myModel.notePaint.setARGB(255, R, G, B);
        }
        if(myModel.isPencil) {
            myModel.pencilPaint.setARGB(255, R, G, B);
        }
        if(myModel.isTable) {
            myModel.tablePaint.setARGB(255, R, G, B);
        }
        myDonut.invalidate();
    }//onProgressChanged()

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {/* Does nothing */}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {/* Does nothing */}

    /**
     * This method handles touch events from the user interacting with the surface view.
     *
     * @param view - The view the motionEvent occurred on
     * @param motionEvent - the touch from the user as an object containing information
     * @return true - if the listener obtains the event
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int paint = 0;

        //Make sure only one element can change at a time.
        setToFalse();

        //Discern which element the user touches and get its color.
        whichObject(x, y);
        paint = whichPaint();

        //Set the seekBars to the touched element's current sRGB values.
        setSeekColorValues(paint);

        return true;
    }

    /**
     * This method resets the booleans of an instance of DonutModel.
     */
    public void setToFalse() {
        myModel.isDonut = false;
        myModel.isPlate = false;
        myModel.isNapkin = false;
        myModel.isNote = false;
        myModel.isPencil = false;
        myModel.isTable = false;
    }

    /**
     * Sets the seekBars to the touched element's current sRGB values.
     *
     * @param paint - an integer representing a color
     */
    public void setSeekColorValues(int paint) {
        /**
         External Citation
         Date:     29 September 2024
         Problem:  I didn't know which methods I should use for obtaining sRGB values.
         Resource: https://developer.android.com/reference/android/graphics/Color#public-methods
         Solution: I read through the class and found out how to use the methods necessary.
         */

        int r = Color.red(paint);
        int g = Color.green(paint);
        int b = Color.blue(paint);

        seekRed.setProgress(r);
        seekGreen.setProgress(g);
        seekBlue.setProgress(b);
    }

    /**
     * This method uses information from a touch event to discern which object the user taps.
     *
     * @param x - x-location of the touch event
     * @param y - y-location of the touch event
     */
    public void whichObject(float x, float y) {
        int paint = 0;

        //Donut coordinates
        if(((x > myModel.plateX - myModel.plateRadius/3) && (x < myModel.plateX +
                (myModel.plateRadius/3))) && ((y > myModel.plateY - myModel.plateRadius/3)
                && (y < myModel.plateY + myModel.plateRadius/3))) {
            elementName.setText("Donut");
            myModel.isDonut = true;
        }
        //Plate coordinates
        else if(((x > myModel.plateX - myModel.plateRadius) && (x < myModel.plateX +
                myModel.plateRadius)) && ((y > myModel.plateY - myModel.plateRadius) &&
                (y < myModel.plateY + myModel.plateRadius))) {
            elementName.setText("Plate");
            myModel.isPlate = true;
        }
        //Napkin coordinates
        else if((x > myModel.napkinLeft && x < (myModel.napkinLeft + myModel.napkinWidth)) &&
                (y > myModel.napkinTop && y < (myModel.napkinTop + myModel.napkinWidth))) {
            elementName.setText("Napkin");
            myModel.isNapkin = true;
        }
        //Notebook coordinates
        else if((x > myModel.noteLeft && x < (myModel.noteLeft + myModel.noteWidth)) &&
                (y > myModel.noteTop && y < (myModel.noteTop + myModel.noteLength))) {
            elementName.setText("Notebook");
            myModel.isNote = true;
        }
        //Pencil coordinates
        else if((x > myModel.pencilLeft && x < (myModel.pencilLeft + myModel.pencilWidth)) &&
                (y > myModel.pencilTop && y < (myModel.pencilTop + myModel.pencilLength))) {
            elementName.setText("Pencil");
            myModel.isPencil = true;
        }
        else {
            elementName.setText("Table");
            myModel.isTable = true;
        }
    }//whichObject()

    /**
     * Finds the integer paint color of an object the user tapped
     *
     * @return paint - the paint color of the tapped object
     */
    public int whichPaint() {
        int paint = 0;

        /**
         External Citation
         Date:     29 September 2024
         Problem:  Didn't know which method I should use for obtaining integer color values.
         Resource: https://developer.android.com/reference/android/graphics/Paint#public-methods
         Solution: I read through the class and found out how to use the method.
         */

        if(myModel.isDonut) {
            paint = myModel.donutPaint.getColor();
        }
        if(myModel.isPlate) {
            paint = myModel.platePaint.getColor();
        }
        if(myModel.isNapkin) {
            paint = myModel.napkinPaint.getColor();
        }
        if(myModel.isNote) {
            paint = myModel.notePaint.getColor();
        }
        if(myModel.isPencil) {
            paint = myModel.pencilPaint.getColor();
        }
        if(myModel.isTable) {
            paint = myModel.tablePaint.getColor();
        }

        return paint;
    }//whichPaint()
}