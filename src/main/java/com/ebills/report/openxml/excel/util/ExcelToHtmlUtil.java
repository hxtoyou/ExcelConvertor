package com.ebills.report.openxml.excel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.ebills.report.openxml.excel.entity.HtmlRowStructure;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年6月4日 下午1:24:48
 * 
 */
public class ExcelToHtmlUtil {
	private static String top="";
	private static String left="";
	private static String right="";
	private static String bottom="";
	private static String bg_color="";
	public static String getFileNameByInternationnal(String path,String fileName ,String locale){
		int splitIndex = fileName.indexOf(".xls");
		String startString = fileName.substring(0, splitIndex);
		String end = fileName.substring(splitIndex);
		if(locale.equals( "en_US")){
			fileName = startString+"_US"+end;
			if(!isFileExist(path+fileName)){
				fileName=startString+end;
			}
		}else if(locale.equals("zh_CN")){
			fileName = startString+"_CN"+end;
			if(!isFileExist(path+fileName)){
				fileName=startString+end;
			}
		}else{
			fileName = startString+end;
		}
		if(!isFileExist(path+fileName)){
			System.out.println("模板文件不存在");
			return "";
		}else{
			return path+fileName;
		}
	}
	private static boolean isFileExist(String path){
		Boolean isExist=false;
		File file=new File(path); 
		if(file.exists()){
			isExist = true;
		}else{
			System.out.println("模板文件不存在");	
		}
		return isExist;
	}
	/**
	 * 根据文件的路径创建Workbook对象
	 * 
	 * @param filePath
	 */
	public static Workbook getExcelWorkBook(String filePath) {
		InputStream ins = null;
		Workbook book = null;
		try {
			ins = new FileInputStream(new File(filePath));
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
	 * 执行SQL匹配
	 */
	public static String getPreSQL(Row row){
		String preSQL="";
		String preSQLpattern = "执行SQL_*[0-9]{0,}$";
		Pattern pre = Pattern.compile(preSQLpattern);
		Matcher preM = pre.matcher(row.getCell(0).toString());
		if (preM.find()) {
			if (!Strings.isNullOrEmpty(row.getCell(1).toString())) {
				preSQL += row.getCell(1).toString() + ";";
			}
		}
		return preSQL;
	}
	/**
	 * 模板基础参数解析
	 * @param sheet
	 * @param rownum
	 * @return
	 */
	public static Map<String,String> getExcelAnalyze(Sheet sheet,Integer rownum){
		String preSQL = "";// 查询钱执行sql
		String querySQL = "";// 查询sql
		String queryTag = "";// queryTag表示输出结果是data还是datasum
		String queryParamaters = "";
		String queryConvert = "";
		String templateName="";
		String totalColumnNum="";
		String heads="";
		String tails="";
		String details="";
		String pagination="";
		String javaClass="";
		Map<String,String> result = Maps.newHashMap();
			for (int i = 1; i <= rownum; i++) {
				Row row = sheet.getRow(i);
				if(row==null){
					continue;
				}
				
				/**
				 * 执行SQL匹配
				 */
				String preSQLpattern = "PRESQL_*[0-9]{0,}$";
				Pattern pre = Pattern.compile(preSQLpattern);
				Matcher preM = pre.matcher(row.getCell(0).toString());
				if (preM.find()) {
					if (!Strings.isNullOrEmpty(row.getCell(2).toString())) {
						preSQL += row.getCell(2).toString() + ";";
					}
				}
				/**
				 * 查询sql匹配
				 */
				String querySQLpattern = "QUERYSQL_*[0-9]{0,}$";
				// 创建 Pattern 对象
				Pattern query = Pattern.compile(querySQLpattern);
				// 现在创建 matcher 对象
				Matcher queryM = query.matcher(row.getCell(0).toString());
				if (queryM.find()) {
					if (!Strings.isNullOrEmpty(row.getCell(2).toString())) {
						querySQL += row.getCell(2).toString() + ";";
						queryParamaters += row.getCell(5)==null ? ""+";" : row.getCell(5).toString() + ";";
						queryTag += row.getCell(3).toString() + ";";
						queryConvert += row.getCell(6) != null ? row.getCell(6).toString()+";" : "" + ";";
					}
				}

				/**
				 * 配置页基本选项配置
				 */
				if (row.getCell(0).toString().equals("PAGINATION")) {
					pagination =row.getCell(2).toString();
				}
				if (row.getCell(0).toString().equals("TABLENAME")) {
					templateName =row.getCell(2).toString();
				}
				if (row.getCell(0).toString().equals("TABLEHEADS")) {
					heads = row.getCell(2).toString();
				}
				if (row.getCell(0).toString().equals("TOTALCOLUMNS")) {
					totalColumnNum=row.getCell(2).toString();
				}
				if (row.getCell(0).toString().equals("TABLEDATAS")) {
					details = row.getCell(2).toString();
				}
				if (row.getCell(0).toString().equals("TABLETAILS")) {
					tails = row.getCell(2).toString();
				}
				if (row.getCell(0).toString().equals("JAVACLASS")) {
					javaClass = row.getCell(2).toString();
				}

			}
			result.put("pagination", pagination);
			result.put("preSQL", preSQL);
			result.put("querySQL", querySQL);
			result.put("queryTag", queryTag);
			result.put("queryParamaters", queryParamaters);
			result.put("templateName", templateName);
			result.put("totalColumnNum", totalColumnNum);
			result.put("heads", heads);
			result.put("tails", tails);
			result.put("details", details);
			result.put("queryConvert", queryConvert);
			result.put("javaClass", javaClass);
			return result;
	}

	/**
	 * 解析起始行
	 * 
	 * @param details
	 * @return
	 */
	public static int paraseHead(String details) {
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
	public static int paraseTail(String details) {
		List<String> headsNum = Arrays.asList(details.split(","));
		int headsTail = (int) Math.floor(Double.parseDouble(headsNum.get(headsNum.size() - 1)));
		return headsTail;
	}
	/**
	 * 带小数位数的String类型转为int
	 * @param value
	 * @return
	 */
	public static int paraseString(String value){
		Integer result = 0;
		if(!Strings.isNullOrEmpty(value)){
			 result = (int) Math.floor(Double.parseDouble(value));
			
		}
		return result;
	}
	/**
	 * 返回所有合并单元格对象
	 * @param sheet
	 * @return
	 */
	public static List<CellRangeAddress> getMergeRegons(Sheet sheet){
		List<CellRangeAddress> addresses = Lists.newArrayList();
		// 遍历合并单元格
		int sheetmergerCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetmergerCount; i++) {
			// 获得合并单元格加入list中
			CellRangeAddress ca = sheet.getMergedRegion(i);
			addresses.add(ca);
		}
		return addresses;
	}
	/**
	 * 读取内容上下排版
	 * @param align
	 * @return
	 */
	public static String getVerticalAlign(Short align){
		switch (align) {
		case 0:
			return "top";
		case 1:
			return "center";
		case 2:
			return "bottom";
		case 3:
			return "justify";
		default:
			return "center";
		}
	}
	/**
	 * 读取内容左右排版
	 * @param align
	 * @return
	 */
	public static String getHorizontalAlign(Short align){
		switch (align) {
		case 1:
			return "left";
		case 2:
			return "center";
		case 3:
			return "right";
		case 5:
			return "justify";
		default:
			return "center";
		}
	}
	/**
	 * 把enum类型转为用序列号为Key，颜色的RGB值为value的map对象
	 * @return
	 */
	public static String getColorRGB(Short colorIndex){
		if(colorIndex>0&&colorIndex!=null){
			HSSFColor indexed = (HSSFColor) createColorsByIndexMap().get((int)colorIndex);
			if(indexed!=null){
				String rgbhex = getARGBHex(indexed);
				return "#"+rgbhex.substring(2);
			}else{
				return "";
			}
		}else{
			return "";
		}
	}

    @SuppressWarnings("unused")
	private static Hashtable<Integer,HSSFColor> createColorsByIndexMap() {
        HSSFColor[] colors = getAllColors();
        Hashtable<Integer,HSSFColor> result = new Hashtable<Integer,HSSFColor>(colors.length * 3 / 2);

        for (int i = 0; i < colors.length; i++) {
            HSSFColor color = colors[i];

            Integer index1 = Integer.valueOf(color.getIndex());
            if (result.containsKey(index1)) {
                HSSFColor prevColor = (HSSFColor)result.get(index1);
                throw new RuntimeException("Dup color index (" + index1
                        + ") for colors (" + prevColor.getClass().getName()
                        + "),(" + color.getClass().getName() + ")");
            }
            result.put(index1, color);
        }

        for (int i = 0; i < colors.length; i++) {
            HSSFColor color = colors[i];
            Integer index2 = getIndex2(color);
            if (index2 == null) {
                // most colors don't have a second index
                continue;
            }
            if (result.containsKey(index2)) {
                if (false) { // Many of the second indexes clash
                    HSSFColor prevColor = (HSSFColor)result.get(index2);
                    throw new RuntimeException("Dup color index (" + index2
                            + ") for colors (" + prevColor.getClass().getName()
                            + "),(" + color.getClass().getName() + ")");
                }
            }
            result.put(index2, color);
        }
        return result;
    }
    private static Integer getIndex2(HSSFColor color) {

        Field f;
        try {
            f = color.getClass().getDeclaredField("index2");
        } catch (NoSuchFieldException e) {
            // can happen because not all colors have a second index
            return null;
        }

        Short s;
        try {
            s = (Short) f.get(color);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return Integer.valueOf(s.intValue());
    }
    private static HSSFColor[] getAllColors() {

        return new HSSFColor[] {
                new HSSFColor.BLACK(), new HSSFColor.BROWN(), new HSSFColor.OLIVE_GREEN(), new HSSFColor.DARK_GREEN(),
                new HSSFColor.DARK_TEAL(), new HSSFColor.DARK_BLUE(), new HSSFColor.INDIGO(), new HSSFColor.GREY_80_PERCENT(),
                new HSSFColor.ORANGE(), new HSSFColor.DARK_YELLOW(), new HSSFColor.GREEN(), new HSSFColor.TEAL(), new HSSFColor.BLUE(),
                new HSSFColor.BLUE_GREY(), new HSSFColor.GREY_50_PERCENT(), new HSSFColor.RED(), new HSSFColor.LIGHT_ORANGE(), new HSSFColor.LIME(),
                new HSSFColor.SEA_GREEN(), new HSSFColor.AQUA(), new HSSFColor.LIGHT_BLUE(), new HSSFColor.VIOLET(), new HSSFColor.GREY_40_PERCENT(),
                new HSSFColor.PINK(), new HSSFColor.GOLD(), new HSSFColor.YELLOW(), new HSSFColor.BRIGHT_GREEN(), new HSSFColor.TURQUOISE(),
                new HSSFColor.DARK_RED(), new HSSFColor.SKY_BLUE(), new HSSFColor.PLUM(), new HSSFColor.GREY_25_PERCENT(), new HSSFColor.ROSE(),
                new HSSFColor.LIGHT_YELLOW(), new HSSFColor.LIGHT_GREEN(), new HSSFColor.LIGHT_TURQUOISE(), new HSSFColor.PALE_BLUE(),
                new HSSFColor.LAVENDER(), new HSSFColor.WHITE(), new HSSFColor.CORNFLOWER_BLUE(), new HSSFColor.LEMON_CHIFFON(),
                new HSSFColor.MAROON(), new HSSFColor.ORCHID(), new HSSFColor.CORAL(), new HSSFColor.ROYAL_BLUE(),
                new HSSFColor.LIGHT_CORNFLOWER_BLUE(), new HSSFColor.TAN(),
        };
    }
	/**
	 * 获取颜色
	 * @param indexed
	 * @return
	 */
    public static String getARGBHex(HSSFColor indexed) {
        StringBuffer sb = new StringBuffer();
        byte[] rgb = getARgb(indexed);
        if(rgb == null) {
           return null;
        }
        for(byte c : rgb) {
           int i = (int)c;
           if(i < 0) {
              i += 256;
           }
           String cs = Integer.toHexString(i);
           if(cs.length() == 1) {
              sb.append('0');
           }
           sb.append(cs);
        }
        return sb.toString().toUpperCase();
     }
    public static byte[] getARgb(HSSFColor indexed) {
        byte[] rgb = getRGBOrARGB(indexed);
        if(rgb == null) return null;

        if(rgb.length == 3) {
           // Pad with the default Alpha
           byte[] tmp = new byte[4];
           tmp[0] = -1;
           System.arraycopy(rgb, 0, tmp, 1, 3);
           return tmp;
        } else {
           return rgb;
        }
     }
    private static byte[] getRGBOrARGB(HSSFColor indexed) {
        byte[] rgb = null;

               rgb = new byte[3];
               rgb[0] = (byte) indexed.getTriplet()[0];
               rgb[1] = (byte) indexed.getTriplet()[1];
               rgb[2] = (byte) indexed.getTriplet()[2];
               return rgb;

         // Grab the colour
    }
	/**
	 * 对一行的每个单元格设置边框颜色、宽度、背景颜色
	 * @param j
	 * @param k
	 * @param sheet
	 * @param htmlRowStructures
	 */
	public static void setColor(Integer j,Integer k,Sheet sheet1,List<HtmlRowStructure> htmlRowStructures){
		if(sheet1 instanceof XSSFSheet){
			XSSFSheet sheet = (XSSFSheet)sheet1;
			if(sheet.getRow(j).getCell(k)!=null){
				top=!Strings.isNullOrEmpty(String.valueOf(((Short)sheet.getRow(j).getCell(k).getCellStyle().getBorderTop())))?String.valueOf(((Short)sheet.getRow(j).getCell(k).getCellStyle().getBorderTop())):top;
				left= !Strings.isNullOrEmpty(String.valueOf(((Short)sheet.getRow(j).getCell(k).getCellStyle().getBorderLeft())))?String.valueOf(((Short)sheet.getRow(j).getCell(k).getCellStyle().getBorderLeft())):left;
				right = !Strings.isNullOrEmpty(String.valueOf(((Short)sheet.getRow(j).getCell(k).getCellStyle().getBorderRight())))?String.valueOf(((Short)sheet.getRow(j).getCell(k).getCellStyle().getBorderRight())):right;
				bottom = !Strings.isNullOrEmpty(String.valueOf(((Short)sheet.getRow(j).getCell(k).getCellStyle().getBorderBottom())))?String.valueOf(((Short)sheet.getRow(j).getCell(k).getCellStyle().getBorderBottom())):bottom;
				bg_color = getColorRGB(sheet.getRow(j).getCell(k).getCellStyle().getFillForegroundColor());
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_top(top);
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_left(left);
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_right(right);
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_bottom(bottom);
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBg_color(bg_color);
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setFont_color(!Strings.isNullOrEmpty(getColorRGB(sheet.getRow(j).getCell(k).getCellStyle().getFont().getColor()))?getColorRGB(sheet.getRow(j).getCell(k).getCellStyle().getFont().getColor()):"");
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setFont_size(String.valueOf(((Short)sheet.getRow(j).getCell(k).getCellStyle().getFont().getFontHeightInPoints())));
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setIsBold(sheet.getRow(j).getCell(k).getCellStyle().getFont().getBold());
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setFontFamily(sheet.getRow(j).getCell(k).getCellStyle().getFont().getFontName());
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setHorizontalAlign(getHorizontalAlign(sheet.getRow(j).getCell(k).getCellStyle().getAlignment()));
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setVerticalAlign(getVerticalAlign(sheet.getRow(j).getCell(k).getCellStyle().getVerticalAlignment()));
			}else{
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_top(top);
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_left(left);
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_right(right);
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBorder_bottom(bottom);
				htmlRowStructures.get(j).getHtmlTdStructure().get(k).setBg_color(bg_color);
			}
		}else if(sheet1 instanceof HSSFSheet){
			
		}
	}
	/**
	 * 解析系统设置excel模板
	 * @param sheet
	 * @param rownum
	 */
	public static Map<String,String> analyzeSystemSetting(Sheet sheet,Integer rownum){
		Map<String,String> result = Maps.newHashMap();
		String showType = "";
		String detailSetSheet = "";
		String detailTemplateSheet = "";
		String statisticSetSheet = "";
		String statisticTemplateSheet = "";
		String querySheet = "";
		String queryFromName="";
		String validateType="";
		String validateFile="";
		for (int i = 0; i < rownum; i++) {
			Row row = sheet.getRow(i);
			if(row==null){
				continue;
			}
			if (row.getCell(0).toString().equals("SHOWTYPE")) {
				showType =row.getCell(2).toString();
			}
			if (row.getCell(0).toString().equals("DETAILSETSHEET")) {
				if(row.getCell(2)!=null){
					detailSetSheet =row.getCell(2).toString();
				}
			}
			if (row.getCell(0).toString().equals("DETAILTEMPLATESHEET")) {
				if(row.getCell(2)!=null){
				detailTemplateSheet =row.getCell(2).toString();
				}
			}
			if (row.getCell(0).toString().equals("STATISTICSETSHEET")) {
				if(row.getCell(2)!=null){
				statisticSetSheet =row.getCell(2).toString();
				}
			}
			if (row.getCell(0).toString().equals("STATISTICTEMPLATESHEET")) {
				if(row.getCell(2)!=null){
				statisticTemplateSheet =row.getCell(2).toString();
				}
			}
			if (row.getCell(0).toString().equals("QUERYFORMNAME")) {
				if(row.getCell(2)!=null){
					queryFromName =row.getCell(2).toString();
				}
			}
			if (row.getCell(0).toString().equals("QUERYSHEET")) {
				if(row.getCell(2)!=null){
				querySheet =row.getCell(2).toString();
				}else{
					System.out.println("未设置excel查询页码");
				}
			}
			if (row.getCell(0).toString().equals("JAVACLASS")) {
				if(row.getCell(2)!=null){
				querySheet =row.getCell(2).toString();
				}
			}
			if(row.getCell(0).toString().equals("QUERYFILE")){
				if(row.getCell(2)!=null){
					validateFile = row.getCell(2).toString();
				}
			}
		}
		result.put("showType", showType);
		result.put("detailSetSheet", detailSetSheet);
		result.put("detailTemplateSheet", detailTemplateSheet);
		result.put("statisticSetSheet",statisticSetSheet);
		result.put("statisticTemplateSheet", statisticTemplateSheet);
		result.put("querySheet", querySheet);
		result.put("queryFromName", queryFromName);
		result.put("validateType", validateType);
		result.put("validateFile", validateFile);
		return result;
	}
	/**
	 * 复制表格
	 * @param srcSheet
	 * @param destSheet
	 * @param srcwb
	 * @param destwb
	 * @return
	 */
	public static Workbook copyWbSheet(Sheet srcSheet,Sheet destSheet,Workbook srcwb,Workbook destwb,Integer contentRow){
		int maxCellNum = 0;
		try {
			maxCellNum = copySheet(srcSheet, destSheet, srcwb, destwb,contentRow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 设置列宽
	    setSheetWidth(srcSheet, destSheet, maxCellNum);
	    // 合并单元格
	    mergeSheetAllRegion(srcSheet, destSheet,contentRow);
	    return destwb;
	}
	public static void mergeSheetAllRegion(Sheet srcSheet, Sheet destSheet,Integer contentRow) {
	    int num = srcSheet.getNumMergedRegions();
	    CellRangeAddress cellR = null;
	    for (int i = 0; i < num; i++) {
	      cellR = srcSheet.getMergedRegion(i);
	      int lastRow=cellR.getLastRow();
	      if(lastRow<contentRow){
	    	  destSheet.addMergedRegion(cellR);
	      }
	    }
	  }

	public static void setSheetWidth(Sheet srcSheet, Sheet destSheet,
	      int maxCellNum) {
	    for (int i = 0; i <= maxCellNum; i++) {
	      destSheet.setColumnWidth(i, srcSheet.getColumnWidth(i));
	    }
	  }
	public static int copySheet(Sheet srcSheet, Sheet destSheet,
		      Workbook srcwb, Workbook destwb,Integer contentRow) throws Exception {
//		    int rowCount = srcSheet.getLastRowNum();// 总行数
		    int maxCellNum = 0;
		    Row srcRow = null, destRow = null;
		    for (int i = 0; i < contentRow; i++) {
		      srcRow = srcSheet.getRow(i);
		      destRow = destSheet.getRow(i);
		      if (srcRow == null) {
		        continue;
		      }
		      // 最大列数
		      maxCellNum = maxCellNum < srcRow.getLastCellNum() ? srcRow
		          .getLastCellNum() : maxCellNum;
		      if (destRow == null) {
		        destRow = destSheet.createRow(i);
		      }
		      // 设置行高
		      destRow.setHeight(srcRow.getHeight());
		      copySheetRow(srcRow, destRow, srcwb, destwb);
		      srcRow = null;
		      destRow = null;
		    }
		    srcRow = null;
		    destRow = null;
		    return maxCellNum;
		  }
	private static void copySheetRow(Row srcRow, Row destRow,
		      Workbook srcwb, Workbook destwb) {
		    int cellCount = srcRow.getLastCellNum();// 每行的总列数
		    Cell srcCell = null, destCell = null;
		    CellStyle srcCellStyle = null, destCellStyle = null;
		    for (int j = 0; j < cellCount; j++) {// 遍历行单元格
		      srcCell = srcRow.getCell(j);
		      destCell = destRow.getCell(j);
		      if (destCell == null) {
		        destCell = destRow.createCell(j);
		      }
		      if (srcCell != null) {
		        srcCellStyle = srcCell.getCellStyle();// 原sheet页样式
		        destCellStyle = null;
		        destCellStyle = destCell.getCellStyle();
		        // 复制样式
		        destCellStyle.cloneStyleFrom(srcCellStyle);
		        // 处理单元格内容
		        switch (srcCell.getCellType()) {
		        case Cell.CELL_TYPE_STRING:
		          destCell.setCellValue(srcCell.getRichStringCellValue());
		          destCell.setCellStyle(srcCellStyle);
		          break;
		        // 这里判断是否是日期
		        case Cell.CELL_TYPE_NUMERIC:
		          // 判断是否是日期格式
		          // 测试发现如果这里不新建样式,日期显示的是数字
		          if (DateUtil.isCellDateFormatted(srcCell)) {
		            // 新建样式
		            destCellStyle = destwb.createCellStyle();
		            // 复制样式
		            destCellStyle.cloneStyleFrom(srcCellStyle);
		            destCell.setCellStyle(srcCellStyle);
		            destCell.setCellValue(srcCell.getDateCellValue());
		          } else {
		            destCell.setCellValue(srcCell.getNumericCellValue());
		          }
		          break;
		        case Cell.CELL_TYPE_FORMULA:
		          destCell.setCellFormula(srcCell.getCellFormula());
		          destCell.setCellStyle(srcCellStyle);
		          break;
		        case Cell.CELL_TYPE_BOOLEAN:
		          destCell.setCellValue(srcCell.getBooleanCellValue());
		          destCell.setCellStyle(srcCellStyle);
		          break;
		        case Cell.CELL_TYPE_BLANK:
		          destCell.setCellType(Cell.CELL_TYPE_BLANK);
		          destCell.setCellStyle(srcCellStyle);
		          break;
		        case Cell.CELL_TYPE_ERROR:
		          break;
		        default:
		          break;
		        }
		      }
		    }
		    srcCellStyle = null;
		    destCellStyle = null;
		    srcCell = null;
		    destCell = null;
		  }
	/**
	 * 循环主数据时合并单元格
	 * @param srcSheet
	 * @param destSheet
	 * @param head
	 * @param tail
	 * @param copyNum 主数据的行数
	 * @param start excel模板中的起始位置
	 */
	public static void mergeSheetInDetail(Sheet srcSheet, Sheet destSheet,Integer head,Integer copyNum,Integer start,Integer startIndex){
		  int num = srcSheet.getNumMergedRegions();
		    CellRangeAddress cellR = null;
		    for (int i = 0; i < num; i++) {
		      cellR = srcSheet.getMergedRegion(i);
		      Integer firstRow = cellR.getFirstRow();
		      Integer lastRow = cellR.getLastRow();
 		      if(firstRow>=head&&lastRow<(head+copyNum)){
		    	  cellR.setFirstRow(firstRow-startIndex+start);
		    	  cellR.setLastRow(lastRow-startIndex+start);
		    	  destSheet.addMergedRegion(cellR);
		      }
		    }
	}
	public static XSSFCellStyle copyCellStyle(XSSFCellStyle srcStyle,SXSSFWorkbook workbook){

	    
		XSSFCellStyle cellStyle = null;
	  
	     cellStyle = (XSSFCellStyle) workbook.createCellStyle();    
	    
	     cellStyle.setBorderTop(srcStyle.getBorderTop());
	     cellStyle.setBorderBottom(srcStyle.getBorderBottom());
	     cellStyle.setBorderLeft(srcStyle.getBorderLeft());
	     cellStyle.setBorderRight(srcStyle.getBorderRight());

	     cellStyle.setAlignment(srcStyle.getAlignment());
	     cellStyle.setVerticalAlignment(srcStyle.getVerticalAlignment());
	     XSSFColor color = new XSSFColor();
	     color.setIndexed(srcStyle.getFillForegroundColor());
	     cellStyle.setFillBackgroundColor(color);
	     cellStyle.setWrapText(srcStyle.getWrapText());
	     
	     Font font =srcStyle.getFont();
	     Font fonts = workbook.createFont();
	     
	     fonts.setFontHeightInPoints(font.getFontHeightInPoints());
	     fonts.setColor(font.getColor()); 							
	     fonts.setFontName(font.getFontName());						
	     fonts.setBoldweight(font.getBoldweight());					
	     
	     cellStyle.setFont(fonts);
	     
	     return cellStyle;
	}
}
