package exportExcel.entity;

import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年6月2日 上午10:01:44
 * 
 */
public class ExcelInputType {

private static final Map<String,String> typeMaps = init();
private static final Map<String,String>  init() {
	Map<String,String> typeMap = Maps.newHashMap();
	typeMap.put("combobox", "easyui-combobox");
	typeMap.put("datebox", "easyui-datebox");
	typeMap.put("datetimebox", "easyui-datetimebox");
	typeMap.put("numberbox", "easyui-numberbox");
	typeMap.put("searchbox", "easyui-searchbox");
	return typeMap;
	// TODO Auto-generated constructor stub
}
public static String getValue(String value){
	 value = typeMaps.get(value);
	 if(Strings.isNullOrEmpty(value)){
		 value = "easyui-textbox";
	 }
	return value;
}
}
