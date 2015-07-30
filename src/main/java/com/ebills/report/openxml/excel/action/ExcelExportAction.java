package com.ebills.report.openxml.excel.action;

import javax.servlet.http.HttpSession;

import org.apache.commons.bussprocess.context.Context;
import org.apache.commons.bussprocess.exception.BPException;

import com.eap.core.EAPConstance;
import com.eap.flow.EAPAction;
import com.ebills.report.openxml.excel.service.ReportsGenerator;
import com.ebills.report.openxml.excel.service.ReportsGeneratorService;

/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年6月29日 下午2:48:50
 * 
 */
public class ExcelExportAction extends EAPAction{
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String execute(Context context) throws BPException{
		ReportsGenerator generator = new ReportsGeneratorService();
		generator.export(context);
		return null;
	}
}
