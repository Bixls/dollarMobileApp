package com.bixls.dollarprices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Ahmed on 3/17/2015.
 */

public class SpinnerHomepageAdapter  extends ArrayAdapter<SpinnerItemHome> {

    Context mContext;
    int layoutResourceId;
    ArrayList<SpinnerItemHome> spinnerItemArrayList = null;
    SpinnerItemHome spinnerItem=null;
    LayoutInflater inflater;

    public SpinnerHomepageAdapter(Context mContext, int layoutResourceId, ArrayList<SpinnerItemHome> spinnerItems) {
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
        View row = inflater.inflate(R.layout.spinerhomepage, parent, false);

        /***** Get each Model object from Arraylist ********/
        spinnerItem=null;
        spinnerItem = (SpinnerItemHome) spinnerItemArrayList.get(position);

        TextView Name=(TextView) row.findViewById(R.id.Name);
        TextView Value=(TextView) row.findViewById(R.id.Value);
        ImageView flag= (ImageView)  row.findViewById(R.id.Flag);
        Name.setText(spinnerItem.Curshort);
        flag.setImageDrawable(spinnerItem.Flag);





        Value.setText(spinnerItem.Value);
        return row;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = inflater.inflate(R.layout.spiner_item, parent, false);

        /***** Get each Model object from Arraylist ********/
        spinnerItem=null;
        spinnerItem = (SpinnerItemHome) spinnerItemArrayList.get(position);

        TextView Name=(TextView) row.findViewById(R.id.Name);
        ImageView flag= (ImageView)  row.findViewById(R.id.Flag);
        Name.setText(spinnerItem.CurLong);
        flag.setImageDrawable(spinnerItem.Flag);

        return row;
    }
}



