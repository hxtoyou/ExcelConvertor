package com.ebills.report.openxml.excel.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.bussprocess.context.Context;
import org.apache.commons.bussprocess.exception.InvalidArgumentException;
import org.apache.commons.bussprocess.exception.ObjectNotFoundException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.eap.core.EAPConstance;
import com.eap.flow.EAPAction;
import com.ebills.report.openxml.excel.entity.ExcelForm;
import com.ebills.report.openxml.excel.service.ReportsGeneratorService;
import com.ebills.report.openxml.excel.util.ExcelToHtmlUtil;
import com.ebills.util.EbillsException;
import com.ebills.utils.CommonUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;


/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年6月1日 下午2:57:46
 * 
 */
public class ExcelCreateFormAction extends EAPAction {
	private List<ExcelForm> configItems = Lists.newArrayList();
	@SuppressWarnings("deprecation")
	@Override
	public String execute(Context context) throws ObjectNotFoundException, InvalidArgumentException{
		HttpServletRequest request = (HttpServletRequest)context.getValue(EAPConstance.SERVLET_REQUEST);
	        //将要被返回到客户端的对象  
		
			long start = System.currentTimeMillis();
			String locale = request.getParameter("locale");
			String pathName = request.getParameter("action");
			ReportsGeneratorService genaratorService = new ReportsGeneratorService();
			String filePath = genaratorService.getFilePath(pathName);
			String realPath = request.getRealPath(filePath);
			int fileNameIndex = realPath.lastIndexOf(File.separator);
			String fileName = realPath.substring(fileNameIndex+1);
			String path = realPath.substring(0,fileNameIndex+1);
			String absoltePath = ExcelToHtmlUtil.getFileNameByInternationnal(path,fileName, locale);
		
			Workbook workbook = ExcelToHtmlUtil.getExcelWorkBook(absoltePath);
			/**
			 * 解析excel系统设置模板配置信息
			 */
			Sheet sheet1 = workbook.getSheetAt(0);//系统设置模板
			int systtemSetrownum = sheet1.getLastRowNum();
			Map<String,String> systemSetResult = ExcelToHtmlUtil.analyzeSystemSetting(sheet1, systtemSetrownum+1);
			Integer querySheet = ExcelToHtmlUtil.paraseString(systemSetResult.get("querySheet"));//查询参数模板页码
			String queryFromName = systemSetResult.get("queryFromName");
			String validateFile = systemSetResult.get("validateFile");
			/**
			 * 查询条件模板页条件解析
			 * 
			 * 
			 */
			Sheet sheet = workbook.getSheetAt(querySheet-1);
			configItems = genaratorService.getExcelForm(sheet);
			  try {
					context.put("user", CommonUtil.ListToJson(configItems).toString());
					context.put("queryFromName", queryFromName);
					context.put("filePath",absoltePath.replaceAll("\\\\", "/"));
					if(!Strings.isNullOrEmpty(validateFile)){
						context.put("validateFile", validateFile);
					}else{
						System.out.println("生成表格或者验证js文件路径未设置");
					}
				} catch (EbillsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  System.out.println("耗时: " + (System.currentTimeMillis() - start)+"毫秒");
			  return null;
	}

}
