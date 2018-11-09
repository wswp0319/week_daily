package com.wp.week.mapper;

import com.wp.week.model.DailyDto;

public interface DailyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DailyDto record);

    int insertSelective(DailyDto record);

    DailyDto selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DailyDto record);

    int updateByPrimaryKey(DailyDto record);
}