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
@Table(name = "S_city")
public class CityData extends IDEntity {
	private String cityname;
	private String countryCode;
	private String district;
	private Integer population;

	@Column(name = "Name")
	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	@Column(name = "CountryCode")
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Column(name = "District")
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(name = "Population")
	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

}
