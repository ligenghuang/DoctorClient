package com.example.doctorclient.ui.mine.inquiry;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.doctorclient.BuildConfig;
import com.example.doctorclient.R;
import com.example.doctorclient.actions.MineAction;
import com.example.doctorclient.actions.SelectPrescriptionDetailsAction;
import com.example.doctorclient.adapter.DrugListAdapter;
import com.example.doctorclient.adapter.PrescriptionDrugListAdapter;
import com.example.doctorclient.event.DrugListDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.PrescriptionDrugInfoDto;
import com.example.doctorclient.event.PrescriptionDrugListDto;
import com.example.doctorclient.event.post.AddPrescribePost;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.MainActivity;
import com.example.doctorclient.ui.impl.SelectPrescriptionDetailsView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.ui.mine.UserInfoActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.config.MyApp;
import com.example.doctorclient.util.cusview.FlowLayout;
import com.example.doctorclient.util.data.DisplayUtil;
import com.example.doctorclient.util.data.MySp;
import com.example.doctorclient.util.dialog.PicturesDialog;
import com.example.doctorclient.util.imageloader.GlideImageLoader;
import com.example.doctorclient.util.photo.PicUtils;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.config.MyApplication;
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
 * description:新建处方
 * autour: huang
 * date: 2019/5/20 11:12
 * update: 2019/5/20
 * version:
 */
public class SelectPrescriptionDetailsActivity extends UserBaseActivity<SelectPrescriptionDetailsAction> implements SelectPrescriptionDetailsView {

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

    @BindView(R.id.rv_drug)
    RecyclerView drugRv;
    @BindView(R.id.et_note)
    EditText noteEt;
    @BindView(R.id.pic_flow)
    FlowLayout picFlow;

    PrescriptionDrugListAdapter drugListAdapter;
    String iuid;


