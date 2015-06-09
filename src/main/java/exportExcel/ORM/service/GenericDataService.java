/*
 * Copyright (C) CCRISE.
 */
package exportExcel.ORM.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import exportExcel.ORM.DAO.GenericDAO;
import exportExcel.utils.Page;

/**
 * 泛型数据服务接口.
 * <p>
 * 子类通过重写{@link #getDAO()}方法获取其他方法依赖的GenericDAO的实现类.
 * 
 * @param <T>
 *            实体类型
 * @param <PK>
 *            主键类型
 * 
 * @author Xiong Shuhong
 */
public interface GenericDataService<T, PK extends Serializable> {
	/**
	 * 获取具体实体类在数据库中的对象总数.
	 * 
	 * @return 总数
	 */
	long count();

	/**
	 * 根据对象id删除对象，并返回删除操作的结果.
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
	 * @return 对象
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
	 * 根据主键获取对象.
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
	List<T> getAll(final String orderByProperty, final boolean isAsc);

	/**
	 * 子类通过重写此方法注入具体的GenericDAO实现类.
	 * 
	 * @return GenericDAO实现类
	 */
	GenericDAO<T, PK> getDAO();

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
