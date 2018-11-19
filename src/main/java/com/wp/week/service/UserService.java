package com.wp.week.service;

import com.wp.week.mapper.UserMapper;
import com.wp.week.model.User;
import com.wp.week.model.UserDto;
import com.wp.week.utils.AjaxList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zl on 2015/8/27.
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public AjaxList getUserInfo(Map<String,Object> map) {
        UserDto userInfo = userMapper.getUserInfo(map);
        if (userInfo != null) {
            return AjaxList.createSuccess("登陆成功");
        }
        return AjaxList.createError("账号或密码错误");
    }
}
