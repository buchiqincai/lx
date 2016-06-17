package com.lx.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import com.lx.dao.UserDao;
import com.lx.entity.User;
import com.lx.service.POIService;
import com.lx.util.DateUtils;

/**
 * poi 处理
 * @author lx
 *
 */
@Service("pOIService")
public class POIServiceImpl implements POIService {
	@Resource
	private UserDao userDao;

	@Override
	public HSSFWorkbook exportExcel(Timestamp startTime, Timestamp endTime) throws Exception {
		/**
		 * 创建步骤：
		 * 第一步：创建一个webbook，对应的是一个Excel
		 * 第二步：在webbook添加一个sheet，对应的是Excel中的sheet
		 * 第三步：在sheet中添加第0行，（老版本poi要求的行数列数为short）
		 * 第四步：创建单元格
		 * 第五步：插入数据
		 */
		//第一步ss
		HSSFWorkbook work=new HSSFWorkbook();
		//第二步
		HSSFSheet sheet=work.createSheet("用户列表");
		//第三步,老版本poi要求的行数列数为short
		HSSFRow row=sheet.createRow(0);
		//第四步，设置表头居中
		HSSFCellStyle style=work.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCell cell =row.createCell(0);	
		cell.setCellValue("用户名");
		cell.setCellStyle(style);
		
		cell=row.createCell(1);	
		cell.setCellValue("密码");
		cell.setCellStyle(style);
		
		cell=row.createCell(2);	
		cell.setCellValue("时间");
		cell.setCellStyle(style);
		
		Map<String, Timestamp> map=new HashMap<String,Timestamp>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<User> users=userDao.selectUserByTime(map);
		if(users !=null && users.size() >0){
			for (int i = 0; i < users.size(); i++) {
				User user=users.get(i);
				row=sheet.createRow(i+1);
				row.createCell(0).setCellValue(user.getLoginName());
				row.createCell(1).setCellValue(user.getPassword());
				row.createCell(2).setCellValue(DateUtils.formatDay(user.getCrrateTime()));
			}
		}
		return work;
	}

}
