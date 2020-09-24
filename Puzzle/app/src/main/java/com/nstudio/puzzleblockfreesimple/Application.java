package com.nstudio.puzzleblockfreesimple;

import com.google.android.gms.ads.MobileAds;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this,
                "ca-app-pub-2116449996149867~9559797305");

    }
}
