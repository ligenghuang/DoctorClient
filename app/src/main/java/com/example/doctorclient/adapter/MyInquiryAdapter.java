package com.example.doctorclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.MyInquiryDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.message.MessageDetailActivity;
import com.example.doctorclient.ui.mine.inquiry.InquiryDetailsActivity;
import com.example.doctorclient.ui.mine.inquiry.InquiryDetailsActivity2;
import com.example.doctorclient.ui.mine.inquiry.PrescriptionActivity;
import com.example.doctorclient.util.data.DynamicTimeFormat;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.PriceUtils;
import com.lgh.huanglib.util.data.ResUtil;

import cn.carbs.android.expandabletextview.library.ExpandableTextView;

/**
* 我的问诊单 列表适配器
* @author lgh
* created at 2019/5/17 0017 17:46
*/
public class MyInquiryAdapter extends BaseRecyclerAdapter<MyInquiryDto.DataBean> {
    Context context;

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public MyInquiryAdapter(Context context) {
        super(R.layout.layout_item_my_inquiry);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, MyInquiryDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView userPortaitIv = holder.itemView.findViewById(R.id.iv_item_portrait);
        String portrait = model.getThe_img();
        if (portrait.indexOf("DOC") != -1 || portrait.indexOf("H5/Uimg") != -1){
            GlideUtil.setImageCircle(context, WebUrlUtil.IMG_URL+portrait,userPortaitIv,R.drawable.icon_placeholder);
        }else {
            GlideUtil.setImageCircle(context,WebUrlUtil.IMG_URL+"DOC/my"+portrait,userPortaitIv,R.drawable.icon_placeholder);
        }
        holder.text(R.id.tv_item_prescription,model.getDrug_flag() == 1?ResUtil.getString(R.string.inquity_tip_14):"");
        holder.text(R.id.tv_item_name,model.getPatienName());
        holder.text(R.id.tv_item_user_pay, ResUtil.getFormatString(R.string.inquity_tip_12, PriceUtils.formatPrice(model.getPay_money())));
        holder.text(R.id.tv_item_income,ResUtil.getFormatString(R.string.inquity_tip_13,PriceUtils.formatPrice(model.getBrokerage())));
        holder.text(R.id.tv_item_time, DynamicTimeFormat.LongToString2(model.getCreate_time_stamp()));
        holder.text(R.id.tv_item_time_2, DynamicTimeFormat.LongToString2(model.getCreate_time_stamp()));
        ExpandableTextView expandableTextView = (ExpandableTextView) holder.itemView.findViewById(R.id.etv);
        //展开收起监听
        expandableTextView.setExpandListener(new ExpandableTextView.OnExpandListener() {
            @Override
            public void onExpand(ExpandableTextView view) {
                Log.i("lgh", "展开") ;
            }

            @Override
            public void onShrink(ExpandableTextView view) {
                Log.i("lgh", "收起") ;
            }
        });
        expandableTextView.setText(model.getIll_note());
        setType(holder,model.getAsk_flag());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.e("lgh_accepts","model.getAsk_flag()   = "+model.getAsk_flag());
                Intent intent = new Intent(context, model.getAsk_flag()== 1? InquiryDetailsActivity2.class:InquiryDetailsActivity.class);
                intent.putExtra("iuid",model.getAskIUID());
                intent.putExtra("isAccepts",model.getAsk_flag() == 0);
                context.startActivity(intent);
            }
        });

        holder.itemView.findViewById(R.id.tv_item_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 历史记录
                Intent intent = new Intent(context, model.getAsk_flag()== 1? InquiryDetailsActivity2.class:InquiryDetailsActivity.class);
                intent.putExtra("iuid",model.getAskIUID());
                context.startActivity(intent);
            }
        });

        holder.itemView.findViewById(R.id.tv_item_prescribe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 开处方
                Intent intent = new Intent(context, PrescriptionActivity.class);
                intent.putExtra("iuid",model.getAskIUID());
                context.startActivity(intent);
            }
        });

        holder.itemView.findViewById(R.id.tv_item_confirm_accepts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 确认接诊
                onClickListener.confirmationListener(model.getAskIUID(),model.getUserid());
            }
        });
        holder.itemView.findViewById(R.id.tv_item_pay_return_visit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 回访
                Intent intent = new Intent(context, MessageDetailActivity.class);
                intent.putExtra("touserId",model.getUserid());
                intent.putExtra("askId",model.getAskIUID());
                intent.putExtra("userid",model.getUserid());
                context.startActivity(intent);
            }
        });

    }

    private void setType(SmartViewHolder holder, int ask_flag) {
        TextView typeTv = holder.itemView.findViewById(R.id.tv_item_type);
        TextView confirmAcceptsTv = holder.itemView.findViewById(R.id.tv_item_confirm_accepts);
        TextView historyTv = holder.itemView.findViewById(R.id.tv_item_history);
        LinearLayout inTheInterrogationTv = holder.itemView.findViewById(R.id.ll_in_the_interrogation);
        LinearLayout payInfoLl = holder.itemView.findViewById(R.id.ll_pay_info);
        LinearLayout timeLl = holder.itemView.findViewById(R.id.ll_time);
        confirmAcceptsTv.setVisibility(View.GONE);
        historyTv.setVisibility(View.GONE);
        inTheInterrogationTv.setVisibility(View.GONE);
        payInfoLl.setVisibility(View.GONE);
        timeLl.setVisibility(View.GONE);
        String text = "";
        int resId = 0;
        switch (ask_flag){
            case 0:
                //todo 待接诊
                text = ResUtil.getString(R.string.inquity_tip_2);
                resId = R.color.color_ff7d00;
                confirmAcceptsTv.setVisibility(View.VISIBLE);
                payInfoLl.setVisibility(View.VISIBLE);
                break;
            case 1:
                //todo 问诊中
                text = ResUtil.getString(R.string.inquity_tip_3);
                resId = R.color.color_ff7d00;
                inTheInterrogationTv.setVisibility(View.VISIBLE);
                payInfoLl.setVisibility(View.VISIBLE);
                break;
            case 2:
                //todo 已完成
                text = ResUtil.getString(R.string.inquity_tip_4);
                resId = R.color.color_38a234;
                historyTv.setVisibility(View.VISIBLE);
                payInfoLl.setVisibility(View.VISIBLE);
                break;
            case 3:
                //todo 已退回
                text = ResUtil.getString(R.string.inquity_tip_5);
                resId = R.color.color_9;
                timeLl.setVisibility(View.VISIBLE);
                break;
        }
        typeTv.setText(text);
        typeTv.setTextColor(ResUtil.getColor(resId));
    }

    public interface OnClickListener{
        void confirmationListener(String iuid,String Userid);
    }
}
