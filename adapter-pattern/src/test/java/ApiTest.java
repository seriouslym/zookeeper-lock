import com.alibaba.fastjson.JSON;
import com.zjj.design.mobilephone.Mobile;
import com.zjj.design.mobilephone.V220Power;
import com.zjj.design.mobilephone.V220To5Adapter;
import com.zjj.design.mq.CreateAccount;
import com.zjj.design.mq.MQAdapter;
import com.zjj.design.mq.RebateInfo;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;

public class ApiTest {

    @Test
    public void t(){
        Mobile mobile = new Mobile();
        V220Power v220Power = new V220Power();
        mobile.inputPower(new V220To5Adapter(v220Power));
    }
    @Test
    public void t1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        CreateAccount createAccount = new CreateAccount();
        createAccount.setNumber("100001");
        createAccount.setAddress("河北省.廊坊市.广阳区.大学里职业技术学院");
        createAccount.setAccountDate(333L);
        createAccount.setDesc("在校开户");

        HashMap<String, String> link01 = new HashMap<String, String>();
        link01.put("userId", "number");
        link01.put("bizId", "number");
        link01.put("bizTime", "accountDate");
        link01.put("desc", "desc");
        System.out.println(createAccount.toString());
        Map map = JSON.parseObject(createAccount.toString(), Map.class);
        System.out.println(map);
//        RebateInfo rebateInfo01 = MQAdapter.filter(createAccount.toString(), link01);
//        System.out.println("mq.createAccount(适配前)" + createAccount.toString());
//        System.out.println("mq.createAccount(适配后)" + JSON.toJSONString(rebateInfo01));
    }

    @Test
    public void  t2() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RebateInfo rebateInfo = new RebateInfo();
        Field field = RebateInfo.class.getField("bizTime");
        System.out.println(field.getDeclaringClass());
        Method method = RebateInfo.class.getMethod("setBizTime", field.getType());
        Object invoke = method.invoke(rebateInfo, 2L);
        System.out.println(rebateInfo);
    }
}
