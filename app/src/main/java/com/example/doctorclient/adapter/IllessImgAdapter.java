package com.example.doctorclient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.doctorclient.R;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.message.MessageDetailActivity;
import com.example.doctorclient.util.popup.CusviewXPopup;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lxj.xpopup.core.ImageViewerPopupView;
import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener;
import com.lxj.xpopup.interfaces.XPopupImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IllessImgAdapter extends BaseRecyclerAdapter<String>{

    Context context;
    RecyclerView recyclerView;

    public IllessImgAdapter(Context context,RecyclerView recyclerView) {
        super(R.layout.layout_item_illess_img);
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, String model, int position) {
       holder.setIsRecyclable(false);
        ImageView userPortaitIv = holder.itemView.findViewById(R.id.iv_img_illess);
        String url="";
        if (model.indexOf("DOC") != -1){
            url = WebUrlUtil.IMG_URL+model;
            L.e("lgh",WebUrlUtil.IMG_URL+model);
        }else {
           url= WebUrlUtil.IMG_URL+"DOC/my"+model;
            L.e("lgh",WebUrlUtil.IMG_URL+"DOC/my"+model);
        }
        GlideUtil.setImage(context, url,userPortaitIv,0);
        String finalUrl = url;
        userPortaitIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new CusviewXPopup.Builder(context)
                        .asImageViewer(userPortaitIv, finalUrl, false, -1, -1, -1, false, new ImageLoader())
                        .show();
            }
        });
    }

    public static class ImageLoader implements XPopupImageLoader {
        @Override
        public void loadImage(int position, @NonNull Object url, @NonNull ImageView imageView) {
            //必须指定Target.SIZE_ORIGINAL，否则无法拿到原图，就无法享用天衣无缝的动画
            Glide.with(imageView).load(url).apply(new RequestOptions().placeholder(R.mipmap.ic_launcher_round).override(Target.SIZE_ORIGINAL)).into(imageView);
        }

        @Override
        public File getImageFile(@NonNull Context context, @NonNull Object uri) {
            try {
                return Glide.with(context).downloadOnly().load(uri).submit().get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
