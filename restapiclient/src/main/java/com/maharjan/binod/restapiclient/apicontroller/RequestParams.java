package com.maharjan.binod.restapiclient.apicontroller;

import java.util.HashMap;

/**
 * Created by binod on 6/14/15.
 */
public class RequestParams <K,V> extends HashMap {

    public RequestParams() {

    }
    public void add(K firstPair,V secondPair){
        super.put(firstPair,secondPair);
    }
}
