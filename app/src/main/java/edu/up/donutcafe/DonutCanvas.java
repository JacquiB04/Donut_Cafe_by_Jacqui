package edu.up.donutcafe;

/**
 * @author: Jacqui Bouchard
 * @date: 09/30/2024
 * @Assignment: Custom Coloring HW
 * @file: DonutCanvas.java
 * @Description:
 *  This file contains the DonutCanvas class which extends the SurfaceView. This class includes
 *  the methods for drawing the elements onto the surface view.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class DonutCanvas extends SurfaceView {
    private DonutModel myModel = new DonutModel();

    /**
     * Constructs the DonutCanvas class by defining the colors of each element
     *
     * @param context - abstract android class for applications
     * @param attrs - a collection of attributes as found in xml files
     */
    public DonutCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);

        //Tell the app that this view actually DOES draw something so please call the onDraw()
        //method at the right time.
        setWillNotDraw(false);

        //Set up the colors
        myModel.tablePaint.setColor(0xFFB30000);
        myModel.tablePaint.setStyle(Paint.Style.FILL);
        myModel.platePaint.setColor(0xFFFFFFFF);
        myModel.platePaint.setStyle(Paint.Style.FILL);
        myModel.donutPaint.setColor(0xFFA98148);
        myModel.donutPaint.setStyle(Paint.Style.STROKE);
        myModel.donutPaint.setStrokeWidth(myModel.plateRadius/3);
        myModel.napkinPaint.setColor(0xFF000000);
        myModel.napkinPaint.setStyle(Paint.Style.FILL);
        myModel.notePaint.setColor(0xFF000080);
        myModel.notePaint.setStyle(Paint.Style.FILL);
        myModel.pencilPaint.setColor(0xFFCEB301);
        myModel.pencilPaint.setStyle(Paint.Style.FILL);
    }

    public void drawPlate(Canvas canvas, Paint paint) {
        canvas.drawCircle(myModel.plateX, myModel.plateY, myModel.plateRadius, paint);
    }

    public void drawDonut(Canvas canvas, Paint paint) {
        canvas.drawCircle(myModel.plateX, myModel.plateY,myModel.plateRadius/3, paint);

    }

    public void drawNapkin(Canvas canvas, Paint paint) {
        canvas.drawRect(myModel.napkinLeft, myModel.napkinTop,
                myModel.napkinLeft + myModel.napkinWidth,
                myModel.napkinTop + myModel.napkinWidth, paint);
    }

    /**
     * Draws the notebook to the screen along with notebook spirals
     * I used an external source for drawing the spirals as indicated
     * below.
     *
     * @param canvas - canvas we are drawing on
     * @param paint - notebook color
     */
    public void drawNote(Canvas canvas, Paint paint) {
        canvas.drawRect(myModel.noteLeft, myModel.noteTop,
                myModel.noteLeft + myModel.noteWidth,
                myModel.noteTop + myModel.noteLength, paint);

        //Draw notebook spirals
        int numSpirals = 5;
        float spiralChangeY = myModel.noteLength / numSpirals;
        float spiralRadius = 20.0f;
        float spiralStartY = myModel.noteTop + spiralRadius;
        Paint spiralPaint = new Paint();
        spiralPaint.setColor(0xFFFFFFFF);
        spiralPaint.setStyle(Paint.Style.STROKE);
        spiralPaint.setStrokeWidth(5.0f);

        /**
         External Citation
         Date:     21 September 2024
         Problem:  Couldn't figure out how to draw only parts of a circle.
         Resource:
         https://stackoverflow.com/questions/35135153/android-drawing-semi-circle-with-canvas
         Solution: I read through the post and learned how to use the drawArc(...)
                    function for my spirals.
         */
        for (int i = 0; i < numSpirals; i++) {
            canvas.drawArc(myModel.noteLeft - spiralRadius, spiralStartY,
                    myModel.noteLeft + spiralRadius, spiralStartY + spiralRadius,
                    0, 270, false, spiralPaint);

            spiralStartY += spiralChangeY;
        }
    }//drawNote()

    /**
     * Draws the pencil element along with a flattened pencil tip.
     *
     * @param canvas - canvas we are drawing on
     * @param paint - pencil paint
     */
    public void drawPencil(Canvas canvas, Paint paint) {
        canvas.drawRect(myModel.pencilLeft, myModel.pencilTop,
                myModel.pencilLeft + myModel.pencilWidth,
                myModel.pencilTop + myModel.pencilLength, paint);

        //Draw rectangular pencil tip
        Paint pencilTip = new Paint();
        pencilTip.setColor(0xFF837E7C);
        pencilTip.setStyle(Paint.Style.FILL);
        canvas.drawRect(myModel.pencilLeft, myModel.pencilTop + myModel.pencilLength,
                myModel.pencilLeft + myModel.pencilWidth,
                myModel.pencilTop + myModel.pencilLength + 10.0f, pencilTip);
    }

    public void drawTable(Canvas canvas, Paint paint) {
        setBackgroundColor(paint.getColor());
    }

    /**
     * Calls the methods necessary for drawing the elements onto the canvas.
     *
     * @param canvas - canvas we are drawing on
     */
    @Override
    public void onDraw(Canvas canvas){
        drawPlate(canvas, myModel.platePaint);
        drawDonut(canvas, myModel.donutPaint);
        drawNapkin(canvas, myModel.napkinPaint);
        drawNote(canvas, myModel.notePaint);
        drawPencil(canvas, myModel.pencilPaint);
        drawTable(canvas, myModel.tablePaint);
    }

    public DonutModel getModel() { return this.myModel; }
}
