package com.nstudio.puzzleblockfreesimple.engine;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.nstudio.puzzleblockfreesimple.Library.GameOverDialog;
import com.nstudio.puzzleblockfreesimple.Library.GameOverListener;
import com.nstudio.puzzleblockfreesimple.R;
import com.nstudio.puzzleblockfreesimple.Utils.ScreenUtils;
import com.nstudio.puzzleblockfreesimple.Utils.SharedPrefsUtil;

import java.util.Random;

import static android.widget.RelativeLayout.ALIGN_PARENT_BOTTOM;
import static android.widget.RelativeLayout.TRUE;

/**
 * Created by Nhatran241 on 18/04/2019
 */
public class GameView extends GridView{
    float dX, dY;
    float dX2, dY2;
    float dX3, dY3;
    float odX, odY;
    float odX2, odY2;
    float odX3, odY3;

    private int currentBlock=3;

    private PuzzleBlock child,childhint;
    private PuzzleBlock child2,childhint2;
    private PuzzleBlock child3,childhint3;

    private RelativeLayout paren;

    public Dialog dialo;

    InterstitialAd mInterstitialAd;

    /**
     * để tạm score ở đây sau nên đem vào library;
     */
    private TextView Vscore,Vhighscore;
    private int hscore =0;


    int style=-1;
    public GameView(final Context context , AttributeSet attrs){
        super(context,attrs);
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdListener(new AdListener() {


            @Override
            public void onAdOpened() {
                restart();

            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
        mInterstitialAd.setAdUnitId("ca-app-pub-2116449996149867/4559640118");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                GameEngine.getInstance().createGrid(context);
                setNumColumns(GameEngine.WIDTH);
                setAdapter(new GridAdapter());

                paren = (RelativeLayout) getParent();
                /*
                        addView
                 */
//                child = new PuzzleBlockView(getContext());
//                highScoreView=paren.findViewById(R.id.hv);
                Vscore = paren.findViewById(R.id.tv_score);
                Vhighscore =paren.findViewById(R.id.tv_highscore);
                hscore=SharedPrefsUtil.getInstance().getScore(getContext());
                Vscore.setText(hscore+"");
                Vhighscore.setText(SharedPrefsUtil.getInstance().getHighScore(getContext())+"");


                final RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(getWidth()/4, getWidth()/4);

                params.addRule(RelativeLayout.BELOW, R.id.gameview);
                params.addRule(ALIGN_PARENT_BOTTOM,TRUE);
//                params.addRule(CENTER_VERTICAL,TRUE);
                params.setMargins((int) ScreenUtils.convertPixelsToDp(getWidth()/9,getContext()),0,0,0);
//                child.setGravity(CENTER_VERTICAL);
//                child.setGravity(CENTER_HORIZONTAL);

                if(SharedPrefsUtil.getInstance().getBlock1(context)==-1){
                    child = new PuzzleBlock(context,0);
                    child.setVisibility(INVISIBLE);
                    GameEngine.block1=-1;
                    currentBlock--;
                }else {
                    child = new PuzzleBlock(context,SharedPrefsUtil.getInstance().getBlock1(context));
                        SharedPrefsUtil.getInstance().setBlock1(context,child.getPuzzleBlockCell().getValue());
                }
                child.setLayoutParams(params);
                 paren.addView(child);

                final RelativeLayout.LayoutParams params2= new RelativeLayout.LayoutParams(getWidth()/4,  getWidth()/4);
                params2.addRule(RelativeLayout.BELOW,R.id.gameview);
                params2.addRule(ALIGN_PARENT_BOTTOM,TRUE);
                params2.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
//                params2.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);

                if(SharedPrefsUtil.getInstance().getBlock2(context)==-1){
                    child2 = new PuzzleBlock(context,0);
                    child2.setVisibility(INVISIBLE);
                    GameEngine.block2=-1;
                    currentBlock--;
                }else {
                    child2 = new PuzzleBlock(context,SharedPrefsUtil.getInstance().getBlock2(context));
                    SharedPrefsUtil.getInstance().setBlock2(context,child2.getPuzzleBlockCell().getValue());
                }
                child2.setLayoutParams(params2);
                paren.addView(child2);

                final RelativeLayout.LayoutParams params3= new RelativeLayout.LayoutParams(getWidth()/4,  getWidth()/4);
                params3.addRule(RelativeLayout.BELOW, R.id.gameview);
                params3.addRule(ALIGN_PARENT_BOTTOM,TRUE);
                params3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
                params3.setMargins( 0,0, (int) ScreenUtils.convertPixelsToDp(getWidth()/9,getContext()),0);

                if(SharedPrefsUtil.getInstance().getBlock3(context)==-1){
                    child3 = new PuzzleBlock(context,0);
                    child3.setVisibility(INVISIBLE);

                    GameEngine.block3=-1;
                    currentBlock--;
                }else {
                    child3 = new PuzzleBlock(context,SharedPrefsUtil.getInstance().getBlock3(context));
                    SharedPrefsUtil.getInstance().setBlock3(context,child3.getPuzzleBlockCell().getValue());
                }
                child3.setLayoutParams(params3);
                paren.addView(child3);


//                 final View gameview = paren.findViewById(R.id.gameview);
//                 final PuzzleBlockView child = paren.findViewById(R.id.puzzle1);
//                final PuzzleBlockView2 child2 = paren.findViewById(R.id.puzzle2);
                 child.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                     @Override
                     public void onGlobalLayout() {
                         getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                         child.setScaleX(child.getPuzzleCells()[0].getColum()/3f);
//                         child.setScaleY(child.getPuzzleCells()[0].getColum()/3f);
                        odX=child.getX();
                        odY=child.getY();
//                        SharedPrefsUtil.getInstance().setBlock1(context,child.getPuzzleBlockCell().getValue());
                        GameEngine.block1=child.getPuzzleBlockCell().getValue();
                     }
                 });
                child2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                        child2.setScaleX(child2.getPuzzleCells()[0].getColum()/3f);
//                        child2.setScaleY(child2.getPuzzleCells()[0].getColum()/3f);
                        odX2=child2.getX();
                        odY2=child2.getY();
//                        SharedPrefsUtil.getInstance().setBlock2(context,child2.getPuzzleBlockCell().getValue());
                        GameEngine.block2=child2.getPuzzleBlockCell().getValue();
                    }
                });
                child3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                        child3.setScaleX(child3.getPuzzleCells()[0].getColum()/3f);
