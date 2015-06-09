/*
 * Copyright (C) CCRISE.
 */
package exportExcel.utils;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.common.collect.Lists;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装.
 * <p>
 * 默认页码为1，默认页面大小为10.
 * 
 * @param <T>
 *            实体类型
 * 
 * @author Xiong Shuhong
 */
public class Page<T> {
	// 公共变量
	/**
	 * 升序.
	 */
	public static final String ASC = "asc";
	/**
	 * 降序.
	 */
	public static final String DESC = "desc";
	private static final int DEFAULT_PAGE_SIZE = 10;

	// 分页参数
	protected int pageNumber = 0;
	protected int pageSize = DEFAULT_PAGE_SIZE;
	protected String orderBy = "";
	protected String order = "";

	// 返回结果
	protected List<T> result = Lists.newArrayList();
	protected long totalCount = -1;

	// 用于RestTemplate
	protected int totalPages;
	protected int first;
	protected int nextPage;
	protected int prevPage;
	protected boolean hasNext;
	protected boolean hasPrev;

	/**
	 * 默认构造方法.
	 */
	public Page() {
	}

	/**
	 * 构造方法，通过参数设置页面大小.如果传入参数小于0，则页面大小将被设置为1.
	 * 
	 * @param pageSize
	 *            页面大小
	 */
	public Page(final int pageSize) {
		if (pageSize < 0) {
			this.pageSize = 1;
		} else {
			this.pageSize = pageSize;
		}
	}

	/**
	 * 根据pageNumber和pageSize计算当前页第一条数据在总结果集中的位置，序号从1开始.
	 * 
	 * @return 当前页第一条数据在结果集中的位置
	 */
	public int getFirst() {
		return ((pageNumber - 1) * pageSize) + 1;
	}

	/**
	 * 取得下页的页号，序号从1开始.当前页为尾页时仍返回尾页序号.
	 * 
	 * @return 下页的页号
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNumber + 1;
		} else {
			return pageNumber;
		}
	}

	/**
	 * 获得排序方向, 无默认值.
	 * 
	 * @return 排序方向
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 获得排序字段,无默认值.多个排序字段时用','分隔.
	 * 
	 * @return 排序字段
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 获得当前页的页号，序号从1开始.
	 * 
	 * @return 当前页号
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * 获得每页的数据数量.
	 * 
	 * @return 页面大小
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 取得上页的页号，序号从1开始，当前页为首页时返回首页序号.
	 * 
	 * @return 上页的页号
	 */
	public int getPrevPage() {
		if (isHasPrev()) {
			return pageNumber - 1;
		} else {
			return pageNumber;
		}
	}

	/**
	 * 取得页内的数据列表.
	 * 
	 * @return 包含结果的数据列表
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 获取数据总数.
	 * 
	 * @return 数据总数
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 根据pageSize与totalCount计算总页数，默认值为-1.
	 * 
	 * @return 总页数
	 */
	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 是否还有下一页.
	 * 
	 * @return 是否有下一页
	 */
	public boolean isHasNext() {
		return (pageNumber + 1 <= getTotalPages());
	}

	/**
	 * 是否还有上一页.
	 * 
	 * @return 是否有上一页
	 */
	public boolean isHasPrev() {
		return (pageNumber - 1 >= 1);
	}

	public void setFirst(final int first) {
		this.first = first;
	}

	public void setHasNext(final boolean hasNext) {
		this.hasNext = hasNext;
	}

	public void setHasPrev(final boolean hasPrev) {
		this.hasPrev = hasPrev;
	}

	public void setNextPage(final int nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * 设置排序方式向.
	 * 
	 * @param order
	 *            可选值为desc或asc,多个排序字段时用','分隔
	 */
	public void setOrder(final String order) {
		String lowcaseOrder = StringUtils.lowerCase(order);

		// 检查order字符串的合法值
		String[] orders = StringUtils.split(lowcaseOrder, ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
			}
		}

		this.order = lowcaseOrder;
	}

	/**
	 * 设置排序字段,多个排序字段时用','分隔.
	 * 
	 * @param orderBy
	 *            排序字段
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 设置当前页的页号，序号从1开始，低于1时自动调整为1.
	 * 
	 * @param pageNumber
	 *            页号
	 */
	public void setPageNumber(final int pageNumber) {
		if (pageNumber < 0) {
			this.pageNumber = 0;
			return;
		}

		this.pageNumber = pageNumber;
	}

	/**
	 * 设置每页的数据数量，低于1时自动调整为1.
	 * 
	 * @param pageSize
	 *            设置每页数据数量
	 */
	public void setPageSize(final int pageSize) {
		if (pageSize < 1) {
			this.pageSize = 1;
			return;
		}

		this.pageSize = pageSize;
	}

	public void setPrevPage(final int prevPage) {
		this.prevPage = prevPage;
	}

	/**
	 * 设置数据列表.
	 * 
	 * @param result
	 *            数据列表
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * 设置数据总数.
	 * 
	 * @param totalCount
	 *            数据总数
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	// 用于RestTemplate
	public void setTotalPages(final int totalPages) {
		this.totalPages = totalPages;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
