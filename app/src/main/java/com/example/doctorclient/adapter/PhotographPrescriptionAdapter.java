package com.example.doctorclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.PrescriptionListDto;
import com.example.doctorclient.ui.mine.inquiry.SelectPrescriptionDetailsActivity2;
import com.lgh.huanglib.util.data.ResUtil;
/**
 * description:拍照开方  处方药列表 适配器
 * autour: huang
 * date: 2019/5/28 11:31
 * update: 2019/5/28
 * version:
 */
public class PhotographPrescriptionAdapter extends BaseRecyclerAdapter<PrescriptionListDto.DataBean> {
    Context context;
    public PhotographPrescriptionAdapter(Context context) {
        super(R.layout.layout_item_photo_prescription);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, PrescriptionListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        int num = position +1;
        holder.text(R.id.tv_item_title, ResUtil.getFormatString(R.string.photograph_prescription_tip_4,num+""));
        holder.text(R.id.tv_note,model.getThe_memo());
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.rv_drug);
        PrescriptionDrugChildAdapter prescriptionDrugChildAdapter = new PrescriptionDrugChildAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(prescriptionDrugChildAdapter);
        prescriptionDrugChildAdapter.refresh(model.getAskDrugMV());
        TextView editTv = holder.itemView.findViewById(R.id.tv_edit);
        TextView payTv = holder.itemView.findViewById(R.id.tv_pay);
        editTv.setVisibility(model.getPay_flag() == 0 ? View.VISIBLE:View.GONE);
        payTv.setVisibility(model.getPay_flag() != 0 ? View.VISIBLE:View.GONE);
        editTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SelectPrescriptionDetailsActivity2.class);
                intent.putExtra("iuid",model.getIUID());
                context.startActivity(intent);
            }
        });
    }
}
