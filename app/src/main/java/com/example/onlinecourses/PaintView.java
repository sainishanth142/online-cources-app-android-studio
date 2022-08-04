package com.example.onlinecourses;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

public class PaintView extends View {

    // below we are creating variables for our paint
    Paint otherPaint, outerPaint, textPaint;

    // and a floating variable for our left arc.
    float arcLeft;

    @SuppressLint("ResourceAsColor")
    public PaintView(Context context) {
        super(context);

        // on below line we are initializing our paint variable for our text
        textPaint = new Paint(Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        // on below line we are setting color to it.
        textPaint.setColor(Color.WHITE);

        // on below line we are setting text size to it.
        // In Paint we have to add text size using px so
        // we have created a method where we are converting dp to pixels.
        textPaint.setTextSize(pxFromDp(context, 24));

        // on below line we are initializing our outer paint
        outerPaint = new Paint();

        // on below line we are setting style to our paint.
        outerPaint.setStyle(Paint.Style.FILL);

        // on below line we are setting color to it.
        outerPaint.setColor(getResources().getColor(R.color.purple_200));

        // on below line we are creating a display metrics
        DisplayMetrics displayMetrics = new DisplayMetrics();

        // on below line we are getting display metrics.
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);

        // on below line we are assigning
        // the value to the arc left.
        arcLeft = pxFromDp(context, 20);

        // on below line we are creating
        // a new variable for our paint
        otherPaint = new Paint();
    }

    // below method is use to generate px from DP.
    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // below four lines of code is use to add
        // back color to our screen which is green
        canvas.drawPaint(outerPaint);

        // on below line we are setting color to our paint.
        otherPaint.setColor(Color.WHITE);

        // on below line we are setting style to out paint.
        otherPaint.setStyle(Paint.Style.FILL);

        // below 4 lines of code is use to
        // create white rectangle of screen
        canvas.drawRect(
                getLeft() + (getRight() - getLeft()) / 3,
                getTop() + (getBottom() - getTop()) / 3,
                getRight() - (getRight() - getLeft()) / 3,
                getBottom() - (getBottom() - getTop()) / 3, otherPaint);

        // on below line we are changing the color for our paint.
        otherPaint.setColor(getResources().getColor(R.color.purple_200));

        // on below line we are drawing a circle and passing
        // width, height, left arc and paint to add color.
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, arcLeft, otherPaint);

        // on below line we are adding text using paint in our canvas.
        canvas.drawText("Geeks for Geeks", (float) (getWidth() * 0.3), (float) (getHeight() * 0.8), textPaint);
    }
}

