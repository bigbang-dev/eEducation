package com.bigbang.log.service;

import java.util.Map;

import com.bigbang.BuildConfig;
import com.bigbang.log.service.bean.ResponseBody;
import com.bigbang.log.service.bean.response.AppVersionRes;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CommonService {

    // osType 1 iOS 2 Android
    // terminalType 1 phone 2 pad
    @GET("/edu/v1/app/version?appCode=" + BuildConfig.CODE + "&osType=2&terminalType=1&appVersion=" + BuildConfig.VERSION_NAME)
    Call<ResponseBody<AppVersionRes>> appVersion();

    @GET("/edu/v1/multi/language")
    Call<ResponseBody<Map<String, Map<Integer, String>>>> language();

}
