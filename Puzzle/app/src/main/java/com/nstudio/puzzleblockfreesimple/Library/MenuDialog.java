package com.nstudio.puzzleblockfreesimple.Library;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.nstudio.puzzleblockfreesimple.R;

/**
 * Created by Shashank Singhal on 06/01/2018.
 */

public class MenuDialog {
    public static class Builder {
        private Activity activity;
        private MenuListener cListener, reListener ,rListener,sListener;

        public Builder(Activity activity) {
            this.activity = activity;
        }


        public Builder OnContinue(MenuListener cListener) {
            this.cListener = cListener;
            return this;
        }

        public Builder OnReplay(MenuListener reListener) {
            this.reListener = reListener;
            return this;
        }
        public Builder OnRate(MenuListener rListener) {
            this.rListener = rListener;
            return this;
        }
        public Builder OnShare(MenuListener sListener) {
            this.sListener = sListener;
            return this;
        }


        public MenuDialog build() {
            RelativeLayout btnCon, btnRe,btnR,btnS;
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.menudialog);

            btnCon= dialog.findViewById(R.id.rl_continue);
            btnRe= dialog.findViewById(R.id.rl_replay);
            btnR= dialog.findViewById(R.id.rl_rate);
            btnS= dialog.findViewById(R.id.rl_share);
//            //getting resources
//            btnCon = dialog.findViewById(R.id.btn_continue);
//            btnNew = dialog.findViewById(R.id.btn_newgame);
//            btnMenu = dialog.findViewById(R.id.btn_menu);
//            btnCon.setText("Continue");
//            btnNew.setText("New Game");
//            btnMenu.setText("Menu");
            btnCon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (cListener != null) cListener.OnClick();
                        dialog.dismiss();
                    }

                });
            btnRe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (reListener != null) reListener.OnClick();
                    dialog.dismiss();
                }

            });
            btnR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rListener != null) rListener.OnClick();
                }

            });
            btnS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sListener != null) sListener.OnClick();
                }

            });


            dialog.show();

            return new MenuDialog();

        }
    }

}
