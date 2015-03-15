package com.bixls.dollarprices;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Calculator extends Fragment {

    static   private CountryAdapter mCountryAdapter;
    static   private SharedPreferences mPreferences;
    static   private Context context;




    private static final String ARG_SECTION_NUMBER = "section_number";
    public static Calculator newInstance(int sectionNumber,SharedPreferences preferences,Context mcontext) {
        mPreferences=preferences;
        context=mcontext;
        Calculator fragment = new Calculator();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public Calculator() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CountryList countryList=new CountryList();
        mCountryAdapter = new CountryAdapter(countryList.init(getResources()),mPreferences,context);
         final  View rootView = inflater.inflate(R.layout.fragment_calculator, container, false);


        final Spinner spinner1 = (Spinner)     rootView.findViewById(R.id.spinner);
        final Spinner spinner2 = (Spinner)     rootView.findViewById(R.id.spinner2);

        final EditText input = (EditText)     rootView.findViewById(R.id.CalcAmount);

        final EditText output = (EditText)     rootView.findViewById(R.id.Calc_Value);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (spinner1.isFocused()) {
                    updateView(spinner1, spinner2);

                }
                calculate(spinner1, spinner2, input, output, false);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculate(spinner1,spinner2,input,output,false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setSpinner(spinner1, -1);
        setSpinner(spinner2,0);

        spinner2.setSelection(1);

        ((ImageButton) rootView.findViewById(R.id.Rev)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           animateCrystalBall(rootView);
           Rev(spinner1,spinner2);

            }
        });
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(input.isFocused())
                {
                calculate(spinner1,spinner2,input,output,false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        output.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(output.isFocused()) {
                    calculate(spinner1, spinner2, output, input,true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return rootView;

    }
    private static void animateCrystalBall(View rootView) {

        AnimationDrawable ballAnimation = (AnimationDrawable)  ((ImageButton)(rootView.findViewById(R.id.Rev))).getBackground();
        if (ballAnimation.isRunning()) {
            ballAnimation.stop();
        }
        ballAnimation.start();
    }

    void Rev(Spinner spinner1,Spinner spinner2)
    {
        int s1Item=spinner1.getSelectedItemPosition();
        int s2Item=spinner2.getSelectedItemPosition();
        SpinnerAdapter spinner2Adapter=spinner2.getAdapter();
        spinner2.setAdapter(spinner1.getAdapter());
        spinner1.setAdapter(spinner2Adapter);
        spinner2.setSelection(s1Item);
        spinner1.setSelection(s2Item);
    }
    void calculate(Spinner spinner1,Spinner spinner2 ,EditText input,EditText output,boolean Rev)
    {
        try {
            Country from = mCountryAdapter.Countries.get(spinner1.getSelectedItemPosition());
            //Country to = mCountryAdapter.GetCountryByCode(mCountryAdapter.GetList(mCountryAdapter.Countries.get(spinner1.getSelectedItemPosition()), "Code").get(spinner2.getSelectedItemPosition()));
            Country to = mCountryAdapter.Countries.get(spinner2.getSelectedItemPosition());
            double CalcAmount = Double.parseDouble(input.getText().toString());
          if(!Rev)
          {
              output.setText(Calculate(CalcAmount, from, to) + "");
          }
            else{
              output.setText(Calculate(CalcAmount, to, from) + "");
          }
        }catch (Exception E)
        {
            Log.e("Error in",E.toString());
        }
    }
    void updateView(Spinner A,Spinner B)
    {
        int except=A.getSelectedItemPosition();
        setSpinner(B,except);
    }
    public String Calculate(double amount,Country From,Country to){

        double ratio=to.Value/From.Value;
        DecimalFormat df = new DecimalFormat("#0.0000");
        return  df.format(amount*ratio);
    }

    public void setSpinner(Spinner spinner,int Except)
    {
        ArrayList<SpinnerItem> spinnerItems = new ArrayList<SpinnerItem>();

        for(int i=0;i<mCountryAdapter.Countries.size();i++) {

                spinnerItems.add(new SpinnerItem(mCountryAdapter.Countries.get(i).CurFull, mCountryAdapter.Countries.get(i).Flag));


        }
            SpinnerCustomAdapter adapter = new SpinnerCustomAdapter(context, R.layout.spiner_item, spinnerItems);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }


}
