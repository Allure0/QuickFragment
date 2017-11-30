package com.allure.demo.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.allure.demo.R;
import com.allure.fragment.QuickFragment;

/**
 * Created by Cherish on 2017/11/27.
 */

public class FragmentTwo extends QuickFragment {
    private static final String TAG = "FragmentTwo";



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
        return R.layout.fragment_two;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button button = (Button) view.findViewById(R.id.buttonPanel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
