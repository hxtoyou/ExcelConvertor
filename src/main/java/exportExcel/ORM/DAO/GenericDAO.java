/*
 * Copyright (C) CCRISE.
 */
package exportExcel.ORM.DAO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import exportExcel.utils.Page;

/**
 * 泛型DAO，定义对数据的基本操作方法.
 * 
 * @param <T>
 *            实体类型
 * @param <PK>
 *            主键类型
 * 
 * @author Xiong Shuhong
 */
public interface GenericDAO<T, PK extends Serializable> {
	/**
	 * 获取具体实体类在数据库中的记录总数.
	 * 
	 * @return 总数
	 */
	long count();

	/**
	 * 根据id删除对象，并返回删除操作的结果.
	 * 
	 * @param id
	 *            实体类对象id
	 * @return true 删除成功 false 删除失败
	 */
	boolean delete(final PK id);

	/**
	 * 删除对象，并返回删除操作的结果.
	 * 
	 * @param entity
	 *            实体类对象
	 * @return true 删除成功 false 删除失败
	 */
	boolean delete(final T entity);

	/**
	 * 根据属性名和值查找对象.
	 * 
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            值
	 * @return 对象列表
	 */
	List<T> findBy(final String propertyName, final Object value);

	/**
	 * 根据属性名和值查找唯一对象，匹配方法为相等.
	 * 
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            值
	 * @return 对象或者<code>null</code>
	 */
	T findUniqueBy(final String propertyName, final Object value);

	/**
	 * 根据id列表获取对象列表.
	 * 
	 * @param ids
	 *            id列表
	 * @return 对象列表
	 */
	List<T> get(final Collection<PK> ids);

	/**
	 * 根据主键获取对象，找不到对象则返回null.
	 * 
	 * @param id
	 *            对象对应数据库的主键
	 * @return 实体类对象
	 */
	T get(final PK id);

	/**
	 * 获取具体实体类在数据库的所有对象，返回对象列表.
	 * 
	 * @return 所有实体类对象列表
	 */
	List<T> getAll();

	/**
	 * 排序获取所有对象列表.
	 * 
	 * @param orderByProperty
	 *            排序字段
	 * @param isAsc
	 *            true 顺序 false 降序
	 * @return 对象列表
	 */
	List<T> getAll(final String orderByProperty, boolean isAsc);

	/**
	 * 分页获取数据.
	 * 
	 * @param page
	 *            Page对象
	 * @return 包含数据的Page对象
	 */
	Page<T> getPage(final Page<T> page);

	/**
	 * 保存对象，并返回保存操作的结果.
	 * 
	 * @param entity
	 *            实体类对象
	 * @return true 保存成功 false 保存失败
	 */
	boolean save(final T entity);

	/**
	 * 更新对象，并返回更新操作的结果.
	 * 
	 * @param entity
	 *            实体类对象
	 * @return true 更新成功 false 更新失败
	 */
	boolean update(final T entity);
}
