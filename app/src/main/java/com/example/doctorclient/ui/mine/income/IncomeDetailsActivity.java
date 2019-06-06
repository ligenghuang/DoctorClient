package com.example.doctorclient.ui.mine.income;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.IncomeDetailsAction;
import com.example.doctorclient.adapter.IncomeDetailsAdapter;
import com.example.doctorclient.event.IncomeDetailsListDto;
import com.example.doctorclient.ui.impl.IncomeDetailsView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
/**
 * description:收入明细
 * autour: huang
 * date: 2019/5/30 11:35
 * update: 2019/5/30
 * version:
 */
public class IncomeDetailsActivity extends UserBaseActivity<IncomeDetailsAction> implements IncomeDetailsView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.rv_income_details)
    RecyclerView incomeDetailsRv;

    IncomeDetailsAdapter incomeDetailsAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_income_details;
    }

    @Override
    protected IncomeDetailsAction initAction() {
        return new IncomeDetailsAction(this,this);
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
                .addTag("IncomeDetailsActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.income_tip_3));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        incomeDetailsAdapter = new IncomeDetailsAdapter();
        incomeDetailsRv.setLayoutManager(new LinearLayoutManager(this));
        incomeDetailsRv.setAdapter(incomeDetailsAdapter);

        getIncomeDetailsList();
    }

    /**
     * 获取收入明细
     */
    @Override
    public void getIncomeDetailsList() {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.getIncomeDetailsList();
        }
    }

    /**
     * 获取收入明细成功
     * @param incomeDetailsListDto
     */
    @Override
    public void getIncomeDetailsSuccessful(IncomeDetailsListDto incomeDetailsListDto) {
        loadDiss();
        incomeDetailsAdapter.refresh(incomeDetailsListDto.getData());
    }

    /**
     * 失败
     * @param message
     * @param code
     */
    @Override
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
