package exportExcel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import exportExcel.ORM.DAO.IDEntity;

/**
 * @author Xiao He(hxtoyou@163.com)
 * 
 * 
 */
@Entity
@Table(name = "S_countrylanguage")
public class CountryLanguage extends IDEntity {
	private String countryCode;
	private String language;
	private Float percentage;

	@Column(name = "CountryCode")
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Column(name = "Language")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name = "Percentage")
	public Float getPercentage() {
		return percentage;
	}

	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}

}
