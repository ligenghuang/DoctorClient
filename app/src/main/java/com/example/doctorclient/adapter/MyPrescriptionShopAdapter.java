package com.example.doctorclient.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.MyPrescriptionDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.PriceUtils;

/**
* 我的处方 商品列表
* @author lgh
* created at 2019/5/18 0018 11:23
*/
public class MyPrescriptionShopAdapter extends BaseRecyclerAdapter<MyPrescriptionDto.DataBean.DrugMVBean> {
    Context context;
    public MyPrescriptionShopAdapter(Context context) {
        super(R.layout.layout_item_prescription_shop);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, MyPrescriptionDto.DataBean.DrugMVBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_name,model.getName());
        holder.text(R.id.tv_item_spec,model.getThe_spec());
        holder.text(R.id.tv_item_price, "￥"+PriceUtils.formatPrice(model.getPrice()));
        holder.text(R.id.tv_item_num,"x"+model.getDrug_num());
        ImageView userPortaitIv = holder.itemView.findViewById(R.id.iv_item_shop);
        String portrait = model.getThe_img();
        if (portrait.indexOf("DOC") != -1){
            GlideUtil.setImage(context, WebUrlUtil.IMG_URL+portrait,userPortaitIv,0);
            L.e("lgh",WebUrlUtil.IMG_URL+portrait);
        }else {
            GlideUtil.setImage(context,WebUrlUtil.IMG_URL+"DOC/my"+portrait,userPortaitIv,0);
            L.e("lgh",WebUrlUtil.IMG_URL+"DOC/my"+portrait);
        }
    }
}
