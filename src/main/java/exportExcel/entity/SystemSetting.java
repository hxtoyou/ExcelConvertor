package exportExcel.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import exportExcel.ORM.DAO.IDEntity;

@Entity
@Table(name = "S_template_set")
public class SystemSetting extends IDEntity {
	/**
	 * 系统设置项名称
	 */
	private String name;
	/**
	 * 系统设置值
	 */
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
