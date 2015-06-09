package exportExcel.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

/**
 * @author Xiao He(hxtoyou@163.com)
 * 
 * 
 */
public class DataConvert {
	private int val =0;
	/**
	 * String类型的时间数据转为制定格式的String
	 * 
	 * @param dateString
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static String String2String(String dateString, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(dateString);
		String datea = sdf.format(date);
		return datea;
	}

	/**
	 * 数字转换为制定格式(有几位小数位)的数据
	 * 
	 * @param value
	 * @param format
	 * @return
	 */
	public static String NumberFormat(String value, String format) {
		Double num = Double.parseDouble(value);
		DecimalFormat df = new java.text.DecimalFormat(format);
		return df.format(num);
	}

	public static void main(String[] args) {
		String value = "0:贸易" + ",1:" + "非贸易";
		List<String> v = Arrays.asList(value.split(","));
		Map<String, String> map = new HashMap<String, String>();
		for (String content : v) {
			List<String> c = Arrays.asList(content.split(":"));
			map.put(c.get(0), c.get(1));
		}
		System.out.println(map);
	}

	/**
	 * 匹配数据转换内容
	 * 
	 * @param value
	 * @return
	 */
	public static String ConvertContent(String value) {
		String contentC = value;
		String regexC = "\\((.*?)\\)";
		Pattern pC = Pattern.compile(regexC);
		Matcher mC = pC.matcher(contentC);
		mC.find();
		return mC.group(1);
	}

	/**
	 * 解析结果转换内容
	 * 
	 * @param value
	 * @return
	 */
	public static Map<String, Map<String, String>> ConvertAll(Map<String, Map<String, String>> map, String value) {
		String content = value;
		String regex = "\\[(.*?)\\]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(content);
		m.find();
		List<String> head = Arrays.asList(m.group(1).split(":"));
		String contentC = value;
		String regexC = "\\((.*?)\\)";
		Pattern pC = Pattern.compile(regexC);
		Matcher mC = pC.matcher(contentC);
		mC.find();
		Map<String, String> typeDetail = new HashMap<String, String>();
		typeDetail.put(head.get(0), mC.group(1));
		map.put(head.get(1), typeDetail);
		return map;
	}

	/**
	 * 数据为空时显示默认值
	 * 
	 * @param value
	 * @return
	 */
	public static String defualtString(String value, String defualt) {
		if (!Strings.isNullOrEmpty(value) && value != "null") {
			return value;
		} else {
			return defualt;
		}
	}

	/**
	 * 把值转为对应的设定的值
	 * 
	 * @param value
	 * @return
	 */
	public static Map<String, String> convertMap(String value) {
		List<String> v = Arrays.asList(value.split(","));
		Map<String, String> map = new HashMap<String, String>();
		for (String content : v) {
			List<String> c = Arrays.asList(content.split(":"));
			map.put(c.get(0), c.get(1));
		}
		return map;
	}
	public int setVal(int plus){
		 val = val+plus;
		return val;
	}
}
