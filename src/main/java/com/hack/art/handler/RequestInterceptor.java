package com.hack.art.handler;

import com.hack.art.util.LogUtil;
import com.hack.art.util.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by ROLO on 18.02.2016.
 */
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LogUtil log;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuilder sb = new StringBuilder();
        try {
            RequestWrapper requestWrapper = new RequestWrapper(request);
            String method = requestWrapper.getMethod();
            Map<String, String[]> parameters = requestWrapper.getParameterMap();
            sb.append(";HTTP-");
            sb.append(method.toUpperCase());
            sb.append(";" + requestWrapper.getRemoteHost() + ":" + requestWrapper.getRemotePort());
            sb.append(";" + requestWrapper.getRequestURL());
            if (parameters.size() < 1) {
                sb.append(";parameters:");
                sb.append(parameters);
            }
            if (method.equals("POST")) {
                String body = requestWrapper.getBody();
                sb.append(";body:");
                sb.append(body);
            }
            log.info(null != sb ? sb.toString() : "");
        } catch (Exception e) {
            log.error(e);
        }
        return super.preHandle(request, response, handler);
    }
}

