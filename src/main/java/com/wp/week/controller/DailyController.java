package com.wp.week.controller;

import com.wordnik.swagger.annotations.ApiParam;
import com.wp.week.model.DailyDto;
import com.wp.week.service.DailyService;
import com.wp.week.utils.AjaxList;
import com.wp.week.utils.DateUtil;
import com.wp.week.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            @ApiParam(name = "dept", value = "分组") @RequestParam(required = false) Integer dept,
            @ApiParam(name = "planStartDate", value = "开始时间") @RequestParam(required = false) String planStartDate,
            @ApiParam(name = "planEndDate", value = "结束时间") @RequestParam(required = false) String planEndDate,
            HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();


        //管理权限直接匹配查看权限,时间查询==+
        map.put("userId", 1);
        if (dept != null && dept != -1) {
            map.put("dept", dept);
        }
        if (!StringUtils.isEmpty(planStartDate)) {
            map.put("planStartDate", planStartDate);
        }
        if (!StringUtils.isEmpty(planEndDate)) {
            map.put("planEndDate", planEndDate);
        }

        AjaxList ajaxList = dailyService.getDailysByRole(map);

        return ajaxList;
    }


    @RequestMapping("/delOneDaily")
    @ResponseBody
    public AjaxList delOneDaily(
            @ApiParam(name = "dailyId", value = "id", required = true) @RequestParam Integer dailyId,
            HttpServletRequest request) {

        AjaxList ajaxList = dailyService.delOneDaily(dailyId);

        return ajaxList;
    }

    //页面跳转
    @RequestMapping("/editDaily")
    public String editDaily(
            @ApiParam(name = "dailyId", value = "id") @RequestParam(required = false) Integer dailyId,
            Model model) {

        DailyDto dailyDto = new DailyDto();
        dailyDto.setId(dailyId);
        if (dailyId != null) {
            dailyDto = dailyService.getDailysById(dailyId);
        }

        model.addAttribute("dailyDto", dailyDto);

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
            @ApiParam(name = "claim", value = "标准和要求") @RequestParam(required = false) String claim,
            @ApiParam(name = "planB", value = "补救措施") @RequestParam(required = false) String planB,
            @ApiParam(name = "lookRole", value = "查看权限") @RequestParam(required = false) Integer lookRole,
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
        dailyDto.setClaim(claim == "on" ? 1 : 0);
        dailyDto.setLookRole(lookRole);
        dailyDto.setPlanB(planB == null ? "无" : planB);
        dailyDto.setRemarks(remarks == null ? "无" : remarks);

        dailyService.addOrUpdateDaily(dailyDto);

        return "page/news/newsList";
    }

    //    updateClaim
    @RequestMapping("/updateClaim")
    @ResponseBody
    public AjaxList updateClaim(
            @ApiParam(name = "dailyId", value = "日报id", required = true) @RequestParam Integer dailyId,
            @ApiParam(name = "claim", value = "合格状态(1:OK,0:NG)", required = true) @RequestParam Integer claim,
            HttpServletRequest request) {

        AjaxList ajaxList = dailyService.updateClaim(dailyId, claim);

        return ajaxList;
    }

    @RequestMapping("/download")
    public void download(
            @ApiParam(name = "workSchedule", value = "分组") @RequestParam(required = false) Integer workSchedule,
            @ApiParam(name = "planStartDate", value = "开始时间") @RequestParam(required = false) String planStartDate,
            @ApiParam(name = "planEndDate", value = "结束时间") @RequestParam(required = false) String planEndDate,
            HttpServletResponse response,
            HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();


        //管理权限直接匹配查看权限,时间查询==+
        map.put("userId", 1);
        if (workSchedule != null && workSchedule != -1) {
            map.put("dept", workSchedule);
        }
        if (!StringUtils.isEmpty(planStartDate)) {
            map.put("planStartDate", planStartDate);
        }
        if (!StringUtils.isEmpty(planEndDate)) {
            map.put("planEndDate", planEndDate);
        }

//        AjaxList ajaxList = dailyService.getDailysByRole(map);
//        List<DailyDto> dailyDtos = (List<DailyDto>) ajaxList.getData();


//        String dept = request.getParameter("dept");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        String date = DateUtil.getMondayDayStr(new Date()) + "-" + DateUtil.getSaturdayStr(new Date());

        File file = new File("研发部每周工作计划及完成情况" + date + ".xlsx");

//        Map<String, Object> params = new HashMap<>();
//        if (dept != null) {
//            params.put("dept", Integer.parseInt(dept));
//        }


//        List<Map<String, Object>> dataList = workResultService.findAll(params);

//        AjaxList ajaxList = dailyService.getDailysByRole(map);
        List<Map<String, Object>> dataList = dailyService.getDailysExcel(map);

        try {
            ExcelUtils.writeDataToFile(file, dataList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        System.out.println("mimetype : " + mimeType);

        response.setContentType(mimeType);

        try {
            response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(file.getName().getBytes("gb2312"), "ISO8859-1") + "\"");


        response.setContentLength((int) file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
