package com.lx.community.service;

import com.lx.community.enums.CommentType;
import com.lx.community.exception.CustomizeErrorCode;
import com.lx.community.exception.CustomizeException;
import com.lx.community.mapper.CommentMapper;
import com.lx.community.mapper.QuestionMapper;
import com.lx.community.model.Comment;
import com.lx.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Transactional
    public void insert(Comment comment)
    {
        if(comment.getParent_id() == null || comment.getParent_id() == 0)
        {
            throw new CustomizeException(CustomizeErrorCode.TARGET_NOT_FOUND);
        }
        if(comment.getType() == null || CommentType.isExist(comment.getType()))
        {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_TYPE_NOT_FOUND);
        }
        if(comment.getType().equals(CommentType.COMMENT.getType()))
        {
            Comment dbComment = commentMapper.getById(comment.getParent_id());
            if(dbComment == null)
            {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);

        }else
        {
            Question dbQuestion = questionMapper.getById(comment.getParent_id());
            if(dbQuestion == null)
            {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            questionMapper.incCommentCount(dbQuestion);

        }

    }

}
