package kr.co.ahsolove.newhorrorstory;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import kr.co.ahsolove.newhorrorstory.Util.Constants;
import kr.co.ahsolove.newhorrorstory.Util.PreferenceManage;
import kr.co.ahsolove.newhorrorstory.Util.SoundUtil;

public class ConfigActivity extends Activity implements View.OnClickListener{

    ImageView imageViewMusicConfig;
    ImageView imageViewEffectConfig;

    SoundPool soundPool;
    int soundId;
    boolean isCheckMusic;
    boolean isCheckEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        setClickEffectSound();

//        isCheckMusic = false;
//        isCheckEffect = false;

        setMusicFlag();
        setEffectFlag();
        setMatchingIdNsetListner();

    }

    /**
     * 요소 매칭
     */
    private void setMatchingIdNsetListner(){
        imageViewMusicConfig = (ImageView)findViewById(R.id.music_config_imageview);
        imageViewEffectConfig = (ImageView)findViewById(R.id.effect_config_imageview);

        if(Constants.getIsBackGroundMusicPlay()) {
            imageViewMusicConfig.setImageResource(R.drawable.music_on);
        }else {
            imageViewMusicConfig.setImageResource(R.drawable.music_off);
        }

        if(Constants.getIsEffectPlay()) {
            imageViewEffectConfig.setImageResource(R.drawable.effect_on);
        }else {
            imageViewEffectConfig.setImageResource(R.drawable.effect_off);
        }

        imageViewMusicConfig.setOnClickListener(this);
        imageViewEffectConfig.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SoundUtil.soundStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Constants.getIsBackGroundMusicPlay())
            SoundUtil.soundResume();
    }

    @Override
    public void onClick(View v) {
        if(v == imageViewMusicConfig){
            if(Constants.getIsBackGroundMusicPlay()){
                setMusicPreferenceFlag(Constants.PREFERENCE_VALUE_N);
                Constants.setIsBackGroundMusicPlay(this);
                imageViewMusicConfig.setImageResource(R.drawable.music_off);
                SoundUtil.soundStop();
            }else{
                setMusicPreferenceFlag(Constants.PREFERENCE_VALUE_Y);
                Constants.setIsBackGroundMusicPlay(this);
                imageViewMusicConfig.setImageResource(R.drawable.music_on);
                SoundUtil.soundResume();
            }
        }else if(v == imageViewEffectConfig){
            if(Constants.getIsEffectPlay()){
                setEffectPreferenceFlag(Constants.PREFERENCE_VALUE_N);
                Constants.setIsEffectPlay(this);
                imageViewEffectConfig.setImageResource(R.drawable.effect_off);
            }else{
                setClickEffectSound();
                soundPool.play(soundId, 1, 1, 1, 0, 1);
                setEffectPreferenceFlag(Constants.PREFERENCE_VALUE_Y);
                Constants.setIsEffectPlay(this);
                imageViewEffectConfig.setImageResource(R.drawable.effect_on);
            }
        }
    }

    private void setMusicFlag(){
        PreferenceManage pm = new PreferenceManage(ConfigActivity.this);
        String result = pm.getPreferenceStatus(Constants.PREFERENCE_SOUND_MAIN_KEY, Constants.PREFERENCE_MUSIC_KEY);

        System.out.println("setMusicFlag result : " + result);

        if("Y".equals(result)){
            setMusicPreferenceFlag(Constants.PREFERENCE_VALUE_Y);
            Constants.setIsBackGroundMusicPlay(this);
        }else{
            setMusicPreferenceFlag(Constants.PREFERENCE_VALUE_N);
            Constants.setIsBackGroundMusicPlay(this);
        }
    }

    private void setMusicPreferenceFlag(String value){
        PreferenceManage pm = new PreferenceManage(ConfigActivity.this);
        pm.setPreferenceStatusChange(Constants.PREFERENCE_SOUND_MAIN_KEY, Constants.PREFERENCE_MUSIC_KEY, value);
    }

    private void setEffectFlag(){
        PreferenceManage pm = new PreferenceManage(ConfigActivity.this);
        String result = pm.getPreferenceStatus(Constants.PREFERENCE_SOUND_MAIN_KEY, Constants.PREFERENCE_EFFECT_KEY);

        System.out.println("setEffectFlag result : " + result);

        if("Y".equals(result)){
            setEffectPreferenceFlag(Constants.PREFERENCE_VALUE_Y);
            Constants.setIsBackGroundMusicPlay(this);
        }else{
            setEffectPreferenceFlag(Constants.PREFERENCE_VALUE_N);
            Constants.setIsBackGroundMusicPlay(this);
        }
    }

    private void setEffectPreferenceFlag(String value){
        PreferenceManage pm = new PreferenceManage(ConfigActivity.this);
        pm.setPreferenceStatusChange(Constants.PREFERENCE_SOUND_MAIN_KEY, Constants.PREFERENCE_EFFECT_KEY, value);
    }

    private void setClickEffectSound(){
        soundPool = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .build();
        } else {
            soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
        }
        soundId = soundPool.load(this, R.raw.click, 1);
    }
}
