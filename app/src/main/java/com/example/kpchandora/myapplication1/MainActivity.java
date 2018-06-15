package com.example.kpchandora.myapplication1;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Thread thread;
    private final String TAG = "'MainActivity'";
    public static final String BD_CAST = "broadcast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate: ");

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter(BD_CAST));

        final Handler handler = new Handler();


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "run: ");
                handler.postDelayed(this, 1000);
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        };
        thread = new Thread(runnable);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.i(TAG, "onReceive: "+intent.getAction());

//            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "id");
//            builder.setSmallIcon(android.R.drawable.ic_notification_clear_all);
//            builder.setContentTitle("Test Notification")
//                    .setContentText("This is a bound service" + intent.getIntExtra("count", 0));
//
//            NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//            manager.notify(intent.getIntExtra("count", 0), builder.build());

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void onClick(View view) {
//        thread.start();
        startService(new Intent(this, MyService.class));
    }

    public void onClick1(View view) {
        stopService(new Intent(this, MyService.class));
//        thread.interrupt();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TAG Ac", "onDestroy: ");
    }
}
