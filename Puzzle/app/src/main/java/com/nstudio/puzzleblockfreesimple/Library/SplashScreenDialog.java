package com.nstudio.puzzleblockfreesimple.Library;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.nstudio.puzzleblockfreesimple.R;
import com.nstudio.puzzleblockfreesimple.Utils.GameOverTextView;
import com.nstudio.puzzleblockfreesimple.engine.PuzzleBlock;

/**
 * Created by Shashank Singhal on 06/01/2018.
 */

public class SplashScreenDialog {
    public static class Builder {
        private Context activity;
        private SplashScreenListener splashScreenListener;
        private InterstitialAd interstitialAd;

        public Builder(Context activity) {
            this.activity = activity;
        }
        public SplashScreenDialog.Builder SetListener(SplashScreenListener splashScreenListener) {
            this.splashScreenListener = splashScreenListener;
            return this;
        }
        public SplashScreenDialog.Builder SetAds(InterstitialAd interstitialAd) {
            this.interstitialAd = interstitialAd;
            return this;
        }



        public SplashScreenDialog build() {
            final PuzzleBlock pb1,pb2,pb3,pb4;
            final GameOverTextView percent;
            final Dialog dialog = new Dialog(activity,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setContentView(R.layout.splashscreendialog);

            pb1=dialog.findViewById(R.id.pb1);
            pb2=dialog.findViewById(R.id.pb2);
            pb3=dialog.findViewById(R.id.pb3);
            pb4=dialog.findViewById(R.id.pb4);
            percent =dialog.findViewById(R.id.percent);
            interstitialAd.setAdListener(new AdListener()
            {
                @Override
                public void onAdClosed() {
                    dialog.dismiss();
                }
            });

            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(final DialogInterface dialog) {
                    pb1.animate().rotation(360).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            percent.setText("25%");
                            if(interstitialAd.isLoaded()){
                                interstitialAd.show();
                            }else {
                                pb2.animate().rotation(360).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        percent.setText("50%");
                                        if(interstitialAd.isLoaded()){
                                            interstitialAd.show();
                                        }else {
                                            pb3.animate().rotation(360).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    super.onAnimationEnd(animation);
                                                    percent.setText("75%");
                                                    if(interstitialAd.isLoaded()){
                                                        interstitialAd.show();
                                                    }else {
                                                        pb4.animate().rotation(360).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                                                            @Override
                                                            public void onAnimationEnd(Animator animation) {
                                                                super.onAnimationEnd(animation);
                                                                percent.setText("100%");
                                                                if(interstitialAd.isLoaded()){
                                                                    interstitialAd.show();
                                                                }else {
                                                                    splashScreenListener.OnFinish();
                                                                    dialog.dismiss();
                                                                }
                                                            }
                                                        }).start();
                                                    }
                                                }
                                            }).start();
                                        }
                                    }
                                }).start();
                            }
                        }
                    }).start();
                }
            });

            dialog.show();

            return new SplashScreenDialog();

        }
    }

}
