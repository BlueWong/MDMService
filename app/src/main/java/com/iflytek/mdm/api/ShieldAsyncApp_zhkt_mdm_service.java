package com.iflytek.mdm.api;

import com.iflytek.fsp.shield.android.sdk.constant.HttpConstant;
import com.iflytek.fsp.shield.android.sdk.constant.SdkConstant;
import com.iflytek.fsp.shield.android.sdk.enums.Method;
import com.iflytek.fsp.shield.android.sdk.enums.ParamPosition;
import com.iflytek.fsp.shield.android.sdk.http.ApiCallback;
import com.iflytek.fsp.shield.android.sdk.http.ApiClient;
import com.iflytek.fsp.shield.android.sdk.http.ApiRequest;
import com.iflytek.fsp.shield.android.sdk.http.BaseApp;

public class ShieldAsyncApp_zhkt_mdm_service extends BaseApp {

    public ShieldAsyncApp_zhkt_mdm_service() {
        this.apiClient = new ApiClient();
        this.appId = "zhkt-mdm-service";
        this.appSecret = "8bbddfa50a70a6f9";
        this.host = "pre.api.changyan.com";
        this.httpPort = 80;
        this.httpsPort = 443;
        this.stage = "PRE";
        this.publicKey = "请从Epas支撑组获取";
    }
    
    /**
    * Version:201804191729038640
    */
    public <T> void getDemoList(ApiCallback<T> apiCallback, Object tag) {
        ApiRequest apiRequest = new ApiRequest(HttpConstant.SCHEME_HTTP, Method.POST, "/mdm", SdkConstant.AUTH_TYPE_DEFAULT, "9f23723f6a9b47cfa71e4243f70d1174");
        
        
        asyncInvoke(apiRequest, apiCallback, tag);
    }

    
    /**
    * Version:201803302113243383
    */
    public <T> void getAreaById(String id, ApiCallback<T> apiCallback, Object tag) {
        ApiRequest apiRequest = new ApiRequest(HttpConstant.SCHEME_HTTP, Method.POST, "/user/basis/area", SdkConstant.AUTH_TYPE_DEFAULT, "f5f03b6b32bc431f8a6935ea6d42981a");
        
        
        apiRequest.addParam("id", id, ParamPosition.FORM, false);
        
        asyncInvoke(apiRequest, apiCallback, tag);
    }

    
}
