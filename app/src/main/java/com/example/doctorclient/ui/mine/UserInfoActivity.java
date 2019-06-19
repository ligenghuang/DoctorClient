package com.example.doctorclient.ui.mine;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doctorclient.BuildConfig;
import com.example.doctorclient.R;
import com.example.doctorclient.actions.BaseAction;
import com.example.doctorclient.actions.LoginAction;
import com.example.doctorclient.actions.UserInfoAction;
import com.example.doctorclient.event.DepartidDto;
import com.example.doctorclient.event.DoctorInfoDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.HospitalListDto;
import com.example.doctorclient.event.UserInfoDto;
import com.example.doctorclient.event.post.DoctorsInfoPost;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.impl.UserInfoView;
import com.example.doctorclient.ui.login.LoginActivity;
import com.example.doctorclient.util.base.UserBaseActivity;
import com.example.doctorclient.util.data.DynamicTimeFormat;
import com.example.doctorclient.util.dialog.ModifyDialog;
import com.example.doctorclient.util.dialog.PicturesDialog;
import com.example.doctorclient.util.dialog.PrescriptionDialog;
import com.example.doctorclient.util.imageloader.GlideImageLoader;
import com.example.doctorclient.util.photo.PicUtils;
import com.example.doctorclient.util.picker.TimePickerBuilder;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
* 个人信息
* @author lgh
* created at 2019/5/16 0016 10:22
*/
public class UserInfoActivity extends UserBaseActivity<UserInfoAction> implements UserInfoView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.iv_user_portrait)
    ImageView userPortaitIv;
    @BindView(R.id.tv_user_name)
    TextView userNameTv;
    @BindView(R.id.tv_user_sex)
    TextView userSexTv;
    @BindView(R.id.tv_user_working_time)
    TextView userWorkingTimeTv;
    @BindView(R.id.tv_user_jobs)
    TextView userJobsTv;
    @BindView(R.id.tv_user_specialty)
    TextView userSpecialtyTv;
    @BindView(R.id.tv_user_department)
    TextView userDepartmentTv;
    @BindView(R.id.tv_user_hospital)
    TextView userHospitalTv;
    @BindView(R.id.tv_user_prescription)
    TextView userPrescriptionTv;
    @BindView(R.id.tv_user_mobile)
    TextView userMobileTv;
    @BindView(R.id.tv_user_certificate)
    TextView userCertificateTv;
    @BindView(R.id.ll_user_certificate)
    LinearLayout userCertificateLl;
    @BindView(R.id.iv_user_certificate)
    ImageView userCertificateIv;

    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    public static final int REQUEST_CODE_TAKE = 102;
    public static final int REQUEST_CODE_ALBUM = 103;
    public static int REQUEST_SELECT_TYPE = -1;//选择的类型
    private ArrayList<ImageItem> selImageList = new ArrayList<>(); //当前选择的所有图片
    ArrayList<ImageItem> images = null;
    /**
     * 是否修改头像
     */
    private boolean isPortrait = true;
    /**
     * 头像
     */
    private String portrait="";
    /**
     * 姓名
     */
    private String doctorName="";
    /**
     * 性别
     */
    private String sex="";
    /**
     * 从业时间
     */
    private String workingTime="";
    /**
     * 职位
     */
    private String jobs="";
    /**
     * 特长
     */
    private String specialty="";
    /**
     * 科室
     */
    private String department="";
    /**
     * 在职医院
     */
    private String hospital="";
    /**
     * 开处方
     */
    private int prescription=0;
    /**
     * 手机号码
     */
    private String mobile="";
    /**
     * 执业证书
     */
    private String certificate="";

    List<String> sexLists = new ArrayList<>();

    @Override
    public int intiLayout() {
        return R.layout.activity_user_info;
    }
    @Override
    protected UserInfoAction initAction() {
        return new UserInfoAction(this,this);
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
                .addTag("FindPwdActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.user_info_tip_title));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        sexLists = new ArrayList<>();
        sexLists.add("男");
        sexLists.add("女");
        initImagePicker();
        getDoctorsInfo();
    }


    @OnClick({R.id.rl_user_portrait,R.id.rl_user_name,R.id.rl_user_sex,
            R.id.rl_user_working_time,R.id.rl_user_jobs,R.id.rl_user_specialty,
    R.id.rl_user_department,R.id.rl_user_hospital,R.id.rl_user_prescription,R.id.rl_user_mobile,R.id.rl_user_certificate,R.id.tv_submit})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.rl_user_portrait:
                //todo 头像
                isPortrait = true;
                showSelectDiaLog();
                break;
            case R.id.rl_user_name:
                //todo 姓名
                ModifyDialog modifyDialog = new ModifyDialog(mContext,R.style.MY_AlertDialog,ResUtil.getString(R.string.user_info_tip_17));
                modifyDialog.setOnClickListener(new ModifyDialog.OnClickListener() {
                    @Override
                    public void confirm(String txet) {
                        if (TextUtils.isEmpty(txet)){
                            showNormalToast(ResUtil.getString(R.string.user_info_tip_17));
                        }else {
                            doctorName = txet;
                            userNameTv.setText(doctorName);
                            hideInput();
                            modifyDialog.dismiss();
                        }
                    }
                });
                modifyDialog.show();
                break;
            case R.id.rl_user_sex:
                //todo 性别
                new TimePickerBuilder(mContext).setSexPicker(sexLists,"选择性别",new TimePickerBuilder.SexPickerCustomListener() {
                    @Override
                    public void sexSelect(String sexStr) {
                        sex = sexStr;
                        userSexTv.setText(sex);
                    }
                }).show();
                break;
            case R.id.rl_user_working_time:
                //todo 从业时间
                new TimePickerBuilder(mContext).TimePickerBuilder(new TimePickerBuilder.TimePickerCustomListener() {
                    @Override
                    public void customLayout(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                        workingTime = format.format(date);
                        userWorkingTimeTv.setText(workingTime);
                    }
                }).show();
                break;
            case R.id.rl_user_jobs:
                //todo 职位
                ModifyDialog modifyJobDialog = new ModifyDialog(mContext,R.style.MY_AlertDialog,ResUtil.getString(R.string.user_info_tip_18));
                modifyJobDialog.setOnClickListener(new ModifyDialog.OnClickListener() {
                    @Override
                    public void confirm(String txet) {
                        if (TextUtils.isEmpty(txet)){
                            showNormalToast(ResUtil.getString(R.string.user_info_tip_18));
                        }else {
                            jobs = txet;
                            userJobsTv.setText(jobs);
                            hideInput();
                            modifyJobDialog.dismiss();
                        }
                    }
                });
                modifyJobDialog.show();
                break;
            case R.id.rl_user_specialty:
                //todo 特长
                Intent intent = new Intent(mContext,SpecialtyActivity.class);
                intent.putExtra("specialty",specialty);
                startActivityForResult(intent,200);
                break;
            case R.id.rl_user_department:
                //todo 科室
                getFindDepartid();
                break;
            case R.id.rl_user_hospital:
                //todo 在职医院

                getHospitalName();
                break;
            case R.id.rl_user_prescription:
                //todo 开处方
                PrescriptionDialog prescriptionDialog = new PrescriptionDialog(mContext,R.style.MY_AlertDialog);
                prescriptionDialog.setOnClickListener(new PrescriptionDialog.OnClickListener() {
                    @Override
                    public void isPrescription(int type,String text) {
                        prescription = type;
                        userPrescriptionTv.setText(text);
                        prescriptionDialog.dismiss();
                    }
                });
                prescriptionDialog.show();
                break;
            case R.id.rl_user_mobile:
                //todo 手机号码
//                Intent mobileIntent = new Intent(mContext,BindMobileActivity.class);
//                mobileIntent.putExtra("mobile",mobile);
//                startActivity(mobileIntent);
                break;
            case R.id.rl_user_certificate:
                //todo 执业证书
                isPortrait = false;
                showSelectDiaLog();
                break;
            case R.id.tv_submit:
                sevaDoctorsInfo();
                break;
        }
    }


    /**
     * 获取个人信息
     */
    @Override
    public void getDoctorsInfo() {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.getDoctorsInfo();
        }
    }

    /**
     * 获取个人信息成功
     */
    @Override
    public void getDoctorsInfoSuccessful(DoctorInfoDto userInfoDto) {
        loadDiss();
        DoctorInfoDto.DataBean dataBean = userInfoDto.getData();
        userNameTv.setText(dataBean.getName());
        doctorName = dataBean.getName();
        workingTime = DynamicTimeFormat.LongToString3(dataBean.getPracticing_time_stamp());
        userWorkingTimeTv.setText(workingTime);
        if (dataBean.getThe_img().indexOf("DOC") != -1){
            GlideUtil.setImageCircle(mContext, WebUrlUtil.IMG_URL+dataBean.getThe_img(),userPortaitIv,R.drawable.icon_placeholder);
            L.e("lgh",WebUrlUtil.IMG_URL+dataBean.getThe_img());
        }else {
            GlideUtil.setImageCircle(mContext,WebUrlUtil.IMG_URL+"DOC/my"+dataBean.getThe_img(),userPortaitIv,R.drawable.icon_placeholder);
            L.e("lgh",WebUrlUtil.IMG_URL+"DOC/my"+dataBean.getThe_img());
        }
        /**
         * 性别
         */
        userSexTv.setText(dataBean.getSex());
        sex = dataBean.getSex();
        /**
         * 职称
         */
        userJobsTv.setText(dataBean.getThe_level());
        jobs = dataBean.getThe_level();
        /**
         * 特长
         */
        userSpecialtyTv.setText(ResUtil.getString(!dataBean.getThe_spec().isEmpty()?R.string.user_info_tip_24:R.string.user_info_tip_25));
        specialty = dataBean.getThe_spec();
        /**
         * 科室
         */
        userDepartmentTv.setText(dataBean.getDepartName());
        department = dataBean.getDepartName();
        /**
         * 在职医院
         */
        userHospitalTv.setText(dataBean.getHospital());
        hospital = dataBean.getHospital();
        /**
         * 是否可开处方药
         */
        prescription =dataBean.getIsPrescribe();
        userPrescriptionTv.setText(ResUtil.getString(dataBean.getIsPrescribe() == 0?R.string.user_info_tip_13:R.string.user_info_tip_14));
        /**
         * 手机号码
         */
        userMobileTv.setText(dataBean.getPhone());
        mobile = dataBean.getPhone();
        /**
         * 是否上传执业证书
         */
        if (dataBean.getPracticing_certificate().isEmpty()){
            userCertificateLl.setVisibility(View.GONE);
            userCertificateTv.setText(ResUtil.getString(R.string.user_info_tip_15));
        }else {
            userCertificateLl.setVisibility(View.GONE);
            userCertificateTv.setText(ResUtil.getString(R.string.user_info_tip_16));
//            GlideUtil.setImage(mContext,WebUrlUtil.IMG_URL+dataBean.getPracticing_certificate(),userCertificateIv,0);
        }


    }

    /**
     * 获取科室
     */
    @Override
    public void getFindDepartid() {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.getFindDepartid();
        }
    }

    /**
     * 获取科室成功
     * @param departidDto
     */
    @Override
    public void getFindDepartidSuccessful(DepartidDto departidDto) {
        loadDiss();
        List<String> list = new ArrayList<>();
        for (int i = 0; i <departidDto.getData().size() ; i++) {
            list.add(departidDto.getData().get(i).getName());
        }
        new TimePickerBuilder(mContext).setSexPicker(list, "选择科室",new TimePickerBuilder.SexPickerCustomListener() {
            @Override
            public void sexSelect(String sexStr) {
                department = sexStr;
                userDepartmentTv.setText(department);
            }
        }).show();
    }

    /**
     * 保存医生个人信息
     */
    @Override
    public void sevaDoctorsInfo() {
        DoctorsInfoPost post = new DoctorsInfoPost();
        post.setFile(portrait);
        post.setName(doctorName);
        post.setPracticing_img(certificate);
        post.setDepartName(department);
        post.setHospital(hospital);
        post.setIsPrescribe(prescription);
        post.setPhone(mobile);
        post.setPracticing_time(workingTime);
        post.setSex(sex);
        post.setThe_level(jobs);
        post.setThe_spec(specialty);
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.seveInfo(post);
        }
    }

    /**
     * 保存医生个人信息成功
     * @param generalDto
     */
    @Override
    public void sevaDoctorsInfoSuccessful(GeneralDto generalDto) {
        loadDiss();

        if (generalDto.getCode() == 1){
            showNormalToast(generalDto.getMsg());
            finish();
        }else if (generalDto.getCode() == 0){
            showNormalToast("您未修改信息，提交失败");
        }else {
            showNormalToast(generalDto.getMsg());
        }
    }

    @Override
    public void getHospitalName() {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.getHospitalName();
        }
    }

    @Override
    public void getHospitalNameSuccessful(HospitalListDto hospitalListDto) {
        loadDiss();
        new TimePickerBuilder(mContext).setSexPicker(hospitalListDto.getData(), ResUtil.getString(R.string.user_info_tip_28),new TimePickerBuilder.SexPickerCustomListener() {
            @Override
            public void sexSelect(String sexStr) {
                hospital = sexStr;
                userHospitalTv.setText(hospital);
            }

        }).show();
    }

    /**
     * 失败
     * @param message
     * @param code
     */
    @Override
    public void onError(String message, int code) {
        loadDiss();
      if (code == -2){
          showNormalToast(message);
          jumpActivity(mContext, LoginActivity.class);
      }else {
          showNormalToast(message);
      }
    }

    @Override
    public void onLigonError() {
        jumpActivity(mContext, LoginActivity.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (baseAction != null) {
            baseAction.toRegister();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
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
        Intent intent = new Intent(UserInfoActivity.this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }

    private void takeUserGally() {
        //打开选择,本次允许选择的数量

        REQUEST_SELECT_TYPE = REQUEST_CODE_ALBUM;
        ImagePicker.getInstance().setSelectLimit(1);
        Intent intent1 = new Intent(UserInfoActivity.this, ImageGridActivity.class);
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
                                    if (isPortrait){
                                        portrait = images.get(0).path;
                                        GlideUtil.setImageCircle(mContext,images.get(0).path,userPortaitIv,R.drawable.icon_placeholder);
                                    }else {
                                        certificate = images.get(0).path;
                                        GlideUtil.setImage(mContext,images.get(0).path,userCertificateIv,R.drawable.icon_placeholder);
                                        userCertificateLl.setVisibility(View.VISIBLE);
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
                            //todo  请求接口 修改头像
//                            uploadAvatar(images.get(0).path);
                            L.e("lgh","images.get(0).path  = "+images.get(0).path);
                            if (isPortrait){
                                portrait = images.get(0).path;
                                GlideUtil.setImageCircle(mContext,images.get(0).path,userPortaitIv,R.drawable.icon_placeholder);
                            }else {
                                certificate = images.get(0).path;
                                GlideUtil.setImage(mContext,images.get(0).path,userCertificateIv,R.drawable.icon_placeholder);
                                userCertificateLl.setVisibility(View.VISIBLE);
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
        }else if (resultCode == 200){
            if (data != null){
                specialty = data.getStringExtra("specialty");
                userSpecialtyTv.setText(specialty);
            }
        }

    }

    /**********************************修改头像 end*********************************************/



}
