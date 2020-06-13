package com.bigbang.base;

public interface Callback<T> {

    void onSuccess(T res);

    void onFailure(Throwable throwable);

}
