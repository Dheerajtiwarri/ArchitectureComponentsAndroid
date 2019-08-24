package com.example.webbiestest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.example.webbiestest.BrodcastReceiver.MyAlarm;
import com.example.webbiestest.R;

import java.util.Calendar;

public class BackgroundTaskActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_background_task);

  }
  public void onClickOfStart(View view)
  {

    Toast.makeText(this, "some one click me :) ", Toast.LENGTH_SHORT).show();
    AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
   Intent intent=new Intent(".BrodcastReceiver.MyAlarm");
   sendBroadcast(intent);



  }


}
