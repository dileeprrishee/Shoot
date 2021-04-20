package com.example.Shoot.Storage;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by harishkumar
 */

public class PreferenceController {
    private static final String PREFERENCE_NAME = "pref_shoot";

    public interface PreferenceKeys {
        String PREFERENCE_LOGGED_STATUS = "LOGGED_STATUS";
        String PREFERENCE_WINNER_STATUS = "WINNER_STATUS";
        String PREFERENCE_FIRST_TIME = "FIRST_TIME";

        String PREFERENCE_CUSTOMER_ID = "LOGGED_CUSTOMER_ID";
        String PREFERENCE_GAME_END_TIME = "GAME_ENDTIME";
        String PREFERENCE_LOGGED_FIRST_NAME = "LOGGED_FIRST_NAME";
        String PREFERENCE_MID = "MID";
        String PREFERENCE_LOGGED_EMAIL = "LOGGED_EMAIL";
        String PREFERENCE_LOGGED_TELEPHONE = "LOGGED_TELEPHONE";
        String PREFERENCE_SELECTED_PAYMENT_METHOD = "PAYMENT_METHOD";
        String PREFERENCE_USER_COUNTRY_ID = "USER_COUNTRY_ID";
        String PREFERENCE_WALLET_BALANCE = "WALLET_BALLANCE";
        String PREFERENCE_AMOUNT_0 = "WALLET_AMOUNT_0";
        String PREFERENCE_AMOUNT_1 = "WALLET_AMOUNT_1";
        String PREFERENCE_AMOUNT_2 = "WALLET_AMOUNT_2";
        String PREFERENCE_AMOUNT_3 = "WALLET_AMOUNT_3";
        String PREFERENCE_AMOUNT_4 = "WALLET_AMOUNT_4";
        String PREFERENCE_AMOUNT_5 = "WALLET_AMOUNT_5";
        String PREFERENCE_AMOUNT_6 = "WALLET_AMOUNT_6";
        String PREFERENCE_AMOUNT_7 = "WALLET_AMOUNT_7";
        String PREFERENCE_AMOUNT_8 = "WALLET_AMOUNT_8";
        String PREFERENCE_AMOUNT_9 = "WALLET_AMOUNT_9";

        String PREFERENCE_PLAY_STATUS = "PREFERENCE_PLAY_STATUS";
        String PREFERENCE_START_TIME = "START TIME";
        String PREFERENCE_END_TIME = "END TIME";
        String PREFERENCE_FCM_TOKEN = "token";
    }

    public static void setPreference(Context context,String key, String value) {
        SharedPreferences preferences =  context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void setPreference(Context context,String key, int value) {

        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void setPreference(Context context,String key, float value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public static void setPreference(Context context,String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void setPreference(Context context,String key, long value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static String getStringPreference(Context context,String key) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    public static int getIntPreference(Context context,String key) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(key, -1);
    }

    public static float getFloatPreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return preferences.getFloat(key, -1f);
    }

    public static boolean getBooleanPreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    public static long getLongPreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return preferences.getLong(key, -1);
    }

    public static void clearData(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
    }

    public static boolean isUsrLoggedIn(Context context) {
        return PreferenceController.getBooleanPreference(context, PreferenceKeys.PREFERENCE_LOGGED_STATUS);
    }

    public static boolean isFirstTime(Context context) {
        return PreferenceController.getBooleanPreference(context, PreferenceKeys.PREFERENCE_FIRST_TIME);
    }

    public static boolean isWinnerLogin(Context context) {
        return PreferenceController.getBooleanPreference(context, PreferenceKeys.PREFERENCE_WINNER_STATUS);
    }

}
