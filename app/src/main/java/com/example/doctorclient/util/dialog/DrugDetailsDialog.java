package com.example.doctorclient.util.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.DrugDetailsDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.PriceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.http.POST;

/**
 * description:药品详情
 * autour: huang
 * date: 2019/5/21 16:33
 * update: 2019/5/21
 * version:
 */
public class DrugDetailsDialog extends Dialog {

    Context context;
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @BindView(R.id.iv_drug)
    ImageView drugIv;
    @BindView(R.id.tv_drug_name)
    TextView drugNameTv;
    @BindView(R.id.tv_drug_company)
    TextView drugCompanyTv;
    @BindView(R.id.tv_drug_spec)
    TextView drugSpecTv;
    @BindView(R.id.tv_drug_price)
    TextView drugPriceTv;
    @BindView(R.id.tv_item_drug_num)
    TextView drugNumTv;
    @BindView(R.id.tv_drug_composition)
    TextView drugComposition;
    @BindView(R.id.tv_drug_shape)
    TextView drugShapeTv;

    DrugDetailsDto.DataBean dataBean;
    int num = 0;

    public DrugDetailsDialog(@NonNull Context context, int themeResId,DrugDetailsDto.DataBean dataBean,int num) {
        super(context, themeResId);
        this.context = context;
        this.dataBean = dataBean;
        this.num = num;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_drug_details);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        Window win = this.getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.x = 0;
        lp.y = 0;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        win.setGravity(Gravity.BOTTOM);
        setData();
    }


    public void setData(){
        drugNameTv.setText(dataBean.getName());
        drugCompanyTv.setText(dataBean.getThe_company());
        drugSpecTv.setText(dataBean.getThe_spec());
        drugPriceTv.setText(PriceUtils.formatPrice(dataBean.getPrice()));
        drugComposition.setText(dataBean.getElement());
        drugShapeTv.setText(dataBean.getAppear());
        String portrait = dataBean.getThe_img();
        drugNumTv.setText(num+"");
        if (portrait.indexOf("DOC") != -1) {
            GlideUtil.setImage(context, WebUrlUtil.IMG_URL + portrait, drugIv, 0);
            L.e("lgh", WebUrlUtil.IMG_URL + portrait);
        } else {
            GlideUtil.setImage(context, WebUrlUtil.IMG_URL + "DOC/my" + portrait, drugIv, 0);
            L.e("lgh", WebUrlUtil.IMG_URL + "DOC/my" + portrait);
        }
    }

    @OnClick({R.id.tv_item_drug_add,R.id.tv_item_drug_subtract,R.id.tv_submit})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_item_drug_add:
                num++;
                drugNumTv.setText(num+"");
                break;
            case R.id.tv_item_drug_subtract:
                if (num > 0){
                    num--;
                    drugNumTv.setText(num+"");
                }
                break;
            case R.id.tv_submit:
                onClickListener.submit(dataBean.getIUID(),num);
                dismiss();
                break;
        }
    }

    public interface OnClickListener{
        void submit(String iuid,int num);
    }
}
