package com.bigbang.whiteboard.netless.manager;

import androidx.annotation.NonNull;

import com.bigbang.whiteboard.BuildConfig;
import com.herewhite.sdk.domain.Promise;
import com.herewhite.sdk.domain.SDKError;

import com.bigbang.base.Callback;
import com.bigbang.base.network.RetrofitManager;

import com.bigbang.whiteboard.netless.service.NetlessService;
import com.bigbang.whiteboard.netless.service.bean.ResponseBody;
import com.bigbang.whiteboard.netless.service.bean.response.RoomJoin;

abstract class NetlessManager<T> {

    T t;
    Promise<T> promise = new Promise<T>() {
        @Override
        public void then(T t) {
            NetlessManager.this.t = t;
            onSuccess(t);
        }

        @Override
        public void catchEx(SDKError t) {
            onFail(t);
        }
    };

    abstract void onSuccess(T t);

    abstract void onFail(SDKError error);

    public void roomJoin(String uuid, String sdkToken, @NonNull Callback<RoomJoin> callback) {
        RetrofitManager.instance().getService(BuildConfig.API_BASE_URL, NetlessService.class)
                .roomJoin(uuid, sdkToken).enqueue(new RetrofitManager.Callback<>(200, new Callback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody res) {
                callback.onSuccess(res.msg);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onFailure(throwable);
            }
        }));
    }

}
