package com.nstudio.puzzleblockfreesimple.engine;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class PuzzleBlockCell extends BaseCell {
    private int color=Color.parseColor("#594d41");
    private float rectsize;
    private float rectRadious;
    public PuzzleBlockCell(Context context) {
        super(context);
        rectsize=0.8f;
        rectRadious=2;
    }


    public void setColor(int color) {
        this.color = color;
        setValue(1);
        invalidate();
    }

    public int getColor() {
        return color;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
    public void Destroy(boolean Y){
//        if(Y) {
            animate().setDuration(300).scaleY(0).scaleX(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    setValue(0);
                    color = Color.parseColor("#AC9A6A");
                    invalidate();
                    setScaleY(1);
                    setScaleX(1);
                }
            });
//        }else {
//            animate().setDuration(300).scaleX(0).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    setValue(0);
//                    setScaleX(1);
//                    color = Color.parseColor("#FF474747");
//                    invalidate();
//                }
//            });
//        }
//        animate().setDuration(300).alpha(0).setListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                setAlpha(1);
//                color= Color.parseColor("#FF474747");
//                invalidate();
//            }
//        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint2 = new Paint();
        paint2.setColor(color);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);

        RectF rectF = new RectF(
                rectsize, // left
                rectsize, // top
                canvas.getWidth() - rectsize, // right
                canvas.getHeight() -rectsize // bottom
        );



        canvas.drawRoundRect(
                rectF, // rect
                rectRadious*2, // rx
                rectRadious*2, // ry
                paint2 // Paint
        );
    }
}
