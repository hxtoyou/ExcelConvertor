package exportExcel.web.entity;

/**
 * @author Xiao He(hxtoyou@163.com)
 * 
 *         HTML表格单元格格式与数据
 */
public class HtmlTdStructure {
	private Integer colspan;
	private Integer rowspan;
	private String value;
	private Boolean tag;
	private String width;
	private String height;
	private Short border_top;
	private Short border_left;
	private Short border_right;
	private Short border_bottom;
	private Short border_top_color;
	private Short border_left_color;
	private Short border_right_color;
	private Short border_bottom_color;
	private Short bg_color;
	private Short font_color;
	private Short font_size;
	public Short getBorder_top() {
		return border_top;
	}

	public void setBorder_top(Short border_top) {
		this.border_top = border_top;
	}

	public Short getBorder_left() {
		return border_left;
	}

	public void setBorder_left(Short border_left) {
		this.border_left = border_left;
	}

	public Short getBorder_right() {
		return border_right;
	}

	public void setBorder_right(Short border_right) {
		this.border_right = border_right;
	}

	public Short getBorder_bottom() {
		return border_bottom;
	}

	public void setBorder_bottom(Short border_bottom) {
		this.border_bottom = border_bottom;
	}

	public Short getBorder_top_color() {
		return border_top_color;
	}

	public void setBorder_top_color(Short border_top_color) {
		this.border_top_color = border_top_color;
	}

	public Short getBorder_left_color() {
		return border_left_color;
	}

	public void setBorder_left_color(Short border_left_color) {
		this.border_left_color = border_left_color;
	}

	public Short getBorder_right_color() {
		return border_right_color;
	}

	public void setBorder_right_color(Short border_right_color) {
		this.border_right_color = border_right_color;
	}

	public Short getBorder_bottom_color() {
		return border_bottom_color;
	}

	public void setBorder_bottom_color(Short border_bottom_color) {
		this.border_bottom_color = border_bottom_color;
	}

	public Short getBg_color() {
		return bg_color;
	}

	public void setBg_color(Short bg_color) {
		this.bg_color = bg_color;
	}

	public Short getFont_color() {
		return font_color;
	}

	public void setFont_color(Short font_color) {
		this.font_color = font_color;
	}

	public Short getFont_size() {
		return font_size;
	}

	public void setFont_size(Short font_size) {
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
