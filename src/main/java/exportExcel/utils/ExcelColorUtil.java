package exportExcel.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFColor;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年6月5日 上午10:55:16
 * 
 */
public class ExcelColorUtil {
	public final static Map<Short,String> colorMap=getColorMap();
	public  static Map<Short, String> getColorMap(){
		 Map<Short,String> colorMaps = Maps.newHashMap();
		 for(int i=0;i<IndexedColors.values().length;i++){
		       	XSSFColor color = new XSSFColor();
		       	color.setIndexed(IndexedColors.values()[i].getIndex());
		       	colorMaps.put(IndexedColors.values()[i].getIndex(), !Strings.isNullOrEmpty(color.getARGBHex())?color.getARGBHex().substring(2):"");
		       	System.out.println("getARGBHex:"+color.getARGBHex());
	
		 }
		 return colorMaps;
	} 
public static void main(String args[]){
	Workbook workbook = getExcelWorkBook("src\\main\\java\\exportExcel\\utils\\test2.xlsx");
	Sheet sheet = workbook.getSheetAt(1);
	Workbook workbook2 =new SXSSFWorkbook();
	Sheet sheet2 = workbook2.createSheet("2");
	CellStyle  cellStyle = sheet.getRow(0).getCell(0).getCellStyle();
	System.out.println("cellSTYle1:"+colorMap.get(cellStyle.getFillForegroundColor()));
	Row row = sheet2.createRow(0);
	Cell cell = row.createCell(0);
	cell.setCellStyle(cellStyle);
	System.out.println("cellSTYle:"+colorMap.get(cell.getCellStyle().getFillForegroundColor()));
//	Short co = workbook.getSheetAt(0).getRow(0).getCell(0).getCellStyle().getBorderLeft();
//	Color c1 = null;
//	colorMap=getColorMap();
//	   Map<Short,String> colorMap = Maps.newLinkedHashMap();
//       for(int i=0;i<IndexedColors.values().length;i++){
//       	XSSFColor color = new XSSFColor();
//       	color.setIndexed(IndexedColors.values()[i].getIndex());
//       	colorMap.put(IndexedColors.values()[i].getIndex(), !Strings.isNullOrEmpty(color.getARGBHex())?color.getARGBHex().substring(2):"");
//       }
//   	if (workbook instanceof HSSFWorkbook) {  
//	     HSSFWorkbook hb = (HSSFWorkbook) workbook;  
//		     HSSFColor hc = hb.getCustomPalette().getColor(50);
//		     HSSFSheet sheet = hb.getSheetAt(1);
//				Integer rowNum = sheet.getLastRowNum();
//				for(int i=0;i<rowNum;i++){
//					Integer cellNum = (int) sheet.getRow(i).getLastCellNum();
//					for(int j=0;j<cellNum;j++){
////						int x = sheet.getRow(i).getCell(j).getRichStringCellValue().
////						colorMap.get(x)
//						System.out.println("row:"+i);
//						System.out.println("cell:"+j);
////						System.out.println("x:"+colorMap.get(x));
//					}
//				}
//		     System.out.print(hc);
//	 }else if(workbook instanceof XSSFWorkbook){
//		   XSSFWorkbook hb = (XSSFWorkbook) workbook;  
//			XSSFSheet sheet = hb.getSheetAt(1);
//			Integer rowNum = sheet.getLastRowNum();
//			System.out.println(colorMap);
//			for(int i=0;i<rowNum;i++){
//				Integer cellNum = (int) sheet.getRow(i).getLastCellNum();
//				for(int j=0;j<cellNum;j++){
//					XSSFFont x = sheet.getRow(i).getCell(j).getCellStyle().getFont();
////					colorMap.get(x)
//					System.out.println("row:"+i);
//					System.out.println("cell:"+j);
//					Boolean b = x.getBold();
//					Short height = x.getFontHeightInPoints();
//					Short h = x.getFontHeight();
//					int name = x.getFamily();
//					Short w = x.getBoldweight();
//					Short c = x.getColor();
//					String na = x.getFontName();
//					System.out.println("getBold:"+b);
////					System.out.println("getFontHeightInPoints:"+height);
//					System.out.println("getFontHeight:"+h);
//					System.out.println("getFamily:"+name);
//					System.out.println("getBoldweight:"+w);
//					System.out.println("getColor:"+c);
//					System.out.println("getFontName:"+na);
//				}
//			}
	 }

//	if(workbook.getSheetAt(0).getRow(1).getCell(0)!=null){
//		 c1= workbook.getSheetAt(0).getRow(1).getCell(0).getCellStyle().getFillBackgroundColorColor();
//	}
//	
//       System.out.println("index:"+((XSSFColor)c1).getIndexed());
//       System.out.println("cl颜色:"+colorMap.get(c1));
//	Color color1 = null;
//	if (workbook instanceof HSSFWorkbook) {  
//        HSSFWorkbook hb = (HSSFWorkbook) workbook;  
//        HSSFColor hc = hb.getCustomPalette().getColor(50);
//        System.out.print(hc);
//    }else if(workbook instanceof XSSFWorkbook){
//    	XSSFWorkbook hb = (XSSFWorkbook) workbook;  
//    	Short colors = hb.getSheetAt(0).getColumnStyle(0).getBottomBorderColor();
//    	Short cl = hb.getSheetAt(0).
////    	String co = CellUtil.RIGHT_BORDER_COLOR;
//////        XSSFFont hc = hb.getFontAt((short)2); 
////        XSSFColor color = hb.getSheetAt(0).getRow(1).getCell(1).getCellStyle().getFont().getXSSFColor();
////    	XSSFColor color = new XSSFColor();
////    	color.setIndexed(8);
////    	System.out.println(color.getARGBHex());
////        System.out.println(color.getIndexed());
//        IndexedColors.values();
//        String rgbHex = color.getCTColor().getDomNode().getNodeValue();
     
//        System.out.println("index:"+cl);
//        System.out.println("cl颜色:"+colorMap.get(cl));
//        System.out.println("index:"+colors);
//        System.out.println("颜色:"+colorMap.get(colors));
//        colorMap.put((short)0, "00000000");
//        colorMap.put((short)1, "00FFFFFF");
//        colorMap.put((short)2, "00FF0000");
//        colorMap.put((short)3, "0000FF00");
//        colorMap.put((short)4, "000000FF");
//        colorMap.put((short)5, "00FFFF00");
//        colorMap.put((short)6, "00FF00FF");
//        colorMap.put((short)7, "0000FFFF");
//        System.out.println(colorMap);
//        if (rgbHex.length() > 6) {
//        rgbHex = rgbHex.substring(2);
//        }
//        color1 = Color.decode("#" + rgbHex);
//        }
        
/**
 * 根据文件的路径创建Workbook对象
 * 
 * @param filePath
 */
private static Workbook getExcelWorkBook(String filePath) {
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