//                        child3.setScaleY(child3.getPuzzleCells()[0].getColum()/3f);
                        odX3=child3.getX();
                        odY3=child3.getY();
//                        SharedPrefsUtil.getInstance().setBlock3(context,child3.getPuzzleBlockCell().getValue());
                        GameEngine.block3=child3.getPuzzleBlockCell().getValue();
                    }
                });
                paren.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
//                        final int x = (int) event.getRawX();
//                        final int y = (int) event.getRawY();

                        switch (event.getAction() & MotionEvent.ACTION_MASK) {
                            case MotionEvent.ACTION_DOWN:
                                if(event.getRawY()>getHeight()/2) {
                                    if (event.getRawX() > getWidth() / 3 * 2) {
                                        if (child3.getVisibility() == VISIBLE)
                                            style = 2;
                                        else style = -1;
                                    } else if (event.getRawX() > getWidth() / 3) {
                                        if (child2.getVisibility() == VISIBLE)
                                            style = 1;
                                        else style = -1;
                                    } else {
                                        if (child.getVisibility() == VISIBLE)
                                            style = 0;
                                        else style = -1;
                                    }
                                    if (style == 0) {

                                        dX = child.getX() - event.getRawX();
                                        dY = child.getY() - event.getRawY();
                                        child.animate()
                                                .x(child.getX())
                                                .y(getHeight()+child.getHeight()/2f)
                                                .scaleX(1.8f)
                                                .scaleY(1.8f)
                                                .setDuration(0)
                                                .start();
                                    } else if (style == 1) {
                                        dX2 = child2.getX() - event.getRawX();
                                        dY2 = child2.getY() - event.getRawY();
                                        child2.animate()
                                                .x(child2.getX())
                                                .y(getHeight()+child2.getHeight()/2f)
                                                .scaleX(1.8f)
                                                .scaleY(1.8f)
                                                .setDuration(0)
                                                .start();
                                    } else if (style == 2) {
                                        dX3 = child3.getX() - event.getRawX();
                                        dY3 = child3.getY() - event.getRawY();
                                        child3.animate()
                                                .x(child3.getX())
                                                .y(getHeight()+child3.getHeight()/2f)
                                                .scaleX(1.8f)
                                                .scaleY(1.8f)
                                                .setDuration(0)
                                                .start();
                                    }
                                }else {
                                    style=-1;
                                }


                                break;
                            case MotionEvent.ACTION_UP:
                                if(style==1){
                                    int pos = pointToPosition((int)child2.getX()+child2.getCenterpointX(), (int) (child2.getY()+child2.getCenterpointY())-(paren.getHeight()/2-getHeight()/2));
                                    GameEngine.getInstance().addPuzzle(pos, new GameEngine.addPuzzleListener() {
                                        @Override
                                        public void onAddPuzzleSuccess() {
//

                                            setScore(child2.getScore());
                                            child2.setVisibility(GONE);
                                            GameEngine.block2=-1;
                                            SharedPrefsUtil.getInstance().setBlock2(context,-1);
                                            currentBlock--;
                                            checkReCreateBlock();
                                        }

                                        @Override
                                        public void onAddPuzzleFailed() {
                                            child2.animate()
                                                    .x(odX2)
                                                    .y(odY2)
                                                    .scaleX(1f)
                                                    .scaleY(1f)
                                                    .setDuration(100)
                                                    .start();
                                        }

                                        @Override
                                        public void onScore(int score) {
                                            setScore(score);
//                                            highScoreView.setScore(hscore);
                                        }

                                        @Override
                                        public void onLose() {
                                           showDialogGameOver();

                                        }
                                    },child2.getPuzzleBlockCell());


                                }else if(style==0) {
                                    int pos = pointToPosition((int)child.getX()+child.getCenterpointX(), (int) (child.getY()+child.getCenterpointY())-(paren.getHeight()/2-getHeight()/2));
                                  GameEngine.getInstance().addPuzzle(pos, new GameEngine.addPuzzleListener() {
                                        @Override
                                        public void onAddPuzzleSuccess() {
//                                            GameEngine.getInstance().createBlock(context,child.getPuzzleCells());
//                                            child.updateAdapter();
//                                            child.reCreateBlock();
//                                            child.animate()
//                                                    .x(odX)
//                                                    .y(odY)
//                                                    .setDuration(0)
//                                                    .start();
                                            setScore(child.getScore());
                                            child.setVisibility(GONE);
                                            GameEngine.block1=-1;
                                            SharedPrefsUtil.getInstance().setBlock1(context,-1);
                                            currentBlock--;
                                            checkReCreateBlock();

                                        }

                                        @Override
                                        public void onAddPuzzleFailed() {
                                            child.animate()
                                                    .x(odX)
                                                    .y(odY)
                                                    .scaleX(1f)
                                                    .scaleY(1f)
                                                    .setDuration(100)
                                                    .start();
                                        }

                                      @Override
                                      public void onScore(int score) {
                                          setScore(score);
//                                          highScoreView.setScore(hscore);

                                      }

                                      @Override
                                      public void onLose() {
                                        showDialogGameOver();

                                      }
                                  },child.getPuzzleBlockCell());

                                }else if(style==2) {
                                    int pos = pointToPosition((int)child3.getX()+child3.getCenterpointX(), (int) (child3.getY()+child3.getCenterpointY())-(paren.getHeight()/2-getHeight()/2));
                                    GameEngine.getInstance().addPuzzle(pos, new GameEngine.addPuzzleListener() {
                                        @Override
                                        public void onAddPuzzleSuccess() {
//                                            GameEngine.getInstance().createBlock(context,child.getPuzzleCells());
//                                            child.updateAdapter();
//                                            child3.reCreateBlock();
//                                            child3.animate()
//                                                    .x(odX3)
//                                                    .y(odY3)
//                                                    .setDuration(0)
//                                                    .start();

                                            setScore(child3.getScore());
                                            child3.setVisibility(GONE);
                                            GameEngine.block3=-1;
                                            SharedPrefsUtil.getInstance().setBlock3(context,-1);
                                            currentBlock--;
                                            checkReCreateBlock();
                                        }

                                        @Override
                                        public void onAddPuzzleFailed() {
                                            child3.animate()
                                                    .x(odX3)
                                                    .y(odY3)
                                                    .scaleX(1f)
                                                    .scaleY(1f)
                                                    .setDuration(100)
                                                    .start();
                                        }

                                        @Override
                                        public void onScore(int score) {
                                            setScore(score);
//                                            highScoreView.setScore(hscore);

                                        }

                                        @Override
                                        public void onLose() {

                                            showDialogGameOver();

                                        }
                                    },child3.getPuzzleBlockCell());
                                }

                                    break;
                            case MotionEvent.ACTION_POINTER_DOWN:
                                break;
                            case MotionEvent.ACTION_POINTER_UP:
                                break;
                            case MotionEvent.ACTION_MOVE:
                                if(style==0){
                                    child.animate()
                                            .x(event.getRawX() + dX)
                                            .y(event.getRawY() + dY-child.getHeight()/2f)
                                            .setDuration(0)
                                            .start();
                                    int pos = pointToPosition((int)child.getX()+child.getCenterpointX(), (int) (child.getY()+child.getCenterpointY())-(paren.getHeight()/2-getHeight()/2));
//                                    int pos = pointToPosition((int)child.getX(), (int) (child.getY()+child.getHeight()/2)-(paren.getHeight()/2-getHeight()/2));
                                    GameEngine.getInstance().showHint(pos,child.getPuzzleBlockCell());
                                }else if(style==1) {
                                    child2.animate()
                                            .x(event.getRawX() + dX2)
                                            .y(event.getRawY() + dY2-child2.getHeight()/2f)
                                            .setDuration(0)
                                            .start();
                                    int pos = pointToPosition((int)child2.getX()+child2.getCenterpointX(), (int) (child2.getY()+child2.getCenterpointY())-(paren.getHeight()/2-getHeight()/2));
                                    GameEngine.getInstance().showHint(pos,child2.getPuzzleBlockCell());
                                }else if(style==2) {
                                    child3.animate()
                                            .x(event.getRawX() + dX3)
                                            .y(event.getRawY() + dY3-child3.getHeight()/2f)
                                            .setDuration(0)
                                            .start();
                                    int pos = pointToPosition((int)child3.getX()+child3.getCenterpointX(), (int) (child3.getY()+child3.getCenterpointY())-(paren.getHeight()/2-getHeight()/2));
                                   GameEngine.getInstance().showHint(pos,child3.getPuzzleBlockCell());
                                }

                                break;
                        }
                        return true;
                    }
                });
