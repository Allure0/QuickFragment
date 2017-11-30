# QuikcFragment

Fragment的使用一般用于Tab类型UI界面,或者模块化流程式中使用.


## 特性
- 适用于单Activity+多Fragment或者多Activity+多Fragment
- 使用于模块化，便于业务组的开发维护
- 支持Tab类型界面一句话调用显示
- 支持模块化流程式界面控制
- 支持fragment配合ViewPager的懒加载
- 支持Fragment动画入栈出栈的控制显示
- 支持单独设置某一个Fragment的动画
- 支持Fragment单个实例或多个实例
- 支持回退栈BackStack管理
- 支持类似Activity的startActivityForResult的回执信息
- 支持参数传递并且回执

## 动画设置

注意使用的时候使用**属性动画**,否则出栈动画会有显示异常。
对于入栈动画和出栈动画只需要重写返回enterAnim和popExitAnim即可。

下图的动画是设置了3秒的动画时间

![Anim.gif](http://upload-images.jianshu.io/upload_images/2698278-d6fec74fc56a1b1f.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
 
| 方法        | 解释|  默认值| 
| :--------:  | :-----:  | :--:|
| useAnim        | 是否使用Fragment动画    | false|  
| enterAnim        | 入栈动画    |  R.animator.fragment_slide_left_enter| 
| exitAnim        | ----    | R.animator.fragment_slide_left_exit| 
| popEnterAnim       | ----    | R.animator.fragment_slide_right_enter|  
| popExitAnim      | 出栈动画    | R.animator.fragment_slide_right_exit  | 

注意使用的时候使用**属性动画**,否则出栈动画会有显示异常。

```
public class FlowModeActivity extends QuickActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flowmode_activity);

        startFragment(StartFragment.class);

    }

   @Override
    public boolean useAnim() {
        return true;
    }

    @Override
    public int enterAnim() {
        return super.enterAnim();
    }

    @Override
    public int exitAnim() {
        return super.exitAnim();
    }

    @Override
    public int popEnterAnim() {
        return super.popEnterAnim();
    }

    @Override
    public int popExitAnim() {
        return super.popExitAnim();
    }

    @Override
    public int fragmentId() {
        return R.id.rootview;
    }

}
```

## Tab类型（使用showFragment（XXX））
  Tab类型UI界面的fragment属于同一层级,一般不将其加入回退栈,并且直接采用Hide Show的方式进行显示隐藏控制。
  
### Tab类型使用方式

```
 showFragment(FragmentOne.class);
 showFragment(new FragmentOne());
```


## 模块化流程式（使用startFragment（XXX））

模块化流程式的Fragment一般用于模块或者流程,比如登录-注册-忘记密码等等,可以将其理解为用户模块,对页面采用Fragment处理进行流程控制化.着重于需要对Fragment的栈进行管理控制

**模块流程式类型的允许存在一个fragment实例**


## Fragment之间Intent单独传参
- 传递
```
 Bundle bundle = new Bundle();
                bundle.putString("msg", "我爱你");
                QuickFragment fragment = fragment(ArgumentFragment.class, bundle);
                startFragment(fragment);
```
- 接受
```
  Bundle bundle = getArguments();
        message = bundle.getString("msg");
```

## startFragmentForResult（）回执信息

- 请求


```
 startFragmentForResquest(CallBackFragment.class, 100);
```

- 返回信息

```
  Bundle bundle = new Bundle();
                bundle.putString("message", editText.getText().toString());
                setResult(RESULT_OK, bundle);
                finish();
```
- 获取回执信息

```
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
```

## 传递参数并且回执信息

结合单独发送消息以及获取回执消息即可（参见DEMO）

## BackStack回退栈

###  保存回退栈
所谓保存回退栈意思为A-->B-->C，那么按返回键回来的时候会依次回到C-->B-->A

```
startFragment(BackStackFragment1.class, true);
```
### 不保存回退栈

同样的A-->B-->C-->D，如果我们在启动的时候不保存B和C，那么按返回键回来会直接D-->A，中间的B,C将不会出现
```
startFragment(BackStackFragment1.class, false);
```
# 懒加载

```
@Override
    protected void initLazy() {
        //懒加载设置值或者网络请求等
//        textView.setText(content);
    }

    @Override
    protected void initNotLazy() {

    }
```
### 如果是没有配合ViewPager使用，那么初始化请求设置等操作直接在initNotLazy执行，如果在initLazy执行界面将不会显示

# License

Copyright 2017 Cherish（Allure）

Source code of the QuickFragment Project can be used according to the [Apache License, Version 2.0.](http://www.apache.org/licenses/LICENSE-2.0.html)