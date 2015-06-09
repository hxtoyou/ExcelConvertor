/*
 * Copyright (C) CCRISE.
 */
package exportExcel.ORM.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import exportExcel.utils.Page;

/**
 * 针对Hibernate的数据操作接口，在DAO接口的基础上增加了一些常用的方法.
 * 
 * @param <T>
 *            实体类型
 * @param <PK>
 *            主键类型
 * 
 * @author Xiong Shuhong
 */
public interface HibernateDAO<T, PK extends Serializable> extends GenericDAO<T, PK> {
	/**
	 * 根据零或多个条件获取具体实体类对应数据库的记录总数.
	 * 
	 * @param criteria
	 *            零或多个条件
	 * @return 记录总数
	 */
	long count(final Criterion... criteria);

	/**
	 * 根据零或多个条件获取创建条件.
	 * 
	 * @param criteria
	 *            零或多个条件
	 * @return Criteria对象
	 */
	Criteria createCriteria(final Criterion... criteria);

	/**
	 * 根据HQL语句创建Query对象.
	 * 
	 * @param queryString
	 *            HQL语句
	 * @return Query对象
	 */
	Query createQuery(final String queryString);

	/**
	 * 根据SQL语句创建SQLQuery对象.
	 * 
	 * @param queryString
	 *            SQL语句
	 * @return SQLQuery对象
	 */
	SQLQuery createSQLQuery(final String queryString);

	/**
	 * 根据零或多个条件获取具体实体类对应数据库的记录列表.
	 * 
	 * @param criteria
	 *            零或多个条件
	 * @return 记录列表
	 */
	List<T> find(final Criterion... criteria);

	/**
	 * 根据零或多个条件获取具体实体类对应数据库的记录列表并排序.
	 * 
	 * @param order
	 *            排序
	 * @param criteria
	 *            零或多个条件
	 * @return 记录列表
	 */
	List<T> find(final Order order, final Criterion... criteria);

	/**
	 * 根据零或多个条件获取具体实体类对应数据库的唯一记录.
	 * 
	 * @param criteria
	 *            零或多个条件
	 * @return 唯一记录
	 */
	T findUnique(final Criterion... criteria);

	/**
	 * 根据零或多个条件和关联属性的零或多个条件获取分页数据.
	 * 
	 * @param page
	 *            Page对象
	 * @param masterCriteria
	 *            零或多个条件
	 * @param subCriteria
	 *            关联属性的零或多个条件
	 * @return 包含数据的Page对象
	 */
	Page<T> getPage(final Page<T> page, final Criteria masterCriteria, final Criteria subCriteria);

	/**
	 * 根据零或多个条件获取分页数据.
	 * 
	 * @param page
	 *            Page对象
	 * @param criteria
	 *            零或多个条件
	 * @return 包含数据的Page对象
	 */
	Page<T> getPage(final Page<T> page, final Criterion... criteria);

	/**
	 * 获取当前Session对象.
	 * 
	 * @return Session对象
	 */
	Session getSession();

	/**
	 * 根据主键获取对象，在真正用到对象时才执行数据库操作，找不到对象时抛出ObjectNotFoundEcception.
	 * 
	 * @param id
	 *            对象对应数据库的主键
	 * @return 实体类对象
	 */
	T load(final PK id);

	/**
	 * 合并更新瞬态和持久态的同一实体类对象.
	 * 
	 * @param entity
	 *            实体类对象
	 * @return true 合并成功 false 合并失败
	 */
	boolean merge(final T entity);
}
