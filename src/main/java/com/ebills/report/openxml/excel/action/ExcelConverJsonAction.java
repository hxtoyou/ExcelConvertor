package com.ebills.report.openxml.excel.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ebills.report.openxml.excel.service.ReportsGeneratorService;
import com.google.common.collect.Maps;


/**
 * 返回所有Excel显示数据
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年6月9日 下午3:42:08
 * 
 */
@Controller
public class ExcelConverJsonAction{
	private static final long	serialVersionUID	= -8477388752748427429L;
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 
	 */
	@Autowired
	private ReportsGeneratorService genaratorService;
	private String path;
	private String results;
	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	@RequestMapping(value = "/location/location-areas-staff/detail", method = RequestMethod.GET)
	public String getExcelHtml() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//取组件名称，
		Map<String,String> req = RequestUtils.getParametersMap(request);
		Map<String, Object> context = Maps.newHashMap();
		context.putAll(req);
			results = (String) genaratorService.generateReport(context).get("output");
		return JSON;
	}
}