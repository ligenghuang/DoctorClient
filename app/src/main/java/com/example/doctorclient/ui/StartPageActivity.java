package com.example.doctorclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.os.Process;
import com.example.doctorclient.R;
import com.example.doctorclient.actions.BaseAction;
import com.example.doctorclient.actions.LoginAction;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.base.BaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

/**
* 启动页
* @author lgh
* created at 2019/5/13 0013 14:37
*/
public class StartPageActivity extends UserBaseActivity {



    @Override
    public int intiLayout() {
        return R.layout.activity_start_page;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        mImmersionBar
                .statusBarView(R.id.top_view)
                .navigationBarWithKitkatEnable(false)
                .statusBarDarkFont(true)
                .addTag("StartPageActivity")  //给上面参数打标记，以后可以通过标记恢复
                .init();
    }

    @Override
    protected void initTitlebar() {
        super.initTitlebar();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }
        List<String> needRequestPermissionList = findDeniedPermissions(needPermissions);
        if (null != needRequestPermissionList && needRequestPermissionList.size() > 0) {
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    isNeedAnim = false;
                    finish();
                }
            }, 2000);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Process.killProcess(Process.myPid());
    }


    @Override
    protected BaseAction initAction() {
        return null;
    }
}
