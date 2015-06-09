package exportExcel.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;

import exportExcel.ORM.DAO.IDEntity;

/**
 * excel模板文件中的所有参数类
 * 
 * @author Xiao He(hxtoyou@163.com)
 * 
 */
@Entity
@Table(name = "S_excel_templates")
public class ExcelTemplate extends IDEntity {
	private String excelName;

	/**
	 * 头行数
	 */
	private String headRows;
	/**
	 * 详细数据行数
	 */
	private String detailRows;
	/**
	 * 统计栏所占行数
	 */
	private String tailRows;
	/**
	 * 查询前执行SQL
	 */
	private String preSQL;
	/**
	 * 查询后执行SQL
	 */
	private String querySQL;
	/**
	 * 查询执行sql的输出结果
	 */
	private String queryParamater;
	/**
	 * 查询类型，data或者dataSUM
	 */
	private String queryTag;
	/**
	 * 模板文件
	 */
	private UploadedFile attachment;
	/**
	 * 系统设置
	 */
	private List<SystemSetting> systemSetting = Lists.newArrayList();
	/**
	 * 页面显示的条件
	 */
	private List<ConfigItem> configItem = Lists.newArrayList();
	/**
	 * 数据转换参数
	 */
	private String queryConvertor;
	/**
	 * 总列数
	 */
	private String columnNum;

	public String getColumnNum() {
		return columnNum;
	}

	public void setColumnNum(String columnNum) {
		this.columnNum = columnNum;
	}

	@Lob
	public String getQueryConvertor() {
		return queryConvertor;
	}

	public void setQueryConvertor(String queryConvertor) {
		this.queryConvertor = queryConvertor;
	}

	public String getQueryTag() {
		return queryTag;
	}

	public void setQueryTag(String queryTag) {
		this.queryTag = queryTag;
	}

	@Lob
	public String getQueryParamater() {
		return queryParamater;
	}

	public void setQueryParamater(String queryParamater) {
		this.queryParamater = queryParamater;
	}

	@OneToMany
	@JoinTable(name = "S_temp_conf")
	public List<ConfigItem> getConfigItem() {
		return configItem;
	}

	public void setConfigItem(List<ConfigItem> configItem) {
		this.configItem = configItem;
	}

	public String getHeadRows() {
		return headRows;
	}

	public void setHeadRows(String headRows) {
		this.headRows = headRows;
	}

	public String getDetailRows() {
		return detailRows;
	}

	public void setDetailRows(String detailRows) {
		this.detailRows = detailRows;
	}

	public String getTailRows() {
		return tailRows;
	}

	public void setTailRows(String tailRows) {
		this.tailRows = tailRows;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	@Lob
	public String getPreSQL() {
		return preSQL;
	}

	public void setPreSQL(String preSQL) {
		this.preSQL = preSQL;
	}

	@Lob
	public String getQuerySQL() {
		return querySQL;
	}

	public void setQuerySQL(String querySQL) {
		this.querySQL = querySQL;
	}

	@ManyToOne
	public UploadedFile getAttachment() {
		return attachment;
	}

	public void setAttachment(UploadedFile attachment) {
		this.attachment = attachment;
	}

	@OneToMany
	@JoinTable(name = "S_temp_set")
	public List<SystemSetting> getSystemSetting() {
		return systemSetting;
	}

	public void setSystemSetting(List<SystemSetting> systemSetting) {
		this.systemSetting = systemSetting;
	}

}
