package com.yuyx.servicebase.exceptionhandler;


import com.yuyx.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


    /**
     2 * 统一异常处理类
     3 */
 @ControllerAdvice
 public class GlobalExceptionHandler {
       //指定出现什么异常时执行这个方法
        @ExceptionHandler(Exception.class)
        @ResponseBody   //改注解是可以返回jaso
 public R error(Exception e){
             e.printStackTrace();
             return R.error().message("执行了全局异常..");
             }

             //特定异常
        @ExceptionHandler(ArithmeticException.class)
        @ResponseBody   //该注解是可以返回jason格式
        public R error(ArithmeticException e){
            e.printStackTrace();
            return R.error().message("执行了ArithmeticException异常处理..");
            //
        }
 }

