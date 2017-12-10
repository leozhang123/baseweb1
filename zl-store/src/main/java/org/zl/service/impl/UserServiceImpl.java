/**
 * 
 * Create on 2016年11月8日
 */
package org.zl.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zl.dao.UserDao;
import org.zl.dao.hibernate.model.Car;
import org.zl.dao.hibernate.model.WXUser;
import org.zl.dao.hibernate.model.ZUser;
import org.zl.exception.ServiceException;
import org.zl.exception.UserAlreadyExistsException;
import org.zl.exception.UserNotFoundException;
import org.zl.jpa.UserRepository;
import org.zl.model.User;
import org.zl.service.Page4Datatables;
import org.zl.service.UserService;

/**
 * @author Leo
 * @version 0.0.1
 */
@Service("zlUserService")
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserRepository userRepository;

	/* (non-Javadoc)
	 * @see org.zl.service.UserService#getWXUser(java.lang.String)
	 */
	@Override
	public User getWXUser(String id) throws ServiceException {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		WXUser user = userDao.getWXUser(id);
		return toWXUser(user);
	}

	User toWXUser(WXUser user){
		if(user==null)
			return null;
		User u = new User();
		u.setOpenid(user.getOpenid());
		
		if(user.getUser()!=null){
			ZUser zuser = user.getUser();
			u.setUserid(zuser.getId());
			u.setRealname(zuser.getName());
			u.setSex(""+zuser.getSex());
			u.setIdcard(zuser.getIdcard());
			u.setTel(zuser.getTel());
			u.setMobile(zuser.getMobile());
			u.setGroup(zuser.getGroup());
			u.setEmail(zuser.getEmail());
			if(zuser.getCar()!=null){
				Car car = zuser.getCar();
				u.setCarid(car.getCarid());
				u.setCartype(car.getCartype());
				u.setRunroute(car.getRunroute());
			}
		}
		return u;
	}
	/* (non-Javadoc)
	 * @see org.zl.service.UserService#saveUser(org.zl.model.User)
	 */
	@Override
	public void saveUser(User user) throws ServiceException {
		if(StringUtils.isEmpty(user.getIdcard())) {
			throw new ServiceException("用户身份证号不能为空");
		}
		
		ZUser zuser = new ZUser();
		zuser.setId(user.getUserid());
		zuser.setSex(NumberUtils.toInt(user.getSex()));
		zuser.setName(user.getRealname());
		zuser.setIdcard(user.getIdcard());
		zuser.setTel(user.getTel());
		zuser.setMobile(user.getMobile());
		zuser.setGroup(user.getGroup());
		zuser.setEmail(user.getEmail());
		zuser.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
		zuser.setLastModified(Timestamp.valueOf(LocalDateTime.now()));
		
		
		WXUser wxuser = userDao.getUser(WXUser.class,user.getOpenid());
		if(wxuser == null){
			wxuser =new WXUser();
		}
		wxuser.setOpenid(user.getOpenid());
		wxuser.setUnionid(user.getUnionid());
		wxuser.setUser(zuser);
		
		userDao.saveUser(zuser);
		userDao.saveOrUpdate(wxuser);
		
		if(StringUtils.isEmpty(user.getCarid())) {
			logger.info("新增的用户[{}]没有car信息",user);
			return;
		}
		
		Car car = userDao.getUser(Car.class, user.getCarid());
		if(car!=null){
			throw new ServiceException("车辆已经被注册");
		}else{
			car = new Car();
		}
		car.setCarid(user.getCarid());
		car.setCartype(user.getCartype());
		car.setRunroute(user.getRunroute());
		car.setUser(zuser);
		userDao.saveUser(car);
	}

	/* (non-Javadoc)
	 * @see org.zl.service.UserService#saveOrUpdateUser(org.zl.model.User)
	 */
	@Override
	public void saveOrUpdateUser(User user) throws ServiceException {
		if(StringUtils.isEmpty(user.getUserid())){
			saveUser(user);
		}else{
			updateUser(user);
		}
	}

	/* (non-Javadoc)
	 * @see org.zl.service.UserService#updateUser(org.zl.model.User)
	 */
	@Override
	public void updateUser(User user) throws ServiceException {
		
		
		ZUser zuser = userDao.getUser(ZUser.class, user.getUserid());
		zuser.setSex(NumberUtils.toInt(user.getSex()));
		zuser.setIdcard(user.getIdcard());
		zuser.setTel(user.getTel());
		zuser.setMobile(user.getMobile());
		zuser.setGroup(user.getGroup());
		zuser.setEmail(user.getEmail());
		zuser.setLastModified(Timestamp.valueOf(LocalDateTime.now()));
		
		WXUser wxuser = userDao.getUser(WXUser.class,user.getOpenid());
		if(wxuser == null){
			wxuser =new WXUser();
		}
		wxuser.setOpenid(user.getOpenid());
		wxuser.setUser(zuser);
		
		
		Car car = zuser.getCar();
		if(car == null){
			car = new Car();
		}
		if(StringUtils.isNotEmpty(car.getCarid()) && !StringUtils.equals(car.getCarid(), user.getCarid())){
			Car c = userDao.getUser(Car.class, user.getCarid());
			if(c!=null){
				throw new ServiceException("车辆已经被注册");
			}
			
			zuser.setCar(null);
			car.setUser(null);
			userDao.deleteUser(car);
			car = new Car();
		}
		car.setCarid(user.getCarid());
		car.setCartype(user.getCartype());
		car.setRunroute(user.getRunroute());
		car.setUser(zuser);
		
		
		userDao.updateUser(zuser);
		userDao.saveOrUpdate(wxuser);
		userDao.saveOrUpdate(car);
	}

	/* (non-Javadoc)
	 * @see org.zl.service.UserService#saveOrUpdateWXUser(org.zl.model.WXUser)
	 */
	@Override
	public void saveOrUpdateWXUser(org.zl.model.WXUser user) throws ServiceException {
		WXUser wx = userDao.getWXUser(user.getOpenid());
		logger.debug("查询到的微信用户:"+wx);
		if(wx ==null){
			wx = new WXUser();
		}
		wx.setOpenid(user.getOpenid());
		wx.setNickname(user.getNickname());
		wx.setCity(user.getCity());
		wx.setCountry(user.getCountry());
		wx.setGroupid(user.getGroupid());
		wx.setHeadimgurl(user.getHeadimgurl());
		wx.setLanguage(user.getLanguage());
		wx.setProvince(user.getProvince());
		wx.setRemark(user.getRemark());
		wx.setSex(user.getSex());
		wx.setSubscribe(user.getSubscribe());
		wx.setSubscribe_time(user.getSubscribe_time());
		wx.setTagid_list(user.getTagid_list()==null?"":StringUtils.join(user.getTagid_list(), ","));
		wx.setUnionid(user.getUnionid());
		
		userDao.saveOrUpdate(wx);
	}

	/* (non-Javadoc)
	 * @see org.zl.service.UserService#getUsers(java.lang.String, java.lang.String)
	 */
	@Override
	public List<User> getUsers(String pageId, String pageSize) {
		int id = NumberUtils.toInt(pageId,0);
		int size = NumberUtils.toInt(pageSize,10);
		List<User> list = toUsers(userDao.findAll(ZUser.class,id, size));
		return list;
	}

	List<User> toUsers(List<ZUser> users){
		List<User> list = new ArrayList<>();
		for (ZUser user : users) {
			if(user.getWxUsers()!=null && user.getWxUsers().size()>0){
				list.add(toWXUser(user.getWxUsers().get(0)));
			}
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see org.zl.service.UserService#findUsers(java.lang.String, java.lang.String)
	 */
	@Override
	public Page<User> findUsers(String pageId, String pageSize) {
		int id = NumberUtils.toInt(pageId,0);
		int size = NumberUtils.toInt(pageSize,10);
		Pageable page = new PageRequest(id,size);
		Page<ZUser> users = userRepository.findAll(page);
		List<User> list = toUsers(users.getContent());
		Page<User> pages = new Page4Datatables<User>(list,page,0);
		return pages;
	}

	@Override
	public Page<User> findDriver(Pageable pageable, User params) throws ServiceException {
		logger.trace("查询司机:"+params);
		ZUser car = new ZUser();
		Page<ZUser> page = userDao.findDriver(pageable, car);
		Page<User> pages = new Page4Datatables<User>(toUsers(page.getContent()),null,page.getTotalElements());
		return pages;
	}

	@Override
	public User getUser(String userid) throws ServiceException {
		logger.trace("查询用户:"+userid);
		if(StringUtils.isEmpty(userid)){
			return null;
		}
		ZUser zuser = userDao.getUser(ZUser.class,userid);
		return toUser(zuser);
	}

	User toUser(ZUser zuser){
		if(zuser==null)
			return null;
		User u = new User();
		u.setUserid(zuser.getId());
		u.setRealname(zuser.getName());
		u.setSex("" + zuser.getSex());
		u.setIdcard(zuser.getIdcard());
		u.setTel(zuser.getTel());
		u.setMobile(zuser.getMobile());
		u.setGroup(zuser.getGroup());
		u.setEmail(zuser.getEmail());
		if (zuser.getCar() != null) {
			Car car = zuser.getCar();
			u.setCarid(car.getCarid());
			u.setCartype(car.getCartype());
			u.setRunroute(car.getRunroute());
		}
		if(zuser.getWxUsers()!=null && !zuser.getWxUsers().isEmpty()) {
			WXUser wuser = zuser.getWxUsers().get(0);
			u.setOpenid(wuser.getOpenid());
		}
		return u;
	}

	@Override
	public User getUserForMiniapp(String sessionId) throws ServiceException {
		WXUser zuser = userDao.getUserByMiniapp(sessionId);
		return toWXUser(zuser);
	}

	@Override
	public void saveUserForMiniapp(User user) throws ServiceException, UserAlreadyExistsException {
		//通过openid查询微信用户
		WXUser wx = userDao.getWXUser(user.getOpenid());
		logger.debug("查询到的微信用户:{}",wx);
		if(wx !=null){
			throw new ServiceException(user.getOpenid()+"用户存在");
		}
		ZUser zuser = userRepository.findUserByIDcard(user.getIdcard());
		if(zuser!=null) {
			throw new UserAlreadyExistsException(user.getIdcard()+"证件已被注册");
		}
		//不存在微信用户新增
		saveUser(user);
	}

	@Override
	public void bindUserForMiniapp(User user) throws ServiceException, UserAlreadyExistsException {
		ZUser zuser = userRepository.findUserByIDcard(user.getIdcard());
		if(zuser==null) {
			throw new UserNotFoundException(user.getIdcard()+"用户不存在");
		}
		if(!StringUtils.equals(zuser.getMobile(), user.getMobile())) {
			//绑定规则,用户身份证号和手机号与之前注册信息一致
			throw new ServiceException("电话号不匹配");
		}
		WXUser wxuser = userDao.getUser(WXUser.class,user.getOpenid());
		if(wxuser != null){
			throw new UserAlreadyExistsException(user.getOpenid()+"用户存在,不能进行绑定");
		}
		wxuser =new WXUser();
		wxuser.setOpenid(user.getOpenid());
		wxuser.setUnionid(user.getUnionid());
		wxuser.setUser(zuser);
		userDao.saveOrUpdate(wxuser);
	}
}
