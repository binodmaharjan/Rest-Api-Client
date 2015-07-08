package com.maharjan.binod;

import android.app.Application;

import com.maharjan.binod.restapiclient.apicontroller.ConnectionOptions;

/**
 * Created by binod on 6/14/15.
 */
public class AppController extends Application {



    @Override
    public void onCreate() {
        super.onCreate();

         final ConnectionOptions options=new ConnectionOptions.Builder("Application/json")
                .setConnectionTime(10*1000)
                .addLanguage("en-Us")
                .build();


    }
}
