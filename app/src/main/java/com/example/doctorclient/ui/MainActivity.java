package com.example.doctorclient.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.example.doctorclient.R;
import com.example.doctorclient.actions.BaseAction;
import com.example.doctorclient.event.MessageDto;
import com.example.doctorclient.event.RongUserInfoDto;
import com.example.doctorclient.event.RoogIMDto;
import com.example.doctorclient.event.SendMessageDto;
import com.example.doctorclient.event.UserInfoDto;
import com.example.doctorclient.event.post.SendMessagePost;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.ui.message.MessageFragment;
import com.example.doctorclient.ui.mine.MineFragment;
import com.example.doctorclient.ui.physicianvisits.PhysicianvisitsFragment;
import com.example.doctorclient.util.Constanst;
import com.example.doctorclient.util.Utilt;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.cusview.NotificationHelper;
import com.example.doctorclient.util.data.DynamicTimeFormat;
import com.example.doctorclient.util.data.MySp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.event.StoreEvent;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.base.MyFragmentPagerAdapter;
import com.lgh.huanglib.util.cusview.CustomViewPager;
import com.pgyersdk.feedback.PgyFeedbackShakeManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnTouch;
import io.rong.callkit.RongCallAction;
import io.rong.callkit.SingleCallActivity;
import io.rong.calllib.IRongReceivedCallListener;
import io.rong.calllib.RongCallClient;
import io.rong.calllib.RongCallCommon;
import io.rong.calllib.RongCallSession;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

import android.os.Process;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static android.app.Notification.VISIBILITY_SECRET;
import static com.lgh.huanglib.event.EventBusUtils.post;

public class MainActivity extends UserBaseActivity {

    private static final String TAG = "MainActivity";
    public static int Position = 2;
    private static final int POIONTONE = 0;
    private static final int POIONTTWO = 1;
    private static final int POIONTTHREE = 2;

