package com.lx.community.mapper;

import com.lx.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user( account_id , name , token , gmt_create , gmt_modified , avatar_url) values ( #{account_id} , #{name}  ,  #{token} , #{gmt_create} , #{gmt_modified} , #{avatar_url})")
     void insert(User user);

    @Select("select * from user where token = #{token}")
    User findUser(@Param("token") String token);

    @Select("select * from user where account_id = #{account_id}")
    User findUserById(Long account_id);

    @Update("update user set token = #{user.token} , gmt_modified = #{user.gmt_modified} , name = #{user.name} , avatar_url = #{user.avatar_url} where account_id = #{user.account_id}")
    void update(@Param("user") User user);
}
