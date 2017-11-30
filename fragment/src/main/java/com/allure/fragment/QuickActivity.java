package com.allure.fragment;


import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by Cherish on 2017/11/28.
 */
public abstract class QuickActivity extends AppCompatActivity implements AnimInterface {

    public static final int REQUEST_CODE_INVALID = -1;

    // The current display of Fragment
    protected QuickFragment mCurrentFragment;

    private FragmentManager fragmentManager;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private List<QuickFragment> mFragmentStack = new ArrayList<>();
    private Map<QuickFragment, FragmentStackEntity> mFragmentEntityMap = new HashMap<>();


    static class FragmentStackEntity {
        private FragmentStackEntity() {
        }

        private boolean isSticky = false;
        private int requestCode = REQUEST_CODE_INVALID;
        @ResultCode
        int resultCode = RESULT_CANCELED;
        Bundle result = null;
    }

    public final <T extends QuickFragment> T fragment(Class<T> fragmentClass) {
        //noinspection unchecked
        return (T) Fragment.instantiate(this, fragmentClass.getName());
    }

    public final <T extends QuickFragment> T fragment(Class<T> fragmentClass, Bundle bundle) {
        //noinspection unchecked
        return (T) Fragment.instantiate(this, fragmentClass.getName(), bundle);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
    }

