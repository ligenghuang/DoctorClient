package com.example.doctorclient.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.DrugClassDto;

/**
 * description:药品种类 列表 适配器
 * autour: huang
 * date: 2019/5/20 13:34
 * update: 2019/5/20
 * version:
 */
public class DrugClassAdapter extends BaseRecyclerAdapter<DrugClassDto> {
    OnClcikListener onClcikListener;


    public void setOnClcikListener(OnClcikListener onClcikListener) {
        this.onClcikListener = onClcikListener;
    }

    public DrugClassAdapter() {
        super(R.layout.layout_item_depart);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, DrugClassDto model, int position) {
        holder.setIsRecyclable(false);
        TextView textView = holder.itemView.findViewById(R.id.tv_item_depart_name);
        textView.setText(model.getName());
        textView.setSelected(model.isClick());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClcikListener.OnClick(model.getName(),position);
            }
        });
    }

    public interface OnClcikListener{
        void OnClick(String drugClass,int position);
    }
}
