package exportExcel.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 页面属性注解 用于生成前端jsp,js代码
 * 
 * <p>
 * 
 * @author Xiao He(hxtoyou@163.com)
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.FIELD })
public @interface PageFields {

	/**
	 * 是否允许为空
	 * 
	 * @return
	 */
	public abstract boolean allowedNull() default true;

	/**
	 * 是否在表格中显示
	 * 
	 * @return
	 */
	public abstract boolean columnShow() default true;

	/**
	 * 表格中显示的宽度
	 * 
	 * @return
	 */
	public abstract int columnWidth() default 0;

	/**
	 * 属性描述
	 * 
	 * @return
	 */
	public abstract String describtion();

	/**
	 * 允许的最大长度
	 * 
	 * @return
	 */
	public abstract int maxLenth() default 1024;

	/**
	 * 是否参与查询
	 * 
	 * @return
	 */
	public abstract boolean search() default false;

	/**
	 * 下拉属性对应的数据源
	 * 
	 * @return
	 */
	public abstract String selectDataUri() default "";

	/**
	 * 下拉属性显示的属性
	 * 
	 * @return
	 */
	public abstract String selectShowField() default "";

	/**
	 * 字段类型:text,textarea,select
	 * 
	 * @return
	 */
	public abstract String type() default "";
}