    MineFragment mineFragment;
    MessageFragment messageFragment;
    PhysicianvisitsFragment physicianvisitsFragment;

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.my_pager)
    CustomViewPager myPager;
    @BindView(R.id.lin_1)
    RelativeLayout lin1;
    @BindView(R.id.lin_2)
    LinearLayout lin2;
    @BindView(R.id.lin_3)
    LinearLayout lin3;
    @BindView(R.id.iv_2)
    ImageView iv_2;

    private ArrayList<Fragment> fragments;
    private MyFragmentPagerAdapter fragmentPagerAdapter;
    private int fragmentSize = 3;
    // 是否能够退出
    private boolean isBack = false;

    // 上次按退出的时间
    private long downTime;

    static WebSocketClient client;

    int Heartbeat = 0;//心跳包发送失败次数

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    protected BaseAction initAction() {
        return null;
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        mImmersionBar
//                .statusBarView(R.id.top_view)
                .fullScreen(true)
                .navigationBarWithKitkatEnable(false)
                .statusBarDarkFont(true)
                .addTag("main")  //给上面参数打标记，以后可以通过标记恢复
                .init();
        initViewPager();


    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        fragments = new ArrayList<Fragment>();
        for (int i = 0; i < fragmentSize; i++) {
            switch (i) {
                case POIONTONE://
                    messageFragment = new MessageFragment();
                    if (Position != POIONTONE) {
                        messageFragment.setUserVisibleHint(false);//
                    }

                    fragments.add(messageFragment);
                    break;
                case POIONTTWO://
                    physicianvisitsFragment = new PhysicianvisitsFragment();
                    if (Position != POIONTTWO) {
                        physicianvisitsFragment.setUserVisibleHint(false);//
                    }
                    fragments.add(physicianvisitsFragment);
                    break;
                case POIONTTHREE://
                    mineFragment = new MineFragment();
                    if (Position != POIONTTHREE) {
                        mineFragment.setUserVisibleHint(false);
                    }
                    fragments.add(mineFragment);

                    break;

                default:
                    break;
            }
        }

        fragmentPagerAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(), fragments);

        fragmentPagerAdapter.setFragments(fragments);
        setSelectedLin(Position);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myPager.setAdapter(fragmentPagerAdapter);
                myPager.setCurrentItem(Position, false);
                myPager.setOffscreenPageLimit(fragmentSize);


            }
        }, 500);

    }

    @OnTouch({R.id.lin_1, R.id.lin_2, R.id.lin_3})
    public boolean onTouch(View v) {
        switch (v.getId()) {
            case R.id.lin_1:
                if (!MySp.iSLoginLive(mContext)) {
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return false;
                }
                Position = POIONTONE;
                break;
            case R.id.lin_2:
                if (!MySp.iSLoginLive(mContext)) {
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return false;
                }
                Position = POIONTTWO;
                break;
            case R.id.lin_3:
                Position = POIONTTHREE;
                break;
            default:
                break;
        }
        setSelectedLin(Position);
        myPager.setCurrentItem(Position, false);
        return false;
    }

    public void changeStatusBar(boolean isBlack, int bgColor) {
        mImmersionBar.statusBarDarkFont(isBlack).statusBarColor(bgColor).init();
    }


    /**
     * 显示是否有未读消息
     * @param b
     */
    public void setIv2(boolean b){
        L.e("lgh_l","b = "+b);
        iv_2.setVisibility(b?View.VISIBLE:View.GONE);
    }
    /**
     * 选择
     *
     * @param position
     */
    private void setSelectedLin(int position) {
        lin1.setSelected(false);
        lin2.setSelected(false);
        lin3.setSelected(false);
        //设置状态栏黑色字体与图标
//        QMUIStatusBarHelper.setStatusBarLightMode(this);
        switch (position) {
            case 0:
                lin1.setSelected(true);
                break;
            case 1:
                lin2.setSelected(true);
                break;
            case 2:
                lin3.setSelected(true);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:

                if (!isBack) {
                    showNormalToast(R.string.main_exit);
                    downTime = event.getDownTime();
                    isBack = true;
                    return true;
                } else {
                    if (event.getDownTime() - downTime <= 2000) {
                        ActivityStack.getInstance().removeAll();
                        Process.killProcess(Process.myPid());
                    } else {
                        showNormalToast(R.string.main_exit);
                        downTime = event.getDownTime();
                        return true;
                    }
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }


    boolean isFirst = true;

    private WebSocketClient initSocket(String url) throws URISyntaxException {
        return new WebSocketClient(new URI(url), new Draft_6455()) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.e("lgh_Socket:", "------连接成功!!!");
                isFirst = true;
                Heartbeat = 0;
                handler.postDelayed(runnable, 1000);
            }

            @Override
            public void onMessage(String message) {
                Log.e("lgh_Socket:", "接受到消息 message = " + message);
                if (isFirst) {
                    sendLogin();
                    isFirst = false;
                } else {
                    MessageDto messageDto = new Gson().fromJson(message, new TypeToken<MessageDto>() {
                    }.getType());
                    if (messageDto.getEvent().equals("chat")) {
                        sendEvent(StoreEvent.ACTION_KEY_SUCCESS, 200, WebUrlUtil.GET_MESSAGE,
                                Action.KEY_OBJ, message);
                        sendEvent(StoreEvent.ACTION_KEY_SUCCESS, 200, WebUrlUtil.GET_MESSAGE_1,
                                Action.KEY_OBJ, message);
                        if (MySp.getMessage(mContext)) {
//                            NotificationHelper.show(mContext,messageDto);
                        }
                    } else if (messageDto.getEvent().equals("return")) {
                        sendEvent(StoreEvent.ACTION_KEY_SUCCESS, 200, WebUrlUtil.GET_MESSAGE,
                                Action.KEY_OBJ, message);
                        sendEvent(StoreEvent.ACTION_KEY_SUCCESS, 200, WebUrlUtil.GET_MESSAGE_1,
                                Action.KEY_OBJ, message);

                    } else if (messageDto.getEvent().equals("Heartbeat")) {
                        Heartbeat = 0;
                    }
                }

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.e("lgh_Socket:", "------连接关闭!!!" + reason);
                isFirst = true;
                if (client.getReadyState() == READYSTATE.OPEN) {
                    client.close();
                    L.e("lgh_Socket", "Close the current client, ready to reconnect");
                } else if (client.getReadyState() == READYSTATE.NOT_YET_CONNECTED || client.getReadyState() == READYSTATE.CLOSED || client.getReadyState() == READYSTATE.CLOSING) {
                    try {
                        Thread.currentThread().sleep(500);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        client = initSocket(WebUrlUtil.SOCKET);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    client.connect();
                    L.e("lgh_Socket", "正在重连----------------------------");
                }
            }

            @Override
            public void onError(Exception ex) {
                Log.e("lgh_socket:", ex.toString());
            }
        };
    }

    /**
     * 登录Socket
     */
    private void loginSocket() {
        try {
            client = initSocket(WebUrlUtil.SOCKET);
            // wss需添加
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) {
                    Log.e("lgh_Socket:", "------checkClientTrusted!!!" + authType);
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) {
                    Log.e("lgh_Socket:", "------checkServerTrusted!!!" + authType);
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    Log.e("lgh_Socket:", "------getAcceptedIssuers!!!");
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            SSLSocketFactory factory = sslContext.getSocketFactory();
            client.setSocket(factory.createSocket());
            client.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("lgh_Socket:", "------URISyntaxException!!!" + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.e("lgh_Socket:", "------NoSuchAlgorithmException!!!" + e.getMessage());
        } catch (KeyManagementException e) {
            e.printStackTrace();
            Log.e("lgh_Socket:", "------KeyManagementException!!!" + e.getMessage());
        } catch (SSLException e) {
            e.printStackTrace();
            Log.e("lgh_Socket:", "------SSLException!!!" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("lgh_Socket:", "------IOException!!!" + e.getMessage());
        }
    }


    public void sendEvent(String type, int errorType, String identifying, Object... data) {
        if (isEmpty(type)) {
            throw new IllegalArgumentException("Type must not be empty");
        }

        if (data.length % 2 != 0) {
            throw new IllegalArgumentException("StoreData must be a valid list of key,value pairs");
        }

        Action.Builder actionBuilder = Action.type(type, errorType, identifying);
//        actionBuilder.clazz(actionClazz);
        int i = 0;
        while (i < data.length) {
            String key = (String) data[i++];
            Object value = data[i++];
            actionBuilder.bundle(key, value);
        }
        Log.e("xx", "准备post------->");
        post(actionBuilder.buildWithIdentifying());
    }

    private boolean isEmpty(String type) {
        return type == null || type.isEmpty();
    }

    Handler handler = new Handler();


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //要做的事情
            if (Heartbeat == 3) {
                try {
                    client = initSocket(WebUrlUtil.SOCKET);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                client.connect();
                L.e("lgh_Socket", "正在重连----------------------------");
            } else {
                sendHeartbeat();
                handler.postDelayed(this, 30000);
            }

        }
    };

    /**
     * 发送消息
     *
     * @param type
     */
    public static void sendMessage(String type, SendMessageDto sendMessageDto, Context context) {
        SendMessagePost post = new SendMessagePost();
        post.setEvent("chat");
        post.set_class(type);
        post.setIuid(sendMessageDto.getData().getIUID());
        post.setNote(sendMessageDto.getData().getChat_note());
        post.setTime(sendMessageDto.getData().getChat_time_stamp());
        post.setToUserId(sendMessageDto.getData().getTouserid());
        post.setUserid(MySp.getToken(context));
        post.setUserImg(sendMessageDto.getData().getUserIMG());
        L.e("lgh_Socket", post.toString());
        if (client != null && client.isOpen()) {
            client.send(post.toString());
        }

    }

    /**
     * 登录
     */
    public void sendLogin() {
        SendMessagePost post = new SendMessagePost();
        post.setEvent("login");
        post.setUserid(MySp.getToken(mContext));
        L.e("lgh_Socket", post.toLogin());
        client.send(post.toLogin());
    }

    /**
     * 发送心跳包
     */
    public void sendHeartbeat() {
        SendMessagePost post = new SendMessagePost();
        Heartbeat = Heartbeat + 1;
        post.setEvent("Heartbeat");
        post.setUserid(MySp.getToken(mContext));
        L.e("lgh_Socket", post.toLogin());
        if (client != null) {
            try {
                client.send(post.toLogin());
            } catch (WebsocketNotConnectedException e) {

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isFirst && MySp.iSLoginLive(mContext)) {
            loginSocket();
            loginRoogIM(MySp.getRoogUserId(mContext), MySp.getRoogUserName(mContext), WebUrlUtil.IMG_URL+MySp.getRoogUserImg(mContext));
        }
        if (!MySp.iSLoginLive(mContext)) {
            if (client != null) {
                client.close();
            }
        }

    }

    @Override
    public void finish() {
        super.finish();
        client.close();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }


    private void loginRoogIM(String userId, String name, String portraitUri) {
        int rand = Utilt.getRandom();
        String timestamp = DynamicTimeFormat.getTimestamp();
        String signature = Utilt.shaEncrypt(Constanst.appSecret + rand + timestamp);
        OkHttpUtils.post().url("http://api-cn.ronghub.com/user/getToken.json")
                .addHeader("App-Key", Constanst.appkey)
                .addHeader("Nonce", rand + "")
                .addHeader("Timestamp", timestamp)
                .addHeader("Signature", signature)
                .addParams("userId", userId)
                .addParams("name", name)
                .addParams("portraitUri", portraitUri)
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
                        RoogIMDto roogIMDto = JSON.parseObject(response, RoogIMDto.class);
                        if (roogIMDto.getCode() == 200) {
                            L.d("lgh_userId", "成功....." + roogIMDto.toString());
//                            loginlistener.onSuccess(accessTokenEntity);
                            connect(roogIMDto.getToken());
                            MySp.setRoogToken(mContext, roogIMDto.getToken());
                        } else {
                            L.d("lgh_userId", "获取失败");
                        }
                    }
                });
    }

    /**
     * 连接融云服务器
     *
     * @param token
     */
    private void connect(String token) {
        RongIMClient.connect(token, new RongIMClient.ConnectCallback() {

            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             *                            2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            @Override
            public void onTokenIncorrect() {

            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            @Override
            public void onSuccess(String userid) {
                L.e("lgh_userId", "onSuccess  userId  = " + userid);
                MySp.setRoogLoginUserId(mContext, userid);
                setListener();
                RongCallClient.getInstance().setVideoProfile(RongCallCommon.CallVideoProfile.VIDEO_PROFILE_720P);
                RongCallClient.getInstance().setEnableBeauty(false);
//                finish();
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                L.e("lgh_userId", "onError  errorCode  = " + errorCode);
            }
        });
    }

    private void setListener() {
        RongCallClient.setReceivedCallListener(new IRongReceivedCallListener() {
            /**
             * 来电回调
             * @param callSession 通话实体
             */
            @Override
            public void onReceivedCall(RongCallSession callSession) {
                //accept or hangup the call
                L.e("RongRTCVideoActivity", "callSession  = " + callSession.getCallId());
                L.e("RongRTCVideoActivity", "callSession  = " + callSession.getCallerUserId());
                L.e("RongRTCVideoActivity", "callSession  = " + callSession.getMediaType().name());
                L.e("RongRTCVideoActivity", "callSession  = " + callSession.getInviterUserId());
                L.e("RongRTCVideoActivity", "callSession  = " + callSession.getSelfUserId());
//               Intent intent = new Intent(mContext, SingleCallActivity.class);
//               intent.putExtra("checkPermissions",true);
//               intent.putExtra("callAction", RongCallAction.ACTION_INCOMING_CALL.getName());
//               intent.putExtra("callSession",(Parcelable) callSession);
                setCallVideo(callSession);
            }

            /**
             * targetSDKVersion>＝23时检查权限的回调。当targetSDKVersion<23的时候不需要实现。
             * 在这个回调里用户需要使用Android6.0新增的动态权限分配接口requestCallPermissions通知用户授权，
             * 然后在onRequestPermissionResult回调里根据用户授权或者不授权分别回调
             * RongCallClient.getInstance().onPermissionGranted()和
             * RongCallClient.getInstance().onPermissionDenied()来通知CallLib。
             * 其中audio call需要获取Manifest.permission.RECORD_AUDIO权限；
             * video call需要获取Manifest.permission.RECORD_AUDIO和Manifest.permission.CAMERA两项权限。
             * @param callSession 通话实体
             */
            @Override
            public void onCheckPermission(RongCallSession callSession) {

            }
        });
    }

    private void setCallVideo(RongCallSession callSession) {
        int rand = Utilt.getRandom();
        String timestamp = DynamicTimeFormat.getTimestamp();
        String signature = Utilt.shaEncrypt(Constanst.appSecret + rand + timestamp);
        OkHttpUtils.post().url("http://api-cn.ronghub.com/user/info.json")
                .addHeader("App-Key", Constanst.appkey)
                .addHeader("Nonce", rand + "")
                .addHeader("Timestamp", timestamp)
                .addHeader("Signature", signature)
                .addParams("userId", callSession.getCallerUserId())
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
                        UserInfo userInfo = new UserInfo(callSession.getCallerUserId(), userInfoDto.getUserName(), uri);
                        Intent intent = new Intent(mContext, SingleCallActivity.class);
                        intent.putExtra("checkPermissions", true);
                        intent.putExtra("callAction", RongCallAction.ACTION_INCOMING_CALL.getName());
                        intent.putExtra("callSession", (Parcelable) callSession);
                        intent.putExtra("userInfoDto", userInfo);
                        startActivity(intent);
                    }
                });
    }
}
