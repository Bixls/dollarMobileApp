package com.bixls.dollarprices;


import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */

public class Settings extends Fragment {

    static   private CountryAdapter mCountryAdapter;
    static   private SharedPreferences mPreferences;
    static   private Context context;
    static int Type;

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static Settings newInstance(int sectionNumber,SharedPreferences preferences,Context mcontext) {
        mPreferences=preferences;
        Type=sectionNumber;
        context=mcontext;
        Settings fragment = new Settings();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public Settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        CountryList countryList=new CountryList();
        mCountryAdapter = new CountryAdapter(countryList.init(getResources()),mPreferences,context);

        final  View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        if(Type==-1)
        {
            ((TextView)rootView.findViewById(R.id.textVie4)).setVisibility(View.VISIBLE);
        }

        final Spinner spinner1 = (Spinner)     rootView.findViewById(R.id.spinner3);
        final Spinner spinner2 = (Spinner)     rootView.findViewById(R.id.spinner4);

        setSpinner(spinner1,null);
        setSpinner(spinner2,mCountryAdapter.GetCountryByCode("USD"));


        ((Button) rootView.findViewById(R.id.Save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Country from = mCountryAdapter.GetCountryByCurFull(spinner1.getSelectedItem().toString());
                Country to = mCountryAdapter.GetCountryByCurFull(spinner2.getSelectedItem().toString());

                    SharedPreferences.Editor editor = mPreferences.edit();
                    editor.putString("CFrom",from.Code);
                    editor.putString("CTo",to.Code);
                    double ratio = to.Value / from.Value;
                    editor.putString("ToValue", ratio+"");
                    editor.putString("To",to.CurFull);
                    editor.putString("From",from.CurFull);
                    editor.commit();

                Intent i = new Intent(context, DollarWidget.class);
                i.setAction(DollarWidget.UPDATE_ACTION);
                context.sendBroadcast(i);






                Toast.makeText(context,context.getResources().getString(R.string.SaveMsg),Toast.LENGTH_SHORT).show();

                if(Type==-1)
                {
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();


                }else {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, MainActivity.HomeFragment.newInstance(0))
                            .commit();
                }

            }
        });


        return rootView;
    }

    public void setSpinner(Spinner spinner,Country Except)
    {
        ArrayList<String> arrayList=mCountryAdapter.GetList(Except,"CurFull");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                arrayList );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }



}
