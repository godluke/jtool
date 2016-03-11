package cn.com.llg.jtool.exception;

/**
 * Created by god.luke on 2016/1/23.
 */
public class RunTime extends RuntimeException {

	private static final long serialVersionUID = -3762718905039622389L;
	
	RunTimeType type ;

    public enum RunTimeType{
        DataAlter,
        PageAlter,
        DevAlert
    }

    public static RunTime create(String msg,RunTimeType type){
        RunTime rt = new RunTime(msg) ;
        rt.type = type ;
        return rt ;
    }

    public static RunTime create(String msg){
        RunTime rt = new RunTime(msg) ;
        rt.type = RunTimeType.PageAlter ;
        return rt ;
    }

    private RunTime(String message) {
        super(message);
    }
}
