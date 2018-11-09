package com.wp.week.service;

import com.wp.week.mapper.UserMapper;
import com.wp.week.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zl on 2015/8/27.
 */

@Service
public class UserService1 {

    @Autowired
    private UserMapper userMapper;

    public User getUserInfo(){

        User user=null;
        return user;
    }

}
