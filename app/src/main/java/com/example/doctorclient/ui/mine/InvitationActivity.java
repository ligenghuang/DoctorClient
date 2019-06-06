package com.example.doctorclient.ui.mine;

import android.content.ClipboardManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.actions.InvitationAction;
import com.example.doctorclient.event.InviteCodeDto;
import com.example.doctorclient.ui.impl.InvitationView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.IsFastClick;
import com.lgh.huanglib.util.data.ResUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:邀请用户
 * autour: huang
 * date: 2019/5/30 19:03
 * update: 2019/5/30
 * version:
 */
public class InvitationActivity extends UserBaseActivity<InvitationAction> implements InvitationView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.iv_qrcode)
    ImageView qrCodeIv;
    @BindView(R.id.tv_invite_code)
    TextView inviteCodeTv;
    @BindView(R.id.tv_invite_url)
    TextView inviteUrlTv;
    Bitmap bitmap;
    String url;

    @Override
    protected InvitationAction initAction() {
        return new InvitationAction(this, this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_invitation;
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
                .addTag("InvitationActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.mine_tip_1));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        isLogin();
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        qrCodeIv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
               if (!TextUtils.isEmpty(url)){
                   saveBitmap();
               }else {
                   showNormalToast(ResUtil.getString(R.string.edit_prescription_tip_17));
               }
                return false;
            }
        });
    }

    @OnClick({R.id.tv_copy_invite_code, R.id.tv_copy_invite_url})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_copy_invite_code:
                copy(inviteCodeTv);
                break;
            case R.id.tv_copy_invite_url:
                copy(inviteUrlTv);
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
        getInviteCode();
    }

    @Override
    public void isLoginError() {
        loadDiss();
        jumpActivity(mContext, LoginActivity.class);
    }

    @Override
    public void getInviteCode() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getInviteCode();
        }
    }

    @Override
    public void getInviteCodeSuccessful(InviteCodeDto inviteCodeDto) {
        loadDiss();
        InviteCodeDto.DataBean dataBean = inviteCodeDto.getData();
        inviteCodeTv.setText(dataBean.getInvitationcode());
        url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + dataBean.getUrl();
        inviteUrlTv.setText(url);
        bitmap = returnBitMap(url);
        GlideUtil.setImage(mContext,url,qrCodeIv,0);

    }

    @Override
    public void onError(String message, int code) {
        loadDiss();
//        showNormalToast(message);
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
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        IsFastClick.lastClickTime = 0;
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }

    /**
     * 复制
     *
     * @param textView
     */
    private void copy(TextView textView) {
        ClipboardManager cmb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (!TextUtils.isEmpty(textView.getText().toString())) {
            cmb.setText(textView.getText().toString());
        }
    }

    /**
     * 保存方法
     */
    public void saveBitmap() {
        L.e("lgh_bitmap", "保存图片");
        String fileName;
        String bitName = "yizhitong.png";
        if(Build.BRAND .equals("Xiaomi") ){ // 小米手机
            fileName = Environment.getExternalStorageDirectory().getPath()+"/DCIM/Camera/"+bitName ;
        }else{  // Meizu 、Oppo
            fileName = Environment.getExternalStorageDirectory().getPath()+"/DCIM/"+bitName ;
        }
        File f = new File(fileName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            L.i("lgh_bitmap", "已经保存"+f.getCanonicalPath());
            showNormalToast("图片已保存至"+f.getCanonicalPath());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            L.e("lgh_bitmap", e.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            L.e("lgh_bitmap", e.toString());
        }

    }

    /**
     * 将URL转化成bitmap
     * @param url
     * @return
     */
    public Bitmap returnBitMap(final String url){

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection)imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    //这是一个一步请求，不能直接返回获取，要不然永远为null
                    //在这里得到BitMap之后记得使用Hanlder或者EventBus传回主线程，不过现在加载图片都是用框架了，很少有转化为Bitmap的需求
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return bitmap;
    }

}
