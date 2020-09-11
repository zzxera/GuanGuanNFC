package com.example.guanguannfc.view;

import com.example.guanguannfc.model.util.HttpUtil;

public class TestController {
    Message message;
    public TestController (Message message) {
        this.message = message;
    }



    public void load(final String username, final String password) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //下面调用model方法
                String str = HttpUtil.get("http://49.232.151.194/DaoUserInfo/loadQuery", "?username=" + username + "&password=" + password);

                //通过getMessage作为Activity的回调函数(保证str获得之后，再执行getLoadMessage(str)方法)
                message.getLoadMessage(str);
            }
        }).start();

    }
    //定义一个内部接口，Activity实现这个接口
    interface Message{
        //Activity实现接口后需要重写这个回调方法，这个回调方法会在上面的load方法中被调用
        void getLoadMessage(String str);
    }
}
