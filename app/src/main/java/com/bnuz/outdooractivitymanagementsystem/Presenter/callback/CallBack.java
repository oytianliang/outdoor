package com.bnuz.outdooractivitymanagementsystem.Presenter.callback;

public interface CallBack<T> {
    void onSuccess(T response);

    void onError(String t);
}
