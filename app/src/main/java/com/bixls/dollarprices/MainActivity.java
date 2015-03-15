package com.bixls.dollarprices;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    static   private SharedPreferences mPreferences;
    static   private CountryAdapter mCountryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));



        CountryList countryList=new CountryList();

        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);

        if(!(mPreferences.contains("CFrom")&&mPreferences.contains("CTo")))
        {
            Intent intent = new Intent(MainActivity.this, SettingFirstTime.class);
            startActivity(intent);
            finish();
        }

        mCountryAdapter = new CountryAdapter(countryList.init(getResources()),mPreferences,MainActivity.this);






    }



    @Override
     public  void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position) {
            case 0:
            fragmentManager.beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance(position))
                    .commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, Settings.newInstance(position,mPreferences,MainActivity.this))
                        .commit();
                break;
            case 2:
               fragmentManager.beginTransaction()
                       .replace(R.id.container, Calculator.newInstance(position,mPreferences,MainActivity.this))
                       .commit();
                break;
            case 3:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, allCont.newInstance(position,mPreferences,MainActivity.this))
                        .commit();
                break;
        }
    }

    public void onSectionAttached(int number) {
        String[] headers=getResources().getStringArray(R.array.drawer_Array);
                mTitle = headers[number];
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    public static void UpdateView(View rootView){

        if((mPreferences.contains("CFrom")&&mPreferences.contains("CTo"))) {
            ((TextView) (rootView.findViewById(R.id.SyncText))).setText(mPreferences.getString("time", ""));
            Country countryFrom = mCountryAdapter.GetCountryByCode(mPreferences.getString("CFrom", ""));
            Country countryTo = mCountryAdapter.GetCountryByCode(mPreferences.getString("CTo", ""));

            if (countryFrom != null && countryTo != null) {
                double ratio = countryTo.Value / countryFrom.Value;
                DecimalFormat df = new DecimalFormat("#0.0000");

                ((TextView) (rootView.findViewById(R.id.FromTextType))).setText(countryFrom.CurShort);
                ((TextView) (rootView.findViewById(R.id.fromAmount))).setText("1");
                ((ImageView)(rootView.findViewById(R.id.FlagFrom))).setImageDrawable(countryFrom.Flag);
                ((TextView) (rootView.findViewById(R.id.ToTextType))).setText(countryTo.CurShort);
                ((TextView) (rootView.findViewById(R.id.toAmount))).setText("" + df.format(ratio));
                ((ImageView)(rootView.findViewById(R.id.FlagTo))).setImageDrawable(countryTo.Flag);
            }
        }

    }
    public static void Reverse(View rootView)
    {
        animateCrystalBall(rootView);
        String oldFrom=mPreferences.getString("CFrom", "");
        String oldTo=mPreferences.getString("CTo", "");
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("CFrom", oldTo);
        editor.putString("CTo", oldFrom);
        editor.commit();
        UpdateView(rootView);
    }
    private static void animateCrystalBall(View rootView) {

        AnimationDrawable ballAnimation = (AnimationDrawable)  ((ImageButton)(rootView.findViewById(R.id.equaltextview))).getBackground();
        if (ballAnimation.isRunning()) {
            ballAnimation.stop();
        }
        ballAnimation.start();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class HomeFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static HomeFragment newInstance(int sectionNumber) {
            HomeFragment fragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public HomeFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            UpdateView(rootView);
            animateCrystalBall(rootView);

            ((Button)(rootView.findViewById(R.id.SyncButtom))).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCountryAdapter.SyncValuesWithInterface(rootView);
                }
            });
            ((ImageButton)(rootView.findViewById(R.id.equaltextview))).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Reverse(rootView);
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

}
