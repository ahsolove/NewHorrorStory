package kr.co.ahsolove.newhorrorstory.Util;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by shsolove on 2017-05-28.
 */
public class SoundUtil {

   private static MediaPlayer mediaPlayer;
//   private Context context;
//   private int resId;

//   public SoundUtil(Context context, int resId){
//      this.context = context;
//      this.resId = resId;
//      mediaPlayer = MediaPlayer.create(this.context, resId);
//   }

   public static void setMediaPlayer(Context context, int resId){

      if(mediaPlayer == null){
         System.out.println("mediaPlayer Object is Null");
      }
      mediaPlayer = MediaPlayer.create(context, resId);

      if(mediaPlayer != null){
         System.out.println("mediaPlayer Object is not Null");
      }

   }

   public static void soundFirstPlay(boolean isLooping){

      if(mediaPlayer != null) {
         mediaPlayer.setLooping(isLooping);
         mediaPlayer.start();
      }
   }

   public static void soundStop(){
      mediaPlayer.pause();
   }

   public static void soundResume(){
      mediaPlayer.start();
   }


}
