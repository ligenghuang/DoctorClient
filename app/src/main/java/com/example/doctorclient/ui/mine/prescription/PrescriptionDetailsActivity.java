package com.example.doctorclient.ui.mine.prescription;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.PrescriptionDetailsAction;
import com.example.doctorclient.adapter.IllessImgAdapter;
import com.example.doctorclient.adapter.PrescriptionDetailDrugAdapter;
import com.example.doctorclient.event.PrescriptionDetailDto;
import com.example.doctorclient.ui.impl.PrescriptionDetailsView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.ui.mine.inquiry.SelectPrescriptionDetailsActivity;
import com.example.doctorclient.ui.mine.inquiry.SelectPrescriptionDetailsActivity2;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.cusview.CustomLinearLayoutManager;
import com.example.doctorclient.util.data.DynamicTimeFormat;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.PriceUtils;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:处方详情
 * autour: huang
 * date: 2019/5/22 10:33
 * update: 2019/5/22
 * version:
 */
public class PrescriptionDetailsActivity extends UserBaseActivity<PrescriptionDetailsAction> implements PrescriptionDetailsView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.tv_name)
    TextView nameTv;
    @BindView(R.id.tv_age)
    TextView ageTv;
    @BindView(R.id.tv_unwind)
    TextView unwindTv;
    @BindView(R.id.ll_user_info)
    LinearLayout userInfoLl;
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

    @BindView(R.id.tv_prescription_no)
    TextView prescriptionNoTv;
    @BindView(R.id.rv_prescription_detail)
    RecyclerView prescriptionDetailRv;

    @BindView(R.id.tv_prescription_time)
    TextView prescriptionTimeTv;
    @BindView(R.id.tv_order_time)
    TextView orderTimeTv;
    @BindView(R.id.tv_pay_type)
    TextView payTypeTv;
    @BindView(R.id.tv_order_total)
    TextView orderTotalTv;
    @BindView(R.id.tv_real_pay)
    TextView realPayTv;
    @BindView(R.id.tv_price)
    TextView priceTv;

    String iuid;
    String drugIuid;
    boolean isUnwind = false;

    IllessImgAdapter illessImgAdapter;
    PrescriptionDetailDrugAdapter prescriptionDetailDrugAdapter;
    CustomLinearLayoutManager linearLayoutManager;

    @Override
    protected PrescriptionDetailsAction initAction() {
        return new PrescriptionDetailsAction(this, this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_prescription_details;
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
                .addTag("PrescriptionDetailsActivity")  //给上面参数打标记，以后可以通过标记恢复
                .statusBarDarkFont(true, 0.2f)
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.prescription_tip_15));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        illessImgAdapter = new IllessImgAdapter(mContext);
        imgIllnessRv.setLayoutManager(new LinearLayoutManager(mContext));
        imgIllnessRv.setAdapter(illessImgAdapter);

        prescriptionDetailDrugAdapter = new PrescriptionDetailDrugAdapter(mContext);
        linearLayoutManager = new CustomLinearLayoutManager(this);
        linearLayoutManager.setScrollEnabled(false);
        linearLayoutManager.setStackFromEnd(true);
        prescriptionDetailRv.setLayoutManager(linearLayoutManager);
        prescriptionDetailRv.setAdapter(prescriptionDetailDrugAdapter);

        iuid = getIntent().getStringExtra("iuid");

    }

    @Override
    public void getPreInfo(String iuid) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getPreInfo(iuid);
        }
    }

    @OnClick({R.id.tv_unwind,R.id.tv_edit})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_unwind:
                isUnwind = !isUnwind;
                userInfoLl.setVisibility(isUnwind ? View.VISIBLE : View.GONE);
                unwindTv.setSelected(isUnwind);
                unwindTv.setText(ResUtil.getString(isUnwind ? R.string.prescription_tip_8: R.string.prescription_tip_7));
                break;
            case R.id.tv_edit:
                Intent intent = new Intent(mContext,  SelectPrescriptionDetailsActivity2.class);
                intent.putExtra("iuid",iuid);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getPreInfoSuccessful(PrescriptionDetailDto prescriptionDetailDto) {
        loadDiss();
        PrescriptionDetailDto.DataBean dataBean = prescriptionDetailDto.getData();
        PrescriptionDetailDto.DataBean.PatientMVBean patientMVBean = dataBean.getPatientMV();
        nameTv.setText(patientMVBean.getName());
        ageTv.setText("("+patientMVBean.getSex()+"   "+patientMVBean.getAge()+"岁）");
        allergyTv.setText(patientMVBean.getAllergy_note());
        familyTv.setText(patientMVBean.getMed_family());
        medicalHistoryTv.setText(patientMVBean.getMed_history());
        illnessTv.setText(dataBean.getIll_note());
        illessImgAdapter.refresh(dataBean.getIll_img());

        prescriptionNoTv.setText(ResUtil.getFormatString(R.string.prescription_tip_17,dataBean.getAskdrug_no()));
        priceTv.setText(ResUtil.getFormatString(R.string.prescription_tip_16,PriceUtils.formatPrice(dataBean.getBrokerage())));
        prescriptionTimeTv.setText(DynamicTimeFormat.LongToString5(dataBean.getCreate_time_stamp()));
        orderTimeTv.setText(DynamicTimeFormat.LongToString5(dataBean.getPay_time_stamp()));
        orderTotalTv.setText(PriceUtils.formatPrice(dataBean.getDrug_money()));
        realPayTv.setText(PriceUtils.formatPrice(dataBean.getPay_money()));
        payTypeTv.setText(dataBean.getPay_class());
        prescriptionDetailDrugAdapter.refresh(dataBean.getDrugMV());
    }

    @Override
    public void onError(String message, int code) {
        loadDiss();
        showNormalToast(message);
    }

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
            getPreInfo(iuid);
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
