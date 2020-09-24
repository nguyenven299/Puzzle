package com.nstudio.puzzleblockfreesimple.engine;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.util.Log;

import com.nstudio.puzzleblockfreesimple.Utils.SharedPrefsUtil;

import java.util.Random;

/**
 * Created by Nhatran241 18/04/2019
 */
public class GameEngine {
    private static GameEngine instance;
    public static int WIDTH=8;
    public static int HEIGHT=8;
    private int CELLSIZE=10;

    private Context context;

    private PuzzleCell[][] puzzleCellGrid;

    public static int block1;
    public static int block2;
    public static int block3;
    public static int checkendcount=0;
    public static int checkount=0;
    public static int blockre1=0;
    public static int blockre2=0;
    public static int blockre3=0;


    Handler handler = new Handler();



    public static GameEngine getInstance() {
        if( instance == null ){
            instance = new GameEngine();
        }

        return instance;
    }

    private GameEngine(){ }

    public void createGrid(Context context){
        this.context = context;
        puzzleCellGrid = new PuzzleCell[WIDTH][HEIGHT];
        setGrid(context);
        if(SharedPrefsUtil.getInstance().loadArray(context).length>0) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(SharedPrefsUtil.getInstance().loadArray(context)[i][j]!=Color.parseColor("#AC9A6A"))
                    puzzleCellGrid[i][j].setColor(SharedPrefsUtil.getInstance().loadArray(context)[i][j]);
                }
            }
        }


    }

    private void setGrid( final Context context){
        for( int x = 0 ; x < WIDTH ; x++ ){
            for( int y = 0 ; y < HEIGHT ; y++ ){
                if( puzzleCellGrid[x][y] == null ){
                        puzzleCellGrid[x][y] = new PuzzleCell(context);
                }
//                PuzzleGrid[x][y].setColor(Color.argb(255,rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));


            }
        }
    }

    public PuzzleCell getCellAt(int position) {
        int x = position % WIDTH;
        int y = position / WIDTH;

        return puzzleCellGrid[x][y];
    }
    public void addPuzzle(int position,addPuzzleListener addPuzzleListener,PuzzleBlockCell puzzleBlockCell){
        clearHint();
        int x = position % WIDTH;
        int y = position / WIDTH;

        try {
            switch (puzzleBlockCell.getValue()) {
                case 1: {
                    if (puzzleCellGrid[x][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x][y+1].getColor() == Color.parseColor("#AC9A6A")) {
//                        puzzleCellGrid[x][y - 1].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x][y].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x + 1][y].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x - 1][y].setColor(puzzleBlock1[1].getColor());

                        puzzleCellGrid[x][y].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x][y+1].setColor(puzzleBlockCell.getColor());
                        addPuzzleListener.onAddPuzzleSuccess();
                        checkdestroy(addPuzzleListener);
                    }
                    addPuzzleListener.onAddPuzzleFailed();
                    break;
                }
                case 2: {
                    if (puzzleCellGrid[x][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y + 1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x][y + 1].getColor() == Color.parseColor("#AC9A6A")) {
                        puzzleCellGrid[x][y].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x + 1][y].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x + 1][y + 1].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x][y + 1].setColor(puzzleBlockCell.getColor());
                        addPuzzleListener.onAddPuzzleSuccess();
                        checkdestroy(addPuzzleListener);
                    }
                    addPuzzleListener.onAddPuzzleFailed();
                    break;
                }
                case 3: {
                    if (puzzleCellGrid[x][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x - 1][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y].getColor() == Color.parseColor("#AC9A6A")) {
                        puzzleCellGrid[x][y].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x - 1][y].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x + 1][y].setColor(puzzleBlockCell.getColor());
                        addPuzzleListener.onAddPuzzleSuccess();
                        checkdestroy(addPuzzleListener);
                    }
                    addPuzzleListener.onAddPuzzleFailed();
                    break;
                }
                case 4: {
                    if (puzzleCellGrid[x][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x][y-1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x][y-2].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x][y-3].getColor() == Color.parseColor("#AC9A6A")) {
//                        puzzleCellGrid[x][y - 1].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x][y].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x + 1][y].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x - 1][y].setColor(puzzleBlock1[1].getColor());

                        puzzleCellGrid[x][y].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x][y-1].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x][y-2].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x][y-3].setColor(puzzleBlockCell.getColor());
                        addPuzzleListener.onAddPuzzleSuccess();
                        checkdestroy(addPuzzleListener);
                    }
                    addPuzzleListener.onAddPuzzleFailed();
                    break;
                }
                case 5: {
                    if (puzzleCellGrid[x][y+1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x - 1][y+1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y+1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x - 1][y].getColor() == Color.parseColor("#AC9A6A")) {
                        puzzleCellGrid[x][y+1].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x - 1][y+1].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x + 1][y+1].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x - 1][y].setColor(puzzleBlockCell.getColor());
                        addPuzzleListener.onAddPuzzleSuccess();
                        checkdestroy(addPuzzleListener);
                    }
                    addPuzzleListener.onAddPuzzleFailed();
                    break;
                }
                case 6: {
                    if (puzzleCellGrid[x][y+1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x - 1][y+1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y+1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y].getColor() == Color.parseColor("#AC9A6A")) {
                        puzzleCellGrid[x][y+1].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x - 1][y+1].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x + 1][y+1].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x + 1][y].setColor(puzzleBlockCell.getColor());
                        addPuzzleListener.onAddPuzzleSuccess();
                        checkdestroy(addPuzzleListener);
                    }
                    addPuzzleListener.onAddPuzzleFailed();
                    break;
                }
                case 7: {
                    if (puzzleCellGrid[x][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x][y + 1].getColor() == Color.parseColor("#AC9A6A")) {
                        puzzleCellGrid[x][y].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x + 1][y].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x][y + 1].setColor(puzzleBlockCell.getColor());
                        addPuzzleListener.onAddPuzzleSuccess();
                        checkdestroy(addPuzzleListener);
                    }
                    addPuzzleListener.onAddPuzzleFailed();
                    break;
                }
                case 8: {
                    if (puzzleCellGrid[x][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y + 1].getColor() == Color.parseColor("#AC9A6A")) {
                        puzzleCellGrid[x][y].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x + 1][y].setColor(puzzleBlockCell.getColor());
                        puzzleCellGrid[x + 1][y + 1].setColor(puzzleBlockCell.getColor());
                        addPuzzleListener.onAddPuzzleSuccess();
                        checkdestroy(addPuzzleListener);
                    }
                    addPuzzleListener.onAddPuzzleFailed();
                    break;
                }
                case 9: {
                    if (puzzleCellGrid[x][y].getColor() == Color.parseColor("#AC9A6A")) {
//                        puzzleCellGrid[x][y - 1].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x][y].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x + 1][y].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x - 1][y].setColor(puzzleBlock1[1].getColor());

                        puzzleCellGrid[x][y].setColor(puzzleBlockCell.getColor());
                        addPuzzleListener.onAddPuzzleSuccess();
                        checkdestroy(addPuzzleListener);
                    }
                    addPuzzleListener.onAddPuzzleFailed();
                    break;
                }

            }
        }catch (Exception e){
            addPuzzleListener.onAddPuzzleFailed();
        }finally {

        }



    }




    private void checkdestroy(final addPuzzleListener addPuzzleListener) {
        checkount=0;
        checkendcount=1;
        int count=0;
        checkend checkend = new checkend() {
            @Override
            public void checkend() {
                if(checkendcount==checkount*8){
                    checkEnd(addPuzzleListener);
                    Log.d("tesstasnhat", "checkend: ");
                    SharedPrefsUtil.getInstance().saveArray(context,puzzleCellGrid,false);
                }else {
                    checkendcount++;
                }
            }
        };

        for (int i = 0; i <HEIGHT ; i++) {
                if(puzzleCellGrid[0][i].getValue()==1&&
                   puzzleCellGrid[1][i].getValue()==1&&
                   puzzleCellGrid[2][i].getValue()==1&&
                   puzzleCellGrid[3][i].getValue()==1&&
                   puzzleCellGrid[4][i].getValue()==1&&
                   puzzleCellGrid[5][i].getValue()==1&&
                   puzzleCellGrid[6][i].getValue()==1&&
                   puzzleCellGrid[7][i].getValue()==1
                ){
                    count++;
                    checkount++;
                    puzzleCellGrid[0][i].Destroy(checkend);
                    puzzleCellGrid[1][i].Destroy(checkend);
                    puzzleCellGrid[2][i].Destroy(checkend);
                    puzzleCellGrid[3][i].Destroy(checkend);
                    puzzleCellGrid[4][i].Destroy(checkend);
                    puzzleCellGrid[5][i].Destroy(checkend);
                    puzzleCellGrid[6][i].Destroy(checkend);
                    puzzleCellGrid[7][i].Destroy(checkend);
           }
        }
        for (int i = 0; i <WIDTH ; i++) {
            if(puzzleCellGrid[i][0].getValue()==1&&
                    puzzleCellGrid[i][1].getValue()==1&&
                    puzzleCellGrid[i][2].getValue()==1&&
                    puzzleCellGrid[i][3].getValue()==1&&
                    puzzleCellGrid[i][4].getValue()==1&&
                    puzzleCellGrid[i][5].getValue()==1&&
                    puzzleCellGrid[i][6].getValue()==1&&
                    puzzleCellGrid[i][7].getValue()==1
            ){
                Log.d("testtnnhat", "aaaa2");
                count++;
                checkount++;
                puzzleCellGrid[i][0].Destroy(checkend);
                puzzleCellGrid[i][1].Destroy(checkend);
                puzzleCellGrid[i][2].Destroy(checkend);
                puzzleCellGrid[i][3].Destroy(checkend);
                puzzleCellGrid[i][4].Destroy(checkend);
                puzzleCellGrid[i][5].Destroy(checkend);
                puzzleCellGrid[i][6].Destroy(checkend);
                puzzleCellGrid[i][7].Destroy(checkend);
//                puzzleCellGrid[i][8].Destroy(false);
//                puzzleCellGrid[i][9].Destroy(false);
            }
        }
        if(count!=0){
            addPuzzleListener.onScore(count*10);
        }else {
            SharedPrefsUtil.getInstance().saveArray(context,puzzleCellGrid,false);
            checkEnd(addPuzzleListener);
        }
//        SharedPrefsUtil.getInstance().setBlock1(context,block1);
//        SharedPrefsUtil.getInstance().setBlock2(context,block2);
//        SharedPrefsUtil.getInstance().setBlock3(context,block3);

    }
    public void restart(){
        for (int i = 0; i <WIDTH ; i++) {
            for (int j = 0; j <HEIGHT ; j++) {
                puzzleCellGrid[i][j].Destroy();
            }
        }
        SharedPrefsUtil.getInstance().saveArray(context,puzzleCellGrid,true);
//        SharedPrefsUtil.getInstance().setBlock1(context,block1);
//        SharedPrefsUtil.getInstance().setBlock2(context,block2);
//        SharedPrefsUtil.getInstance().setBlock3(context,block3);

    }

    public void findBlock(){
        blockre1=0;
        boolean has1=false;
        boolean has2=false;
        boolean has3=false;
        boolean has4=false;
        boolean has5=false;
        boolean has6=false;
        boolean has7=false;
        boolean has8=false;
        boolean has9=false;

        for (int i = 0; i <WIDTH ; i++) {
            for (int j = 0; j <HEIGHT ; j++) {
                if(puzzleCellGrid[i][j].getValue()!=1){
                    has9=true;
                    try {
                        if(puzzleCellGrid[i][j+1].getValue()!=1){
                            has1=true;
                        }
                    }catch (Exception ignored){}
                    try {
                        if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                puzzleCellGrid[i+1][j].getValue()!=1&&
                                puzzleCellGrid[i+1][j+1].getValue()!=1){
                            has2=true;
                        }
                    }catch (Exception ignored){}
                    try {
                        if(puzzleCellGrid[i+1][j].getValue()!=1&&
                                puzzleCellGrid[i-1][j].getValue()!=1){
                            has3=true;
                        }
                    }catch (Exception ignored){}
                    try {
                        if(puzzleCellGrid[i][j-1].getValue()!=1&&
                                puzzleCellGrid[i][j-2].getValue()!=1&&
                                puzzleCellGrid[i][j-3].getValue()!=1){
                            has4=true;
                        }
                    }catch (Exception ignored){}
                    try {
                        if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                puzzleCellGrid[i+1][j+1].getValue()!=1&&
                                puzzleCellGrid[i+2][j+1].getValue()!=1){
                            has5=true;
                        }
                    }catch (Exception ignored){}
                    try {
                        if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                puzzleCellGrid[i-1][j+1].getValue()!=1&&
                                puzzleCellGrid[i-2][j+1].getValue()!=1){
                            has6=true;
                        }
                    }catch (Exception ignored){}
                    try {
                        if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                puzzleCellGrid[i+1][j].getValue()!=1){
                            has7=true;
                        }
                    }catch (Exception ignored){}
                    try {
                        if(
                                puzzleCellGrid[i+1][j].getValue()!=1&&
                                puzzleCellGrid[i+1][j+1].getValue()!=1){
                            has8=true;
                        }
                    }catch (Exception ignored){}


                }
            }
        }
       switch (new Random().nextInt(9 - 1 + 1) + 1){
           case 1:{
               if(has1){
                   blockre1=1;
               }else if(has7){
                   blockre1=7;
               }else if(has8){
                   blockre1=8;
               }else if(has2){
                   blockre1=2;
               }else if(has3) {
                   blockre1=3;
               }else if(has4) {
                   blockre1=4;
               }else if(has5){
                   blockre1=5;
               }else if(has6){
                   blockre1=6;

               }
               break;
           }
           case 2:{
               if(has2){
                   blockre1=2;
               }else if(has1){
                   blockre1=1;
               }else if(has7){
                   blockre1=7;
               }else if(has8){
                   blockre1=8;
               }else if(has3){
                   blockre1=3;
               }else if(has4) {
                   blockre1=4;
               }else if(has5){
                   blockre1=5;

               }else if(has6){
                   blockre1=6;

               }
               break;
           }
           case 3:{
               if(has3){
                   blockre1=3;
               }else if(has1){
                   blockre1=1;
               }else if(has2){
                   blockre1=2;
               }else if(has7){
                   blockre1=7;
               }else if(has8){
                   blockre1=8;
               }else if(has4) {
                   blockre1=4;
               }else if(has5){
                   blockre1=5;

               }else if(has6){
                   blockre1=6;

               }
               break;
           }
           case 4:{
               if(has4){
                   blockre1=4;
               }else if(has1){
                   blockre1=1;
               }else if(has8){
                   blockre1=8;
               }else if(has7){
                   blockre1=7;
               }else if(has2){
                   blockre1=2;
               }else if(has3) {
                   blockre1=3;
               }else if(has6){
                   blockre1=6;
               }else if(has5){
                   blockre1=5;

               }
               break;
           }
           case 5:{
               if(has5){
                   blockre1=5;
               }else if(has1){
                   blockre1=1;
               }else if(has8){
                   blockre1=8;
               }else if(has7){
                   blockre1=7;
               }else if(has2){
                   blockre1=2;
               }else if(has3) {
                   blockre1=3;
               }else if(has4){
                   blockre1=4;
               }else if(has6){
                   blockre1=6;

               }
               break;
           }
           case 6:{
               if(has6){
                   blockre1=6;
               }else if(has1){
                   blockre1=1;
               }else if(has8){
                   blockre1=8;
               }else if(has7){
                   blockre1=7;
               }else if(has2){
                   blockre1=2;
               }else if(has3) {
                   blockre1=3;
               }else if(has4){
                   blockre1=4;
               }else if(has5){
                   blockre1=5;

               }
               break;
           }
           case 7:{
               if(has7){
                   blockre1=7;
               }else if(has1){
                   blockre1=1;
               }else if(has8){
                   blockre1=8;
               }else if(has2){
                   blockre1=2;
               }else if(has3) {
                   blockre1=3;
               }else if(has4){
                   blockre1=4;
               }else if(has6){
                   blockre1=6;

               }else if(has5){
                   blockre1=5;
               }
               break;
           }
           case 8:{
               if(has8){
                   blockre1=8;
               }else if(has1){
                   blockre1=1;
               }else if(has7){
                   blockre1=7;
               }else if(has2){
                   blockre1=2;
               }else if(has3) {
                   blockre1=3;
               }else if(has4){
                   blockre1=4;
               }else if(has6){
                   blockre1=6;

               }else if(has5){
                   blockre1=5;
               }
               break;
           }
           case 9:{
               if(has9){
                   blockre1=9;
               }
           }
       }

    }
    private void checkEnd(addPuzzleListener addPuzzleListener) {
        int count =0;

        for (int i = 0; i <WIDTH ; i++) {
            for (int j = 0; j <HEIGHT ; j++) {
                if(puzzleCellGrid[i][j].getValue()!=1){

                    if(block1==1){
                        try {
                        if(puzzleCellGrid[i][j+1].getValue()!=1){
                            count++;
                        }
                        }catch (Exception ignored){}
                    }
                    if(block1==2){
                        try {
                            if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                    puzzleCellGrid[i+1][j].getValue()!=1&&
                                    puzzleCellGrid[i+1][j+1].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block1==3){
                        try {
                            if(puzzleCellGrid[i+1][j].getValue()!=1&&
                                    puzzleCellGrid[i-1][j].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block1==4){
                        try {
                            if(puzzleCellGrid[i][j-1].getValue()!=1&&
                                    puzzleCellGrid[i][j-2].getValue()!=1&&
                                    puzzleCellGrid[i][j-3].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block1==5){
                        try {
                            if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                    puzzleCellGrid[i+1][j+1].getValue()!=1&&
                                    puzzleCellGrid[i+2][j+1].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block1==6){
                        try {
                            if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                    puzzleCellGrid[i-1][j+1].getValue()!=1&&
                                    puzzleCellGrid[i-2][j+1].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block1==7){
                        try {
                            if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                    puzzleCellGrid[i+1][j].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block1==8){
                        try {
                            if(
                                    puzzleCellGrid[i+1][j].getValue()!=1&&
                                    puzzleCellGrid[i+1][j+1].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    } if(block1==9){
                        count++;
                    }
                    if(block2==1){
                        try {
                        if(puzzleCellGrid[i][j+1].getValue()!=1){
                            count++;
                        }
                        }catch (Exception ignored){}
                    }
                    if(block2==2){
                        try {
                        if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                puzzleCellGrid[i+1][j].getValue()!=1&&
                                puzzleCellGrid[i+1][j+1].getValue()!=1){
                            count++;
                        }
                        }catch (Exception ignored){}
                    }
                    if(block2==3){
                        try {
                            if(puzzleCellGrid[i+1][j].getValue()!=1&&
                                    puzzleCellGrid[i-1][j].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block2==4){
                        try {
                            if(puzzleCellGrid[i][j-1].getValue()!=1&&
                                    puzzleCellGrid[i][j-2].getValue()!=1&&
                                    puzzleCellGrid[i][j-3].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block2==5){
                        try {
                            if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                    puzzleCellGrid[i+1][j+1].getValue()!=1&&
                                    puzzleCellGrid[i+2][j+1].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block2==6){
                        try {
                            if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                    puzzleCellGrid[i-1][j+1].getValue()!=1&&
                                    puzzleCellGrid[i-2][j+1].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block2==7){
                        try {
                            if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                    puzzleCellGrid[i+1][j].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block2==8){
                        try {
                            if(
                                    puzzleCellGrid[i+1][j].getValue()!=1&&
                                            puzzleCellGrid[i+1][j+1].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    } if(block2==9){
                        count++;
                    }
                    if(block3==1){
                        try {
                        if(puzzleCellGrid[i][j+1].getValue()!=1){
                            count++;
                        }
                        }catch (Exception ignored){}
                    }
                    if(block3==2){
                        try {
                        if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                puzzleCellGrid[i+1][j].getValue()!=1&&
                                puzzleCellGrid[i+1][j+1].getValue()!=1){
                            count++;
                        }
                        }catch (Exception ignored){}
                    }
                    if(block3==3){
                        try {
                            if(puzzleCellGrid[i+1][j].getValue()!=1&&
                                    puzzleCellGrid[i-1][j].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block3==4){
                        try {
                            if(puzzleCellGrid[i][j-1].getValue()!=1&&
                                    puzzleCellGrid[i][j-2].getValue()!=1&&
                                    puzzleCellGrid[i][j-3].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block3==5){
                        try {
                            if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                    puzzleCellGrid[i+1][j+1].getValue()!=1&&
                                    puzzleCellGrid[i+2][j+1].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block3==6){
                        try {
                            if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                    puzzleCellGrid[i-1][j+1].getValue()!=1&&
                                    puzzleCellGrid[i-2][j+1].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block3==7){
                        try {
                            if(puzzleCellGrid[i][j+1].getValue()!=1&&
                                    puzzleCellGrid[i+1][j].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    }
                    if(block3==8){
                        try {
                            if(
                                    puzzleCellGrid[i+1][j].getValue()!=1&&
                                            puzzleCellGrid[i+1][j+1].getValue()!=1){
                                count++;
                            }
                        }catch (Exception ignored){}
                    } if(block3==9){
                        count++;
                    }

                }
            }
        }
        if(count==0){
            addPuzzleListener.onLose();
            SharedPrefsUtil.getInstance().setScore(context,0);
            SharedPrefsUtil.getInstance().setBlock1(context,0);
            SharedPrefsUtil.getInstance().setBlock2(context,0);
            SharedPrefsUtil.getInstance().setBlock3(context,0);
            SharedPrefsUtil.getInstance().saveArray(context,puzzleCellGrid,true);
        }

    }

    //    public void addPuzzle(int x,int y){
//        puzzleCellGrid[x][y].setColor(Color.RED);
//        puzzleCellGrid[x-1][y-1].setColor(Color.RED);
//        puzzleCellGrid[x][y-1].setColor(Color.RED);
//        puzzleCellGrid[x+1][y-1].setColor(Color.RED);
//
//    }
    public PuzzleCell getBlockat(int position,PuzzleCell[] puzzleBlock1) {
        int x = position % 5;
        int y = position / 5;

        return puzzleBlock1[position];
    }
    public  int adjustAlpha(@ColorInt int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    public void showHint(int position,PuzzleBlockCell puzzleBlockCell){
        int x = position % WIDTH;
        int y = position / WIDTH;

        try {
            switch (puzzleBlockCell.getValue()) {
                case 1: {

                    clearHint();
                    if (puzzleCellGrid[x][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x][y+1].getColor() == Color.parseColor("#AC9A6A")) {
//                        puzzleCellGrid[x][y - 1].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x][y].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x + 1][y].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x - 1][y].setColor(puzzleBlock1[1].getColor());

                        puzzleCellGrid[x][y].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x][y+1].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                    }
                    break;
                }
                case 2: {
                    clearHint();
                    if (puzzleCellGrid[x][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y + 1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x][y + 1].getColor() == Color.parseColor("#AC9A6A")) {

                        puzzleCellGrid[x][y].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x + 1][y].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x + 1][y + 1].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x][y + 1].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));

                    }
                    break;
                }
                case 3: {
                    clearHint();
                    if (puzzleCellGrid[x][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x - 1][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y].getColor() == Color.parseColor("#AC9A6A")) {
                        puzzleCellGrid[x][y].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x - 1][y].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x + 1][y].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                    }
                    break;
                }
                case 4: {

                    clearHint();
                    if (puzzleCellGrid[x][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x][y-1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x][y-2].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x][y-3].getColor() == Color.parseColor("#AC9A6A")) {
//                        puzzleCellGrid[x][y - 1].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x][y].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x + 1][y].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x - 1][y].setColor(puzzleBlock1[1].getColor());

                        puzzleCellGrid[x][y].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x][y-1].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x][y-2].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x][y-3].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                    }
                    break;
                }
                case 5: {
                    clearHint();
                    if (puzzleCellGrid[x][y+1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x - 1][y+1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y+1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x - 1][y].getColor() == Color.parseColor("#AC9A6A")) {
                        puzzleCellGrid[x][y+1].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x - 1][y+1].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x + 1][y+1].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x - 1][y].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                    }
                    break;
                }
                case 6: {
                    clearHint();
                    if (puzzleCellGrid[x][y+1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x - 1][y+1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y+1].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y].getColor() == Color.parseColor("#AC9A6A")) {
                        puzzleCellGrid[x][y+1].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x - 1][y+1].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x + 1][y+1].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x + 1][y].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                    }
                    break;
                }
                case 7: {
                    clearHint();
                    if (puzzleCellGrid[x][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x][y + 1].getColor() == Color.parseColor("#AC9A6A")) {

                        puzzleCellGrid[x][y].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x + 1][y].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x][y + 1].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));

                    }
                    break;
                }
                case 8: {
                    clearHint();
                    if (puzzleCellGrid[x][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y].getColor() == Color.parseColor("#AC9A6A")
                            && puzzleCellGrid[x + 1][y + 1].getColor() == Color.parseColor("#AC9A6A")
                            ) {

                        puzzleCellGrid[x][y].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x + 1][y].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                        puzzleCellGrid[x + 1][y + 1].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));

                    }
                    break;
                }
                case 9: {

                    clearHint();
                    if (puzzleCellGrid[x][y].getColor() == Color.parseColor("#AC9A6A")
                           ) {
//                        puzzleCellGrid[x][y - 1].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x][y].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x + 1][y].setColor(puzzleBlock1[1].getColor());
//                        puzzleCellGrid[x - 1][y].setColor(puzzleBlock1[1].getColor());

                        puzzleCellGrid[x][y].setColorHint(adjustAlpha(puzzleBlockCell.getColor(),0.5f));
                    }
                    break;
                }


            }
        }catch (Exception e){
            clearHint();
        }



    }

    public void clearHint() {
        for (int i = 0; i <HEIGHT ; i++) {
            for (int j = 0; j <WIDTH ; j++) {
                if(puzzleCellGrid[i][j].getValue()==100){
                    puzzleCellGrid[i][j].clearColorHint();
                }
            }
        }
    }


    public interface addPuzzleListener{
        void onAddPuzzleSuccess();
        void onAddPuzzleFailed();
        void onScore(int score);
        void onLose();
    }
    public interface gameRestartListener{
        void onCompleted();
    }
    public interface checkend{
        void checkend();
    }

}

