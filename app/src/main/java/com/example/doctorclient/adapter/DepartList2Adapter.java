package com.example.doctorclient.adapter;

import android.view.View;

import com.example.doctorclient.R;
import com.example.doctorclient.event.DepartidDto;

public class DepartList2Adapter extends BaseRecyclerAdapter<DepartidDto.DataBean> {
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public DepartList2Adapter() {
        super(R.layout.layout_item_depart_child);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, DepartidDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_name,model.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(model.getIUID(),model.getName());
            }
        });
    }

    public interface OnClickListener{
        void onClick(String IUID,String name);
    }
}
