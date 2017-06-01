package kr.co.ahsolove.newhorrorstory;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import kr.co.ahsolove.newhorrorstory.Util.Constants;
import kr.co.ahsolove.newhorrorstory.Util.SoundUtil;

public class ListActivity extends Activity implements View.OnClickListener{

    ImageButton imageButtonMenu1;
    ImageButton imageButtonMenu2;

    SoundPool soundPool;
    int soundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setClickEffectSound();
        setMatchingIdNsetListner();

    }

    /**
     * 요소 매칭
     */
    private void setMatchingIdNsetListner(){
        imageButtonMenu1 = (ImageButton)findViewById(R.id.menu1_imagebutton);
        imageButtonMenu2 = (ImageButton)findViewById(R.id.menu2_imagebutton);

        imageButtonMenu1.setOnClickListener(this);
        imageButtonMenu2.setOnClickListener(this);
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
        if(v == imageButtonMenu1){
            System.out.println("menu click!!!!!");
            soundPool.play(soundId, 1, 1, 1, 0, 1);
        }else if(v == imageButtonMenu2){
            soundPool.play(soundId, 1, 1, 1, 0, 1);
            Intent i = new Intent(ListActivity.this,ConfigActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    private void setClickEffectSound(){
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
