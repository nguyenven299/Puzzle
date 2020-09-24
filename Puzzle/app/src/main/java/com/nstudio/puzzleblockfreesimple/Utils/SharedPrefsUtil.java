package com.nstudio.puzzleblockfreesimple.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nstudio.puzzleblockfreesimple.engine.PuzzleCell;

public class SharedPrefsUtil {

    private static SharedPrefsUtil instance;
    private static final String PREFS_NAME = "default_preferences";

    public synchronized static SharedPrefsUtil getInstance() {
        if (instance == null) {
            instance = new SharedPrefsUtil();
        }
        return instance;
    }

    private SharedPrefsUtil() {
    }

    public boolean getFirsttime(@NonNull Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getBoolean("Firsttime", true);
    }

    public void setFirsttime(@NonNull Context context, boolean value) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit().putBoolean("Firsttime", value).apply();
    }

    public int getHighScore(@NonNull Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getInt("HighScore", 0);
    }

    public void setHighScore(@NonNull Context context, int value) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit().putInt("HighScore", value).apply();
    }

    public int getScore(@NonNull Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getInt("Score", 0);
    }

    public void setScore(@NonNull Context context, int value) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit().putInt("Score", value).apply();
    }
    public long getTime(@NonNull Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getLong("Time", 0);
    }

    public void setTime(@NonNull Context context, long value) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit().putLong("Time", value).apply();
    }

    public boolean saveArray(Context context,PuzzleCell[][] va,boolean lose)
    {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor mEdit1 = sp.edit();

        for(int i=0;i<8;i++)
        {
            for (int j = 0; j <8 ; j++) {
                if(lose) {
                    mEdit1.putInt("Status_" + i + j,Color.parseColor("#AC9A6A"));
                }else {

                    mEdit1.putInt("Status_" + i + j, va[i][j].getColor());
                }
            }
        }

        return mEdit1.commit();
    }
    public int[][] loadArray(Context mContext)
    {
        SharedPreferences mSharedPreference1 =   PreferenceManager.getDefaultSharedPreferences(mContext);
        int[][] va = new int[8][8];
        for(int i=0;i<8;i++)
        {
            for (int j = 0; j < 8; j++) {
                va[i][j]=mSharedPreference1.getInt("Status_" + i+j, Color.parseColor("#AC9A6A"));
                Log.d("tesssad123", i+"I"+j+"I"+va[i][j]);
            }
        }
        return va;

    }

    public void setBlock1(Context context,int v){
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit().putInt("block1", v).apply();
    }
    public int getBlock1(Context context){
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getInt("block1", 0);
    }

    public void setBlock2(Context context,int v){
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit().putInt("block2", v).apply();
    }
    public int getBlock2(Context context){
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getInt("block2", 0);
    }
    public void setBlock3(Context context,int v){
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit().putInt("block3", v).apply();
    }
    public int getBlock3(Context context){
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getInt("block3", 0);
    }


}