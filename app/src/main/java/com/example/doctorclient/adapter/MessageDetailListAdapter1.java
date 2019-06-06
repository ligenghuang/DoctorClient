package com.example.doctorclient.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.MessageDetailListDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.util.data.DynamicTimeFormat;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;

/**
 * description:消息详情 列表适配器
 * autour: huang
 * date: 2019/5/23 16:17
 * update: 2019/5/23
 * version:
 */
public class MessageDetailListAdapter1 extends BaseRecyclerAdapter<MessageDetailListDto.DataBean.ListBean> {

    Context context;
    String touserid;
    public MessageDetailListAdapter1(Context context, String touserid) {
        super(R.layout.layout_item_message_detail);
        this.context = context;
        this.touserid = touserid;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, MessageDetailListDto.DataBean.ListBean model, int position) {
        holder.setIsRecyclable(false);
        RelativeLayout rightRl = holder.itemView.findViewById(R.id.rl_right);
        RelativeLayout leftRl = holder.itemView.findViewById(R.id.rl_left);
        rightRl.setVisibility(View.GONE);
        leftRl.setVisibility(View.GONE);
        if (!model.getUserid().equals(touserid)){
            //todo 发送者
            rightRl.setVisibility(View.VISIBLE);
            holder.text(R.id.tv_right_time, DynamicTimeFormat.format(model.getChat_time_stamp()));
            setImage(model.getUserIMG(),holder.itemView.findViewById(R.id.iv_right));
            ImageView rightIv = holder.itemView.findViewById(R.id.iv_right_content);
            TextView rightTv = holder.itemView.findViewById(R.id.tv_right_content);
            setContent(model.getChat_note(),model.getThe_class(),rightIv,rightTv);
        }else {
            leftRl.setVisibility(View.VISIBLE);
            holder.text(R.id.tv_left_time, DynamicTimeFormat.format(model.getChat_time_stamp()));
            setImage(model.getUserIMG(),holder.itemView.findViewById(R.id.iv_left));
            ImageView leftIv = holder.itemView.findViewById(R.id.iv_left_content);
            TextView leftTv = holder.itemView.findViewById(R.id.tv_left_content);
            setContent(model.getChat_note(),model.getThe_class(),leftIv,leftTv);
        }
    }

    private void setContent(String chat_note, int read_flag, ImageView rightIv, TextView rightTv) {
        L.e("lgh_message","read_flag  ="+read_flag);
        L.e("lgh_message","chat_note  ="+chat_note);
        rightIv.setVisibility(View.GONE);
        rightTv.setVisibility(View.GONE);
        switch (read_flag){
            case 0:
                //todo 文字
                rightTv.setVisibility(View.VISIBLE);
                rightTv.setText(chat_note);
                break;
            case 1:
                //todo 图片
                rightIv.setVisibility(View.VISIBLE);
                GlideUtil.setImage(context, WebUrlUtil.IMG_URL + chat_note, rightIv, 0);
                break;
        }
    }

    /**
     * 设置头像
     * @param img
     * @param imageView
     */
    private void setImage(String img, ImageView imageView){
        if (img.indexOf("DOC") != -1) {
            GlideUtil.setImageCircle(context, WebUrlUtil.IMG_URL + img, imageView, R.drawable.icon_placeholder);
            L.e("lgh", WebUrlUtil.IMG_URL + img);
        } else {
            GlideUtil.setImageCircle(context, WebUrlUtil.IMG_URL + "DOC/my" + img, imageView, R.drawable.icon_placeholder);
            L.e("lgh", WebUrlUtil.IMG_URL + "DOC/my" + img);
        }
    }
}
