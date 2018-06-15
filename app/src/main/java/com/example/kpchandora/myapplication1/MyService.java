package com.example.kpchandora.myapplication1;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class MyService extends Service {

    private Handler handler;
    private int count;
    private Runnable runnable;

    private final String TAG = "Service";

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        count = 0;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("TAG", "onStartCommand: ");


//        NotificationManager manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
//        manager.notify(0, builder.build());

        runnable = new Runnable() {
            @Override
            public void run() {
                sendNotification(count++);

                if (count == 7) {
//                    handler.removeCallbacks(runnable);
                    stopSelf();
                    return;
                }

                handler.postDelayed(runnable, 3000);
            }
        };

        runnable.run();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                sendNotification(count++);
//
//                if (count == 5) {
//                    stopSelf();
//                    handler.removeCallbacks(this);
//                    return;
//                }
//                handler.postDelayed(this, 5000);
//            }
//        }, 0);


        return START_NOT_STICKY;
    }


    private void sendNotification(int count) {
        Log.i(TAG, "sendNotification: " + count);

//        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent().setAction(MainActivity.BD_CAST)
//        .putExtra("count", count));
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "id");
        builder.setSmallIcon(android.R.drawable.ic_notification_clear_all);
        builder.setContentTitle("Test Notification")
                .setContentText("This is a bound service " + count);

        startForeground(1, builder.build());

    }


    @Override
    public void onDestroy() {

        handler.removeCallbacks(runnable);

        super.onDestroy();
        Log.i("TAG", "onDestroy: ");
    }
}
