package com.example.doctorclient.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.doctorclient.R;
import com.example.doctorclient.event.MessageDetailListDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.message.ImageDetailActivity;
import com.example.doctorclient.util.data.DynamicTimeFormat;
import com.example.doctorclient.util.popup.CusviewXPopup;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.ImageViewerPopupView;
import com.lxj.xpopup.enums.PopupPosition;
import com.lxj.xpopup.enums.PopupType;
import com.lxj.xpopup.impl.AttachListPopupView;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener;
import com.lxj.xpopup.interfaces.XPopupImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * description:消息详情 列表适配器
 * autour: huang
 * date: 2019/5/23 16:17
 * update: 2019/5/23
 * version:
 */
public class MessageDetailListAdapter extends BaseRecyclerAdapter<MessageDetailListDto.DataBean.ListBean> {

    Context context;
    String touserid;
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public MessageDetailListAdapter(Context context, String touserid) {
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
        if (!model.getUserid().equals(touserid)) {
            //todo 发送者
            rightRl.setVisibility(View.VISIBLE);
            holder.text(R.id.tv_right_time, DynamicTimeFormat.format(model.getChat_time_stamp()));
            setImage(model.getUserIMG(), holder.itemView.findViewById(R.id.iv_right));
            ImageView rightIv = holder.itemView.findViewById(R.id.iv_right_content);
            TextView rightTv = holder.itemView.findViewById(R.id.tv_right_content);
            setContent(model.getChat_note(), model.getThe_class(), rightIv, rightTv);
//            showXPopup(rightTv);
        } else {
            leftRl.setVisibility(View.VISIBLE);
            L.e("lgh_time", "time1 = " + model.getChat_time_stamp());
            holder.text(R.id.tv_left_time, DynamicTimeFormat.format(model.getChat_time_stamp()));
            setImage(model.getUserIMG(), holder.itemView.findViewById(R.id.iv_left));
            ImageView leftIv = holder.itemView.findViewById(R.id.iv_left_content);
            TextView leftTv = holder.itemView.findViewById(R.id.tv_left_content);
            setContent(model.getChat_note(), model.getThe_class(), leftIv, leftTv);
//            showXPopup(leftTv);
        }
    }

    /**
     * 弹窗
     * @param rightTv
     */
    private void showXPopup(TextView rightTv) {
        // 必须在事件发生前，调用这个方法来监视View的触摸
        final CusviewXPopup.Builder builder = new CusviewXPopup.Builder(context)
                .watchView(rightTv);
        rightTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                builder.asAttachList(new String[]{"复制", "删除"}, null,
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                L.e("lgh_message", "onSelect    = " + position + "   +   " + text);
                                switch (position){
                                    case 0:
                                        //todo 复制
                                        ClipboardManager cmb = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                                        cmb.setText(rightTv.getText().toString());
                                        break;
                                    case 1:
                                        //todo 删除
                                        break;
                                }
                            }
                        })
                        .show();
                return false;
            }
        });
    }


    private void setContent(String chat_note, int read_flag, ImageView rightIv, TextView rightTv) {
        L.e("lgh_message", "read_flag  =" + read_flag);
        L.e("lgh_message", "chat_note  =" + chat_note);
        rightIv.setVisibility(View.GONE);
        rightTv.setVisibility(View.GONE);
        switch (read_flag) {
            case 0:
                //todo 文字
                rightTv.setVisibility(View.VISIBLE);
                rightTv.setText(chat_note);
                break;
            case 1:
                //todo 图片
                rightIv.setVisibility(View.VISIBLE);
                GlideUtil.setImage(context, WebUrlUtil.IMG_URL + chat_note, rightIv, 0);
                onClickListener.OnClick(WebUrlUtil.IMG_URL + chat_note, rightIv);
                break;
        }
    }


    /**
     * 设置头像
     *
     * @param img
     * @param imageView
     */
    private void setImage(String img, ImageView imageView) {
        if (img.indexOf("DOC") != -1 || img.indexOf("H5/Uimg") != -1) {
            GlideUtil.setImageCircle(context, WebUrlUtil.IMG_URL + img, imageView, R.drawable.icon_placeholder);
            L.e("lgh", WebUrlUtil.IMG_URL + img);
        } else {
            GlideUtil.setImageCircle(context, WebUrlUtil.IMG_URL + "DOC/my" + img, imageView, R.drawable.icon_placeholder);
            L.e("lgh", WebUrlUtil.IMG_URL + "DOC/my" + img);
        }
    }

    public interface OnClickListener{
        void OnClick(String url,ImageView imageView);
    }
}
