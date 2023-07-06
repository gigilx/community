package com.lx.community.service;

import com.lx.community.dto.PaginationDto;
import com.lx.community.dto.QuestionDto;
import com.lx.community.exception.CustomizeErrorCode;
import com.lx.community.exception.CustomizeException;
import com.lx.community.mapper.QuestionMapper;
import com.lx.community.mapper.UserMapper;
import com.lx.community.model.Question;
import com.lx.community.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public  PaginationDto getQuestionList(Integer page , Integer size) {
        Integer offset = (page-1) * size ;
        List<QuestionDto> questionList = new ArrayList<>();
        List<Question> questions = questionMapper.getQuestionList(offset, size);
        PaginationDto paginationDto = new PaginationDto();
        for(Question q : questions)
        {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(q , questionDto);
            User user = userMapper.findUserById(q.getCreator());
            questionDto.setUser(user);
            questionList.add(questionDto);
        }
        paginationDto.setQuestionDtoList(questionList);
        Integer totalCount = questionMapper.count();
        paginationDto.setPagination(totalCount , page , size);
        return paginationDto;
    }
    public  PaginationDto getQuestionListById( Long account_id , Integer page , Integer size) {
        Integer offset = (page-1) * size ;
        List<QuestionDto> questionList = new ArrayList<>();
        List<Question> questions = questionMapper.getQuestionListById(account_id , offset, size);
        PaginationDto paginationDto = new PaginationDto();
        for(Question q : questions)
        {
                QuestionDto questionDto = new QuestionDto();
                BeanUtils.copyProperties(q , questionDto);
                User user = userMapper.findUserById(account_id);
                questionDto.setUser(user);
                questionList.add(questionDto);
        }
        paginationDto.setQuestionDtoList(questionList);
        Integer totalCount = questionMapper.countById(account_id);
        paginationDto.setPagination(totalCount , page , size);
        return paginationDto;
    }


    public QuestionDto getById( Long id) {
        Question question = questionMapper.getById(id);
        if(question == null)
        {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question , questionDto);
        User user = userMapper.findUserById(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }

    public void createOrUpdate(Question question , HttpServletRequest request) {
        Long id = question.getId();
        if(id == null)
        {
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(question.getGmt_create());
            User user = (User)request.getSession().getAttribute("user");
            question.setCreator(user.getAccount_id());
            questionMapper.createQuestion(question);
        }else{
            question.setGmt_modified(System.currentTimeMillis());
            questionMapper.updateQuestion(question);
        }
    }

    public void incView(Long id) {
        Question question = questionMapper.getById(id);
        questionMapper.incViewCount(question);
    }
    public void incComment(Long id) {
        Question question = questionMapper.getById(id);
        questionMapper.incCommentCount(question);
    }
}
