package com.example.doctorclient.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.example.doctorclient.R;
import com.example.doctorclient.net.WebUrlUtil;
import com.lgh.huanglib.util.config.GlideUtil;

public class PrescriptionImgAdapter extends BaseRecyclerAdapter<String> {
    Context context;
    public PrescriptionImgAdapter(Context context) {
        super(R.layout.layout_item_illess_img);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, String model, int position) {
        holder.setIsRecyclable(false);
        ImageView userPortaitIv = holder.itemView.findViewById(R.id.iv_img_illess);
        GlideUtil.setImage(context, WebUrlUtil.IMG_URL+model,userPortaitIv,0);
    }
}
