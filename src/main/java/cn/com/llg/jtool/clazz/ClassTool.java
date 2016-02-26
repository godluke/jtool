package cn.com.llg.jtool.clazz;

import cn.com.llg.jtool.exception.RunTime;
import cn.com.llg.jtool.util.UtilTool;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类工具
 * Created by god.luke on 2016/1/23.
 */
public class ClassTool {

    private static Logger log = Logger.getLogger(ClassTool.class) ;
    
    
    /**
     * 将对象非空属性排序，再依次取属性值的字符串值进行md5编码,可以去除指定的属性
     * @param o
     * @param fields
     * @return
     */
    public static String fielDescMD5(Object o,String... fields){
        log.debug("fielDescMD5 param1 is "+o.toString());
        log.debug("fielDescMD5 param2 is "+fields.toString());
        StringBuffer fieldStr = new StringBuffer() ;
        Field[] fs = o.getClass().getDeclaredFields() ;
        List<String> strs = new ArrayList<String>(10) ;
        for(int i = 0 ;i<fs.length ;i++){
            if("this$0".equals(fs[i].getName()))
                continue;
            strs.add(fs[i].getName()) ;
        }
        String[] tmpStr = new String[strs.size()];
        strs.toArray(tmpStr) ;
        Arrays.sort(tmpStr);

        Field field = null ;
        Boolean pc = false ;
        try {
            for(String fieldName:tmpStr){
                field = o.getClass().getDeclaredField(fieldName) ;
                field.setAccessible(true) ;
                if(field.get(o)!=null){
                    pc = false ;
                    for(String s :fields){
                        if(s.equals(field.getName())){
                            pc = true ;
                            break ;
                        }
                    }
                    if(!pc)
                        fieldStr.append(field.get(o).toString()) ;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw RunTime.create("数据异常：ClassTool.fielDescMD5:"+o.getClass().getName()) ;
        }

        return UtilTool.strToMD5(fieldStr.toString()) ;
    }

    /**
     * Map数据封装成为指的类型的对像
     * @param data
     * @param clazz
     * @return
     */
    public static Object mapToObject(Map<String,Object> data,Class<?> clazz){
        Object obj = null ;
        try {
            obj = clazz.newInstance() ;
            Field field = null ;
            for(Map.Entry<String,Object> e:data.entrySet()){
                field = obj.getClass().getDeclaredField(e.getKey().toString()) ;
                field.setAccessible(true);
                field.set(obj,e.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw RunTime.create(e.getMessage(), RunTime.RunTimeType.DataAlter) ;
        }
        return obj ;
    }
    
    /**
     * 对象实例转为Map对象
     * @param obj
     * @return
     */
    public static Map<String,Object> objToMap(Object obj){
    	Field[] fields = obj.getClass().getFields() ;
    	Map<String ,Object> map = new HashMap<String, Object>(fields.length) ;
    	try {
			for(Field f:fields){
				f.setAccessible(true);
				map.put(f.getName(), f.get(obj)) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	return map ;
    }

}
