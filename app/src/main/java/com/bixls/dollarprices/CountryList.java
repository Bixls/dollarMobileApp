package com.bixls.dollarprices;

import android.content.res.Resources;

import java.util.ArrayList;

/**
 * Created by Ahmed on 3/2/2015.
 */
public class CountryList {

  public   ArrayList<Country>  init(Resources res)
    {
    ArrayList<Country> countries=new ArrayList<Country>();
        countries.add(new Country(
                "USD",
                res.getString(R.string.USD),
                res.getString(R.string.USD_Full),
                res.getString(R.string.USD_short),
                res.getDrawable(R.drawable.egp)
        ));
    countries.add(new Country(
            "EGP",
            res.getString(R.string.EGP),
            res.getString(R.string.EGP_Full),
            res.getString(R.string.EGP_short),
            res.getDrawable(R.drawable.egp)
    ));
        countries.add(new Country(
                "SAR",
                res.getString(R.string.SAR),
                res.getString(R.string.SAR_Full),
                res.getString(R.string.SAR_short),
                res.getDrawable(R.drawable.sar)
        ));
    countries.add(new Country(
            "KWD",
            res.getString(R.string.KWD),
            res.getString(R.string.KWD_Full),
            res.getString(R.string.KWD_short),
            res.getDrawable(R.drawable.kwd)
    ));

        return countries;
    }
}
