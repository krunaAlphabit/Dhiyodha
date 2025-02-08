package com.alphabit.dhiyodha.Retrofit.utils;

import android.content.Context;

import com.alphabit.dhiyodha.App.Constants;
import com.alphabit.dhiyodha.Retrofit.model.ErrorObject;
import com.google.gson.Gson;

import org.json.JSONObject;

public class HttpUtil {
    private static Logger logger = new Logger(HttpUtil.class.getSimpleName());

    /**
     * This method returns a Json object for handling Force update error
     *
     * @return
     */
    public static JSONObject getServerErrorJsonObject(Context context) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.ErrorClass.STATUS, 505);
            jsonObject.put(Constants.ErrorClass.CODE, 3000);
            jsonObject.put(Constants.ErrorClass.MESSAGE, "Server error");
            jsonObject.put(Constants.ErrorClass.DEVELOPER_MESSAGE, "Server error");
        } catch (Exception e) {
            logger.error(e);
        }
        return jsonObject;
    }

    /**
     * This method returns a Json object for handling Force update error
     *
     * @return
     */
    public static ErrorObject getServerErrorPojo(Context context) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(getServerErrorJsonObject(context).toString(), ErrorObject.class);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
}