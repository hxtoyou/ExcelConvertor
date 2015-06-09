/*
 * Copyright (C) CCRISE.
 */
package exportExcel.ORM.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import exportExcel.ORM.DAO.HibernateDAO;
import exportExcel.utils.Page;

/**
 * 默认的数据服务实现.
 * <p>
 * 子类通过覆盖{@link #getDAO()}方法实现注入具体的DAO实现类.
 * 
 * 例如：
 * 
 * <pre>
 * &#064;Service
 * public class ResourceService extends HibernateDataServiceImpl&lt;Resource, Long&gt; {
 * 	&#064;Autowired
 * 	private ResourceDAO resourceDAO;
 * 
 * 	&#064;Override
 * 	public HibernateDAO&lt;Resource, Long&gt; getDAO() {
 * 		return resourceDAO;
 * 	}
 * }
 * </pre>
 * 
 * @param <T>
 *            实体类型
 * @param <PK>
 *            主键类型
 * 
 * @author Xiong Shuhong
 */
@Transactional
public abstract class HibernateDataServiceImpl<T, PK extends Serializable> implements HibernateDataService<T, PK> {
	/**
	 * 日志.
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional(readOnly = true)
	public long count() {
		return getDAO().count();
	}

	@Override
	@Transactional(readOnly = true)
	public long count(final Criterion... criterions) {
		return getDAO().count(criterions);
	}

	@Override
	public Query createQuery(final String queryString) {
		return getDAO().createQuery(queryString);
	}

	@Override
	public SQLQuery createSQLQuery(final String queryString) {
		return getDAO().createSQLQuery(queryString);
	}

	@Override
	public boolean delete(final PK id) {
		return getDAO().delete(id);
	}

	@Override
	public boolean delete(final T entity) {
		return getDAO().delete(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> find(final Criterion... criterions) {
		return getDAO().find(criterions);
	}

	@Override
	public List<T> find(final Order order, final Criterion... criteria) {
		return getDAO().find(order, criteria);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findBy(final String propertyName, final Object value) {
		return getDAO().findBy(propertyName, value);
	}

	@Override
	@Transactional(readOnly = true)
	public T findUnique(final Criterion... criterions) {
		return getDAO().findUnique(criterions);
	}

	@Override
	@Transactional(readOnly = true)
	public T findUniqueBy(final String propertyName, final Object value) {
		return getDAO().findUniqueBy(propertyName, value);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> get(final Collection<PK> ids) {
		return getDAO().get(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public T get(final PK id) {
		return getDAO().get(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getAll() {
		return getDAO().getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getAll(final String orderByProperty, final boolean isAsc) {
		return getDAO().getAll(orderByProperty, isAsc);
	}

	/**
	 * 子类通过重写此方法注入具体的DAO实现类.
	 * 
	 * @return 子类的DAO实现类
	 */
	@Override
	public abstract HibernateDAO<T, PK> getDAO();

	@Override
	@Transactional(readOnly = true)
	public Page<T> getPage(final Page<T> page) {
		return getDAO().getPage(page);
	}

	@Override
	public Page<T> getPage(final Page<T> page, final Criteria masterCriteria, final Criteria subCriteria) {
		return getDAO().getPage(page, masterCriteria, subCriteria);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<T> getPage(final Page<T> page, final Criterion... criterions) {
		return getDAO().getPage(page, criterions);
	}

	@Override
	@Transactional(readOnly = true)
	public T load(final PK id) {
		return getDAO().load(id);
	}

	@Override
	public boolean merge(final T entity) {
		return getDAO().merge(entity);
	}

	@Override
	public boolean save(final T entity) {
		return getDAO().save(entity);
	}

	@Override
	public boolean update(final T entity) {
		return getDAO().update(entity);
	}
}
