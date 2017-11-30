package com.allure.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.allure.demo.fragment.FlowModeActivity;
import com.allure.demo.lazyfragment.LazyFragmentActivity;
import com.allure.demo.tab.MainActivity;

/**
 * Created by Cherish on 2017/11/28.
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
    }

    public  void tab(View view){
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
    }
    public  void flowmode(View view){
        startActivity(new Intent(WelcomeActivity.this,FlowModeActivity.class));
    }

    public void lazyFragment(View view) {
        startActivity(new Intent(WelcomeActivity.this,LazyFragmentActivity.class));

    }
}
