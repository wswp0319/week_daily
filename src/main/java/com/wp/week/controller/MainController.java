package com.wp.week.controller;

import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping("/login")
    public String login(
            @ApiParam(name = "username", value = "用户名", required = true) @RequestParam String username,
            @ApiParam(name = "pwd", value = "签名", required = true) @RequestParam String pwd,
            HttpServletRequest request) {
//        model.addAttribute("name", name);
        System.out.println("----------------------------------------------------");
        System.out.println(username);
        System.out.println(pwd);
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(
            HttpServletRequest request) {
//        model.addAttribute("name", name);
        System.out.println("----*************************************************************");


        return "hello";
    }
    @RequestMapping("/tables")
    public String goTables(
            HttpServletRequest request) {
//        model.addAttribute("name", name);
        System.out.println("----*************************************************************");


        return "index";
    }
}
