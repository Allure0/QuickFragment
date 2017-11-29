package com.allure.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.allure.demo.R;
import com.allure.fragment.QuickFragment;


/**
 * Created by Cherish on 2017/11/28.
 */

public class StartFragment extends QuickFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button button1 = (Button) view.findViewById(R.id.button1);
        Button button2 = (Button) view.findViewById(R.id.button2);
        Button button3 = (Button) view.findViewById(R.id.button3);
        Button button4 = (Button) view.findViewById(R.id.button4);
        Button button5 = (Button) view.findViewById(R.id.button5);


        //参数传递
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("msg", "我爱你");
                QuickFragment fragment = fragment(ArgumentFragment.class, bundle);
                startFragment(fragment);

            }
        });
        //回执信息
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragmentForResquest(CallBackFragment.class, 100);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("msg", "我爱你");
                QuickFragment fragment = fragment(ArgumentFragment.class, bundle);
                startFragmentForResquest(fragment, 101);
            }
        });
        //保存回退栈
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startFragment(BackStackFragment1.class, true);

            }
        });
        //不保存回退栈
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startFragment(BackStackFragment1.class, false);

            }
        });

    }


    @Override
    public void onFragmentResult(int requestCode, int resultCode, @Nullable Bundle result) {
        switch (requestCode) {
            case 100: {
                if (resultCode == RESULT_OK) {
                    String message = result.getString("message");
                    if (TextUtils.isEmpty(message)) {
                        Toast.makeText(activity, "没有返回信息", 1).show();
                    } else {
                        Toast.makeText(activity, message, 1).show();
                    }
                } else if (resultCode == RESULT_CANCELED) {
                }
                break;
            }

            case 101: {
                if (resultCode == RESULT_OK) {
                    String message = result.getString("message");
                    if (TextUtils.isEmpty(message)) {
                        Toast.makeText(activity, "没有返回信息", 1).show();
                    } else {
                        Toast.makeText(activity, message, 1).show();
                    }
                } else if (resultCode == RESULT_CANCELED) {
                }
                break;
            }
        }
    }
}
