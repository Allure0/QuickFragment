package com.allure.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;


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
 *  If you need to be lazy fragment, need to initialize it at initLazy.
 *  If you don't need to be lazy fragment, need to initialize it at initNotLazy and initLazy should be empty.
 *
 *
 * Tips:1
 *  If used with ViewPager,invoke setUserVisibleHint.
 *
 * Tips:2
 * If userd with FragmentTransaction.show() or FragmentTransaction.hide(),invoke onHiddenChanged.
 */


public abstract class LazyFragment extends Fragment {

    protected View rootView;
    private boolean isVisible;
    private boolean isPrepared;
    private boolean isFirstLoad = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == rootView) {
            isFirstLoad = true;
            isPrepared = true;
            rootView = inflater.inflate(initFragmentLayout(), container, false);
            initListener();
            initNotLazy();
            lazyLoad();
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // for bug ---> java.lang.IllegalStateException: Activity has been destroyed
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * If used with Viewpager{@link android.support.v4.view.ViewPager}
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * If used with FragmentTransaction{@link android.support.v4.app.FragmentTransaction},and used show or hide
     * If the initial fragment need display,it needs hide and then show
     *
     * @param hidden hidden true if the fragment is now hidden, false if it is not visible
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * Something can do do it,if is show
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * Something can do do it,if is hidden
     */
    protected void onInvisible() {
    }


    /**
     * Lazy load
     */
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;
        initLazy();
    }

    protected abstract int initFragmentLayout();

    protected abstract void initLazy();

    protected abstract void initNotLazy();

    protected abstract void initListener();


}
