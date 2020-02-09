package com.example.socialmedia.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {
    private Context context;
    private static final String PREFERENCE_NAME = "preference_name";
    private static final String TOKEN = "token";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public AppPreference(Context context){
        this.context = context;

        sharedPreferences = context.getSharedPreferences(
                PREFERENCE_NAME,
                Context.MODE_PRIVATE
        );

        editor = sharedPreferences.edit();
    }


    public boolean isLoggedIn(){
        return sharedPreferences.getString(TOKEN, null) != null;
    }

    public void saveToken(String token){
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public String getToken(){
        return sharedPreferences.getString(TOKEN, null);
    }

    private void logout(){
        editor.clear();
        editor.apply();
    }

}
