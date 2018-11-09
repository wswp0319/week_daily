package com.wp.week.controller;

import com.wordnik.swagger.annotations.ApiParam;
import com.wp.week.model.MenuDto;
import com.wp.week.service.MenuService;
import com.wp.week.utils.AjaxList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 根据用户权限返回对应的菜单
     */
    @RequestMapping("/getMenus")
    @ResponseBody
    public AjaxList getMenu(
            @ApiParam(name = "userId", value = "用户id", required = true) @RequestParam Integer userId,
            HttpServletRequest request) {

        return menuService.getMenusByUid(userId);

    }
}
