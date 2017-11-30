package com.allure.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.allure.demo.R;
import com.allure.fragment.QuickFragment;

/**
 * Created by Cherish on 2017/11/28.
 */

public class BackStackFragment1 extends QuickFragment {

    public static final String KEY = "save";
    public static final int TYPE_SAVE = 1;
    public static final int TYPE_NOT_SAVE = 2;

    private int type = 1;


    @Override
    protected void initListener() {

    }

    @Override
    protected void initLazy() {

    }

    @Override
    protected void initNotLazy() {

    }

    @Override
    protected int initFragmentLayout() {
        return R.layout.fragment_backstack1;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFragment(BackStackFragment2.class);

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
