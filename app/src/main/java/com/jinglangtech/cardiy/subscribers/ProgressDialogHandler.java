package com.jinglangtech.cardiy.subscribers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private ProgressDialog pd;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener,
                                 boolean cancelable) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    private void initProgressDialog(String msg){
        if (pd == null) {
            pd = new ProgressDialog(context, ProgressDialog.THEME_HOLO_DARK);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            if (!TextUtils.isEmpty(msg)) {
                pd.setMessage(msg);
            } else {
                pd.setMessage("请求数据中...");
            }
            pd.setCancelable(cancelable);

            if (cancelable) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }

            if (!pd.isShowing()) {
                pd.show();
            }
        }
    }

    private void dismissProgressDialog(){
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                String showMsg = (String) msg.obj;
                initProgressDialog(showMsg);
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
            default:
                break;
        }
    }

}
