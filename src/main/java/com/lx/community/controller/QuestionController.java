package com.lx.community.controller;

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
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id , Model model )
    {
        QuestionDto questionDto = questionService.getById(id);
        model.addAttribute("question" , questionDto);
        questionService.incView(id);
        return "/question";

    }

}
