package com.maharjan.binod.restapiclient.controller;

/**
 * Created by G6 on 7/7/2015.
 */
public interface FutureCallback<T> {
    void onComplete(Exception e, T result);
}
