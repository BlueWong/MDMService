package com.iflytek.mdmsdk;

import android.content.Context;
import android.content.Intent;

public class IflytekMDM {

    interface MDMIntent {
        String ACTION_DISABLE_CAMERA = "com.iflytek.mdm.intent.ACTION_DISABLE_CAMERA";
        String ACTION_DISABLE_APP = "com.iflytek.mdm.intent.ACTION_DISABLE_APP";
    }

    private static IflytekMDM instance;
    private Context mContext;

    private IflytekMDM(Context context) {
        this.mContext = context;
    }

    public IflytekMDM getInstance(Context context) {
        if (instance == null) {
            synchronized (IflytekMDM.class) {
                if (instance == null) {
                    instance = new IflytekMDM(context);
                }
            }
        }
        return instance;
    }

    //禁用摄像头
    public void disableCamera() {
        Intent intent = new Intent();
        intent.setAction(MDMIntent.ACTION_DISABLE_CAMERA);
        //签名标示
        intent.putExtra("flag", mContext.getPackageName());
        intent.putExtra("sign", "");
        mContext.sendBroadcast(intent);
    }

    public void disableApp(String packageName) {
        Intent intent = new Intent();
        intent.setAction(MDMIntent.ACTION_DISABLE_APP);
        //签名标示
        intent.putExtra("packageName", packageName);
        intent.putExtra("flag", mContext.getPackageName());
        intent.putExtra("sign", "");
        mContext.sendBroadcast(intent);
    }
}
