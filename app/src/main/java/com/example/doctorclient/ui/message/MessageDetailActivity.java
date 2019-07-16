package com.example.doctorclient.ui.message;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doctorclient.BuildConfig;
import com.example.doctorclient.R;
import com.example.doctorclient.actions.MessageDetailAction;
import com.example.doctorclient.adapter.CommonLanguageAdpater;
import com.example.doctorclient.adapter.MessageDetailListAdapter;
import com.example.doctorclient.event.CheckOnlineDto;
import com.example.doctorclient.event.CommonLanguageListDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.MessageDetailInquiryDto;
import com.example.doctorclient.event.MessageDetailListDto;
import com.example.doctorclient.event.MessageDto;
import com.example.doctorclient.event.RongUserInfoDto;
import com.example.doctorclient.event.SendMessageDto;
import com.example.doctorclient.ui.MainActivity;
import com.example.doctorclient.ui.impl.MessageDetailView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.ui.mine.inquiry.InquiryDetailsActivity;
import com.example.doctorclient.ui.mine.inquiry.InquiryDetailsActivity2;
import com.example.doctorclient.ui.mine.inquiry.PrescriptionActivity;
import com.example.doctorclient.ui.mine.inquiry.SelectPrescriptionActivity;
import com.example.doctorclient.util.Constanst;
import com.example.doctorclient.util.Utilt;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.config.MyApp;
import com.example.doctorclient.util.cusview.CustomLinearLayoutManager;
import com.example.doctorclient.util.cusview.SoftKeyBoardListener;
import com.example.doctorclient.util.data.DynamicTimeFormat;
import com.example.doctorclient.util.data.MySp;
import com.example.doctorclient.util.dialog.PicturesDialog;
import com.example.doctorclient.util.imageloader.GlideImageLoader;
import com.example.doctorclient.util.photo.PicUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.java_websocket.client.WebSocketClient;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.callkit.RongCallAction;
import io.rong.callkit.RongVoIPIntent;
import io.rong.callkit.SingleCallActivity;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

import static com.lgh.huanglib.util.cusview.magicIndicator.ScrollState.SCROLL_STATE_IDLE;

/**
 * description:消息
 * autour: huang
 * date: 2019/5/23 10:16
 * update: 2019/5/23
 * version:
 */
