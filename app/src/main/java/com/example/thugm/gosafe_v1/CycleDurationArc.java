package com.example.thugm.gosafe_v1;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;

import java.util.Calendar;
import java.util.Date;

/**
 * Author: Saimir Sulaj
 * Date: December 28, 2016
 * Purpose: Class to animate cycle progress arc.
 */

public class CycleDurationArc extends View {

    private static final int JANUARY = 0;
    private static final int FEBRUARY = 1;
    private static final int MARCH = 2;
    private static final int APRIL = 3;
    private static final int MAY = 4;
    private static final int JUNE = 5;
    private static final int JULY = 6;
    private static final int AUGUST = 7;
    private static final int SEPTEMBER = 8;
    private static final int OCTOBER = 9;
    private static final int NOVEMBER = 10;
    private static final int DECEMBER = 11;

    private static final int ARC_START_ANGLE = 180;
    private static final float THICKNESS_SCALE = 0.03f;
    private static final int ANIMATION_DURATION_SECONDS = 3;

    private Bitmap mBitmap;
    private Canvas mCanvas;

    private RectF mCircleOuterBounds;
    private RectF mCircleInnerBounds;

    private Paint mCirclePaint;
    private Paint mCircleOffPaint;
    private Paint mEraserPaint;

    private float mCircleSweepAngle;

    private ValueAnimator mAnimator;

    public CycleDurationArc(Context context) {
        this(context, null);
    }

    public CycleDurationArc(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CycleDurationArc(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        int circleColor = Color.RED;

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CycleDurationArc);
            if (ta != null) {
                circleColor = ta.getColor(R.styleable.CycleDurationArc_circleColor, circleColor);
                ta.recycle();
            }
        }

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(circleColor);

        mCircleOffPaint = new Paint();
        mCircleOffPaint.setAntiAlias(true);
        mCircleOffPaint.setColor(Color.LTGRAY);

        mEraserPaint = new Paint();
        mEraserPaint.setAntiAlias(true);
        mEraserPaint.setColor(Color.TRANSPARENT);
        mEraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // Trick to make the view square
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw || h != oldh) {
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mBitmap.eraseColor(Color.TRANSPARENT);
            mCanvas = new Canvas(mBitmap);
        }

        super.onSizeChanged(w, h, oldw, oldh);
        updateBounds();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas.drawColor(0, PorterDuff.Mode.CLEAR);

        if (mCircleSweepAngle > 0f) {
            mCanvas.drawArc(mCircleOuterBounds, ARC_START_ANGLE, 180, true, mCircleOffPaint);
            mCanvas.drawArc(mCircleOuterBounds, ARC_START_ANGLE, mCircleSweepAngle, true, mCirclePaint);
            mCanvas.drawOval(mCircleInnerBounds, mEraserPaint);
        }

        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    public void start(int startDate) {
        stop();

        java.util.Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);

        mAnimator = ValueAnimator.ofFloat(0f, (float) startDate / CycleDurationArc.daysInMonth(month) * 0.5f);
        Log.v("CUSTOMDEBUG", "startDate: " + Integer.toString(startDate));
        Log.v("CUSTOMDEBUG", "month: " + Integer.toString(month));
        Log.v("CUSTOMDEBUG", "daysInMonth: " + Integer.toString(CycleDurationArc.daysInMonth(month)));
        mAnimator.setDuration((int) (ANIMATION_DURATION_SECONDS * 1000 * ((float) startDate / CycleDurationArc.daysInMonth(month))));
        mAnimator.setInterpolator(new BounceInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                drawProgress((float) animation.getAnimatedValue());
            }
        });
        mAnimator.start();
    }

    public void stop() {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
            mAnimator = null;

            drawProgress(0f);
        }
    }

    private void drawProgress(float progress) {
        mCircleSweepAngle = 360 * progress;

        invalidate();
    }

    private void updateBounds() {
        final float thickness = getWidth() * THICKNESS_SCALE;

        mCircleOuterBounds = new RectF(0, 0, getWidth(), getHeight());
        mCircleInnerBounds = new RectF(
                mCircleOuterBounds.left + thickness,
                mCircleOuterBounds.top + thickness,
                mCircleOuterBounds.right - thickness,
                mCircleOuterBounds.bottom - thickness);

        invalidate();
    }

    public static int daysInMonth(int month) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Log.v("CUSTOMDEBUG", Integer.toString(year));
        if(month == FEBRUARY)
        {
            if (year % 400 == 0) {
                return 29;
            } else if (year % 100 == 0) {
                return 28;
            } else if (year % 4 == 0) {
                return 29;
            } else {
                return 28;
            }
        }
        else
        {
            // 0, 2, 4, 6 (july)
            // 7, 9, 11 then return 31
            return ((month < 7 && month % 2 == 0) || (month > 6 && month % 2 == 1)) ? 31 : 30;
        }
        /*switch (month) {
            case JANUARY: return 31;
            case FEBRUARY:
                if (year % 400 == 0) {
                    return 29;
                } else if (year % 100 == 0) {
                    return 28;
                } else if (year % 4 == 0) {
                    return 29;
                } else {
                    return 28;
                }
            case MARCH: return 31;
            case APRIL: return 30;
            case MAY: return 31;
            case JUNE: return 30;
            case JULY: return 31;
            case AUGUST: return 31;
            case SEPTEMBER: return 30;
            case OCTOBER: return 31;
            case NOVEMBER: return 30;
            default: return 31;
        }*/
    }
}
