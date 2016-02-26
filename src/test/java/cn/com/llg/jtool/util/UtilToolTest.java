package cn.com.llg.jtool.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by god.luke on 2016/1/23.
 */
public class UtilToolTest {


    @Test
    public void strIsNull(){
        Assert.assertEquals(UtilTool.strIsNull("llg"),false);
    }
    @Test
    public void strIsNullDo(){
        Assert.assertEquals("luke",UtilTool.strIsNullDo(null,"luke"));
        Assert.assertEquals("god",UtilTool.strIsNullDo("god","luke"));
    }
    @Test
    public void strToPingYinLong(){
        Assert.assertEquals(UtilTool.strToPingYinLong("臭臭"),"ChouChou");
    }
    @Test
    public void strToPingYinShort(){
        Assert.assertEquals(UtilTool.strToPingYinShort("臭臭"), "CC");
    }
    @Test
    public void strToMD5(){
        Assert.assertNotEquals(UtilTool.strToMD5("luke"), "luke");
    }
    @Test
    public void stringToDate_2param(){
        Date d = UtilTool.stringToDate("2016-11-11","yyyy-MM-dd") ;
        Assert.assertEquals(UtilTool.dateToStr(d, "yyyy"),"2016");
        Assert.assertEquals(UtilTool.dateToStr(d, "MM"),"11");
        Assert.assertEquals(UtilTool.dateToStr(d, "dd"),"11");
    }
    @Test
    public void stringToDate_1param(){
        Date d = UtilTool.stringToDate("2016-11-11 00:00:59") ;
        Assert.assertEquals(UtilTool.dateToStr(d, "yyyy"),"2016");
        Assert.assertEquals(UtilTool.dateToStr(d, "MM"),"11");
        Assert.assertEquals(UtilTool.dateToStr(d, "dd"),"11");
        Assert.assertEquals(UtilTool.dateToStr(d, "HH"),"00");
    }
    @Test
    public void strToDate(){
        Date d = UtilTool.stringToDate("2016-11-11 01:12:59") ;
        Assert.assertEquals(UtilTool.dateToStr(d, "yyyy"),"2016");
    }
    @Test
    public void strToDateStart(){
        Date d = UtilTool.strToDateStart("2016-11-11") ;
        Assert.assertEquals(UtilTool.dateToStr(d, "ss"),"00");
    }
    @Test
    public void strToDateEnd(){
        Date d = UtilTool.strToDateEnd("2016-11-11") ;
        Assert.assertEquals(UtilTool.dateToStr(d, "ss"),"59");
    }
    @Test
    public void dateToStr_2param(){
        Date d = UtilTool.strToDate("2016-11-11") ;
        String str = UtilTool.dateToStr(d,"yyyy-MM-dd HH:mm:ss") ;
        Assert.assertEquals("2016-11-11 00:00:00",str);
    }
    @Test
    public void dateToStr_1param(){
        Date d = UtilTool.strToDate("2016-11-11") ;
        String str = UtilTool.dateToStr(d) ;
        Assert.assertEquals("2016-11-11",str);
    }

    @Test
    public void decimalToLong(){
        Assert.assertEquals(10000,UtilTool.decimalToLong(1.00001d),0);
    }

    @Test
    public void longToDecimal(){
        Assert.assertEquals(1,UtilTool.longToDecimal(10000l),0);
    }

    @Test
    public void decimal45(){
        Assert.assertEquals(23.235,UtilTool.decimal45(23.2345, 3),0);
    }

    @Test
    public void decimal245(){
        Assert.assertEquals(23.23,UtilTool.decimal245(23.2345),0);
    }

    @Test
    public void doubleToMoney(){
        Assert.assertEquals("￥234,321.00",UtilTool.doubleToMoney(234321d));
        Assert.assertEquals("￥234,321.22",UtilTool.doubleToMoney(234321.224d));
        Assert.assertEquals("￥234,321.23",UtilTool.doubleToMoney(234321.225d));
    }

}
