package exportExcel.entity;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年5月28日 下午4:08:02
 * 	
 * excel行样式
 */
public class HtmlRowStructure {
	private Integer rowNum;
	private Integer cellsNum;

	List<HtmlTdStructure> htmlTdStructure = Lists.newArrayList();

	public List<HtmlTdStructure> getHtmlTdStructure() {
		return htmlTdStructure;
	}

	public void setHtmlTdStructure(List<HtmlTdStructure> htmlTdStructure) {
		this.htmlTdStructure = htmlTdStructure;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Integer getCellsNum() {
		return cellsNum;
	}

	public void setCellsNum(Integer cellsNum) {
		this.cellsNum = cellsNum;
	}

	@Override
	public HtmlRowStructure clone() {
		HtmlRowStructure clone = null;
		try {
			clone = (HtmlRowStructure) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e); // won't happen
		}
		return clone;
	}
}
