/*
 * Copyright (C) CCRISE.
 */
package exportExcel.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 反射工具类.
 * <p>
 * 提供获取继承的父类泛型类型的方法.
 * 
 * @author Xiong Shuhong
 */
public abstract class ReflectionUtils {
	/**
	 * 复制实体类属性，如果属性值为null，则不复制.
	 * 
	 * @param dest
	 *            目标对象
	 * @param orig
	 *            源对象
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static void copyProperties(final Object dest, final Object orig) {
		if (dest == null) {
			return;
		}
		if (orig == null) {
			return;
		}

		PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(orig);
		for (PropertyDescriptor origDescriptor : origDescriptors) {
			String name = origDescriptor.getName();
			try {
				Object value = PropertyUtils.getSimpleProperty(orig, name);
				if (value != null) {
					PropertyUtils.setSimpleProperty(dest, name, value);
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据模型的名称获取模型的实例.
	 * <p>
	 * 例如：
	 * 
	 * <pre>
	 * User user = (User) ReflectionUtils.getEntityInstance(&quot;User&quot;);
	 * </pre>
	 * 
	 * @param entityName
	 *            模型名称，不含包名，区分大小写，类名必须以大写开头
	 * @param packageName
	 *            包名
	 * @return 模型实例
	 * @throws NoSuchMethodException
	 *             NoSuchMethodException
	 * @throws ClassNotFoundException
	 *             ClassNotFoundException
	 * @throws InstantiationException
	 *             InstantiationException
	 * @throws IllegalAccessException
	 *             IllegalAccessException
	 * @throws InvocationTargetException
	 *             InvocationTargetException
	 */
	public static Object getEntityInstance(final String entityName, final String packageName)
			throws NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException,
			InvocationTargetException {
		Constructor<?> constructor = Class.forName(packageName + entityName).getConstructor();
		return constructor.newInstance();
	}

	/**
	 * 通过反射，获得类定义中声明的父类的第一个泛型参数的类型.如无法找到，返回Object.class.<br>
	 * 
	 * 例如：
	 * 
	 * <pre>
	 * public UserDAO extends DefaultDAOImpl&lt;User, Long&gt;
	 * </pre>
	 * 
	 * 可通过此方法获取到User类型.
	 * 
	 * @param target
	 *            目标类
	 * @param <T>
	 *            泛型
	 * 
	 * @return 第一个泛型类型
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getSuperClassGenricType(@SuppressWarnings("rawtypes") final Class target) {
		return getSuperClassGenricType(target, 0);
	}

	/**
	 * 通过反射，获得类定义中声明的父类的泛型参数的类型.如无法找到，返回Object.class.<br>
	 * 
	 * 例如：
	 * 
	 * <pre>
	 * public UserDAO extends DefaultDAOImpl&lt;User, Long&gt;
	 * </pre>
	 * 
	 * 可通过此方法获取到User类型.
	 * 
	 * @param clazz
	 *            类
	 * @param index
	 *            索引
	 * @return 泛型类型
	 */
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}

		return (Class) params[index];
	}
}
