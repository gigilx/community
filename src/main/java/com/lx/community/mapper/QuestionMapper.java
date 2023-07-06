package com.lx.community.mapper;

import com.lx.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title , description , gmt_create, gmt_modified , creator , tag , comment_count , view_count , like_count ) values (#{title} , #{description} ,#{gmt_create} ,#{gmt_modified} ,#{creator} ,#{tag} ,#{comment_count} ,#{view_count} ,#{like_count})")
    void createQuestion(Question q);

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> getQuestionList(@Param(value = "offset") Integer offset , @Param(value = "size")Integer size);

    @Select("select count(*) from question")
    Integer count();

    @Select("select * from question where creator = #{creator} limit #{offset}, #{size} ")
    List<Question> getQuestionListById(@Param(value = "creator") Long creator , @Param(value = "offset") Integer offset , @Param(value = "size")Integer size);

    @Select("select count(*) from question where creator = #{creator}")
    Integer countById(@Param(value = "creator") Long creator);

    @Select("select * from question where id = #{id}")
    Question getById(@Param(value = "id")Long id);

    @Update("update question set title = #{question.title} , description = #{question.description} , tag = #{question.tag} , gmt_modified = #{question.gmt_modified}  , comment_count = #{question.comment_count} , view_count = #{question.view_count} ,like_count = #{question.like_count} where id = #{question.id}")
    void updateQuestion( @Param(value = "question") Question question);

    @Update("update question set view_count = view_count+1  where id = #{question.id}")
    void incViewCount(@Param(value = "question")Question question);

    @Update("update question set comment_count = comment_count+1  where id = #{id}")
    void incCommentCount(Question question);
}
