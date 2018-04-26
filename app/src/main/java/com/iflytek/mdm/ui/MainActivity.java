package com.iflytek.mdm.ui;

import android.Manifest;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.iflytek.fsp.shield.android.sdk.http.ApiCallback;
import com.iflytek.fsp.shield.android.sdk.http.ApiProgress;
import com.iflytek.fsp.shield.android.sdk.http.ApiResponse;
import com.iflytek.mdm.MDMApp;
import com.iflytek.mdm.R;
import com.iflytek.mdm.api.ShieldAsyncApp_zhkt_mdm_service;
import com.iflytek.mdm.receiver.AdminReceiver;
import com.iflytek.pushclient.PushManager;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;

/**
 * @Company: 科大讯飞
 * @Title:
 * @Description:
 * @Author 王泽民
 * @Email zmwang7@iflytek.com
 * @Date 2018/4/19 16:28
 * @Version V1.0
 */
public class MainActivity extends Activity {

    boolean flag = false;
    public static final int DPM_REQUEST_CODE = 201603;

    private ComponentName componentName;

    public class BindBroadcastReceiver extends BroadcastReceiver {
        public static final String ACTION = "com.iflytek.xpush.Action.bind";

        public void onReceive(Context context, Intent intent) {
            //MainActivity.this.showInfo();
            Log.i("Blue", "BindBroadcastReceiver.onReceiver");
        }
    }

    private BindBroadcastReceiver bindReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        componentName = new ComponentName(MainActivity.this, AdminReceiver.class);

        setBrightnessMode(this);
        PushManager.cleanCaches(this);
        PushManager.setDebugEnable(true);
        PushManager.startWork(MainActivity.this/*, Constant.XPush.APP_ID*/);
        findViewById(R.id.btn_call_api).setOnClickListener(v -> new Thread(() -> MainActivity.this.callApi()).start());

        findViewById(R.id.btn_install_plugin).setOnClickListener(v -> {
            PluginInfo pluginInfo = RePlugin.install("/sdcard/iflytek/strategy-debug.apk");
            String packageName = pluginInfo.getPackageName();
            Log.i("Blue", "plugin package name: " + packageName);
        });

        findViewById(R.id.btn_start_plugin).setOnClickListener(v -> RePlugin.startActivity(MDMApp.getContext(),
                RePlugin.createIntent("strategy", "com.iflytek.mdm.strategy.MainActivity")));

        findViewById(R.id.btn_call_rom_api).setOnClickListener(v -> {
            /*IRomApi romApi = new RomApi();
            DeviceManager deviceManager = DeviceManager.newInstance(romApi);
            if (flag) {
                deviceManager.disableWifi();
            } else {
                deviceManager.enableWifi();
            }
            flag = !flag;*/
        });
        findViewById(R.id.btn_bugly_test).setOnClickListener(v -> {
//            CrashReport.testJavaCrash();
        });
        findViewById(R.id.btn_check_xpush).setOnClickListener(v -> {
            boolean isRunning = PushManager.isPushEnabled(MainActivity.this);
            Log.i("Blue", "XPush isRunning:" + isRunning);
        });
        findViewById(R.id.btn_throw_exception).setOnClickListener(v -> {
            DevicePolicyManager mDPM = (DevicePolicyManager) MainActivity.this.getSystemService(Context.DEVICE_POLICY_SERVICE);
            mDPM.lockNow();
        });

//        new Thread(() ->  Log.i("Blue", "Test")).start();

        this.bindReceiver = new BindBroadcastReceiver();
        registerReceiver(this.bindReceiver, new IntentFilter(BindBroadcastReceiver.ACTION));
    }

    public void adminActive() {
        // 设备安全管理服务    2.2之前的版本是没有对外暴露的 只能通过反射技术获取
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        // 申请权限
        ComponentName componentName = new ComponentName(this, AdminReceiver.class);
        // 判断该组件是否有系统管理员的权限
        boolean isAdminActive = devicePolicyManager.isAdminActive(componentName);
        if (isAdminActive) {
            devicePolicyManager.lockNow(); // 锁屏
//            devicePolicyManager.resetPassword("123", 0); // 设置锁屏密码
//            devicePolicyManager.wipeData(0);  //恢复出厂设置  (建议大家不要在真机上测试) 模拟器不支持该操作
        } else {
            Intent intent = new Intent();
            // 指定动作名称
            intent.setAction(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
//            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"提示文字");
            // 指定给哪个组件授权
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1122: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Blue", "permission request successed");
                } else {
                    Log.e("Blue", "permission request failed");
                }
                return;
            }
        }
    }

    private void setBrightnessMode(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int permission = ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.LOCATION_HARDWARE,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_SETTINGS}, 1122);
                }
                if (!Settings.System.canWrite(context)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                            Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, 112233);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 112233) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 判断是否有WRITE_SETTINGS权限
                if (Settings.System.canWrite(this)) {
                    PushManager.startWork(this);
                    bindReceiver = new BindBroadcastReceiver();
                    IntentFilter filter = new IntentFilter(BindBroadcastReceiver.ACTION);
                    registerReceiver(bindReceiver, filter);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        PushManager.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        PushManager.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.bindReceiver != null) {
            unregisterReceiver(this.bindReceiver);
        }
    }

    public void callApi() {
        ShieldAsyncApp_zhkt_mdm_service service = new ShieldAsyncApp_zhkt_mdm_service();
        service.getDemoList(new ApiCallback<Object>() {
            @Override
            public void onDownloadProgress(ApiProgress apiProgress) {
                Log.i("Blue", "api.onDownloadProgress");
            }

            @Override
            public void onHttpDone() {
                Log.i("Blue", "api.onHttpDone");
            }

            @Override
            public void onSuccess(ApiResponse apiResponse, Object o) {
                Log.i("Blue", "api.onSuccess");
            }

            @Override
            public void onFailed(ApiResponse apiResponse) {
                Log.i("Blue", "api.onFailed"+ apiResponse.getStatusCode());
            }

            @Override
            public void onException(Exception e) {
                Log.i("Blue", "api.onException:" + e.getMessage());
            }
        }, "blue");
    }
}
