package com.cyqhl.mvplib.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.cyqhl.mvplib.contract.IPresenterContract;
import com.cyqhl.mvplib.contract.IViewContract;

import java.lang.reflect.Constructor;

public abstract class MvpFragmentActivity extends FragmentActivity implements IBaseView, IViewContract {
    private IPresenterContract mPresenter;

    public IPresenterContract getPresenter() {
        if (mPresenter == null) {
            throw new RuntimeException("presenter is null");
        }
        return mPresenter;
    }

    private void initPresenter() {
        Class<IPresenterContract> clazz = this.registerPresenter();
        Constructor constructor;
        try {
            constructor = clazz.getConstructor();
            this.mPresenter = (IPresenterContract) constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mPresenter == null) {
            throw new RuntimeException("presenter is null");
        }
        mPresenter.registerMvpView(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initPresenter();
        if (mPresenter != null) {
            mPresenter.onCreate();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onStart();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onPause();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        mPresenter = null;
    }
}
