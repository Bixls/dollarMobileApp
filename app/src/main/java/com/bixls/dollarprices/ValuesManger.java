package com.bixls.dollarprices;

import java.util.HashMap;

/**
 * Created by Ahmed on 3/1/2015.
 */
public class ValuesManger {


    public   HashMap<String, Double> getValues(){
        HashMap<String, Double> Values = new HashMap<String, Double>();
        Values.put("EGP",7.5);
        Values.put("KWD",6.0);
        return Values;
    }
}
