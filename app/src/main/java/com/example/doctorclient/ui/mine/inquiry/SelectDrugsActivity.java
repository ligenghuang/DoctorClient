package com.example.doctorclient.ui.mine.inquiry;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.SelectDrugsAction;
import com.example.doctorclient.adapter.DrugAdapter;
import com.example.doctorclient.adapter.DrugClassAdapter;
import com.example.doctorclient.event.DrugClassDto;
import com.example.doctorclient.event.DrugClassListDto;
import com.example.doctorclient.event.DrugDetailsDto;
import com.example.doctorclient.event.DrugListDto;
import com.example.doctorclient.ui.impl.SelectDrugsView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.dialog.DrugDetailsDialog;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.http.POST;

/**
 * description:选择药品
 * autour: huang
 * date: 2019/5/20 11:15
 * update: 2019/5/20
 * version:
 */
public class SelectDrugsActivity extends UserBaseActivity<SelectDrugsAction> implements SelectDrugsView {

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

    @BindView(R.id.tv_drug_num)
    TextView drugNumTv;
    @BindView(R.id.tv_drug_total)
    TextView drugTotalTv;
    @BindView(R.id.et_search)
    EditText searchEt;

    DrugClassAdapter drugClassAdapter;
    DrugAdapter drugAdapter;

    List<DrugListDto.DataBean> list = new ArrayList<>();

    int type = 0;
    int Position = 0;
    int Num = 0;
    boolean isSelect = false;

    String name = "";

