package exportExcel.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.poi.hssf.converter.ExcelToFoUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年6月5日 上午10:55:16
 * 
 */
public class ExcelColorUtil {
public static void main(String args[]){
	Workbook workbook = getExcelWorkBook("src\\main\\java\\exportExcel\\utils\\test2.xlsx");
	Short co = workbook.getSheetAt(0).getRow(0).getCell(0).getCellStyle().getBorderLeft();
	Short c1 = 0;
	if(workbook.getSheetAt(0).getRow(15).getCell(0)!=null){
		 c1= workbook.getSheetAt(0).getRow(15).getCell(0).getCellStyle().getBorderLeft();
	}
	   Map<Short,String> colorMap = Maps.newLinkedHashMap();
       for(int i=0;i<IndexedColors.values().length;i++){
       	XSSFColor color = new XSSFColor();
       	color.setIndexed(IndexedColors.values()[i].getIndex());
       	colorMap.put(IndexedColors.values()[i].getIndex(), !Strings.isNullOrEmpty(color.getARGBHex())?color.getARGBHex().substring(2):"");
       }
       System.out.println("index:"+c1);
     System.out.println("cl颜色:"+colorMap.get(c1));
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
        
    }
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
