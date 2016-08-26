package com.bixls.dollarprices;

import android.graphics.drawable.Drawable;

/**
 * Created by Ahmed on 3/17/2015.
 */

    public class SpinnerItemHome {
        public String CurLong;
        public String Value;
        public String Curshort;
        public Drawable Flag;

        public SpinnerItemHome(String curLong,String value,String curShort,Drawable flag) {
            this.CurLong=curLong;
            this.Flag=flag;
            this.Curshort=curShort;
            this.Value=value;



        }

    }

