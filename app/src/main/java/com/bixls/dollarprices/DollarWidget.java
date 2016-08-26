package com.bixls.dollarprices;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.RemoteViews;

import java.text.DecimalFormat;


/**
 * Implementation of App Widget functionality.
 */
public class DollarWidget extends AppWidgetProvider {


    static   private SharedPreferences mPreferences;
    static   private CountryAdapter mCountryAdapter;
    public  static String UPDATE_ACTION="UPDATEME";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
            // Create an Intent to launch ExampleActivity
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.dollar_widget);
            views.setOnClickPendingIntent(R.id.dollarWidget, pendingIntent);
            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);

        }



    }
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (action != null && action.equals(UPDATE_ACTION)) {
            final AppWidgetManager manager = AppWidgetManager.getInstance
                    (context);

            onUpdate(context, manager,
                    manager.getAppWidgetIds(new ComponentName(
                                    context, DollarWidget.class)
                    )
            );
        }

        else {
            super.onReceive(context, intent);
        }
    }

    @Override
    public void onEnabled(Context context) {

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {



         mPreferences = context.getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);

        CountryList countryList=new CountryList();
        mCountryAdapter = new CountryAdapter(countryList.init(context.getResources()),mPreferences,context);


        Country from= mCountryAdapter.GetCountryByCode(mPreferences.getString("CFrom", ""));
        Country to= mCountryAdapter.GetCountryByCode(mPreferences.getString("CTo", ""));


        double ratio=to.Value/from.Value;

        DecimalFormat df = new DecimalFormat("#0.0000");



        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.dollar_widget);

        views.setTextViewText(R.id.appwidget_text,df.format(ratio));

        views.setImageViewBitmap(R.id.FTo,((BitmapDrawable)to.Flag).getBitmap());
        views.setImageViewBitmap(R.id.FFrom,((BitmapDrawable)from.Flag).getBitmap());

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}


