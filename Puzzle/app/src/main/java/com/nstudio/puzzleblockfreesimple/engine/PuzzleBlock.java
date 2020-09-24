package com.nstudio.puzzleblockfreesimple.engine;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import java.util.Random;

public class PuzzleBlock extends LinearLayout {
    private int numrow=5;

    PuzzleBlockCell puzzleCell1;

    Random random = new Random();
    int centerpointx;
    int centerpointy;
    private int score;
    int r=0;

    public PuzzleBlock(Context context) {
        super(context);
            initLayout(context);
            initPuzzleBlockCell(context);

    }
    public PuzzleBlock(Context context,int r) {
        super(context);
        this.r =r;
        initLayout(context);
        initPuzzleBlockCell(context);

    }

    public PuzzleBlock(Context context,AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
        initPuzzleBlockCell(context);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }



    public int getCenterpointX() {
        return centerpointx;
    }

    public int getCenterpointY() {
        return centerpointy;
    }

    private void initPuzzleBlockCell(Context context) {
        puzzleCell1 =new PuzzleBlockCell(context);
        LinearLayout.LayoutParams cellparam = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
        int co = puzzleCell1.getColor();
        switch (random.nextInt(5-1+1)+1){
            case 1:{co=Color.parseColor("#f09132");break;} // màu cam
            case 2:{co=Color.parseColor("#73c21f");break;} // xanh lá
            case 3:{co=Color.parseColor("#405ed6");break;} // xanh biển
            case 4:{co=Color.parseColor("#d20be0");break;} // tím cánh sen
            case 5:{co=Color.parseColor("#cf1d58");break;} // hồng cách sen
        }
        if(r==0) {
            r = random.nextInt(9 - 1 + 1) + 1;
        }
        switch(r){
            case 1:{
                 /**
                 * 2 cham
                 */
                puzzleCell1.setColor(co);
                puzzleCell1.setValue(1);
                puzzleCell1.setLayoutParams(cellparam);

                LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
                LinearLayout linearLayout1 = new LinearLayout(context);
                linearLayout1.setWeightSum(5);
                linearLayout1.setOrientation(HORIZONTAL);
                linearLayout1.setLayoutParams(params1);
                linearLayout1.setGravity(Gravity.CENTER);
                linearLayout1.setId((int) System.currentTimeMillis());
                addView(linearLayout1);
                linearLayout1.addView(puzzleCell1);

                LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//                params2.addRule(BELOW,linearLayout1.getId());
                LinearLayout linearLayout2 = new LinearLayout(context);
                linearLayout2.setWeightSum(5);
                linearLayout2.setOrientation(HORIZONTAL);
                linearLayout2.setLayoutParams(params2);
                linearLayout2.setGravity(Gravity.CENTER);
                addView(linearLayout2);

                PuzzleBlockCell puzzleCell2 =new PuzzleBlockCell(context);
                puzzleCell2.setColor(co);
                puzzleCell2.setLayoutParams(cellparam);
                linearLayout2.addView(puzzleCell2);
                getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        centerpointx=getWidth()/2;
                        centerpointy=getHeight()/4;
                        setScore(2);
                    }
                });

