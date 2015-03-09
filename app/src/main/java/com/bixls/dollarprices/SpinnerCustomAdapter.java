package com.bixls.dollarprices;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ahmed on 3/9/2015.
 */
public class SpinnerCustomAdapter extends ArrayAdapter<SpinnerItem> {
    Context mContext;
    int layoutResourceId;
    ArrayList<SpinnerItem> spinnerItemArrayList = null;

    public SpinnerCustomAdapter(Context mContext, int layoutResourceId, ArrayList<SpinnerItem> spinnerItems) {
        super(mContext, layoutResourceId, spinnerItems);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.spinnerItemArrayList = spinnerItems;
    }

    @Override
    public View getView(int position, final View convertView, android.view.ViewGroup parent) {

        View listItem = convertView;

        final SpinnerItem spinnerItem = spinnerItemArrayList.get(position);

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        TextView Name=(TextView) listItem.findViewById(R.id.Name);
        ImageView flag= (ImageView)  listItem.findViewById(R.id.Flag);

        Name.setText(spinnerItem.Name);
        flag.setImageDrawable(spinnerItem.Flag);


        return listItem;
    }

}
