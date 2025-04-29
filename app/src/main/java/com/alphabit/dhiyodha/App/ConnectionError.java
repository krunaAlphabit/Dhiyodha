package com.alphabit.dhiyodha.App;

import android.content.Context;
import android.view.View;

import com.alphabit.dhiyodha.Utils.AlertDialog;
import com.google.gson.JsonSyntaxException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ConnectionError {

    public Listener mListener;

    public ConnectionError(Context context, Throwable t) {
        t.printStackTrace();
        if (t instanceof SocketTimeoutException)
            showPopup(context, "Request time out, please retry your request.");

        else if (t instanceof UnknownHostException)
            showPopup(context, "The connection to the server was unsuccessful. Please retry your request.");

        else if (t instanceof JsonSyntaxException)
            showPopup(context, "Could not complete your request because of a problem parsing the data.");

        else
            showPopup(context, "Oops!! It looks like you are not connected with internet, please check your internet connection and try again.");
    }


    private void showPopup(Context context, String error) {
        final AlertDialog alertDialog = new AlertDialog(context);
        alertDialog.setCancelable(false);
        alertDialog.setTitle("Connection Error");
        alertDialog.setMessage(error);
        alertDialog.setPositiveButton("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                mListener.returnData();
            }
        });
        alertDialog.setNegativeButton("Cancel", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        //if (!((Activity)context).isFinishing() && !((Activity)context).isDestroyed())
        alertDialog.show();
    }


    /**
     * Method is used to set the listener...
     *
     * @param listener
     */
    public void setListener(Listener listener) {
        mListener = listener;
    }


    /**
     * INTERFACE is used to get the listener when dialog is closed...
     */
    public static interface Listener {
        void returnData();
    }
}
