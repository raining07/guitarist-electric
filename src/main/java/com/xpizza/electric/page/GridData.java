package com.xpizza.electric.page;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 
 * @ClassName: GridData
 * @Description: TODO 封装成mmgrid需要的json格式
 * @author: Xpizza
 * @date: Jul 18, 2017 10:51:14 AM
 */
public class GridData<E> {

	/** 总记录数 */
	private long totalCount;

	/** 数据 */
	private List<E> items;

	public GridData(Page<E> page) {
		super();
		this.totalCount = page.getTotalElements();
		this.items = page.getContent();
	}

	public GridData(long totalCount, List<E> items) {
		super();
		this.totalCount = totalCount;
		this.items = items;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<E> getItems() {
		return items;
	}

	public void setItems(List<E> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "GridData [totalCount=" + totalCount + ", items.size=" + items.size() + "]";
	}

}
