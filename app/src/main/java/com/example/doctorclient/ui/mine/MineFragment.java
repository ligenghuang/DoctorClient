package com.example.doctorclient.ui.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.MineAction;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.UserInfoDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.MainActivity;
import com.example.doctorclient.ui.impl.MineView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.ui.mine.income.IncomeActivity;
import com.example.doctorclient.ui.mine.inquiry.MyInquiryActivity;
import com.example.doctorclient.ui.mine.inquiry.SelectPrescriptionActivity;
import com.example.doctorclient.ui.mine.prescription.MyPrescriptionActivity;
import com.example.doctorclient.ui.mine.prescription.MyPrescriptionFragment;
import com.example.doctorclient.util.Utilt;
import com.example.doctorclient.util.base.UserBaseFragment;
import com.example.doctorclient.util.data.MySp;
import com.example.doctorclient.util.dialog.ModifyDialog;
import com.example.doctorclient.util.dialog.UpdateFactPriceDialog;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.PriceUtils;
import com.lgh.huanglib.util.data.ResUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的fragment
 *
 * @author lgh
 * created at 2019/5/15 0015 14:30
 */
public class MineFragment extends UserBaseFragment<MineAction> implements MineView {
    View view;
    @BindView(R.id.top_view)
    View topView;


    @BindView(R.id.iv_user_portrait)
    ImageView userPortraitIv;
    @BindView(R.id.tv_user_name)
    TextView userNmaeTv;
    @BindView(R.id.tv_user_position)
    TextView userPositionTv;
    @BindView(R.id.tv_user_hospital)
    TextView userHospitalTv;
    @BindView(R.id.tv_income_amount)
    TextView incomeAmountTv;

    @BindView(R.id.tv_ask_doctors)
    TextView askDoctorsTv;

    double incomeAmount = 0;

