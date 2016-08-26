package com.bixls.dollarprices;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class internetFirst extends ActionBarActivity {

    static private SharedPreferences mPreferences;
    public static String UPDATE_ACTION = "UPDATEME";
    static private CountryAdapter mCountryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_first);

        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
        CountryList countryList = new CountryList();
        mCountryAdapter = new CountryAdapter(countryList.init(getResources()), mPreferences, internetFirst.this);



        ((Button) findViewById(R.id.SyncButtom)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountryAdapter.SyncValues(2);

            }
        });

    }



    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Intent intents = new Intent(internetFirst.this, MainActivity.class);
            startActivity(intents);
            finish();

        }
    };

    @Override
    protected void onResume() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("finish");
        registerReceiver(receiver, filter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(receiver);
        super.onPause();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_internet_first, menu);
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
