package com.maharjan.binod.restapiclient.apicontroller;

import android.util.Log;

public class AppLog {

    private final static String TAG = "Async Task Rest client";


    public static void logString(String tag, String message)
    {
        Log.i(tag, message);
    }


    public static void logString( String message)
    {
         Log.i(TAG, message);
    }


    public static void logError(String tag, Exception e)
    {
         Log.e(tag, e.getMessage());
    }


    public static void logError(Exception e)
    {
         Log.e(TAG, e.getMessage());
    }



}
