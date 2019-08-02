package com.example.doctorclient.ui.mine.inquiry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.PrescriptionAction;
import com.example.doctorclient.adapter.IllessImgAdapter;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.InquiryDetailDto;
import com.example.doctorclient.ui.impl.PrescriptionView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 开处方
 *
 * @author lgh
 * created at 2019/5/18 0018 14:56
 */
public class PrescriptionActivity extends UserBaseActivity<PrescriptionAction> implements PrescriptionView {
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

    @BindView(R.id.et_diagnostic_message)
    EditText diagnosticMessageEt;
    @BindView(R.id.tv_diagnostic_message_num)
    TextView diagnosticMessageNumTv;

    IllessImgAdapter illessImgAdapter;

    String iuid;


    @Override
    public int intiLayout() {
        return R.layout.activity_prescription;
    }

    @Override
    protected PrescriptionAction initAction() {
        return new PrescriptionAction(this, this);
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

        illessImgAdapter = new IllessImgAdapter(mContext,imgIllnessRv);
        imgIllnessRv.setLayoutManager(new GridLayoutManager(mContext,3));
        imgIllnessRv.setAdapter(illessImgAdapter);

        getAskHeadById();
        loadView();
    }


    @Override
    protected void loadView() {
        super.loadView();
        diagnosticMessageEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //TODO 监听输入框字数变化
                if (TextUtils.isEmpty(diagnosticMessageEt.getText().toString())){
                    diagnosticMessageNumTv.setText("0/300");
                }else {
                    diagnosticMessageNumTv.setText(diagnosticMessageEt.getText().length()+"/300");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        diagnosticMessageEt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //canScrollVertically()方法为判断指定方向上是否可以滚动,参数为正数或负数,负数检查向上是否可以滚动,正数为检查向下是否可以滚动
                if (diagnosticMessageEt.canScrollVertically(1) || diagnosticMessageEt.canScrollVertically(-1)){
                    v.getParent().requestDisallowInterceptTouchEvent(true);//requestDisallowInterceptTouchEvent();要求父类布局不在拦截触摸事件
                    if (event.getAction() == MotionEvent.ACTION_UP){ //判断是否松开
                        v.getParent().requestDisallowInterceptTouchEvent(false); //requestDisallowInterceptTouchEvent();让父类布局继续拦截触摸事件
                    }
                }
                return false;
            }
        });
    }

    @OnClick({R.id.tv_prescription,R.id.tv_submit})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_prescription:
                //todo 选择处方
                jumpActivityNotFinish(mContext,SelectPrescriptionActivity.class);
                break;
            case R.id.tv_submit:
                //todo 提交
                if (TextUtils.isEmpty(diagnosticMessageEt.getText().toString())){
                    showNormalToast(ResUtil.getString(R.string.inquity_tip_30));
                    return;
                }
                updateDiagnosis(iuid,diagnosticMessageEt.getText().toString());
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
        nameTv.setText(patientMVBean.getName());
        sexTv.setText(patientMVBean.getSex());
        ageTv.setText(ResUtil.getFormatString(R.string.inquity_tip_28, patientMVBean.getAge() + ""));
        if (!TextUtils.isEmpty(patientMVBean.getWeight())) {
            weighrTv.setText(patientMVBean.getWeight() + "KG");
        }
        allergyTv.setText(patientMVBean.getAllergy_note());
        familyTv.setText(patientMVBean.getMed_family());
        medicalHistoryTv.setText(patientMVBean.getMed_history());

        illnessTv.setText(dataBean.getIll_note());
        illessImgAdapter.refresh(dataBean.getIll_img());
    }

    @Override
    public void updateDiagnosis(String iuid, String diagonsis) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.updateDiagnosis(iuid,diagonsis);
        }
    }

    @Override
    public void updateDiagnosisSuccessful(GeneralDto generalDto) {
        loadDiss();
        showNormalToast(generalDto.getMsg());
        finish();
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
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }
}
