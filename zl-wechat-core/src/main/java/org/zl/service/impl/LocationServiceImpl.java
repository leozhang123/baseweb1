/**
 * 
 * Create on 2016年11月14日
 */
package org.zl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zl.dao.UserDao;
import org.zl.dao.hibernate.model.WXLocation;
import org.zl.exception.ServiceException;
import org.zl.service.LocationService;
import org.zl.weixin.MPBase;
import org.zl.weixin.message.LocationEvent;

/**
 * @author Leo
 * @version 0.0.1
 */
@Service("zlLocationService")
public class LocationServiceImpl extends MPBase implements LocationService {

	@Autowired
	UserDao userDao;
	
	/* (non-Javadoc)
	 * @see org.zl.service.LocationService#getLastLocation(java.lang.String)
	 */
	@Override
	public LocationEvent getLastLocation(String openId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.zl.service.LocationService#saveOrUpdate(org.zl.weixin.message.LocationEvent)
	 */
	@Override
	public void saveOrUpdate(LocationEvent location) throws ServiceException {
		weixinlog.debug("save location message:"+location);
		WXLocation wxLocation = new WXLocation();
		wxLocation.setId(location.getCreateTime());
		wxLocation.setOpenid(location.getFromUserName());
		wxLocation.setLatitude(location.getLatitude());
		wxLocation.setLongitude(location.getLongitude());
		wxLocation.setPrecision(location.getPrecision());
		
		userDao.saveOrUpdate(wxLocation);
	}

}
