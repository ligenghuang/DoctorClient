package com.example.doctorclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.MyPrescriptionDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.example.doctorclient.ui.mine.prescription.PrescriptionDetailsActivity;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.IsFastClick;
import com.lgh.huanglib.util.data.PriceUtils;
import com.lgh.huanglib.util.data.ResUtil;

public class MyPrescriptionAdapter extends BaseRecyclerAdapter<MyPrescriptionDto.DataBean>{
    Context context;
    public MyPrescriptionAdapter(Context context) {
        super(R.layout.layout_item_prescription);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, MyPrescriptionDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView userPortaitIv = holder.itemView.findViewById(R.id.iv_item_portrait);
        GlideUtil.setImageCircle(context, model.getImt_url(),userPortaitIv,R.drawable.icon_placeholder);
        holder.text(R.id.tv_item_name,model.getPatientName());
        holder.text(R.id.tv_item_income, ResUtil.getFormatString(R.string.prescription_tip_1, PriceUtils.formatPrice(model.getBrokerage())));
        holder.text(R.id.tv_item_subtotal,ResUtil.getFormatString(R.string.prescription_tip_2,PriceUtils.formatPrice(model.getDrug_money())));
        int type = 0;
        if (model.getReback_flag() == 1){
            //todo 已取消
            type = 3;
        }else if (model.getFinish_flag() == 1){
            //TODO 已完成
            type = 2;
        }else if (model.getPay_flag() == 1 && model.getFinish_flag() != 1 && model.getReback_flag() != 1){
            //TODO  待发货
            type = 1;
        }else if (model.getPay_flag() == 0) {
            //TODO  待付款
            type = 0;
        }
        setType(holder,type);

        RecyclerView recyclerView = holder.itemView.findViewById(R.id.rv_item_shop);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        MyPrescriptionShopAdapter myPrescriptionShopAdapter = new MyPrescriptionShopAdapter(context);
        recyclerView.setAdapter(myPrescriptionShopAdapter);
        myPrescriptionShopAdapter.refresh(model.getDrugMV());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (IsFastClick.isFastClick()){
                  Intent intent = new Intent(context, PrescriptionDetailsActivity.class);
                  intent.putExtra("iuid",model.getAskdrugheadid());
                  context.startActivity(intent);
              }
            }
        });

//        recyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                return holder.itemView.onTouchEvent(motionEvent);
//            }
//        });
    }

    private void setType(SmartViewHolder holder, int agree_flag) {
        TextView typeTv = holder.itemView.findViewById(R.id.tv_item_type);
        String text = "";
        int resId = 0;
        switch (agree_flag){
            case 0:
                //todo 待支付
                text = ResUtil.getString(R.string.prescription_tip_3);
                resId = R.color.color_e64949;
                break;
            case 1:
                //todo 待收货
                text = ResUtil.getString(R.string.prescription_tip_4);
                resId = R.color.color_ff7d00;
                break;
            case 2:
                //todo 已收货
                text = ResUtil.getString(R.string.prescription_tip_5);
                resId = R.color.color_e64949;
                break;
            case 3:
                //todo 已退款
                text = ResUtil.getString(R.string.prescription_tip_6);
                resId = R.color.color_38a234;
                break;

        }
        typeTv.setText(text);
        typeTv.setTextColor(ResUtil.getColor(resId));
    }
}
