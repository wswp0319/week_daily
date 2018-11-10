package com.wp.week.service;

import com.wp.week.mapper.MenuMapper;
import com.wp.week.mapper.UserMapper;
import com.wp.week.model.MenuDto;
import com.wp.week.utils.AjaxList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zl on 2015/8/27.
 */

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;


    public AjaxList getMenusByUid(Integer userId) {

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        List<MenuDto> menus = menuMapper.getMenusByUid(params);

        for (MenuDto menu : menus) {
            System.out.println(menu.getCreateTime());
        }
        
        return AjaxList.createSuccess("获取成功", menus);

    }
}
