package cn.com.llg.jtool.util;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class UtilTool {

    private static Logger log = Logger.getLogger(UtilTool.class) ;

    /**
     * 字符串是否为空
     * @param str
     * @return
     */
    public static Boolean strIsNull(String str){
        log.debug("strIsNull param is " + str);
        if(str==null||str.trim().equals("")){
            return true ;
        }else{
            return false ;
        }
    }

    /**
     * le
     * @param str
     * @param d
     * @return
     */
    public static String strIsNullDo(String str,String d){
        log.debug("strIsNullDo 1param is "+str);
        log.debug("strIsNullDo 2param is " + d);
        if(strIsNull(str))
            return d ;
        else
            return str ;
    }

    /**
     * 名字转拼音 全拼
     *
     * @param name
     * @return String
     * @author llg
     */
    public static String strToPingYinLong(String name) {
        log.debug("strToPingYinLong param is "+name);

        if (name == null || name.trim().equals(""))
            return "";
        String rt = "";
        HanyuPinyinOutputFormat hypyFormate = new HanyuPinyinOutputFormat();
        hypyFormate.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hypyFormate.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hypyFormate.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (int i = 0; i < name.length(); i++) {
                String[] ss = PinyinHelper.toHanyuPinyinStringArray(
                        name.charAt(i), hypyFormate);
                if (ss == null || ss[0].length() <= 0) {
                    rt += name.charAt(i);
                    continue;
                }
                rt += ss[0].substring(0, 1).toUpperCase()
                        + ss[0].substring(1, ss[0].length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rt;
    }

    /**
     * 名字转拼音 简拼（首字母）
     *
     * @param name
     * @return String
     * @author llg
     */
    public static String strToPingYinShort(String name) {
        log.debug("strToPingYinShort param is "+name);

        if (name == null || name.trim().equals(""))
            return "";
        String rt = "";
        for (int i = 0; i < name.length(); i++) {
            String[] ss = PinyinHelper.toHanyuPinyinStringArray(name.charAt(i));
            if (ss == null || ss[0].length() <= 0) {
                rt += name.charAt(i);
                continue;
            }

            rt += ss[0].substring(0, 1).toUpperCase();
        }
        if(rt.length()>=36){
            rt = rt.substring(0,35) ;
        }

        return rt;
    }

    /**
     * md5加密（网上找的，应该靠谱）
     *
     * @param s
     * @return String
     * @author llg
     */
    public final static String strToMD5(String s) {
        log.debug("strToMD5 param is "+s);

        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 字符串转时间,按规定formate格式转
     *
     * @param time
     * @param format
     * @return Date
     * @throws Exception
     */
    public static Date stringToDate(String time, String format) {
        log.debug("stringToDate 1param is "+time);
        log.debug("stringToDate 2param is "+format);

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 字符串转时间,按规定yyyy-MM-dd HH:mm:ss格式转
     *
     * @param time
     * @return Date
     * @throws Exception
     */
    public static Date stringToDate(String time) {
        return stringToDate(time, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 字符串转时间,按规定yyyy-MM-dd格式转
     *
     * @param time
     * @return Date
     * @throws Exception
     */
    public static Date strToDate(String time) {
        return stringToDate(time, "yyyy-MM-dd");
    }

    /**
     * 字符串转时间,按规定yyyy-MM-dd HH:mm:ss格式转 yyyy-MM-dd 00:00：00
     *
     * @param time
     * @return Date
     * @throws Exception
     */
    public static Date strToDateStart(String time) {
        return stringToDate(time + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
    }
    /**
     *字符串转时间,按规定yyyy-MM-dd HH:mm:ss格式转 yyyy-MM-dd 23:59：59
     *
     * @param time
     * @return Date
     * @throws Exception
     */
    public static Date strToDateEnd(String time) {
        return stringToDate(time + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 时间转字符串
     * @param d
     * @return
     */
    public static String dateToStr(Date d,String format){

        log.debug("stringToDate 1param is "+d.toString());
        log.debug("stringToDate 2param is "+format);

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }

    /**
     * 时间转字符串
     * @param d
     * @return
     */
    public static String dateToStr(Date d){
        log.debug("dateToStr param is "+d.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);

    }


    private static int t7 = 10000 ;

    /**
     * 小数转为10000倍数据
     */
    public static Long decimalToLong(Double num){
        log.debug("decimalToLong param is "+num);
       return Math.round(num * t7) ;
    }

    /**
     * 小数据转化10000倍后再转回来，保留两们小数据，（四舍五入）
     * @param num
     * @return
     */
    public static Double longToDecimal(Long num){
        log.debug("longToDecimal param is "+num);
        NumberFormat nf= NumberFormat.getNumberInstance() ;
        nf.setMaximumFractionDigits(2);
        Double dNum = Double.parseDouble(num.toString()) ;
        return Double.parseDouble(nf.format(dNum / t7)) ;
    }

    /**
     * 四舍五入n位小数
     * @param num
     * @param n
     * @return
     */
    public static Double decimal45(Double num,int n){
        log.debug("decimal45 param1 is "+num);
        log.debug("decimal45 param2 is " + n);
        NumberFormat nf= NumberFormat.getNumberInstance() ;
        nf.setMaximumFractionDigits(n);
        return Double.parseDouble(nf.format(num)) ;
    }
    /**
     * 四舍五入2位小数
     * @param num
     * @return
     */
    public static Double decimal245(Double num){
        log.debug("decimal245 param is "+num);
        NumberFormat nf= NumberFormat.getNumberInstance() ;
        nf.setMaximumFractionDigits(2);
        return Double.parseDouble(nf.format(num)) ;
    }

    /**
     * Double数字转为钱币形式字符串
     * @param num
     * @return
     */
    public static String doubleToMoney(Double num){
        log.debug("doubleToMoney param is " + num);
        NumberFormat nf = new DecimalFormat("￥,###.00");
        return nf.format(num);
    }

    /**
     * 读取properties文件，返回Properties类
     * @param propertiesPath
     * @return
     */
    public static Properties readProperties(String propertiesPath){
        InputStream is = null;
        Properties prop = new Properties();
        try {
            // 使用Class的方法，获取配置文件的输入流
            is = UtilTool.class.getResourceAsStream(propertiesPath);
            // 读取文件
            prop.load(is);// 读取配置文件中的键值对，直接存到prop集合中
            return prop ;
        } catch (Exception e) {
            e.printStackTrace();// 打印异常堆栈信息
            throw new ExceptionInInitializerError(e);// 手动抛出异常
        } finally {
            try {
                if (is != null)
                    is.close();//关闭流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 时间转年龄
     * @param borthDate
     * @return int
     * @author llg
     */
    public static int DateToAge(Date borthDate) {
        String now = dateToStr(new Date());
        String borth = dateToStr(borthDate);
        int rt = Integer.parseInt(now.substring(0, 4))
                - Integer.parseInt(borth.substring(0, 4));
        return rt;
    }

    /**
     * 时间转年龄
     * @param borthDate
     * @return int
     * @author llg
     */
    public static int DateToAge(String borthDate) {
        String now = dateToStr(new Date());
        String borth = dateToStr(strToDate(borthDate));
        int rt = Integer.parseInt(now.substring(0, 4))
                - Integer.parseInt(borth.substring(0, 4));
        return rt;
    }
}
