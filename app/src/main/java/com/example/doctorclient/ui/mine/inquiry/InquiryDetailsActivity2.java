package com.example.doctorclient.ui.mine.inquiry;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doctorclient.BuildConfig;
import com.example.doctorclient.R;
import com.example.doctorclient.actions.PhotographPrescriptionAction;
import com.example.doctorclient.adapter.IllessImgAdapter;
import com.example.doctorclient.adapter.PhotographPrescriptionAdapter;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.InquiryDetailDto;
import com.example.doctorclient.event.PrescriptionListDto;
import com.example.doctorclient.ui.MainActivity;
import com.example.doctorclient.ui.impl.PhotographPrescriptionView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.ui.message.MessageDetailActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.cusview.CustomLinearLayoutManager;
import com.example.doctorclient.util.dialog.PicturesDialog;
import com.example.doctorclient.util.imageloader.GlideImageLoader;
import com.example.doctorclient.util.photo.PicUtils;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description: 问诊详情
 * autour: huang
 * date: 2019/5/28 10:14
 * update: 2019/5/28
 * version:
 */
public class InquiryDetailsActivity2 extends UserBaseActivity<PhotographPrescriptionAction> implements PhotographPrescriptionView {

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

    @BindView(R.id.rv_prescription)
    RecyclerView prescriptionRv;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    IllessImgAdapter illessImgAdapter;
    PhotographPrescriptionAdapter photographPrescriptionAdapter;

    String iuid;
    String touserId;
    String askId;

    boolean isSelect = false;
    boolean isAccepts = false;

    @Override
    public int intiLayout() {
        return R.layout.activity_inquiry_details_2;
    }


    @Override
    protected PhotographPrescriptionAction initAction() {
        return new PhotographPrescriptionAction(this, this);
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
                .addTag("PhotographPrescriptionActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.inquity_tip_15));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        iuid = getIntent().getStringExtra("iuid");
        isSelect = getIntent().getBooleanExtra("isSelect", false);
        isAccepts = getIntent().getBooleanExtra("isAccepts", false);
        mTvSubmit.setText(ResUtil.getString(isAccepts?R.string.inquity_tip_6:R.string.inquity_tip_27));
        illessImgAdapter = new IllessImgAdapter(mContext);
        imgIllnessRv.setLayoutManager(new LinearLayoutManager(mContext));
        imgIllnessRv.setAdapter(illessImgAdapter);

        photographPrescriptionAdapter = new PhotographPrescriptionAdapter(mContext);
        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(this);
        linearLayoutManager.setScrollEnabled(false);
        linearLayoutManager.setStackFromEnd(true);
        prescriptionRv.setLayoutManager(linearLayoutManager);
        prescriptionRv.setAdapter(photographPrescriptionAdapter);
        isLogin();
    }

    @OnClick({R.id.tv_submit})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                if (isSelect){
                    finish();
                }else {
                    Intent intent = new Intent(mContext, MessageDetailActivity.class);
                    intent.putExtra("touserId", touserId);
                    intent.putExtra("askId", askId);
                    startActivity(intent);
                }

                break;
        }
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
        loadDiss();
        getAskHeadById();
        getPrescriptionList();
    }

    /**
     * 获取问诊信息
     */
    @Override
    public void getAskHeadById() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getAskHeadById(iuid);
        }
    }

    /**
     * 获取问诊信息成功
     *
     * @param inquiryDetailDto
     */
    @Override
    public void getAskHeadByIdSuccessful(InquiryDetailDto inquiryDetailDto) {
        loadDiss();
        InquiryDetailDto.DataBean dataBean = inquiryDetailDto.getData();
        InquiryDetailDto.DataBean.PatientMVBean patientMVBean = dataBean.getPatientMV();
        touserId = dataBean.getUserid();
        askId = dataBean.getAskIUID();
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

    /**
     * 获取处方药品列表
     */
    @Override
    public void getPrescriptionList() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getPrescriptionList(iuid);
        }
    }

    /**
     * 获取处方药品列表成功
     *
     * @param prescriptionListDto
     */
    @Override
    public void getPrescriptionListSuccessful(PrescriptionListDto prescriptionListDto) {
        loadDiss();
        photographPrescriptionAdapter.refresh(prescriptionListDto.getData());
    }

    /**
     * 上传图片
     *
     * @param str
     */
    @Override
    public void updataFileName(String str) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.updatafileName(str);
        }
    }

    /**
     * 上传图片成功
     *
     * @param str
     */
    @Override
    public void updataFileNameSuccessful(String str) {
        loadDiss();
    }

    /**
     * 保存处方信息
     *
     * @param iuid
     * @param diagonsis
     * @param theImg
     */
    @Override
    public void savePhotographPrescription(String iuid, String diagonsis, String theImg) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.savePhotographPrescription(iuid, diagonsis, theImg);
        }
    }

    /**
     * 保存处方信息成功
     *
     * @param generalDto
     */
    @Override
    public void savePhotographPrescriptionSuccessful(GeneralDto generalDto) {
        loadDiss();
        showNormalToast(generalDto.getMsg());
        finish();
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
        jumpActivity(mContext, LoginActivity.class);
        ActivityStack.getInstance().exitIsNotHaveMain(MainActivity.class, LoginActivity.class);
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
