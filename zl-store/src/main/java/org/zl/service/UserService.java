/**
 * 
 * Create on 2016年11月6日
 */
package org.zl.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zl.exception.ServiceException;
import org.zl.exception.UserAlreadyExistsException;
import org.zl.model.User;
import org.zl.model.WXUser;

/**
 * 用户服务
 * 
 * @author Leo
 * @version 0.0.1
 */
public interface UserService {

	
	User getWXUser(String id) throws ServiceException;
	
	User getUser(String userid) throws ServiceException;
	
	void saveUser(User user) throws ServiceException;
	
	void updateUser(User user) throws ServiceException;
	
	void saveOrUpdateUser(User user) throws ServiceException;
	
	void saveOrUpdateWXUser(WXUser user) throws ServiceException;
	
	public List<User> getUsers(String pageId,String pageSize);
	
	public Page<User> findUsers(String pageId,String pageSize);
	
	/**
	 * 查找司机
	 * @param pageable
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public Page<User> findDriver(Pageable pageable,User params) throws ServiceException;
	
	/**
	 * 小程序获取用户信息
	 * 
	 * @param sessionId 小程序会话id
	 * @return
	 * @throws ServiceException
	 */
	User getUserForMiniapp(String sessionId) throws ServiceException;
	
	/**
	 * 小程序用户注册
	 * 
	 * @param sessionId 会话id
	 * @param user 注册用户信息
	 * @throws ServiceException 服务异常
	 * @throws UserAlreadyExistsException 用户存在
	 */
	void saveUserForMiniapp(User user) throws ServiceException, UserAlreadyExistsException;
	
	/**
	 * 小程序绑定用户
	 * 
	 * @param user
	 * @throws ServiceException
	 * @throws UserAlreadyExistsException 
	 */
	void bindUserForMiniapp(User user) throws ServiceException, UserAlreadyExistsException;
}
