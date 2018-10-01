package com.cyqhl.mvplib.contract;

public interface IPresenterContract {
    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void registerMvpView(IViewContract mvpView);
}
