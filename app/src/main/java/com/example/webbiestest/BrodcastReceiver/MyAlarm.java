package com.example.webbiestest.BrodcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;

public class MyAlarm extends BroadcastReceiver {
  private static final String TAG = "MyAlarm";
  @Override
  public void onReceive(Context context, Intent intent) {
    Log.i(TAG, "onReceive: I am also clicked." );
    MediaPlayer mediaPlayer=MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
    mediaPlayer.start();
  }
}
