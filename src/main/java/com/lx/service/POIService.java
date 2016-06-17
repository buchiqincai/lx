package com.lx.service;

import java.sql.Timestamp;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * poi 导出excel
 * @author lx
 *
 */
public interface POIService {
	/**
	 * 把用户的信息放入excel中
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	HSSFWorkbook exportExcel(Timestamp startTime,Timestamp endTime)throws Exception;

}
