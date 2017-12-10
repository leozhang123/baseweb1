/**
 * 
 * Create on 2016年11月1日
 */
package org.zl.service.weixin;

import org.zl.exception.ServiceException;

/**
 * 微信发送消息接口
 * @author Leo
 * @version 0.0.2
 */
public interface SendService {
	
	/**
	 * 发送微信文本消息
	 * @param wxMessage 文本消息
	 * @throws ServiceException
	 */
	void sendTextMessage(String wxMessage) throws ServiceException;
	
	/**
	 * 发送微信文本消息
	 * @param wxMessage 文本消息
	 * @param receiver 接收者
	 * @throws ServiceException
	 */
	void sendTextMessage(String wxMessage,String receiver) throws ServiceException;

}
