package com.allure.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allure.demo.R;
import com.allure.fragment.QuickFragment;

/**
 * Created by Cherish on 2017/11/28.
 */

public class BackStackFragment2 extends QuickFragment {



    @Override
    protected int initFragmentLayout() {
        return R.layout.fragment_backstack2;
    }

    @Override
    protected void initLazy() {

    }

    @Override
    protected void initNotLazy() {

    }

    @Override
    protected void initListener() {

    }
}
