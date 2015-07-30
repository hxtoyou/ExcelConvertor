package com.ebills.report.openxml.excel.service;
/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年6月4日 下午4:03:24
 * 
 */
public interface ReportsGenerator<T> {
	T generateReport(T context);
	
	void export(T context);
}
