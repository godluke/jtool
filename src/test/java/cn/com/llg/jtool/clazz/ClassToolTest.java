package cn.com.llg.jtool.clazz;

import cn.com.llg.jtool.util.UtilTool;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by god.luke on 2016/1/23.
 */
public class ClassToolTest {



    @Test
    public void fielDescMD5() throws Exception {

        TU tu = new TU("god",100l, UtilTool.strToDate("2016-11-12")) ;
        TU t2 = new TU() ;
        t2.setDtC(UtilTool.strToDate("2016-11-12"));
        t2.setStrA("god");
        t2.setLngB(100l);
        Assert.assertEquals(ClassTool.fielDescMD5(tu), ClassTool.fielDescMD5(t2));
        
    }

    @Test
    public void mapToObject()  {
        Map<String,Object> m = new HashMap<String, Object>(3) ;
        m.put("strA","strA") ;
        m.put("lngB",1000l) ;
        m.put("dtC", UtilTool.strToDate("2016-11-11")) ;
        TU tu = (TU) ClassTool.mapToObject(m, TU.class);
        Assert.assertEquals(tu.getStrA(),"strA");
    }
}
