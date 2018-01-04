package com.allure.demo.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    protected int initFragmentLayout() {
        return R.layout.fragment_one;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG,"onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


    }

    @Override
    protected void lazyLoadData() {
        Log.e(TAG,"FragmentOne");

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
