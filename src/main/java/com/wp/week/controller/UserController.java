package com.wp.week.controller;

import com.wordnik.swagger.annotations.ApiParam;
import com.wp.week.model.User;
import com.wp.week.service.UserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zl on 2015/8/27.
 */
@Controller
public class UserController {

//    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService1 userService;

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo(
            @ApiParam(name = "username", value = "姓名", required = true) @RequestParam String username,
            @ApiParam(name = "pwd", value = "pwd", required = true) @RequestParam String pwd,
            HttpServletRequest request) {
        System.out.println(username);
        System.out.println(pwd);
//        User user = userService.getUserInfo();

//        if(user!=null){
//            System.out.println("user.getName():"+user.getName());
//            logger.info("user.getAge():"+user.getAge());
//        }
        return null;
    }
}
