package com.jinglangtech.cardiy.subscribers;

import android.content.Context;
import android.os.Message;
import android.widget.Toast;

import com.jinglangtech.cardiy.ApiKey;
import com.jinglangtech.cardiy.utils.Logger;
import com.jinglangtech.cardiy.utils.SPUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Description:
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 */
public class ProgressObserver<T> implements ProgressCancelListener,Observer<T> {

    private String msg;
    private ObserverOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;
    private Disposable mDisposable;

    public ProgressObserver(ObserverOnNextListener mSubscriberOnNextListener, Context context,String msg) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        this.msg = msg;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    private void showProgressDialog() {
        Logger.LOGD("showProgressDialog");
        if (mProgressDialogHandler != null) {
            Message message = mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG);
            message.obj = msg;
            mProgressDialogHandler.sendMessage(message);
        }
    }

    private void dismissProgressDialog() {
        Logger.LOGD("dismissProgressDialog");
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        Logger.LOGD("onError");
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
            // 是否可用gprs
            boolean isMobile = (boolean) SPUtil.get(context, ApiKey.QFB_MOBILE, false);
            // 是否可用wifi
            boolean isWifi = (boolean) SPUtil.get(context, ApiKey.QFB_WIFI, false);
            if (!isWifi && !isMobile) {
                Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        dismissProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onComplete() {
        Logger.LOGD("ProgressObserver onComplete");
        dismissProgressDialog();
        unSubcribe();
    }

    @Override
    public void onSubscribe(Disposable d) {
        Logger.LOGD("onSubscribe");
        this.mDisposable = d;
        showProgressDialog();
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        Logger.LOGD("onNext");
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        Logger.LOGD("onCancelProgress");
        unSubcribe();
    }

    private void unSubcribe() {
        Logger.LOGD("unSubcribe");
        if (this.mDisposable != null && !this.mDisposable.isDisposed()) {
            this.mDisposable.isDisposed();
            Logger.LOGD("isDisposed");
        }
    }
}