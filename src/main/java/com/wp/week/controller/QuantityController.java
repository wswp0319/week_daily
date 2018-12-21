package com.wp.week.controller;

import com.wordnik.swagger.annotations.ApiParam;
import com.wp.week.model.QuantityDto;
import com.wp.week.service.quantityService;
import com.wp.week.utils.AjaxList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class QuantityController {

    @Autowired
    private quantityService quantityService;

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping("/getQuantitys")
    @ResponseBody
    public AjaxList getQuantitys(
            HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();


        AjaxList ajaxList = quantityService.getList(map);

        return ajaxList;
    }


    @RequestMapping("/delOneQuantity")
    @ResponseBody
    public AjaxList delOneQuantity(
            @ApiParam(name = "quantityId", value = "id", required = true) @RequestParam Integer quantityId,
            HttpServletRequest request) {

//        HttpSession session = request.getSession();
//        String username = (String) session.getAttribute("username");

        AjaxList ajaxList = quantityService.delOne(quantityId);

        return ajaxList;
    }

    //页面跳转
    @RequestMapping("/editQuantity")
    public String editquantity(
            @ApiParam(name = "quantityId", value = "id") @RequestParam(required = false) Integer quantityId,
            Model model) {

        QuantityDto quantityDto = new QuantityDto();
        quantityDto.setId(quantityId);

        if (quantityId != null) {
            quantityDto = quantityService.getById(quantityId);
        }

        model.addAttribute("quantityDto", quantityDto);

        return "page/news/quantityAdd";
    }


//    private Integer id;
//    /**粮食编号**/
//    private Integer plantNo;
//    /**植物名称**/
//    private String plantName;
//    /**类别所属**/
//    private Integer gsno;
//    /**产地分布**/
//    private String prodarea;
//    /**生态习性**/
//    private String ecolhabit;
//    /****/
//    private String imagePath;
//    /**二维码**/
//    private String dimenCode;
//    private java.util.Date createTime;
//    private java.util.Date updateTime;
//    /**是否发布**/
//    private String isState;
//    /****/
//    private String remark;

    @RequestMapping("/addOrUpdatequantity")
    public String addOrUpdatequantity(
            @ApiParam(name = "quantityId", value = "id") @RequestParam(required = false) Integer quantityId,
            @ApiParam(name = "checker", value = "工作成果", required = true) @RequestParam String checker,
            @ApiParam(name = "difference", value = "提交内容", required = true) @RequestParam Integer difference,
            @ApiParam(name = "gradient", value = "内容说明", required = true) @RequestParam Integer gradient,
            @ApiParam(name = "inQuantity", value = "开始时间", required = true) @RequestParam Integer inQuantity,
            @ApiParam(name = "outQuantity", value = "结束时间", required = true) @RequestParam Integer outQuantity,
            @ApiParam(name = "totalQuantity", value = "结束时间", required = true) @RequestParam Integer totalQuantity,
            @ApiParam(name = "isActive", value = "标准和要求") @RequestParam(required = false) String isActive,
            @ApiParam(name = "remark", value = "查看权限") @RequestParam(required = false) String remark,
            @ApiParam(name = "totalQuantity", value = "补救措施") @RequestParam(required = false) String isState,
            HttpServletRequest request,
            Model model) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        QuantityDto quantityDto = new QuantityDto();
        quantityDto.setId(quantityId);
        quantityDto.setChecker(checker);
        quantityDto.setDifference(difference);
        quantityDto.setGradient(gradient);
        quantityDto.setInQuantity(inQuantity);
        quantityDto.setIsActive(isActive);
        quantityDto.setOutQuantity(outQuantity);
        quantityDto.setRemark(remark);
        quantityDto.setTotalQuantity(totalQuantity);
        quantityDto.setRemark(remark);

        quantityService.addOrUpdate(quantityDto);

        return "page/news/quantityList";
    }


    @RequestMapping("/quantitys")
    public String plantInfos(
            HttpServletRequest request) {
        return "page/news/quantityList";
    }
}
