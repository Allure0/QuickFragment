package com.allure.demo.tab;

import android.util.Log;

import com.allure.demo.R;
import com.allure.fragment.QuickFragment;

/**
 * Created by Cherish on 2017/11/27.
 */

public class FragmentFourth extends QuickFragment {

    private static final String TAG = "FragmentFourth";
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
        return R.layout.fragment_fourth;
    }
}
