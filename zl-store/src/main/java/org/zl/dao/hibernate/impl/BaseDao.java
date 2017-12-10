/**
 * 
 * Create on 2017年6月6日
 */
package org.zl.dao.hibernate.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.zl.dao.HDao;
import org.zl.service.Page4Datatables;

/**
 * @author Leo
 * @version 0.0.1
 */
public class BaseDao extends HibernateTemplate implements HDao {

	protected final Logger logger = LoggerFactory.getLogger("store");
	
	/* (non-Javadoc)
	 * @see org.zl.dao.HDao#getValue(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public <T> T getValue(Class<T> entityClass, Serializable id) {
		return super.get(entityClass, id);
	}

	/* (non-Javadoc)
	 * @see org.zl.dao.HDao#findAll(java.lang.Class, int, int)
	 */
	@Override
	public <T> List<T> findAll(Class<T> entityClass, int pageid, int pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>)super.findByCriteria(dc,pageid*pageSize,pageSize);
		return list;
	}

	/* (non-Javadoc)
	 * @see org.zl.dao.HDao#findAll(java.lang.Class, org.springframework.data.domain.Pageable)
	 */
	@Override
	public <T> Page<T> findAll(Class<T> entityClass, Pageable pageable) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>)super.findByCriteria(dc,pageable.getOffset(),pageable.getPageSize());
		Page<T> page = new Page4Datatables<T>(list,pageable,count(entityClass));
		return page;
	}

	public long count(Class<?> entityClass,Criterion... criterions) {
	    Criteria c = this.getSessionFactory().getCurrentSession().createCriteria(entityClass);
	    for (Criterion criterion : criterions) {
	    	if(criterion!=null)
	    	c.add(criterion);
		}
	    c.setProjection(Projections.rowCount());
	    Number cnt = (Number) c.uniqueResult();
	    return cnt.longValue();
	  }
}
