package com.ebills.report.openxml.excel.util;


import java.util.Map;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年7月3日 下午2:52:35
 * 
 */
@Component
public class Test implements ReportExtendInterface<Map<String,Object>> {
	
	@Autowired
	private ExcelDAO excelDAO;
	public Test(){};
	@Override
	public void execute(Map<String,Object> context) {
		String sql10="CREATE TABLE IF NOT EXISTS person (number INT(11),name VARCHAR(255),birthday DATE);";
		try {
			excelDAO.doCreateView(sql10, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String sql8=" insert into person(number,name,birthday) values('12','asd','2015-05-08')";
		try {
			excelDAO.doCreateView(sql8, null);
		} catch (EbillsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void clean(Map<String,Object> context) {
		String sql="drop TABLE testtemptablex";
		// TODO Auto-generated method stub
		try {
			excelDAO.doCreateView(sql, null);
		} catch (EbillsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
