package com.imooc.manager.Error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一错误处理
 */
@ControllerAdvice(basePackages = {"com.imooc.manager.controller"})
public class ErrorControllerAdvice {


    @ExceptionHandler(Exception.class)
   public ResponseEntity handlEexception(Exception e){
        Map<String,Object> attrs = new HashMap<>();
        String errorEnum = e.getMessage();
        return null;
        //未写完

    }




}
