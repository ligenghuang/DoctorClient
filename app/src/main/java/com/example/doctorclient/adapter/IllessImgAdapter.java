package com.example.doctorclient.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.example.doctorclient.R;
import com.example.doctorclient.net.WebUrlUtil;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;

public class IllessImgAdapter extends BaseRecyclerAdapter<String>{

    Context context;

    public IllessImgAdapter(Context context) {
        super(R.layout.layout_item_illess_img);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, String model, int position) {
       holder.setIsRecyclable(false);
        ImageView userPortaitIv = holder.itemView.findViewById(R.id.iv_img_illess);
        if (model.indexOf("DOC") != -1){
            GlideUtil.setImage(context, WebUrlUtil.IMG_URL+model,userPortaitIv,0);
            L.e("lgh",WebUrlUtil.IMG_URL+model);
        }else {
            GlideUtil.setImage(context,WebUrlUtil.IMG_URL+"DOC/my"+model,userPortaitIv,0);
            L.e("lgh",WebUrlUtil.IMG_URL+"DOC/my"+model);
        }
    }
}
