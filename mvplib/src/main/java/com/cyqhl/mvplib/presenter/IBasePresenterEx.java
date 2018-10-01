package com.cyqhl.mvplib.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.cyqhl.mvplib.contract.IViewContract;

public class IBasePresenterEx {

    public static Context getContextEx(IBasePresenter basePresenter) {
        if(basePresenter == null){
            throw new RuntimeException("basePresenter can not be null");
        }
        IViewContract mvpView = basePresenter.getMvpView();
        if (mvpView instanceof Activity) {
            return (Activity) mvpView;
        }else if(mvpView instanceof Fragment){
            Fragment fragment = (Fragment) mvpView;
            return fragment.getActivity();
        }else {
            throw new IllegalStateException("the presenter not found context");
        }
    }
}
