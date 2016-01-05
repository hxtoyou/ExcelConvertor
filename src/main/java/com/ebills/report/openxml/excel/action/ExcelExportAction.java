package com.ebills.report.openxml.excel.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;








import org.apache.struts2.ServletActionContext;
import org.ironrhino.core.metadata.AutoConfig;
import org.ironrhino.core.struts.BaseAction;
import org.ironrhino.core.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ebills.common.platform.report.excel.service.ReportsGeneratorService;


/**
 * Excel导出
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年6月29日 下午2:48:50
 * 
 */
@AutoConfig
public class ExcelExportAction extends BaseAction{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6953963279404708654L;
	@Autowired
	private ReportsGeneratorService genaratorService;
	
	public String export() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//取组件名称，
		Map<String,String> req = RequestUtils.getParametersMap(request);
		Map<String,Object> context = new HashMap<String,Object>();
		context.putAll(req);
		genaratorService.export(context);
		return NONE;
	}
}
