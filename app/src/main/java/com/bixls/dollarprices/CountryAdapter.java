package com.bixls.dollarprices;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Ahmed on 3/1/2015.
 */
public class CountryAdapter {
    private SharedPreferences mPreferences;

    Context context;
    View RootView;
   ArrayList<Country> Countries=new ArrayList<Country>();

     CountryAdapter( ArrayList<Country> countries,SharedPreferences Mpreferences,Context cn)
    {
        mPreferences=Mpreferences;
        context=cn;
        Countries=countries;
      if(mPreferences.contains("valid"))
      {

          for(int i=0;i<Countries.size();i++){
              if(mPreferences.contains(Countries.get(i).Code)){
            Countries.get(i).SetCurrency(mPreferences.getString(Countries.get(i).Code,""));
              Log.e("found",""+mPreferences.getString(Countries.get(i).Code,""));
          }
          }


      }
        else {

          SyncValues();
          Log.e("found","not found");

      }
    }

    public ArrayList<String> GetList(Country Except,String Type)
    {
        ArrayList<String> CodeList = new ArrayList<String>();

        switch (Type) {
            case "CurShort":
        for (int i = 0; i < Countries.size(); i++) {
            if (Countries.get(i) != Except) {
                CodeList.add(Countries.get(i).CurShort);
            }
        }
        return CodeList;
            case "Value":
                for (int i = 0; i < Countries.size(); i++) {
                    if (Countries.get(i) != Except) {
                        CodeList.add(Countries.get(i).Value+"");
                    }
                }
                return CodeList;
            case "Name":
                for (int i = 0; i < Countries.size(); i++) {
                    if (Countries.get(i) != Except) {
                        CodeList.add(Countries.get(i).Name);
                    }
                }
                return CodeList;
            default:
                for (int i = 0; i < Countries.size(); i++) {
                    if (Countries.get(i) != Except) {
                        CodeList.add(Countries.get(i).CurFull);
                    }
                }
                return CodeList;
    }
    }


    void SyncValuesWithInterface(View rootView)
    {
        SyncValues();
        RootView=rootView;
    }
    void SyncValues()
    {
        GetDataFromServer getDataFromServer = new GetDataFromServer();
        getDataFromServer.execute();


    }

    void SyncValuesCashed(HashMap<String, String> Values)
    {
        SharedPreferences.Editor editor = mPreferences.edit();
        for(int i=0;i<Countries.size();i++){

                try {
                    Countries.get(i).SetCurrency(Values.get(Countries.get(i).Code));
                    editor.putString(Countries.get(i).Code, Values.get(Countries.get(i).Code).toString());
                    Log.v("found in cashing",""+mPreferences.getString(Countries.get(i).Code,""));
                }catch (Exception e)
                {
                }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        editor.putString("time",currentDateandTime);
        editor.putString("valid","true");
        editor.commit();
        Toast.makeText(context, context.getResources().getString(R.string.SaveMsg), Toast.LENGTH_SHORT).show();
        if(RootView!=null)
        {
            MainActivity.UpdateView(RootView);
        }

    }

    Country GetCountryByCode(String code){


        for(int i=0;i<Countries.size();i++){

               if (code.equals(Countries.get(i).Code))
               {
                   return Countries.get(i);
               }
            }

        return  null;
    }
    Country GetCountryByName(String code){


        for(int i=0;i<Countries.size();i++){

            if (code.equals(Countries.get(i).Name))
            {
                return Countries.get(i);
            }
        }

        return  null;
    }
    Country GetCountryByCurFull(String curFull){

        for(int i=0;i<Countries.size();i++){

            if (Countries.get(i).CurFull.equals(curFull))
            {
                return Countries.get(i);
            }
        }

        return  null;
    }


    //Get data
    private class GetDataFromServer extends AsyncTask<Object, Void, JSONObject> {

        ProgressDialog progDailog = new ProgressDialog(context);
        protected void onPreExecute() {
            super.onPreExecute();
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(true);
            progDailog.show();
        }



        @Override
        protected JSONObject doInBackground(Object... arg0) {
            return GET("http://www.dollar-prices-today.com/api.php");
        }

        @Override
        protected void onPostExecute(JSONObject result) {

            handleResponse(result);
            progDailog.cancel();
        }

    }


    public static JSONObject GET(String url) {
        InputStream inputStream = null;
        String result = "";
        JSONObject jsonResponse = null;
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";


            jsonResponse = new JSONObject(result);

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }


        return jsonResponse;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public void handleResponse(JSONObject result) {

        if (result == null) {
            Log.e("Error in","Response is empty");
        } else {
            try {
                HashMap<String, String> Values = new HashMap<String, String>();

                JSONArray CountriesArray = result.getJSONArray("values");

                for (int i = 0; i < CountriesArray.length(); i++) {
                    JSONObject post = CountriesArray.getJSONObject(i);



                    Values.put(post.getString("title"),post.getString("value"));
                }


                Log.e("json is", result.toString());
                SyncValuesCashed(Values);

            } catch (JSONException e) {
                Log.e("", "Exception caught!", e);

            }
        }

    }



}
