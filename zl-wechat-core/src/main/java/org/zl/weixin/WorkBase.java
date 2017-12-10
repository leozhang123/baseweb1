/**
 * 
 * Create on 2017年7月16日
 */
package org.zl.weixin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.zl.model.WorkApp;
import org.zl.weixin.util.TokenUtils;

/**
 * 企业号基类
 * 
 * @author Leo
 * @version 0.2
 */
public abstract class WorkBase extends WeixinBase {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 企业号服务信息，默认启动时加载一次
	 */
	protected static Map<String,WorkApp> workApps = new ConcurrentHashMap<String, WorkApp>();
	
	/**
	 * 微信企业号id
	 */
	@Value("${weixin.work.CorpID}")
	protected String corpID;
	
	@Value("${weixin.qy.token.geturi}")
	protected String qyGettokenURI;
	
	@Value("${weixin.qy.getuserinfo.url}")
	protected String qyUserinfoURI;
	
	@Value("${weixin.qy.authorize.url}")
	protected String qyAuthorizeURI;
	
	@Value("${weixin.qy.js.getticket.url}")
	protected String qyJsTicketURI;
	
	@Value("${weixin.qy.user.create.uri}")
	protected String qyCreateUserURI;
	
	@Value("${weixin.qy.user.update.uri}")
	protected String qyUpdateUserURI;
	
	@Value("${weixin.qy.user.get.uri}")
	protected String qyGetUserURI;
	
	@Value("${weixin.qy.user.group.id}")
	protected String defaultQYuserGroup;
	
	/**
	 * 页面认证url
	 */
	@Value("${weixin.work.page.authorize.uri}")
	protected String pageAuthorizeURI;
	
	/**
	 * 微信企业号页面入口url
	 */
	@Value("${weixin.work.page.entry.uri}")
	protected String pageEntryURI;
	
	/**
	 * 公众号挂起标识
	 */
	@Value("${weixin.work.suspend}")
	protected boolean workSuspend;
	
	/**
	 * 微信发送消息接口
	 */
	@Value("${weixin.qy.sendmsg.uri}")
	private String sendMsgURI;
	
	/**
	 * 默认应用id,暂用来发送默认消息
	 */
	@Value("${weixin.work.default.appId}")
	protected String defaultAppID;
	/**
	 * 司机应用id
	 */
	@Value("${weixin.work.driver.appId}")
	protected String driverAppID;

	
	/**
	 * 获取用户信息URL
	 * @param secret 应用的凭证密钥
	 * @param code
	 * @return
	 */
	protected String getQYUserinfoURL(String secret,String code){
		String url = StringUtils.replace(qyUserinfoURI, "ACCESS_TOKEN", TokenUtils.getAccessToken(secret));
		url = StringUtils.replace(url, "CODE", code);
		return url;
	}
	
	@Override
	String getSendMessageURL(String secret){
		String url = StringUtils.replace(sendMsgURI, "ACCESS_TOKEN", TokenUtils.getAccessToken(secret));
		weixinlog.debug("微信发送消息接口:"+url);
		return url;
	}

	/**
	 * 获取企业号应用信息
	 * @param agentId 企业号应用id
	 * @return 企业号应用信息
	 */
	public WorkApp getWorkApp(String agentId) {
		return workApps.get(agentId);
	}
}
