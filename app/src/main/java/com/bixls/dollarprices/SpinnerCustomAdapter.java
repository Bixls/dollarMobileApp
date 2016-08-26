package com.bixls.dollarprices;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ahmed on 3/9/2015.
 */
public class SpinnerCustomAdapter  extends ArrayAdapter<SpinnerItem> {

    Context mContext;
    int layoutResourceId;
    ArrayList<SpinnerItem> spinnerItemArrayList = null;
    SpinnerItem spinnerItem=null;
    LayoutInflater inflater;

    public SpinnerCustomAdapter(Context mContext, int layoutResourceId, ArrayList<SpinnerItem> spinnerItems) {
        super(mContext, layoutResourceId, spinnerItems);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.spinnerItemArrayList = spinnerItems;

        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomViewFirst(position, convertView, parent);
    }

    public View getCustomViewFirst(int position, View convertView, ViewGroup parent) {

        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = inflater.inflate(R.layout.spiner_item, parent, false);

        /***** Get each Model object from Arraylist ********/
        spinnerItem=null;
        spinnerItem = (SpinnerItem) spinnerItemArrayList.get(position);

        TextView Name=(TextView) row.findViewById(R.id.Name);
        ImageView flag= (ImageView)  row.findViewById(R.id.Flag);
        Name.setText(spinnerItem.Name);
        flag.setImageDrawable(spinnerItem.Flag);

        return row;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = inflater.inflate(R.layout.spiner_item, parent, false);

        /***** Get each Model object from Arraylist ********/
        spinnerItem=null;
        spinnerItem = (SpinnerItem) spinnerItemArrayList.get(position);

        TextView Name=(TextView) row.findViewById(R.id.Name);
        ImageView flag= (ImageView)  row.findViewById(R.id.Flag);
        Name.setText(spinnerItem.Name);
        flag.setImageDrawable(spinnerItem.Flag);

        return row;
    }
}