    @Override
    protected SelectDrugsAction initAction() {
        return new SelectDrugsAction(this, this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_select_drugs;
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
                .addTag("SelectDrugsActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.select_drugs_title));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        isSelect = getIntent().getBooleanExtra("isSelect", false);

        drugClassAdapter = new DrugClassAdapter();
        departRv.setLayoutManager(new LinearLayoutManager(this));
        departRv.setAdapter(drugClassAdapter);

        drugAdapter = new DrugAdapter(this);
        drugRv.setAdapter(drugAdapter);
        drugRv.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        getDrugClass();
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        drugClassAdapter.setOnClcikListener(new DrugClassAdapter.OnClcikListener() {
            @Override
            public void OnClick(String drugClass, int position) {
                for (int i = 0; i < drugClassAdapter.mList.size(); i++) {
                    drugClassAdapter.mList.get(i).setClick(position == i);
                }
                type = position;
                drugClassAdapter.notifyDataSetChanged();
                getDrugByClass(drugClass);
            }
        });

        drugAdapter.setOnClickListener(new DrugAdapter.OnClickListener() {
            @Override
            public void OnClick(DrugListDto.DataBean model) {
                int total = 0;
                L.e("lgh_list", "name  = " + model.toString());
                for (int i = 0; i < drugAdapter.mList.size(); i++) {
                    if (drugAdapter.mList.get(i).getIUID().equals(model.getIUID())) {
                        drugAdapter.mList.get(i).setDrug_num(model.getDrug_num());
                    }
                }
                if (list.size() == 0) {
                    list.add(model);
                    total = model.getDrug_num();
                } else {
                    boolean isSet = false;
                    for (int i = 0; i < list.size(); i++) {
                        if (model.getIUID().equals(list.get(i).getIUID())) {
                            list.set(i, model);
                            isSet = true;
                        }
                    }

                    if (!isSet) {
                        list.add(model);
                    }
                    for (int i = 0; i < list.size(); i++) {
                        total = total + list.get(i).getDrug_num();
                    }

                }
                int num = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getDrug_num() > 0) {
                        num = num + 1;
                    }
                }

                drugNumTv.setText(ResUtil.getFormatString(R.string.select_drugs_tip_2, num + ""));
                drugTotalTv.setText(ResUtil.getFormatString(R.string.select_drugs_tip_3, total + ""));
            }

            @Override
            public void OnitemViewClick(String iuid, int position, int num) {
                Position = position;
                getDrugByIuid(iuid);
                Num = num;
            }
        });

        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    hideInput();
                    if (!TextUtils.isEmpty(searchEt.getText().toString())) {
                        name = searchEt.getText().toString();
                        if (CheckNetwork.checkNetwork2(mContext)) {
                            loadDialog();
                            baseAction.getDrugClass(searchEt.getText().toString());
                        }
                    } else {
                        name = "";
                        showNormalToast(ResUtil.getString(R.string.select_drugs_tip_1));
                    }
                    return true;
                }
                return false;
            }
        });

    }

    @OnClick(R.id.tv_next)
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
                //TODO  携带药品列表跳转页面
                List<DrugListDto.DataBean> lists = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getDrug_num() != 0) {
                        lists.add(list.get(i));
                    }
                }
                Intent intent;
                if (isSelect) {
                    intent = new Intent();
                    intent.putExtra("list", (Serializable) lists);
                    setResult(200, intent);
                    finish();
                } else {
                    if (lists.size() == 0) {
                        showNormalToast(ResUtil.getString(R.string.edit_prescription_tip_16));
                        return;
                    }
                    intent = new Intent(mContext, EditPrescriptionActivity.class);
                    intent.putExtra("list", (Serializable) lists);
                    startActivity(intent);
                }

                break;
        }
    }

    /**
     * 获取药品分类列表
     */
    @Override
    public void getDrugClass() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getDrugClass();
        }
    }

    /**
     * 获取药品分类列表成功
     *
     * @param drugClassListDto
     */
    @Override
    public void getDrugClassSuccessful(DrugClassListDto drugClassListDto) {
        type = 0;
        loadDiss();
       if (drugClassListDto.getData().size() != 0){
           List<DrugClassDto> list = new ArrayList<>();
           List<String> drugList = drugClassListDto.getData();
           for (int i = 0; i < drugClassListDto.getData().size(); i++) {
               DrugClassDto drugClassDto = new DrugClassDto();
               drugClassDto.setName(drugList.get(i));
               drugClassDto.setClick(i == 0);
               list.add(drugClassDto);
           }
           drugClassAdapter.refresh(list);
           getDrugByClass(drugList.get(0));
       }
    }

    /**
     * 获取药品列表
     *
     * @param drugClass
     */
    @Override
    public void getDrugByClass(String drugClass) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            if (TextUtils.isEmpty(name)) {
                baseAction.getDrugByClass(drugClass);
            }else {
                baseAction.getDrugByClass(drugClass,name);
            }
        }
    }

    /**
     * 获取药品列表成功
     *
     * @param drugListDto
     */
    @Override
    public void getDrugByClassSuccessful(DrugListDto drugListDto) {
        loadDiss();
        if (drugClassAdapter.mList.get(type).getDataBean().size() == 0) {
            drugClassAdapter.mList.get(type).setDataBean(drugListDto.getData());
        }
        drugAdapter.refresh(drugClassAdapter.mList.get(type).getDataBean());
    }

    /**
     * 获取药品详情
     *
     * @param iuid
     */
    @Override
    public void getDrugByIuid(String iuid) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getDrugByIuid(iuid);
        }
    }

    /**
     * 获取药品详情成功
     *
     * @param drugDetailsDto
     */
    @Override
    public void getDrugByIuidSuccessful(DrugDetailsDto drugDetailsDto) {
        loadDiss();
        DrugDetailsDialog drugDetailsDialog = new DrugDetailsDialog(mContext, R.style.MY_AlertDialog, drugDetailsDto.getData(), Num);
        drugDetailsDialog.setOnClickListener(new DrugDetailsDialog.OnClickListener() {
            @Override
            public void submit(String iuid, int num) {
                int total = 0;
                L.e("lgh", "iuid  = " + iuid);
                DrugListDto.DataBean model = null;
                for (int i = 0; i < drugAdapter.mList.size(); i++) {
                    if (drugAdapter.mList.get(i).getIUID().equals(iuid)) {
                        drugAdapter.mList.get(i).setDrug_num(num);
                        model = drugAdapter.mList.get(i);
                    }
                }
                drugAdapter.notifyDataSetChanged();
                if (list.size() == 0) {
                    list.add(model);
                    total = model.getDrug_num();
                } else {
                    boolean isSet = false;
                    for (int i = 0; i < list.size(); i++) {
                        if (model.getIUID().equals(list.get(i).getIUID())) {
                            list.set(i, model);
                            isSet = true;
                        }
                    }
                    if (!isSet) {
                        list.add(model);
                    }
                    for (int i = 0; i < list.size(); i++) {
                        total = total + list.get(i).getDrug_num();
                    }

                }
                drugNumTv.setText(ResUtil.getFormatString(R.string.select_drugs_tip_2, list.size() + ""));
                drugTotalTv.setText(ResUtil.getFormatString(R.string.select_drugs_tip_3, total + ""));
            }
        });
        drugDetailsDialog.show();
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
