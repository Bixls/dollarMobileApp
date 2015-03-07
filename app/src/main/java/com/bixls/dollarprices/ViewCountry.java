package com.bixls.dollarprices;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


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

            ((TextView)findViewById(R.id.CountryTitle)).setText(DefaultCountry.Name);
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
