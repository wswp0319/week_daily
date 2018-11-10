package com.wp.week.service;

import com.wp.week.mapper.DailyMapper;
import com.wp.week.mapper.UserMapper;
import com.wp.week.model.DailyDto;
import com.wp.week.utils.AjaxList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zl on 2015/8/27.
 */

@Service
public class DailyService {

    @Autowired
    private DailyMapper dailyMapper;

    public AjaxList getDailysByRole(Map<String, Object> map) {

        List<DailyDto> dailys = dailyMapper.getDailysByRole(map);

        return AjaxList.createSuccess("获取成功", dailys);
    }

    public AjaxList delOneDaily(Integer dailyId) {

        //不处理异常情况
        dailyMapper.deleteByPrimaryKey(dailyId);
        return AjaxList.createSuccess("删除成功");
    }

    /**
     * 新增或修改周报
     * @param dailyDto
     */
    public void addOrUpdateDaily(DailyDto dailyDto) {

        Integer id = dailyDto.getId();

        if (id == null) {
            dailyMapper.insert(dailyDto);
        } else {
            dailyMapper.updateByPrimaryKey(dailyDto);
        }
    }
}
