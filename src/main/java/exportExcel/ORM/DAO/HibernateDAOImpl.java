/*
 * Copyright (C) CCRISE.
 */
package exportExcel.ORM.DAO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import exportExcel.utils.Page;
import exportExcel.utils.ReflectionUtils;

/**
 * 默认的DAO实现.通常情况下，继承此类即可完成多数情况下的数据操作功能.
 * 
 * <p>
 * 示例：
 * 
 * <pre>
 * &#064;Repository
 * public class UserDao extends HibernateDAOImpl&lt;User, Long&gt; {
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
public abstract class HibernateDAOImpl<T, PK extends Serializable> implements HibernateDAO<T, PK> {
	/**
	 * 日志.
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 负责初始化Hibernate.
	 */
	@Autowired
	protected SessionFactory sessionFactory;
	/**
	 * 实体类型.
	 */
	protected Class<T> entityClass;

	/**
	 * 默认构造方法.
	 */
	public HibernateDAOImpl() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 用于省略DAO层, 在Service层直接使用通用HibernateDAOImpl的构造函数.
	 * <p>
	 * 在构造函数中定义对象类型Class.
	 * <p>
	 * 示例：
	 * 
	 * <pre>
	 * HibernateDAOImpl&lt;User, Long&gt; userDao = new HibernateDAOImpl&lt;User, Long&gt;(sessionFactory, User.class);
	 * </pre>
	 * 
	 * @param sessionFactory
	 *            session工厂
	 * @param entityClass
	 *            实体类型
	 */
	public HibernateDAOImpl(final SessionFactory sessionFactory, final Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	@Override
	public long count() {
		return Long.parseLong(createCriteria().setProjection(Projections.rowCount()).list().get(0).toString());
	}

	@Override
	public long count(final Criterion... criterions) {
		return Long
				.parseLong(createCriteria(criterions).setProjection(Projections.rowCount()).list().get(0).toString());
	}

	@Override
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria result = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			result.add(c);
		}
		return result;
	}

	@Override
	public Query createQuery(final String queryString) {
		Assert.hasLength(queryString, "HQL语句不能为空");
		return getSession().createQuery(queryString);
	}

	@Override
	public SQLQuery createSQLQuery(final String queryString) {
		Assert.hasLength(queryString, "SQL语句不能为空");
		return getSession().createSQLQuery(queryString);
	}

	@Override
	public boolean delete(final PK id) {
		Assert.notNull(id, "删除实体类对象id不能为null");
		try {
			getSession().delete(load(id));
			logger.debug("删除实体类{}成功，id为：{}", this.entityClass.getSimpleName(), id);
			return true;
		} catch (HibernateException e) {
			logger.error("删除实体类{}的实体时出错，id为：{}", this.entityClass.getSimpleName(), id);
			return false;
		}
	}

	@Override
	public boolean delete(final T entity) {
		Assert.notNull(entity, "删除实体类对象不能为null");
		try {
			getSession().delete(entity);
			logger.debug("删除实体类：{}成功", entity);
			return true;
		} catch (HibernateException e) {
			logger.error("删除实体类：{}的实体时出错", this.entityClass.getSimpleName());
			return false;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> find(final Order order, final Criterion... criterions) {
		return createCriteria(criterions).addOrder(order).list();
	}

	@Override
	public List<T> findBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findUnique(final Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findUniqueBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}

	/**
	 * 刷新当前Session.
	 */
	public void flush() {
		getSession().flush();
	}

	@Override
	public List<T> get(final Collection<PK> ids) {
		return find(Restrictions.in(getIdName(), ids));
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get(final PK id) {
		Assert.notNull(id, "实体类对象id不能为null");
		return (T) getSession().get(entityClass, id);
	}

	@Override
	public List<T> getAll() {
		return find();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll(final String orderByProperty, final boolean isAsc) {
		Criteria c = createCriteria();
		if (isAsc) {
			c.addOrder(Order.asc(orderByProperty));
		} else {
			c.addOrder(Order.desc(orderByProperty));
		}
		return c.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Page<T> getPage(final Page<T> page) {
		page.setTotalCount(count());
		if (!page.getOrderBy().equals("") && !page.getOrder().equals("")) {
			String[] properties = StringUtils.split(page.getOrderBy(), ',');
			String[] orders = StringUtils.split(page.getOrder(), ',');

			Criteria criteria = createCriteria().setFirstResult(page.getFirst() - 1).setMaxResults(page.getPageSize());
			for (int i = 0; i < properties.length; i++) {
				if (orders[i].equals("desc")) {
					criteria = criteria.addOrder(Order.desc(properties[i]));
				} else {
					criteria = criteria.addOrder(Order.asc(properties[i]));
				}
			}

			page.setResult(criteria.list());
			return page;
		}
		page.setResult(createCriteria().setFirstResult(page.getFirst() - 1).setMaxResults(page.getPageSize()).list());
		return page;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Page<T> getPage(final Page<T> page, final Criteria masterCriteria, final Criteria subCriteria) {
		subCriteria.setFirstResult(page.getFirst() - 1).setMaxResults(page.getPageSize());

		if (!page.getOrderBy().equals("") && !page.getOrder().equals("")) {
			String[] properties = StringUtils.split(page.getOrderBy(), ',');
			String[] orders = StringUtils.split(page.getOrder(), ',');

			for (int i = 0; i < properties.length; i++) {
				if (orders[i].equals("desc")) {
					masterCriteria.addOrder(Order.desc(properties[i]));
				} else {
					masterCriteria.addOrder(Order.asc(properties[i]));
				}
			}
		}

		List<T> result = subCriteria.list();
		page.setTotalCount(result.size());
		page.setResult(result);
		return page;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Page<T> getPage(final Page<T> page, final Criterion... criterions) {
		page.setTotalCount(count(criterions));
		if (!page.getOrderBy().equals("") && !page.getOrder().equals("")) {
			String[] properties = StringUtils.split(page.getOrderBy(), ',');
			String[] orders = StringUtils.split(page.getOrder(), ',');

			Criteria criteria = createCriteria(criterions).setFirstResult(page.getFirst() - 1).setMaxResults(
					page.getPageSize());
			for (int i = 0; i < properties.length; i++) {
				if (orders[i].equals("desc")) {
					criteria.addOrder(Order.desc(properties[i]));
				} else {
					criteria.addOrder(Order.asc(properties[i]));
				}
			}

			page.setResult(criteria.list());
			return page;
		}
		page.setResult(createCriteria(criterions).setFirstResult(page.getFirst() - 1).setMaxResults(page.getPageSize())
				.list());
		return page;
	}

	@Override
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 获取Statistics对象.
	 * 
	 * @return Statistics对象
	 */
	public Statistics getStatistics() {
		return sessionFactory.getStatistics();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T load(final PK id) {
		Assert.notNull(id, "实体类对象id不能为null");
		return (T) getSession().load(entityClass, id);
	}

	@Override
	public boolean merge(final T entity) {
		Assert.notNull(entity, "合并更新实体类对象不能为null");
		try {
			getSession().merge(entity);
			logger.debug("合并更新实体类：{}成功", entity);
			return true;
		} catch (HibernateException e) {
			logger.error("合并更新类：{}的实体时出错", this.entityClass.getSimpleName());
			return false;
		}
	}

	@Override
	public boolean save(final T entity) {
		Assert.notNull(entity, "保存实体类对象不能为null");
		try {
			getSession().saveOrUpdate(entity);
			logger.debug("保存实体类：{}成功", entity);
			return true;
		} catch (HibernateException e) {
			logger.error("保存实体类：{}的实体时出错", this.entityClass.getSimpleName());
			return false;
		}
	}

	@Override
	public boolean update(final T entity) {
		try {
			getSession().update(entity);
			logger.debug("更新实体类：{}成功", entity);
			return true;
		} catch (HibernateException e) {
			logger.error("更新实体类：{}的实体时出错", this.entityClass.getSimpleName());
			return false;
		}
	}

	/**
	 * 取得对象的主键名.
	 * 
	 * @return 主键名
	 */
	private String getIdName() {
		ClassMetadata meta = getSession().getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}
}
