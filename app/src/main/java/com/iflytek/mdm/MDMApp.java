package com.iflytek.mdm;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.iflytek.mdm.util.Constant;
import com.iflytek.pushclient.PushManager;
import com.qihoo360.replugin.RePluginApplication;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * @Company: 科大讯飞-教育事业群
 * @Title:
 * @Description:
 * @Author 王泽民
 * @Email zmwang7@iflytek.com
 * @Date 2018/4/9 14:29
 * @Version V1.0
 */
public class MDMApp extends RePluginApplication {

    private static Context mContext;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("Blue", "MDMApp.onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("Blue", "MDMApp.onServiceDisconnected");
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Blue", "MDMApp.onCreate");
        mContext = this;
//        Intent intent = new Intent(this, MonitorService.class);
//        bindService(intent, connection, BIND_AUTO_CREATE);

        initBugly();

//        initXPush();
    }

    public static Context getContext() {
        return mContext;
    }

    private void initBugly() {
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(mContext);
        //在这里设置strategy的属性，在bugly初始化时传入
//        strategy.setAppChannel("myChannel");  //设置渠道
//        strategy.setAppVersion("1.0.1");      //App的版本
//        strategy.setAppPackageName("com.tencent.xx");  //App的包名
        strategy.setAppReportDelay(20000);//Bugly会在启动20s后联网同步数据
        CrashReport.initCrashReport(mContext, Constant.Bugly.APP_ID, true, strategy);
        CrashReport.setIsDevelopmentDevice(mContext, BuildConfig.DEBUG);
    }

    private void initXPush() {
        PushManager.setDebugEnable(BuildConfig.DEBUG);
    }
}
