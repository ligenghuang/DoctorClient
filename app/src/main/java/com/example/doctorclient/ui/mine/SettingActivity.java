package com.example.doctorclient.ui.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.SettingAction;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.ui.MainActivity;
import com.example.doctorclient.ui.impl.SettingView;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.cusview.NotificationHelper;
import com.example.doctorclient.util.data.MySp;
import com.example.doctorclient.util.dialog.LogoutDialog;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.suke.widget.SwitchButton;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imlib.RongIMClient;

/**
 * description:设置页面
 * autour: huang
 * date: 2019/5/22 11:37
 * update: 2019/5/22
 * version:
 */
public class SettingActivity extends UserBaseActivity<SettingAction> implements SettingView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.sb_message)
    SwitchButton messageSb;
    @BindView(R.id.sb_voice)
    SwitchButton voiceSb;
    @BindView(R.id.sb_vibration)
    SwitchButton vibrationSb;

    @Override
    public int intiLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected SettingAction initAction() {
        return new SettingAction(this,this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    protected void initTitlebar() {
        super.initTitlebar();
        mImmersionBar
                .statusBarView(R.id.top_view)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0.2f)
                .addTag("SettingActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.setting_tip_9));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        messageSb.setChecked(MySp.getMessage(mContext));
        voiceSb.setChecked(MySp.getVoice(mContext));
        vibrationSb.setChecked(MySp.getVibration(mContext));
        loadView();
    }


    @Override
    protected void loadView() {
        super.loadView();
        messageSb.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO 新消息提示
                MySp.setMessage(mContext,isChecked);
            }
        });
        voiceSb.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO 声音提示
                MySp.setVoice(mContext,isChecked);
            }
        });
        vibrationSb.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO 震动提示
                MySp.setVibration(mContext,isChecked);
            }
        });
    }

    @OnClick(R.id.tv_logout)
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_logout:
                //todo 退出登录
                LogoutDialog logoutDialog = new LogoutDialog(mContext,R.style.MY_AlertDialog);
                logoutDialog.setOnClickListener(new LogoutDialog.OnClickListener() {
                    @Override
                    public void confirm() {
                        logout();
                        //todo 融云退出登录
                        RongIMClient.getInstance().logout();
                    }
                });
               logoutDialog.show();
                break;
        }
    }

    @Override
    public void logout() {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.logout();
        }
    }

    @Override
    public void logoutSuccessful(String str) {
        loadDiss();
        finish();
        MySp.clearAllSP(mContext);
        MySharedPreferencesUtil.setSessionId(mContext,null);
    }

    @Override
    public void onError(String message, int code) {
        loadDiss();
        showNormalToast(message);
    }

    @Override
    public void onLigonError() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (baseAction != null){
            baseAction.toRegister();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
       if (baseAction != null){
           baseAction.toUnregister();
       }
    }
}
