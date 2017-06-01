package kr.co.ahsolove.newhorrorstory.Util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by shsolove on 2017-05-30.
 */
public class PreferenceManage {

    private Context context;

    public PreferenceManage(Context context){
        this.context = context;
    }

    public String getPreferenceStatus(String preKey, String subKey){
        SharedPreferences prefs = context.getSharedPreferences(preKey, context.MODE_PRIVATE);
        return prefs.getString(subKey, "Y");
    }

    public void setPreferenceStatusChange(String preKey, String subKey, String value){
        SharedPreferences pref = context.getSharedPreferences(preKey, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(subKey, value);
        editor.commit();
    }
}

