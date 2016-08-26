package com.bixls.dollarprices;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SettingFirstTime extends FragmentActivity {
    static   private SharedPreferences mPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_first_time);

        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, Settings.newInstance(-1,mPreferences,SettingFirstTime.this))
                .commit();

    }


}
