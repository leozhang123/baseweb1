/**
 * 
 * Create on 2016年11月6日
 */
package org.zl.dao;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zl.dao.hibernate.model.WXUser;
import org.zl.dao.hibernate.model.ZUser;

/**
 * @author Leo
 * @version 0.0.1
 */
public interface UserDao extends HDao {

	public int DEFAULT_PAGE_SIZE = 10;
	
	public <T> T getUser(Class<T> entityClass, Serializable id);
	
	public void saveUser(Object user);
	
	public void updateUser(Object user);
	
	public void deleteUser(Object user);
	
	public WXUser getWXUser(String userId);
	
	public void saveWX(WXUser user);
	
	public void updateWX(WXUser user);
	
	public void deleteWX(WXUser user);
	
	public ZUser getUserByloginName(String loginName);
	
	public <T> Page<T> findDriver(Pageable pageable,ZUser params);
	
	WXUser getUserByMiniapp(String sessionId);
}