                break;


            }
            case 2:{
                /**
                 * 4 cham
                 */
                puzzleCell1.setColor(co);
                puzzleCell1.setValue(2);
                puzzleCell1.setLayoutParams(cellparam);

                LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
                LinearLayout linearLayout1 = new LinearLayout(context);
                linearLayout1.setWeightSum(5);
                linearLayout1.setOrientation(HORIZONTAL);
                linearLayout1.setLayoutParams(params1);
                linearLayout1.setGravity(Gravity.CENTER);
                addView(linearLayout1);
                linearLayout1.addView(puzzleCell1);

                PuzzleBlockCell puzzleCell2 =new PuzzleBlockCell(context);
                puzzleCell2.setColor(co);
                puzzleCell2.setLayoutParams(cellparam);
                linearLayout1.addView(puzzleCell2);
                ///

                LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//                params2.addRule(BELOW,linearLayout1.getId());
                LinearLayout linearLayout2 = new LinearLayout(context);
                linearLayout2.setWeightSum(5);
                linearLayout2.setOrientation(HORIZONTAL);
                linearLayout2.setLayoutParams(params2);
                linearLayout2.setGravity(Gravity.CENTER);
                addView(linearLayout2);

                PuzzleBlockCell puzzleCell3 =new PuzzleBlockCell(context);
                puzzleCell3.setColor(co);
                puzzleCell3.setLayoutParams(cellparam);
                linearLayout2.addView(puzzleCell3);

                PuzzleBlockCell puzzleCell4 =new PuzzleBlockCell(context);
                puzzleCell4.setColor(co);
                puzzleCell4.setLayoutParams(cellparam);
                linearLayout2.addView(puzzleCell4);
                getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        centerpointx=getWidth()/2-getWidth()/10;
                        centerpointy=getHeight()/4;
                        setScore(4);
                    }
                });
                break;



            }
            case 3:{

                /**
                 * 3 chaasm ngang
                 */
                puzzleCell1.setColor(co);
                puzzleCell1.setValue(3);
                puzzleCell1.setLayoutParams(cellparam);
                LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
                LinearLayout linearLayout1 = new LinearLayout(context);
                linearLayout1.setWeightSum(5);
                linearLayout1.setOrientation(HORIZONTAL);
                linearLayout1.setLayoutParams(params1);
                linearLayout1.setGravity(Gravity.CENTER);
                addView(linearLayout1);
                linearLayout1.addView(puzzleCell1);

                PuzzleBlockCell puzzleCell2 =new PuzzleBlockCell(context);
                puzzleCell2.setColor(co);
                puzzleCell2.setLayoutParams(cellparam);
                linearLayout1.addView(puzzleCell2);

                PuzzleBlockCell puzzleCell3 =new PuzzleBlockCell(context);
                puzzleCell3.setColor(co);
                puzzleCell3.setLayoutParams(cellparam);
                linearLayout1.addView(puzzleCell3);

                getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        centerpointx=getWidth()/2;
                        centerpointy=getHeight()/2;
                        setScore(3);
                    }
                });
                break;
            }
            case 4:{
                /**
                 * 5 cham doc
                 */
                puzzleCell1.setColor(co);
                puzzleCell1.setValue(4);
                puzzleCell1.setLayoutParams(cellparam);

                LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
                LinearLayout linearLayout1 = new LinearLayout(context);
                linearLayout1.setWeightSum(5);
                linearLayout1.setOrientation(HORIZONTAL);
                linearLayout1.setLayoutParams(params1);
                linearLayout1.setGravity(Gravity.CENTER);
                linearLayout1.setId((int) System.currentTimeMillis());
                addView(linearLayout1);
                linearLayout1.addView(puzzleCell1);

                LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//                params2.addRule(BELOW,linearLayout1.getId());
                LinearLayout linearLayout2 = new LinearLayout(context);
                linearLayout2.setWeightSum(5);
                linearLayout2.setOrientation(HORIZONTAL);
                linearLayout2.setLayoutParams(params2);
                linearLayout2.setGravity(Gravity.CENTER);
                addView(linearLayout2);

                PuzzleBlockCell puzzleCell2 =new PuzzleBlockCell(context);
                puzzleCell2.setColor(co);
                puzzleCell2.setLayoutParams(cellparam);
                linearLayout2.addView(puzzleCell2);

                LayoutParams params3 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//                params2.addRule(BELOW,linearLayout1.getId());
                LinearLayout linearLayout3 = new LinearLayout(context);
                linearLayout3.setWeightSum(5);
                linearLayout3.setOrientation(HORIZONTAL);
                linearLayout3.setLayoutParams(params3);
                linearLayout3.setGravity(Gravity.CENTER);
                addView(linearLayout3);

                PuzzleBlockCell puzzleCell3 =new PuzzleBlockCell(context);
                puzzleCell3.setColor(co);
                puzzleCell3.setLayoutParams(cellparam);
                linearLayout3.addView(puzzleCell3);

                LayoutParams params4 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//                params2.addRule(BELOW,linearLayout1.getId());
                LinearLayout linearLayout4 = new LinearLayout(context);
                linearLayout4.setWeightSum(5);
                linearLayout4.setOrientation(HORIZONTAL);
                linearLayout4.setLayoutParams(params4);
                linearLayout4.setGravity(Gravity.CENTER);
                addView(linearLayout4);

                PuzzleBlockCell puzzleCell4 =new PuzzleBlockCell(context);
                puzzleCell4.setColor(co);
                puzzleCell4.setLayoutParams(cellparam);
                linearLayout4.addView(puzzleCell4);

                getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        centerpointx=getWidth()/2;
                        centerpointy=getHeight()/8*7;
                        setScore(4);
                    }
                });
                break;



            }
            case 5:{

                /**
                 * 3 chaasm ngang +1 cham left
                 */
                LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
                LinearLayout linearLayout2 = new LinearLayout(context);
                linearLayout2.setWeightSum(5);
                linearLayout2.setOrientation(HORIZONTAL);
                linearLayout2.setLayoutParams(params2);
                linearLayout2.setGravity(Gravity.LEFT);
                addView(linearLayout2);

                PuzzleBlockCell puzzleCell4 =new PuzzleBlockCell(context);
                puzzleCell4.setLayoutParams(cellparam);
                puzzleCell4.setVisibility(INVISIBLE);


                PuzzleBlockCell puzzleCell5 =new PuzzleBlockCell(context);
                puzzleCell5.setColor(co);
                puzzleCell5.setLayoutParams(cellparam);

                linearLayout2.addView(puzzleCell4);
                linearLayout2.addView(puzzleCell5);

                puzzleCell1.setColor(co);
                puzzleCell1.setValue(5);
                puzzleCell1.setLayoutParams(cellparam);
                LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
                LinearLayout linearLayout1 = new LinearLayout(context);
                linearLayout1.setWeightSum(5);
                linearLayout1.setOrientation(HORIZONTAL);
                linearLayout1.setLayoutParams(params1);
                linearLayout1.setGravity(Gravity.CENTER);
                addView(linearLayout1);
                linearLayout1.addView(puzzleCell1);

                PuzzleBlockCell puzzleCell2 =new PuzzleBlockCell(context);
                puzzleCell2.setColor(co);
                puzzleCell2.setLayoutParams(cellparam);
                linearLayout1.addView(puzzleCell2);

                PuzzleBlockCell puzzleCell3 =new PuzzleBlockCell(context);
                puzzleCell3.setColor(co);
                puzzleCell3.setLayoutParams(cellparam);
                linearLayout1.addView(puzzleCell3);



                getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        centerpointx=getWidth()/2;
                        centerpointy=getHeight()/4;
                        setScore(4);
                    }
                });
                break;
            }

            case 6:{

                /**
                 * 3 chaasm ngang +1 cham right
                 */
                LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
                LinearLayout linearLayout2 = new LinearLayout(context);
                linearLayout2.setWeightSum(5);
                linearLayout2.setOrientation(HORIZONTAL);
                linearLayout2.setLayoutParams(params2);
                linearLayout2.setGravity(Gravity.RIGHT);
                addView(linearLayout2);

                PuzzleBlockCell puzzleCell4 =new PuzzleBlockCell(context);
                puzzleCell4.setLayoutParams(cellparam);
                puzzleCell4.setVisibility(INVISIBLE);


                PuzzleBlockCell puzzleCell5 =new PuzzleBlockCell(context);
                puzzleCell5.setColor(co);
                puzzleCell5.setLayoutParams(cellparam);

                linearLayout2.addView(puzzleCell5);
                linearLayout2.addView(puzzleCell4);

                puzzleCell1.setColor(co);
                puzzleCell1.setValue(6);
                puzzleCell1.setLayoutParams(cellparam);
                LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
                LinearLayout linearLayout1 = new LinearLayout(context);
                linearLayout1.setWeightSum(5);
                linearLayout1.setOrientation(HORIZONTAL);
                linearLayout1.setLayoutParams(params1);
                linearLayout1.setGravity(Gravity.CENTER);
                addView(linearLayout1);
                linearLayout1.addView(puzzleCell1);

                PuzzleBlockCell puzzleCell2 =new PuzzleBlockCell(context);
                puzzleCell2.setColor(co);
                puzzleCell2.setLayoutParams(cellparam);
                linearLayout1.addView(puzzleCell2);

                PuzzleBlockCell puzzleCell3 =new PuzzleBlockCell(context);
                puzzleCell3.setColor(co);
                puzzleCell3.setLayoutParams(cellparam);
                linearLayout1.addView(puzzleCell3);



                getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        centerpointx=getWidth()/2;
                        centerpointy=getHeight()/4;
                        setScore(4);
                    }
                });
                break;
            }
            case 7:{
                /**
                 * 3 cham left
                 */
                puzzleCell1.setColor(co);
                puzzleCell1.setValue(7);
                puzzleCell1.setLayoutParams(cellparam);

                LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
                LinearLayout linearLayout1 = new LinearLayout(context);
                linearLayout1.setWeightSum(5);
                linearLayout1.setOrientation(HORIZONTAL);
                linearLayout1.setLayoutParams(params1);
                linearLayout1.setGravity(Gravity.CENTER);
                addView(linearLayout1);
                linearLayout1.addView(puzzleCell1);

                PuzzleBlockCell puzzleCell2 =new PuzzleBlockCell(context);
                puzzleCell2.setColor(co);
                puzzleCell2.setLayoutParams(cellparam);
                linearLayout1.addView(puzzleCell2);
                ///

                LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//                params2.addRule(BELOW,linearLayout1.getId());
                LinearLayout linearLayout2 = new LinearLayout(context);
                linearLayout2.setWeightSum(5);
                linearLayout2.setOrientation(HORIZONTAL);
                linearLayout2.setLayoutParams(params2);
                linearLayout2.setGravity(Gravity.CENTER);
                addView(linearLayout2);

                PuzzleBlockCell puzzleCell3 =new PuzzleBlockCell(context);
                puzzleCell3.setColor(co);
                puzzleCell3.setLayoutParams(cellparam);
                linearLayout2.addView(puzzleCell3);

                PuzzleBlockCell puzzleCell4 =new PuzzleBlockCell(context);
                puzzleCell4.setLayoutParams(cellparam);
                puzzleCell4.setVisibility(INVISIBLE);
                linearLayout2.addView(puzzleCell4);


                getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        centerpointx=getWidth()/2-getWidth()/10;
                        centerpointy=getHeight()/4;
                        setScore(3);
                    }
                });
                break;



            }
            case 8:{
                /**
                 * 3 cham right
                 */
                puzzleCell1.setColor(co);
                puzzleCell1.setValue(8);
                puzzleCell1.setLayoutParams(cellparam);

                LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
                LinearLayout linearLayout1 = new LinearLayout(context);
                linearLayout1.setWeightSum(5);
                linearLayout1.setOrientation(HORIZONTAL);
                linearLayout1.setLayoutParams(params1);
                linearLayout1.setGravity(Gravity.CENTER);
                addView(linearLayout1);
                linearLayout1.addView(puzzleCell1);

                PuzzleBlockCell puzzleCell2 =new PuzzleBlockCell(context);
                puzzleCell2.setColor(co);
                puzzleCell2.setLayoutParams(cellparam);
                linearLayout1.addView(puzzleCell2);
                ///

                LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//                params2.addRule(BELOW,linearLayout1.getId());
                LinearLayout linearLayout2 = new LinearLayout(context);
                linearLayout2.setWeightSum(5);
                linearLayout2.setOrientation(HORIZONTAL);
                linearLayout2.setLayoutParams(params2);
                linearLayout2.setGravity(Gravity.CENTER);
                addView(linearLayout2);

                PuzzleBlockCell puzzleCell3 =new PuzzleBlockCell(context);
                puzzleCell3.setLayoutParams(cellparam);
                puzzleCell3.setVisibility(INVISIBLE);
                linearLayout2.addView(puzzleCell3);

                PuzzleBlockCell puzzleCell4 =new PuzzleBlockCell(context);
                puzzleCell4.setColor(co);
                puzzleCell4.setLayoutParams(cellparam);
                linearLayout2.addView(puzzleCell4);

                getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        centerpointx=getWidth()/2-getWidth()/10;
                        centerpointy=getHeight()/4;
                        setScore(3);
                    }
                });
                break;



            }
            case 9:{
                /**
                 * 1 cham
                 */
                puzzleCell1.setColor(co);
                puzzleCell1.setValue(9);
                puzzleCell1.setLayoutParams(cellparam);

                LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
                LinearLayout linearLayout1 = new LinearLayout(context);
                linearLayout1.setWeightSum(5);
                linearLayout1.setOrientation(HORIZONTAL);
                linearLayout1.setLayoutParams(params1);
                linearLayout1.setGravity(Gravity.CENTER);
                linearLayout1.setId((int) System.currentTimeMillis());
                addView(linearLayout1);
                linearLayout1.addView(puzzleCell1);

                getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        centerpointx=getWidth()/2;
                        centerpointy=getHeight()/2;
                        setScore(1);
                    }
                });

                break;


            }
        }
    }

    private void initLayout(Context context) {

        //
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
//        LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//        LinearLayout linearLayout1 = new LinearLayout(context);
//        linearLayout1.setWeightSum(5);
//        linearLayout1.setOrientation(HORIZONTAL);
//        linearLayout1.setLayoutParams(params1);
//        addView(linearLayout1);
//
//        LinearLayout.LayoutParams cellparam = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
//
//        puzzleCell1.setColor(Color.RED);
//        puzzleCell1.setLayoutParams(cellparam);
//
//        PuzzleBlockCell puzzleCell2 =new PuzzleBlockCell(context);
//        puzzleCell2.setColor(Color.CYAN);
//        puzzleCell2.setLayoutParams(cellparam);
//
//        PuzzleBlockCell puzzleCell3 =new PuzzleBlockCell(context);
//        puzzleCell3.setColor(Color.BLACK);
//        puzzleCell3.setLayoutParams(cellparam);
//
//        PuzzleBlockCell puzzleCell4 =new PuzzleBlockCell(context);
//        puzzleCell4.setColor(Color.RED);
//        puzzleCell4.setLayoutParams(cellparam);
//
//        PuzzleBlockCell puzzleCell5 =new PuzzleBlockCell(context);
//        puzzleCell5.setColor(Color.RED);
//        puzzleCell5.setLayoutParams(cellparam);
//
//        linearLayout1.addView(puzzleCell1);
////        linearLayout1.addView(puzzleCell2);
////        linearLayout1.addView(puzzleCell3);
////        linearLayout1.addView(puzzleCell4);
////        linearLayout1.addView(puzzleCell5);
//
//
//
//        //
//        LayoutParams params2 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//        LinearLayout linearLayout2 = new LinearLayout(context);
//        linearLayout1.setWeightSum(5);
//        linearLayout1.setOrientation(HORIZONTAL);
//        linearLayout1.setLayoutParams(params2);
////        addView(linearLayout2);
//
//        //
//        LayoutParams params3 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//        LinearLayout linearLayout3 = new LinearLayout(context);
//        linearLayout1.setWeightSum(5);
//        linearLayout1.setOrientation(HORIZONTAL);
//        linearLayout1.setLayoutParams(params3);
////        addView(linearLayout3);
//
//        //
//        LayoutParams params4 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//        LinearLayout linearLayout4 = new LinearLayout(context);
//        linearLayout1.setWeightSum(5);
//        linearLayout1.setOrientation(HORIZONTAL);
//        linearLayout1.setLayoutParams(params4);
////        addView(linearLayout4);
//
//        //
//        LayoutParams params5 = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//        LinearLayout linearLayout5 = new LinearLayout(context);
//        linearLayout1.setWeightSum(5);
//        linearLayout1.setOrientation(HORIZONTAL);
//        linearLayout1.setLayoutParams(params5);
//        addView(linearLayout5);
    }

    public PuzzleBlockCell getPuzzleBlockCell() {
        return puzzleCell1;
    }

    public void reCreateBlock(int r) {
        removeAllViews();
        Log.d("recreat", "reCreateBlock: "+r);
        this.r=r;
        initPuzzleBlockCell(getContext());
        invalidate();
    }
    public void reCreateBlock() {
        removeAllViews();
        r=0;
        Log.d("recreat", "reCreateBlock2: "+r);
        initPuzzleBlockCell(getContext());
        invalidate();
    }
}
