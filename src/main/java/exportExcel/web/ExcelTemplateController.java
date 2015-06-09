package exportExcel.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import exportExcel.entity.ConfigItem;
import exportExcel.entity.ExcelTemplate;
import exportExcel.service.ConfigItemService;
import exportExcel.service.ExcelTemplateService;
import exportExcel.utils.Response;
import exportExcel.web.entity.ExcelTemplateView;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * excel模板解析
 * 
 * @author Page(hxtoyou@163.com)
 * 
 */
@Controller
public class ExcelTemplateController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ExcelTemplateService excelTemplateService;
	@Autowired
	private ConfigItemService configItemService;
	/**
	 * 上传文件路径
	 */
	public static final String DEFAULT_PATH = "/WEB-INF/uploads/template";
	private final String defaultUploadPath = DEFAULT_PATH;
	/**
	 * Web JSP 源码前缀.
	 */
	private static final String JSP_SOURCE_PREFIX = "/WEB-INF/views";
	private static final String ENCODING = "UTF-8";

	@RequestMapping(value = "/supervise/tours/export", method = RequestMethod.GET)
	public ModelAndView export(HttpSession httpSession, String className, String accountName, String mineName,
			String type, Date tourStartDate, Date tourEndDate, Long belongCounty, Long groupId) {
		Map<String, Object> model = Maps.newHashMap();
		if (tourStartDate == null) {
			model.put("startTime", " ");
		} else {
			model.put("startTime", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(tourStartDate));
		}
		if (tourEndDate == null) {
			model.put("endTime", "现在");
		} else {
			model.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(tourEndDate));
		}
		ExcelTemplateView excelTemplateView = new ExcelTemplateView();
		excelTemplateView.setUrl("template/ToursExcel");
		return new ModelAndView(excelTemplateView, model);
	}

	@RequestMapping(value = "/exportExcel/analyze", method = RequestMethod.GET)
	public String TempalteAnalayze(@RequestBody String templateName, HttpSession httpSession)
			throws FileNotFoundException, IOException {
		ExcelTemplate excelTemplate = new ExcelTemplate();
		String path = httpSession.getServletContext().getRealPath(defaultUploadPath);
		Workbook workbook = getExcelWorkBook(path + "/2015-05/test4-21130107-783255.xlsx");
		/**
		 * 模板配置页面条件设置sheet1
		 */
		Sheet sheet1 = workbook.getSheetAt(0);
		int lastRow = sheet1.getLastRowNum();
		String preSQL = "";// 查询钱执行sql
		String querySQL = "";// 查询sql
		String queryTag = "";// queryTag表示输出结果是data还是datasum
		String queryParamaters = "";
		String queryConvert = "";
		for (int i = 1; i <= lastRow; i++) {
			Row row = sheet1.getRow(i);
			/**
			 * 执行SQL匹配
			 */
			String preSQLpattern = "执行SQL_*[0-9]{0,}$";
			Pattern pre = Pattern.compile(preSQLpattern);
			Matcher preM = pre.matcher(row.getCell(0).toString());
			if (preM.find()) {
				if (!Strings.isNullOrEmpty(row.getCell(1).toString())) {
					preSQL += row.getCell(1).toString() + ";";
				}
			}
			/**
			 * 查询sql匹配
			 */
			String querySQLpattern = "查询SQL_*[0-9]{0,}$";

			// 创建 Pattern 对象
			Pattern query = Pattern.compile(querySQLpattern);
			// 现在创建 matcher 对象
			Matcher queryM = query.matcher(row.getCell(0).toString());
			int h = 1;
			if (queryM.find()) {
				if (!Strings.isNullOrEmpty(row.getCell(1).toString())) {
					querySQL += row.getCell(1).toString() + ";";
					queryParamaters += row.getCell(4).toString() + ";";
					queryTag += row.getCell(2).toString() + ";";
					queryConvert += row.getCell(5) != null ? row.getCell(5).toString() : "" + ";";
				}
			}

			/**
			 * 配置页基本选项配置
			 */
			if (row.getCell(0).toString().equals("报表名称")) {
				excelTemplate.setExcelName(row.getCell(1).toString());
			}
			if (row.getCell(0).toString().equals("表头行")) {
				excelTemplate.setHeadRows(row.getCell(1).toString());
			}
			if (row.getCell(0).toString().equals("总列数")) {
				excelTemplate.setColumnNum(row.getCell(1).toString());
			}
			if (row.getCell(0).toString().equals("数据体行")) {
				excelTemplate.setDetailRows(row.getCell(1).toString());
			}
			if (row.getCell(0).toString().equals("表尾行")) {
				excelTemplate.setTailRows(row.getCell(1).toString());
			}

		}
		/**
		 * 条件保存
		 */
		if (preSQL.length() > 0) {
			excelTemplate.setPreSQL(preSQL);
		}
		if (querySQL.length() > 0) {
			excelTemplate.setQuerySQL(querySQL);
			excelTemplate.setQueryParamater(queryParamaters);
			excelTemplate.setQueryTag(queryTag);
			excelTemplate.setQueryConvertor(queryConvert);
		}

		/**
		 * 查询条件模板页条件解析sheet2
		 * 
		 * 
		 */
		Sheet sheet2 = workbook.getSheetAt(1);
		int sheet2LastRow = sheet2.getLastRowNum();
		List<ConfigItem> configItems = Lists.newArrayList();

		for (int i = 1; i <= sheet2LastRow; i++) {
			Row row = sheet2.getRow(i);
			ConfigItem configItem = new ConfigItem();
			if ((!Strings.isNullOrEmpty(row.getCell(0).toString()))
					&& (!Strings.isNullOrEmpty(row.getCell(2).toString()))) {
				configItem.setName(row.getCell(0).toString());
				configItem.setContent(row.getCell(1).toString());
				configItem.setVarName(row.getCell(2).toString());
				configItem.setMemo(row.getCell(3).toString());
				configItem.setAcurrate(row.getCell(4).toString());
				configItemService.save(configItem);
				// configItem.setNeccesary(row.getCell(4).toString());
				configItems.add(configItem);
			}
		}
		excelTemplate.setConfigItem(configItems);
		// // String value = firstRow.getCell(0).toString();
		// /**
		// * 输入限制项模板页解析sheet4
		// *
		// *
		// */
		// Sheet sheet4 = workbook.getSheetAt(3);
		// int sheet4LastRow = sheet4.getLastRowNum();
		// List<SystemSetting> systemSetting = Lists.newArrayList();
		// for (int i = 0; i <= sheet4LastRow; i++) {
		// Row row = sheet2.getRow(i);
		// ConfigItem configItem = new ConfigItem();
		// configItem.setName(row.getCell(0).toString());
		// configItem.setContent(row.getCell(1).toString());
		// configItem.setVarName(row.getCell(2).toString());
		// configItem.setMemo(row.getCell(3).toString());
		// // configItem.setNeccesary(row.getCell(4).toString());
		// configItems.add(configItem);
		// }
		excelTemplateService.save(excelTemplate);
		return "excel/index";
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

	/**
	 * 生成html表单文件
	 * 
	 * @param templateId
	 * @return
	 */
	@RequestMapping(value = "/excel/create/html", method = RequestMethod.GET)
	@ResponseBody
	public Response index(Long templateId, HttpSession httpSession) {
		String sessionpath = httpSession.getServletContext().getRealPath(JSP_SOURCE_PREFIX);
		String path = sessionpath + "/common";
		String ftl = "jsp.ftl";
		ExcelTemplate excelTemplate = excelTemplateService.get(templateId);
		generateFiles(path, ftl, excelTemplate.getConfigItem());
		return new Response(excelTemplateService.get(templateId));
	}

	/**
	 * 生成文件
	 * 
	 * @param path
	 * @param ftl
	 * @param configItems
	 */
	private static void generateFiles(final String path, final String ftl, List<ConfigItem> configItems) {
		try {
			Template template = getConfiguration(path).getTemplate(ftl, ENCODING);
			FileUtils.forceMkdir(new File(path));
			Writer writer = new FileWriter(path + "/index.jsp");
			Map<String, List<ConfigItem>> root = new HashMap<String, List<ConfigItem>>();
			root.put("root", configItems);
			template.process(root, writer);
			writer.flush();
		} catch (IOException e) {
			System.err.println("读取不到模板文件");
			e.printStackTrace();
		} catch (TemplateException e) {
			System.err.println("模板文件异常");
		}
	}

	/**
	 * 获取默认配置.
	 * 
	 * @return 模板配置
	 * @throws IOException
	 */
	private static Configuration getConfiguration(String path) throws IOException {
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(path));
		cfg.setCacheStorage(new NullCacheStorage());
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		return cfg;
	}

	@RequestMapping(value = "/common", method = RequestMethod.GET)
	public String spmiAqkDailyReform() {
		return "common/index";
	}

	@RequestMapping(value = "/excel", method = RequestMethod.GET)
	public String getExcel() {
		return "excel/index";
	}
}
