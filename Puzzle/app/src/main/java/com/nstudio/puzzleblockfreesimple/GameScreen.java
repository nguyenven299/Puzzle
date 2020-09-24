package com.nstudio.puzzleblockfreesimple;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.nstudio.puzzleblockfreesimple.Library.MenuDialog;
import com.nstudio.puzzleblockfreesimple.Library.MenuListener;
import com.nstudio.puzzleblockfreesimple.Library.SplashScreenDialog;
import com.nstudio.puzzleblockfreesimple.Library.SplashScreenListener;
import com.nstudio.puzzleblockfreesimple.Utils.SharedPrefsUtil;
import com.nstudio.puzzleblockfreesimple.engine.GameView;

public class GameScreen extends AppCompatActivity {
    GameView gameView;
    AdView mAdView;
    MenuDialog menuDialog;
    SplashScreenDialog splashScreenDialog;
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameView = findViewById(R.id.gameview);
        mInterstitialAd = new InterstitialAd(this);
//        if(SharedPrefsUtil.getInstance().getFirsttime(this)){
//            SharedPrefsUtil.getInstance().setFirsttime(this,false);
//        }else {
//            if(System.currentTimeMillis()-SharedPrefsUtil.getInstance().getTime(this)>1000*60){
//
//                mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//                mInterstitialAd.loadAd(new AdRequest.Builder().build());
//            }
//
//        }
        splashScreenDialog = new SplashScreenDialog.Builder(this).SetAds(mInterstitialAd).SetListener(new SplashScreenListener() {
            @Override
            public void OnFinish() {

            }
        }).build();
        SharedPrefsUtil.getInstance().setTime(this,System.currentTimeMillis());



    }

    public void onMenuClick(View view) {
        menuDialog =new MenuDialog.Builder(this)
                .OnContinue(new MenuListener() {
                    @Override
                    public void OnClick() {

                    }
                })
                .OnReplay(new MenuListener() {
                    @Override
                    public void OnClick() {
                        gameView.restart();
                    }
                })
                .OnRate(new MenuListener() {
                    @Override
                    public void OnClick() {
                        Uri uri = Uri.parse("market://details?id=" + getPackageName());
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add following flags to intent.
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        try {
                            startActivity(goToMarket);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" +getPackageName())));
                        }
                    }
                })
                .OnShare(new MenuListener() {
                    @Override
                    public void OnClick() {
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "Try this game";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                    }
                })
                .build();
    }

    @Override
    protected void onDestroy() {
        Log.d("nhatssss", "onDestroy: ");
        SharedPrefsUtil.getInstance().setTime(this,System.currentTimeMillis());
        super.onDestroy();
    }
}
