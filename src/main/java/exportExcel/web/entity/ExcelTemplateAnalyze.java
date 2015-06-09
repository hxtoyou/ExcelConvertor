package exportExcel.web.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import exportExcel.entity.ExcelTemplate;

/**
 * @author Xiao He(hxtoyou@163.com)
 * 
 * 
 */
public class ExcelTemplateAnalyze extends AbstractExcelView {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

	}

	public ExcelTemplate analyzeTemplate(String url) throws FileNotFoundException, IOException {
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(url)));
		HSSFSheet sheet1 = workbook.getSheetAt(0);
		// HSSFSheet sheet2 = workbook.getSheetAt(1);
		// HSSFSheet sheet3 = workbook.getSheetAt(2);
		HSSFRow row1 = sheet1.getRow(0);
		ExcelTemplate excelTemplate = new ExcelTemplate();
		excelTemplate.setExcelName(row1.getCell(0).toString());
		return excelTemplate;
	}
}
