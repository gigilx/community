package com.lx.community.service;

import com.lx.community.mapper.UserMapper;
import com.lx.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbUser = userMapper.findUserById(user.getAccount_id());
        if(dbUser == null)
        {
            userMapper.insert(user);
        }
        else{
            userMapper.update(user);
        }
    }

}
