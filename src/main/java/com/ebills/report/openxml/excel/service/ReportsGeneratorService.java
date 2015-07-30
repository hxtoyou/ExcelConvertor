package com.ebills.report.openxml.excel.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.bussprocess.context.Context;
import org.apache.commons.bussprocess.exception.InvalidArgumentException;
import org.apache.commons.bussprocess.exception.ObjectNotFoundException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.eap.core.EAPConstance;
import com.ebills.report.openxml.excel.entity.ExcelForm;
import com.ebills.report.openxml.excel.entity.ExcelFormEntity;
import com.ebills.report.openxml.excel.entity.ExcelInputType;
import com.ebills.report.openxml.excel.entity.HtmlRowStructure;
import com.ebills.report.openxml.excel.entity.HtmlStructure;
import com.ebills.report.openxml.excel.entity.HtmlTdStructure;
import com.ebills.report.openxml.excel.util.ExcelToHtmlUtil;
import com.ebills.report.openxml.excel.util.Page;
import com.ebills.util.EbillsCfg;
import com.ebills.util.EbillsException;
import com.ebills.utils.CommonUtil;
import com.ebills.utils.EbpDao;
import com.google.common.base.Strings;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年6月4日 下午4:48:18
 * 
 */
public class ReportsGeneratorService implements ReportsGenerator<Context> {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Context generateReport(Context context) {

		// HttpServletResponse response =
		// (HttpServletResponse)context.getValue(EAPConstance.SERVLET_REQSPONSE);

