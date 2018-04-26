package com.iflytek.mdm.receiver;

import android.content.Context;
import android.util.Log;

import com.iflytek.pushclient.PushReceiver;

/**
 * @Company: 科大讯飞
 * @Title:
 * @Description:
 * @Author 王泽民
 * @Email zmwang7@iflytek.com
 * @Date 2018/4/23 13:52
 * @Version V1.0
 */
public class XPushReceiver extends PushReceiver {

    @Override
    protected void onBind(Context context, String did, String appId, int errorCode) {
        Log.i("Blue", "onBind | did = " + did + ",appId = " + appId + ", errorCode = " + errorCode);
        //context.sendBroadcast(new Intent(MainActivity.BindBroadcastReceiver.ACTION));
    }

    @Override
    protected void onUnBind(Context context, String did, String appId, int errorCode) {
        Log.i("Blue", "onUnBind | did = " + did + ",appId = " + appId + ", errorCode = " + errorCode);
    }

    @Override
    protected void onMessage(Context context, String msgId, byte[] content) {
        Log.i("Blue", "onMessage | msgId = " + msgId + ", content = " + new String(content));
    }

    @Override
    protected void onClickNotification(Context context, String messageId, String title, String content, String extraContent) {
        Log.i("Blue", "XPushReceiver.onClickNotification: " + messageId + "<><>" + title + "<><>" + content + "<><>" + extraContent);
    }

    @Override
    protected void onTags(Context context, String s, String s1, int i) {
        Log.i("Blue", "XPushReceiver.onTags: " + s + "<><>" + s1 + "<><>" + i);
    }

    @Override
    protected void onStateChanged(Context context, int i) {
        Log.i("Blue", "XPushReceiver.onStateChanged: " + i);
    }
}
