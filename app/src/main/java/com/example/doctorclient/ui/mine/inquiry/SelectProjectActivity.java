package com.example.doctorclient.ui.mine.inquiry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.SelectProjectAction;
import com.example.doctorclient.adapter.DepartList1Adapter;
import com.example.doctorclient.adapter.DepartList2Adapter;
import com.example.doctorclient.event.DepartidDto;
import com.example.doctorclient.ui.MainActivity;
import com.example.doctorclient.ui.impl.SelectProjectView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;

/**
 * description: 选择项目
 * autour: huang
 * date: 2019/5/21 10:32
 * update: 2019/5/21
 * version:
 */
public class SelectProjectActivity extends UserBaseActivity<SelectProjectAction> implements SelectProjectView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.rv_depart)
    RecyclerView departRv;
    @BindView(R.id.rv_drug)
    RecyclerView drugRv;

    DepartList1Adapter departList1Adapter;
    DepartList2Adapter departList2Adapter;

    @Override
    protected SelectProjectAction initAction() {
        return new SelectProjectAction(this, this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_select_prescription;
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
                .addTag("SelectProjectActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.edit_prescription_tip_9));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        departList1Adapter = new DepartList1Adapter();
        departRv.setLayoutManager(new LinearLayoutManager(this));
        departRv.setAdapter(departList1Adapter);

        departList2Adapter = new DepartList2Adapter();
        drugRv.setAdapter(departList2Adapter);
        drugRv.setLayoutManager(new LinearLayoutManager(this));

        getDepartVip1();
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        departList1Adapter.setOnClickListener(new DepartList1Adapter.OnClickListener() {
            @Override
            public void onClick(String id, int position) {
                getDepartVip2(id);
            }
        });
        departList2Adapter.setOnClickListener(new DepartList2Adapter.OnClickListener() {
            @Override
            public void onClick(String IUID, String name) {
                Intent intent = new Intent();
                intent.putExtra("iuid",IUID);
                intent.putExtra("name",name);
                setResult(201,intent);
                finish();
            }
        });
    }

    /**
     * 获取科室（一级）
     */
    @Override
    public void getDepartVip1() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getDepartVip1();
        }
    }

    /**
     * 获取科室（一级）成功
     * @param generalDto
     */
    @Override
    public void getDepartVip1Successful(DepartidDto generalDto) {
        loadDiss();
        List<DepartidDto.DataBean> list = generalDto.getData();
        list.get(0).setClick(true);
        departList1Adapter.refresh(list);
        getDepartVip2(list.get(0).getIUID());
    }

    /**
     * 获取科室（二级）
     * @param iuid
     */
    @Override
    public void getDepartVip2(String iuid) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getDepartVip2(iuid);
        }
    }

    /**
     * 获取科室（二级）成功
     * @param generalDto
     */
    @Override
    public void getDepartVip2Successful(DepartidDto generalDto) {
        loadDiss();
        departList2Adapter.refresh(generalDto.getData());
    }

    /**
     * 失败
     * @param message
     * @param code
     */
    public void onError(String message, int code) {
        loadDiss();
        showNormalToast(message);
    }

    /**
     * 未登录
     */
    @Override
    public void onLigonError() {
        loadDiss();
        jumpActivity(mContext, LoginActivity.class);
        ActivityStack.getInstance().exitIsNotHaveMain(MainActivity.class,LoginActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (baseAction != null) {
            baseAction.toRegister();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }

}
