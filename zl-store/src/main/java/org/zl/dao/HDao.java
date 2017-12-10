/**
 * 
 * Create on 2016年11月6日
 */
package org.zl.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Leo
 * @version 0.0.1
 */
public interface HDao {

	int DEFAULT_PAGE_SIZE = 10;
	
	<T> T getValue(Class<T> entityClass, Serializable id);
	
	void saveOrUpdate(Object object);
	
	public <T> List<T> findAll(Class<T> entityClass,int pageid, int pageSize);
	
	public <T> Page<T> findAll(Class<T> entityClass,Pageable pageable);
	
}
