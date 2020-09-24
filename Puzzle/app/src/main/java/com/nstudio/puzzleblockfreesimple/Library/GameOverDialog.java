package com.nstudio.puzzleblockfreesimple.Library;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.nstudio.puzzleblockfreesimple.R;
import com.nstudio.puzzleblockfreesimple.Utils.GameOverTextView;

/**
 * Created by Shashank Singhal on 06/01/2018.
 */

public class GameOverDialog {
    public static class Builder {
        private Context activity;
        private int score;
        private int highscore;
        private GameOverListener sListener, reListener ,rListener,onshow;

        public Builder(Context activity) {
            this.activity = activity;
        }


        public GameOverDialog.Builder OnReplay(GameOverListener reListener) {
            this.reListener = reListener;
            return this;
        }
        public GameOverDialog.Builder OnShow(GameOverListener onshow) {
            this.onshow = onshow;
            return this;
        }

        public GameOverDialog.Builder OnRate(GameOverListener rListener) {
            this.rListener = rListener;
            return this;
        }
        public GameOverDialog.Builder OnShare(GameOverListener sListener) {
            this.sListener = sListener;
            return this;
        }
        public GameOverDialog.Builder SetScore(int score) {
            this.score = score;
            return this;
        }
        public GameOverDialog.Builder SetHScore(int highscore) {
            this.highscore = highscore;
            return this;
        }


        public MenuDialog build() {
            RelativeLayout btnshare, btnreplay,btnrate;
            GameOverTextView high,your;
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(R.layout.gameoverdialog);

            btnrate = dialog.findViewById(R.id.rl_rate);
            btnshare = dialog.findViewById(R.id.rl_share);
            btnreplay = dialog.findViewById(R.id.rl_replay);
            high = dialog.findViewById(R.id.tv_highscore);
            your = dialog.findViewById(R.id.tv_yourscore);

            high.setText(highscore+"");
            your.setText(score+"");

            btnrate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rListener != null) rListener.OnClick(dialog);
                }

            });
            btnreplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (reListener != null) reListener.OnClick(dialog);
                }

            });
            btnshare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sListener != null) sListener.OnClick(dialog);
                }

            });





            dialog.show();

            return new MenuDialog();

        }
    }

}
