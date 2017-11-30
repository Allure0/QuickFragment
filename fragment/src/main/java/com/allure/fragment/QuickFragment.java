package com.allure.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


/**
 * Created by Cherish on 2017/11/28.
 */
public abstract class QuickFragment extends LazyFragment {

    public static final int RESULT_OK = QuickActivity.RESULT_OK;
    public static final int RESULT_CANCELED = QuickActivity.RESULT_CANCELED;

    private static final int REQUEST_CODE_INVALID = QuickActivity.REQUEST_CODE_INVALID;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activity = (QuickActivity) context;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new instance of a Fragment with the given class name.  This is the same as calling its empty constructor.
     *
     * @param context       context.
     * @param fragmentClass class of fragment.
     * @param <T>           subclass of {@link QuickFragment}.
     * @return new instance.
     * @deprecated In {@code Activity} with {@link QuickActivity#fragment(Class)} instead;
     * in the {@code Fragment} width {@link #fragment(Class)} instead.
     */
    @Deprecated
    public static <T extends QuickFragment> T instantiate(Context context, Class<T> fragmentClass) {
        //noinspection unchecked
        return (T) instantiate(context, fragmentClass.getName(), null);
    }

    /**
     * Create a new instance of a Fragment with the given class name.  This is the same as calling its empty constructor.
     *
     * @param context       context.
     * @param fragmentClass class of fragment.
     * @param bundle        argument.
     * @param <T>           subclass of {@link QuickFragment}.
     * @return new instance.
     * @deprecated In {@code Activity} with {@link QuickActivity#fragment(Class, Bundle)} instead;
     * in the {@code Fragment} width {@link #fragment(Class, Bundle)} instead.
     */
    @Deprecated
    public static <T extends QuickFragment> T instantiate(Context context, Class<T> fragmentClass, Bundle bundle) {
        //noinspection unchecked
        return (T) instantiate(context, fragmentClass.getName(), bundle);
    }

    /**
     * Create a new instance of a Fragment with the given class name.  This is the same as calling its empty constructor.
     *
     * @param fragmentClass class of fragment.
     * @param <T>           subclass of {@link QuickFragment}.
     * @return new instance.
     */
    public final <T extends QuickFragment> T fragment(Class<T> fragmentClass) {
        //noinspection unchecked
        return (T) instantiate(getContext(), fragmentClass.getName(), null);
    }

    /**
     * Create a new instance of a Fragment with the given class name.  This is the same as calling its empty constructor.
     *
     * @param fragmentClass class of fragment.
     * @param bundle        argument.
     * @param <T>           subclass of {@link QuickFragment}.
     * @return new instance.
     */
    public final <T extends QuickFragment> T fragment(Class<T> fragmentClass, Bundle bundle) {
        //noinspection unchecked
        return (T) instantiate(getContext(), fragmentClass.getName(), bundle);
    }


    /**
     * Solving memory leakage
     * QuickActivity.
     */
    protected QuickActivity activity;

    /**
     * Get BaseActivity.
     *
     * @return {@link QuickActivity}.
     */
    protected final QuickActivity getCompatActivity() {
        return activity;
    }

    /**
     * Start activity.
     *
     * @param clazz class for activity.
     * @param <T>   {@link Activity}.
     */
    protected final <T extends Activity> void startActivity(Class<T> clazz) {
        startActivity(new Intent(activity, clazz));
    }

    /**
     * Start activity and finish my parent.
     *
     * @param clazz class for activity.
     * @param <T>   {@link Activity}.
     */
    protected final <T extends Activity> void startActivityFinish(Class<T> clazz) {
        startActivity(new Intent(activity, clazz));
        activity.finish();
    }

    /**
     * Destroy me.
     */
    public void finish() {
        activity.onBackPressed();
    }


    // ------------------------- Stack ------------------------- //

    /**
     * Stack info.
     */
    private QuickActivity.FragmentStackEntity mStackEntity;

    /**
     * Set result.
     *
     * @param resultCode result code, one of {@link QuickFragment#RESULT_OK}, {@link QuickFragment#RESULT_CANCELED}.
     */
    protected final void setResult(@ResultCode int resultCode) {
        mStackEntity.resultCode = resultCode;
    }

    /**
     * Set result.
     *
     * @param resultCode resultCode, use {@link }.
     * @param result     {@link Bundle}.
     */
    protected final void setResult(@ResultCode int resultCode, @NonNull Bundle result) {
        mStackEntity.resultCode = resultCode;
        mStackEntity.result = result;
    }

    /**
     * Get the resultCode for requestCode.
     */
    final void setStackEntity(@NonNull QuickActivity.FragmentStackEntity stackEntity) {
        this.mStackEntity = stackEntity;
    }

    /**
     * You should override it.
     *
     * @param resultCode resultCode.
     * @param result     {@link Bundle}.
     */
    public void onFragmentResult(int requestCode, @ResultCode int resultCode, @Nullable Bundle result) {
    }

    /**
     * Show a fragment.
     *
     * @param clazz fragment class.
     * @param <T>   {@link QuickFragment}.
     */
    public final <T extends QuickFragment> void startFragment(Class<T> clazz) {
        try {
            QuickFragment targetFragment = clazz.newInstance();
            startFragment(targetFragment, true, REQUEST_CODE_INVALID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show a fragment.
     *
     * @param clazz       fragment class.
     * @param stickyStack sticky to back stack.
     * @param <T>         {@link QuickFragment}.
     */
    public final <T extends QuickFragment> void startFragment(Class<T> clazz, boolean stickyStack) {
        try {
            QuickFragment targetFragment = clazz.newInstance();
            startFragment(targetFragment, stickyStack, REQUEST_CODE_INVALID);
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
        startFragment(targetFragment, true, REQUEST_CODE_INVALID);
    }

    /**
     * Show a fragment.
     *
     * @param targetFragment fragment to display.
     * @param stickyStack    sticky back stack.
     * @param <T>            {@link QuickFragment}.
     */
    public final <T extends QuickFragment> void startFragment(T targetFragment, boolean stickyStack) {
        startFragment(targetFragment, stickyStack, REQUEST_CODE_INVALID);
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
     * @deprecated use {@link #startFragmentForResult(Class, int)} instead.
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
        try {
            QuickFragment targetFragment = clazz.newInstance();
            startFragment(targetFragment, true, requestCode);
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
        startFragment(targetFragment, true, requestCode);
    }

    /**
     * Show a fragment.
     *
     * @param targetFragment fragment to display.
     * @param stickyStack    sticky back stack.
     * @param requestCode    requestCode.
     * @param <T>            {@link QuickFragment}.
     */
    private <T extends QuickFragment> void startFragment(T targetFragment, boolean stickyStack, int requestCode) {
        activity.startFragment(this, targetFragment, stickyStack, requestCode);
    }


}