		HttpServletRequest request = null;
		try {
			request = (HttpServletRequest) context.getValue(EAPConstance.SERVLET_REQUEST);
		} catch (ObjectNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String fileName = request.getParameter("templateName");// 模板名称
		Workbook workbook = ExcelToHtmlUtil.getExcelWorkBook(fileName);
		/**
		 * 解析excel系统设置模板配置信息
		 */
		Sheet sheet = workbook.getSheetAt(0);// 系统设置模板
		int systtemSetrownum = sheet.getLastRowNum();
		Map<String, String> systemSetResult = ExcelToHtmlUtil.analyzeSystemSetting(sheet, systtemSetrownum);
		Integer detailSetSheet = ExcelToHtmlUtil.paraseString(systemSetResult.get("detailSetSheet"));// 明细配置配置页码
		Integer detailTemplateSheet = ExcelToHtmlUtil.paraseString(systemSetResult.get("detailTemplateSheet"));// 明细格式模板页码
		Integer statisticSetSheet = ExcelToHtmlUtil.paraseString(systemSetResult.get("statisticSetSheet"));// 统计配置模板页码
		Integer statisticTemplateSheet = ExcelToHtmlUtil.paraseString(systemSetResult.get("statisticTemplateSheet"));// 统计格式模板页码
		Integer querySheet = ExcelToHtmlUtil.paraseString(systemSetResult.get("querySheet"));// 查询参数模板页码
		String showType = systemSetResult.get("showType");// 模板类型
		List<String> types = Arrays.asList(showType.split("&"));
		Integer setSheet = null;// 设置模板页码
		Integer templateSheet = null;// 样式模板页码
		if (types.size() < 1) {
			System.out.println("未设置模板信息");
			return null;
		}
		context.put("templateType", showType);
		String templateType = request.getParameter("templateType");// 获取模板类型参数
		Page page = new Page();
		if (Strings.isNullOrEmpty(templateType)) {
			templateType = types.get(0);
		}

		// 根据模板类型参数选择模板
		if (templateType.equals("detail")) {
			setSheet = detailSetSheet;
			templateSheet = detailTemplateSheet;
		} else if (templateType.equals("statistic")) {
			setSheet = statisticSetSheet;
			templateSheet = statisticTemplateSheet;
		} else {
			System.out.println("模板类型设置错误");
			return null;
		}
		if (setSheet < 1 || templateSheet < 1 || querySheet < 1) {
			System.out.println("模板类型设置错误");
			return null;
		}
		Sheet sheet1 = workbook.getSheetAt(setSheet - 1);// 配置sheet
		Sheet sheet2 = workbook.getSheetAt(templateSheet - 1);// 模板sheet
		Sheet sheet3 = workbook.getSheetAt(querySheet - 1);// 查询参数sheet
		List<ExcelForm> forms = getExcelForm(sheet3);

		/**
		 * 模板配置页面条件设置sheet1
		 */
		int cellnum = 0;
		int rownum = sheet1.getLastRowNum();
		Map<String, String> analyzeResult = ExcelToHtmlUtil.getExcelAnalyze(sheet1, rownum);
		String javaClass = analyzeResult.get("javaClass");// 执行java代码
		String preSQL = analyzeResult.get("preSQL");// 执行前sql
		String querySQL = analyzeResult.get("querySQL");// 查询sql
		String queryTag = analyzeResult.get("queryTag");// queryTag表示输出结果是data还是datasum
		String pagination = analyzeResult.get("pagination");// 是否分页
		String totalColumnNum = analyzeResult.get("totalColumnNum");// 总列数
		String heads = analyzeResult.get("heads");// 数据头所占行
		String tails = analyzeResult.get("tails");// 数据尾所占行
		String details = analyzeResult.get("details");// 数据体所占行
		List<CellRangeAddress> addresses = ExcelToHtmlUtil.getMergeRegons(sheet2);// 所有合并单元格
		cellnum = (int) Math.floor(Double.parseDouble(totalColumnNum));// 获取中列数
		/**
		 * 根据是否分页设置查询数据结果的多少，不分页最多显示1000条数据
		 */
		if (pagination.equals("YES")) {
			if (!Strings.isNullOrEmpty(request.getParameter("pageSize"))) { 
				Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
				page.setPageSize(pageSize);
			} else {
				page.setPageSize(10);
			}
			if (!Strings.isNullOrEmpty(request.getParameter("pageNumber"))) {
				Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
				page.setPageNumber(pageNumber);
			} else {
				page.setPageNumber(1);
			}
		} else if (pagination.equals("NO")) {
			page.setPageSize(1000);
			page.setPageNumber(1);
		} else {
			System.out.println("模板类型设置错误");
			return null;
		}

		// 获取查询sql
		List<String> querySqlList = Lists.newArrayList();
		if (!Strings.isNullOrEmpty(querySQL)) {
			querySqlList = Arrays.asList(querySQL.split(";"));
		}
		// 获取执行前sql
		List<String> preSqlList = Lists.newArrayList();
		if (!Strings.isNullOrEmpty(preSQL)) {
			preSqlList = Arrays.asList(preSQL.split(";"));
		}
		List<String> queryTagList = Lists.newArrayList();
		if (!Strings.isNullOrEmpty(queryTag)) {

			queryTagList = Arrays.asList(queryTag.split(";"));
		}
		// 获取详细内容行数
		int detailHead = ExcelToHtmlUtil.paraseHead(details);// 起始位置
		int detailTail = ExcelToHtmlUtil.paraseTail(details);// 结束位置
		int copyRowNum = detailTail - detailHead + 1;// 源数据（详细内容）行数
		// 每页显示的内容条数

		int pageViewNum = (page.getPageSize() / copyRowNum) < 1 ? 1 : (page.getPageSize() / copyRowNum);
		// 显示数据的起始位置
		int dataStartIndex = pageViewNum * (page.getPageNumber() - 1);
		// 获取表头行数
		int headsTail = ExcelToHtmlUtil.paraseTail(heads);
		// 获取表尾行数
		int tailRowNum = 0;
		if (!Strings.isNullOrEmpty(tails)) {
			int tailHead = ExcelToHtmlUtil.paraseHead(tails);
			int tailTail = ExcelToHtmlUtil.paraseTail(tails);
			tailRowNum = tailTail - tailHead + 1;//
		}

		// table模板的行数
		int trueRowNum = headsTail + copyRowNum + tailRowNum;// 排除空行后的table结构的真实行数
		int contentEnd = trueRowNum - tailRowNum + copyRowNum * pageViewNum - copyRowNum;// 加上N条源数据后详细内容加上表头的行数
		int detailLineNum = contentEnd - headsTail;// 详细内容行数

		Table<String, String, String> paramValues = HashBasedTable.create();
		// 解析查询模板中的查询条件
		for (ExcelForm form : forms) {
			try {
				paramValues.put(form.getVarName(), form.getClassType(), request.getParameter(form.getVarName()));

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		long start = System.currentTimeMillis();
		// 遍历合并单元格，标记被合并的单元格
		HtmlStructure htmlStructure = createByRegin(addresses, null, null, null, trueRowNum, cellnum);
		// 向单元格填充结构数据并返回宽度
		Double tableWidth = fillStructureData(htmlStructure, trueRowNum, cellnum, sheet2);
		// table宽度
		htmlStructure.setTableWidth(tableWidth / trueRowNum);
		// 删除被合并的单元格
		tableStyle(htmlStructure, addresses, null, null, trueRowNum - 1, rownum, cellnum);
		// 根据要显示的数据行数扩展htmlStructure对象行数
		expendHtmlStructure(htmlStructure, copyRowNum, pageViewNum, detailHead, detailTail);
		 System.out.println("耗时: " + (System.currentTimeMillis() - start)+"毫秒");
		// 执行sql返回数据
		EbpDao ebpDAO = new EbpDao();
		// 执行用户自定义java代码
		if (!Strings.isNullOrEmpty(javaClass)) {
			doPreJavaCode(javaClass, context);
		}
		// 创建视图
		doPreSQL(preSqlList, ebpDAO, paramValues);
		// 返回数据
		page = getResult(page, htmlStructure, querySqlList, queryTagList, ebpDAO, dataStartIndex, pageViewNum,
				detailLineNum, copyRowNum, headsTail, tailRowNum, pagination, paramValues);
		// 用户自定义java类创建的表或者数据清除
		if (!Strings.isNullOrEmpty(javaClass)) {
			doClean(javaClass,context);
		}
		page.setPageSize(pageViewNum);
		Map<String, String> resultMap = Maps.newHashMap();
		resultMap.put("data", JSON.toJSONString(page));
		resultMap.put("showType", showType);
		resultMap.put("tableTitle", String.valueOf(headsTail));
		resultMap.put("pagination", pagination);
		String resultOut = JSON.toJSONString(resultMap);
		context.put("output", resultOut);
		return context;
	}

	/**
	 * 执行sql填充数据
	 * 
	 * @param htmlStructure
	 * @param querySqlList
	 * @param queryTagList
	 * @param baseDAO
	 * @param params
	 * @param dataStartIndex
	 * @param pageViewNum
	 * @param map
	 * @param detailLineNum
	 * @param copyRowNum
	 * @param headsTail
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Page<HtmlStructure> getResult(Page<HtmlStructure> page, HtmlStructure htmlStructure,
			List<String> querySqlList, List<String> queryTagList, EbpDao baseDAO, Integer dataStartIndex,
			Integer pageViewNum, Integer detailLineNum, Integer copyRowNum, Integer headsTail, Integer tailRowNum,
			String pagination, Table paramValues) {
		int totalSize = 0;
		int resultSize = 0;
		String dataBaseType="";
		try {
			dataBaseType = CommonUtil.getConstant("system.DataBase");
		} catch (EbillsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int y = 0; y < querySqlList.size(); y++) {
			if (!Strings.isNullOrEmpty(querySqlList.get(y))) {
				String resultTag = queryTagList.get(y);
				List<Map<String, String>> results = Lists.newArrayList();
				List<Map<String, String>> totalResults = Lists.newArrayList();
				String sql = "";
				String totalSql;
				// 如果是内容项，则数据库分页查询
				if (pagination.equals("YES")) {
					if (resultTag.matches(".*DATA_.*")) {
						totalSql = "SELECT COUNT(*) FROM(" + querySqlList.get(y) + ")";
						totalResults = baseDAO.queryBySqlExcel(totalSql, paramValues, resultTag);
						for (Map.Entry<String, String> entry : totalResults.get(0).entrySet()) {
							if (totalSize < Integer.parseInt(entry.getValue())) {// 取最大的totalSize
								totalSize = Integer.parseInt(entry.getValue());
							}
						}
						if(dataBaseType!=null&&dataBaseType.equals("db2")){
							sql =  "select * from ("+querySqlList.get(y)+") a where a.row_number>"+dataStartIndex+" fetch first "+pageViewNum+" rows only";
						}else{
							sql = "SELECT * FROM(SELECT A.*, rownum r FROM (" + querySqlList.get(y) + ") A WHERE rownum <="
									+ (dataStartIndex + pageViewNum) + ") B WHERE r >" + dataStartIndex;
						}
						results = baseDAO.queryBySqlExcel(sql, paramValues, resultTag);
					} else if (resultTag.matches(".*DATASUM_.*")) {
						sql = querySqlList.get(y);
						results = baseDAO.queryBySqlExcel(sql, paramValues, resultTag);
					}
				} else if (pagination.equals("NO")) {
					sql = querySqlList.get(y);
					results = baseDAO.queryBySqlExcel(sql, paramValues, resultTag);
					totalSql = "SELECT COUNT(*) FROM(" + querySqlList.get(y) + ")";
					totalResults = baseDAO.queryBySqlExcel(totalSql, paramValues, resultTag);
					for (Map.Entry<String, String> entry : totalResults.get(0).entrySet()) {
						if (totalSize < Integer.parseInt(entry.getValue())) {// 取最大的totalSize
							totalSize = Integer.parseInt(entry.getValue());
						}
					}
				}
				if (resultSize < results.size()) {
					resultSize = results.size();
				}
				// 谈查询结果只有一行的时候（大多数时候是统计查询），则直接遍历所有excel单元格向里面填充数据
				if (results.size() <= 1 && results.size() > 0) {
					inputStatisticData(results, htmlStructure, copyRowNum, headsTail, tailRowNum);
				} else if (results.size() > 1) {
					inputData(detailLineNum, copyRowNum, headsTail, results, pageViewNum, htmlStructure, tailRowNum);
				}
			}
		}
		Integer delRowNum = 0;
		if (resultSize < 1) {
			delRowNum = headsTail + copyRowNum;
		} else {
			delRowNum = resultSize * copyRowNum + headsTail;
		}
		// Integer delRowNum = resultSize * copyRowNum + headsTail+copyRowNum;
		int size = htmlStructure.getHtmlRowStructure().size();
		Iterator<HtmlRowStructure> rowIter = htmlStructure.getHtmlRowStructure().iterator();
		int rowCount = 0;
		while (rowIter.hasNext()) {
			rowCount++;
			rowIter.next();
			if (totalSize == 0) {
				if (rowCount > (delRowNum + 3) && rowCount < (size - tailRowNum + 1)) {
					rowIter.remove();// 这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
				}
			} else {
				if (rowCount > delRowNum && rowCount < (size - tailRowNum + 1)) {
					rowIter.remove();// 这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
				}
			}
		}
		page.setTotalCount(totalSize);
		page.setPageSize(pageViewNum);
		List<HtmlStructure> resultHtmlStructure = Lists.newArrayList();
		resultHtmlStructure.add(htmlStructure);
		page.setResult(resultHtmlStructure);
		return page;
	}

	/**
	 * 填充统计数据
	 * 
	 * @param results
	 * @param htmlStructure
	 * @param map
	 */
	private void inputStatisticData(List<Map<String, String>> results, HtmlStructure htmlStructure, Integer copyRowNum,
			Integer headsTail, Integer tailRowNum) {
		
		for (HtmlRowStructure addRowData : htmlStructure.getHtmlRowStructure()) {
			for (HtmlTdStructure tdStructure : addRowData.getHtmlTdStructure()) {
					Map<String, String>  map = results.get(0);
					if (!Strings.isNullOrEmpty(tdStructure.getValue())) {
						try {
							String content = tdStructure.getValue();
							String regex = "\\{(.*?)\\}";
							Pattern p = Pattern.compile(regex);
							Matcher m = p.matcher(content);
							m.find();
							String n="";
							String regx = "{"+m.group(1)+"}";
							if(!Strings.isNullOrEmpty(map.get(m.group(1)))){
								String regexValue=map.get(m.group(1));
								n = content.replace(regx, regexValue);
								tdStructure.setValue(n);
							}
						} catch (IllegalStateException e) {
//							tdStructure.setValue(entry.getValue());
							// TODO: handle exception
						}
				}
			}
		}
		 
	}

	/**
	 * 填充主数据
	 * 
	 * @param detailLineNum
	 * @param copyRowNum
	 * @param headsTail
	 * @param results
	 * @param pageViewNum
	 * @param htmlStructure
	 * @param map
	 */
	private void inputData(Integer detailLineNum, Integer copyRowNum, Integer headsTail,
			List<Map<String, String>> results, Integer pageViewNum, HtmlStructure htmlStructure, Integer tailRowNum) {
		int n = 0;
		int l = 0;
		for (int q = 0; q < detailLineNum / copyRowNum; q++) {// 取N条data,如果表格的源数据是N行，那么当一行处理只取一条数据
			if (results.size() - n >= (pageViewNum - q)) {// 当结果集不能填满整个表格的时候判断
				for (int a = 0; a < copyRowNum; a++) {
					HtmlRowStructure addRowData = htmlStructure.getHtmlRowStructure().get(
							headsTail + l * copyRowNum + a);// 取出一行的表格结构数据
					List<HtmlTdStructure> htmlTdStructures = addRowData.getHtmlTdStructure();
					Map<String,String> resultMap = results.get(n);
						for (int s = 0; s < htmlTdStructures.size(); s++) {
							if (!Strings.isNullOrEmpty(htmlTdStructures.get(s).getValue())) {
								try {
									String content = htmlTdStructures.get(s).getValue();
									String regex = "\\{(.*?)\\}";
									Pattern p = Pattern.compile(regex);
									Matcher m = p.matcher(content);
									m.find();
									String result="";
									String regx = "{"+m.group(1)+"}";
									if(!Strings.isNullOrEmpty(resultMap.get(m.group(1)))){
										String regexValue=resultMap.get(m.group(1));
										result = content.replace(regx, regexValue);
										htmlTdStructures.get(s).setValue(result);
									}
								} catch (IllegalStateException e) {
								}
							}
						}
					}
				n++;
				l++;
			}
		}
	}

	/**
	 * 填充结构数据
	 * 
	 * @param htmlStructure
	 * @param trueRowNum
	 * @param cellnum
	 * @param sheet
	 * @return
	 */
	private Double fillStructureData(HtmlStructure htmlStructure, Integer trueRowNum, Integer cellnum, Sheet sheet) {
		Double tableWidth = 0d;
		List<HtmlRowStructure> htmlRowStructures = htmlStructure.getHtmlRowStructure();
		for (int j = 0; j < trueRowNum; j++) {
			if (sheet.getRow(j) != null) {
				for (int k = 0; k < cellnum; k++) {
					String value = "";
					if (sheet.getRow(j).getCell(k) != null && !"null".equals(sheet.getRow(j).getCell(k))) {
						try {
							value = sheet.getRow(j).getCell(k).getRichStringCellValue().toString();
						} catch (IllegalStateException e) {
							value = String.valueOf(sheet.getRow(j).getCell(k).getNumericCellValue());
							// TODO: handle exception
						}
					} else if ("null".equals(sheet.getRow(j).getCell(k))) {
						value = "";
					} else {
						value = "";
					}
					ExcelToHtmlUtil.setColor(j, k, sheet, htmlRowStructures);// 设置颜色、边框、背景色
					Short height = sheet.getRow(j).getHeight();
					htmlRowStructures
							.get(j)
							.getHtmlTdStructure()
							.get(k)
							.setValue((Strings.isNullOrEmpty(value.trim()) || "null".equals(value.trim())) ? "" : value);
					htmlRowStructures.get(j).getHtmlTdStructure().get(k)
							.setHeight(String.valueOf((height / 20) * 1.2368));
					htmlRowStructures.get(j).getHtmlTdStructure().get(k)
							.setWidth(String.valueOf((sheet.getColumnWidth(k) / 256) * 7.425));
					tableWidth += (sheet.getColumnWidth(k) / 256) * 7.425;
				}
			}
		}
		return tableWidth;
	}

	/**
	 * 根据要显示的页数扩展html机构对象
	 * 
	 * @param htmlStructure
	 * @param copyRowNum
	 * @param pageViewNum
	 * @param detailHead
	 * @param detailTail
	 * @return
	 */
	private List<HtmlRowStructure> expendHtmlStructure(HtmlStructure htmlStructure, Integer copyRowNum,
			Integer pageViewNum, Integer detailHead, Integer detailTail) {
		List<HtmlRowStructure> htmlRowStructures = htmlStructure.getHtmlRowStructure();
		List<HtmlRowStructure> copyRow = htmlRowStructures.subList(detailHead - 1, detailTail);
		// 复制内容行的表格结构
		List<HtmlRowStructure> pasteRow = new ArrayList<HtmlRowStructure>(copyRowNum * pageViewNum);
		for (int e = 1; e < pageViewNum; e++) {
			try {
				for (HtmlRowStructure rowStructure : copyRow) {
					HtmlRowStructure copyStructure = new HtmlRowStructure();
					List<HtmlTdStructure> tdS = Lists.newArrayList();
					for (HtmlTdStructure tdStructure : rowStructure.getHtmlTdStructure()) {
						HtmlTdStructure copyTdStructure = new HtmlTdStructure();
						copyTdStructure.setColspan(tdStructure.getColspan());
						copyTdStructure.setRowspan(tdStructure.getRowspan());
						copyTdStructure.setTag(tdStructure.getTag());
						copyTdStructure.setValue(tdStructure.getValue());
						copyTdStructure.setHeight(tdStructure.getHeight());
						copyTdStructure.setWidth(tdStructure.getWidth());
						copyTdStructure.setBg_color(tdStructure.getBg_color());
						copyTdStructure.setBorder_bottom(tdStructure.getBorder_bottom());
						copyTdStructure.setBorder_bottom_color(tdStructure.getBorder_bottom_color());
						copyTdStructure.setBorder_left(tdStructure.getBorder_left());
						copyTdStructure.setBorder_left_color(tdStructure.getBorder_left_color());
						copyTdStructure.setBorder_right(tdStructure.getBorder_right());
						copyTdStructure.setBorder_right_color(tdStructure.getBorder_right_color());
						copyTdStructure.setBorder_top(tdStructure.getBorder_top());
						copyTdStructure.setBorder_top_color(tdStructure.getBorder_top_color());
						copyTdStructure.setFont_color(tdStructure.getFont_color());
						copyTdStructure.setFont_size(tdStructure.getFont_size());
						copyTdStructure.setIsBold(tdStructure.getIsBold());
						copyTdStructure.setFontFamily(tdStructure.getFontFamily());
						copyTdStructure.setVerticalAlign(tdStructure.getVerticalAlign());
						copyTdStructure.setHorizontalAlign(tdStructure.getHorizontalAlign());
						tdS.add(copyTdStructure);
					}
					copyStructure.setHtmlTdStructure(tdS);
					pasteRow.add(copyStructure);
					// pasteRow.add(rowStructure.clone());
				}
			} catch (Exception e2) {
				// TODO: handle exception
			} finally {
			}
		}
		htmlRowStructures.addAll(detailTail, pasteRow);
		return htmlRowStructures;
	}

	/**
	 * 根据表格合并情况设置整个表格每行的行数和列数，表格的合成情况在htmlTDstructure表中的tag标志位来表示是否被合并
	 * 
	 * @param addresses
	 *            所有合并项
	 * @param headers
	 *            表格头行数
	 * @param details
	 *            表格详细数据行数
	 * @param tails
	 *            表格统计栏行数
	 * @param row
	 *            表格总行数
	 * @param cells
	 *            表格总列行数
	 */
	private HtmlStructure createByRegin(List<CellRangeAddress> addresses, List<Integer> headers, List<Integer> details,
			List<Integer> tails, Integer row, Integer cell) {
		HtmlStructure htmlStructure = new HtmlStructure();
		htmlStructure.setRowNum(row);
		htmlStructure.setColumn(cell);
		// 初始化html表的数据结构
		List<HtmlRowStructure> htmlRowStructures = Lists.newArrayList();
		for (int k = 0; k <= row; k++) {
			HtmlRowStructure htmlStructureTemp = new HtmlRowStructure();
			List<HtmlTdStructure> htmlTdStructures = Lists.newArrayList();
			for (int h = 0; h < cell; h++) {
				HtmlTdStructure htmlTdStructureTemp = new HtmlTdStructure();
				htmlTdStructures.add(htmlTdStructureTemp);
			}
			htmlStructureTemp.setHtmlTdStructure(htmlTdStructures);
			htmlStructureTemp.setCellsNum(cell);
			htmlRowStructures.add(htmlStructureTemp);
		}
		// 标记被合并的单元格，计算每一行有多少个单元格
		for (int i = 0; i <= row; i++) {

			HtmlRowStructure htmlRowStructure = htmlRowStructures.get(i);
			int cellNum = htmlRowStructure.getCellsNum();
			for (CellRangeAddress address : addresses) {
				int firstColumn = address.getFirstColumn();
				int lastColumn = address.getLastColumn();
				int firstRow = address.getFirstRow();
				int lastRow = address.getLastRow();
				int reginRow = lastRow - firstRow;
				int reginCell = lastColumn - firstColumn;
				if (lastColumn >= cell) {
					System.out.print("列数设置错误");
				}
				if (firstRow > i) {
					continue;
				} else if (firstRow == i) {
					if (reginCell > 0) {
						cellNum -= reginCell;
						if (reginCell > 0) {
							for (int y = 1; y <= reginCell; y++) {
								htmlRowStructures.get(i).getHtmlTdStructure().get(firstColumn + y).setTag(true);
							}
						}
					}
					// 但出现合并行的时候，下一行需要生成的表格最好减一，再加上合并的列数并且把被合并的单元格标记为true
					if (reginRow > 0) {
						for (int j = 1; j <= reginRow; j++) {
							int otherRowCellnum = htmlRowStructures.get(i + j).getCellsNum() - 1 - reginCell;
							htmlRowStructures.get(i + j).setCellsNum(otherRowCellnum);
							htmlRowStructures.get(i + j).getHtmlTdStructure().get(firstColumn).setTag(true);
							if (reginCell > 0) {
								for (int x = 1; x <= reginCell; x++) {
									htmlRowStructures.get(i + j).getHtmlTdStructure().get(firstColumn + x).setTag(true);
								}
							}
						}
					}
				} else {

				}
			}
			htmlRowStructure.setRowNum(i);
			htmlRowStructure.setCellsNum(cellNum);
			htmlRowStructures.set(i, htmlRowStructure);
		}
		htmlStructure.setHtmlRowStructure(htmlRowStructures);
		return htmlStructure;
	}

	/**
	 * 删除被标记的单元格
	 * 
	 * @param htmlStructure
	 * @param addresses
	 * @param headers
	 * @param details
	 * @param tails
	 * @param row
	 * @param cell
	 * @return
	 */
	private HtmlStructure tableStyle(HtmlStructure htmlStructure, List<CellRangeAddress> addresses,
			List<Integer> headers, List<Integer> details, int trueRowNum, Integer row, Integer cell) {
		List<HtmlRowStructure> htmlRowStructures = htmlStructure.getHtmlRowStructure();
		for (CellRangeAddress address : addresses) {
			int firstColumn = address.getFirstColumn();
			int lastColumn = address.getLastColumn();
			int firstRow = address.getFirstRow();
			int lastRow = address.getLastRow();
			int reginRow = lastRow - firstRow;
			int reginCell = lastColumn - firstColumn;
			List<HtmlTdStructure> tdStructures = htmlRowStructures.get(firstRow).getHtmlTdStructure();
			if (reginRow > 0) {
				tdStructures.get(firstColumn).setRowspan(reginRow + 1);
			}
			if (reginCell > 0) {
				tdStructures.get(firstColumn).setColspan(reginCell + 1);
			}
		}
		// 根据表尾行数，删除剩余的row
		int tailsize = trueRowNum + 1;
		int rowSize = htmlRowStructures.size();
		for (int delRow = tailsize; delRow < rowSize; delRow++) {
			htmlRowStructures.remove(tailsize);
		}
		// 删除被标记为被合并的单元格，由于一次遍历删除不掉某一些单元格，遍历两次。待优化
		for (HtmlRowStructure htmlRowStructure : htmlRowStructures) {
			List<HtmlTdStructure> htmlTdStructures = htmlRowStructure.getHtmlTdStructure();
			Iterator<HtmlTdStructure> tdIter = htmlTdStructures.iterator();
			while (tdIter.hasNext()) {
				HtmlTdStructure td = tdIter.next();
				if (td.getTag() != null) {
					tdIter.remove();// 这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
				}
			}
		}
		return htmlStructure;
	}

	/**
	 * excel查询参数解析
	 * 
	 * @param sheet
	 * @return
	 */
	public List<ExcelForm> getExcelForm(Sheet sheet) {
		int sheet2LastRow = sheet.getLastRowNum();
		List<ExcelForm> inputs = Lists.newArrayList();
		for (int i = 1; i <= sheet2LastRow; i++) {
			Row row = sheet.getRow(i);
			ExcelForm configItem = new ExcelFormEntity();
			if ((row.getCell(0) != null) && (!Strings.isNullOrEmpty(row.getCell(0).toString()))
					&& (!Strings.isNullOrEmpty(row.getCell(2).toString()))) {
				configItem.setName(row.getCell(0).toString());
				configItem.setContent(row.getCell(1).toString());
				configItem.setVarName(row.getCell(2).toString());
				configItem
						.setClassType(!Strings.isNullOrEmpty(ExcelInputType.getValue(row.getCell(3).toString())) ? ExcelInputType
								.getValue(row.getCell(3).toString()) : "easyui-textbox");
				configItem.setComboAttr(row.getCell(4).toString());
				configItem.setDataOption(row.getCell(5).toString());
				configItem.setAcurrate(row.getCell(5).toString());
				inputs.add(configItem);
			}
		}
		return inputs;
	}
	/**
	 * 获取模板路径
	 * @param name
	 * @return
	 */
	public String getFilePath(String name) {
		String sql = "select cardpath from ptcards where cardno=?";
		EbpDao ebpDao = new EbpDao();
		List<Object> inputList = Lists.newArrayList();
		inputList.add(name);
		String path = "";
		try {
			List<Map<String, Object>> rtnList = ebpDao.queryBySql(sql, "", inputList);
			path = (String) rtnList.get(0).get("cardpath");
		} catch (EbillsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	@Override
	public void export(Context context) {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		try {
			request = (HttpServletRequest) context.getValue(EAPConstance.SERVLET_REQUEST);
			response = (HttpServletResponse) context.getValue(EAPConstance.SERVLET_REQSPONSE);
		} catch (ObjectNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String fileName = request.getParameter("templateName");// 模板名称
		Workbook workbook = ExcelToHtmlUtil.getExcelWorkBook(fileName);
		/**
		 * 解析excel系统设置模板配置信息
		 */

		Sheet sheet = workbook.getSheetAt(0);// 系统设置模板
		int systtemSetrownum = sheet.getLastRowNum();
		Map<String, String> systemSetResult = ExcelToHtmlUtil.analyzeSystemSetting(sheet, systtemSetrownum);
		Integer detailSetSheet = ExcelToHtmlUtil.paraseString(systemSetResult.get("detailSetSheet"));// 明细配置配置页码
		Integer detailTemplateSheet = ExcelToHtmlUtil.paraseString(systemSetResult.get("detailTemplateSheet"));// 明细格式模板页码
		Integer statisticSetSheet = ExcelToHtmlUtil.paraseString(systemSetResult.get("statisticSetSheet"));// 统计配置模板页码
		Integer statisticTemplateSheet = ExcelToHtmlUtil.paraseString(systemSetResult.get("statisticTemplateSheet"));// 统计格式模板页码
		Integer querySheet = ExcelToHtmlUtil.paraseString(systemSetResult.get("querySheet"));// 查询参数模板页码
		String showType = systemSetResult.get("showType");// 模板类型
		List<String> types = Arrays.asList(showType.split("&"));
		Integer setSheet = null;// 设置模板页码
		Integer templateSheet = null;// 样式模板页码

		if (types.size() < 1) {
			System.out.println("未设置模板信息");
		}
		String templateType = request.getParameter("templateType");// 获取模板类型参数
		if (Strings.isNullOrEmpty(templateType)) {
			templateType = types.get(0);
		}

		// 根据模板类型参数选择模板
		if (templateType.equals("detail")) {
			setSheet = detailSetSheet;
			templateSheet = detailTemplateSheet;
		} else if (templateType.equals("statistic")) {
			setSheet = statisticSetSheet;
			templateSheet = statisticTemplateSheet;

		} else {
			System.out.println("模板类型设置错误");
		}
		if (setSheet < 1 || templateSheet < 1 || querySheet < 1) {
			System.out.println("模板类型设置错误");
		}
		Sheet sheet1 = workbook.getSheetAt(setSheet - 1);// 配置sheet
		Sheet sheet2 = workbook.getSheetAt(templateSheet - 1);// 模板sheet
		Sheet sheet3 = workbook.getSheetAt(querySheet - 1);// 查询参数sheet

		List<ExcelForm> forms = getExcelForm(sheet3);

		/**
		 * 模板配置页面条件设置sheet1
		 */
		int rownum = sheet1.getLastRowNum();
		Map<String, String> analyzeResult = ExcelToHtmlUtil.getExcelAnalyze(sheet1, rownum);
		String templateName = analyzeResult.get("templateName");
		String javaClass = analyzeResult.get("javaClass");// 执行存储过程java类
		String preSQL = analyzeResult.get("preSQL");// 执行前sql
		String querySQL = analyzeResult.get("querySQL");// 查询sql
		String queryTag = analyzeResult.get("queryTag");// queryTag表示输出结果是data还是datasum
		String heads = analyzeResult.get("heads");// 数据头所占行
		String tails = analyzeResult.get("tails");// 数据尾所占行
		String details = analyzeResult.get("details");// 数据体所占行
		// 获取查询sql
		List<String> querySqlList = Lists.newArrayList();
		if (!Strings.isNullOrEmpty(querySQL)) {
			querySqlList = Arrays.asList(querySQL.split(";"));
		}

		// 获取执行前sql
		List<String> preSqlList = Lists.newArrayList();
		if (!Strings.isNullOrEmpty(preSQL)) {
			preSqlList = Arrays.asList(preSQL.split(";"));
		}
		List<String> queryTagList = Lists.newArrayList();
		if (!Strings.isNullOrEmpty(queryTag)) {

			queryTagList = Arrays.asList(queryTag.split(";"));
		}
		// 获取详细内容行数
		int detailHead = ExcelToHtmlUtil.paraseHead(details);// 起始位置
		int detailTail = ExcelToHtmlUtil.paraseTail(details);// 结束位置
		int copyRowNum = detailTail - detailHead + 1;// 源数据（详细内容）行数
		// 每页显示的内容条数

		// 获取表头行数
		int headsTail = ExcelToHtmlUtil.paraseTail(heads);
		// 获取表尾行数
		int tailRowNum = 0;
		if (!Strings.isNullOrEmpty(tails)) {
			int tailHead = ExcelToHtmlUtil.paraseHead(tails);
			int tailTail = ExcelToHtmlUtil.paraseTail(tails);
			tailRowNum = tailTail - tailHead + 1;//
		}

		Table<String, String, String> paramValues = HashBasedTable.create();
		// 解析查询模板中的查询条件
		for (ExcelForm form : forms) {
			try {
				paramValues.put(form.getVarName(), form.getClassType(), request.getParameter(form.getVarName()));

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		EbpDao ebpDAO = new EbpDao();
		/**
		 * 执行用户自定义java代码
		 */
		if (!Strings.isNullOrEmpty(javaClass)) {
			doPreJavaCode(javaClass, context);
		}
		Integer totalMax = getTotalMax(querySqlList, queryTagList, ebpDAO, paramValues);
		int size = 1000;
		int startNum = 0;
		int totalMaxpageNum = 0;
		if (totalMax >= size && totalMax / size == 0) {
			totalMaxpageNum = totalMax / size;
		} else {
			totalMaxpageNum = totalMax / size + 1;
		}
		/**
		 * 创建结果输出excel
		 */
		Workbook workbookExcport = null;
		if (workbook instanceof XSSFWorkbook) {
			workbookExcport = new SXSSFWorkbook(1000);
		} else if (workbook instanceof HSSFWorkbook) {
			// workbookExcport = new XSSFWorkbook();
		}
		Sheet sheetExport1 = workbookExcport.createSheet("name");
		/**
		 * 复制模板创建新的sxssf模板
		 */
		workbookExcport = ExcelToHtmlUtil.copyWbSheet(sheet2, sheetExport1, workbook, workbookExcport, copyRowNum
				+ headsTail);
		Sheet sheetExport = workbookExcport.getSheetAt(0);
	
		/**
		 * 创建视图
		 */
		doPreSQL(preSqlList, ebpDAO, paramValues);
		/**
		 * 返回所有统计数据
		 */
		List<Map<String, String>> sumResults = getExcelSUMResult(querySqlList, queryTagList, ebpDAO, paramValues);
		/**
		 * 数据库分页查询数据，向excel中插入数据
		 */
		for (int pageExportNum = 0; pageExportNum < totalMaxpageNum; pageExportNum++) {
			List<List<Map<String, String>>> dataResults = getExcelDATAResult(sheetExport, querySqlList, queryTagList,
					ebpDAO, paramValues, size, pageExportNum);
			if (dataResults.size() == 0) {
				dataResults.add(0, null);
				dataResults.add(1, sumResults);
			} else {
				dataResults.add(sumResults);
			}
			startNum = createExcel(sheetExport, sheet2, totalMax, headsTail, tailRowNum, size, startNum, copyRowNum,
					pageExportNum, workbookExcport, dataResults);
		}
		/**
		 * 清除用户通过代码创建的临时表单和数据
		 */
		if (!Strings.isNullOrEmpty(javaClass)) {
			doClean(javaClass,context);
		}
		// 设置响应
		String exportName = "";
		try {
			exportName = URLEncoder.encode(templateName, "utf-8") + "["
					+ DateFormat.getDateTimeInstance(2, 2, Locale.CHINA).format(new Date());
			response.setHeader("Content-Disposition", "inline; filename=" + exportName + "].xlsx");
			response.setContentType("application/octet-stream");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			/* 写数据到文件中 */
			ServletOutputStream os = response.getOutputStream();
			workbookExcport.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	}

	/**
	 * 获取结果的最大行数
	 * 
	 * @param querySqlList
	 * @param queryTagList
	 * @param baseDAO
	 * @param paramValues
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private int getTotalMax(List<String> querySqlList, List<String> queryTagList, EbpDao baseDAO, Table paramValues) {
		int totalSize = 0;
		for (int y = 0; y < querySqlList.size(); y++) {
			if (!Strings.isNullOrEmpty(querySqlList.get(y))) {
				String resultTag = queryTagList.get(y);
				List<Map<String, String>> totalResults = Lists.newArrayList();
				String totalSql;
				// 如果是内容项，则数据库分页查询
				totalSql = "SELECT COUNT(*) FROM(" + querySqlList.get(y) + ")";
				totalResults = baseDAO.queryBySqlExcel(totalSql, paramValues, resultTag);
				for (Map.Entry<String, String> entry : totalResults.get(0).entrySet()) {
					if (totalSize < Integer.parseInt(entry.getValue())) {// 取最大的totalSize
						totalSize = Integer.parseInt(entry.getValue());
					}
				}
			}
		}
		return totalSize;
	}

	/**
	 * 查询返回一页详细数据
	 * 
	 * @param sheetExcel
	 * @param querySqlList
	 * @param queryTagList
	 * @param baseDAO
	 * @param paramValues
	 * @param size
	 * @param pageExportNum
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<List<Map<String, String>>> getExcelDATAResult(Sheet sheetExcel, List<String> querySqlList,
			List<String> queryTagList, EbpDao baseDAO, Table paramValues, Integer size, Integer pageExportNum) {
		List<List<Map<String, String>>> allResults = Lists.newArrayList();
		String dataBaseType="";
		try {
			dataBaseType = CommonUtil.getConstant("system.DataBase");
		} catch (EbillsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int y = 0; y < querySqlList.size(); y++) {

			if (!Strings.isNullOrEmpty(querySqlList.get(y))) {
				String resultTag = queryTagList.get(y);
				String sql = "";
				if (resultTag.matches(".*DATA_.*")) {
					List<Map<String, String>> results = Lists.newArrayList();
					if(dataBaseType!=null&&dataBaseType.equals("db2")){
						sql =  "select * from ("+querySqlList.get(y)+") a where a.row_number>"+pageExportNum * size+" fetch first "+size+" rows only";
					}else{
						sql = "SELECT * FROM(SELECT A.*, rownum r FROM (" + querySqlList.get(y) + ") A WHERE rownum <="
								+ ((pageExportNum + 1) * size) + ") B WHERE r >" + pageExportNum * size;
					}
					results = baseDAO.queryBySqlExcel(sql, paramValues, resultTag);
					allResults.add(results);
				}
			}
		}
		return allResults;
	}

	/**
	 * 查询返回所有统计数据
	 * 
	 * @param querySqlList
	 * @param queryTagList
	 * @param baseDAO
	 * @param paramValues
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<Map<String, String>> getExcelSUMResult(List<String> querySqlList, List<String> queryTagList,
			EbpDao baseDAO, Table paramValues) {
		List<Map<String, String>> resultsCollect = Lists.newArrayList();
		for (int y = 0; y < querySqlList.size(); y++) {

			if (!Strings.isNullOrEmpty(querySqlList.get(y))) {

				String resultTag = queryTagList.get(y);
				if (resultTag.matches(".*DATASUM_.*")) {
					List<Map<String, String>> results = Lists.newArrayList();
					String sql = "";
					sql = querySqlList.get(y);
					results = baseDAO.queryBySqlExcel(sql, paramValues, resultTag);
					if (results.size() <= 1 && results.size() > 0) {
						resultsCollect.addAll(results);
					} else {
						System.out.println("sql类型配置出错！改为DATA类型");
					}
				}

			}
		}
		return resultsCollect;
	}

	/**
	 * 创建表结构并填充数据
	 * 
	 * @param sheetExcel
	 * @param sheet
	 * @param totalMax
	 * @param head
	 * @param tail
	 * @param size
	 * @param startNum
	 * @param copyNum
	 * @param pageExportNum
	 * @param workbook
	 * @param results
	 * @return
	 */
	private Integer createExcel(Sheet sheetExcel, Sheet sheet, Integer totalMax, Integer head, Integer tail,
			Integer size, Integer startNum, Integer copyNum, Integer pageExportNum, Workbook workbook,
			List<List<Map<String, String>>> results) {
		int resultSize = results.size();
		List<Map<String, String>> sumResults = results.get(resultSize - 1);
		/**
		 * 创建excel头
		 */
		if (startNum < head) {

			for (int a = startNum; a < head; a++) {
				Row row = sheetExcel.createRow(a);
				row.setRowStyle(sheet.getRow(a).getRowStyle());
				int cellNum = sheet.getRow(a).getLastCellNum();
				XSSFCellStyle cellStyle = null;
				XSSFCellStyle newCellStyle = null;
				RichTextString richTextString = null;
				for (int j = 0; j < cellNum; j++) {
					Cell cell = row.createCell(j);
					if (sheet.getRow(a).getCell(j) != null) {
						cellStyle = (XSSFCellStyle) sheet.getRow(a).getCell(j).getCellStyle();
						richTextString = sheet.getRow(a).getCell(j).getRichStringCellValue();
						newCellStyle = ExcelToHtmlUtil.copyCellStyle(cellStyle, (SXSSFWorkbook) workbook);
						cell.setCellStyle(newCellStyle);
						cell.setCellValue(richTextString);
						String content = cell.getRichStringCellValue().toString();
						String regex = "\\{(.*?)\\}";
						Pattern p = Pattern.compile(regex);
						Matcher m = p.matcher(content);
						m.find();
						String n="";
						String regexValue="";
						try {
							String regx = "{"+m.group(1)+"}";
							for (Map<String, String> map : sumResults) {
									if (cell.getRichStringCellValue() != null) {
										if(!Strings.isNullOrEmpty(map.get(m.group(1)))){
											regexValue = map.get(m.group(1));
											break;
										}
									}
							}
							n = content.replace(regx, regexValue);
							cell.setCellValue(n);
						} catch (IllegalStateException e) {
							cell.setCellValue(richTextString);
							// TODO: handle exception
						}
					} else {
						newCellStyle = ExcelToHtmlUtil.copyCellStyle(cellStyle, (SXSSFWorkbook) workbook);
						cell.setCellStyle(newCellStyle);
					}
				}
				startNum++;
			}
			ExcelToHtmlUtil.mergeSheetInDetail(sheet, sheetExcel, 0, head, startNum, head);
		}
		/**
		 * 创建excel主数据结构
		 */
		if (startNum >= head && (startNum + size * copyNum) < (totalMax * copyNum + head)) {

			for (int a = 0; a < size; a++) {
				for (int i = 0; i < (results.size() - 1); i++) {
					List<Map<String, String>> result = results.get(i);
					for (int k = 0; k < copyNum; k++) {
						Row row = sheetExcel.createRow(startNum);
						row.setRowStyle(sheet.getRow(head + k).getRowStyle());
						int cellNum = sheet.getRow(head + k).getLastCellNum();
//						XSSFCellStyle cellStyle = null;
						RichTextString richTextString = null;
						for (int j = 0; j < cellNum; j++) {
							Cell cell = row.createCell(j);
							if (sheet.getRow(head + k).getCell(j) != null) {
//								cellStyle = (XSSFCellStyle) sheet.getRow(head + k).getCell(j).getCellStyle();
								richTextString = sheet.getRow(head + k).getCell(j).getRichStringCellValue();
//								cell.setCellStyle(cellStyle);
								cell.setCellValue(richTextString);
								if (cell.getRichStringCellValue() != null) {
									try {
										String content = cell.getRichStringCellValue().toString();
										String regex = "\\{(.*?)\\}";
										Pattern p = Pattern.compile(regex);
										Matcher m = p.matcher(content);
										m.find();
										String n="";
										String regexValue="";
										String regx = "{"+m.group(1)+"}";
										if (result != null && result.size() > a) {
											Map<String, String> map = result.get(a);
											if (!Strings.isNullOrEmpty(map.get(m.group(1)))) {
												
												regexValue = (!Strings.isNullOrEmpty(map.get(m.group(1)))) ? map.get(m.group(1)): "";
											} else {
												for (Map<String, String> map2 : sumResults) {
													if (!Strings.isNullOrEmpty(map2.get(m.group(1)))) {
														regexValue = (!Strings.isNullOrEmpty(map2.get(m.group(1)))) ? map2.get(m.group(1)): "";
														break;
													}
												}
											}
										} else {
											for (Map<String, String> map2 : sumResults) {
												if (!Strings.isNullOrEmpty(map2.get(m.group(1)))) {
													regexValue = (!Strings.isNullOrEmpty(map2.get(m.group(1)))) ? map2.get(m.group(1)): "";
													break;
												}
											}
										}
										n = content.replace(regx, regexValue);
										cell.setCellValue(n);
									} catch (IllegalStateException e) {
										cell.setCellValue(richTextString);
										// TODO: handle exception
									}
								} else {
									cell.setCellValue("");
								}
							} else {
//								cell.setCellStyle(cellStyle);
							}
						}
						startNum++;
					}
				}
				ExcelToHtmlUtil.mergeSheetInDetail(sheet, sheetExcel, head, copyNum, startNum, copyNum + head);
			}
			// 档数据的条数小于size时
		} else if (startNum >= head && ((startNum + size * copyNum) >= (totalMax * copyNum + head))
				&& (startNum < (totalMax * copyNum + head + tail))) {
			Integer start = startNum;
			for (int a = 0; a < (totalMax * copyNum + head - start) / copyNum; a++) {
				for (int i = 0; i < results.size() - 1; i++) {
					List<Map<String, String>> result = results.get(i);
					for (int k = 0; k < copyNum; k++) {
						Row row = sheetExcel.createRow(startNum);
						row.setRowStyle(sheet.getRow(head + k).getRowStyle());
						int cellNum = sheet.getRow(head + k).getLastCellNum();
//						XSSFCellStyle cellStyle = null;
						RichTextString richTextString = null;
						for (int j = 0; j < cellNum; j++) {
							Cell cell = row.createCell(j);
							if (sheet.getRow(head + k).getCell(j) != null) {
//								cellStyle = (XSSFCellStyle) sheet.getRow(head + k).getCell(j).getCellStyle();
								richTextString = sheet.getRow(head + k).getCell(j).getRichStringCellValue();
//								cell.setCellStyle(cellStyle);
								cell.setCellValue(richTextString);
								if (!Strings.isNullOrEmpty(cell.getRichStringCellValue().toString())) {
									try {
										String content = cell.getRichStringCellValue().toString();
										String regex = "\\{(.*?)\\}";
										Pattern p = Pattern.compile(regex);
										Matcher m = p.matcher(content);
										m.find();
										String n="";
										String regexValue="";
										String regx = "{"+m.group(1)+"}";
										if (result != null && result.size() > a) {
											Map<String, String> map = result.get(a);
											if (!Strings.isNullOrEmpty(map.get(m.group(1)))) {
												
												regexValue = (!Strings.isNullOrEmpty(map.get(m.group(1)))) ? map.get(m.group(1)): "";
											} else {
												for (Map<String, String> map2 : sumResults) {
													if (!Strings.isNullOrEmpty(map2.get(m.group(1)))) {
														regexValue = (!Strings.isNullOrEmpty(map2.get(m.group(1)))) ? map2.get(m.group(1)): "";
														break;
													}
												}
											}
										} else {
											for (Map<String, String> map2 : sumResults) {
												if (!Strings.isNullOrEmpty(map2.get(m.group(1)))) {
													regexValue = (!Strings.isNullOrEmpty(map2.get(m.group(1)))) ? map2.get(m.group(1)): "";
													break;
												}
											}
										}
										n = content.replace(regx, regexValue);
										cell.setCellValue(n);
									} catch (IllegalStateException e) {
										cell.setCellValue(richTextString);
										// TODO: handle exception
									}
								} else {
									cell.setCellValue("");
								}
							} else {
//								cell.setCellStyle(cellStyle);
							}
						}
						startNum++;
					}
				}
				ExcelToHtmlUtil.mergeSheetInDetail(sheet, sheetExcel, head, copyNum, startNum, copyNum + head);

			}
		}
		/**
		 * 创建excel表尾结构
		 */
		if (startNum >= (totalMax * copyNum + head)) {
			int k = 0;

			for (int q = 0; q < tail; q++) {
				Row row = sheetExcel.createRow(startNum);
				int cellNum = sheet.getRow(head + copyNum + k).getLastCellNum();
				XSSFCellStyle cellStyle = null;
				XSSFCellStyle newCellStyle = null;
				RichTextString richTextString = null;
				for (int j = 0; j < cellNum; j++) {
					Cell cell = row.createCell(j);
					if (sheet.getRow(head + copyNum + k) != null && sheet.getRow(head + copyNum + k).getCell(j) != null) {
						cellStyle = (XSSFCellStyle) sheet.getRow(head + copyNum + k).getCell(j).getCellStyle();
						richTextString = sheet.getRow(head + copyNum + k).getCell(j).getRichStringCellValue();
						newCellStyle = ExcelToHtmlUtil.copyCellStyle(cellStyle, (SXSSFWorkbook) workbook);
						cell.setCellStyle(newCellStyle);
						cell.setCellValue(richTextString);
						try {
							if (cell.getRichStringCellValue() != null) {
								String content = cell.getRichStringCellValue().toString();
								String regex = "\\{(.*?)\\}";
								Pattern p = Pattern.compile(regex);
								Matcher m = p.matcher(content);
								m.find();
								String regx = "{"+m.group(1)+"}";
								String regexValue="";
								String n="";
								for (Map<String, String> map : sumResults) {
									if (cell.getRichStringCellValue() != null) {
										if(!Strings.isNullOrEmpty(map.get(m.group(1)))){
											regexValue = map.get(m.group(1));
											break;
										}
									}
								}
								n = content.replace(regx, regexValue);
								cell.setCellValue(n);
							} else {
								cell.setCellValue("");
							}
						} catch (IllegalStateException e) {
							cell.setCellValue(richTextString);
							// TODO: handle exception
						}
					} else {
						cell.setCellStyle(newCellStyle);
					}
				}
				startNum++;
				k++;
			}
			ExcelToHtmlUtil.mergeSheetInDetail(sheet, sheetExcel, head + copyNum, head + copyNum + tail, startNum, head
					+ copyNum + tail);
		}
		return startNum;
	}

	/**
	 * 创建视图
	 * 
	 * @param preSQL
	 * @param ebpDAO
	 * @param paramValues
	 */
	@SuppressWarnings("rawtypes")
	private void doPreSQL(List<String> preSqlList, EbpDao ebpDAO, Table paramValues) {
		try {
			for (String sql : preSqlList) {
				if (Strings.isNullOrEmpty(sql)) {
					ebpDAO.doCreateView(sql, paramValues);
				}
			}
		} catch (EbillsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 执行自定义java代码
	 * @param javaClass
	 * @param context
	 */
	private void doPreJavaCode(String javaClass, Context context) {
		try {
			Class<?> demo = Class.forName(javaClass);
			// Constructor<?> demoStructure = demo.getConstructor();
			Object o = demo.newInstance();
			Method method = o.getClass().getDeclaredMethod("execute", new Class[] { Context.class});
			method.invoke(o, new Object[] { context });
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 用户自定义表或者数据清除
	private void doClean(String javaClass, Context context) {
		try {
			Class<?> demo = Class.forName(javaClass);
			// Constructor<?> demoStructure = demo.getConstructor();
			Object o = demo.newInstance();
			Method method = o.getClass().getDeclaredMethod("clean",new Class[] { Context.class});
			method.invoke(o,new Object[] { context });
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
