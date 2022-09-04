package com.example.templates.beans;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Container implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(Container.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // Response res = (Response) request.getAttribute("res");
        // res.setEnd(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        // response.getOutputStream().write(new ObjectMapper().writeValueAsBytes(res));
        log.info(String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()));
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Response res = new Response();
        // res.setTitle(request.getPathInfo());
        // res.setStart(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        // request.setAttribute("res", res);
        log.info(String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()));
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
    
}
