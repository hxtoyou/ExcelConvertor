package com.ebills.report.openxml.excel.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.ironrhino.core.metadata.AutoConfig;
import org.ironrhino.core.struts.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

import com.ebills.common.platform.report.excel.entity.ExcelForm;
import com.ebills.common.platform.report.excel.service.ReportsGeneratorService;
import com.ebills.common.platform.report.excel.util.ExcelToHtmlUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;


/**
 * Excel创建查询窗口
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年6月1日 下午2:57:46
 * 
 */
@AutoConfig
public class ExcelCreateFormAction extends BaseAction {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private List<ExcelForm> configItems = Lists.newArrayList();
	private ObjectMapper mapper = new ObjectMapper();  
	protected Map<String, Object> context = new HashMap<String, Object>();
	@Autowired
	private ReportsGeneratorService genaratorService;
	@SuppressWarnings("deprecation")
	public String getForm(){
			HttpServletRequest request = ServletActionContext.getRequest();
	        //将要被返回到客户端的对象  
		
			long start = System.currentTimeMillis();
			String locale = request.getParameter("locale");
			String pathName = request.getParameter("action");
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
				context.put("user",mapper.writeValueAsString(configItems));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			context.put("queryFromName", queryFromName);
			context.put("filePath",absoltePath.replaceAll("\\\\", "/"));
			if(!Strings.isNullOrEmpty(validateFile)){
				context.put("validateFile", validateFile);
			}else{
				System.out.println("生成表格或者验证js文件路径未设置");
			}
			  System.out.println("耗时: " + (System.currentTimeMillis() - start)+"毫秒");
//			  ServletActionContext.getRequest().setAttribute("context", context);
			  return "info";
	}
	public Map<String, Object> getContext() {
		return context;
	}
	public void setContext(Map<String, Object> context) {
		this.context = context;
	}

}
