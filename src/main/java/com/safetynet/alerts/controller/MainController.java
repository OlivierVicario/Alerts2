package com.safetynet.alerts.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
 
    @ResponseBody
    @RequestMapping(path = "/")
    public String home(HttpServletRequest request) {
    	
        String contextPath = request.getContextPath();
        String host = request.getServerName();
 
        String endpointBasePath = "/actuator";
     
        StringBuilder sb = new StringBuilder();
         
        sb.append("<h2>Spring Boot Actuator</h2>");
        sb.append("<ul>");
 
        String url = "http://" + host + ":8090" + contextPath + endpointBasePath;
 
        sb.append("<li><a href='" + url + "'>" + url + "</a></li>");
 
        sb.append("</ul>");
         
        return sb.toString();
    }
     
}
