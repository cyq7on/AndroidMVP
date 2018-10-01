package com.cyqhl.mvplib.view;

import com.cyqhl.mvplib.contract.IPresenterContract;

public interface IBaseView {
    Class<IPresenterContract> registerPresenter();
}
