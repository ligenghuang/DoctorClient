package com.example.doctorclient.util.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.doctorclient.R;
import com.lgh.huanglib.util.data.ResUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
* 是否可开处方药
* @author lgh
* created at 2019/5/17 0017 10:57
*/
public class PrescriptionDialog extends Dialog {

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public PrescriptionDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_prescription_dialog);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        Window win = this.getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.x = 20;
        lp.y = 20;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        win.setGravity(Gravity.BOTTOM);
    }

    @OnClick({R.id.tv_prescription_n,R.id.tv_prescription_y})
    void OnCilck(View view){
        switch (view.getId()){
            case R.id.tv_prescription_n:
                //todo 不可开处方药
                onClickListener.isPrescription(0, ResUtil.getString(R.string.user_info_tip_13));
                break;
            case R.id.tv_prescription_y:
                //todo 可开处方药
                onClickListener.isPrescription(1, ResUtil.getString(R.string.user_info_tip_14));
                break;
        }
    }

    public interface OnClickListener{
        void isPrescription(int type,String text);
    }
}
