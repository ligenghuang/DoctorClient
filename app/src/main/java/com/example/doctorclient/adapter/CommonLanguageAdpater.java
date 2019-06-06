package com.example.doctorclient.adapter;

import android.view.View;
import android.widget.ImageView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.CommonLanguageListDto;
/**
 * description:常用语列表 适配器
 * autour: huang
 * date: 2019/5/29 11:36
 * update: 2019/5/29
 * version:
 */
public class CommonLanguageAdpater extends BaseRecyclerAdapter<CommonLanguageListDto.DataBean> {
    OnClickListener onClickListener;
    boolean isEdit = false;

    public void setEdit(boolean edit) {
        isEdit = edit;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CommonLanguageAdpater() {
        super(R.layout.layout_item_common_language);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, CommonLanguageListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_name,model.getTextContent());
        ImageView imageView = holder.itemView.findViewById(R.id.iv_close);
        imageView.setVisibility(isEdit ? View.VISIBLE:View.GONE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClose(model);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             onClickListener.onClick(model.getTextContent());
            }
        });
    }

    public interface OnClickListener{
        void onClick(String txt);
        void onClose(CommonLanguageListDto.DataBean model);
    }
}
