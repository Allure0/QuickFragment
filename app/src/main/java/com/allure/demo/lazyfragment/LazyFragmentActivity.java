package com.allure.demo.lazyfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.allure.demo.R;
import com.allure.fragment.QuickActivity;
import com.allure.fragment.adapter.QuickFragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cherish on 2017/11/30.
 */

public class LazyFragmentActivity extends QuickActivity {

    private List<String> title = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private String[] string = new String[]{"Java", "C++", "C", "Python", "Ruby", "Kotlin", "iOS", "Android", "Web"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazy_fragment);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);

        for (int i = 0; i < string.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putString(LazyFragmentContent.CONTENT_KEY, string[i]);
            title.add(string[i]);
            fragments.add(fragment(LazyFragmentContent.class, bundle));
        }

        QuickFragmentStateAdapter viewPagerAdapter = new QuickFragmentStateAdapter(getSupportFragmentManager(), fragments, title);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int fragmentId() {
        return 0;
    }


    private class ViewPagerAdapter extends QuickFragmentStateAdapter {


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm, fragmentList);
        }

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> mTitles) {
            super(fm, fragmentList, mTitles);
        }


    }
}