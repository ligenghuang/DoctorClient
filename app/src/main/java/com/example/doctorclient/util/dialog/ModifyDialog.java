package com.example.doctorclient.util.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doctorclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
* 修改
* @author lgh
* created at 2019/5/17 0017 9:59
*/
public class ModifyDialog extends Dialog {

    String title = "";
    Context context;

    @BindView(R.id.et_content)
    EditText contentEt;
    @BindView(R.id.tv_title)
    TextView titleTv;

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public ModifyDialog(@NonNull Context context, int themeResId, String title) {
        super(context, themeResId);
        this.context = context;
        this.title = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_modify_dialog);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(true);
        Window win = this.getWindow();
        win.setGravity(Gravity.CENTER);
        initView();
    }

    private void initView() {
        titleTv.setText(title);

    }

    @Override
    public void dismiss() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        super.dismiss();
    }

    @OnClick({R.id.tv_cancel,R.id.tv_confirm})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_confirm:
                onClickListener.confirm(contentEt.getText().toString());
                break;
        }
    }

    public interface OnClickListener{
        void confirm(String txet);
    }

}
