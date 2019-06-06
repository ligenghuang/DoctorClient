package com.example.doctorclient.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.DepartidDto;
/**
 * description:
 * autour: huang
 * date: 2019/5/20 10:04
 * update: 2019/5/20
 * version:
 */
public class ChildDepartAdapter extends BaseRecyclerAdapter<DepartidDto.DataBean>{

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public ChildDepartAdapter() {
        super(R.layout.layout_item_child_depart);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, DepartidDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        TextView textView = holder.itemView.findViewById(R.id.tv_item_child_depart);
        textView.setText(model.getName());
        textView.setSelected(model.isClick());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(model.getIUID(),position);
            }
        });
    }

    public interface OnClickListener {
        void onClick(String id, int position);
    }
}
