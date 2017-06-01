package kr.co.ahsolove.newhorrorstory;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import kr.co.ahsolove.newhorrorstory.Util.AnimationUtil;
import kr.co.ahsolove.newhorrorstory.Util.Constants;
import kr.co.ahsolove.newhorrorstory.Util.SoundUtil;

public class MainActivity extends Activity {

    ImageView imageViewLoading;
    SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 환경값 세팅
        Constants.setIsBackGroundMusicPlay(this);
        Constants.setIsEffectPlay(this);

        // 요소 매칭
        setMatchingId();

        // 배경음악 초기화 및 재생선택
        setBackGroundMusicNEffect();

        // 로딩바 깜박임 효과
        loadingImageAnimation();

        mNextHandler.sendEmptyMessageDelayed(0, 3000);
    }

    /**
     * 요소 매칭
     */
    private void setMatchingId(){
        imageViewLoading = (ImageView)findViewById(R.id.loading_imageview);
    }

    @Override
    protected void onPause() {
        super.onPause();

//        if(!Constants.getIsBackGroundMusicPlay()) {
//            System.out.println("onPause");
            SoundUtil.soundStop();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
        if(Constants.getIsBackGroundMusicPlay())
            SoundUtil.soundResume();
    }

    private void setBackGroundMusicNEffect(){

        // 효과음 초기화
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .build();
        } else {
            soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
        }

        // 배경음악 초기화 및 시작
        SoundUtil.setMediaPlayer(MainActivity.this, R.raw.bg);
        SoundUtil.soundFirstPlay(true);

        if(!Constants.getIsBackGroundMusicPlay()) {
            SoundUtil.soundStop();
        }
    }

    private void loadingImageAnimation(){
        AnimationUtil animationUtil = new AnimationUtil();
        animationUtil.blinkImageAnimation(imageViewLoading, 1000);
    }

    Handler mNextHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            Intent i = new Intent(MainActivity.this,ListActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            finish();
        }
    };
}
