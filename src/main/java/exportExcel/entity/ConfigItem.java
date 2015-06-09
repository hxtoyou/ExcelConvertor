package exportExcel.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import exportExcel.ORM.DAO.IDEntity;

/***
 * 查询条件
 * 
 * @author Xiao He(hxtoyou@163.com)
 * 
 */
@Entity
@Table(name = "S_confige_item")
public class ConfigItem extends IDEntity {
	/**
	 * 条件名称
	 */
	protected String name;
	/**
	 * 查询内容
	 */
	protected String content;
	/**
	 * 条件名称
	 */
	protected String varName;
	/**
	 * 数据类型
	 */
	protected String memo;
	/**
	 * 是否精准查询
	 */
	protected String acurrate;
	/**
	 * 是否必须输入
	 */
	protected String neccesary;

	public String getAcurrate() {
		return acurrate;
	}

	public void setAcurrate(String acurrate) {
		this.acurrate = acurrate;
	}

	public String getNeccesary() {
		return neccesary;
	}

	public void setNeccesary(String neccesary) {
		this.neccesary = neccesary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

}
