package com.lx.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lx.service.POIService;
import com.lx.util.DateUtil;
import com.lx.util.DateUtils;
import com.lx.util.ExportUtil;

/**
 * poi 导出excel
 * @author lx
 *
 */
@Controller
@RequestMapping("/poi/")
public class ExportExlTestController {
	
	private final Logger log=LoggerFactory.getLogger(getClass()); 
	@Resource
	private POIService pOIService;
	

	/**
	 * 导出用户列表
	 * @param request
	 * @param reponse
	 * @param startTime 开始时间
	 * @param endTime 结束时间  限制条件 格式为 yyyy-MM-dd
	 */
	@RequestMapping("userExcel")
	public void exportUser(HttpServletRequest request,HttpServletResponse response,
			String startTime,String endTime){
		//要在finally中关闭流
		OutputStream out = null;
		try{
			out=response.getOutputStream();
			Timestamp starttime=DateUtil.strToStamp(startTime, DateUtil.DAY_FORMAT);
			Timestamp endtime=DateUtil.strToStamp(endTime, DateUtil.DAY_FORMAT);
			
		   HSSFWorkbook workbook=pOIService.exportExcel(starttime, endtime);
		   
		   String fileName=DateUtils.formatDay(starttime)+"到"+DateUtils.formatDay(endtime)+"注册用户列表"+".xls";	   
		   fileName=ExportUtil.encodeFilename(fileName, request);
		   
		   response.setContentType("application/vnd.ms-excel");
		   response.setHeader("Content-disposition", "attachment;filename="+fileName);
		   workbook.write(out);
		   out.flush();
			
		}catch(Exception e){
			log.error(e.getMessage(), e);
		}finally{
			//关闭流的时间需要抛出异常
			try {
				out.close();
			} catch (IOException e) {
				// TODO 关流
				log.error(e.getMessage(), e);
			}
		}
	}

}
