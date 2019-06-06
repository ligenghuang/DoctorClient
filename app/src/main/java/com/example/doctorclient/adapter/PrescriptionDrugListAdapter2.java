package com.example.doctorclient.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.PreInfoDto;
import com.example.doctorclient.event.PrescriptionDrugListDto;
import com.example.doctorclient.net.WebUrlUtil;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;

/**
 * description:药品清单列表 适配器
 * autour: huang
 * date: 2019/5/21 10:01
 * update: 2019/5/21
 * version:
 */
public class PrescriptionDrugListAdapter2 extends BaseRecyclerAdapter<PreInfoDto.DataBean.DrugMVBean> {
    Context context;

    public PrescriptionDrugListAdapter2(Context context) {
        super(R.layout.layout_item_drug_list);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, PreInfoDto.DataBean.DrugMVBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_drug_shop);
        String portrait = model.getThe_img();
        if (portrait.indexOf("DOC") != -1) {
            GlideUtil.setImage(context, WebUrlUtil.IMG_URL + portrait, imageView, 0);
            L.e("lgh", WebUrlUtil.IMG_URL + portrait);
        } else {
            GlideUtil.setImage(context, WebUrlUtil.IMG_URL + "DOC/my" + portrait, imageView, 0);
            L.e("lgh", WebUrlUtil.IMG_URL + "DOC/my" + portrait);
        }
        holder.text(R.id.tv_item_drug_name, model.getName());
        holder.text(R.id.tv_item_drug_spec, model.getThe_spec());
        TextView drugNum = holder.itemView.findViewById(R.id.tv_item_drug_num);
        TextView drugAdd = holder.itemView.findViewById(R.id.tv_item_drug_add);
        TextView drugSubtract = holder.itemView.findViewById(R.id.tv_item_drug_subtract);
        drugNum.setText(model.getDrug_num()+"");
        drugAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = model.getDrug_num() + 1;
                model.setDrug_num(num);
                drugNum.setText(num+"");
            }
        });

        drugSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getDrug_num() >= 1){
                    int num = model.getDrug_num() - 1;
                    model.setDrug_num(num);
                    drugNum.setText(num+"");
                }
            }
        });

        EditText editText = holder.itemView.findViewById(R.id.et_item_drug_usage);
        editText.setText(model.getNum_note());
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(editText.getText().toString())){
                    model.setNum_note(editText.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
