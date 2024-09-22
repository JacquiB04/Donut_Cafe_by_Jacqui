package edu.up.donutcafe;

/**
 * @author: Jacqui Bouchard
 * @Assignment: Custom Coloring HW PartA
 * @file: DonutCanvas.java
 * @Description:
 *  This file contains the DonutCanvas class which extends the SurfaceView. This class
 *  includes the methods for drawing the elements onto the surface view.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class DonutCanvas extends SurfaceView {

    //The paint of each element
    private Paint tablePaint = new Paint();
    private Paint platePaint = new Paint();
    private Paint donutPaint = new Paint();
    private Paint napkinPaint = new Paint();
    private Paint notePaint = new Paint();
    private Paint pencilPaint = new Paint();

    //Element Dimensions
    public static final float plateLeft = 350.0f;
    public static final float plateTop = 300.0f;
    public static final float plateRadius = 200.0f;
    public static final float napkinLeft = 600.0f;
    public static final float napkinTop = 520.0f;
    public static final float napkinWidth = 250.0f;
    public static final float noteLeft = 1100.0f;
    public static final float noteTop = 300.0f;
    public static final float noteWidth = 350.0f;
    public static final float noteLength = 450.0f;
    public static final float pencilLeft = 1550.0f;
    public static final float pencilTop = 400.0f;
    public static final float pencilWidth = 15.0f;
    public static final float pencilLength = 250.0f;

    /**
     * Constructs the DonutCanvas class by defining the colors of each element
     * and setting the background color of the canvas.
     *
     * @param context - abstract android class for applications
     * @param attrs - a collection of attributes as found in xml files
     */
    public DonutCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);

        //Tell the app that this view (i.e., LizardCanvas object)
        //actually DOES draw something so please call the onDraw()
        //method at the right time.
        setWillNotDraw(false);

        //Set up the colors
        tablePaint.setColor(0xFFB30000);
        tablePaint.setStyle(Paint.Style.FILL);
        platePaint.setColor(0xFFFFFFFF);
        platePaint.setStyle(Paint.Style.FILL);
        donutPaint.setColor(0xFFA98148);
        donutPaint.setStyle(Paint.Style.STROKE);
        donutPaint.setStrokeWidth(plateRadius/3);
        napkinPaint.setColor(0xFF000000);
        napkinPaint.setStyle(Paint.Style.FILL);
        notePaint.setColor(0xFF000080);
        notePaint.setStyle(Paint.Style.FILL);
        pencilPaint.setColor(0xFFCEB301);
        pencilPaint.setStyle(Paint.Style.FILL);

        //Fills the table color
        //I will need to make a different method for this
        //to account for the user changing the table color
        setBackgroundColor(0xFFB30000);
    }

    public void drawPlate(Canvas canvas, Paint paint) {
        canvas.drawCircle(plateLeft, plateTop, plateRadius, paint);
    }

    public void drawDonut(Canvas canvas, Paint paint) {
        canvas.drawCircle(plateLeft, plateTop,plateRadius/3, paint);

    }

    public void drawNapkin(Canvas canvas, Paint paint) {
        canvas.drawRect(napkinLeft, napkinTop, napkinLeft + napkinWidth,
                napkinTop + napkinWidth, paint);
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
        canvas.drawRect(noteLeft, noteTop, noteLeft + noteWidth,
                noteTop + noteLength, paint);

        //Draw notebook spirals
        int numSpirals = 5;
        float spiralChangeY = noteLength / numSpirals;
        float spiralRadius = 20.0f;
        float spiralStartY = noteTop + spiralRadius;
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
            canvas.drawArc(noteLeft - spiralRadius, spiralStartY,
                    noteLeft + spiralRadius, spiralStartY + spiralRadius,
                    0, 270, false, spiralPaint);

            spiralStartY += spiralChangeY;
        }
    }


    /**
     * Draws the pencil element along with a flattened pencil tip.
     *
     * @param canvas - canvas we are drawing on
     * @param paint - pencil paint
     */
    public void drawPencil(Canvas canvas, Paint paint) {
        canvas.drawRect(pencilLeft, pencilTop, pencilLeft + pencilWidth,
                pencilTop + pencilLength, paint);

        //Draw rectangular pencil tip
        Paint pencilTip = new Paint();
        pencilTip.setColor(0xFF837E7C);
        pencilTip.setStyle(Paint.Style.FILL);
        canvas.drawRect(pencilLeft, pencilTop + pencilLength, pencilLeft + pencilWidth,
                pencilTop + pencilLength + 10.0f, pencilTip);
    }

    /**
     * Calls the methods necessary for drawing the elements onto the canvas.
     *
     * @param canvas - canvas we are drawing on
     */
    @Override
    public void onDraw(Canvas canvas){
        drawPlate(canvas, platePaint);
        drawDonut(canvas, donutPaint);
        drawNapkin(canvas, napkinPaint);
        drawNote(canvas, notePaint);
        drawPencil(canvas, pencilPaint);
    }
}
