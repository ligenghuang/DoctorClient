package com.example.doctorclient.ui.mine.prescription;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.MyPrescriptionAction;
import com.example.doctorclient.adapter.MyPrescriptionAdapter;
import com.example.doctorclient.event.MyPrescriptionDto;
import com.example.doctorclient.ui.impl.MyPrescriptionView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.ui.mine.inquiry.MyInquiryActivity;
import com.example.doctorclient.util.base.UserBaseFragment;
import com.example.doctorclient.util.data.MySp;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.data.IsFastClick;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
* 我的处方
* @author lgh
* created at 2019/5/18 0018 10:19
*/
public class MyPrescriptionFragment extends UserBaseFragment<MyPrescriptionAction> implements MyPrescriptionView {
    View view;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    MyPrescriptionAdapter myPrescriptionAdapter;
    int position = 0;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getContext();
        mActivity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.e("xx", "ArrangingManageFragment  个人中心   onCreate.........");
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            position = bundle.getInt("position");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_inquiry, container, false);
        ButterKnife.bind(this, view);
        binding();
        mImmersionBar.statusBarDarkFont(true, 0.2f).init();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    protected void init() {
        super.init();

        myPrescriptionAdapter = new MyPrescriptionAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(myPrescriptionAdapter);

    }

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected MyPrescriptionAction initAction() {
        return new MyPrescriptionAction((RxAppCompatActivity) mActivity, this);
    }

    @Override
    protected void initialize() {
        init();
        initView();
        loadView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        L.e("xx", "个人中心 onFragmentVisibleChange........." + isVisible);
        if (isVisible && MySp.iSLoginLive(mContext) && MyPrescriptionActivity.Position == position) {
            //更新界面数据，如果数据还在下载中，就显示加载框
//            loadNet();
            loadDialog();
            getPrescription();
        }
//        else if(!MySp.iSLoginLive(mContext) && MyPrescriptionActivity.Position == position){
//            onLigonError();
//        }
    }

    @Override
    protected void onFragmentFirstVisible() {
        //去服务器下载数据
        L.e("xx", "个人中心 onFragmentFirstVisible.........");
    }


    public static MyPrescriptionFragment newInstance(int position) {
        MyPrescriptionFragment fragment = new MyPrescriptionFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void getPrescription() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.getPrescription(position-1);
        }
    }

    @Override
    public void getPrescriptionSuccessful(MyPrescriptionDto myPrescriptionDto) {
        loadDiss();
        myPrescriptionAdapter.refresh(myPrescriptionDto.getData());
    }


    @Override
    public void onError(String message, int code) {
        loadDiss();
        if (MyInquiryActivity.Position == position) {
            showToast(message);
        }
    }
    @Override
    public void onLigonError() {
        if (MyInquiryActivity.Position == position) {
            jumpActivity(mContext, LoginActivity.class);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        IsFastClick.lastClickTime = 0;
        if (baseAction != null) {
            baseAction.toRegister();
        }
        if (MyPrescriptionActivity.Position == position){
            getPrescription();
        }
        L.e("lgh", "onResume  = " + position);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }


}
