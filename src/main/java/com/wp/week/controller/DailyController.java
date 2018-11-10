package com.wp.week.controller;

import com.wordnik.swagger.annotations.ApiParam;
import com.wp.week.model.DailyDto;
import com.wp.week.model.MenuDto;
import com.wp.week.service.DailyService;
import com.wp.week.utils.AjaxList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class DailyController {

    @Autowired
    private DailyService dailyService;

    /**
     * 先查所有，权限最后设计
     *
     * @param request
     * @return
     */
    @RequestMapping("/getDailys")
    @ResponseBody
    public AjaxList goTables(
            HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();


        //管理权限直接匹配查看权限,时间查询==+
        map.put("userId", 1);
        AjaxList ajaxList = dailyService.getDailysByRole(map);

        return ajaxList;
    }

    @RequestMapping("/delOneDaily")
    @ResponseBody
    public AjaxList delOneDaily(
            @ApiParam(name = "dailyId", value = "用户名", required = true) @RequestParam Integer dailyId,
            HttpServletRequest request) {

        AjaxList ajaxList = dailyService.delOneDaily(dailyId);

        return ajaxList;
    }

    //页面跳转
    @RequestMapping("/editDaily")
    public String editDaily(
            @ApiParam(name = "dailyId", value = "id") @RequestParam(required = false) Integer dailyId,
            Model model) {

        System.out.println("*************" + dailyId);
        model.addAttribute("dailyId", dailyId);

        return "page/news/newsAdd";
    }

    /**
     * id   id,
     * work_result   workResult,
     * submit_content   submitContent,
     * content_description   contentDescription,
     * plan_start_date   planStartDate,
     * plan_end_date   planEndDate,
     * work_schedule   workSchedule,
     * demo_address   demoAddress,
     * claim   claim,
     * plan_b   planB,
     * submitter   submitter,
     * remarks   remarks,
     * <p>
     * update_time   updateTime,
     * create_time   createTime
     */
    //add or update
    @RequestMapping("/addOrUpdateDaily")
    public String addOrUpdateDaily(
            @ApiParam(name = "dailyId", value = "id") @RequestParam(required = false) Integer dailyId,
            @ApiParam(name = "workResult", value = "工作成果", required = true) @RequestParam String workResult,
            @ApiParam(name = "submitContent", value = "提交内容", required = true) @RequestParam String submitContent,
            @ApiParam(name = "contentDescription", value = "内容说明", required = true) @RequestParam String contentDescription,
            @ApiParam(name = "planStartDate", value = "开始时间", required = true) @RequestParam String planStartDate,
            @ApiParam(name = "planEndDate", value = "结束时间", required = true) @RequestParam String planEndDate,
            @ApiParam(name = "workSchedule", value = "完成情况", required = true) @RequestParam String workSchedule,
            @ApiParam(name = "demoAddress", value = "演示地址", required = true) @RequestParam String demoAddress,
            @ApiParam(name = "claim", value = "标准和要求", required = true) @RequestParam Integer claim,
            @ApiParam(name = "planB", value = "补救措施") @RequestParam(required = false) String planB,
            @ApiParam(name = "remarks", value = "备注", required = true) @RequestParam(required = false) String remarks,
            Model model) {

        DailyDto dailyDto = new DailyDto();
        dailyDto.setId(dailyId);
        dailyDto.setWorkResult(workResult);
        dailyDto.setSubmitContent(submitContent);
        dailyDto.setContentDescription(contentDescription);
        dailyDto.setPlanStartDate(planStartDate);
        dailyDto.setPlanEndDate(planEndDate);
        dailyDto.setWorkSchedule(workSchedule);
        dailyDto.setDemoAddress(demoAddress);
        dailyDto.setClaim(claim);
        dailyDto.setPlanB(planB == null ? "无" : planB);
        dailyDto.setRemarks(remarks == null ? "无" : remarks);

        dailyService.addOrUpdateDaily(dailyDto);

        return "page/news/newsList";
    }


}
