package com.jc.common;

import com.jc.domain.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by zhaojingchun on 2017/9/14.
 */
public class ResponseUtils {
    private final static Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

    public static void ajaxResponse(HttpServletRequest request, HttpServletResponse response , String jsonMsg ) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            String jsonp = request.getParameter(Constants.PARAM_JSONP);
            StringBuffer buf = new StringBuffer();
            if(response !=null && StringUtils.isNotEmpty(jsonp)){
                buf.append(jsonp);
                buf.append("(");
                buf.append(jsonMsg);
                buf.append(")");
            } else{
                buf.append(jsonMsg);
            }
            writer.write(new String(buf.toString().getBytes(),"utf-8"));
            writer.flush();
        } catch (Exception e) {
            logger.error("--ajaxResponse error--", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    logger.error("--ajaxResponse close writer error--", e);
                }
            }
        }
    }



}
