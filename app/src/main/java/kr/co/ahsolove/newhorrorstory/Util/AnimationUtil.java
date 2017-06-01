package kr.co.ahsolove.newhorrorstory.Util;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Created by shsolove on 2017-05-28.
 */
public class AnimationUtil {

    public AnimationUtil(){

    }

    public void blinkImageAnimation(ImageView imgView, int duration){

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(duration);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        imgView.startAnimation(anim);

    }

}
