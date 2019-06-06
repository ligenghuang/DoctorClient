package com.example.doctorclient.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.PrescriptionListDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;

/**
 * description: 拍照开方  处方药列表 药品列表 适配器
 * autour: huang
 * date: 2019/5/28 13:58
 * update: 2019/5/28
 * version:
 */
public class PrescriptionDrugChildAdapter extends BaseRecyclerAdapter<PrescriptionListDto.DataBean.AskDrugMVBean>{
    Context context;
    public PrescriptionDrugChildAdapter(Context context) {
        super(R.layout.layout_item_message_prescription_drug);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, PrescriptionListDto.DataBean.AskDrugMVBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_drug_name,model.getDrug_name());
        holder.text(R.id.tv_item_drug_company,model.getThe_company());
        holder.text(R.id.tv_item_drug_spec,model.getThe_spec());
        holder.text(R.id.tv_item_num,"X"+model.getDrug_num());
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
