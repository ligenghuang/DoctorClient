package com.example.doctorclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.PrescriptionDrugsDto;
import com.example.doctorclient.ui.mine.inquiry.EditPrescriptionActivity2;
import com.example.doctorclient.ui.mine.inquiry.SelectPrescriptionDetailsActivity;
import com.lgh.huanglib.util.data.ResUtil;

/**
 * description:处方药 列表 适配器
 * autour: huang
 * date: 2019/5/20 10:53
 * update: 2019/5/20
 * version:
 */
public class PrescriptionDrugsAdapter extends BaseRecyclerAdapter<PrescriptionDrugsDto.DataBean> {
    Context context;
    boolean isSelect;
    public PrescriptionDrugsAdapter(Context context,boolean isSelect) {
        super(R.layout.layout_item_prescription_drug);
        this.context = context;
        this.isSelect = isSelect;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, PrescriptionDrugsDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView imageView = holder.itemView.findViewById(R.id.icon);
        imageView.setVisibility(isSelect ? View.VISIBLE:View.GONE);
        holder.text(R.id.tv_item_drug_name,model.getName());
        holder.text(R.id.tv_item_drug_num, ResUtil.getFormatString(R.string.inquity_tip_35,model.getDrugCount()+""));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, isSelect? EditPrescriptionActivity2.class:SelectPrescriptionDetailsActivity.class);
                intent.putExtra("iuid",model.getIUID());
                context.startActivity(intent);
            }
        });
    }
}
