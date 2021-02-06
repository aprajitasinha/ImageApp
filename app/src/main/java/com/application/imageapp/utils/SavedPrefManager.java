package com.application.imageapp.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.application.imageapp.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class SavedPrefManager {
    //preferences variables
    private static final String KEY_USER_DETAIL = "key_user_detail";
    private static final String KEY_IS_REMEMBER_ME = "key_is_remember_me";
    private static final String KEY_IS_LOGIN = "key_is_login";
    private static final String KEY_DEVICE_TOKEN = "key_device_token";
    private static final String KEY_NOTIFICATION_ID = "notification_id";
    private static final String KEY_LATITUDE = "key_lattitude";
    private static final String KEY_LONGITUDE = "key_longitude";
    private static final String KEY_CALL_TYPE = "key_call_type";
    private static final String QB_USER_ID = "qb_user_id";
    private static final String QB_USER_LOGIN = "qb_user_login";
    private static final String QB_USER_PASSWORD = "qb_user_password";
    private static final String QB_USER_FULL_NAME = "qb_user_full_name";
    private static final String KEY_END_POINT_ARN = "end_point_arn";
    private static SavedPrefManager instance;
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;
    public Context context;


    public SavedPrefManager(Context context) {
        super();
        this.context = context;
        this.preferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        this.editor = this.preferences.edit();
    }

    public static SavedPrefManager getInstance(Context context) {
        if (instance == null) {
            synchronized (SavedPrefManager.class) {
                if (instance == null) {
                    instance = new SavedPrefManager(context);
                }
            }
        }
        return instance;
    }

    public static void saveStringPreferences(Context context, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public  void setKeyEndPointARN(String keyEndPointARN) {
        setStringValue(KEY_END_POINT_ARN,keyEndPointARN);
    }
    public static void saveIntPreferences(Context context, String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void saveFloatPreferences(Context context, String key, float value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /*
  This method is used to get string values from shared preferences.
   */
    public static String getStringPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }

    /*
     This method is used to get string values from shared preferences.
      */
    public static int getIntPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(key, 0);
    }

    public static void savePreferenceBoolean(Context context, String key, boolean b) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, b);
        editor.commit();
    }

    /*
      This method is used to get string values from shared preferences.
       */
    public static boolean getBooleanPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * Removes all the fields from SharedPrefs
     */
    public static void clearPrefs(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }
        public void setKeyIsRememberMe(boolean keyIsRememberMe) {
        setBooleanValue(KEY_IS_REMEMBER_ME, keyIsRememberMe);
    }

    public boolean isRememberMe() {
        return getBooleanValue(KEY_IS_REMEMBER_ME);
    }

    public void setKeyIsLogin(boolean keyIsLogin) {
        setBooleanValue(KEY_IS_LOGIN, keyIsLogin);
    }

    public boolean isLogin() {
        return getBooleanValue(KEY_IS_LOGIN);
    }

    public String getKeyDeviceToken() {
        return getStringValue(KEY_DEVICE_TOKEN);
    }

    public void setKeyDeviceToken(String token) {
        setStringValue(KEY_DEVICE_TOKEN, token);
    }

    public int getKeyNotificationId() {
        return getIntValue(KEY_NOTIFICATION_ID);
    }

    public void setKeyNotifiactionId(int keyNotifiaction) {
        setIntValue(KEY_NOTIFICATION_ID, keyNotifiaction);
    }

    public String getLatitude() {
        return getStringValue(KEY_LATITUDE);
    }

    public void setLatitude(String keyLatitude) {
        setStringValue(KEY_LATITUDE, keyLatitude);
    }

    public String getLongitude() {
        return getStringValue(KEY_LONGITUDE);
    }

    public void setLongitude(String longitude) {
        setStringValue(KEY_LONGITUDE, longitude);
    }

    /**
     * Retrieving the value from the preference for the respective key.
     *
     * @param key : Key for which the value is to be retrieved
     * @return return value for the respective key as boolean.
     */
    private boolean getBooleanValue(String key) {
        return this.preferences.getBoolean(key, false);
    }

    /**
     * Saving the preference
     *
     * @param key   : Key of the preference.
     * @param value : Value of the preference.
     */
    private void setBooleanValue(String key, boolean value) {
        this.editor.putBoolean(key, value);
        this.editor.commit();
    }

    /**
     * Retrieving the value from the preference for the respective key.
     *
     * @param key : Key for which the value is to be retrieved
     * @return return value for the respective key as string.
     */
    private String getStringValue(String key) {
        return this.preferences.getString(key, "");
    }

    /**
     * Saving the preference
     *
     * @param key   : Key of the preference.
     * @param value : Value of the preference.
     */
    private  void setStringValue(String key, String value) {
        this.editor.putString(key, value);
        this.editor.commit();
    }

    /**
     * Retrieving the value from the preference for the respective key.
     *
     * @param key : Key for which the value is to be retrieved
     * @return return value for the respective key as string.
     */
    private int getIntValue(String key) {
        return this.preferences.getInt(key, 0);
    }

    /**
     * Saving the preference
     *
     * @param key   : Key of the preference.
     * @param value : Value of the preference.
     */
    private void setIntValue(String key, int value) {
        this.editor.putInt(key, value);
        this.editor.commit();
    }

    /**
     * Retrieving the value from the preference for the respective key.
     *
     * @param key : Key for which the value is to be retrieved
     * @return return value for the respective key as string.
     */
    public long getLongValue(String key) {
        return this.preferences.getLong(key, 0L);
    }

    /**
     * Saving the preference
     *
     * @param key   : Key of the preference.
     * @param value : Value of the preference.
     */
    public void setLongValue(String key, long value) {
        this.editor.putLong(key, value);
        this.editor.commit();
    }

    /**
     * Remove the preference for the particular key
     *
     * @param key : Key for which the preference to be cleared.
     */
    public void removeFromPreference(String key) {
        this.editor.remove(key);
        this.editor.commit();
    }

}
