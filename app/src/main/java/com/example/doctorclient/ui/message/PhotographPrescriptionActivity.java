package com.example.doctorclient.ui.message;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.doctorclient.BuildConfig;
import com.example.doctorclient.R;
import com.example.doctorclient.actions.PhotographPrescriptionAction;
import com.example.doctorclient.adapter.IllessImgAdapter;
import com.example.doctorclient.adapter.PhotographPrescriptionAdapter;
import com.example.doctorclient.adapter.PrescriptionImgAdapter;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.InquiryDetailDto;
import com.example.doctorclient.event.PrescriptionListDto;
import com.example.doctorclient.ui.MainActivity;
import com.example.doctorclient.ui.impl.PhotographPrescriptionView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.ui.mine.UserInfoActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.cusview.CustomLinearLayoutManager;
import com.example.doctorclient.util.dialog.PicturesDialog;
import com.example.doctorclient.util.imageloader.GlideImageLoader;
import com.example.doctorclient.util.photo.PicUtils;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description: 拍照开方
 * autour: huang
 * date: 2019/5/28 10:14
 * update: 2019/5/28
 * version:
 */
public class PhotographPrescriptionActivity extends UserBaseActivity<PhotographPrescriptionAction> implements PhotographPrescriptionView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.et_diagnostic_message)
    EditText editText;
    @BindView(R.id.tv_diagnostic_message_num)
    TextView messageNumTv;

    @BindView(R.id.tv_name)
    TextView nameTv;
    @BindView(R.id.tv_sex)
    TextView sexTv;
    @BindView(R.id.tv_age)
    TextView ageTv;
    @BindView(R.id.tv_weight)
    TextView weighrTv;
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
    @BindView(R.id.rv_prescription_img)
    RecyclerView prescriptionImgRv;

    @BindView(R.id.rv_prescription)
    RecyclerView prescriptionRv;

    IllessImgAdapter illessImgAdapter;
    PhotographPrescriptionAdapter photographPrescriptionAdapter;

    String iuid;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    public static final int REQUEST_CODE_TAKE = 102;
    public static final int REQUEST_CODE_ALBUM = 103;
    public static int REQUEST_SELECT_TYPE = -1;//选择的类型
    private ArrayList<ImageItem> selImageList = new ArrayList<>(); //当前选择的所有图片
    ArrayList<ImageItem> images = null;
    List<String> list = new ArrayList<>();

    PrescriptionImgAdapter prescriptionImgAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_photograph_prescription;
    }


    @Override
    protected PhotographPrescriptionAction initAction() {
        return new PhotographPrescriptionAction(this, this);
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
                .addTag("PhotographPrescriptionActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.message_tip_7));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        iuid = getIntent().getStringExtra("iuid");

        illessImgAdapter = new IllessImgAdapter(mContext);
        imgIllnessRv.setLayoutManager(new LinearLayoutManager(mContext));
        imgIllnessRv.setAdapter(illessImgAdapter);

        photographPrescriptionAdapter = new PhotographPrescriptionAdapter(mContext);
        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(this);
        linearLayoutManager.setScrollEnabled(false);
        linearLayoutManager.setStackFromEnd(true);
        prescriptionRv.setLayoutManager(linearLayoutManager);
        prescriptionRv.setAdapter(photographPrescriptionAdapter);

        prescriptionImgAdapter = new PrescriptionImgAdapter(this);
        prescriptionImgRv.setLayoutManager(new GridLayoutManager(mContext,4));
        prescriptionImgRv.setAdapter(prescriptionImgAdapter);

        initImagePicker();
        isLogin();
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(editText.getText().toString())) {
                    messageNumTv.setText("0/200");
                } else {
                    messageNumTv.setText(editText.getText().length() + "/200");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //canScrollVertically()方法为判断指定方向上是否可以滚动,参数为正数或负数,负数检查向上是否可以滚动,正数为检查向下是否可以滚动
                if (editText.canScrollVertically(1) || editText.canScrollVertically(-1)){
                    v.getParent().requestDisallowInterceptTouchEvent(true);//requestDisallowInterceptTouchEvent();要求父类布局不在拦截触摸事件
                    if (event.getAction() == MotionEvent.ACTION_UP){ //判断是否松开
                        v.getParent().requestDisallowInterceptTouchEvent(false); //requestDisallowInterceptTouchEvent();让父类布局继续拦截触摸事件
                    }
                }
                return false;
            }
        });
    }

    @OnClick({R.id.tv_submit,R.id.tv_prescription})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_submit:
                //TODO 提交数据
                submit();
                break;
            case R.id.tv_prescription:
                //TODO 选择图片
                showSelectDiaLog();
                break;
        }
    }

    /**
     * 提交数据
     */
    private void submit() {
        String diagonsis = "";
        if (!TextUtils.isEmpty(editText.getText().toString())){
            diagonsis = editText.getText().toString();
        }
        String theImg = "[]";
        for (int i = 0; i <list.size() ; i++) {
            if (i == 0) {
                theImg = "[";
            }else {
                theImg = theImg+",";
            }
            theImg = theImg + "\""+list.get(i)+ "\"";
            if (i == list.size() - 1) {
                theImg = theImg + "]";
            }
        }
        savePhotographPrescription(iuid,diagonsis,theImg);

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
        getAskHeadById();
        getPrescriptionList();
    }

    /**
     * 获取问诊信息
     */
    @Override
    public void getAskHeadById() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getAskHeadById(iuid);
        }
    }

    /**
     * 获取问诊信息成功
     * @param inquiryDetailDto
     */
    @Override
    public void getAskHeadByIdSuccessful(InquiryDetailDto inquiryDetailDto) {
        loadDiss();
        InquiryDetailDto.DataBean dataBean = inquiryDetailDto.getData();
        InquiryDetailDto.DataBean.PatientMVBean patientMVBean = dataBean.getPatientMV();
        editText.setText(dataBean.getDiagnosis());
        nameTv.setText(patientMVBean.getName());
        sexTv.setText(patientMVBean.getSex());
        ageTv.setText(ResUtil.getFormatString(R.string.inquity_tip_28, patientMVBean.getAge() + ""));
        if (!TextUtils.isEmpty(patientMVBean.getWeight())) {
            weighrTv.setText(patientMVBean.getWeight() + "KG");
        }
        allergyTv.setText(patientMVBean.getAllergy_note());
        familyTv.setText(patientMVBean.getMed_family());
        medicalHistoryTv.setText(patientMVBean.getMed_history());

        illnessTv.setText(dataBean.getIll_note());
        illessImgAdapter.refresh(dataBean.getIll_img());
    }

    /**
     * 处方药品列表
     */
    @Override
    public void getPrescriptionList() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getPrescriptionList(iuid);
        }
    }

    /**
     * 处方药品列表成功
     * @param prescriptionListDto
     */
    @Override
    public void getPrescriptionListSuccessful(PrescriptionListDto prescriptionListDto) {
        loadDiss();
        photographPrescriptionAdapter.refresh(prescriptionListDto.getData());
    }

    /**
     * 上传处方图片
     * @param str
     */
    @Override
    public void updataFileName(String str) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.updatafileName(str);
        }
    }

    /**
     * 上传处方图片 成功
     * @param str
     */
    @Override
    public void updataFileNameSuccessful(String str) {
        loadDiss();
        list.add(str);
        prescriptionImgAdapter.refresh(list);
    }

    /**
     * 保存拍照开方数据
     * @param iuid
     * @param diagonsis
     * @param theImg
     */
    @Override
    public void savePhotographPrescription(String iuid,String diagonsis,String theImg) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.savePhotographPrescription(iuid,diagonsis,theImg);
        }
    }

    /**
     * 保存拍照开方数据成功
     * @param generalDto
     */
    @Override
    public void savePhotographPrescriptionSuccessful(GeneralDto generalDto) {
        loadDiss();
        showNormalToast(generalDto.getMsg());
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
        loadDiss();
        jumpActivity(mContext, LoginActivity.class);
        ActivityStack.getInstance().exitIsNotHaveMain(MainActivity.class);
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
        Intent intent = new Intent(PhotographPrescriptionActivity.this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }

    private void takeUserGally() {
        //打开选择,本次允许选择的数量

        REQUEST_SELECT_TYPE = REQUEST_CODE_ALBUM;
        ImagePicker.getInstance().setSelectLimit(1);
        Intent intent1 = new Intent(PhotographPrescriptionActivity.this, ImageGridActivity.class);
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
//                                    PicUtils.showCutPhoto(data, zoomSacle, imgUri.getPath());
////                                    PicUtils.getCompressedImgPath(images.get(0).path, photoOption);
//                                    //todo  请求接口 修改头像
//                                    uploadAvatar(images.get(0).path);
                                    L.e("lgh","images.get(0).path  = "+images.get(0).path);
                                    updataFileName(images.get(0).path);
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
                            //todo  请求接口 修改头像
//                            uploadAvatar(images.get(0).path);
                            L.e("lgh","images.get(0).path  = "+images.get(0).path);
                            updataFileName(images.get(0).path);

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
