package com.example.doctorclient.ui.mine.inquiry;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.BaseAction;
import com.example.doctorclient.actions.InquiryDetailsAction;
import com.example.doctorclient.adapter.IllessImgAdapter;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.InquiryDetailDto;
import com.example.doctorclient.ui.impl.InquiryDetailsView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.ui.message.MessageDetailActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;
import java.util.logging.Level;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 问诊详情
 *
 * @author lgh
 * created at 2019/5/18 0018 11:51
 */
public class InquiryDetailsActivity extends UserBaseActivity<InquiryDetailsAction> implements InquiryDetailsView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.tv_name)
    TextView nameTv;
    @BindView(R.id.tv_sex)
    TextView sexTv;
    @BindView(R.id.tv_age)
    TextView ageTv;
    @BindView(R.id.tv_weight)
    TextView weighrTv;
    @BindView(R.id.tv_allergy)
    TextView allergyTv;
    @BindView(R.id.tv_family)
    TextView familyTv;
    @BindView(R.id.tv_medical_history)
    TextView medicalHistoryTv;
    @BindView(R.id.tv_illness)
    TextView illnessTv;
    @BindView(R.id.rv_img_illness)
    RecyclerView imgIllnessRv;

    @BindView(R.id.ll_prescription)
    LinearLayout prescriptionLl;
    @BindView(R.id.rv_prescription)
    RecyclerView prescriptionRv;
    @BindView(R.id.tv_prescription_note)
    TextView prescriptionNoteTv;
    @BindView(R.id.tv_view_chat_history)
    TextView viewChatHistoryTv;
    @BindView(R.id.tv_inquity_diagnosis)
    TextView diagnosisTv;

    IllessImgAdapter illessImgAdapter;

    String iuid;

    String touserId;
    String askId;

    boolean isSelect = false;
    boolean isAccepts = false;

    @Override
    protected InquiryDetailsAction initAction() {
        return new InquiryDetailsAction(this, this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_inquiry_details;
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
                .addTag("InquiryDetailsActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.inquity_tip_15));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        iuid = getIntent().getStringExtra("iuid");
        isSelect = getIntent().getBooleanExtra("isSelect",false);
        isAccepts = getIntent().getBooleanExtra("isAccepts",false);
        L.e("lgh_accepts","isAccepts  = "+isAccepts);
        viewChatHistoryTv.setText(ResUtil.getString(isAccepts?R.string.inquity_tip_6:R.string.inquity_tip_27));
        illessImgAdapter = new IllessImgAdapter(mContext);
        imgIllnessRv.setLayoutManager(new LinearLayoutManager(mContext));
        imgIllnessRv.setAdapter(illessImgAdapter);


    }

    @OnClick(R.id.tv_view_chat_history)
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_view_chat_history:
                if (isAccepts) {
                    Confirmation(askId);
                } else {
                    if (isSelect) {
                        finish();
                    } else {
                        Intent intent = new Intent(mContext, MessageDetailActivity.class);
                        intent.putExtra("touserId", touserId);
                        intent.putExtra("askId", askId);
                        startActivity(intent);
                    }
                }

                break;
        }
    }

    /**
     * 获取问诊详情
     */
    @Override
    public void getAskHeadById() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getAskHeadById(iuid);
        }
    }

    /**
     * 获取问诊详情成功
     * @param inquiryDetailDto
     */
    @Override
    public void getAskHeadByIdSuccessful(InquiryDetailDto inquiryDetailDto) {
        loadDiss();
        InquiryDetailDto.DataBean dataBean = inquiryDetailDto.getData();
        InquiryDetailDto.DataBean.PatientMVBean patientMVBean = dataBean.getPatientMV();
        touserId = dataBean.getUserid();
        askId = dataBean.getAskIUID();
        viewChatHistoryTv.setVisibility(View.VISIBLE);
        nameTv.setText(patientMVBean.getName());
        sexTv.setText(patientMVBean.getSex());
        ageTv.setText(ResUtil.getFormatString(R.string.inquity_tip_28, patientMVBean.getAge() + ""));
        if (!TextUtils.isEmpty(patientMVBean.getWeight())) {
            weighrTv.setText(patientMVBean.getWeight() + "KG");
        }
        allergyTv.setText(patientMVBean.getAllergy_note());
        familyTv.setText(patientMVBean.getMed_family());
        medicalHistoryTv.setText(patientMVBean.getMed_history());
        diagnosisTv.setText(dataBean.getDiagnosis());
        illnessTv.setText(dataBean.getIll_note());
        illessImgAdapter.refresh(dataBean.getIll_img());
    }
    /**
     * 确认接诊
     *
     * @param iuid
     */
    @Override
    public void Confirmation(String iuid) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.Confirmation(iuid);
        }
    }

    /**
     * 确认接诊成功
     *
     * @param generalDto
     */
    @Override
    public void ConfirmationSuccessful(GeneralDto generalDto) {
        loadDiss();
        if (generalDto.getCode() == 1) {
            Intent intent = new Intent(mContext, MessageDetailActivity.class);
            intent.putExtra("touserId", iuid);
            intent.putExtra("askId", askId);
            intent.putExtra("isFirst", true);
            startActivity(intent);

        } else {
            showToast(generalDto.getMsg());
        }
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
        if (baseAction != null) {
            baseAction.toRegister();
        }
        if (!TextUtils.isEmpty(iuid)){
            getAskHeadById();
        }
    }

    @Override
    protected void onPause() {
        L.e("lgh_error","onPause  - - 1");
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
        L.e("lgh_error","onPause - - 2");
    }

    @Override
    protected void onDestroy() {
        L.e("lgh_error","onDestroy - - 1");
        super.onDestroy();
        L.e("lgh_error","onDestroy - - 2");
    }

    @Override
    public void finish() {
        L.e("lgh_error","finish - -1");
        super.finish();
        L.e("lgh_error","finish - -2");
    }
}
