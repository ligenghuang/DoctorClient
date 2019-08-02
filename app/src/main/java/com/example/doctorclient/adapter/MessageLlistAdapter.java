package com.example.doctorclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.MessageListDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.message.MessageDetailActivity;
import com.example.doctorclient.util.config.MyApp;
import com.example.doctorclient.util.data.DynamicTimeFormat;
import com.example.doctorclient.util.data.MySp;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.lgh.huanglib.util.data.TimeUtils;

/**
 * description:消息列表 适配器
 * autour: huang
 * date: 2019/5/22 17:08
 * update: 2019/5/22
 * version:
 */
public class MessageLlistAdapter extends BaseRecyclerAdapter<MessageListDto.DataBean>{
    Context context;
    public MessageLlistAdapter(Context context) {
        super(R.layout.layout_item_message);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, MessageListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_message_name,model.getNiceName());
        holder.text(R.id.tv_message_time, DynamicTimeFormat.LongToString6(model.getChat_time_stamp()));
        ImageView imageView = holder.itemView.findViewById(R.id.iv_message_portrait);
        String portrait = model.getUserIMG();
        if (portrait.indexOf("DOC") != -1 || portrait.indexOf("H5/Uimg") != -1) {
            GlideUtil.setImage(context, WebUrlUtil.IMG_URL + portrait, imageView, R.drawable.icon_placeholder);
            L.e("lgh", WebUrlUtil.IMG_URL + portrait);
        } else {
            GlideUtil.setImage(context, WebUrlUtil.IMG_URL + "DOC/my" + portrait, imageView, R.drawable.icon_placeholder);
            L.e("lgh", WebUrlUtil.IMG_URL + "DOC/my" + portrait);
        }
        String text = "";
        switch (model.getThe_class()){
            case 0:
                text =model.getChat_note();
                break;
            case 1:
                text = ResUtil.getString(R.string.message_tip_2);
                break;

        }
        holder.text(R.id.tv_message_content,text);
        TextView messageNumTv = holder.itemView.findViewById(R.id.tv_message_num);
        if (model.getNotread() == 0){
            messageNumTv.setText(ResUtil.getString(R.string.message_tip_1));
            messageNumTv.setBackgroundResource(R.color.white);
            messageNumTv.setTextColor(ResUtil.getColor(R.color.color_9));
        }else if (model.getNotread() > 99){
            messageNumTv.setText("...");
            messageNumTv.setBackgroundResource(R.drawable.shape_round_bg);
            messageNumTv.setTextColor(ResUtil.getColor(R.color.white));
        }else {
            messageNumTv.setText(model.getNotread()+"");
            messageNumTv.setBackgroundResource(R.drawable.shape_round_bg);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageDetailActivity.class);
                intent.putExtra("touserId",model.getUserid());
                intent.putExtra("askId",model.getAskid());
                intent.putExtra("userid",model.getUserid());
                context.startActivity(intent);
            }
        });
    }
}
