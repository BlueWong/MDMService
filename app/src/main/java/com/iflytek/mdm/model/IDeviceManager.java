package com.iflytek.mdm.model;

/**
 * @Company: 科大讯飞-教育事业群
 * @Title:
 * @Description:
 * @Author 王泽民
 * @Email zmwang7@iflytek.com
 * @Date 2018/4/10 18:19
 * @Version V1.0
 */
public interface IDeviceManager {

    void enableCamera();

    void disableCamera();

    void enableUsb();

    void disableUsb();

    void enableWIFI();

    void disableWIFI();

    void enableBluetooth();

    void disableBluetooth();

    void enableApp();

    void disableApp();

    void enableNavigation();

    void disableNavigation();

    void enableHome();

    void disableHome();

    void enableBack();

    void disableBack();

    void enableVolume();

    void disableVolume();

    void enableShortPressPower();

    void disableShortPressPower();

    void enableLongPressPower();

    void disableLongPressPower();

    void silentInstall();

    void silentUpgrade();

    void silentDowngrade();

    void silentUninstall();

    void setScreenPassword();

    void clearLockedScreenPassword();

    void resetFactory();

    void appWhiteListWrite();

    void appWhiteListRead();

    void urlWhiteListWrite();

    void urlWhiteListRead();

    /**
     * 应用访问网络白名单
     */
    void setNetworkAccessAppWhiteList();

    /**
     * 应用访问网络白名单
     */
    void getNetworkAccessAppWhiteList();

    /**
     * 应用访问网络白名单
     */
    void cleanNetworkAccessAppWhiteList();

    void enableMobileData();

    void disableMobileData();

    void enableGPS();

    void disableGPS();

    void callDeviceAdmin();

    void activeDeviceAdmin();

    void modifySystemTime();

    void enableNFC();

    void disableNFC();

    void enableSpeech();

    void disableSpeech();

    void showWifiAdvanced();

    void hideWifiAdvancecd();

    void resetSettings();

    void enableOTA();

    void disableOTA();

    /**
     * 限制网络
     */
    void enableFireWall();

    /**
     * 放开网络
     */
    void disableFireWall();

    void enableScreenShot();

    void disableScreenShot();

    void enableOTG();

    void disableOTG();

    void enableSdCard();

    void disableSdCard();



}
