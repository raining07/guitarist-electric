package com.xpizza.electric.page;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: PageParams
 * @Description: TODO 分页参数
 * @author: Xpizza
 * @date: Jul 17, 2017 2:00:48 PM
 */
public class PageParams implements java.io.Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 8491829841762306952L;

	/**
	 * 每页大小条数
	 */
	@JsonProperty(value = "limit")
	private int pageSize = 20;

	/**
	 * 当前页 从1开始
	 */
	@JsonProperty(value = "page")
	private int pageNo = 1;

	@JsonProperty(value = "sort")
	private List<Order> orders = new ArrayList<Order>();

	public PageParams() {
		super();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int size) {
		this.pageSize = (size < 0 ? 10 : size);
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int page) {
		this.pageNo = (page < 1 ? 1 : page);
	}

	public void addOrder(Direction direction, String property) {
		orders.add(new Order(direction, property));
	}

	public void addOrder(String property) {
		addOrder(Direction.DESC, property);
	}

	public Pageable toPageable() {
		Pageable pageable = null;
		if (orders == null || orders.size() < 1)
			pageable = new PageRequest(pageNo - 1, pageSize);
		else {
			pageable = new PageRequest(pageNo - 1, pageSize, new Sort(orders));
		}
		return pageable;
	}

	@Override
	public String toString() {
		return "PageParams [pageSize=" + pageSize + ", pageNo=" + pageNo + "]";
	}

}
