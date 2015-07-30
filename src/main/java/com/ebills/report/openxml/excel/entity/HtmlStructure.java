package com.ebills.report.openxml.excel.entity;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年5月28日 下午4:08:02
 * 
 *         HTML文件table格式
 */
public class HtmlStructure {
	private Integer rowNum;
	private Integer column;
	List<HtmlRowStructure> htmlRowStructure = Lists.newArrayList();
	private Double tableWidth;

	public Double getTableWidth() {
		return tableWidth;
	}

	public void setTableWidth(Double tableWidth) {
		this.tableWidth = tableWidth;
	}

	public List<HtmlRowStructure> getHtmlRowStructure() {
		return htmlRowStructure;
	}

	public void setHtmlRowStructure(List<HtmlRowStructure> htmlRowStructure) {
		this.htmlRowStructure = htmlRowStructure;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

}
