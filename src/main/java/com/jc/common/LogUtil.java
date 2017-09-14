package com.jc.common;

import org.slf4j.Logger;

/**
 * Created by zhaojingchun on 2017/9/14.
 */
public class LogUtil {
    public static String getLogClassMethodName(){
        return StackTraceUtil.getClassMethodName(4);
    }

    public static void debug(Logger log , String value){
        if(log.isDebugEnabled()){
            if(value==null) value="";
            String logStr =  String.format("%s  %s",getLogClassMethodName(),value);
            log.debug(logStr);
        }
    }

    public static void info(Logger log , String value){
        if(log.isInfoEnabled()){
            if(value==null) value="";
            String logStr =  String.format("%s  %s",getLogClassMethodName(),value);
            log.info(logStr);
        }
    }

    /**
     * 输出错误日志
     */
    public static void error(Logger log ,Exception e,String value){
        if(log.isErrorEnabled()){
            if(value==null) value="";
            String logStr =  String.format("%s ---> Exception %s %s",getLogClassMethodName(),value,e.getMessage());
            log.error(logStr,e);
        }
    }

    /**
     * 输出错误日志
     */
    public static void error(Logger log ,String value){
        if(log.isErrorEnabled()){
            if(value==null) value="";
            String logStr =  String.format("%s --->  %s",getLogClassMethodName(),value);
            log.error(logStr);
        }
    }

}
