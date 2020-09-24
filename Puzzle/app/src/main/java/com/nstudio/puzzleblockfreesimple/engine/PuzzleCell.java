package com.nstudio.puzzleblockfreesimple.engine;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.nstudio.puzzleblockfreesimple.R;

public class PuzzleCell extends BaseCell {
    private int color=Color.parseColor("#AC9A6A");
    private float rectsize;
    private float rectRadious;
    private int blocksize;
    private int colum;
    public PuzzleCell(Context context) {
        super(context);
        rectsize=getContext().getResources().getDimension(R.dimen.dp5);
        rectRadious=getContext().getResources().getDimension(R.dimen.dp10);
    }


    public int getColum() {
        return colum;
    }

    public void setColum(int colum) {
        this.colum = colum;
    }

    public int getColor() {
        return color;
    }

    public int getBlocksize() {
        return blocksize;
    }

    public void setBlocksize(int blocksize) {
        this.blocksize = blocksize;
    }

    public void setColor(int color) {
        this.color = color;
        setValue(1);
        invalidate();
    }
    public void setColorHint(int color) {
        this.color = color;
        setValue(100);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
    public void Destroy(final GameEngine.checkend checkend){
        animate().scaleX(0f).scaleY(0f).rotation(180).setDuration(400).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setValue(0);
                color = Color.parseColor("#AC9A6A");
                invalidate();
                setDrawingCacheEnabled(true);
                setRotation(0);
                setScaleX(1f);
                setScaleY(1f);
                checkend.checkend();
            }
        }).start();

    }

    public void Destroy(){
//
        animate().scaleX(0f).scaleY(0f).rotation(180).setDuration(400).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setDrawingCacheEnabled(true);
                setRotation(0);

                setValue(0);
                color = Color.parseColor("#AC9A6A");
                invalidate();
                setScaleX(1f);
                setScaleY(1f);

            }
        }).start();

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
                rectsize*2, // rx
                rectsize*2, // ry
                paint2 // Paint
        );
    }

    public void clearColorHint() {
        this.color=Color.parseColor("#AC9A6A");
        invalidate();
        setValue(0);
    }
}
