package com.alphabit.dhiyodha.Retrofit;

import android.content.Context;

import com.alphabit.dhiyodha.App.ConnectionError;
import com.alphabit.dhiyodha.App.Constants;
import com.alphabit.dhiyodha.Retrofit.listener.RetrofitListener;
import com.alphabit.dhiyodha.Utils.AppUtils;
import com.alphabit.dhiyodha.Utils.LoadingDialog;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiServiceProvider extends RetrofitBase {
    private ApiServices apiServices;

    private LoadingDialog mDialog;

    private String mApiUrl;

    private ApiServiceProvider(Context context) {
        super(context, "", true);
        mDialog = new LoadingDialog(context);
        apiServices = retrofit.create(ApiServices.class);
    }

    private ApiServiceProvider(Context context, String baseUrl) {
        super(context, baseUrl, true);
        mDialog = new LoadingDialog(context);
        apiServices = retrofit.create(ApiServices.class);
    }

    public static ApiServiceProvider getInstance(Context context) {
        return new ApiServiceProvider(context);
    }

    public static ApiServiceProvider getInstance(Context context, String baseUrl) {
        return new ApiServiceProvider(context, baseUrl);
    }


    private void manageResponse(Response response, RetrofitListener retrofitListener) {
        switch (response.code()) {
            case Constants.ResponseCode.CODE_200:
            case Constants.ResponseCode.CODE_204:
                validateResponse(response, retrofitListener);
                break;

            case Constants.ResponseCode.CODE_500:
            case Constants.ResponseCode.CODE_400:
            case Constants.ResponseCode.CODE_401:
            case Constants.ResponseCode.CODE_403:
            case Constants.ResponseCode.CODE_404:
            case Constants.ResponseCode.CODE_422:
            case Constants.ResponseCode.CODE_428:
            case Constants.ResponseCode.CODE_429:
                try {
                    JSONObject resObj = new JSONObject(response.errorBody().string());
                    if (resObj.has("message")) {
                        AppUtils.INSTANCE.showAlertDialog(context, resObj.getString("message"), "Ok");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void dismissDialog() {
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
    }


    /**
     * Used to get list of country...
     *
     * @param retrofitListener
     */
    public void sendPostData(String url, JsonObject requestData, Boolean isProgressShown, final RetrofitListener retrofitListener) {
        if (isProgressShown)
            mDialog.show();

        mApiUrl = url;
        Call<JsonElement> call = apiServices.sendPostData(url, requestData);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                dismissDialog();
                manageResponse(response, retrofitListener);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                dismissDialog();
                t.printStackTrace();
                new ConnectionError(context, t).setListener(() -> sendPostData(url, requestData, isProgressShown, retrofitListener));
                //retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), t, Constants.UrlPath.IS_EMAIL_EXISTS);
            }
        });
    }

    public void sendPatchData(String url, JsonObject requestData, Boolean isProgressShown, final RetrofitListener retrofitListener) {
        if (isProgressShown)
            mDialog.show();

        mApiUrl = url;
        Call<JsonElement> call = apiServices.sendPatchData(url, requestData);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                dismissDialog();
                manageResponse(response, retrofitListener);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                dismissDialog();
                t.printStackTrace();
                new ConnectionError(context, t).setListener(() -> sendPatchData(url, requestData, isProgressShown, retrofitListener));
                //retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), t, Constants.UrlPath.IS_EMAIL_EXISTS);
            }
        });
    }

    public void sendGetData(String url, Boolean isProgressShown, final RetrofitListener retrofitListener) {
        if (isProgressShown)
            mDialog.show();
        mApiUrl = url;
        Call<JsonElement> call = apiServices.sendGetData(url);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                dismissDialog();
                manageResponse(response, retrofitListener);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                dismissDialog();
                t.printStackTrace();
                new ConnectionError(context, t).setListener(() -> sendGetData(url, isProgressShown, retrofitListener));
            }
        });
    }

    public void sendGetDataWithHashmap(String url, String map, Boolean isProgressShown, final RetrofitListener retrofitListener) {
        if (isProgressShown)
            mDialog.show();
        mApiUrl = url;
        Call<JsonElement> call = apiServices.sendGetDataWithHashmap(url, map);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                dismissDialog();
                manageResponse(response, retrofitListener);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                dismissDialog();
                t.printStackTrace();
                new ConnectionError(context, t).setListener(() -> sendGetDataWithHashmap(url, map, isProgressShown, retrofitListener));
            }
        });
    }

    public void sendGetWithQueryData(String url, String clubId, Boolean isProgressShown, final RetrofitListener retrofitListener) {
        if (isProgressShown)
            mDialog.show();

        mApiUrl = url;
        Call<JsonElement> call = apiServices.sendGetWithQueryData(url, clubId);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                dismissDialog();
                manageResponse(response, retrofitListener);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                dismissDialog();
                t.printStackTrace();
                new ConnectionError(context, t).setListener(() -> sendGetWithQueryData(url, clubId, isProgressShown, retrofitListener));
            }
        });
    }

    public void sendMultiPartData(String url, MultipartBody.Part requestData, Boolean isProgressShown, final RetrofitListener retrofitListener) {
        if (isProgressShown)
            mDialog.show();

        mApiUrl = url;
        Call<MultipartBody> call = apiServices.sendMultiPartData(url, requestData);
        call.enqueue(new Callback<MultipartBody>() {
            @Override
            public void onResponse(Call<MultipartBody> call, Response<MultipartBody> response) {
                dismissDialog();
                manageResponse(response, retrofitListener);
            }

            @Override
            public void onFailure(Call<MultipartBody> call, Throwable t) {
                dismissDialog();
                t.printStackTrace();
                new ConnectionError(context, t).setListener(() -> sendMultiPartData(url, requestData, isProgressShown, retrofitListener));
                //retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), t, Constants.UrlPath.IS_EMAIL_EXISTS);
            }
        });
    }

    public void uploadImage(String url, RequestBody requestData, MultipartBody.Part image, Boolean isProgressShown, final RetrofitListener retrofitListener) {
        if (isProgressShown)
            mDialog.show();
        mApiUrl = url;
        Call<JsonElement> call = apiServices.uploadImage(url, requestData, image);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                dismissDialog();
                manageResponse(response, retrofitListener);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                dismissDialog();
                t.printStackTrace();
                new ConnectionError(context, t).setListener(() -> uploadImage(url, requestData, image, isProgressShown, retrofitListener));
            }
        });
    }

    public void uploadProfilePictures(String url, MultipartBody.Part image, Boolean isProgressShown, final RetrofitListener retrofitListener) {
        if (isProgressShown)
            mDialog.show();
        mApiUrl = url;
        Call<JsonElement> call = apiServices.uploadProfilePictures(url, image);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                dismissDialog();
                manageResponse(response, retrofitListener);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                dismissDialog();
                t.printStackTrace();
                new ConnectionError(context, t).setListener(() -> uploadProfilePictures(url, image, isProgressShown, retrofitListener));
            }
        });
    }

    public void addStory(String url, RequestBody expiresInDays, RequestBody expiresInHours, RequestBody type, MultipartBody.Part image, Boolean isProgressShown, final RetrofitListener retrofitListener) {
        if (isProgressShown)
            mDialog.show();
        mApiUrl = url;
        Call<JsonElement> call = apiServices.addStory(url, expiresInDays, expiresInHours, type, image);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                dismissDialog();
                manageResponse(response, retrofitListener);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                dismissDialog();
                t.printStackTrace();
                new ConnectionError(context, t).setListener(() -> addStory(url, expiresInDays, expiresInHours, type, image, isProgressShown, retrofitListener));
            }
        });
    }

    public void addGroupStory(String url, RequestBody expiresInDays, RequestBody expiresInHours, RequestBody type, RequestBody groupId, MultipartBody.Part image, Boolean isProgressShown, final RetrofitListener retrofitListener) {
        if (isProgressShown)
            mDialog.show();
        mApiUrl = url;
        Call<JsonElement> call = apiServices.addGroupStory(url, expiresInDays, expiresInHours, type, groupId, image);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                dismissDialog();
                manageResponse(response, retrofitListener);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                dismissDialog();
                t.printStackTrace();
                new ConnectionError(context, t).setListener(() -> addGroupStory(url, expiresInDays, expiresInHours, type, groupId, image, isProgressShown, retrofitListener));
            }
        });
    }

    public void createProfile(String url, RequestBody bio, RequestBody education, RequestBody work, RequestBody basicInfo, RequestBody contactInfo, MultipartBody.Part image, Boolean isProgressShown, final RetrofitListener retrofitListener) {
        if (isProgressShown)
            mDialog.show();
        mApiUrl = url;
        Call<JsonElement> call = apiServices.createProfile(url, bio, education, work, basicInfo, contactInfo, image);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                dismissDialog();
                manageResponse(response, retrofitListener);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                dismissDialog();
                t.printStackTrace();
                new ConnectionError(context, t).setListener(() -> createProfile(url, bio, education, work, basicInfo, contactInfo, image, isProgressShown, retrofitListener));
            }
        });
    }

    public void getPerticularAllMessages(String url, int offset, int limit, String roomId, Boolean isProgressShown, final RetrofitListener retrofitListener) {
        if (isProgressShown) mDialog.show();

        mApiUrl = url;
        Call<JsonElement> call = apiServices.getPerticularUserMessages(url, offset, limit, roomId);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                dismissDialog();
                manageResponse(response, retrofitListener);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                dismissDialog();
                t.printStackTrace();
                new ConnectionError(context, t).setListener(() -> getPerticularAllMessages(url, offset, limit, roomId, isProgressShown, retrofitListener));
            }
        });
    }

    public void getFollowersList(String url, String type, Boolean isProgressShown, final RetrofitListener retrofitListener) {
        if (isProgressShown) mDialog.show();

        mApiUrl = url;
        Call<JsonElement> call = apiServices.getFollowersList(url, type);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                dismissDialog();
                manageResponse(response, retrofitListener);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                dismissDialog();
                t.printStackTrace();
                new ConnectionError(context, t).setListener(() -> getFollowersList(url, type, isProgressShown, retrofitListener));
            }
        });
    }
}