/**
 * 
 * Create on 2016年11月6日
 */
package org.zl.dao.hibernate.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import org.zl.dao.UserDao;
import org.zl.dao.hibernate.model.WXAPPSessionKey;
import org.zl.dao.hibernate.model.WXUser;
import org.zl.dao.hibernate.model.ZUser;
import org.zl.service.Page4Datatables;

/**
 * @author Leo
 * @version 0.0.1
 */
public class UserDaoImpl extends BaseDao implements UserDao {

	
	/* (non-Javadoc)
	 * @see org.zl.dao.UserDao#getWXUser(java.lang.String)
	 */
	@Override
	public WXUser getWXUser(String userId) {
		logger.debug("getWXUser:"+userId);
		return super.get(WXUser.class, userId);
	}

	/* (non-Javadoc)
	 * @see org.zl.dao.UserDao#saveWX(org.zl.model.WXUser)
	 */
	@Override
	public void saveWX(WXUser user) {
		logger.debug("saveWX:"+user);
		super.save(user);
	}

	/* (non-Javadoc)
	 * @see org.zl.dao.UserDao#updateWX(org.zl.model.WXUser)
	 */
	@Override
	public void updateWX(WXUser user) {
		logger.debug("updateWX:"+user);
		super.update(user);
	}

	/* (non-Javadoc)
	 * @see org.zl.dao.UserDao#deleteWX(org.zl.model.WXUser)
	 */
	@Override
	public void deleteWX(WXUser user) {
		logger.debug("deleteWX:"+user);
		super.delete(user);
	}

	/* (non-Javadoc)
	 * @see org.zl.dao.UserDao#getUser(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public <T> T getUser(Class<T> entityClass, Serializable id) {
		return super.get(entityClass, id);
	}

	/* (non-Javadoc)
	 * @see org.zl.dao.UserDao#saveUser(java.lang.Object)
	 */
	@Override
	public void saveUser(Object user) {
		super.save(user);
	}

	/* (non-Javadoc)
	 * @see org.zl.dao.UserDao#updateUser(java.lang.Object)
	 */
	@Override
	public void updateUser(Object user) {
		super.update(user);
	}

	/* (non-Javadoc)
	 * @see org.zl.dao.UserDao#deleteUser(java.lang.Object)
	 */
	@Override
	public void deleteUser(Object user) {
		super.delete(user);
	}

	/* (non-Javadoc)
	 * @see org.zl.dao.UserDao#getUserByloginName(java.lang.String)
	 */
	@Override
	public ZUser getUserByloginName(String loginName) {
		DetachedCriteria dc = DetachedCriteria.forClass(ZUser.class);
		Criterion c1 = Restrictions.eq("loginName", loginName);
		dc.add(c1);
		@SuppressWarnings("unchecked")
		List<ZUser> list = (List<ZUser>) super.findByCriteria(dc);
		ZUser user = null;
		if(list.size()>0){
			user = list.get(0);
		}
		return user;
	}

	@Override
	public <T> Page<T> findDriver(Pageable pageable, ZUser params) {
		DetachedCriteria dc = DetachedCriteria.forClass(ZUser.class);
		Criterion criterion=Restrictions.isNotNull("car");

		@SuppressWarnings("unchecked")
		List<T> list = (List<T>)super.findByCriteria(dc,pageable.getOffset(),pageable.getPageSize());
		Page<T> page = new Page4Datatables<T>(list,pageable,count(ZUser.class,criterion));
		return page;
	}

	@Override
	public WXUser getUserByMiniapp(String sessionId) {
		DetachedCriteria dc = DetachedCriteria.forClass(WXAPPSessionKey.class);
		Criterion c1 = Restrictions.eq("myId", sessionId);
		dc.add(c1);
		@SuppressWarnings("unchecked")
		List<WXAPPSessionKey> list = (List<WXAPPSessionKey>) super.findByCriteria(dc);
		Assert.notEmpty(list, "无session记录");
		WXAPPSessionKey key = list.get(0);
		Assert.notNull(key, "session is invalid");
		WXUser wxUser = getWXUser(key.getOpenid());
		return wxUser;
	}

}
