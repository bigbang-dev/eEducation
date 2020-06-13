package com.bigbang;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;

import java.util.Map;

import com.bigbang.base.PreferenceManager;
import com.bigbang.base.ToastManager;
import com.bigbang.log.LogManager;
import com.bigbang.service.bean.response.AppConfigRes;

public class EduApplication extends MultiDexApplication {

    public static EduApplication instance;

    private AppConfigRes config;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        LogManager.init(this, BuildConfig.EXTRA);
        PreferenceManager.init(this);
        ToastManager.init(this);
    }

    @Nullable
    public static String getAppId() {
        if (instance.config == null) return null;
        return instance.config.appId;
    }

    public static void setAppId(String appId) {
        if (instance.config == null) {
            instance.config = new AppConfigRes();
        }
        instance.config.appId = appId;
    }

    @Nullable
    public static Map<String, Map<Integer, String>> getMultiLanguage() {
        if (instance.config == null) return null;
        return instance.config.multiLanguage;
    }

    public static void setMultiLanguage(Map<String, Map<Integer, String>> multiLanguage) {
        if (instance.config == null) {
            instance.config = new AppConfigRes();
        }
        instance.config.multiLanguage = multiLanguage;
    }

}
