package com.alphabit.dhiyodha.Retrofit;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiServices {

    @POST()
    Call<JsonElement> sendPostData(@Url String url, @Body JsonObject requestData);

    @PATCH()
    Call<JsonElement> sendPatchData(@Url String url, @Body JsonObject requestData);

    @GET()
    Call<JsonElement> sendGetData(@Url String url);

    @GET()
    Call<JsonElement> sendGetWithQueryData(@Url String url, @Query("userId") String clubId);

    @GET()
    Call<JsonElement> sendGetDataWithHashmap(@Url String url, @Query("searchValue") String searchValue);

    @Multipart
    @POST
    Call<MultipartBody> sendMultiPartData(@Url String url, @Part MultipartBody.Part image);

    @Multipart
    @POST
    Call<JsonElement> uploadImage(@Url String url,
                                  @Part("type") RequestBody requestData,
                                  @Part MultipartBody.Part image);

    @GET()
    Call<JsonElement> getCategoryWiseProduct(@Url String url, @Query("browsePath") String browsePath, @Query("categoryId") String categoryId);

}