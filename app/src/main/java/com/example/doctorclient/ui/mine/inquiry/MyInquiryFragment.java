package com.example.doctorclient.ui.mine.inquiry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.BaseAction;
import com.example.doctorclient.actions.MyInquiryAction;
import com.example.doctorclient.adapter.MyInquiryAdapter;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.MyInquiryDto;
import com.example.doctorclient.ui.impl.MyInquiryView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.ui.message.MessageDetailActivity;
import com.example.doctorclient.util.base.UserBaseFragment;
import com.example.doctorclient.util.data.MySp;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.data.IsFastClick;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的问诊单 fragment
 *
 * @author lgh
 * created at 2019/5/17 0017 15:18
 */
public class MyInquiryFragment extends UserBaseFragment<MyInquiryAction> implements MyInquiryView {
    View view;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    MyInquiryAdapter myInquiryAdapter;
    int position = 0;

    String ConfirmationId;
    String UserId;
    boolean isMessage = false;
    boolean isActivity = true;

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
            isActivity = bundle.getBoolean("isActivity");
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

        myInquiryAdapter = new MyInquiryAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(myInquiryAdapter);

    }

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected MyInquiryAction initAction() {
        return new MyInquiryAction((RxAppCompatActivity) mActivity, this);
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
    protected void loadView() {
        super.loadView();
        myInquiryAdapter.setOnClickListener(new MyInquiryAdapter.OnClickListener() {
            @Override
            public void confirmationListener(String iuid, String Userid) {
                if (MyInquiryActivity.Position == position) {
                    Confirmation(iuid);
                    ConfirmationId = iuid;
                    UserId = Userid;
                }
            }
        });
    }


    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        L.e("myinquiry", "个人中心 onFragmentVisibleChange........." + isVisible);
        if (isVisible && MySp.iSLoginLive(mContext) && MyInquiryActivity.Position == position) {
            //更新界面数据，如果数据还在下载中，就显示加载框
//            loadNet();
//            loadDialog();
            getAskHead();
        }
//        else if(!MySp.iSLoginLive(mContext) && MyInquiryActivity.Position == position){
//            onLigonError();
//        }
    }

    @Override
    protected void onFragmentFirstVisible() {
        //去服务器下载数据
        L.e("xx", "个人中心 onFragmentFirstVisible.........");
    }


    public static MyInquiryFragment newInstance(int position,boolean isActivity) {
        MyInquiryFragment fragment = new MyInquiryFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putBoolean("isActivity",isActivity);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 获取问诊单列表
     */
    @Override
    public void getAskHead() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getAskHead(position - 1);
        }
    }

    /**
     * 获取问诊单列表成功
     *
     * @param myInquiryDto
     */
    @Override
    public void getAskHeadSuccessful(MyInquiryDto myInquiryDto) {
        myInquiryAdapter.refresh(myInquiryDto.getData());
        loadDiss();
        loadDiss();
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
            if (MyInquiryActivity.Position == position) {
                Intent intent = new Intent(mContext, MessageDetailActivity.class);
                intent.putExtra("touserId", UserId);
                intent.putExtra("askId", ConfirmationId);
                intent.putExtra("isFirst", true);
                startActivity(intent);
            }

        } else {
            showToast(generalDto.getMsg());
        }
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
        if (!TextUtils.isEmpty(message)) {
            showToast(message);
        }
    }

    /**
     * 未登录
     */
    @Override
    public void onLigonError() {
        loadDiss();
      if (isActivity){
          jumpActivityNotFinish(mContext, LoginActivity.class);
      }
    }

    @Override
    public void onResume() {
        super.onResume();
        IsFastClick.lastClickTime = 0;
        if (baseAction != null) {
            baseAction.toRegister();
        }
        L.e("lgh", "onResume  = " + true);
        if (MyInquiryActivity.Position == position&&MySp.iSLoginLive(mContext)) {
            getAskHead();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }

}
