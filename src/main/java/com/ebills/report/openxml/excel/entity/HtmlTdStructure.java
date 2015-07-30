package com.ebills.report.openxml.excel.entity;

/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年5月28日 下午4:08:02
 * 
 * HTML表格单元格格式与数据
 */
public class HtmlTdStructure {
	private Integer colspan;
	private Integer rowspan;
	private String value;
	private Boolean tag;//标记是否被合并
	private String width;
	private String height;
	private String border_top;
	private String border_left;
	private String border_right;
	private String border_bottom;
	private String border_top_color;
	private String border_left_color;
	private String border_right_color;
	private String border_bottom_color;
	private String bg_color;
	private String font_color;
	private String font_size;
	private Boolean isBold;
	private String fontFamily;
	private String verticalAlign;
	private String horizontalAlign;
	



	public String getVerticalAlign() {
		return verticalAlign;
	}

	public void setVerticalAlign(String verticalAlign) {
		this.verticalAlign = verticalAlign;
	}

	public String getHorizontalAlign() {
		return horizontalAlign;
	}

	public void setHorizontalAlign(String horizontalAlign) {
		this.horizontalAlign = horizontalAlign;
	}

	public Boolean getIsBold() {
		return isBold;
	}

	public void setIsBold(Boolean isBold) {
		this.isBold = isBold;
	}

	public String getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}

	public String getBorder_top() {
		return border_top;
	}

	public void setBorder_top(String border_top) {
		this.border_top = border_top;
	}

	public String getBorder_left() {
		return border_left;
	}

	public void setBorder_left(String border_left) {
		this.border_left = border_left;
	}

	public String getBorder_right() {
		return border_right;
	}

	public void setBorder_right(String border_right) {
		this.border_right = border_right;
	}

	public String getBorder_bottom() {
		return border_bottom;
	}

	public void setBorder_bottom(String border_bottom) {
		this.border_bottom = border_bottom;
	}

	public String getBorder_top_color() {
		return border_top_color;
	}

	public void setBorder_top_color(String border_top_color) {
		this.border_top_color = border_top_color;
	}

	public String getBorder_left_color() {
		return border_left_color;
	}

	public void setBorder_left_color(String border_left_color) {
		this.border_left_color = border_left_color;
	}

	public String getBorder_right_color() {
		return border_right_color;
	}

	public void setBorder_right_color(String border_right_color) {
		this.border_right_color = border_right_color;
	}

	public String getBorder_bottom_color() {
		return border_bottom_color;
	}

	public void setBorder_bottom_color(String border_bottom_color) {
		this.border_bottom_color = border_bottom_color;
	}

	public String getBg_color() {
		return bg_color;
	}

	public void setBg_color(String bg_color) {
		this.bg_color = bg_color;
	}

	public String getFont_color() {
		return font_color;
	}

	public void setFont_color(String font_color) {
		this.font_color = font_color;
	}

	public String getFont_size() {
		return font_size;
	}

	public void setFont_size(String font_size) {
		this.font_size = font_size;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Integer getColspan() {
		return colspan;
	}

	public void setColspan(Integer colspan) {
		this.colspan = colspan;
	}

	public Integer getRowspan() {
		return rowspan;
	}

	public void setRowspan(Integer rowspan) {
		this.rowspan = rowspan;
	}

	public Boolean getTag() {
		return tag;
	}

	public void setTag(Boolean tag) {
		this.tag = tag;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
