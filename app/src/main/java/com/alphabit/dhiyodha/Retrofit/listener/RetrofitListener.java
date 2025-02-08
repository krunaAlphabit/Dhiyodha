package com.alphabit.dhiyodha.Retrofit.listener;

import retrofit2.Response;

public interface RetrofitListener {
    void onResponseSuccess(Response response);
    //void onResponseError(ErrorObject errorObject, Throwable throwable, String apiFlag);
}