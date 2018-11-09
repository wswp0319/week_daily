package com.wp.week.controller;

import com.wordnik.swagger.annotations.ApiParam;
import com.wp.week.model.MenuDto;
import com.wp.week.utils.AjaxList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/news")
    public String news(
            HttpServletRequest request) {
//        model.addAttribute("name", name);
        System.out.println("----***************************news**********************************");


        return "page/news/newsList";
    }

    @RequestMapping("/newsAdd")
    public String newsAdd(
            HttpServletRequest request) {
//        model.addAttribute("name", name);
        System.out.println("----***************************news**********************************");


        return "page/news/newsAdd";
    }

    @RequestMapping("/users")
    public String users(
            HttpServletRequest request) {
//        model.addAttribute("name", name);
        System.out.println("----***************************news**********************************");


        return "page/user/allUsers";
    }

    @RequestMapping("/getMenu")
    @ResponseBody
    public AjaxList getMenu(
            HttpServletRequest request) {
//        model.addAttribute("name", name);
        System.out.println("----***************************getMenu**********************************");
        List list = new ArrayList();
/**
 * 	"title" : "员工管理",
 * 	"icon" : "icon-text",
 * 	"href" : "users",
 * 	"spread" : false
 */
        MenuDto menu = new MenuDto();
        menu.setTitle("后台首页");
        menu.setIcon("icon-computer");
        menu.setHref("page/main.html");
//        menu.setSpread(false);

        MenuDto menu2 = new MenuDto();
        menu2.setTitle("文章列表");
        menu2.setIcon("icon-text");
        menu2.setHref("news");
//        menu2.setSpread(false);

        MenuDto menu3 = new MenuDto();
        menu3.setTitle("员工管理");
        menu3.setIcon("&#xe64c;");
        menu3.setHref("users");
//        menu3.setSpread(false);

        MenuDto menu4 = new MenuDto();
        menu4.setTitle("友情链接");
        menu4.setIcon("&#xe64c;");
        menu4.setHref("page/links/linksList.html");
//        menu4.setSpread(false);

        MenuDto menu5 = new MenuDto();
        menu5.setTitle("系统基本参数");
        menu5.setIcon("&#xe631;");
        menu5.setHref("page/systemParameter/systemParameter.html");
//        menu5.setSpread(false);
        list.add(menu);
        list.add(menu2);
        list.add(menu3);
        list.add(menu4);
        list.add(menu5);

        AjaxList ajaxList = AjaxList.createSuccess("获取成功", list);

        return ajaxList;
    }
}
