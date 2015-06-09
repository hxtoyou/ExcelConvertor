package exportExcel.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import exportExcel.entity.ExcelTemplate;
import exportExcel.service.ExcelTemplateService;
import exportExcel.service.LibreOfficeConverterService;
import exportExcel.service.UploadedFileService;
import exportExcel.utils.DataConvert;
import exportExcel.utils.ExcelToHtmlStyle;
import exportExcel.utils.Page;
import exportExcel.utils.Response;
import exportExcel.web.entity.HtmlRowStructure;
import exportExcel.web.entity.HtmlStructure;
import exportExcel.web.entity.HtmlTdStructure;

/**
 * @author Xiao He(hxtoyou@163.com)
 * 
 * 
 */
@Controller
public class ExcelToHtmlController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	public static final String DEFAULT_PATH = "/WEB-INF";
	public static final String DEFAULT_XLS_PATH = "/WEB-INF/uploads/template";
	@Autowired
	private LibreOfficeConverterService libreOfficeConverterService;
	@Autowired
	private ExcelTemplateService excelTemplateService;
	@Autowired
	private UploadedFileService uploadedFileService;

	/**
	 * openoffice把xml转为excel
	 * 
	 * @param templateId
	 * @param httpSession
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/excel/excel_html", method = RequestMethod.GET)
	@ResponseBody
	public Response index(Long templateId, HttpSession httpSession) {
		String sessionpath = httpSession.getServletContext().getRealPath(DEFAULT_PATH);
		String path = sessionpath + "/uploads/template/Indexkeywords_30Aug2011.xls";
		String destPath = sessionpath + "/views/common/html.html";
		libreOfficeConverterService.service(path, destPath);
		return new Response("success");
	}

	/**
	 * 读取excel样式生成html
	 * 
	 * @param templateId
	 * @param httpSession
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/excel/generate/html", method = RequestMethod.GET)
	@ResponseBody
	public Response generate(Page<HtmlStructure> page, HttpSession httpSession, Long tempId, Long fileId)
			throws ParseException {
		// page.setPageSize(pageSize);
		// page.setPageNumber(pageNumber);
		logger.debug("xsssss");
		logger.debug("fileId:{}",fileId);
		System.out.println("xxxxxxxx");
		String p = uploadedFileService.get(fileId).getFilePath();
		String path = httpSession.getServletContext().getRealPath("WEB-INF" + p);
		logger.debug("path:{}", path);
		Workbook workbook = getExcelWorkBook(path);
		Sheet sheet1 = workbook.getSheetAt(2);
		int rownum = sheet1.getLastRowNum();
		int cellnum = 0;
		int sheetmergerCount = sheet1.getNumMergedRegions();
		List<CellRangeAddress> addresses = Lists.newArrayList();
		// 遍历合并单元格

		for (int i = 0; i < sheetmergerCount; i++) {
			// 获得合并单元格加入list中
			CellRangeAddress ca = sheet1.getMergedRegion(i);

			addresses.add(ca);
		}
		// 获取模板数据
		ExcelTemplate excelTemplate = excelTemplateService.get(tempId);
		// 获取中列数
		cellnum = (int) Math.floor(Double.parseDouble(excelTemplate.getColumnNum()));
		ExcelToHtmlStyle excelToHtmlStyle = new ExcelToHtmlStyle();
		HtmlStructure htmlStructure = excelToHtmlStyle.createByRegin(addresses, null, null, null, rownum, cellnum);
		// htmlStructure.getHtmlRowStructure().add(index, List <
		// HtmlRowStructure > lists);
		List<HtmlRowStructure> htmlRowStructures = htmlStructure.getHtmlRowStructure();
		// 获取查询sql
		String sql = excelTemplate.getQuerySQL();
		List<String> querySql = Lists.newArrayList();
		if (sql != null) {
			querySql = Arrays.asList(sql.split(";"));
		}
		String tag = excelTemplate.getQueryTag();
		List<String> queryTag = Lists.newArrayList();
		if (tag != null) {

			queryTag = Arrays.asList(tag.split(";"));
		}
		// 获取变量转换格式
		String convertContent = excelTemplate.getQueryConvertor();
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		if (!Strings.isNullOrEmpty(convertContent)) {

			List<String> convertList = Arrays.asList(convertContent.split(";"));
			for (String conv : convertList) {
				DataConvert.ConvertAll(map, conv);
			}
		}
		// 获取详细内容行数
		String details = excelTemplate.getDetailRows();
		int detailHead = paraseHead(details);// 起始位置
		int detailTail = paraseTail(details);// 结束位置
		int copyRowNum = detailTail - detailHead + 1;// 源数据（详细内容）行数
		// 每页显示的内容条数
		int pageViewNum = (page.getPageSize() / copyRowNum) < 1 ? 1 : (page.getPageSize() / copyRowNum);
		// 显示数据的起始位置
		int dataStartIndex = pageViewNum * page.getPageNumber();
		// 获取表头行数
		String heads = excelTemplate.getHeadRows();
		int headsTail = paraseTail(heads);
		// 获取表尾行数
		String tails = excelTemplate.getTailRows();
		int tailHead = paraseHead(tails);
		int tailTail = paraseTail(tails);
		int tailRowNum = tailTail - tailHead + 1;//
		// table模板的行数
		int trueRowNum = headsTail + copyRowNum + tailRowNum;// 排除空行后的table结构的真实行数
		String paramaters = excelTemplate.getQueryParamater();

		int contentEnd = trueRowNum - tailRowNum + copyRowNum * pageViewNum - copyRowNum;// 加上N条源数据后详细内容加上表头的行数
		int detailLineNum = contentEnd - headsTail;// 详细内容行数
		List<String> paramaterList = Arrays.asList(paramaters.split(";"));
		Double tableWidth = 0d;
		// 向单元格填充结构数据
		for (int j = 0; j < trueRowNum; j++) {
			if (sheet1.getRow(j) != null) {
				int thisCell = sheet1.getRow(j).getLastCellNum();
				for (int k = 0; k < thisCell; k++) {
					String value = "";
					if (sheet1.getRow(j).getCell(k) != null) {
						try {
							value = sheet1.getRow(j).getCell(k).getRichStringCellValue().toString();

						} catch (IllegalStateException e) {
							value = String.valueOf(sheet1.getRow(j).getCell(k).getNumericCellValue());
							// TODO: handle exception
						}
					}
					htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_top(sheet1.getRow(j).getCell(k).getCellStyle().getBorderTop());
					htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_left(sheet1.getRow(j).getCell(k).getCellStyle().getBorderLeft());
					htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_right(sheet1.getRow(j).getCell(k).getCellStyle().getBorderRight());
					htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_bottom(sheet1.getRow(j).getCell(k).getCellStyle().getBorderBottom());
					htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_top_color(sheet1.getRow(j).getCell(k).getCellStyle().getTopBorderColor());
					htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_left_color(sheet1.getRow(j).getCell(k).getCellStyle().getLeftBorderColor());
					htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_right_color(sheet1.getRow(j).getCell(k).getCellStyle().getRightBorderColor());
					htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_bottom_color(sheet1.getRow(j).getCell(k).getCellStyle().getBottomBorderColor());
					htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBg_color(sheet1.getRow(j).getCell(k).getCellStyle().getFillBackgroundColor());
					logger.debug("Color:{}",sheet1.getRow(j).getCell(k).getCellStyle().getFillBackgroundColorColor());
					htmlRowStructures.get(j).getHtmlTdStructure().get(k).setFont_color(sheet1.getRow(j).getCell(k).getCellStyle().getFillForegroundColor());
					htmlRowStructures.get(j).getHtmlTdStructure().get(k).setFont_size(sheet1.getRow(j).getCell(k).getCellStyle().getFontIndex());
					Short height = sheet1.getRow(j).getHeight();
					htmlRowStructures.get(j).getHtmlTdStructure().get(k).setValue(value);
					htmlRowStructures.get(j).getHtmlTdStructure().get(k)
							.setHeight(String.valueOf((height / 20) * 1.2368));
					htmlRowStructures.get(j).getHtmlTdStructure().get(k)
							.setWidth(String.valueOf((sheet1.getColumnWidth(k) / 256) * 7.425));
					tableWidth += (sheet1.getColumnWidth(k) / 256) * 7.425;
				}
			}
		}
		// table宽度
		htmlStructure.setTableWidth(tableWidth / trueRowNum);
		rownum += copyRowNum;
//		String sessionpath = httpSession.getServletContext().getRealPath(DEFAULT_PATH);
//		String tablePath = sessionpath + "/views/common";
//		String ftl = "table.ftl";
		HtmlStructure htmlStructure2 = excelToHtmlStyle.tableStyle(htmlStructure, addresses, null, null,
				trueRowNum - 1, rownum, cellnum);
		// 控制内容行数
		List<HtmlRowStructure> htmlRowStructures2 = htmlStructure2.getHtmlRowStructure();
		List<HtmlRowStructure> copyRow = htmlRowStructures2.subList(detailHead - 1, detailTail);
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
		htmlRowStructures2.addAll(detailTail, pasteRow);
		int totalSize = 0;
		// 执行查询sql
		logger.debug("pageViewNum:{}", pageViewNum);
		for (int y = 0; y < querySql.size(); y++) {
			if (!Strings.isNullOrEmpty(querySql.get(y))) {

				String paramAll = paramaterList.get(y);
				String resultTag = queryTag.get(y);
				Query resultQuery = null;
				// 如果是内容项，则数据库分页查询
				// String limitSQL =
				// "SELECT * FROM(SELECT A.*, rownum r FROM (SELECT * FROM S_EXCEL_TEMPLATES) A WHERE rownum <= 2) B WHERE r > 0";
				if (resultTag.equals("DATA")) {
					logger.debug("dataStartIndex:{}", dataStartIndex);
					resultQuery = excelTemplateService.getDAO().createSQLQuery(
							"SELECT * FROM(SELECT A.*, rownum r FROM (" + querySql.get(y) + ") A WHERE rownum <="
									+ (dataStartIndex + pageViewNum) + ") B WHERE r >" + dataStartIndex);
					totalSize = Integer.parseInt(String.valueOf(excelTemplateService.getDAO()
							.createSQLQuery("SELECT COUNT(*) FROM(" + querySql.get(y) + ")").list().get(0)));
					logger.debug("total:{}", totalSize);

					// querySql.get(y) + " LIMIT " + dataStartIndex + "," +
					// pageViewNum);
					// resultQuery =
					// excelTemplateService.getDAO().createQuery(querySql.get(y));
					// resultQuery.setFirstResult(dataStartIndex);
					// resultQuery.setMaxResults(pageViewNum);
				} else {// 如果不是则不用数据库分页
					String hasWhere = "where";
					Pattern query = Pattern.compile(hasWhere);
					Matcher queryM = query.matcher(querySql.get(y));
					if (queryM.find()) {
						resultQuery = excelTemplateService.getDAO()
								.createSQLQuery(querySql.get(y) + " and ROWNUM <= 1");
					} else {
						resultQuery = excelTemplateService.getDAO().createSQLQuery(
								querySql.get(y) + " where ROWNUM <= 1");
					}
				}
				List<String> paramList = Arrays.asList(paramAll.split(","));// 输出数据结果集
				List<Object[]> results = Lists.newArrayList();
				results = resultQuery.list();
				List<Map<String, String>> result = new ArrayList<Map<String, String>>();
				for (int z = 0; z < results.size(); z++) {
					Map<String, String> columnData = new HashMap<String, String>();
					for (int x = 0; x < paramList.size(); x++) {
						try {
							columnData.put(paramList.get(x), String.valueOf(String.valueOf(results.get(z)[x])));// 默认数据表中第一列是id所以排除掉
						} catch (ClassCastException e) {
							Integer count = Integer.parseInt(String.valueOf(results.get(0)));// 捕获统计结果类型为int,无法转为object对象的异常
							columnData.put(paramList.get(x), String.valueOf(count));
							// TODO: handle exception
						}
					}
					result.add(columnData);
				}
				// 谈查询结果只有一行的时候（大多数时候是统计查询），则直接遍历所有excel单元格向里面填充数据
				if (result.size() <= 1 && result.size() > 0) {
					for (HtmlRowStructure addRowData : htmlRowStructures2) {
						for (HtmlTdStructure tdStructure : addRowData.getHtmlTdStructure()) {
							for (Map.Entry<String, String> entry : result.get(0).entrySet()) {// 取出第n条数据
								if (tdStructure.getValue() != null) {
									if (tdStructure.getValue().matches(".*" + entry.getKey() + ".*?")) {
										if (map.get(entry.getKey()) != null) {
											if (map.get(entry.getKey()).containsKey("DATE")) {
												tdStructure.setValue(DataConvert.String2String(entry.getValue(), map
														.get(entry.getKey()).get("DATE")));
											}
											if (map.get(entry.getKey()).containsKey("NUMBER")) {
												tdStructure.setValue(DataConvert.NumberFormat(entry.getValue(), map
														.get(entry.getKey()).get("NUMBER")));
											}
											if (map.get(entry.getKey()).containsKey("DEFUALT")) {
												tdStructure.setValue(DataConvert.defualtString(entry.getValue(), map
														.get(entry.getKey()).get("DEFUALT")));
											}

											if (map.get(entry.getKey()).containsKey("CONVERT")) {
												String vl = DataConvert.convertMap(
														map.get(entry.getKey()).get("CONVERT")).get(entry.getValue());
												tdStructure.setValue(vl);
											}
										} else {
											tdStructure.setValue(entry.getValue());
										}
									}
								}
							}
						}
					}

				}
				// 向表格填充内容数据
				// int n = dataStartIndex;// 设置数据从哪一行开始取
				int n = 0;
				int l = 0;
				for (int q = 0; q < detailLineNum / copyRowNum; q++) {// 取N条data,如果表格的源数据是N行，那么当一行处理只取一条数据
					if (result.size() - n >= (pageViewNum - q)) {// 当结果集不能填满整个表格的时候判断
						for (int a = 0; a < copyRowNum; a++) {
							HtmlRowStructure addRowData = htmlRowStructures2.get(headsTail + l * copyRowNum + a);// 取出一行的表格结构数据
							List<HtmlTdStructure> htmlTdStructures = addRowData.getHtmlTdStructure();
							for (Map.Entry<String, String> entry : result.get(n).entrySet()) {// 取出第n条数据
								for (int s = 0; s < htmlTdStructures.size(); s++) {
									if (!Strings.isNullOrEmpty(htmlTdStructures.get(s).getValue())) {
										if (htmlTdStructures.get(s).getValue().matches(".*" + entry.getKey() + ".*?")) {
											if (map.get(entry.getKey()) != null) {
												if (map.get(entry.getKey()).containsKey("DATE")) {
													htmlTdStructures.get(s).setValue(
															DataConvert.String2String(entry.getValue(),
																	map.get(entry.getKey()).get("DATE")));
												}
												if (map.get(entry.getKey()).containsKey("NUMBER")) {
													htmlTdStructures.get(s).setValue(
															DataConvert.NumberFormat(entry.getValue(),
																	map.get(entry.getKey()).get("NUMBER")));
												}
												if (map.get(entry.getKey()).containsKey("DEFUALT")) {
													htmlTdStructures.get(s).setValue(
															DataConvert.defualtString(entry.getValue(),
																	map.get(entry.getKey()).get("DEFUALT")));
												}
												if (map.get(entry.getKey()).containsKey("CONVERT")) {
													logger.debug("getKEY:{}", "CONVERT");
													logger.debug("entryValue:{}", entry.getValue());
													String vl = DataConvert.convertMap(
															map.get(entry.getKey()).get("CONVERT")).get(
															entry.getValue());
													htmlTdStructures.get(s).setValue(vl);
												}
											} else {
												htmlTdStructures.get(s).setValue(entry.getValue());
											}
										}
									}
								}
							}
						}
						n++;
						l++;
					}
				}
			}
		}
		Map<String, HtmlStructure> result = new HashMap<String, HtmlStructure>();
		result.put("result", htmlStructure);
		page.setTotalCount(totalSize);
		page.setPageSize(pageViewNum);
		List<HtmlStructure> resultHtmlStructure = Lists.newArrayList();
		resultHtmlStructure.add(htmlStructure);
		page.setResult(resultHtmlStructure);
		// result.put("totalSize", totalSize);
		// result.put("pageViewNum", pageViewNum);
		// TemplateGenerator.generateFiles(tablePath, ftl, htmlStructure);
		return new Response(page);

	}

	/**
	 * 解析起始行
	 * 
	 * @param details
	 * @return
	 */
	private int paraseHead(String details) {
		List<String> headsNum = Arrays.asList(details.split(","));
		int headsHead = (int) Math.floor(Double.parseDouble(headsNum.get(0)));
		return headsHead;
	}

	/**
	 * 解析结束行
	 * 
	 * @param details
	 * @return
	 */
	private int paraseTail(String details) {
		List<String> headsNum = Arrays.asList(details.split(","));
		int headsTail = (int) Math.floor(Double.parseDouble(headsNum.get(headsNum.size() - 1)));
		return headsTail;
	}

	/**
	 * 根据文件的路径创建Workbook对象
	 * 
	 * @param filePath
	 */
	private Workbook getExcelWorkBook(String filePath) {
		InputStream ins = null;
		Workbook book = null;
		try {
			ins = new FileInputStream(new File(filePath));
			// ins=
			// ExcelService.class.getClassLoader().getResourceAsStream(filePath);
			book = WorkbookFactory.create(ins);
			ins.close();
			return book;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
