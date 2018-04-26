package com.iflytek.mdm.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Company: 科大讯飞-教育事业群
 * @Title:
 * @Description:
 * @Author 王泽民
 * @Email zmwang7@iflytek.com
 * @Date 2018/4/9 11:17
 * @Version V1.0
 */
public class MonitorService extends Service {

    private static final String TAG = MonitorService.class.getSimpleName();
    private int num = 1;

    private ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

    public class MyBinder extends Binder {
        public MonitorService getService() {
            return MonitorService.this;
        }
    }

    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Blue", "MonitorService.onCreate");

        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "num = " + num++);
                //DeviceManager.callAPI();
            }
        }, 0, 3, TimeUnit.SECONDS);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

}
