package kr.co.ahsolove.newhorrorstory.Util;

import android.content.Context;

/**
 * Created by shsolove on 2017-05-28.
 */
public class Constants {

    private static boolean isBackGroundMusicPlay = true;
    private static boolean isEffectPlay = true;

    public static String PREFERENCE_SOUND_MAIN_KEY = "SOUND";
    public static String PREFERENCE_MUSIC_KEY = "MUSIC";
    public static String PREFERENCE_EFFECT_KEY = "EFFECT";

    public static String PREFERENCE_VALUE_Y = "Y";
    public static String PREFERENCE_VALUE_N = "N";

    public static void setIsBackGroundMusicPlay(Context context){
        PreferenceManage pm = new PreferenceManage(context);
        String result = pm.getPreferenceStatus(Constants.PREFERENCE_SOUND_MAIN_KEY, Constants.PREFERENCE_MUSIC_KEY);

        if("Y".equals(result)) {
            isBackGroundMusicPlay = true;
        }else {
            isBackGroundMusicPlay = false;
        }

    }

    public static boolean getIsBackGroundMusicPlay(){
        return isBackGroundMusicPlay;
    }

    public static boolean getIsEffectPlay(){
        return isEffectPlay;
    }

    public static void setIsEffectPlay(Context context){
        PreferenceManage pm = new PreferenceManage(context);
        String result = pm.getPreferenceStatus(Constants.PREFERENCE_SOUND_MAIN_KEY, Constants.PREFERENCE_EFFECT_KEY);

        if("Y".equals(result)) {
            isEffectPlay = true;
        }else {
            isEffectPlay = false;
        }

    }

}
