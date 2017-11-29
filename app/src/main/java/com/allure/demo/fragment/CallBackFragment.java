package com.allure.demo.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.allure.demo.R;
import com.allure.fragment.QuickFragment;

/**
 * Created by Cherish on 2017/11/28.
 */

public class CallBackFragment extends QuickFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_callback, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText editText = view.findViewById(R.id.edit);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("message", editText.getText().toString());
                setResult(RESULT_OK, bundle);
                finish();
            }
        });
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        Animator enterAnim = AnimatorInflater.loadAnimator(activity, R.animator.fragment_slide_left_enter); //加载指定xml

        Animator exitAnim = AnimatorInflater.loadAnimator(activity, R.animator.fragment_slide_right_exit); //加载指定xml
        if (enter) {
            return enterAnim;
        } else {
            return exitAnim;
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
