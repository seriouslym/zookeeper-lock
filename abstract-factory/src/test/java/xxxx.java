import com.zjj.design.CacheService;
import com.zjj.design.factory.JDKProxy;
import com.zjj.design.factory.impl.EGMCacheAdapter;
import com.zjj.design.factory.impl.IIRCacheAdapter;
import com.zjj.design.impl.CacheServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class xxxx {

    @Test
    public void f(){
//        String t1 = "Hive1.dfsdf.ssdf@2.dsfsd"; // false
//        String t2 = "sdfsdf.sdfsdf"; // true
//        String t3 = "sdfsdf_sfsdf.sdfsdf_sdfsdf2sdfsd_sdfds"; // true
//        String t4 = "sdfsadf_sdfsdf.22dfsd_dsfsdfsdsdf2"; // true
//        String t5 = "2sdfsdf.sdfsdfsd_sdfsdA"; // false
//        String t6 = "sdfsdf.Asdfdsf"; // false
//        String t7 = "sdfsdf.sdfsd2_AAA"; // false
//        String pattern = "[a-z]+[0-9|_|a-z]+\\.[0-9|_|a-z]+";
//        boolean m1 = Pattern.matches(pattern, t1);
//        boolean m2 = Pattern.matches(pattern, t2);
//        boolean m3 = Pattern.matches(pattern, t3);
//        boolean m4 = Pattern.matches(pattern, t4);
//        boolean m5 = Pattern.matches(pattern, t5);
//        boolean m6 = Pattern.matcIIhes(pattern, t6);
//        boolean m7 = Pattern.matches(pattern, t7);
//        System.out.println(m1);
//        System.out.println(m2);
//        System.out.println(m3);
//        System.out.println(m4);
//        System.out.println(m5);
//        System.out.println(m6);
//        System.out.println(m7);
        String table = "sdfsAf.sfs";
        String pattern = ".*[A-Z]+.*";
        boolean x = Pattern.matches(pattern, table);
        System.out.println(x);


    }
    // ([a-z]+[0-9]*[a-z]*\.)*([a-z]+[0-9]*[a-z]*)*
    @Test
    public void t(){
        JDKProxy jdkProxy = new JDKProxy();
        CacheService proxy = jdkProxy.getProxy(CacheServiceImpl.class, new EGMCacheAdapter());
        proxy.set("name","zjj");
        String name = proxy.get("name");
        System.out.println(name);
    }
}

