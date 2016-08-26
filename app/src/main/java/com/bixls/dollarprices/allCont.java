package com.bixls.dollarprices;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class allCont extends Fragment {

    static   private CountryAdapter mCountryAdapter;
    static   private SharedPreferences mPreferences;
    static   private Context context;

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static allCont newInstance(int sectionNumber,SharedPreferences preferences,Context mcontext) {
        mPreferences=preferences;
        context=mcontext;
        allCont fragment = new allCont();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public allCont() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        CountryList countryList=new CountryList();
        mCountryAdapter = new CountryAdapter(countryList.init(getResources()),mPreferences,context);

        final  View rootView = inflater.inflate(R.layout.fragment_all_cont, container, false);
        // Inflate the layout for this fragment


        final   ListView listView=(ListView)rootView.findViewById(R.id.listView);

        ArrayList<SpinnerItem> spinnerItems = new ArrayList<SpinnerItem>();

        for(int i=0;i<mCountryAdapter.Countries.size();i++) {
            if (!(mCountryAdapter.Countries.get(i).Code == "USD")) {
                spinnerItems.add(new SpinnerItem(mCountryAdapter.Countries.get(i).Name, mCountryAdapter.Countries.get(i).Flag));

            }
        }


        ListCustomAdapter adapter = new ListCustomAdapter(context, R.layout.list_item, spinnerItems);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, ViewCountry.class);

                intent.putExtra("Country", mCountryAdapter.Countries.get(position+1).Code);

                startActivity(intent);
                Log.e("we click on", position+"");
            }
        });




        return rootView;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }


}
