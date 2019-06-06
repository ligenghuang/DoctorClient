package com.example.doctorclient.ui.mine.inquiry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.SelectPrescriptionAction;
import com.example.doctorclient.adapter.DepartidAdapter;
import com.example.doctorclient.adapter.PrescriptionDrugsAdapter;
import com.example.doctorclient.event.DepartidDto;
import com.example.doctorclient.event.DrugDto;
import com.example.doctorclient.event.PrescriptionDrugsDto;
import com.example.doctorclient.ui.impl.SelectPrescriptionView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 选择处方
 *
 * @author lgh
 * created at 2019/5/18 0018 16:28
 */
public class SelectPrescriptionActivity extends UserBaseActivity<SelectPrescriptionAction> implements SelectPrescriptionView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;
    @BindView(R.id.f_right_tv)
    TextView rightTv;

    @BindView(R.id.rv_depart)
    RecyclerView departRv;
    @BindView(R.id.rv_drug)
    RecyclerView drugRv;
    @BindView(R.id.tv_new_prescription)
    TextView prescriptionTv;

    DepartidAdapter departidAdapter;
    PrescriptionDrugsAdapter prescriptionDrugsAdapter;

    boolean isSelect = false;

    @Override
    public int intiLayout() {
        return R.layout.activity_select_prescription;
    }

    @Override
    protected SelectPrescriptionAction initAction() {
        return new SelectPrescriptionAction(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        isSelect = getIntent().getBooleanExtra("isSelect", false);
        binding();
    }

    @Override
    protected void initTitlebar() {
        super.initTitlebar();
        mImmersionBar
                .statusBarView(R.id.top_view)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0.2f)
                .addTag("SelectPrescriptionActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.inquity_tip_33));
        if (!isSelect) {
            rightTv.setText(ResUtil.getString(R.string.inquity_tip_34));
            rightTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo 新增处方
                    jumpActivityNotFinish(SelectPrescriptionActivity.this, SelectDrugsActivity.class);
                }
            });
        } else {
            prescriptionTv.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        departidAdapter = new DepartidAdapter(this);
        departRv.setLayoutManager(new LinearLayoutManager(mContext));
        departRv.setAdapter(departidAdapter);

        prescriptionDrugsAdapter = new PrescriptionDrugsAdapter(mContext,isSelect);
        drugRv.setLayoutManager(new LinearLayoutManager(this));
        drugRv.setAdapter(prescriptionDrugsAdapter);


        loadView();
    }

    @OnClick(R.id.tv_new_prescription)
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_new_prescription:
                //todo 新增处方
                jumpActivityNotFinish(SelectPrescriptionActivity.this, SelectDrugsActivity.class);
                break;
        }
    }

    @Override
    protected void loadView() {
        super.loadView();
        departidAdapter.setOnClickListener(new DepartidAdapter.OnClickListener() {
            @Override
            public void onClick(String id, int position) {
                getDrugsaveHeadByDepId(id);
            }

            @Override
            public void onChildClick(String id, int position) {
                getDrugsaveHeadByDepId(id);
            }
        });
    }

    /**
     * 获取科室
     */
    @Override
    public void getDepartList() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getFindDepartid();
        }
    }

    /**
     * 获取科室成功
     * @param departidDto
     */
    @Override
    public void getDepartListSuccessful(DepartidDto departidDto) {
        loadDiss();
        List<DepartidDto.DataBean> dataBeanList = departidDto.getData();
        List<DepartidDto.DataBean> dataBeanList1 = new ArrayList<>();
        for (int i = 0; i < dataBeanList.size(); i++) {
            if (dataBeanList.get(i).getParentid().isEmpty()) {
                dataBeanList1.add(dataBeanList.get(i));
            }
        }
        dataBeanList.remove(dataBeanList1);
        List<DepartidDto.DataBean> parentList = new ArrayList<>();
        for (int i = 0; i < dataBeanList1.size(); i++) {
            DepartidDto.DataBean dataBean = dataBeanList1.get(i);
            List<DepartidDto.DataBean> list = new ArrayList<>();
            for (int j = 0; j < dataBeanList.size(); j++) {
                if (dataBeanList.get(j).getParentid().equals(dataBean.getIUID())) {
                    list.add(dataBeanList.get(j));
                }
            }
            dataBean.setChildData(list);
            parentList.add(dataBean);
        }
        parentList.get(0).setClick(true);
        departidAdapter.refresh(parentList);
        getDrugsaveHeadByDepId(parentList.get(0).getIUID());
    }

    /**
     * 获取处方列表
     * @param id
     */
    @Override
    public void getDrugsaveHeadByDepId(String id) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getDrugsaveHeadByDepId(id);
        }
    }

    /**
     * 获取处方列表成功
     * @param prescriptionDrugsDto
     */
    @Override
    public void getDrugsaveHeadByDepIdSuccessful(PrescriptionDrugsDto prescriptionDrugsDto) {
        loadDiss();
        prescriptionDrugsAdapter.refresh(prescriptionDrugsDto.getData());
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
            getDepartList();
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
