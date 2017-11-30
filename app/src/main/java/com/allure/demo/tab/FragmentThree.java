package com.allure.demo.tab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.allure.demo.R;
import com.allure.fragment.QuickFragment;

/**
 * Created by Cherish on 2017/11/27.
 */

public class FragmentThree extends QuickFragment {
    private static final String TAG = "FragmentThree";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button button = (Button) view.findViewById(R.id.buttonPanel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach");

    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initLazy() {
        Log.d(TAG, "initLazy");

    }

    @Override
    protected void initNotLazy() {
        Log.d(TAG, "initNotLazy");

    }

    @Override
    protected int initFragmentLayout() {
        return R.layout.fragment_three;
    }

}
