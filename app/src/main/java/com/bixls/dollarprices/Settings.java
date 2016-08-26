package com.bixls.dollarprices;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


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

        setSpinner(spinner1,-1);
        setSpinner(spinner2,0);

            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    updateView(spinner1,spinner2);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        ((Button) rootView.findViewById(R.id.Save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Country from = mCountryAdapter.Countries.get(spinner1.getSelectedItemPosition());
                Country to = mCountryAdapter.GetCountryByCode(mCountryAdapter.GetList(mCountryAdapter.Countries.get(spinner1.getSelectedItemPosition()), "Code").get(spinner2.getSelectedItemPosition()));

                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putString("CFrom", from.Code);
                editor.putString("CTo", to.Code);
                editor.commit();

                Intent i = new Intent(context, DollarWidget.class);
                i.setAction(DollarWidget.UPDATE_ACTION);
                context.sendBroadcast(i);


                Toast.makeText(context, context.getResources().getString(R.string.SaveMsg), Toast.LENGTH_SHORT).show();

                if (Type == -1) {
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();


                } else {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, MainActivity.HomeFragment.newInstance(0))
                            .commit();
                }

            }
        });


        return rootView;
    }
    void updateView(Spinner A,Spinner B)
    {
        int except=A.getSelectedItemPosition();
        setSpinner(B,except);
    }

    public void setSpinner(Spinner spinner,int Except)
    {
        ArrayList<SpinnerItem> spinnerItems = new ArrayList<SpinnerItem>();

        for(int i=0;i<mCountryAdapter.Countries.size();i++) {
            if (i!=Except) {
                spinnerItems.add(new SpinnerItem(mCountryAdapter.Countries.get(i).CurFull, mCountryAdapter.Countries.get(i).Flag));

            }
        }
        SpinnerCustomAdapter adapter = new SpinnerCustomAdapter(context, R.layout.spiner_item, spinnerItems);
        spinner.setAdapter(adapter);
    }



}
