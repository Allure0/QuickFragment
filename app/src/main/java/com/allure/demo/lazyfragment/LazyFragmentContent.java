package com.allure.demo.lazyfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.allure.demo.R;
import com.allure.fragment.QuickFragment;

/**
 * Created by Cherish on 2017/11/30.
 */

public class LazyFragmentContent extends QuickFragment {
    private static final String TAG = "LazyFragmentContent";
    public static final String CONTENT_KEY="content";
    TextView textView;
    String content;


    public static LazyFragmentContent newInstance(String content) {
        Bundle args = new Bundle();
        LazyFragmentContent fragment = new LazyFragmentContent();
        args.putString(CONTENT_KEY, content);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        content = bundle.getString(CONTENT_KEY);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, content);

    }

    @Override
    protected int initFragmentLayout() {
        return R.layout.fragment_lazy;
    }

    @Override
    protected void initLazy() {
        textView = rootView.findViewById(R.id.text);
        textView.setText(content);
    }

    @Override
    protected void initNotLazy() {

    }

    @Override
    protected void initListener() {

    }
}
