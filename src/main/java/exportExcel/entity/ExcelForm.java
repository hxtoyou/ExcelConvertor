package exportExcel.entity;


/***
 * 查询条件
 * 
 * @author Xiao He(hxtoyou@163.com)
 * 
 */

public abstract class ExcelForm {
	/**
	 * 条件中文名称
	 */
	protected String name;
	/**
	 * 查询内容
	 */
	protected String content;
	/**
	 * 条件英文名称
	 */
	protected String varName;
	/**
	 * 数据类型
	 */
	protected String classType;
	/**
	 * 是否精准查询
	 */
	protected String acurrate;
	/**
	 * 是否必须输入
	 */
	protected String neccesary;
	/**
	 * combobox参数
	 */
	protected String comboAttr;
	
	public String getComboAttr() {
		return comboAttr;
	}

	public void setComboAttr(String comboAttr) {
		this.comboAttr = comboAttr;
	}

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


	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
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
