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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;


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


        return super.onOptionsItemSelected(item);
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class HomeFragment extends Fragment {


        public static void UpdateView(View rootView){

            if((mPreferences.contains("CFrom")&&mPreferences.contains("CTo"))) {
                ((TextView) (rootView.findViewById(R.id.SyncText))).setText(mPreferences.getString("time", ""));
                int countryFrom = mCountryAdapter.GetCountryIDByCode(mPreferences.getString("CFrom", ""));
                int countryTo = mCountryAdapter.GetCountryIDByCode(mPreferences.getString("CTo", ""));




                if (countryFrom != 0 && countryTo != 0) {


                    setSpinner(((Spinner)rootView.findViewById(R.id.spinner1)),true,null);
                    ((Spinner)rootView.findViewById(R.id.spinner1)).setSelection(countryFrom);
                    defPos=countryTo;

                }
            }

        }

        private static void animateCrystalBall(View rootView) {

            AnimationDrawable ballAnimation = (AnimationDrawable)  ((ImageButton)(rootView.findViewById(R.id.equaltextview))).getBackground();
            if (ballAnimation.isRunning()) {
                ballAnimation.stop();
            }
            ballAnimation.start();
        }



         public static   int defPos=0;


        public static void Reverse(Spinner spinner1,Spinner spinner2,View rootView)
        {
            animateCrystalBall(rootView);
            int base=  spinner1.getSelectedItemPosition();
            int up=spinner2.getSelectedItemPosition();
            spinner1.setSelection(up);
            defPos=base;
        }

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static HomeFragment newInstance(int sectionNumber) {
            HomeFragment fragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public static void setSpinner(Spinner spinner,Boolean Type,Country From)
        {
            ArrayList<SpinnerItemHome> spinnerItems = new ArrayList<SpinnerItemHome>();

            if(Type) {
                for (int i = 0; i < mCountryAdapter.Countries.size(); i++) {

                    spinnerItems.add(new SpinnerItemHome(mCountryAdapter.Countries.get(i).CurFull,"1", mCountryAdapter.Countries.get(i).CurShort, mCountryAdapter.Countries.get(i).Flag));

                }
            }else
            {

                DecimalFormat df = new DecimalFormat("#0.0000");

                for (int i = 0; i < mCountryAdapter.Countries.size(); i++) {

                    spinnerItems.add(new SpinnerItemHome(mCountryAdapter.Countries.get(i).CurFull,  df.format(mCountryAdapter.Countries.get(i).Value/From.Value), mCountryAdapter.Countries.get(i).CurShort, mCountryAdapter.Countries.get(i).Flag));

                }

            }

            SpinnerHomepageAdapter adapter = new SpinnerHomepageAdapter(spinner.getContext(), R.layout.spiner_item, spinnerItems);
            spinner.setAdapter(adapter);
        }

        public HomeFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            animateCrystalBall(rootView);

            final Spinner spinner1 = (Spinner)     rootView.findViewById(R.id.spinner1);
            final Spinner spinner2 = (Spinner)     rootView.findViewById(R.id.spinner2);
            setSpinner(spinner1,true,null);


            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    setSpinner(spinner2, false, mCountryAdapter.Countries.get(spinner1.getSelectedItemPosition()));
                    if (position == defPos) {
                        spinner2.setSelection(defPos+1);
                    }
                    else
                    {
                        spinner2.setSelection(defPos);
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            UpdateView(rootView);
            ((ImageButton)(rootView.findViewById(R.id.equaltextview))).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Reverse(spinner1,spinner2,rootView);
                }
            });


            ((Button)(rootView.findViewById(R.id.SyncButtom))).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCountryAdapter.SyncValuesWithInterface(rootView,false);
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
