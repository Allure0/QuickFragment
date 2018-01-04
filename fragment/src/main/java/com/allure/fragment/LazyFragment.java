package com.allure.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * <pre>
 * 若把初始化内容放到initLazy实现
 * 就是采用Lazy方式加载的Fragment
 * 若不需要Lazy加载则initLazy方法内留空,初始化内容放到initNotLazy即可
 *
 * 注1:
 * 如果是与ViewPager一起使用，调用的是setUserVisibleHint。
 *
 * 注2:
 * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
 * 针对初始就show的Fragment 为了触发onHiddenChanged事件 达到lazy效果 需要先hide再show
 * eg:
 * transaction.hide(aFragment);
 * transaction.show(aFragment);
 *
 * Created by Cherish on 2017/11/30.
 *
 * </pre>
 * If you need to be lazy fragment, need to initialize it at initLazy.
 * If you don't need to be lazy fragment, need to initialize it at initNotLazy and initLazy should be empty.
 * <p>
 * <p>
 * Tips:1
 * If used with ViewPager,invoke setUserVisibleHint.
 * <p>
 * Tips:2
 * If userd with FragmentTransaction.show() or FragmentTransaction.hide(),invoke onHiddenChanged.
 */


public abstract class LazyFragment extends Fragment {

    private boolean isCreateView;
    private boolean isLoadDataComplete;
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (null == rootView) {
            rootView = inflater.inflate(initFragmentLayout(), container, false);
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        isCreateView = true;
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && !isLoadDataComplete) {
            isLoadDataComplete = true;
            lazyLoadData();
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !isLoadDataComplete && isCreateView) {
            isLoadDataComplete = true;
            lazyLoadData();
        }
    }

    protected abstract int initFragmentLayout();

    protected abstract void lazyLoadData();

}