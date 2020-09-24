//package com.nstudio.myapplication.engine;
//
//import android.animation.Animator;
//import android.animation.AnimatorListenerAdapter;
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.RectF;
//import android.view.ViewGroup;
//
//import com.nstudio.myapplication.R;
//import com.nstudio.myapplication.Utils.ScreenUtils;
//
//public class PuzzleCell extends BaseCell {
//    private int color=Color.parseColor("#AC9A6A");
//    private float rectsize;
//    private float rectRadious;
//    private int blocksize;
//    private int colum;
//    public PuzzleCell(Context context) {
//        super(context);
//        rectsize=getContext().getResources().getDimension(R.dimen.dp5);
//        rectRadious=getContext().getResources().getDimension(R.dimen.dp10);
//    }
//
//
//    public int getColum() {
//        return colum;
//    }
//
//    public void setColum(int colum) {
//        this.colum = colum;
//    }
//
//    public int getColor() {
//        return color;
//    }
//
//    public int getBlocksize() {
//        return blocksize;
//    }
//
//    public void setBlocksize(int blocksize) {
//        this.blocksize = blocksize;
//    }
//
//    public void setColor(int color) {
//        this.color = color;
//        setValue(1);
//        invalidate();
//    }
//    public void setColorHint(int color) {
//        this.color = color;
//        setValue(100);
//        invalidate();
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
//    }
//    public void Destroy(boolean Y){
////        if(Y) {
//        setValue(0);
//        color = Color.parseColor("#AC9A6A");
//        invalidate();
//
//        animate().scaleX(0f).scaleY(0f).rotation(180).setDuration(300).setListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                setDrawingCacheEnabled(true);
//                setRotation(0);
//                setScaleX(1f);
//                setScaleY(1f);
//            }
//        }).start();
//
////            animate().setDuration(300).scaleY(0f).scaleX(0f).setListener(new AnimatorListenerAdapter() {
////                @Override
////                public void onAnimationEnd(Animator animation) {
////                    animation.cancel();
////                    animate().setDuration(0).scaleY(1f).scaleX(1f).start();
////                }
////            }).start();
////        }else {
////            animate().setDuration(300).scaleX(0).setListener(new AnimatorListenerAdapter() {
////                @Override
////                public void onAnimationEnd(Animator animation) {
////                    setValue(0);
////                    setScaleX(1);
////                    color = Color.parseColor("#FF474747");
////                    invalidate();
////                }
////            });
////        }
////        animate().setDuration(300).alpha(0).setListener(new AnimatorListenerAdapter() {
////            @Override
////            public void onAnimationEnd(Animator animation) {
////                setAlpha(1);
////                color= Color.parseColor("#FF474747");
////                invalidate();
////            }
////        });
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        Paint paint2 = new Paint();
//        paint2.setColor(color);
//        paint2.setStyle(Paint.Style.FILL);
//        paint2.setAntiAlias(true);
//
//        RectF rectF = new RectF(
//                rectsize, // left
//                rectsize, // top
//                canvas.getWidth() - rectsize, // right
//                canvas.getHeight() -rectsize // bottom
//        );
//
//
//
//        canvas.drawRoundRect(
//                rectF, // rect
//                rectsize*2, // rx
//                rectsize*2, // ry
//                paint2 // Paint
//        );
//    }
//
//    public void clearColorHint() {
//        this.color=Color.parseColor("#AC9A6A");
//        invalidate();
//        setValue(0);
//    }
//}
