package com.alphabit.dhiyodha.Utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.alphabit.dhiyodha.R;

public class AlertDialog extends Dialog {

    private String message;
    private String title;
    private String btYesText;
    private String btNoText;
    private View.OnClickListener btYesListener=null;
    private View.OnClickListener btNoListener=null;

    public AlertDialog(Context context) {
        super(context);
    }

    public AlertDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialogfragment_alert);

        if (getTitle() != null) {
            TextView tv = (TextView) findViewById(R.id.txt_title);
            tv.setVisibility(View.VISIBLE);
            tv.setText(getTitle());
        }

        if (getMessage() != null) {
            TextView tvmessage = (TextView) findViewById(R.id.txt_message);
            tvmessage.setText(Html.fromHtml(getMessage()));
            tvmessage.setVisibility(View.VISIBLE);
        }

        TextView btYes = (TextView) findViewById(R.id.btn_positive);
        btYes.setText(btYesText);
        btYes.setOnClickListener(btYesListener);

        if (btNoText != null) {
            TextView btNo = (TextView) findViewById(R.id.btn_negative);
            btNo.setVisibility(View.VISIBLE);
            btNo.setText(btNoText);
            btNo.setOnClickListener(btNoListener);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPositiveButton(String yes, View.OnClickListener onClickListener) {
        dismiss();
        this.btYesText = yes;
        this.btYesListener = onClickListener;
    }

    public void setNegativeButton(String no, View.OnClickListener onClickListener) {
        dismiss();
        this.btNoText = no;
        this.btNoListener = onClickListener;
    }
}