//                setOnItemClickListener(new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        int x= (int) (view.getX()/view.getWidth());
//                        int y= (int) (view.getY()/view.getHeight());
//
//                        Toast.makeText(context, ""+x+"/"+y, Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });

    }

    private void showDialogGameOver(){
                    new GameOverDialog.Builder(getContext())
                            .SetHScore(SharedPrefsUtil.getInstance().getHighScore(getContext())).SetScore(hscore)
                            .OnRate(new GameOverListener() {
                                @Override
                                public void OnClick(Dialog dialog) {
                                    Uri uri = Uri.parse("market://details?id=" + getContext().getPackageName());
                                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                                    // To count with Play market backstack, After pressing back button,
                                    // to taken back to our application, we need to add following flags to intent.
                                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                    try {
                                        getContext().startActivity(goToMarket);
                                    } catch (ActivityNotFoundException e) {
                                        getContext().startActivity(new Intent(Intent.ACTION_VIEW,
                                                Uri.parse("http://play.google.com/store/apps/details?id=" +  getContext().getPackageName())));
                                    }
                                }
                            })
                            .OnReplay(new GameOverListener() {
                                @Override
                                public void OnClick(Dialog dialog) {
                                    dialo=dialog;
                                    if(mInterstitialAd.isLoaded()){
                                        mInterstitialAd.show();
                                        dialog.dismiss();
                                    }else {
                                        dialog.dismiss();
                                        restart();
                                    }
                                }
                            })
                            .OnShare(new GameOverListener() {
                                @Override
                                public void OnClick(Dialog dialog) {
                                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                                    sharingIntent.setType("text/plain");
                                    String shareBody = "Try this game";
                                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                                    getContext().startActivity(Intent.createChooser(sharingIntent, "Share via"));
                                }
                            }).build();

    }
    private void setScore(int score){
        hscore+=score;
        SharedPrefsUtil.getInstance().setScore(getContext(),hscore);
        if(hscore> SharedPrefsUtil.getInstance().getHighScore(getContext())){
            SharedPrefsUtil.getInstance().setHighScore(getContext(),hscore);
            Vhighscore.setText(hscore+"");
            Vscore.setText(hscore+"");
        }else {
            Vscore.setText(hscore+"");
        }
    }

    private void checkReCreateBlock( ) {
        if(currentBlock==0){
            GameEngine.getInstance().findBlock();
            child.reCreateBlock();
            child2.reCreateBlock();
            child3.reCreateBlock();
            switch (new Random().nextInt(3-1+1)+1){
                case 1:{
                    child.reCreateBlock(GameEngine.blockre1);
                    break;
                }
                case 2:{
                    child2.reCreateBlock(GameEngine.blockre1);
                    break;
                }
                case 3:{

                    child3.reCreateBlock(GameEngine.blockre1);
                    break;
                }
            }
            GameEngine.block1=child.getPuzzleBlockCell().getValue();
            SharedPrefsUtil.getInstance().setBlock1(getContext(),child.getPuzzleBlockCell().getValue());
            child.animate()
                    .x(odX)
                    .y(odY)
                    .scaleX(0f)
                    .scaleY(0f)
                    .setDuration(0)
                    .start();
            child.setVisibility(VISIBLE);
            child.animate().scaleX(1f).scaleY(1f).setDuration(200).start();

//            child.animate().scaleX(child.getPuzzleCells()[0].getColum()/3f).scaleY(child.getPuzzleCells()[0].getColum()/3f).setDuration(200).start();


            GameEngine.block2=child2.getPuzzleBlockCell().getValue();
            SharedPrefsUtil.getInstance().setBlock2(getContext(),child2.getPuzzleBlockCell().getValue());
            child2.animate()
                    .x(odX2)
                    .y(odY2)
                    .scaleX(0f)
                    .scaleY(0f)
                    .setDuration(0)
                    .start();
            child2.setVisibility(VISIBLE);
            child2.animate().scaleX(1f).scaleY(1f).setDuration(200).start();
//            child2.animate().scaleX(child2.getPuzzleCells()[0].getColum()/3f).scaleY(child2.getPuzzleCells()[0].getColum()/3f).setDuration(200).start();


            GameEngine.block3=child3.getPuzzleBlockCell().getValue();
            SharedPrefsUtil.getInstance().setBlock3(getContext(),child3.getPuzzleBlockCell().getValue());
            child3.animate()
                    .x(odX3)
                    .y(odY3)
                    .scaleX(0f)
                    .scaleY(0f)
                    .setDuration(0)
                    .start();
            child3.setVisibility(VISIBLE);
            child3.animate().scaleX(1f).scaleY(1f).setDuration(200).start();
//            child3.animate().scaleX(child3.getPuzzleCells()[0].getColum()/3f).scaleY(child3.getPuzzleCells()[0].getColum()/3f).setDuration(200).start();
            currentBlock=3;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void restart() {
            GameEngine.getInstance().restart();
            hscore = 0;
            setScore(hscore);
            currentBlock = 0;
            checkReCreateBlock();
    }

    private class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return GameEngine.WIDTH*GameEngine.HEIGHT;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return GameEngine.getInstance().getCellAt(position);
        }
    }

}
