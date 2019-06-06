package com.example.doctorclient.ui.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.EvaluationAction;
import com.example.doctorclient.adapter.EvaluationAdapter;
import com.example.doctorclient.event.EvaluationListDto;
import com.example.doctorclient.ui.impl.EvaluationView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;

/**
 * description:我的评价
 * autour: huang
 * date: 2019/5/29 15:15
 * update: 2019/5/29
 * version:
 */
public class EvaluationActivity extends UserBaseActivity<EvaluationAction> implements EvaluationView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.rv_evaluation)
    RecyclerView evaluationRv;
    @BindView(R.id.tv_item_ratio)
    TextView ratioTv;

    EvaluationAdapter evaluationAdapter;

    @Override
    protected EvaluationAction initAction() {
        return new EvaluationAction(this, this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_evaluation;
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
                .addTag("EvaluationActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .statusBarDarkFont(true, 0.2f)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.mine_tip_6));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        evaluationAdapter = new EvaluationAdapter(this);
        evaluationRv.setAdapter(evaluationAdapter);
        evaluationRv.setLayoutManager(new LinearLayoutManager(mContext));

        isLogin();
    }

    @Override
    public void isLogin() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.isLogin();
        }
    }

    @Override
    public void isLoginSuccessful() {
        getEvaluationList();
    }

    @Override
    public void isLoginError() {
        loadDiss();
        jumpActivity(mContext, LoginActivity.class);
    }

    /**
     * 获取评价列表
     */
    @Override
    public void getEvaluationList() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getEvaluationList();
        }
    }

    /**
     * 获取评价列表 成功
     *
     * @param evaluationListDto
     */
    @Override
    public void getEvaluationListSuccessful(EvaluationListDto evaluationListDto) {
        loadDiss();
        List<EvaluationListDto.DataBean> list = evaluationListDto.getData();
        int score = 0;
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                EvaluationListDto.DataBean bean = list.get(i);
                score = score + bean.getThe_star();
            }
            int s = (score * 100) / list.size()/5;
            ratioTv.setText(s+"%");
        }
        evaluationAdapter.refresh(evaluationListDto.getData());
    }

    /**
     * 失败
     *
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
        jumpActivityNotFinish(mContext, LoginActivity.class);
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
