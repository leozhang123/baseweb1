/**
 * 
 * Create on 2016年11月2日
 */
package org.zl.web.controller.service;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zl.exception.ServiceException;
import org.zl.model.ActionResult;
import org.zl.model.User;
import org.zl.service.QYService;
import org.zl.service.UserService;
import org.zl.service.weixin.SendService;

/**
 * @author Leo
 * @version 0.0.1
 */
@RestController
@RequestMapping("/user")
public class UserServiceController {

	private final Logger logger = LoggerFactory.getLogger(UserServiceController.class);
	
	@Autowired
	private MessageSource messages;
	
	@Autowired
	@Qualifier("zlGZHService")
	SendService sendService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	@Qualifier("zlQYService")
	QYService qyService;
	
	@RequestMapping("/save")
	public ActionResult saveUser(User user,Locale locale){
		logger.trace("保存user数据:"+user);
		ActionResult actionResult = new ActionResult();
		try {
			
			userService.saveOrUpdateUser(user);
			
			//sendService.sendMessage("歡迎  "+user.getRealname()+" 加入",user.getUserid());
			qyService.pushUser(user);
			
			String msg = messages.getMessage("M10001", null, locale);
			actionResult.setReturnMessage(msg);
		} catch (ServiceException e) {
			String msg = messages.getMessage("service.exception", new String[]{e.getMessage()}, locale);
			logger.error(msg,e);
			actionResult.setReturnCode(1);
			actionResult.setReturnMessage(msg);
		}
		return actionResult;
	}
	
	@RequestMapping("/list")
	public Page<User> userlist(@RequestParam(name="pageid",required=false,defaultValue="0") String pageid,Locale locale){
		logger.debug("userlist["+pageid+"]");
		//List<User> list = userService.getUsers(pageid, null);
		Page<User> page = userService.findUsers(pageid, null);//new Page4Datatables<User>(list);
		logger.debug("getusers["+pageid+"]:"+page);
		return page;
	}
}
