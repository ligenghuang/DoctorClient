package com.example.doctorclient.ui.message;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.BaseAction;
import com.example.doctorclient.actions.MessageAction;
import com.example.doctorclient.adapter.MessageLlistAdapter;
import com.example.doctorclient.event.MessageDto;
import com.example.doctorclient.event.MessageListDto;
import com.example.doctorclient.ui.MainActivity;
import com.example.doctorclient.ui.impl.MessageView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.util.base.UserBaseFragment;
import com.example.doctorclient.util.data.MySp;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.data.ResUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
* 消息fragment
* @author lgh
* created at 2019/5/15 0015 14:29
*/
public class MessageFragment extends UserBaseFragment<MessageAction> implements MessageView {
    View view;
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    MessageLlistAdapter messageLlistAdapter;

    @Override
    protected MessageAction initAction() {
        return new MessageAction((RxAppCompatActivity) getActivity(),this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getContext();
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        binding();
        mImmersionBar.setStatusBarView(getActivity(), topView);
        return view;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible){
            ((MainActivity) getActivity()).changeStatusBar(true, R.color.white);
           if (MySp.iSLoginLive(mContext)){
               getMessageList();
           }
        }

    }

    @Override
    protected void initialize() {
        init();
    }

    @Override
    protected void init() {
        super.init();
        titleTv.setText(ResUtil.getString(R.string.tab_message));

        messageLlistAdapter = new MessageLlistAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(messageLlistAdapter);
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
        getMessageList();
    }

    /**
     * 未登录
     */
    @Override
    public void isLoginError() {
        loadDiss();
        jumpActivityNotFinish(mContext, LoginActivity.class);
    }

    /**
     * 获取消息列表
     */
    @Override
    public void getMessageList() {
        if (CheckNetwork.checkNetwork2(mContext)){
//            loadDialog();
            baseAction.getMessageList();
        }
    }

    /**
     * 获取消息列表成功
     * @param messageListDto
     */
    @Override
    public void getMessageListSuccessful(MessageListDto messageListDto) {
        messageLlistAdapter.refresh(messageListDto.getData());
    }

    /**
     * Socket接收到消息
     * @param messageDto
     */
    @Override
    public void getMessage(MessageDto messageDto) {
        getMessageList();
    }

    @Override
    public void onError(String message, int code) {

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
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }
}
