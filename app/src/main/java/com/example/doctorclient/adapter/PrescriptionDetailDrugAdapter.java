package com.example.doctorclient.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.PrescriptionDetailDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.PriceUtils;
import com.lgh.huanglib.util.data.ResUtil;

/**
 * description:处方详情 药品列表 适配器
 * autour: huang
 * date: 2019/5/22 11:22
 * update: 2019/5/22
 * version:
 */
public class PrescriptionDetailDrugAdapter extends BaseRecyclerAdapter<PrescriptionDetailDto.DataBean.DrugMVBean> {
    Context context;
    public PrescriptionDetailDrugAdapter(Context context) {
        super(R.layout.layout_item_prescription_detail_drug);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, PrescriptionDetailDto.DataBean.DrugMVBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_drug_name,model.getName());
        holder.text(R.id.tv_item_drug_spec, ResUtil.getFormatString(R.string.prescription_tip_18,model.getThe_spec()));
        holder.text(R.id.tv_item_drug_dosage, ResUtil.getFormatString(R.string.prescription_tip_19,model.getNum_note()));

        holder.text(R.id.tv_item_drug_num,"X"+model.getDrug_num());
        holder.text(R.id.tv_item_drug_price,"￥"+ PriceUtils.formatPrice(model.getPrice()));

        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_drug);
        String portrait = model.getThe_img();
        if (portrait.indexOf("DOC") != -1) {
            GlideUtil.setImage(context, WebUrlUtil.IMG_URL + portrait, imageView, 0);
            L.e("lgh", WebUrlUtil.IMG_URL + portrait);
        } else {
            GlideUtil.setImage(context, WebUrlUtil.IMG_URL + "DOC/my" + portrait, imageView, 0);
            L.e("lgh", WebUrlUtil.IMG_URL + "DOC/my" + portrait);
        }
    }
}
