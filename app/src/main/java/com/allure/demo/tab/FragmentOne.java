package com.allure.demo.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.allure.demo.R;
import com.allure.demo.lazyfragment.LazyFragmentContent;
import com.allure.fragment.QuickFragment;
import com.allure.fragment.adapter.QuickFragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cherish on 2017/11/27.
 */

public class FragmentOne extends QuickFragment {
    private static final String TAG = "FragmentOne";
    private List<String> title = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private String[] string = new String[]{"Java", "C++", "C", "Python", "Ruby", "Kotlin", "iOS", "Android", "Web"};
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
        TabLayout tabLayout = rootView.findViewById(R.id.tab_layout);
        ViewPager viewPager = rootView.findViewById(R.id.view_pager);

        for (int i = 0; i < string.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putString(LazyFragmentContent.CONTENT_KEY, string[i]);
            title.add(string[i]);
            fragments.add(fragment(LazyFragmentContent.class, bundle));
        }

        QuickFragmentStateAdapter viewPagerAdapter = new QuickFragmentStateAdapter(getChildFragmentManager(), fragments, title);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
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
