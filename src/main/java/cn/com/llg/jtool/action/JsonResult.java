package cn.com.llg.jtool.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 请求返回数据通用格式
 * Created by god.luke on 2016/1/23.
 */
public class JsonResult {
    private static Logger log = Logger.getLogger(JsonResult.class) ;
    
    
    HttpServletRequest request ;
    HttpServletResponse response ;
    
    /**异常信息*/
    String fialMsg ;
    /**异常*/
    Exception e ;
    /**请求开始时间*/
    Long startTime ;
    /**请求结束时间*/
    Long endTime ;
    /**请求地址是做事件描述*/
    String working ;
    /**请求参数*/
    Map<String, String[]> param ;
    /**请求地址*/
    String url ;
    /**请求反回结果*/
    JsonResultOverType overType ;
    /**返回数据*/
    Object data ;
    /**操作人姓名*/
    String userName ;
    /**操作人id*/
    String userId ;
    /**操作人在哪里操作的*/
    String userLocation ;
    

    public enum JsonResultOverType{
        success,
        fail
    }

    private JsonResult(){} ;

    public static JsonResult create(HttpServletRequest request,HttpServletResponse response,String working,String userName,String userId,String userLocation){
        log.debug("================JsonResult create================ ");
        JsonResult jr = new JsonResult() ;
        jr.startTime = new Date().getTime() ;
        jr.working = working ;
        jr.url = request.getRequestURI() ;
        jr.param = request.getParameterMap() ;
        jr.userName = userName ;
        jr.userLocation = userLocation ;
        jr.userId = userId ;
        return jr ;
    }

	private void setSuccess(Object data){
    	log.debug("================JsonResult success================ ");
    	this.overType = JsonResultOverType.success ;
        this.endTime = new Date().getTime() ;
        this.data = data ;
    }
    /**请求成功*/
    @SuppressWarnings("unused")
	public void requestSuccess(Object data,String... jsonCfg){
    	this.setSuccess(data);
    	JSONObject jo = null ;
		if (jsonCfg != null && jsonCfg.length > 0) {
			JsonConfig jc = new JsonConfig();
			jc.setExcludes(jsonCfg);
			jo = JSONObject.fromObject(this, jc);
		} else {
			jo = JSONObject.fromObject(this);
		}
		this.responseWriteJson();
    }
    private void setFail(Exception e){
    	log.debug("================JsonResult fail================ ");
    	e.printStackTrace();
        this.overType = JsonResultOverType.fail ;
        this.endTime = new Date().getTime() ;
        this.e = e ;
        this.fialMsg = e.getMessage() ;
    }
    
    /**请求失败*/
    public void requestSuccess(Exception e){
    	this.setFail(e);
		this.responseWriteJson();
    }
    
   
    
    /**写设置好的信息*/
	public void responseWriteJson() {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/Json");
//		response.setContentType("text/html");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(this);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 写回json信息
	 * @param json
	 */
	public void responseWriteJson(JSONObject json) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/Json");
//		response.setContentType("text/html");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(json);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
