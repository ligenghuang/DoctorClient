package com.example.doctorclient.adapter;

import android.widget.ImageView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.IncomeDetailsListDto;
import com.example.doctorclient.util.data.DynamicTimeFormat;
import com.lgh.huanglib.util.data.PriceUtils;

public class IncomeDetailsAdapter extends BaseRecyclerAdapter<IncomeDetailsListDto.DataBean> {
    public IncomeDetailsAdapter() {
        super(R.layout.layout_item_income_details);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, IncomeDetailsListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_note,model.getNOTE());
        holder.text(R.id.tv_item_time, DynamicTimeFormat.LongToString4(model.getGENERATIONTIME_stamp()));
        holder.text(R.id.tv_item_price,"+"+ PriceUtils.formatPrice(model.getAVAILABLE())+"元");
        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_type);
        int resId = R.drawable.icon_income_1;
        switch (model.getTRANSATIONTYPE()){
            case "问诊":
                resId = R.drawable.icon_income_1;
                break;
            case "处方":
                resId = R.drawable.icon_income_2;
                break;
        }
        imageView.setImageResource(resId);
    }
}
