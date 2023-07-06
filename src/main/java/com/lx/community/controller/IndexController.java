package com.lx.community.controller;

import com.lx.community.dto.PaginationDto;
import com.lx.community.dto.QuestionDto;
import com.lx.community.mapper.QuestionMapper;
import com.lx.community.mapper.UserMapper;
import com.lx.community.model.Question;
import com.lx.community.model.User;
import com.lx.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model , HttpServletRequest request ,
                        @RequestParam(name = "page" , defaultValue = "1") Integer page ,
                        @RequestParam(name = "size" , defaultValue = "5") Integer size )
    {

        PaginationDto paginationDto= questionService.getQuestionList(page, size);
        model.addAttribute("paginationDto", paginationDto);
        return "index";
    }




}