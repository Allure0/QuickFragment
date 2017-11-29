package com.allure.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.allure.demo.R;
import com.allure.fragment.QuickActivity;

/**
 * Created by Cherish on 2017/11/28.
 */

public class FlowModeActivity extends QuickActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flowmode_activity);

        startFragment(StartFragment.class);

    }

    @Override
    public boolean useAnim() {
        return true;
    }

    @Override
    public int fragmentId() {
        return R.id.rootview;
    }

}
