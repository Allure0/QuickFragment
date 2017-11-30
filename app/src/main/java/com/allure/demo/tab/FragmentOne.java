package com.allure.demo.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.allure.demo.R;
import com.allure.fragment.QuickFragment;

/**
 * Created by Cherish on 2017/11/27.
 */

public class FragmentOne extends QuickFragment {
    private static final String TAG = "FragmentOne";

    @Override
    protected void initListener() {

    }

    @Override
    protected void initLazy() {
        Log.d("initLazy",TAG);
    }

    @Override
    protected void initNotLazy() {
        Log.d("initNotLazy",TAG);
    }

    @Override
    protected int initFragmentLayout() {
        return R.layout.fragment_one;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
