/**
 * 
 * Create on 2016年11月19日
 */
package org.zl.service;

import java.util.List;

import org.zl.exception.ServiceException;
import org.zl.model.CityDelivery;
import org.zl.model.Order;
import org.zl.model.User;
import org.zl.model.WorkApp;
import org.zl.service.weixin.SendService;

/**
 * 企业号服务
 * 扩展发送服务接口
 * 
 * @author Leo
 * @version 0.0.2
 */
public interface QYService extends SendService {

	/**
	 * 推送用户到企业号,异步操作
	 * 
	 * @param user 用户信息
	 * @throws ServiceException
	 */
	void pushUser(User user)  throws ServiceException;
	
	/**
	 * 推送订单消息
	 * 
	 * @param order
	 * @throws ServiceException
	 */
	void pushOrder(Order order)  throws ServiceException;
	
	/**
	 * 向司机推送消息
	 * 消息内容会根据运单状态区分
	 * @param delivery
	 * @throws ServiceException
	 */
	void sendMessage2Driver(CityDelivery delivery) throws ServiceException;
	
	/**
	 * 获取企业号所有服务
	 * 
	 * @return
	 * @throws ServiceException
	 */
	List<WorkApp> listWorkApp() throws ServiceException;
}