    /**
     * The same  level fragment use.
     *
     * @param clazz fragment class
     * @param <T>   {@link QuickFragment}.
     */
    public final <T extends QuickFragment> void showFragment(Class<T> clazz) {
        try {
            QuickFragment showFragment =
                    (QuickFragment) fragmentManager.findFragmentByTag(clazz.getName());

            if (showFragment == null) {
                showFragment = clazz.newInstance();
                if (showFragment == null) {
                    return;
                }
            }
            switchFragment(showFragment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Show a fragment.
     *
     * @param clazz fragment class
     * @param <T>   {@link QuickFragment}.
     */
    public final <T extends QuickFragment> void startFragment(Class<T> clazz) {
        try {
            QuickFragment targetFragment = clazz.newInstance();
            startFragment(mCurrentFragment, targetFragment, true, REQUEST_CODE_INVALID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show a fragment.
     *
     * @param clazz       fragment class.
     * @param stickyStack sticky to back stack.
     */
    public final <T extends QuickFragment> void startFragment(Class<T> clazz, boolean stickyStack) {
        try {
            QuickFragment targetFragment = clazz.newInstance();
            startFragment(mCurrentFragment, targetFragment, stickyStack, REQUEST_CODE_INVALID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show a fragment.
     *
     * @param targetFragment fragment to display.
     * @param <T>            {@link QuickFragment}.
     */
    public final <T extends QuickFragment> void startFragment(T targetFragment) {
        startFragment(mCurrentFragment, targetFragment, true, REQUEST_CODE_INVALID);
    }

    /**
     * Show a fragment.
     *
     * @param targetFragment fragment to display.
     * @param stickyStack    sticky back stack.
     * @param <T>            {@link QuickFragment}.
     */
    public final <T extends QuickFragment> void startFragment(T targetFragment, boolean stickyStack) {
        startFragment(mCurrentFragment, targetFragment, stickyStack, REQUEST_CODE_INVALID);
    }

    /**
     * Show a fragment for result.
     *
     * @param clazz       fragment to display.
     * @param requestCode requestCode.
     * @param <T>         {@link QuickFragment}.
     * @deprecated use {@link #startFragmentForResult(Class, int)} instead.
     */
    @Deprecated
    public final <T extends QuickFragment> void startFragmentForResquest(Class<T> clazz, int requestCode) {
        startFragmentForResult(clazz, requestCode);
    }

    /**
     * Show a fragment for result.
     *
     * @param targetFragment fragment to display.
     * @param requestCode    requestCode.
     * @param <T>            {@link QuickFragment}.
     * @deprecated use {@link #startFragmentForResult(QuickFragment, int)} instead.
     */
    @Deprecated
    public final <T extends QuickFragment> void startFragmentForResquest(T targetFragment, int requestCode) {
        startFragmentForResult(targetFragment, requestCode);
    }

    /**
     * Show a fragment for result.
     *
     * @param clazz       fragment to display.
     * @param requestCode requestCode.
     * @param <T>         {@link QuickFragment}.
     */
    public final <T extends QuickFragment> void startFragmentForResult(Class<T> clazz, int requestCode) {
        if (requestCode == REQUEST_CODE_INVALID)
            throw new IllegalArgumentException("The requestCode must be integer.");
        try {
            QuickFragment targetFragment = clazz.newInstance();
            startFragment(null, targetFragment, true, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show a fragment for result.
     *
     * @param targetFragment fragment to display.
     * @param requestCode    requestCode.
     * @param <T>            {@link QuickFragment}.
     */
    public final <T extends QuickFragment> void startFragmentForResult(T targetFragment, int requestCode) {
        if (requestCode == REQUEST_CODE_INVALID)
            throw new IllegalArgumentException("The requestCode must be positive integer.");
        startFragment(null, targetFragment, true, requestCode);
    }


    /**
     * Hide show fragment.
     *
     * @param targetFragment
     */
    public void switchFragment(QuickFragment targetFragment) {

        if (mCurrentFragment == targetFragment) return;

        FragmentTransaction fragmentTransaction = getFragmentTransaction();

        if (mCurrentFragment != null) {
            fragmentTransaction.hide(mCurrentFragment);
        }

        if (targetFragment.isAdded()) {
            fragmentTransaction.show(targetFragment);
        } else {
            fragmentTransaction.add(fragmentId(), targetFragment, targetFragment.getClass().getName());
        }
        Log.d("switchFragment", targetFragment.getClass().getName());
        fragmentTransaction.commit();
        mCurrentFragment = targetFragment;
    }

    /**
     * get FragmentTransaction
     *
     * @return fragmentTransaction {@link FragmentTransaction}.
     */
    private FragmentTransaction getFragmentTransaction() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // if need fragment anim
        if (useAnim()) {
            fragmentTransaction.setCustomAnimations(enterAnim(), exitAnim(), popEnterAnim(), popExitAnim());
        }
        return fragmentTransaction;
    }


    /**
     * Show a fragment.
     *
     * @param mCurrentFragment displaying fragment,default is mCurrentFragment
     * @param targetFragment   fragment to display.
     * @param stickyStack      sticky back stack.
     * @param requestCode      requestCode.
     * @param <T>              {@link QuickFragment}.
     */
    protected final <T extends QuickFragment> void startFragment(T mCurrentFragment, T targetFragment,
                                                                 boolean stickyStack, int requestCode) {
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        if (mCurrentFragment != null) {
            FragmentStackEntity thisStackEntity = mFragmentEntityMap.get(mCurrentFragment);
            if (thisStackEntity != null) {
                if (thisStackEntity.isSticky) {
                    mCurrentFragment.onPause();
                    mCurrentFragment.onStop();
                    fragmentTransaction.hide(mCurrentFragment);
                } else {
                    fragmentTransaction.remove(mCurrentFragment).commit();
                    fragmentTransaction.commitNow();
                    fragmentTransaction = getFragmentTransaction();

                    mFragmentEntityMap.remove(mCurrentFragment);
                    mFragmentStack.remove(mCurrentFragment);
                }
            }
        }

        String fragmentTag = targetFragment.getClass().getSimpleName() + atomicInteger.incrementAndGet();
        fragmentTransaction.add(fragmentId(), targetFragment, fragmentTag);
        fragmentTransaction.addToBackStack(fragmentTag);
        fragmentTransaction.commit();

        FragmentStackEntity fragmentStackEntity = new FragmentStackEntity();
        fragmentStackEntity.isSticky = stickyStack;
        fragmentStackEntity.requestCode = requestCode;
        targetFragment.setStackEntity(fragmentStackEntity);
        mFragmentEntityMap.put(targetFragment, fragmentStackEntity);

        mFragmentStack.add(targetFragment);
    }

    /**
     * When the back off.
     */
    protected final boolean onBackStackFragment() {
        if (mFragmentStack.size() > 1) {
            fragmentManager.popBackStack();
            QuickFragment inFragment = mFragmentStack.get(mFragmentStack.size() - 2);

            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            fragmentTransaction.show(inFragment);
            fragmentTransaction.commit();

            QuickFragment outFragment = mFragmentStack.get(mFragmentStack.size() - 1);
            inFragment.onResume();

            FragmentStackEntity stackEntity = mFragmentEntityMap.get(outFragment);
            mFragmentStack.remove(outFragment);
            mFragmentEntityMap.remove(outFragment);


            if (stackEntity.requestCode != REQUEST_CODE_INVALID) {
                inFragment.onFragmentResult(stackEntity.requestCode, stackEntity.resultCode, stackEntity.result);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (!onBackStackFragment()) {
            finish();
        }
    }


    /**
     * Should be returned to display fragments id of {@link android.view.ViewGroup}.
     *
     * @return resource id of {@link android.view.ViewGroup}.
     */
    @IdRes
    protected abstract int fragmentId();

    /**
     * if use fragment anim
     *
     * @return boolean
     */
    @Override
    public boolean useAnim() {
        return false;
    }


    /**
     * view of the fragment being added or attached.
     *
     * @return enter An animation or animator resource ID
     */
    @AnimatorRes
    @AnimRes
    @Override

    public int enterAnim() {
        return R.animator.fragment_slide_left_enter;

    }

    /**
     * view of the fragment being removed or detached.
     *
     * @return exit An animation or animator resource ID
     */
    @AnimatorRes
    @AnimRes
    @Override
    public int exitAnim() {
        return R.animator.fragment_slide_left_exit;
    }

    /**
     * view of the fragment being readded or reattached caused by
     * {@link FragmentManager#popBackStack()} or similar methods.
     *
     * @return popEnter An animation or animator resource ID
     */
    @AnimatorRes
    @AnimRes
    @Override
    public int popEnterAnim() {
        return R.animator.fragment_slide_right_enter;

    }

    /**
     * view of the fragment being removed or detached caused by
     * {@link FragmentManager#popBackStack()} or similar methods.
     *
     * @return popExit An animation or animator resource ID
     */
    @AnimatorRes
    @AnimRes
    @Override
    public int popExitAnim() {
        return R.animator.fragment_slide_right_exit;
    }


}