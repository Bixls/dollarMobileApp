package com.bixls.dollarprices;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ahmed on 3/17/2015.
 */
public class viewCountryadapter extends ArrayAdapter<SpinnerItemHome> {
    Context mContext;
    int layoutResourceId;
    ArrayList<SpinnerItemHome> spinnerItemArrayList = null;

    public viewCountryadapter(Context mContext, int layoutResourceId, ArrayList<SpinnerItemHome> spinnerItems) {
        super(mContext, layoutResourceId, spinnerItems);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.spinnerItemArrayList = spinnerItems;
    }

    @Override
    public View getView(int position, final View convertView, android.view.ViewGroup parent) {

        View listItem = convertView;

        final SpinnerItemHome spinnerItem = spinnerItemArrayList.get(position);

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        TextView Name=(TextView) listItem.findViewById(R.id.Name);
        ImageView flag= (ImageView)  listItem.findViewById(R.id.Flag);
        TextView value=(TextView) listItem.findViewById(R.id.Value);

        Name.setText(spinnerItem.CurLong);
        flag.setImageDrawable(spinnerItem.Flag);
        value.setText(spinnerItem.Value);

        return listItem;
    }


}
