package com.example.doctorclient.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.doctorclient.R;
import com.example.doctorclient.event.DepartidDto;
import com.lgh.huanglib.util.L;

import java.util.List;

/**
 * 科室列表适配器
 *
 * @author lgh
 * created at 2019/5/18 0018 16:13
 */
public class DepartidAdapter extends BaseRecyclerAdapter<DepartidDto.DataBean> {
    Context context;

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public DepartidAdapter(Context context) {
        super(R.layout.layout_item_depart);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, DepartidDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        TextView textView = holder.itemView.findViewById(R.id.tv_item_depart_name);
        textView.setSelected(model.isClick());
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.rv_item_depart);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ChildDepartAdapter childDepartAdapter = new ChildDepartAdapter();
        recyclerView.setAdapter(childDepartAdapter);
        recyclerView.setVisibility(model.isClick()?View.VISIBLE:View.GONE);
        childDepartAdapter.refresh(model.getChildData());
        textView.setText(model.getName());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < getAllData().size(); i++) {
                    getAllData().get(i).setClick(i == position);
                }
                notifyDataSetChanged();
                onClickListener.onClick(model.getIUID(),position);
            }
        });
        childDepartAdapter.setOnClickListener(new ChildDepartAdapter.OnClickListener() {
            @Override
            public void onClick(String id, int position) {
                L.e("lgh",position+"");
                List<DepartidDto.DataBean> list = childDepartAdapter.getAllData();
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setClick(i==position);
                }
                childDepartAdapter.notifyDataSetChanged();
                onClickListener.onChildClick(id,position);
            }
        });
    }

    public interface OnClickListener {
        void onClick(String id, int position);
        void onChildClick(String id,int position);
    }
}
