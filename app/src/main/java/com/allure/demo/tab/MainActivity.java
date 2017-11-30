package com.allure.demo.tab;


import android.os.Bundle;
import android.view.View;

import com.allure.demo.R;
import com.allure.fragment.QuickActivity;

public class MainActivity extends QuickActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        showFragment(FragmentOne.class);
        showFragment(new FragmentOne());
    }

    @Override
    public boolean useAnim() {
        return true;
    }

    @Override
    public int enterAnim() {
        return super.enterAnim();
    }

    @Override
    public int exitAnim() {
        return super.exitAnim();
    }

    @Override
    public int popEnterAnim() {
        return super.popEnterAnim();
    }

    @Override
    public int popExitAnim() {
        return super.popExitAnim();
    }

    @Override
    public int fragmentId() {
        return R.id.fragment_rootview;
    }


    public void one(View view) {
        showFragment(FragmentOne.class);

    }

    public void two(View view) {
        showFragment(FragmentTwo.class);
    }

    public void three(View view) {
        showFragment(FragmentThree.class);
    }

    public void fourth(View view) {
        showFragment(FragmentFourth.class);
    }
}
