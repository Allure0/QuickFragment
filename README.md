# QuikcFragment

Fragment的使用一般用于Tab类型UI界面,或者模块化流程式中使用.


## 特性

- 支持Tab类型界面一句话调用显示
- 支持模块化流程式界面控制
- 支持Fragment动画入栈出栈的控制显示
- 支持单独设置某一个Fragment的动画
- 支持Fragment单个实例或多个实例
- 支持回退栈BackStack管理
- 支持类似Activity的startActivityForResult的回执信息
- 支持参数传递并且回执

## 动画设置

 
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
  
### 使用方法
一句话调用显示fragment
```
 showFragment(FragmentOne.class);
```

## 模块化流程式（使用startFragment（XXX））

模块化流程式的Fragment一般用于模块或者流程,比如登录-注册-忘记密码等等,可以将其理解为用户模块,对页面采用Fragment处理进行流程控制化.着重于需要对Fragment的栈进行管理控制



### 单独传参

```
 Bundle bundle = new Bundle();
                bundle.putString("msg", "我爱你");
                QuickFragment fragment = fragment(ArgumentFragment.class, bundle);
                startFragment(fragment);
```

### 回执信息

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

### 传递参数并且回执信息

结合单独发送消息以及获取回执消息即可（参见DEMO）

### 保存回退栈

跳转后点击返回键可以逐级返回

```
startFragment(BackStackFragment1.class, true);
```

#### 不保存回退栈

跳转后点击返回键会直接略过未保存的fragment直接回到上一层

```
  startFragment(BackStackFragment1.class, false);

```
