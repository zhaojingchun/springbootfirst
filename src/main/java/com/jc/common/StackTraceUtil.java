package com.jc.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaojingchun on 2017/9/14.
 */
public class StackTraceUtil {
    private static final Logger log = LoggerFactory.getLogger(StackTraceUtil.class);
    /**
     * 获取类名方法名
     */
    public static String getClassMethodName(int no){
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement e = stackTraceElements[no];
        return getSimpleClassName(e.getClassName())+"_"+e.getMethodName();
    }

    /***
     *  获取简单类名
     */
    public static String getSimpleClassName(String fallName){
        if(StringUtils.isEmpty(fallName)) return "";
        String simpleClassName = null;
        try {
            simpleClassName = fallName.substring(fallName.lastIndexOf(".")+1,fallName.length());
        } catch (Exception e) {
            LogUtil.error(log,e,null);
        }
        return simpleClassName;
    }

}
