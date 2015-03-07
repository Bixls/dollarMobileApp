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

        countries.add(new Country(
                "QAR",
                res.getString(R.string.QAR),
                res.getString(R.string.QAR_Full),
                res.getString(R.string.QAR_short),
                res.getDrawable(R.drawable.qar)
        ));
        countries.add(new Country(
                "JOD",
                res.getString(R.string.JOD),
                res.getString(R.string.JOD_Full),
                res.getString(R.string.JOD_short),
                res.getDrawable(R.drawable.jod)
        ));
        countries.add(new Country(
                "BHD",
                res.getString(R.string.BHD),
                res.getString(R.string.BHD_Full),
                res.getString(R.string.BHD_short),
                res.getDrawable(R.drawable.bhd)
        ));


        countries.add(new Country(
                "AED",
                res.getString(R.string.AED),
                res.getString(R.string.AED_Full),
                res.getString(R.string.AED_short),
                res.getDrawable(R.drawable.aed)
        ));
        countries.add(new Country(
                "LBP",
                res.getString(R.string.LBP),
                res.getString(R.string.LBP_Full),
                res.getString(R.string.LBP_short),
                res.getDrawable(R.drawable.lbp)
        ));
        countries.add(new Country(
                "LYD",
                res.getString(R.string.LYD),
                res.getString(R.string.LYD_Full),
                res.getString(R.string.LYD_short),
                res.getDrawable(R.drawable.lyd)
        ));
        countries.add(new Country(
                "TND",
                res.getString(R.string.TND),
                res.getString(R.string.TND_Full),
                res.getString(R.string.TND_short),
                res.getDrawable(R.drawable.tnd)
        ));
        countries.add(new Country(
                "YER",
                res.getString(R.string.YER),
                res.getString(R.string.YER_Full),
                res.getString(R.string.YER_short),
                res.getDrawable(R.drawable.yer)
        ));
        countries.add(new Country(
                "SYP",
                res.getString(R.string.SYP),
                res.getString(R.string.SYP_Full),
                res.getString(R.string.SYP_short),
                res.getDrawable(R.drawable.syp)
        ));
        countries.add(new Country(
                "DZD",
                res.getString(R.string.DZD),
                res.getString(R.string.DZD_Full),
                res.getString(R.string.DZD_short),
                res.getDrawable(R.drawable.dzd)
        ));

        return countries;
    }
}
