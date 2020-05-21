package com.example.guanguannfc.view.data;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.guanguannfc.R;


public class ShareDialog extends Dialog {

    private EditText et_content;
    private Button btn_confrim,btn_cancel;

    //声明两个点击事件，等会一定要为取消和确定这两个按钮也点击事件
    private IOnCancelListener cancelListener;
    private IOnConfirmListener confirmListener;


    public ShareDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_sharewindow);
        et_content=findViewById(R.id.et_content);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_confrim = findViewById(R.id.btn_confirm);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        Point size = new Point();
        d.getSize(size);
        p.width = (int)(size.x*0.8);
        getWindow().setAttributes(p);

        btn_confrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(confirmListener!=null){
                    confirmListener.onConfirm(ShareDialog.this);

                }
                dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cancelListener!=null){
                    cancelListener.onCancel(ShareDialog.this);
                }
                dismiss();//按钮按之后会消失

            }
        });
    }

    //写两个接口，当要创建一个Dialog对象的时候，必须要实现这两个接口
    //也就是说，当要弹出一个自定义dialog的时候，取消和确定这两个按钮的点击事件，一定要重写！
    public interface IOnCancelListener{
        void onCancel(ShareDialog dialog);
    }
    public interface IOnConfirmListener{
        void onConfirm(ShareDialog dialog);
    }

    public void setCancel(ShareDialog.IOnCancelListener cancelListener) {
        this.cancelListener=cancelListener;
    }
    public void setConfirm(ShareDialog.IOnConfirmListener confirmListener){
        this.confirmListener=confirmListener;
    }


    public EditText getEditText(){
        return et_content;
    }


}