    private View view;
    List<String> photoDtos = new ArrayList<>();

    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    public static final int REQUEST_CODE_TAKE = 102;
    public static final int REQUEST_CODE_ALBUM = 103;
    public static int REQUEST_SELECT_TYPE = -1;//选择的类型
    private ArrayList<ImageItem> selImageList = new ArrayList<>(); //当前选择的所有图片
    ArrayList<ImageItem> images = null;
    @Override
    protected SelectPrescriptionDetailsAction initAction() {
        return new SelectPrescriptionDetailsAction(this,this);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_new_prescription;
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
                .addTag("SelectPrescriptionDetailsActivity")  //给上面参数打标记，以后可以通过标记恢复
                .statusBarDarkFont(true, 0.2f)
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.edit_prescription_tip_14));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        iuid = getIntent().getStringExtra("iuid");


        drugListAdapter = new PrescriptionDrugListAdapter(this);
        drugRv.setLayoutManager(new LinearLayoutManager(this));
        drugRv.setAdapter(drugListAdapter);

        initImagePicker();
        loadPicView();
        getDrugSaveDetailByIuid();
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
        setEditText(editText);
        setEditText(noteEt);
    }

    @OnClick({R.id.tv_submit,R.id.tv_add_drug})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_submit:
                //TODO  提交数据
                submit();
                break;
            case R.id.tv_add_drug:
                //TODO  添加药品
                Intent intent = new Intent(mContext,SelectDrugsActivity.class);
                intent.putExtra("isSelect",true);
                startActivityForResult(intent,200);
                break;
        }
    }

    /**
     * 提交
     */
    private void submit() {
        AddPrescribePost addPrescribePost = new AddPrescribePost();
        addPrescribePost.setAskIuid(MySp.getAskId(mContext));
        addPrescribePost.setAskdrugheadid(iuid);
        addPrescribePost.setDiagnosis(editText.getText().toString());
        addPrescribePost.setThe_memo(noteEt.getText().toString());
        addPrescribePost.setTheImg(photoDtos);
        List<AddPrescribePost.DrugBean> beans = new ArrayList<>();
        for (int i = 0; i <drugListAdapter.mList.size() ; i++) {
            AddPrescribePost.DrugBean drugBean = new AddPrescribePost.DrugBean();
            PrescriptionDrugListDto.DataBean dataBean = drugListAdapter.getAllData().get(i);
            if (dataBean.getDrug_num() == 0){
                showNormalToast(ResUtil.getString(R.string.edit_prescription_tip_15));
                return;
            }
            drugBean.setDrug_num(dataBean.getDrug_num()+"");
            drugBean.setDrugid(dataBean.getDrugid());
            drugBean.setIUID(dataBean.getIUID());
            drugBean.setUse_note(dataBean.getUse_note());
            beans.add(drugBean);
        }
        addPrescribePost.setMycars(beans);
        AddPrescribe(addPrescribePost);

    }

    /**
     * 获取药品备注
     */
    @Override
    public void getDrugSaveHeadByIuid() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.getDrugSaveHeadByIuid(iuid);
        }
    }

    /**
     * 获取药品备注 成功
     * @param prescriptionDrugInfoDto
     */
    @Override
    public void getDrugSaveHeadByIuidSuccessful(PrescriptionDrugInfoDto prescriptionDrugInfoDto) {
        loadDiss();
        noteEt.setText(prescriptionDrugInfoDto.getData().getThe_memo());
    }

    /**
     * 获取药品清单
     */
    @Override
    public void getDrugSaveDetailByIuid() {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.getDrugSaveDetailByIuid(iuid);
        }
    }

    /**
     * 获取药品清单  成功
     * @param prescriptionDrugListDto
     */
    @Override
    public void getDrugSaveDetailByIuidSuccessful(PrescriptionDrugListDto prescriptionDrugListDto) {
        loadDiss();
        drugListAdapter.refresh(prescriptionDrugListDto.getData());

    }

    /**
     * 上传图片
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
     * 上传图片成功
     * @param str
     */
    @Override
    public void updataFileNameSuccessful(String str) {
        loadDiss();
        photoDtos.add(str);
        loadPicView();
    }

    /**
     * 提交添加处方
     * @param prescribePost
     */
    @Override
    public void AddPrescribe(AddPrescribePost prescribePost) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDiss();
            baseAction.AddPrescribe(prescribePost);
        }
    }

    /**
     * 提交添加处方成功
     * @param generalDto
     */
    @Override
    public void AddPrescribeSuccessful(GeneralDto generalDto) {
        loadDiss();
        showNormalToast(generalDto.getMsg());
        ActivityStack.getInstance().exitClass(SelectPrescriptionActivity.class);
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
        ActivityStack.getInstance().exitIsNotHaveMain(MainActivity.class,LoginActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (baseAction != null) {
            baseAction.toRegister();
        }
        getDrugSaveHeadByIuid();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }

    /**
     * 加载图片
     */
    private void loadPicView() {
        if (picFlow.getUserView().size() > 0) {
            picFlow.removeAllViews();
        }
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(DisplayUtil.dip2px(mContext, 10), 0, DisplayUtil.dip2px(mContext, 10), 0);

        for (int i = 0; i < photoDtos.size(); i++) {
            final int p_i = i;
            view = (View) LinearLayout.inflate(MyApp.getInstance(), R.layout.layout_feedback_iv_opinion, null);
            ImageView image = (ImageView) view.findViewById(R.id.education_iv);
            if (photoDtos.get(i) != null) {
                Glide.with(mContext).load(WebUrlUtil.IMG_URL + photoDtos.get(i)).listener(new RequestListener() {

                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {

                        return false;
                    }

                }).into(image);
            } else {
                //图片路径为空
                image.setImageResource(R.color.white);
            }

            picFlow.addView(view, lp);
        }
        int size = picFlow.getUserView().size();
        if (size == 8) {

        } else {
            view = (View) LinearLayout.inflate(MyApplication.getInstance(), R.layout.layout_feedback_iv_opinion, null);
            ImageView image = (ImageView) view.findViewById(R.id.education_iv);
            image.setImageResource(R.drawable.add_photo);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    L.e("xx", " 点击  1 ");
                    //添加图片
                    showSelectDiaLog();
                }
            });
            picFlow.addView(view, lp);
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
        Intent intent = new Intent(SelectPrescriptionDetailsActivity.this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }

    private void takeUserGally() {
        //打开选择,本次允许选择的数量

        REQUEST_SELECT_TYPE = REQUEST_CODE_ALBUM;
        ImagePicker.getInstance().setSelectLimit(1);
        Intent intent1 = new Intent(SelectPrescriptionDetailsActivity.this, ImageGridActivity.class);
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
//                                    //todo  请求接口 上传图片
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
                            //todo  请求接口 上传图片
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
        }else if (resultCode == 200){
            List<PrescriptionDrugListDto.DataBean> list = new ArrayList<>();
            List<DrugListDto.DataBean> dataBeans = (List<DrugListDto.DataBean>) data.getSerializableExtra("list");
            L.e("lgh_list","dataBeans  = "+dataBeans.toString());
            for (int i = 0; i <dataBeans.size() ; i++) {
                PrescriptionDrugListDto.DataBean dataBean = new PrescriptionDrugListDto.DataBean();
                DrugListDto.DataBean drugDto = dataBeans.get(i);
                dataBean.setUse_note(drugDto.getNum_note());
                dataBean.setDrug_num(drugDto.getDrug_num());
                dataBean.setDrug_name(drugDto.getName());
                dataBean.setDrugid(drugDto.getIUID());
                PrescriptionDrugListDto.DataBean.DrugMVBean drugMVBean = new PrescriptionDrugListDto.DataBean.DrugMVBean();
                drugMVBean.setThe_img(drugDto.getThe_img());
                drugMVBean.setThe_spec(drugDto.getThe_spec());
                dataBean.setDrugMV(drugMVBean);
                list.add(dataBean);
                L.e("lgh_list","dataBean  = "+dataBean.toString());
            }
            drugListAdapter.loadMore(list);
            L.e("lgh_list","list  = "+list.toString());
        }

    }

    /**********************************选择图片 end*********************************************/

}
