package com.bixls.dollarprices;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class ViewCountry extends ActionBarActivity {

    Country DefaultCountry;
    static   private SharedPreferences mPreferences;
    static   private CountryAdapter mCountryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_country);



        Bundle extras = getIntent().getExtras();
        CountryList countryList=new CountryList();
        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
        mCountryAdapter = new CountryAdapter(countryList.init(getResources()),mPreferences,ViewCountry.this);


        if (extras != null) {

            DefaultCountry    = mCountryAdapter.GetCountryByCode(extras.getString("Country")) ;

            init(DefaultCountry);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(getResources().getString(R.string.DollarPricesIn)+" "+DefaultCountry.Name);
        }
    }
    public String Calculate(String From,String to){

        Double ratio=Double.parseDouble(to)/Double.parseDouble(From);
        DecimalFormat df = new DecimalFormat("#0.0000");
        return  df.format(ratio);
    }

    public void init(Country from) {

        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);



        TextView tv0 = new TextView(this);
        tv0.setText(getResources().getString(R.string.currencywith)+from.CurFull);
        tv0.setTextColor(Color.BLACK);


        TextView tv1 = new TextView(this);
        tv1.setText(getResources().getString(R.string.currency));
        tv1.setTextColor(Color.BLACK);


        tbrow0.addView(tv0);
        tbrow0.addView(tv1);

        stk.addView(tbrow0);
        ArrayList<String> currencies=mCountryAdapter.GetList(from,"CurrencyFull");
        ArrayList<String> CValuesToDollar=mCountryAdapter.GetList(from,"Value");

        for (int i = 0; i < currencies.size(); i++) {

            TableRow tbrow = new TableRow(this);
            //init
            TextView t1v = new TextView(this);
            TextView t2v = new TextView(this);

            //setting values
            t1v.setText(currencies.get(i));
            t2v.setText(Calculate(CValuesToDollar.get(i), from.Value + ""));
            //setting prop
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.CENTER);
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            //adding to row
            tbrow.addView(t2v);
            tbrow.addView(t1v);
            stk.addView(tbrow);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_country, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
