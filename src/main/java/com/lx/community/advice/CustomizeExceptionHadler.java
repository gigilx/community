package com.lx.community.advice;

import com.alibaba.fastjson2.JSON;
import com.lx.community.dto.ResultDto;
import com.lx.community.exception.CustomizeErrorCode;
import com.lx.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHadler  {

    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable e , Model model , HttpServletRequest request , HttpServletResponse response) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType))
        {
            ResultDto resultDto;
            if(e instanceof CustomizeException)
            {
                resultDto = ResultDto.errorOf((CustomizeException) e);
            }else {
                resultDto = ResultDto.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = null;
                writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDto));
                writer.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return null;
        } else
        {
            //错误页面跳转
            if(e instanceof CustomizeException)
            {
                model.addAttribute("message" , e.getMessage());
            }else{
                model.addAttribute("message" , "The webpage has encountered an error,please be patient and wait.....");
            }
            return new ModelAndView("error");
        }

    }


}