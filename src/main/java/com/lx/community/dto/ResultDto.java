package com.lx.community.dto;

import com.lx.community.exception.CustomizeErrorCode;
import com.lx.community.exception.CustomizeException;
import lombok.Data;

@Data
public class ResultDto {
    private Integer code;
    private String message;

    public static ResultDto errorOf(Integer code , String message)
    {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto ;
    }

    public static ResultDto errorOf(CustomizeErrorCode notLogin) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(notLogin.getCode());
        resultDto.setMessage(notLogin.getMessage());
        return resultDto ;
    }

    public static ResultDto errorOf(CustomizeException e) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(e.getCode());
        resultDto.setMessage(e.getMessage());
        return resultDto ;
    }

    public static ResultDto okOf() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("the request is successful~");
        return resultDto ;
    }
}
