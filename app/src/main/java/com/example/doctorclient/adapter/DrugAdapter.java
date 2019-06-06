package com.example.doctorclient.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.DrugListDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.PriceUtils;

/**
 * description:药品列表
 * autour: huang
 * date: 2019/5/20 14:53
 * update: 2019/5/20
 * version:
 */
public class DrugAdapter extends BaseRecyclerAdapter<DrugListDto.DataBean> {

    Context context;

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public DrugAdapter(Context context) {
        super(R.layout.layout_item_drug);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, DrugListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_drug);
        String portrait = model.getThe_img();
        if (portrait.indexOf("DOC") != -1) {
            GlideUtil.setImage(context, WebUrlUtil.IMG_URL + portrait, imageView, 0);
            L.e("lgh", WebUrlUtil.IMG_URL + portrait);
        } else {
            GlideUtil.setImage(context, WebUrlUtil.IMG_URL + "DOC/my" + portrait, imageView, 0);
            L.e("lgh", WebUrlUtil.IMG_URL + "DOC/my" + portrait);
        }
        holder.text(R.id.tv_item_drug_name, model.getName());
        holder.text(R.id.tv_item_drug_company, model.getThe_company());
        holder.text(R.id.tv_item_drug_spec, model.getThe_spec());
        holder.text(R.id.tv_item_drug_price, "￥" + PriceUtils.formatPrice(model.getPrice()));
        TextView add = holder.itemView.findViewById(R.id.tv_item_add);
        TextView drugAdd = holder.itemView.findViewById(R.id.tv_item_drug_add);
        TextView drugSubtract = holder.itemView.findViewById(R.id.tv_item_drug_subtract);
        TextView drugNum = holder.itemView.findViewById(R.id.tv_item_drug_num);
        LinearLayout linearLayout = holder.itemView.findViewById(R.id.ll_item_drug_num);
        linearLayout.setVisibility(model.getDrug_num() < 1 ? View.GONE : View.VISIBLE);
        add.setVisibility(model.getDrug_num() < 1 ? View.VISIBLE : View.GONE);
        drugNum.setText(model.getDrug_num()+"");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
                add.setVisibility(View.GONE);
                model.setDrug_num(1);
                drugNum.setText(model.getDrug_num() + "");
                onClickListener.OnClick(model);
            }
        });

        drugAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = model.getDrug_num() + 1;
                model.setDrug_num(num);
                drugNum.setText(num + "");
                onClickListener.OnClick(model);
            }
        });

        drugSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = model.getDrug_num() - 1;
                if (num < 1) {
                    linearLayout.setVisibility(View.GONE);
                    add.setVisibility(View.VISIBLE);
                    onClickListener.OnClick(model);
                }
                model.setDrug_num(num);
                drugNum.setText(num + "");
                onClickListener.OnClick(model);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.OnitemViewClick(model.getIUID(),position,model.getDrug_num());
            }
        });
    }

    public interface OnClickListener {
        void OnClick(DrugListDto.DataBean model);
        void OnitemViewClick(String iuid,int position,int num);
    }
}
