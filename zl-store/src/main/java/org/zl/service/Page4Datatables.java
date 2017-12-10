/**
 * 
 * Create on 2016年11月30日
 */
package org.zl.service;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * 前端js datatables组件 需要recordsTotal,recordsFiltered参数
 * 目前没解决前端组件如何修改这2个属性,因此构建此类来解决列表显示问题
 * 
 * @author Leo
 * @version 0.0.1
 */
public class Page4Datatables<T> extends PageImpl<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5766534174922747871L;

	/**
	 * @param content
	 */
	public Page4Datatables(List<T> content) {
		super(content);
	}

	/**
	 * @param content
	 * @param pageable
	 * @param total
	 */
	public Page4Datatables(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	public long getRecordsTotal(){
		return getTotalElements();
	}
	
	public long getRecordsFiltered(){
		return getTotalElements();
	}
}
