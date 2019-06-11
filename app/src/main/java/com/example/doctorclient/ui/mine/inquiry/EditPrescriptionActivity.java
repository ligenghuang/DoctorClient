package com.example.doctorclient.ui.mine.inquiry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.EditPrescriptionAction;
import com.example.doctorclient.adapter.DrugListAdapter;
import com.example.doctorclient.event.DrugListDto;
import com.example.doctorclient.event.EditPrescriptionDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.PrescriptionDrugListDto;
import com.example.doctorclient.event.post.DrugSavePost;
import com.example.doctorclient.ui.impl.EditPrescriptionView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.cusview.CustomLinearLayoutManager;
import com.example.doctorclient.util.data.MySp;
import com.example.doctorclient.util.dialog.ModifyDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
 * description: 编辑处方
 * autour: huang
 * date: 2019/5/21 9:17
 * update: 2019/5/21
 * version:
 */
public class EditPrescriptionActivity extends UserBaseActivity<EditPrescriptionAction> implements EditPrescriptionView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.tv_prescription_name)
    TextView prescriptionNameTv;
    @BindView(R.id.tv_prescription_project)
    TextView prescriptionProjectTv;
    @BindView(R.id.et_item_drug_note)
    TextView DrugNoteEt;
    @BindView(R.id.tv_note_num)
    TextView noteNumTv;

    @BindView(R.id.rv_drug)
    RecyclerView drugRv;

    List<DrugListDto.DataBean> list = new ArrayList<>();

    DrugListAdapter drugListAdapter;

    String prescriptionName;
    int depart = 1;

    @Override
    public int intiLayout() {
        return R.layout.activity_edit_prescription;
    }

    @Override
    protected EditPrescriptionAction initAction() {
        return new EditPrescriptionAction(this, this);
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
                .addTag("EditPrescriptionActivity")  //给上面参数打标记，以后可以通过标记恢复
                .statusBarDarkFont(true, 0.2f)
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

        drugListAdapter = new DrugListAdapter(this);
        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(this);
        linearLayoutManager.setScrollEnabled(false);
        linearLayoutManager.setStackFromEnd(true);
        drugRv.setLayoutManager(linearLayoutManager);
        drugRv.setAdapter(drugListAdapter);

        list = (List<DrugListDto.DataBean>) getIntent().getSerializableExtra("list");
        drugListAdapter.refresh(list);
        initView();
        loadView();
    }

    @Override
    protected void initView() {
        super.initView();
       if (!TextUtils.isEmpty(MySp.getData(mContext))){
           EditPrescriptionDto editPrescriptionDto = new Gson().fromJson(MySp.getData(mContext),new TypeToken<EditPrescriptionDto>() {
           }.getType());
           depart = editPrescriptionDto.getDepart();
           prescriptionProjectTv.setText(editPrescriptionDto.getDepartName());
           prescriptionName = editPrescriptionDto.getPrescriptionName();
           prescriptionNameTv.setText(prescriptionName);
           DrugNoteEt.setText(editPrescriptionDto.getNote());
           if (TextUtils.isEmpty(DrugNoteEt.getText().toString())) {
               noteNumTv.setText("0/200");
           } else {
               noteNumTv.setText(DrugNoteEt.getText().length() + "/200");
           }
       }
    }

    @Override
    protected void loadView() {
        super.loadView();
        //TODO 监听输入框字数
        DrugNoteEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(DrugNoteEt.getText().toString())) {
                    noteNumTv.setText("0/200");
                } else {
                    noteNumTv.setText(DrugNoteEt.getText().length() + "/200");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.tv_submit, R.id.tv_add_drug, R.id.tv_prescription_project, R.id.tv_prescription_name})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                //TODO 提交数据
                submit();
                break;
            case R.id.tv_add_drug:
                //TODO 添加药品
                EditPrescriptionDto editPrescriptionDto = new EditPrescriptionDto();
                editPrescriptionDto.setDepart(depart);
                editPrescriptionDto.setDepartName(prescriptionProjectTv.getText().toString());
                editPrescriptionDto.setNote(DrugNoteEt.getText().toString());
                editPrescriptionDto.setPrescriptionName(prescriptionName);
                MySp.setData(mContext,editPrescriptionDto.toString());
               finish();
                break;
            case R.id.tv_prescription_project:
                //TODO 选择科室
                Intent intent = new Intent(mContext, SelectProjectActivity.class);
                startActivityForResult(intent, 201);
                break;
            case R.id.tv_prescription_name:
                //TODO 填写处方名称
                ModifyDialog modifyDialog = new ModifyDialog(mContext, R.style.MY_AlertDialog, ResUtil.getString(R.string.edit_prescription_tip_10));
                modifyDialog.setOnClickListener(new ModifyDialog.OnClickListener() {
                    @Override
                    public void confirm(String txet) {
                        if (TextUtils.isEmpty(txet)) {
                            showNormalToast(ResUtil.getString(R.string.edit_prescription_tip_10));
                        } else {
                            prescriptionName = txet;
                            prescriptionNameTv.setText(prescriptionName);
                            modifyDialog.dismiss();
                        }
                    }
                });
                modifyDialog.show();
                break;
        }
    }

    /**
     * 提交数据
     */
    private void submit() {
        if (TextUtils.isEmpty(prescriptionNameTv.getText().toString())) {
            showNormalToast(ResUtil.getString(R.string.edit_prescription_tip_10));
            return;
        }
        DrugSavePost drugSavePost = new DrugSavePost();
        drugSavePost.setType("1");
        drugSavePost.setName(prescriptionNameTv.getText().toString());
        drugSavePost.setDepart(depart + "");
        drugSavePost.setIuid("null");
        drugSavePost.setThe_memo(DrugNoteEt.getText().toString());
        List<DrugSavePost.DrugBean> drugBeans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            DrugSavePost.DrugBean drugBean = new DrugSavePost.DrugBean();
            DrugListDto.DataBean dataBean = list.get(i);
            if (dataBean.getDrug_num() < 0){
                showNormalToast(ResUtil.getString(R.string.edit_prescription_tip_15));
                return;
            }
            drugBean.setDrugid(dataBean.getIUID());
            drugBean.setDrug_num(dataBean.getDrug_num() + "");
            drugBean.setUse_note(dataBean.getNum_note());
            dataBean.setIUID(dataBean.getIUID());
            drugBeans.add(drugBean);
        }
        drugSavePost.setMycars(drugBeans);
        updateDrugSave(drugSavePost);
    }

    /**
     * 保存处方
     * @param drugSavePost
     */
    @Override
    public void updateDrugSave(DrugSavePost drugSavePost) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.updateDrugSave(drugSavePost);
        }
    }

    /**
     * 保存处方成功
     * @param generalDto
     */
    @Override
    public void updateDrugSaveSuccessful(GeneralDto generalDto) {
        loadDiss();
        MySp.setData(mContext,null);
        showNormalToast(generalDto.getMsg());
        ActivityStack.getInstance().exitClass(SelectDrugsActivity.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 201){
            if (data != null){
                depart = Integer.parseInt(data.getStringExtra("iuid"));
                String name = data.getStringExtra("name");
                prescriptionProjectTv.setText(name);
            }
        }
    }
}
