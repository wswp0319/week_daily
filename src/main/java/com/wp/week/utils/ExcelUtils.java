package com.wp.week.utils;




import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class ExcelUtils {

    private static XSSFSheet createTitle(XSSFWorkbook workbook,String deptName,int rowIndex){
        XSSFSheet sheet = null;
        switch (deptName){
            case "1":
                sheet = workbook.createSheet("接口组");
                break;
            case "2":
                sheet = workbook.createSheet("测试&运维组");
                break;
            case "3":
                sheet = workbook.createSheet("后台组");
                break;
            case "4":
                sheet = workbook.createSheet("产品组");
                break;
            case "5":
                sheet = workbook.createSheet("APP组");
                break;
            case "6":
                sheet = workbook.createSheet("特殊组");
                break;
        }
        sheet.setColumnWidth(0,15000);
        sheet.setColumnWidth(1,6000);
        sheet.setColumnWidth(2,6000);
        sheet.setColumnWidth(3,6000);
        sheet.setColumnWidth(4,6000);
        sheet.setColumnWidth(5,6000);
        sheet.setColumnWidth(6,15000);
        sheet.setColumnWidth(7,6000);
        sheet.setColumnWidth(8,6000);
        sheet.setColumnWidth(9,6000);
        sheet.setColumnWidth(10,6000);
        XSSFCellStyle setBorder = workbook.createCellStyle();
        setBorder.setAlignment(XSSFCellStyle.ALIGN_CENTER);//左右居中
        setBorder.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//上下居中
        XSSFFont font=workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 14);// 设置字体大小
        setBorder.setFont(font);
        Row row = sheet.createRow(rowIndex++);
        Cell cell0 = row.createCell(0);
        row.setHeight((short) 400);
        cell0.setCellValue("工作内容");
        cell0.setCellStyle(setBorder);
        Cell cell1 = row.createCell(1);
        cell1.setCellValue("提交内容");
        cell1.setCellStyle(setBorder);
        Cell cell2 = row.createCell(2);
        cell2.setCellValue("内容说明");
        cell2.setCellStyle(setBorder);
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("计划开始日期");
        cell3.setCellStyle(setBorder);
        Cell cell4 = row.createCell(4);
        cell4.setCellValue("计划完成日期");
        cell4.setCellStyle(setBorder);
        Cell cell5 = row.createCell(5);
        cell5.setCellValue("完成情况");
        cell5.setCellStyle(setBorder);
        Cell cell6 = row.createCell(6);
        cell6.setCellValue("过程成果或演示地址");
        cell6.setCellStyle(setBorder);
        Cell cell7 = row.createCell(7);
        cell7.setCellValue("标准和要求");
        cell7.setCellStyle(setBorder);
        Cell cell8 = row.createCell(8);
        cell8.setCellValue("未达标补救措施");
        cell8.setCellStyle(setBorder);
        Cell cell9 = row.createCell(9);
        cell9.setCellValue("提交人");
        cell9.setCellStyle(setBorder);
        Cell cell10 = row.createCell(10);
        cell10.setCellValue("备注");
        cell10.setCellStyle(setBorder);
        return sheet;
    }

    private static void createCell(List<Map<String, Object>> dataList, XSSFSheet xssfSheet, XSSFWorkbook xssfWorkbook, int rowIndex){
        Iterator<Map<String, Object>> iterator = dataList.iterator();
        while (iterator.hasNext()) {
            XSSFCellStyle setBorder2 = xssfWorkbook.createCellStyle();
            setBorder2.setAlignment(XSSFCellStyle.ALIGN_CENTER);//左右居中
            setBorder2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//上下居中
            Map<String, Object> next = iterator.next();
            XSSFRow row = xssfSheet.createRow(rowIndex++);
            String workResult = next.get("workResult") == null ? "" : next.get("workResult").toString();
            String submitContent = next.get("submitContent") == null ? "" : next.get("submitContent").toString();
            String contentDescription = next.get("contentDescription") == null ? "" : next.get("contentDescription").toString();
            String planStartDate = next.get("planStartDate") == null ? "" : next.get("planStartDate").toString();
            String planEndDate = next.get("planEndDate") == null ? "" : next.get("planEndDate").toString();
            String workSchedule = next.get("workSchedule") == null ? "" : next.get("workSchedule").toString();
            String demoAddress = next.get("demoAddress") == null ? "" : next.get("demoAddress").toString();
            String claim = next.get("claim") == null ? "" : next.get("claim").toString();
            String planB = next.get("planB") == null ? "" : next.get("planB").toString();
            String submitter = next.get("submitter") == null ? "" : next.get("submitter").toString();
            String remarks = next.get("remarks") == null ? "" : next.get("remarks").toString();
            XSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(workResult);
            cell0.setCellStyle(setBorder2);
            XSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(submitContent);
            cell1.setCellStyle(setBorder2);
            XSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(contentDescription);
            cell2.setCellStyle(setBorder2);
            XSSFCell cell3 = row.createCell(3);
            cell3.setCellValue(planStartDate);
            cell3.setCellStyle(setBorder2);
            XSSFCell cell4 = row.createCell(4);
            cell4.setCellValue(planEndDate);
            cell4.setCellStyle(setBorder2);
            XSSFCell cell5 = row.createCell(5);
            cell5.setCellStyle(setBorder2);
            XSSFCell cell6 = row.createCell(6);
            cell6.setCellStyle(setBorder2);
            XSSFCell cell7 = row.createCell(7);
            cell7.setCellStyle(setBorder2);
            XSSFCell cell8 = row.createCell(8);
            cell8.setCellStyle(setBorder2);
            XSSFCell cell9 = row.createCell(9);
            cell9.setCellStyle(setBorder2);
            XSSFCell cell10 = row.createCell(10);
            cell10.setCellStyle(setBorder2);
            cell5.setCellValue(workSchedule);
            cell6.setCellValue(demoAddress);
            cell7.setCellValue(claim);
            cell8.setCellValue(planB);
            cell9.setCellValue(submitter);
            cell10.setCellValue(remarks);
        }
    }

    public static void writeDataToFile(File file, List<Map<String, Object>> dataList) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Map<String, List<Map<String, Object>>> deptMap=new HashMap<>();
        for(Map<String, Object> map :dataList){
            if(deptMap.containsKey(map.get("dept").toString())){
                deptMap.get(map.get("dept").toString()).add(map);
            }else{
                List<Map<String, Object>> list=new LinkedList<>();
                list.add(map);
                deptMap.put(String.valueOf(map.get("dept")),list);
            }
        }
        Set<String> keySet = deptMap.keySet();
        for(String str: keySet){
            XSSFSheet sheet = createTitle(workbook,str,0);
            int rowIndex = 1;
            createCell(deptMap.get(str),sheet,workbook,rowIndex);
        }
        //将excel内存对象写入文件中
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();
    }
}