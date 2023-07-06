package com.lx.community.mapper;

import com.lx.community.enums.CommentType;
import com.lx.community.model.Comment;
import com.lx.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentMapper {

    @Insert("insert into question (parent_id , type , commentator , gmt_create ,  gmt_modified , like_count , content ) values (#{parent_id } , #{type} , #{commentator} , #{gmt_create} ,#{gmt_modified} ,#{like_count} , #{content})")
    void insert(Comment comment);

    @Select("select * from comment where id = #{id}")
    Comment getById(@Param(value = "id")Long id);


}