public class MessageDetailActivity extends UserBaseActivity<MessageDetailAction> implements MessageDetailView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.ll_info)
    LinearLayout infoLl;
    @BindView(R.id.tv_end_session_time)
    TextView endSessionTimeTv;
    @BindView(R.id.tv_info_name)
    TextView nameInfoTv;
    @BindView(R.id.tv_info_age)
    TextView ageInfoTv;
    @BindView(R.id.tv_illness)
    TextView illnessTv;
    @BindView(R.id.ll_prescription)
    LinearLayout prescriptionLl;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.edit_direct)
    EditText editText;
    @BindView(R.id.tv_send)
    TextView sendTv;
    @BindView(R.id.tv_add)
    TextView addTv;
    @BindView(R.id.ll_add)
    LinearLayout addLl;

    /**
     * 拍照 常用语
     */
    @BindView(R.id.linear)
    LinearLayout linear;
    /**
     * 常用语列表
     */
    @BindView(R.id.ll_common_language)
    LinearLayout commonLanguageLl;
    @BindView(R.id.rv_common_language)
    RecyclerView commonLanguageRv;
    @BindView(R.id.ll_add_common_language)
    LinearLayout addCommonLanguageLl;
    @BindView(R.id.ll_edit_common_languag)
    LinearLayout editCommonLanguageLl;
    @BindView(R.id.edit_common_language)
    EditText editCommonLanguageEt;
    @BindView(R.id.tv_complete)
    TextView completeTv;
    @BindView(R.id.ll_end_session)
    LinearLayout endSessionLl;
    @BindView(R.id.send_ll)
    LinearLayout sendLl;
    @BindView(R.id.ll_end_session_2)
    LinearLayout endSessionLl2;

    String touserId;
    String userid;
    String askId;
    String askIuid;
    int count = 0;
    //是否加载更多
    boolean isSlect = true;
    boolean isRefresh = false;
    int ask_flag = 0;

    boolean isSend = false;
    boolean isAdd = false;
    boolean isFirst = false;

    MessageDetailListAdapter messageDetailListAdapter;

    WebSocketClient client;

    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    public static final int REQUEST_CODE_TAKE = 102;
    public static final int REQUEST_CODE_ALBUM = 103;
    public static int REQUEST_SELECT_TYPE = -1;//选择的类型
    private ArrayList<ImageItem> selImageList = new ArrayList<>(); //当前选择的所有图片
    ArrayList<ImageItem> images = null;

    CommonLanguageAdpater commonLanguageAdpater;
    private RecyclerView.SmoothScroller smoothScroller;
    CustomLinearLayoutManager linearLayoutManager;

    @Override
    public int intiLayout() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected MessageDetailAction initAction() {
        return new MessageDetailAction(this, this);
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
                .addTag("MessageDetailActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.tab_message));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        touserId = getIntent().getStringExtra("touserId");
        askId = getIntent().getStringExtra("askId");
        L.e("lgh_askid","askid  = "+askId);
        isFirst = getIntent().getBooleanExtra("isFirst",false);
        userid = MySp.getToken(mContext);

        messageDetailListAdapter = new MessageDetailListAdapter(mContext, touserId);
        linearLayoutManager = new CustomLinearLayoutManager(this);
        linearLayoutManager.setScrollEnabled(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(messageDetailListAdapter);
        smoothScroller = new LinearSmoothScroller(this) {

            @Override

            protected int getVerticalSnapPreference() {

                return LinearSmoothScroller.SNAP_TO_START;

            }

        };

        commonLanguageAdpater = new CommonLanguageAdpater();
        commonLanguageRv.setLayoutManager(new LinearLayoutManager(mContext));
        commonLanguageRv.setAdapter(commonLanguageAdpater);

        refreshLayout.setEnableLoadMore(false);
        initImagePicker();
        isLogin();
        loadView();
        SoftKeyBoardListener.setListener(this, onSoftKeyBoardChangeListener);
    }

    /**
     * 软键盘弹出收起监听
     */
    private SoftKeyBoardListener.OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener = new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
        @Override
        public void keyBoardShow(int height) {
            L.e("lgh_key","键盘显示 高度 : " + height);
            infoLl.setVisibility(View.GONE);
            recyclerView.scrollToPosition(messageDetailListAdapter.getAllData().size() - 1);
        }
        @Override
        public void keyBoardHide(int height) {
            L.e("lgh_key","键盘隐藏 高度 : " + height);
            infoLl.setVisibility(View.VISIBLE);
            recyclerView.scrollToPosition(messageDetailListAdapter.getAllData().size() - 1);
        }
    };

    @Override
    protected void loadView() {
        super.loadView();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addTv.setVisibility(TextUtils.isEmpty(editText.getText().toString()) ? View.VISIBLE : View.GONE);
                sendTv.setVisibility(TextUtils.isEmpty(editText.getText().toString()) ? View.GONE : View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //滑动监听
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState != SCROLL_STATE_IDLE) {
                    hideInput();
                    isAdd = false;
                    addLl.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideInput();
                isAdd = false;
                addLl.setVisibility(View.GONE);
                return false;
            }
        });

        //布局缩小时滑到最后一项
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5,
                                       int i6, int i7) {
                if (i3 < i7) {
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.scrollToPosition(messageDetailListAdapter.getAllData().size() - 1);
                        }
                    }, 100);
                }
            }
        });

        /**
         * 加载更多聊天记录
         */
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getAskChatMore();
                L.e("lgh", "onRefresh");
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                L.e("lgh", "onLoadMore");
            }
        });

        commonLanguageAdpater.setOnClickListener(new CommonLanguageAdpater.OnClickListener() {
            @Override
            public void onClick(String txt) {
                sendMessage(txt);
            }

            @Override
            public void onClose(CommonLanguageListDto.DataBean model) {
                deleteCommonLanguage(model.getIuid());
            }
        });
    }


    @OnClick({R.id.tv_send, R.id.tv_add, R.id.tv_photo, R.id.edit_direct, R.id.ll_info,
            R.id.tv_prescription, R.id.tv_photograph, R.id.tv_common_expression, R.id.tv_add_common_language,
            R.id.tv_send_common_language, R.id.tv_end_session, R.id.tv_edit_common_language, R.id.tv_complete, R.id.tv_video, R.id.tv_audio})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_send:
                //todo 发送
                if (!TextUtils.isEmpty(editText.getText().toString())) {
                    sendMessage(editText.getText().toString());
                }

                break;
            case R.id.tv_add:
                //todo 添加
                isAdd = !isAdd;
                addLl.setVisibility(isAdd ? View.VISIBLE : View.GONE);
                linear.setVisibility(View.VISIBLE);
                commonLanguageLl.setVisibility(View.GONE);
                hideInput();
                break;
            case R.id.tv_photo:
                //TODO 图片
                showSelectDiaLog();
                break;
            case R.id.edit_direct:
                isAdd = false;
                addLl.setVisibility(View.GONE);
                break;
            case R.id.ll_info:
                //TODO 问诊信息
                Intent intent;
                if (ask_flag == 1){
                    intent = new Intent(mContext, InquiryDetailsActivity2.class);
                    intent.putExtra("iuid", askId);
                    intent.putExtra("isSelect", true);
                    startActivity(intent);
                }else {
                    intent = new Intent(mContext, PrescriptionActivity.class);
                    intent.putExtra("iuid",askId);
                    startActivity(intent);
                }
                break;
            case R.id.tv_prescription:
                //TODO 开方
                Intent i = new Intent(mContext, SelectPrescriptionActivity.class);
                startActivity(i);
                break;
            case R.id.tv_photograph:
                //todo 拍照开方
                Intent intent1 = new Intent(mContext, PhotographPrescriptionActivity.class);
                intent1.putExtra("iuid", askId);
                startActivity(intent1);
                break;
            case R.id.tv_common_expression:
                //todo 获取常用语
                getCommonLanguage();
                break;
            case R.id.tv_add_common_language:
                //todo 新增常用语
                addCommonLanguageLl.setVisibility(View.GONE);
                editCommonLanguageLl.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_send_common_language:
                //todo 提交新增常用语
                sendCommonLanguage();
                break;
            case R.id.tv_end_session:
                //TODO 结束会话
                endSession();
                break;
            case R.id.tv_edit_common_language:
                //todo 编辑常用语
                commonLanguageAdpater.setEdit(true);
                completeTv.setVisibility(View.VISIBLE);
                addCommonLanguageLl.setVisibility(View.GONE);
                break;
            case R.id.tv_complete:
                //todo 完成编辑常用语
                commonLanguageAdpater.setEdit(false);
                completeTv.setVisibility(View.GONE);
                addCommonLanguageLl.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_video:
                //todo 视频聊天
                checkOnline(1);
                addllGone();
                break;
            case R.id.tv_audio:
                //todo 语音聊天
                checkOnline(2);
                addllGone();
                break;
        }
    }

    /**
     * 隐藏布局
     */
    private void addllGone() {
        isAdd = false;
        addLl.setVisibility(View.GONE);
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.scrollToPosition(messageDetailListAdapter.getAllData().size() - 1);
            }
        }, 100);
    }

    /**
     * 判断对方是否在线
     *
     * @param type
     */
    private void checkOnline(int type) {
        int rand = Utilt.getRandom();
        String timestamp = DynamicTimeFormat.getTimestamp();
        String signature = Utilt.shaEncrypt(Constanst.appSecret + rand + timestamp);
        OkHttpUtils.post().url("http://api-cn.ronghub.com/user/checkOnline.json")
                .addHeader("App-Key", Constanst.appkey)
                .addHeader("Nonce", rand + "")
                .addHeader("Timestamp", timestamp)
                .addHeader("Signature", signature)
                .addParams("userId", touserId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        L.d("lgh_userId", "请求错误.." + e.toString());
                        L.d("lgh_userId", "请求错误.." + call.toString());
                        L.d("lgh_userId", "请求错误.." + call.request().url().toString());
                        L.d("lgh_userId", "请求错误.." + call.request().toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        L.d("response:" + response);
                        CheckOnlineDto checkOnlineDto = new Gson().fromJson(response, new TypeToken<CheckOnlineDto>() {
                        }.getType());
                        if (checkOnlineDto.getCode() == 200) {
                            if (checkOnlineDto.getStatus().equals("1")) {
                                setCallVideo(type);
                            } else {
                                showNormalToast("对方不在线！");
                            }
                        }
                    }
                });
    }

    /**
     * 获取对方信息并发起通话
     * @param type
     */
    private void setCallVideo(int type) {
        int rand = Utilt.getRandom();
        String timestamp = DynamicTimeFormat.getTimestamp();
        String signature = Utilt.shaEncrypt(Constanst.appSecret + rand + timestamp);
        OkHttpUtils.post().url("http://api-cn.ronghub.com/user/info.json")
                .addHeader("App-Key", Constanst.appkey)
                .addHeader("Nonce", rand + "")
                .addHeader("Timestamp", timestamp)
                .addHeader("Signature", signature)
                .addParams("userId", touserId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        L.d("lgh_userId", "请求错误.." + e.toString());
                        L.d("lgh_userId", "请求错误.." + call.toString());
                        L.d("lgh_userId", "请求错误.." + call.request().url().toString());
                        L.d("lgh_userId", "请求错误.." + call.request().toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        L.d("RongRTCVideoActivity:" + response);
                        RongUserInfoDto userInfoDto = new Gson().fromJson(response, new TypeToken<RongUserInfoDto>() {
                        }.getType());
                        Uri uri = Uri.parse(userInfoDto.getUserPortrait());
                        UserInfo userInfo = new UserInfo(touserId, userInfoDto.getUserName(), uri);

                        Intent intent = new Intent(mContext, SingleCallActivity.class);
                        switch (type) {
                            case 1:
                                //todo 视频
                                intent.setAction(RongVoIPIntent.RONG_INTENT_ACTION_VOIP_SINGLEVIDEO);
                                break;
                            case 2:
                                //TODO 语音
                                intent.setAction(RongVoIPIntent.RONG_INTENT_ACTION_VOIP_SINGLEAUDIO);
                                break;
                        }
                        intent.putExtra("checkPermissions", true);
                        intent.putExtra("callAction", RongCallAction.ACTION_OUTGOING_CALL.getName());
                        intent.putExtra("conversationType", Conversation.ConversationType.PRIVATE.getName());
                        intent.putExtra("targetId", touserId);
                        intent.putExtra("userInfoDto", (Parcelable) userInfo);
                        startActivity(intent);
                    }
                });
    }


    /**
     * 提交数据
     */
    private void sendCommonLanguage() {
        if (TextUtils.isEmpty(editCommonLanguageEt.getText().toString())) {
            showNormalToast(ResUtil.getString(R.string.message_tip_13));
            return;
        }
        sendCommonLanguage(editCommonLanguageEt.getText().toString());

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
        getAskHeadByUserId();
        getAskChat();
    }

    /**
     * *未登录
     */
    @Override
    public void isLoginError() {
        loadDiss();
        jumpActivity(mContext, LoginActivity.class);
    }

    /**
     * 获取问诊信息
     */
    @Override
    public void getAskHeadByUserId() {
        if (CheckNetwork.checkNetwork2(mContext)) {
//            loadDialog();
            baseAction.getAskHeadByUserId(touserId);
        }
    }

    /**
     * 获取问诊信息成功
     *
     * @param inquiryDetailDto
     */
    @Override
    public void getAskHeadByUserIdSuccessful(MessageDetailInquiryDto inquiryDetailDto) {
        loadDiss();
        MessageDetailInquiryDto.DataBean dataBean = inquiryDetailDto.getData();
        askIuid = dataBean.getAskID();
        MySp.setAskId(MyApp.getContext(),askIuid);
        ask_flag = dataBean.getDrugFlag();
        endSessionTimeTv.setText(ResUtil.getFormatString(R.string.message_tip_3, dataBean.getLastTime()));
        nameInfoTv.setText(dataBean.getName());
        ageInfoTv.setText(dataBean.getSex() + "   " + dataBean.getAge() + "岁");
        illnessTv.setText(dataBean.getNote());
        prescriptionLl.setVisibility(dataBean.getAskFlag() == 2 ? View.GONE : View.VISIBLE);
        int flag = inquiryDetailDto.getData().getAskFlag();
        if (flag == 2 || flag == 3){
            endSessionLl.setVisibility(View.GONE);
            infoLl.setVisibility(View.GONE);
            endSessionLl2.setVisibility(View.VISIBLE);
            sendLl.setVisibility(View.GONE);
        }else {
            endSessionLl.setVisibility(View.VISIBLE);
            infoLl.setVisibility(View.VISIBLE);
            endSessionLl2.setVisibility(View.GONE);
            sendLl.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 获取聊天消息
     */
    @Override
    public void getAskChat() {
        if (CheckNetwork.checkNetwork2(mContext)) {
//            loadDialog();
            isRefresh = true;
            count = 0;
            baseAction.getAskChat(touserId, count);
        }
    }

    /**
     * 获取更多聊天消息
     */
    @Override
    public void getAskChatMore() {
        if (CheckNetwork.checkNetwork2(mContext)) {
//            loadDialog();
            isRefresh = false;
            count++;
            baseAction.getAskChat(touserId, count);
        }
    }

    /**
     * 获取聊天消息成功
     */
    @Override
    public void getAskChatSuccessful(MessageDetailListDto messageDetailListDto) {
        loadDiss();
        refreshLayout.finishRefresh();
        L.e("lgh_size", "size  = " + (messageDetailListDto.getData().getList().size() == 10));
        L.e("lgh_size", "size  = " + messageDetailListDto.getData().getList().size() );
        isSlect = messageDetailListDto.getData().getList().size() == 10;
        if (isRefresh) {
            List<MessageDetailListDto.DataBean.ListBean> list = messageDetailListDto.getData().getList();
//            Collections.reverse(list);
            messageDetailListAdapter.refresh(list);
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.scrollToPosition(messageDetailListAdapter.getAllData().size() - 1);
                }
            }, 100);
        } else {
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.scrollToPosition(messageDetailListDto.getData().getList().size()+1);
                }
            }, 100);

            List<MessageDetailListDto.DataBean.ListBean> listBeans = messageDetailListDto.getData().getList();
