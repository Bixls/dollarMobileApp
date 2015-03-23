package com.bixls.dollarprices;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.w3c.dom.Text;

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

        //Ad start
        AdView mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //Ad End


        Bundle extras = getIntent().getExtras();
        CountryList countryList=new CountryList();
        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
        mCountryAdapter = new CountryAdapter(countryList.init(getResources()),mPreferences,ViewCountry.this);







        if (extras != null) {

            DefaultCountry    = mCountryAdapter.GetCountryByCode(extras.getString("Country")) ;

            init(DefaultCountry);


            ((TextView)findViewById(R.id.bigTitle)).setText(getResources().getString(R.string.DollarPricesIn)+" "+DefaultCountry.Name);
            ((TextView)findViewById(R.id.SmallTitle)).setText(getResources().getString(R.string.DollarPricesInsmall)+" "+DefaultCountry.CurFull);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setIcon(DefaultCountry.Flag);
            actionBar.setTitle(getResources().getString(R.string.DollarPricesIn)+" "+DefaultCountry.Name);

        }
    }
    public String Calculate(double to,double From){

        Double ratio=to/From;
        DecimalFormat df = new DecimalFormat("#0.0000");
        return  df.format(ratio);
    }

    void init(Country defaultCountry)
    {
        final ListView listView=(ListView)findViewById(R.id.listView2);

        ArrayList<SpinnerItemHome> spinnerItems = new ArrayList<SpinnerItemHome>();

        for(int i=0;i<mCountryAdapter.Countries.size();i++) {

            if (!(mCountryAdapter.Countries.get(i).Code == defaultCountry.Code)) {

                spinnerItems.add(new SpinnerItemHome(mCountryAdapter.Countries.get(i).CurFull,Calculate(defaultCountry.Value,mCountryAdapter.Countries.get(i).Value),mCountryAdapter.Countries.get(i).CurShort, mCountryAdapter.Countries.get(i).Flag));

            }

        }
        viewCountryadapter adapter = new viewCountryadapter(this, R.layout.viewcountrylist, spinnerItems);

        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_country, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.action_example)
        {
            Intent intent = new Intent(ViewCountry.this, about.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}
