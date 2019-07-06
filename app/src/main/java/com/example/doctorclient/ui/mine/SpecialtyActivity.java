package com.example.doctorclient.ui.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.BaseAction;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
* 特长
* @author lgh
* created at 2019/5/17 0017 11:40
*/
public class SpecialtyActivity extends UserBaseActivity {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.et_specialty)
    EditText specialtyEt;

    int type=0;

    @Override
    public int intiLayout() {
        return R.layout.activity_specialty;
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
                .addTag("BindMobileActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());

    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        type = getIntent().getIntExtra("type",0);
        titleTv.setText(ResUtil.getString(type == 0?R.string.user_info_tip_6:R.string.user_info_tip_29));
        specialtyEt.setHint(ResUtil.getString(type == 0?R.string.user_info_tip_26:R.string.user_info_tip_30));
        specialtyEt.setText(getIntent().getStringExtra("specialty"));
    }

    @OnClick(R.id.tv_confirm)
    void onClick(View view){
        switch (view.getId()){
            case R.id.tv_confirm:
                if (TextUtils.isEmpty(specialtyEt.getText().toString())){
                    showNormalToast(ResUtil.getString(R.string.user_info_tip_27));
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("specialty",specialtyEt.getText().toString());
                intent.putExtra("type",type);
                setResult(200,intent);
                finish();
                break;
        }
    }


    @Override
    protected BaseAction initAction() {
        return null;
    }
}
