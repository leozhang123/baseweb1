/**
 * 
 * Create on 2016年11月1日
 */
package org.zl.service.weixin;

import org.zl.exception.ServiceException;

/**
 * @author Leo
 * @version 0.0.1
 */
public interface ReceiveService {
	
	/**
	 * 处理微信消息
	 * @param wxMessage
	 * @return
	 * @throws ServiceException
	 */
	String processMessage(String wxMessage) throws ServiceException;
}
