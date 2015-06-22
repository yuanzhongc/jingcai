package com.yz.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

public class ImportExcel {
	
	
	 /**
     * 查询指定目录中电子表格中所有的数据
     * @param file 文件完整路径
     * @return
     */
    public static List<Map<String, String>> getAllByExcel(String file){
    	List<Map<String, String>> list=new ArrayList<Map<String, String>>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(1);//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            
            System.out.println(clos+" rows:"+rows);
            for (int i = 6; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                	Map<String, String> map = new HashMap<String, String>();
                	map.put("name", rs.getCell(j, i).getContents());
                	System.out.println(i+"======="+rs.getCell(j++, i).getContents());
                    //第一个是列数，第二个是行数
                	map.put("jsop_win1", rs.getCell(j++, i).getContents());
                	map.put("jsop_pin1", rs.getCell(j++, i).getContents());
                	map.put("jsop_lose1", rs.getCell(j++, i).getContents());
                	map.put("jsgl_win1", rs.getCell(j++, i).getContents());
                	map.put("jsgl_pin1", rs.getCell(j++, i).getContents());
                	map.put("jsgl_lose1", rs.getCell(j++, i).getContents());
                	map.put("jsgl_rehome1", rs.getCell(j++, i).getContents());
                	map.put("jskl_win1", rs.getCell(j++, i).getContents());
                	map.put("jskl_pin1", rs.getCell(j++, i).getContents());
                	map.put("jskl_lose1", rs.getCell(j++, i).getContents());	

                	map.put("jsop_win2", rs.getCell(j++, i).getContents());
                	map.put("jsop_pin2", rs.getCell(j++, i).getContents());
                	map.put("jsop_lose2", rs.getCell(j++, i).getContents());
                	map.put("jsgl_win2", rs.getCell(j++, i).getContents());
                	map.put("jsgl_pin2", rs.getCell(j++, i).getContents());
                	map.put("jsgl_lose2", rs.getCell(j++, i).getContents());
                	map.put("jsgl_rehome2", rs.getCell(j++, i).getContents());
                	map.put("jskl_win2", rs.getCell(j++, i).getContents());
                	map.put("jskl_pin2", rs.getCell(j++, i).getContents());
                	map.put("jskl_lose2", rs.getCell(j++, i).getContents());
                    list.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return list;
        
    }
    
    public static void main(String[] args) {
		String file = "C:\\Users\\zhong\\Downloads\\FK乌法VS莫尔多维亚(俄超)欧洲数据.xls";
		getAllByExcel(file);
		
	}

}
