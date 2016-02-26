package cn.com.llg.jtool.action;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * 请求返回数据通用格式
 * Created by god.luke on 2016/1/23.
 */
public class JsonResult {
    private static Logger log = Logger.getLogger(JsonResult.class) ;

    public enum JsonResultOverType{
        success,
        fail
    }

    private JsonResult(){} ;

    public static JsonResult create(HttpServletRequest request,HttpServletResponse response,String working,String userName,String userLocation){
        log.debug("================JsonResult create================ ");
        JsonResult jr = new JsonResult() ;
        jr.startTime = new Date().getTime() ;
        jr.working = working ;
        jr.overType = JsonResultOverType.success ;
        jr.url = request.getRequestURI() ;
        jr.param = request.getParameterMap() ;
        jr.userName = userName ;
        jr.userLocation = userLocation ;
        return jr ;
    }

    public void setSuccess(Object Data){
        this.endTime = new Date().getTime() ;
        this.data = data ;
    }

    public void setFail(Exception e){
        log.info(e.getMessage());
        this.endTime = new Date().getTime() ;
        this.e = e ;
        this.fialMsg = e.getMessage() ;
    }

    String fialMsg ;
    Exception e ;
    Long startTime ;
    Long endTime ;
    String working ;
    Map<String, String[]> param ;
    String url ;
    JsonResultOverType overType ;
    /**返回数据*/
    Object data ;
    /**操作人姓名*/
    String userName ;
    /**操作人在哪里操作的*/
    String userLocation ;
    HttpServletRequest request ;
    HttpServletResponse response ;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public Map<String, String[]> getParam() {
        return param;
    }

    public void setParam(Map<String, String[]> param) {
        this.param = param;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JsonResultOverType getOverType() {
        return overType;
    }

    public void setOverType(JsonResultOverType overType) {
        this.overType = overType;
    }

    public Object getData() {
        return data;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getFialMsg() {
        return fialMsg;
    }

    public void setFialMsg(String fialMsg) {
        this.fialMsg = fialMsg;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }
}
