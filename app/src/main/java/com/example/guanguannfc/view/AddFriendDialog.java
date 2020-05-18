package com.example.guanguannfc.view;

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

public class AddFriendDialog extends Dialog {

    private String addName;
    private EditText et_name;
    private Button btn_confrim,btn_cancel;

    //声明两个点击事件，等会一定要为取消和确定这两个按钮也点击事件
    private IOnCancelListener cancelListener;
    private IOnConfirmListener confirmListener;

    public AddFriendDialog(@NonNull Context context) {
        super(context);
    }
    public AddFriendDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_addfriend);
        et_name=findViewById(R.id.et_name);
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
                    confirmListener.onConfirm(AddFriendDialog.this);

                }
                dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cancelListener!=null){
                    cancelListener.onCancel(AddFriendDialog.this);
                }
                dismiss();//按钮按之后会消失
                addName=et_name.getText().toString();
            }
        });
    }

//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btn_confirm:
//                if(confirmListener!=null){
//                    confirmListener.onConfirm(this);
//                    addName=et_name.getText().toString();
//                }
//                dismiss();
//                break;
//            case R.id.btn_cancel:
//                if(cancelListener!=null){
//                    cancelListener.onCancel(this);
//                }
//                dismiss();//按钮按之后会消失
//                break;
//        }
//    }

    //写两个接口，当要创建一个Dialog对象的时候，必须要实现这两个接口
    //也就是说，当要弹出一个自定义dialog的时候，取消和确定这两个按钮的点击事件，一定要重写！
    public interface IOnCancelListener{
        void onCancel(AddFriendDialog dialog);
    }
    public interface IOnConfirmListener{
        void onConfirm(AddFriendDialog dialog);
    }

    public void setCancel(IOnCancelListener cancelListener) {
        this.cancelListener=cancelListener;
    }
    public void setConfirm(IOnConfirmListener confirmListener){
        this.confirmListener=confirmListener;
    }


    public EditText getEditText(){
        return et_name;
    }

}
