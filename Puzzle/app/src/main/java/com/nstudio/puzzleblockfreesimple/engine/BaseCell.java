package com.nstudio.puzzleblockfreesimple.engine;

import android.content.Context;
import android.view.View;

/**
 * Created by Nhatran241 on 18/4/2019
 */
public abstract class BaseCell extends View {

    private int value;


    public BaseCell(Context context ){
        super(context);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
