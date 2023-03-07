package com.example.appwidgetsample;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import androidx.annotation.IdRes;

import java.text.DateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    private static final String PREF_FILE = "com.example.android.appwidgetsample";
    private static final String COUNT_KEY = "count";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        updateId(views, appWidgetId);
        updateDate(context, views, appWidgetId);
        updateWidgetOnButtonClick(context, views, appWidgetId, R.id.button_update);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    /**
     * Updates the widget id every time the widget is created or received an intent to update.
     */
    private static void updateId(RemoteViews views, int appWidgetId) {
        views.setTextViewText(R.id.appwidget_id, String.valueOf(appWidgetId));
    }

    /**
     * Updates the widget date every time the widget is created or received an intent to update.
     */
    private static void updateDate(Context context, RemoteViews views, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_FILE, 0);
        int count = prefs.getInt(COUNT_KEY + appWidgetId, 0);
        count++;

        String dateString = DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());
        views.setTextViewText(R.id.appwidget_update, dateString);
        views.setTextViewText(R.id.appwidget_count, String.valueOf(count));

        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putInt(COUNT_KEY + appWidgetId, count);
        prefEditor.apply();
    }

    /**
     * Updates the widget state when the button is clicked.
     */
    private static void updateWidgetOnButtonClick(Context context, RemoteViews views, int appWidgetId, @IdRes int buttonId) {
        Intent intentUpdate = new Intent(context, NewAppWidget.class);
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] idArray = new int[]{appWidgetId};
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);

        int flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        PendingIntent pendingUpdate = PendingIntent.getBroadcast(context, appWidgetId, intentUpdate, flags);
        views.setOnClickPendingIntent(buttonId, pendingUpdate);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}