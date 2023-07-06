package com.lx.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(2001 , "The problem doesn't exist, why don't you change the question?"),
    TARGET_NOT_FOUND(2002 , "there is no comment selected for reply"),
    NOT_LOGIN(2003 , "user not logged in, please log in first ~"),
    COMMENT_TYPE_NOT_FOUND(2004 , "The type of comment doesn't exist..."),
    COMMENT_NOT_FOUND(2005 , "The comment doesn't exist, why don't you change the comment to reply?"),
    SYS_ERROR(4001 , "An error occurred in the system...");

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code , String message)
    {
        this.code = code ;
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