    @Override
    protected MineAction initAction() {
        return new MineAction((RxAppCompatActivity) getContext(), this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getContext();
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        binding();
        mImmersionBar.setStatusBarView(getActivity(), topView);
        return view;
    }

    @Override
    protected void initialize() {
        init();
    }

    @Override
    protected void init() {
        super.init();
        if (MySp.iSLoginLive(mContext)) {
            loadDialog();
        }
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        loadDiss();
        if (isVisible) {
            ((MainActivity) getActivity()).changeStatusBar(false, R.color.color_38a234);
        }
    }

    @OnClick({R.id.ll_user_info, R.id.ll_income, R.id.ll_my_inquiry, R.id.ll_my_prescription, R.id.ll_setting, R.id.ll_feedback,
            R.id.ll_prescription_template, R.id.ll_ask_doctors, R.id.ll_evaluation, R.id.ll_invitation})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_user_info:
                //todo 个人信息
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                jumpActivityNotFinish(mContext, UserInfoActivity.class);
                break;
            case R.id.ll_income:
                //todo 我的收入
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                Intent incomeIntent = new Intent(mContext, IncomeActivity.class);
                incomeIntent.putExtra("incomeAmount", incomeAmount);
                startActivity(incomeIntent);
                break;
            case R.id.ll_my_inquiry:
                //todo 我的问诊单
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                jumpActivityNotFinish(mContext, MyInquiryActivity.class);
                break;
            case R.id.ll_my_prescription:
                //todo 我的处方药
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                jumpActivityNotFinish(mContext, MyPrescriptionActivity.class);
                break;
            case R.id.ll_setting:
                //todo 设置
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                jumpActivityNotFinish(mContext, SettingActivity.class);
                break;
            case R.id.ll_feedback:
                //todo 意见反馈
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                jumpActivityNotFinish(mContext, FeedbackActivity.class);
                break;
            case R.id.ll_prescription_template:
                //todo 处方模板
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                Intent intent = new Intent(mContext, SelectPrescriptionActivity.class);
                intent.putExtra("isSelect", true);
                startActivity(intent);
                break;
            case R.id.ll_ask_doctors:
                //todo 设置问诊费
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                UpdateFactPriceDialog modifyDialog = new UpdateFactPriceDialog(mContext, R.style.MY_AlertDialog, ResUtil.getString(R.string.mine_tip_10));
                modifyDialog.setOnClickListener(new UpdateFactPriceDialog.OnClickListener() {
                    @Override
                    public void confirm(String txet) {
                        if (TextUtils.isEmpty(txet)) {
                            showToast(ResUtil.getString(R.string.mine_tip_10));
                            return;
                        } else {
                            //todo 判断小数点是否在第一位
                            if (Utilt.isNumber(txet)){
                                updateFactPrice("0"+txet);
                            }else {
                                updateFactPrice(txet);
                            }

                            modifyDialog.dismiss();
                        }
                    }
                });
                modifyDialog.show();
                break;
            case R.id.ll_evaluation:
                //todo 我的评价
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                jumpActivityNotFinish(mContext, EvaluationActivity.class);
                break;
            case R.id.ll_invitation:
                //todo 邀请用户
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                jumpActivityNotFinish(mContext, InvitationActivity.class);
                break;
        }
    }

    @Override
    public void isLogin() {
        if (CheckNetwork.checkNetwork2(mContext)) {
//            loadDialog(ResUtil.getString(R.string.registered_tip_17));
            baseAction.isLogin();
        }
    }

    @Override
    public void isLoginSuccessful() {
        loadDiss();
        getUserInfo();
    }

    /**
     * 获取用户信息
     */
    @Override
    public void getUserInfo() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getUserInfo();
        }
    }

    /**
     * 获取用户信息成功
     *
     * @param userInfoDto
     */
    @Override
    public void getUserInfoSuccessful(UserInfoDto userInfoDto) {
        loadDiss();
        if (userInfoDto.getCode() == 1) {
            loadDiss();
            UserInfoDto.DataBean userInfoBean = userInfoDto.getData();
            userNmaeTv.setText(userInfoBean.getDocName());
            userPositionTv.setText(userInfoBean.getThe_level());
            userHospitalTv.setText(userInfoBean.getHospital());
            incomeAmount = userInfoBean.getIncome();
            incomeAmountTv.setText("￥" + userInfoBean.getIncome());
            askDoctorsTv.setText("￥" + PriceUtils.formatPrice(userInfoBean.getFact_price()));
            userHospitalTv.setVisibility(View.VISIBLE);
            userPositionTv.setVisibility(View.VISIBLE);
            String portrait = userInfoBean.getNiceImg();
            if (portrait.indexOf("DOC") != -1) {
                GlideUtil.setImage(mContext, WebUrlUtil.IMG_URL + portrait, userPortraitIv, R.drawable.icon_placeholder);
                L.e("lgh", WebUrlUtil.IMG_URL + portrait);
            } else {
                GlideUtil.setImage(mContext, WebUrlUtil.IMG_URL + "DOC/my" + portrait, userPortraitIv, R.drawable.icon_placeholder);
                L.e("lgh", WebUrlUtil.IMG_URL + "DOC/my" + portrait);
            }
        } else {
            loadDiss();
            MySp.setToken(mContext, null);
        }
    }

    @Override
    public void updateFactPrice(String num) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.updateFactPrice(num);
        }
    }

    @Override
    public void updateFactPriceSuccessful(GeneralDto generalDto) {
        loadDiss();
        showToast(generalDto.getMsg());
        if (generalDto.getCode() == 1) {
            loadDialog();
            getUserInfo();
        }
    }

    @Override
    public void onError(String message, int code) {
        loadDiss();
        if (!TextUtils.isEmpty(message)) {
            showToast(message);
        }
    }

    @Override
    public void isLoginError() {
        loadDiss();
        jumpActivityNotFinish(mContext, LoginActivity.class);
    }

    @Override
    public void onLigonError() {

    }

    @Override
    public void onResume() {
        super.onResume();
        baseAction.toRegister();
        if (MySp.getToken(mContext) != null) {
            isLogin();
        } else {
            userPortraitIv.setImageResource(R.drawable.icon_placeholder);
            userNmaeTv.setText("未登录");
            userPositionTv.setVisibility(View.GONE);
            userHospitalTv.setVisibility(View.GONE);
            incomeAmountTv.setText("￥" + 0);
            askDoctorsTv.setText("￥" + 0);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }

}
