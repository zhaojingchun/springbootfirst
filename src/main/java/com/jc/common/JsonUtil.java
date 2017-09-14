package com.jc.common;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaojingchun on 2017/9/14.
 */
public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T getJson2ObjBase(String responseStr, Class<T> clazz, Logger log, String dateFormat){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
            objectMapper.setDateFormat(fmt);
            objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            T responseObj = objectMapper.readValue(responseStr, clazz);
            return responseObj;
        } catch (Exception e) {
            LogUtil.error(log,e,null);
        }
        return null;
    }

    /**
     * 把json字符串转化为指定对象
     */
    public static <T> T getJson2Obj(String responseStr,Class<T> clazz,Logger log){
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        return  getJson2ObjBase(responseStr, clazz, log,dateFormat );
    }

    /**
     * 把json字符串转化为指定对象
     */
    public static <T> T getJson2Obj(String responseStr, TypeReference<T> typeReference, Logger log){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            T responseObj = objectMapper.readValue(responseStr, typeReference);
            return responseObj;
        } catch (Exception e) {
            LogUtil.error(log,e,null);
        }
        return null;
    }

    /**
     * 对象转化为json
     */
    public static String getObj2JsonStr(Object obj,Logger log){
        ObjectMapper mapper = new ObjectMapper();
        String taskContent ="";
        try {
            taskContent = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            LogUtil.error(log,e,null);
        }
        return taskContent;
    }

    /**
     * Json串转换为list类型
     */
    public static <T> List<T> jsonObjectToList(String jsonStr, Class<T> clazz, Logger log){
        List<T> retList = null;
        try {
            if(StringUtils.isBlank(jsonStr)) return  retList;
            CollectionType type = TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz);
            retList = mapper.readValue(jsonStr, type);
        } catch (IOException e) {
            LogUtil.error(log,e,null);
        }
        return retList;
    }

}
