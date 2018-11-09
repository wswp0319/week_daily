package com.wp.week.mapper;

import com.wp.week.model.MenuDto;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuDto record);

    int insertSelective(MenuDto record);

    MenuDto selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuDto record);

    int updateByPrimaryKey(MenuDto record);
}