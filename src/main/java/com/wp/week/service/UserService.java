package com.wp.week.service;

import com.wp.week.mapper.UserMapper;
import com.wp.week.model.User;
import com.wp.week.model.UserDto;
import com.wp.week.model.UserVO;
import com.wp.week.utils.AjaxList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zl on 2015/8/27.
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public AjaxList getUserInfo(Map<String, Object> map) {
        UserDto userInfo = userMapper.getUserInfo(map);
        if (userInfo != null) {
            return AjaxList.createSuccess("登陆成功");
        }
        return AjaxList.createError("账号或密码错误");
    }

    public AjaxList getUsers() {

        List<UserVO> users = userMapper.getUsers();

        return AjaxList.createSuccess("获取成功", users);
    }

    public UserDto getDailysById(Integer userId) {

        UserDto userDto = userMapper.selectByPrimaryKey(userId);
        return userDto;
    }

    public void addOrUpdateUser(UserDto userDto) {
        /**
         *         Integer id = dailyDto.getId();
         *         Date date = new Date();
         *         if (id == null) {
         *             dailyDto.setCreateTime(date);
         *             dailyDto.setUpdateTime(date);
         *             dailyMapper.insert(dailyDto);
         *         } else {
         *             dailyDto.setUpdateTime(date);
         *             dailyMapper.updateByPrimaryKey(dailyDto);
         *         }
         */
        Integer id = userDto.getId();
        Date date = new Date();
        if (id == null) {
            UserDto userByName = userMapper.getUserByName(userDto.getUsername());
            if (userByName !=null) {
                return;
            }
            userDto.setPassword("123456");
            userDto.setCreateTime(date);
            userDto.setUpdateTime(date);
            userMapper.insert(userDto);
        } else {
            userDto.setUpdateTime(date);
            userMapper.updateByPrimaryKey(userDto);
        }
    }

    public AjaxList delOneUser(Integer userId) {

        userMapper.deleteByPrimaryKey(userId);
        return AjaxList.createSuccess("删除成功");
    }
}
