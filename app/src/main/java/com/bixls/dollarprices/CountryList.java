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
                res.getDrawable(R.drawable.usd)
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



        countries.add(new Country(
                "DJF",
                res.getString(R.string.DJF),
                res.getString(R.string.DJF_Full),
                res.getString(R.string.DJF_short),
                res.getDrawable(R.drawable.dj)
        ));
        countries.add(new Country(
                "IQD",
                res.getString(R.string.IQD),
                res.getString(R.string.IQD_Full),
                res.getString(R.string.IQD_short),
                res.getDrawable(R.drawable.iq)
        ));
        countries.add(new Country(
                "MAD",
                res.getString(R.string.MAD),
                res.getString(R.string.MAD_Full),
                res.getString(R.string.MAD_short),
                res.getDrawable(R.drawable.mad)
        ));
        countries.add(new Country(
                "OMR",
                res.getString(R.string.OMR),
                res.getString(R.string.OMR_Full),
                res.getString(R.string.OMR_short),
                res.getDrawable(R.drawable.omr)
        ));
        countries.add(new Country(
                "SDG",
                res.getString(R.string.SDG),
                res.getString(R.string.SDG_Full),
                res.getString(R.string.SDG_short),
                res.getDrawable(R.drawable.sdg)
        ));
        countries.add(new Country(
                "SOS",
                res.getString(R.string.SOS),
                res.getString(R.string.SOS_Full),
                res.getString(R.string.SOS_short),
                res.getDrawable(R.drawable.sos)
        ));



        countries.add(new Country(
                "TRY",
                res.getString(R.string.TRY),
                res.getString(R.string.TRY_Full),
                res.getString(R.string.TRY_short),
                res.getDrawable(R.drawable.tr)
        ));
        countries.add(new Country(
                "RON",
                res.getString(R.string.RON),
                res.getString(R.string.RON_Full),
                res.getString(R.string.RON_short),
                res.getDrawable(R.drawable.ro)
        ));
        countries.add(new Country(
                "GBP",
                res.getString(R.string.GBP),
                res.getString(R.string.GBP_Full),
                res.getString(R.string.GBP_short),
                res.getDrawable(R.drawable.gb)
        ));
        countries.add(new Country(
                "EUR",
                res.getString(R.string.EUR),
                res.getString(R.string.EUR_Full),
                res.getString(R.string.EUR_short),
                res.getDrawable(R.drawable.eu)
        ));
        countries.add(new Country(
                "DEM",
                res.getString(R.string.DEM),
                res.getString(R.string.DEM_Full),
                res.getString(R.string.DEM_short),
                res.getDrawable(R.drawable.de)
        ));
        countries.add(new Country(
                "ILS",
                res.getString(R.string.ILS),
                res.getString(R.string.ILS_Full),
                res.getString(R.string.ILS_short),
                res.getDrawable(R.drawable.il)
        ));
        countries.add(new Country(
                "ITL",
                res.getString(R.string.ITL),
                res.getString(R.string.ITL_Full),
                res.getString(R.string.ITL_short),
                res.getDrawable(R.drawable.it)
        ));

        countries.add(new Country(
                "RUB",
                res.getString(R.string.RUB),
                res.getString(R.string.RUB_Full),
                res.getString(R.string.RUB_short),
                res.getDrawable(R.drawable.ru)
        ));
        countries.add(new Country(
                "SZL",
                res.getString(R.string.SZL),
                res.getString(R.string.SZL_Full),
                res.getString(R.string.SZL_short),
                res.getDrawable(R.drawable.se)
        ));
        countries.add(new Country(
                "ANG",
                res.getString(R.string.ANG),
                res.getString(R.string.ANG_Full),
                res.getString(R.string.ANG_short),
                res.getDrawable(R.drawable.nl)
        ));
        countries.add(new Country(
                "CNY",
                res.getString(R.string.CNY),
                res.getString(R.string.CNY_Full),
                res.getString(R.string.CNY_short),
                res.getDrawable(R.drawable.cn)
        ));

        countries.add(new Country(
                "NOK",
                res.getString(R.string.NOK),
                res.getString(R.string.NOK_Full),
                res.getString(R.string.NOK_short),
                res.getDrawable(R.drawable.no)
        ));
        return countries;
    }
}
