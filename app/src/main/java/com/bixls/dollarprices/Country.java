package com.bixls.dollarprices;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Created by Ahmed on 3/1/2015.
 */
public class Country {
       String Code;
      String Name;
     double Value;
      String CurFull;
      String CurShort;
       Drawable Flag;

    Country(String code,String name, String curFull, String curShort, Drawable flag)
    {
        Code=code;
        Name=name;
        CurFull=curFull;
        CurShort=curShort;
        Flag=flag;
    }
    public void SetCurrency(String value)
    {
        Value=Double.parseDouble(value);
    }
    public double GetCurrency()
    {
        return Value;
    }

}
