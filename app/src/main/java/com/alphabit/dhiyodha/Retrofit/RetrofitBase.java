package com.alphabit.dhiyodha.Retrofit;

import android.content.Context;

import com.alphabit.dhiyodha.App.Constants;
import com.alphabit.dhiyodha.App.SessionManager;
import com.alphabit.dhiyodha.Retrofit.listener.RetrofitListener;
import com.alphabit.dhiyodha.Retrofit.model.ErrorObject;
import com.alphabit.dhiyodha.Retrofit.utils.HttpUtil;
import com.alphabit.dhiyodha.Retrofit.utils.Logger;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBase {
    protected Retrofit retrofit;
    protected Context context;
    private Logger logger;

    private SessionManager mSessionManager;

    public RetrofitBase(Context context, String baseUrl, boolean addTimeout) {
        this.context = context;

        mSessionManager = new SessionManager(context);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder().addInterceptor(interceptor);
        if (addTimeout) {
            httpClientBuilder.readTimeout(Constants.TimeOut.SOCKET_TIME_OUT, TimeUnit.SECONDS);
            httpClientBuilder.connectTimeout(Constants.TimeOut.CONNECTION_TIME_OUT, TimeUnit.SECONDS);
        } else {
            httpClientBuilder.readTimeout(Constants.TimeOut.IMAGE_UPLOAD_SOCKET_TIMEOUT, TimeUnit.SECONDS);
            httpClientBuilder.connectTimeout(Constants.TimeOut.IMAGE_UPLOAD_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        }
        addVersioningHeaders(httpClientBuilder, context);

        OkHttpClient httpClient = httpClientBuilder.build();

        logger = new Logger(RetrofitBase.class.getSimpleName());

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    private void addVersioningHeaders(OkHttpClient.Builder builder, Context context) {
        final String appVersion = "v.1.0.1";
        //final int version = AppUtils.INSTANCE.getApplicationVersionCode(context);
        final String appName = "RetroKit";
        final String name = "RetroKit";
        builder.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                String authToken = mSessionManager.getAuthToken(SessionManager.AUTH_TOKEN);
                Request request;
                if (authToken.isEmpty()) {
                    request = chain.request().newBuilder()
                            .addHeader(appName, name)
                            .build();
                } else {
                    request = chain.request().newBuilder()
                            .addHeader(appName, name)
                            .addHeader("Authorization", "Bearer " + authToken)
                            .build();
                }
                return chain.proceed(request);
            }
        });

        System.out.println("AUTH== " + mSessionManager.getAuthToken(SessionManager.AUTH_TOKEN));
    }


    void validateResponse(Response response, RetrofitListener retrofitListener, String apiFlag) {
        if (response.code() == 200) {
            try {
                retrofitListener.onResponseSuccess(response);
            } catch (Exception e) {
                error(response, retrofitListener, apiFlag);
            }
        } else {
            error(response, retrofitListener, apiFlag);
        }
    }

    void validateResponse(Response response, RetrofitListener retrofitListener) {
        if (response.code() == 200 || response.code() == 204 || response.code() == 404 || response.code() == 428 || response.code() == 401) {
            try {
                retrofitListener.onResponseSuccess(response);
            } catch (Exception e) {
                error(response, retrofitListener);
            }
        } else {
            error(response, retrofitListener);
        }
    }

    private void error(Response response, RetrofitListener retrofitListener, String apiFlag) {
        Gson gson = new Gson();
        ErrorObject errorPojo;
        try {
            errorPojo = gson.fromJson((response.errorBody()).string(), ErrorObject.class);
            if (errorPojo == null) {
                errorPojo = HttpUtil.getServerErrorPojo(context);
            }
            //retrofitListener.onResponseError(errorPojo, null, apiFlag);
        } catch (Exception e) {
            //retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), null, apiFlag);
        }
    }

    private void error(Response response, RetrofitListener retrofitListener) {
        Gson gson = new Gson();
        ErrorObject errorPojo;
        try {
            errorPojo = gson.fromJson((response.errorBody()).string(), ErrorObject.class);
            if (errorPojo == null) {
                errorPojo = HttpUtil.getServerErrorPojo(context);
            }
            //retrofitListener.onResponseError(errorPojo, null, apiFlag);
        } catch (Exception e) {
            //retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), null, apiFlag);
        }
    }
}