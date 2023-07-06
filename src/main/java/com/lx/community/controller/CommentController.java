package com.lx.community.controller;

import com.lx.community.dto.CommentDto;
import com.lx.community.dto.ResultDto;
import com.lx.community.exception.CustomizeErrorCode;
import com.lx.community.model.Comment;
import com.lx.community.model.User;
import com.lx.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment" , method = RequestMethod.POST )
    public Object post(@RequestBody CommentDto commentDto , HttpServletRequest request)
    {
        User user = (User)request.getSession().getAttribute("user");
        if(user == null)
        {
           return ResultDto.errorOf(CustomizeErrorCode.NOT_LOGIN );
        }
        Comment comment = new Comment();
        comment.setParent_id(commentDto.getParent_id());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setGmt_create(System.currentTimeMillis());
        comment.setGmt_modified(comment.getGmt_create());
        comment.setLike_count(0L);
        comment.setCommentator(user.getAccount_id());
        commentService.insert(comment);
        return ResultDto.okOf();

    }


}
