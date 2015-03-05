package com.bixls.dollarprices;


import android.app.Activity;
import android.content.Context;
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
import android.widget.Spinner;
import android.widget.Toast;

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

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static Settings newInstance(int sectionNumber,SharedPreferences preferences,Context mcontext) {
        mPreferences=preferences;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        CountryList countryList=new CountryList();
        mCountryAdapter = new CountryAdapter(countryList.init(getResources()),mPreferences,context);

        final  View rootView = inflater.inflate(R.layout.fragment_settings, container, false);


        final Spinner spinner1 = (Spinner)     rootView.findViewById(R.id.spinner3);
        final Spinner spinner2 = (Spinner)     rootView.findViewById(R.id.spinner4);

        setSpinner(spinner1);
        setSpinner(spinner2);

        ((Button) rootView.findViewById(R.id.Save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Country from = mCountryAdapter.GetCountryByCurFull(spinner1.getSelectedItem().toString());
                Country to = mCountryAdapter.GetCountryByCurFull(spinner2.getSelectedItem().toString());

                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putString("CFrom",from.Code);
                editor.putString("CTo",to.Code);
                editor.commit();
                Toast.makeText(context,context.getResources().getString(R.string.SaveMsg),Toast.LENGTH_SHORT).show();

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, MainActivity.HomeFragment.newInstance(0))
                        .commit();


            }
        });


        return rootView;
    }

    public void setSpinner(Spinner spinner)
    {
        ArrayList<String> arrayList=mCountryAdapter.GetList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                arrayList );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }


}
