package com.nstudio.puzzleblockfreesimple.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class GameOverTextView extends TextView {
	public GameOverTextView(Context context) {
		super(context);
		setFont();
	}
	public GameOverTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFont();
	}
	public GameOverTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setFont();
	}

	private void setFont() {
		Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/PressStart2P.ttf");
		setTypeface(font, Typeface.NORMAL);
	}
}