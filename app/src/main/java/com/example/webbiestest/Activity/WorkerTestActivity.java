package com.example.webbiestest.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.webbiestest.R;
import com.example.webbiestest.Worker.MyWork;

import java.util.concurrent.TimeUnit;

/**
 * Test worker class and deal with all type Worker Component...
 * initiate WorkManager on click of button.
 */
public class WorkerTestActivity extends AppCompatActivity {
   private static final String TAG = "WorkerTestActivity";
   OneTimeWorkRequest request;
   WorkRequest workRequest;
   TextView workStatusText;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_worker_test);

      workStatusText = (TextView) findViewById(R.id.workStatusText);
     /* request = new OneTimeWorkRequest.Builder(MyWork.class).build();

      WorkManager.getInstance().getWorkInfoByIdLiveData(request.getId())
            .observe(this, new Observer<WorkInfo>() {
               @Override
               public void onChanged(WorkInfo workInfo) {
                  String status = workInfo.getState().name();

                  workStatusText.append(" " + status + "\n\n");
               }
            });*/

     workRequest=new PeriodicWorkRequest.Builder(MyWork.class, 24, TimeUnit.SECONDS, 3, TimeUnit.SECONDS )
           .addTag(TAG)
           .build();

      WorkManager.getInstance().getWorkInfoByIdLiveData(workRequest.getId())
            .observe(this, new Observer<WorkInfo>() {
               @Override
               public void onChanged(WorkInfo workInfo) {
                  String status = workInfo.getState().name();

                  workStatusText.append(" " + status + "\n\n");
               }
            });

   }

   /**
    * handle click event of button
    * enqueued WorkManager.
    * @param view
    */
   public void getNotificationOnClick(View view) {
      Log.i(TAG, "getNotificationOnClick: button is clicked...");
      new Resopnse().execute();

   }
   class Resopnse extends AsyncTask<Void, Void, Void>
   {

      @Override
      protected Void doInBackground(Void... voids) {
         WorkManager.getInstance().enqueue(workRequest );
         return null;
      }
   }
}