//            Collections.reverse(listBeans);
            listBeans.addAll(messageDetailListAdapter.getAllData());
            messageDetailListAdapter.refresh(listBeans);
        }

        refreshLayout.setEnableRefresh(isSlect);
        if (isFirst){
            //todo 第一次发送问候语
            sendMessage("您好，请问有什么可以帮您？");
            isFirst = false;
        }

    }

    /**
     * 发送文本消息
     *
     * @param chat_note
     */
    @Override
    public void sendMessage(String chat_note) {
        if (CheckNetwork.checkNetwork2(mContext)) {
//            loadDialog();
            baseAction.sendMessage(chat_note, touserId, askId);
            editText.setText("");
        }
    }

    /**
     * 发送文本消息成功
     *
     * @param sendMessageDto
     */
    @Override
    public void sendMessageSuccessful(SendMessageDto sendMessageDto) {
        loadDiss();
        MainActivity.sendMessage("txt", sendMessageDto, mContext);

    }

    /**
     * 发送图片消息成功
     *
     * @param sendMessageDto
     */
    @Override
    public void sendPicturesaSuccessful(SendMessageDto sendMessageDto) {
        loadDiss();
        MainActivity.sendMessage("image", sendMessageDto, mContext);
    }

    /**
     * 结束会话
     */
    @Override
    public void endSession() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.endSession(touserId);
        }
    }

    /**
     * 结束会话成功
     *
     * @param generalDto
     */
    @Override
    public void endSessionSuccessful(GeneralDto generalDto) {
        loadDiss();
        showNormalToast(generalDto.getMsg());
        if (generalDto.getCode() == 1){
            endSessionLl.setVisibility(View.GONE);
            infoLl.setVisibility(View.GONE);
            endSessionLl2.setVisibility(View.VISIBLE);
            sendLl.setVisibility(View.GONE);
        }

    }

    /**
     * 获取常用语
     */
    @Override
    public void getCommonLanguage() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getCommonLanguage();
        }
    }

    /**
     * 获取常用语成功
     *
     * @param commonLanguageListDto
     */
    @Override
    public void getCommonLanguageSuccessful(CommonLanguageListDto commonLanguageListDto) {
        loadDiss();
        commonLanguageAdpater.setEdit(false);
        addCommonLanguageLl.setVisibility(View.VISIBLE);
        editCommonLanguageLl.setVisibility(View.GONE);
        linear.setVisibility(View.GONE);
        commonLanguageLl.setVisibility(View.VISIBLE);
        commonLanguageAdpater.refresh(commonLanguageListDto.getData());
    }

    /**
     * 新增常用语
     *
     * @param txt
     */
    @Override
    public void sendCommonLanguage(String txt) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.sendCommonLanguage(txt);
        }
    }

    /**
     * 新增常用语成功
     *
     * @param generalDto
     */
    @Override
    public void sendCommonLanguageSuccessful(GeneralDto generalDto) {
        loadDiss();
        if (generalDto.getCode() == 1) {
            editCommonLanguageEt.setText("");
            getCommonLanguage();
        } else {
            showNormalToast(generalDto.getMsg());
        }
    }

    /**
     * 删除常用语
     *
     * @param iuid
     */
    @Override
    public void deleteCommonLanguage(String iuid) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.deleteCommonLanguage(iuid);
        }
    }

    /**
     * 删除常用语成功
     *
     * @param generalDto
     */
    @Override
    public void deleteCommonLanguageSuccessful(GeneralDto generalDto) {
        loadDiss();
        if (generalDto.getCode() == 1) {
            getCommonLanguage();
        }
    }

    /**
     * Socket接收到消息
     *
     * @param messageDto
     */
    @Override
    public void getMessage(MessageDto messageDto) {
        isRefresh = true;
        getAskChat();
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

    @Override
    public void onLigonError() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (baseAction != null) {
            baseAction.toRegister();
        }
    }


    /*********************************选择图片*****************************************/

    /**
     * 选择图片
     */
    private void showSelectDiaLog() {
        PicturesDialog dialog = new PicturesDialog(this, R.style.MY_AlertDialog);
        dialog.setOnClickListener(new PicturesDialog.OnClickListener() {
            @Override
            public void onCamera() {
                takePhoto();
            }

            @Override
            public void onPhoto() {
                takeUserGally();
            }
        });
        dialog.show();
    }

    private void takePhoto() {
        /**
         * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
         *
         * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
         *
         * 如果实在有所需要，请直接下载源码引用。
         */
        REQUEST_SELECT_TYPE = REQUEST_CODE_TAKE;
        //打开选择,本次允许选择的数量
        ImagePicker.getInstance().setSelectLimit(1);
        Intent intent = new Intent(MessageDetailActivity.this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }

    private void takeUserGally() {
        //打开选择,本次允许选择的数量

        REQUEST_SELECT_TYPE = REQUEST_CODE_ALBUM;
        ImagePicker.getInstance().setSelectLimit(1);
        Intent intent1 = new Intent(MessageDetailActivity.this, ImageGridActivity.class);
        /* 如果需要进入选择的时候显示已经选中的图片，
         * 详情请查看ImagePickerActivity
         * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
        startActivityForResult(intent1, REQUEST_CODE_SELECT);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setMultiMode(false);
        imagePicker.setSaveRectangle(true);
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(400);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(400);                         //保存文件的高度。单位像素
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            L.e("xx", "添加图片返回....");
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                L.e("xx", REQUEST_SELECT_TYPE + "返回的图片 数量 " + images.size());
                switch (REQUEST_SELECT_TYPE) {
                    case REQUEST_CODE_ALBUM:
                        if (images != null) {
                            selImageList.addAll(images);

                            if (CheckNetwork.checkNetwork2(mContext)) {
                                File imgUri = new File(images.get(0).path);
                                Uri dataUri = FileProvider.getUriForFile
                                        (this, BuildConfig.APPLICATION_ID + ".android7.fileprovider", imgUri);
                                int zoomSacle = 3;
                                try {
                                    // 当图片大小大于512kb至少缩小两倍
                                    if (imgUri.length() / 1024 > 512) {
                                        zoomSacle = zoomSacle * 10;
                                    }
//
//                                    //todo  请求接口 发送图片消息
                                    L.e("lgh", "images.get(0).path  = " + images.get(0).path);
//                                    GlideUtil.setImageCircle(mContext,images.get(0).path,isPortrait?userPortaitIv:userCertificateIv,0);
                                    if (CheckNetwork.checkNetwork2(mContext)) {
                                        loadDialog();
                                        baseAction.sendPicturesa(images.get(0).path, touserId, askId);
                                    }
                                } catch (Exception e) {
                                    loadError(ResUtil.getString(R.string.main_select_phone_error), mContext);
                                }

                            }
                        }
                        break;
                    case REQUEST_CODE_TAKE:
//                        PicUtils.compressBmpToFile(images.get(0).path, photoOption);
                        File imgUri = new File(images.get(0).path);
                        Uri dataUri = FileProvider.getUriForFile
                                (this, BuildConfig.APPLICATION_ID + ".android7.fileprovider", imgUri);
                        int zoomSacle = 3;
                        try {
                            // 当图片大小大于512kb至少缩小两倍
                            if (imgUri.length() / 1024 > 512) {
                                zoomSacle = zoomSacle * 10;
                            }
                            PicUtils.showCutPhoto(data, zoomSacle, imgUri.getPath());
//                                    PicUtils.getCompressedImgPath(images.get(0).path, photoOption);
//                                    baseAction.uploadImage(images.get(0).path);
                        } catch (Exception e) {
                            loadError(ResUtil.getString(R.string.main_select_phone_error), mContext);
                        }

                        try {
                            //todo  请求接口 发送图片消息
//                            uploadAvatar(images.get(0).path);
                            L.e("lgh", "images.get(0).path  = " + images.get(0).path);
//                            GlideUtil.setImageCircle(mContext,images.get(0).path,isPortrait?userPortaitIv:userCertificateIv,0);
                            if (CheckNetwork.checkNetwork2(mContext)) {
                                loadDialog();
                                baseAction.sendPicturesa(images.get(0).path, touserId, askId);
                            }
                        } catch (Exception e) {
                            loadError(ResUtil.getString(R.string.main_select_phone_error), mContext);

                        }
                        break;
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            L.e("xx", "预览图片返回....");
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {

            }
        }

    }

    /**********************************修改头像 end*********************************************/

}
