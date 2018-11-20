package com.wp.week.controller;

import com.wordnik.swagger.annotations.ApiParam;
import com.wp.week.model.MenuDto;
import com.wp.week.service.UserService;
import com.wp.week.utils.AjaxList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserService userService;


    @RequestMapping("/index")
    public String login(
            HttpServletRequest request) {
//        model.addAttribute("name", name);
        System.out.println("---------------------login-------------------------------");
//        System.out.println(username);
//        System.out.println(pwd);
//        HttpSession session = request.getSession();
//        if (session.getAttribute("username") == null) {
//            return "page/news/404";
//        }
        return "index";
    }

    @RequestMapping("/join")
    @ResponseBody
    public AjaxList join(
            @ApiParam(name = "username", value = "用户名", required = true) @RequestParam String username,
            @ApiParam(name = "password", value = "密码", required = true) @RequestParam String password,
            HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);

        AjaxList ajaxList = userService.getUserInfo(map);
        if (ajaxList.isSuccess()) {
            System.out.println("set session value");
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
        }
        return ajaxList;
    }


    @RequestMapping("/goLogin")
    public String goLogin(
            HttpServletRequest request) {
//        model.addAttribute("name", name);
        System.out.println("----**********************goLogin***************************************");

        return "redirect:login.html";
    }


    @RequestMapping("/tables")
    public String goTables(
            @ApiParam(name = "username", value = "用户名", required = true) @RequestParam String username,
            @ApiParam(name = "pwd", value = "密码", required = true) @RequestParam String pwd,
            HttpServletRequest request) {
        System.out.println("----*****************************tables********************************");
        return "index";
    }

    @RequestMapping("/news")
    public String news(
            HttpServletRequest request) {
//        model.addAttribute("name", name);
        System.out.println("----***************************news11**********************************");


        return "page/news/newsList";
    }


    @RequestMapping("/userlists")
    public String users(
            HttpServletRequest request) {
//        model.addAttribute("name", name);
        System.out.println("----***************************userlists**********************************");


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
