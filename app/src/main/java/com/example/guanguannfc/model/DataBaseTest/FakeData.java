package com.example.guanguannfc.model.DataBaseTest;

import android.content.Context;

import com.example.guanguannfc.model.Dao.DaoActSta;
import com.example.guanguannfc.model.Dao.DaoActivity;
import com.example.guanguannfc.model.Dao.DaoActivityType;
import com.example.guanguannfc.model.Dao.DaoBox;
import com.example.guanguannfc.model.Dao.DaoBoxContent;
import com.example.guanguannfc.model.Dao.DaoPush;
import com.example.guanguannfc.model.Dao.DaoUserInfo;
import com.example.guanguannfc.model.Dao.DaoFriend;
import com.example.guanguannfc.model.Dao.DaoMoment;

import java.util.Date;

public class FakeData {

    Context context;

    public FakeData(Context context){
        this.context = context;
    }


    public  void insert(){
        Date date = new Date();
        long time = date.getTime();

        DaoUserInfo daoUserInfo = new DaoUserInfo(context);
        DaoActivityType daoActivityType = new DaoActivityType(context);
        DaoActivity daoActivity = new DaoActivity(context);
        DaoBox daoBox = new DaoBox(context);
        DaoActSta daoActSta = new DaoActSta(context);
        DaoBoxContent daoBoxContent = new DaoBoxContent(context);
        DaoMoment daoMoment = new DaoMoment(context);
        DaoFriend daoFriend = new DaoFriend(context);
        DaoPush daoPush = new DaoPush(context);

        String sum1 = "人生在世，俯仰之间，自当追求卓越，但有尽其所能";
        String pas1 = "时间比水流失的还快，所以想做的事情就去努力，人这辈子，至少自己得对得起自己。";
        String sum2 = "得意时不要得瑟，落魄时不要堕落";
        String pas2 = "时间如流水，逝去了岁月，领悟了生活，顿悟了人生！珍惜当下的每一天，好好生活，静静领悟。昨天，删去。今天，留着。明天，争取。对的，坚持。错的，放弃。你再优秀也会有人对你不屑一顾，你再不堪也会有人把你视若生命。";
        String sum3 = "自律可以改变人生";
        String pas3 = "自律一词，我认为用心在律，应从自身出发，正人先正己。加强自我修养，提升自身素质，因为事物普遍联系的，不要指望为了自律而自律，全面提升自己修养时对自己加以自律教育，不觉间你已养成自律精神。";
        String sum4 = "我要改变";
        String pas4 = "跑步是个特别痛苦的过程，有几个关键的坎儿很那超越，我是了解自己的，停下一次我就再也坚持不下去，这种逼迫的方式后来却带给我彻头彻尾的改变，我不再抵触，反而爱上了这种整日与惰性做斗争的快感，享受一次次超越自己的过程。我终于明白，我的态度决定了生活的质量。";

        if(daoUserInfo.registrationQuery("aaa") != true){
            daoUserInfo.insert("aaa", "123123");
            daoUserInfo.insert("bbb","123123");
            daoUserInfo.insert("ccc", "123123");
            daoUserInfo.insert("ddd", "123123");

            daoActivityType.insert("学习");
            daoActivityType.insert("工作");
            daoActivityType.insert("睡觉");
            daoActivityType.insert("吃饭");
            daoActivityType.insert("其他");

            daoActivity.insert(1, "100001", 1, "学数学");
            daoActivity.insert(1, "100002", 1, "学英语");
            daoActivity.insert(1, "100003", 2, "做作业");
            daoActivity.insert(1, "100004", 2, "上网课");
            daoActivity.insert(1, "100005", 3, "睡午觉");
            daoActivity.insert(1, "100006", 3, "睡晚觉");
            daoActivity.insert(1, "100007", 4, "吃中饭");
            daoActivity.insert(1, "100008", 4, "吃晚饭");
            daoActivity.insert(1, "100009", 5, "玩游戏");
            daoActivity.insert(2, "200001", 1, "学语文");
            daoActivity.insert(2, "200002", 1, "学物理");
            daoActivity.insert(2, "200003", 2, "做ppt");
            daoActivity.insert(2, "200004", 2, "上网课");
            daoActivity.insert(2, "200005", 3, "睡午觉");
            daoActivity.insert(2, "200006", 3, "睡晚觉");
            daoActivity.insert(2, "200007", 4, "吃汉堡");
            daoActivity.insert(2, "200008", 4, "吃披萨");
            daoActivity.insert(2, "200009", 5, "看韩剧");

            daoActSta.insert(1, time + 13000, time + 16000,"学习使我快乐",1);
            daoActSta.insert(1, time+ 121000, time + 123000);
            daoActSta.insert(2, time + 310000, time + 320000);
            daoActSta.insert(2, time + 900000, time + 906000);
            daoActSta.insert(3, time + 86000, time + 88000);
            daoActSta.insert(3, time + 600000, time + 660000);
            daoActSta.insert(1, time + 123453, time + 143242);
            daoActSta.insert(4, time + 7600000, time + 8600000);
            daoActSta.insert(5, time + 6444522, time + 6743321);
            daoActSta.insert(6, time + 4564564, time + 4745224);
            daoActSta.insert(5, time + 8723121, time + 9022346);
            daoActSta.insert(4, time + 6522103, time + 7623012);
            daoActSta.insert(7, time + 10092333, time + 10293839);
            daoActSta.insert(2, time + 2342534, time + 2393023);
            daoActSta.insert(8, time + 234253463, time + 234276291);
            daoActSta.insert(9, time + 99761230, time + 100098760);
            daoActSta.insert(6, time + 600123000, time + 660123000);
            daoActSta.insert(5, time + 3453463, time + 3456786);
            daoActSta.insert(10, time + 2424134, time + 2676789);
            daoActSta.insert(7, time + 45647433, time + 45676549);
            daoActSta.insert(9, time + 6587352, time + 6592345);
            daoActSta.insert(8, time + 1435627, time + 1498763);
            daoActSta.insert(18, time + 996842, time + 1092322);
            daoActSta.insert(12, time + 1374109, time + 1390875);
            daoActSta.insert(13, time + 74931501, time + 74987341);
            daoActSta.insert(10, time + 871491, time + 879876);
            daoActSta.insert(2, time + 1432494, time + 1476823);
            daoActSta.insert(11, time + 79870723, time + 79890872);
            daoActSta.insert(13, time + 6237469, time + 6298761);
            daoActSta.insert(14, time + 1378914, time + 1409876);
            daoActSta.insert(15, time + 184914, time + 201933);
            daoActSta.insert(6, time + 133284, time + 1459087);
            daoActSta.insert(18, time + 8023423, time + 8067453);
            daoActSta.insert(11, time + 2389237, time + 2489062);
            daoActSta.insert(17, time + 1748931, time + 1790321);
            daoActSta.insert(9, time + 3274241, time + 3290652);
            daoActSta.insert(16, time + 23847294, time + 23887123);
            daoActSta.insert(15, time + 2334634, time + 2376123);
            daoActSta.insert(13, time + 8237492, time + 8261459);
            daoActSta.insert(12, time + 1238324, time + 1256743);
            daoActSta.insert(14, time + 452131, time + 459812);
            daoActSta.insert(14, time + 6178364, time + 6241362);
            daoActSta.insert(7, time + 2498234, time + 2501873);
            daoActSta.insert(10, time + 123141, time + 149247);
            daoActSta.insert(8, time + 2347892, time + 2360821);
            daoActSta.insert(17, time + 1231874, time + 1240913);
            daoActSta.insert(6, time + 1423423, time + 1450183);
            daoActSta.insert(3, time + 87998782, time + 88231111);
            daoActSta.insert(2, time + 2349827, time + 2356192);
            daoActSta.insert(13, time + 42388234, time + 42390124);
            daoActSta.insert(14, time + 32849274, time + 32852781);

            daoBox.insert(1, "100101", "化妆品", "家");
            daoBox.insert(1, "100102", "笔盒", "抽屉");
            daoBox.insert(2, "200101", "玩具盒", "玩具房");
            daoBox.insert(2, "200102", "优盘", "书房");

            daoBoxContent.insert(1, "口红", 2);
            daoBoxContent.insert(1, "BB霜", 1);
            daoBoxContent.insert(1, "面霜", 1);
            daoBoxContent.insert(2, "钢笔", 2);
            daoBoxContent.insert(2, "水笔", 3);
            daoBoxContent.insert(3, "泰迪熊", 1);
            daoBoxContent.insert(3, "乐高", 3);
            daoBoxContent.insert(4, "硬盘", 2);
            daoBoxContent.insert(4, "迷你优盘", 1);

            daoMoment.insert(2,1, "加个好友吧", 1);
            daoMoment.insert(2, 3, "我们也加个好友吧", 0);
            daoMoment.insert(3,1,"我喜欢你 我们一起跑步吧", 0);
            daoMoment.insert(3,2,"我不喜欢你 但是我想加你好友",0);
            daoMoment.insert(4,2,"我真的很喜欢你",0);

            daoFriend.insert(1,4);
            daoFriend.insert(4,1);
            daoFriend.insert(3,4);
            daoFriend.insert(4,3);
            daoFriend.insert(1,2);
            daoFriend.insert(1,3);
            daoFriend.insert(2,1);

            daoPush.insert(1,"时间鸡汤",sum1,pas1);
            daoPush.insert(1,"时间鸭汤",sum2,pas2);
            daoPush.insert(2,"自律鸡汤",sum3,pas3);
            daoPush.insert(2,"自律鸭汤",sum4,pas4);

        }

     




    }
}
