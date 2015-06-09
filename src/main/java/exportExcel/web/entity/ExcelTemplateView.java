package exportExcel.web.entity;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelTemplateView extends AbstractExcelView {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// // 获取数据
		// @SuppressWarnings("unchecked")
		// List<ExcelTemplate> templates = (List<ExcelTemplate>)
		// model.get("template");
		HSSFSheet sheet1 = workbook.getSheetAt(0);
		int firstRow = sheet1.getLastRowNum();
		// String value = firstRow.getCell(0).toString();
		logger.debug("value:{}", firstRow);
		// HSSFSheet sheet2 = workbook.getSheetAt(1);
		// HSSFSheet sheet3 = workbook.getSheetAt(2);
		// HSSFRow value = sheet1.getRow(1);
		// // 设置第一行数据
		// HSSFRow firstRow = sheet1.createRow(1);
		// HSSFRow firstCityRow = sheet2.createRow(1);
		// HSSFRow firstCountyRow = sheet3.createRow(1);
		// StringBuilder stringBuilder = new StringBuilder();
		// stringBuilder.append(" 查询范围：" + model.get("startTime") + " 到 " +
		// model.get("endTime"));
		// stringBuilder.append(" 查询时间：" + new
		// SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		// stringBuilder.append(" 县区平台巡视情况");
		// firstRow.createCell(0).setCellValue(stringBuilder.toString());
		// firstCityRow.createCell(0).setCellValue(stringBuilder.toString());
		// firstCountyRow.createCell(0).setCellValue(stringBuilder.toString());
		//
		// // 填充数据
		// int i;
		// for (i = 0; i < templates.size(); i++) {
		// /**
		// * 市巡视
		// */
		// HSSFRow rowCity = sheet2.createRow(i + 5);
		// rowCity.createCell(0).setCellValue(reportCollect1.getCounty());
		// rowCity.createCell(1).setCellValue(reportCollect1.getMineName());
		// rowCity.createCell(2).setCellValue(reportCollect1.getToursCity());
		// rowCity.createCell(3).setCellValue(reportCollect1.getCityGasTour());
		// rowCity.createCell(4).setCellValue(reportCollect1.getCityGasTourAbnormal());
		// rowCity.createCell(5).setCellValue(reportCollect1.getCityGasNum());
		// rowCity.createCell(6).setCellValue(reportCollect1.getCityPositionTour());
		// rowCity.createCell(7).setCellValue(reportCollect1.getCityPositionTourAbnormal());
		// rowCity.createCell(8).setCellValue(reportCollect1.getCityPositionNum());
		// rowCity.createCell(9).setCellValue(reportCollect1.getCityVedioTour());
		// rowCity.createCell(10).setCellValue(reportCollect1.getCityVedioTourAbnormal());
		// rowCity.createCell(11).setCellValue(reportCollect1.getCityVedioNum());
		// rowCity.createCell(12).setCellValue(reportCollect1.getCityOtherTour());
		// rowCity.createCell(13).setCellValue(reportCollect1.getCityOtherTourAbnormal());
		// rowCity.createCell(14).setCellValue(reportCollect1.getCityOtherNum());
		// /**
		// * 县区巡视统计报表
		// */
		// HSSFRow rowCounty = sheet3.createRow(i + 5);
		//
		// rowCounty.createCell(0).setCellValue(reportCollect1.getCounty());
		// rowCounty.createCell(1).setCellValue(reportCollect1.getMineName());
		// rowCounty.createCell(2).setCellValue(reportCollect1.getToursCounty());
		// rowCounty.createCell(3).setCellValue(reportCollect1.getCountyGasTour());
		// rowCounty.createCell(4).setCellValue(reportCollect1.getCountyGasTourAbnormal());
		// rowCounty.createCell(5).setCellValue(reportCollect1.getCountyGasNum());
		// rowCounty.createCell(6).setCellValue(reportCollect1.getCountyPositionTour());
		// rowCounty.createCell(7).setCellValue(reportCollect1.getCountyPositionTourAbnormal());
		// rowCounty.createCell(8).setCellValue(reportCollect1.getCountyPositionNum());
		// rowCounty.createCell(9).setCellValue(reportCollect1.getCountyVedioTour());
		// rowCounty.createCell(10).setCellValue(reportCollect1.getCountyVedioTourAbnormal());
		// rowCounty.createCell(11).setCellValue(reportCollect1.getCountyVedioNum());
		// rowCounty.createCell(12).setCellValue(reportCollect1.getCountyOtherTour());
		// rowCounty.createCell(13).setCellValue(reportCollect1.getCountyOtherTourAbnormal());
		// rowCounty.createCell(14).setCellValue(reportCollect1.getCountyOtherNum());
		// /**
		// * 市县巡视结合统计报表
		// */
		// HSSFRow row = sheet1.createRow(i + 5);
		//
		// row.createCell(0).setCellValue(reportCollect1.getCounty());
		// row.createCell(1).setCellValue(reportCollect1.getMineName());
		// row.createCell(2).setCellValue(reportCollect1.getToursNum());
		// row.createCell(3).setCellValue(reportCollect1.getCityGasTour());
		// row.createCell(4).setCellValue(reportCollect1.getCityGasTourAbnormal());
		// row.createCell(5).setCellValue(reportCollect1.getCityGasNum());
		// row.createCell(6).setCellValue(reportCollect1.getCountyGasTour());
		// row.createCell(7).setCellValue(reportCollect1.getCountyGasTourAbnormal());
		// row.createCell(8).setCellValue(reportCollect1.getCountyGasNum());
		// row.createCell(9).setCellValue(reportCollect1.getCityPositionTour());
		// row.createCell(10).setCellValue(reportCollect1.getCityPositionTourAbnormal());
		// row.createCell(11).setCellValue(reportCollect1.getCityPositionNum());
		// row.createCell(12).setCellValue(reportCollect1.getCountyPositionTour());
		// row.createCell(13).setCellValue(reportCollect1.getCountyPositionTourAbnormal());
		// row.createCell(14).setCellValue(reportCollect1.getCountyPositionNum());
		// row.createCell(15).setCellValue(reportCollect1.getCityVedioTour());
		// row.createCell(16).setCellValue(reportCollect1.getCityVedioTourAbnormal());
		// row.createCell(17).setCellValue(reportCollect1.getCityVedioNum());
		// row.createCell(18).setCellValue(reportCollect1.getCountyVedioTour());
		// row.createCell(19).setCellValue(reportCollect1.getCountyVedioTourAbnormal());
		// row.createCell(20).setCellValue(reportCollect1.getCountyVedioNum());
		// row.createCell(21).setCellValue(reportCollect1.getCityOtherTour());
		// row.createCell(22).setCellValue(reportCollect1.getCityOtherTourAbnormal());
		// row.createCell(23).setCellValue(reportCollect1.getCityOtherNum());
		// row.createCell(24).setCellValue(reportCollect1.getCountyOtherTour());
		// row.createCell(25).setCellValue(reportCollect1.getCountyOtherTourAbnormal());
		// row.createCell(26).setCellValue(reportCollect1.getCountyOtherNum());
		// }
		// /**
		// * 市巡视统计
		// */
		// HSSFRow rowCityNum = sheet2.createRow(i + 5);
		// rowCityNum.createCell(0).setCellValue(tourStaticTotal.getName());
		// rowCityNum.createCell(1).setCellValue("");
		// rowCityNum.createCell(2).setCellValue(tourStaticTotal.getCityTotal());
		// rowCityNum.createCell(3).setCellValue(tourStaticTotal.getCityGasNormalTotal());
		// rowCityNum.createCell(4).setCellValue(tourStaticTotal.getCityGasAbnormalTotal());
		// rowCityNum.createCell(5).setCellValue(tourStaticTotal.getCityGas());
		// rowCityNum.createCell(6).setCellValue(tourStaticTotal.getCityPositionNormalTotal());
		// rowCityNum.createCell(7).setCellValue(tourStaticTotal.getCityPositionAbnormalTotal());
		// rowCityNum.createCell(8).setCellValue(tourStaticTotal.getCityPosition());
		// rowCityNum.createCell(9).setCellValue(tourStaticTotal.getCityVedioNormalTotal());
		// rowCityNum.createCell(10).setCellValue(tourStaticTotal.getCityVedioAbnormalTotal());
		// rowCityNum.createCell(11).setCellValue(tourStaticTotal.getCityVedio());
		// rowCityNum.createCell(12).setCellValue(tourStaticTotal.getCityOtherNormalTotal());
		// rowCityNum.createCell(13).setCellValue(tourStaticTotal.getCityOtherAbnormalTotal());
		// rowCityNum.createCell(14).setCellValue(tourStaticTotal.getCityOther());
		// /**
		// * 县合计
		// */
		// HSSFRow rowCountyNum = sheet3.createRow(i + 5);
		// rowCountyNum.createCell(0).setCellValue(tourStaticTotal.getName());
		// rowCountyNum.createCell(1).setCellValue("");
		// rowCountyNum.createCell(2).setCellValue(tourStaticTotal.getCountyTotal());
		// rowCountyNum.createCell(3).setCellValue(tourStaticTotal.getCountyGasNormalTotal());
		// rowCountyNum.createCell(4).setCellValue(tourStaticTotal.getCountyGasAbnormalTotal());
		// rowCountyNum.createCell(5).setCellValue(tourStaticTotal.getCountyGas());
		// rowCountyNum.createCell(6).setCellValue(tourStaticTotal.getCountyPositionNormalTotal());
		// rowCountyNum.createCell(7).setCellValue(tourStaticTotal.getCountyPositionAbnormalTotal());
		// rowCountyNum.createCell(8).setCellValue(tourStaticTotal.getCountyPosition());
		// rowCountyNum.createCell(9).setCellValue(tourStaticTotal.getCountyVedioNormalTotal());
		// rowCountyNum.createCell(10).setCellValue(tourStaticTotal.getCountyVedioAbnormalTotal());
		// rowCountyNum.createCell(11).setCellValue(tourStaticTotal.getCountyVedio());
		// rowCountyNum.createCell(12).setCellValue(tourStaticTotal.getCountyOtherNormalTotal());
		// rowCountyNum.createCell(13).setCellValue(tourStaticTotal.getCountyOtherAbnormalTotal());
		// rowCountyNum.createCell(14).setCellValue(tourStaticTotal.getCountyOther());
		// /**
		// * 市县结合巡视合计
		// */
		// HSSFRow rowNum = sheet1.createRow(i + 5);
		// rowNum.createCell(0).setCellValue(tourStaticTotal.getName());
		// rowNum.createCell(1).setCellValue("");
		// rowNum.createCell(2).setCellValue(tourStaticTotal.getTotal());
		// rowNum.createCell(3).setCellValue(tourStaticTotal.getCityGasNormalTotal());
		// rowNum.createCell(4).setCellValue(tourStaticTotal.getCityGasAbnormalTotal());
		// rowNum.createCell(5).setCellValue(tourStaticTotal.getCityGas());
		// rowNum.createCell(6).setCellValue(tourStaticTotal.getCountyGasNormalTotal());
		// rowNum.createCell(7).setCellValue(tourStaticTotal.getCountyGasAbnormalTotal());
		// rowNum.createCell(8).setCellValue(tourStaticTotal.getCountyGas());
		// rowNum.createCell(9).setCellValue(tourStaticTotal.getCityPositionNormalTotal());
		// rowNum.createCell(10).setCellValue(tourStaticTotal.getCityPositionAbnormalTotal());
		// rowNum.createCell(11).setCellValue(tourStaticTotal.getCityPosition());
		// rowNum.createCell(12).setCellValue(tourStaticTotal.getCountyPositionNormalTotal());
		// rowNum.createCell(13).setCellValue(tourStaticTotal.getCountyPositionAbnormalTotal());
		// rowNum.createCell(14).setCellValue(tourStaticTotal.getCountyPosition());
		// rowNum.createCell(15).setCellValue(tourStaticTotal.getCityVedioNormalTotal());
		// rowNum.createCell(16).setCellValue(tourStaticTotal.getCityVedioAbnormalTotal());
		// rowNum.createCell(17).setCellValue(tourStaticTotal.getCityVedio());
		// rowNum.createCell(18).setCellValue(tourStaticTotal.getCountyVedioNormalTotal());
		// rowNum.createCell(19).setCellValue(tourStaticTotal.getCountyVedioAbnormalTotal());
		// rowNum.createCell(20).setCellValue(tourStaticTotal.getCountyVedio());
		// rowNum.createCell(21).setCellValue(tourStaticTotal.getCityOtherNormalTotal());
		// rowNum.createCell(22).setCellValue(tourStaticTotal.getCityOtherAbnormalTotal());
		// rowNum.createCell(23).setCellValue(tourStaticTotal.getCityOther());
		// rowNum.createCell(24).setCellValue(tourStaticTotal.getCountyOtherNormalTotal());
		// rowNum.createCell(25).setCellValue(tourStaticTotal.getCountyOtherAbnormalTotal());
		// rowNum.createCell(26).setCellValue(tourStaticTotal.getCountyOther());
		// // 设置响应
		// String fileName = URLEncoder.encode("巡视统计表", "utf-8") + "["
		// + DateFormat.getDateTimeInstance(2, 2, Locale.CHINA).format(new
		// Date());
		// response.setHeader("Content-Disposition", "inline; filename=" +
		// fileName + "].xls");
	}

	@Override
	protected HSSFWorkbook getTemplateSource(String url, HttpServletRequest request) throws Exception {
		LocalizedResourceHelper helper = new LocalizedResourceHelper();
		Locale userLocale = RequestContextUtils.getLocale(request);
		Resource inputFile = helper.findLocalizedResource(url, ".xlsx", userLocale);

		POIFSFileSystem fs = new POIFSFileSystem(inputFile.getInputStream());
		return new HSSFWorkbook(fs);
	}
}
