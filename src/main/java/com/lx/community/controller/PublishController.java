package com.lx.community.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {


    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name="id") Long id , Model model){
        Question question = questionMapper.getById(id);
        model.addAttribute("title" , question.getTitle());
        model.addAttribute("description" , question.getDescription());
        model.addAttribute("tag" , question.getTag());
        model.addAttribute("id" , question.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title" ) String title ,
                            @RequestParam("description" ) String description ,
                            @RequestParam("tag") String tag,
                            @RequestParam(value = "id" , required = false) Long id,
                            HttpServletRequest request,
                            Model model
                            )
    {
        if(title == null || title == "")
        {
            model.addAttribute("error" , "title is empty!");
            return "publish";
        }
        if(description == null || description == "")
        {
            model.addAttribute("error" , "description is empty!");
            return "publish";
        }
        if(tag == null || tag == "")
        {
            model.addAttribute("error" , "tag is empty!");
            return "publish";
        }
        model.addAttribute("title" , title);
        model.addAttribute("description" , description);
        model.addAttribute("tag" , tag);
        User user = (User)request.getSession().getAttribute("user");
        if(user == null)
        {
            model.addAttribute("error" , "Oh snap! Change a few things up and try submitting again.");
            return "publish" ;
        }
        else{
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setId(id);
            questionService.createOrUpdate(question , request);
        }
        return "redirect:/";

    }
}
