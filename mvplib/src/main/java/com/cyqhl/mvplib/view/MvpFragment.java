package com.cyqhl.mvplib.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.cyqhl.mvplib.contract.IPresenterContract;
import com.cyqhl.mvplib.contract.IViewContract;

import java.lang.reflect.Constructor;

public abstract class MvpFragment extends Fragment implements IBaseView, IViewContract {
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initPresenter();
        if (mPresenter != null) {
            mPresenter.onCreate();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onStart();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onPause();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        mPresenter = null;
    }
}
