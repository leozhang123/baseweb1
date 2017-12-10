/**
 * 
 * Create on 2016年11月6日
 */
package org.zl.service;

import org.zl.exception.ServiceException;
import org.zl.weixin.message.LocationEvent;

/**
 * 位置服务
 * 
 * @author Leo
 * @version 0.0.1
 */
public interface LocationService {

	
	LocationEvent getLastLocation(String openId) throws ServiceException;
	
	void saveOrUpdate(LocationEvent location) throws ServiceException;
	
}
