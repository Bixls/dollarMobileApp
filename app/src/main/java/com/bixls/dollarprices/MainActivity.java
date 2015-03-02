package com.bixls.dollarprices;

import android.app.Activity;
import android.content.SharedPreferences;
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
import android.widget.TextView;


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

        mCountryAdapter = new CountryAdapter(countryList.init(getResources()),mPreferences,MainActivity.this);

    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
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
                        .replace(R.id.container, Settings.newInstance(position))
                        .commit();
                break;
            case 2:
               fragmentManager.beginTransaction()
                       .replace(R.id.container, Calculator.newInstance(position))
                       .commit();
                break;
            case 3:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, allCont.newInstance(position))
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            try {
            ((TextView)(rootView.findViewById(R.id.SyncText))).setText(mPreferences.getString("time",""));

            Country countryFrom=mCountryAdapter.GetCountryByCode("USD");
            Country countryTo=mCountryAdapter.GetCountryByCode("EGP");

                ((TextView)(rootView.findViewById(R.id.FromTextType))).setText(countryFrom.CurShort);
                ((TextView)(rootView.findViewById(R.id.fromAmount))).setText("1");

                ((TextView)(rootView.findViewById(R.id.ToTextType))).setText(countryTo.CurShort);
                ((TextView)(rootView.findViewById(R.id.toAmount))).setText(""+countryTo.Value);


            }catch (Exception e)
            {
                Log.e("errore", ""+e);
            }
            ((Button)(rootView.findViewById(R.id.SyncButtom))).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCountryAdapter.SyncValues();
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
