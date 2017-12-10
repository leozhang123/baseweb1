/**
 * 
 * Create on 2016年10月31日
 */
package org.zl.service.impl;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.zl.dao.hibernate.model.WXWorkApp;
import org.zl.exception.ServiceException;
import org.zl.jpa.WorkAppRepository;
import org.zl.model.CityDelivery;
import org.zl.model.Order;
import org.zl.model.User;
import org.zl.model.WorkApp;
import org.zl.service.DeliveryStatus;
import org.zl.service.QYService;
import org.zl.service.weixin.ReceiveService;
import org.zl.weixin.WorkBase;
import org.zl.weixin.user.UserInfo;
import org.zl.weixin.util.TokenUtils;

import com.alibaba.fastjson.JSON;

/**
 * @author Leo
 * @version 0.0.2
 */
@Service("zlQYService")
public class QYServiceImpl extends WorkBase implements ReceiveService , InitializingBean,QYService {

	private static Logger logger = LoggerFactory.getLogger(QYServiceImpl.class);

	
	@Autowired
	WorkAppRepository workAppRepository;
	
	/* (non-Javadoc)
	 * @see org.zl.web.service.DispatchService#processMessage(java.lang.String)
	 */
	@Override
	public String processMessage(String wxMessage) throws ServiceException {
		weixinlog.debug("QY process weixin message:"+wxMessage);
		
		return processWeixinMessage(wxMessage);
	}

	/* (non-Javadoc)
	 * @see org.zl.web.service.SendService#sendMessage(java.lang.String)
	 */
	@Override
	public void sendTextMessage(String wxMessage) throws ServiceException {
		sendTextMessage(wxMessage,"@all");
	}
	
	
	String sendText(String userid,String party,String tag,String content,int safe,String appID){
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("\"touser\": \""+userid+"\",");
		builder.append("\"toparty\": \""+party+"\",");
		builder.append("\"totag\": \""+tag+"\",");
		builder.append("\"msgtype\":\"text\",");
		builder.append("\"agentid\":"+appID+",");
		builder.append("\"text\": {");
		builder.append("\"content\":\""+content+"\"");
		builder.append("},");
		builder.append("\"safe\":"+safe+"");
		builder.append("}");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see org.zl.service.weixin.SendService#sendTextMessage(java.lang.String, java.lang.String)
	 */
	@Override
	public void sendTextMessage(String wxMessage, String receiver) throws ServiceException {
		String content = sendText(receiver,"","",wxMessage,0,defaultAppID);
		sendMessage(content,getWorkApp(defaultAppID).getCorpsecret());
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

	/* (non-Javadoc)
	 * @see org.zl.service.QYService#pushUser(org.zl.model.User)
	 */
	@Override
	@Async
	public void pushUser(User user) throws ServiceException {
		weixinlog.trace("推送用户至企业号:"+user);
		if(StringUtils.isEmpty(user.getIdcard())){
			if(weixinlog.isDebugEnabled()){
				weixinlog.warn("身份证号空，推送终止");
			}
			return;
		}
		//使用默认应用操作
		String secret = getWorkApp(defaultAppID).getCorpsecret();
		
		String url = StringUtils.replace(qyGetUserURI, "ACCESS_TOKEN", TokenUtils.getAccessToken(secret));
		url = StringUtils.replace(url, "USERID",user.getIdcard());
		weixinlog.trace("查询企业号是否有推送用户:"+url);
		String resp = restTemplate.getForObject(url, String.class);
		weixinlog.debug("查询企业号是否有推送用户结果:"+resp);
		UserInfo guser = JSON.parseObject(resp, UserInfo.class);
		weixinlog.debug("查询企业号是否有推送用户结果对象:"+guser);
		if(0==guser.getErrcode()){
			url = StringUtils.replace(qyUpdateUserURI, "ACCESS_TOKEN", TokenUtils.getAccessToken(secret));
		}else{
			url = StringUtils.replace(qyCreateUserURI, "ACCESS_TOKEN", TokenUtils.getAccessToken(secret));
		}
		
		weixinlog.debug("推送企业用户url:"+url);
		String qyuser = toQYUserMsg(user,guser.getErrcode());
		weixinlog.debug("推送企业用户:"+qyuser);
		send(qyuser,secret,url,"推送企业用户");

	}
	
	/**
	 * 企业用户信息文本
	 * @param user 用户信息
	 * @param type 0表示更新，其它新增
	 * @return
	 */
	String toQYUserMsg(User user,int type){
		StringBuilder str = new StringBuilder();
		str.append("{");
		str.append("\"userid\":\""+user.getIdcard()+"\",");
		if(type!=0){
			str.append("\"name\": \""+user.getRealname()+"\",");
			str.append("\"department\": ["+defaultQYuserGroup+"],");
		}
		//str.append("\"position\": \"\",");
		str.append("\"mobile\": \""+user.getMobile()+"\",");
		str.append("\"gender\": \""+user.getSex()+"\",");
		str.append("\"email\": \""+user.getEmail()+"\",");
		//str.append("\"weixinid\": \"\",");
		//str.append("\"enable\": 1");
		str.append("\"extattr\":{\"attrs\":[");
		
		str.append("{\"name\": \"常跑线路\",\"value\": \""+user.getRunroute()+"\"},");
		str.append("{\"name\": \"主力车型\",\"value\": \""+user.getCartype()+"\"},");
		str.append("{\"name\": \"身份证号\",\"value\": \""+user.getIdcard()+"\"},");
		str.append("{\"name\": \"车牌号\",\"value\": \""+user.getCarid()+"\"}");
		
		str.append("]}");
		str.append("}");

		return str.toString();
	}

	@Override
	public void pushOrder(Order order) throws ServiceException {
		weixinlog.trace("推送订单消息至企业号:"+order);
	}

	@Override
	public void sendMessage2Driver(CityDelivery delivery) throws ServiceException {
		if(delivery == null) {
			logger.debug("运单消息为空，终止向司机发消息");
			return;
		}
		//判断状态，构造消息内容
		logger.debug("向司机发送运单消息,类别:{}",delivery.getDeliveryStatus());
		String content = StringUtils.EMPTY;
		switch(delivery.getDeliveryStatus()){
		case DeliveryStatus.PICKUP:
			String desc = delivery.getConsignorAddress()+"运往"+delivery.getConsigneeAddress()+"货件";
			content = createMessaget4textcard(delivery.getDriverWID(),"","","取货确认",desc,getWorkPageURI("citydelivery",delivery.getId().toString(),driverAppID),driverAppID);
			break;
		case DeliveryStatus.ASSIGNED:
			String desc1 = delivery.getConsignorAddress()+"运往"+delivery.getConsigneeAddress()+"货件,请确认是否接收?";
			content = createMessaget4textcard(delivery.getDriverWID(),"","","待确认货运单",desc1,getWorkPageURI("citydelivery",delivery.getId().toString(),driverAppID),driverAppID);
			break;
		}
		
		//发消息给司机
		sendMessage(content,getWorkApp(driverAppID).getCorpsecret());
	}
	
	String getWorkPageURI(String opr,String id,String appID) {
		String url = StringUtils.replaceAll(pageEntryURI, "MYOPR", opr);
		url = StringUtils.replaceAll(url, "MYAPPID", id);
		try {
			url = URLEncoder.encode(url,StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			logger.warn("url编码错误:"+e.getMessage(),e);
		}
		return getWorkPageAuthorizeURI(url,appID);
	}
	
	String getWorkPageAuthorizeURI(String aurl,String appID) {
		String url = StringUtils.replaceAll(pageAuthorizeURI, "CORPID", corpID);
		url = StringUtils.replaceAll(url, "REDIRECT_URI", aurl);
		url = StringUtils.replaceAll(url, "AGENTID",appID );
		url = StringUtils.replaceAll(url, "STATE", ""+LocalTime.now().getNano());
		return url;
	}
	/**
	 * 创建textcard消息体
	 * @param userid 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送
	 * @param party 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
	 * @param tag 标签ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
	 * @param title 标题，不超过128个字节，超过会自动截断
	 * @param description 描述，不超过512个字节，超过会自动截断
	 * @param url 点击后跳转的链接。
	 * @param appID 应用id
	 * @return
	 */
	String createMessaget4textcard(String userid,String party,String tag,String title,String description,String url,String appID){
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("\"touser\": \""+userid+"\",");
		builder.append("\"toparty\": \""+party+"\",");
		builder.append("\"totag\": \""+tag+"\",");
		builder.append("\"msgtype\":\"textcard\",");
		builder.append("\"agentid\":"+appID+",");
		builder.append("\"textcard\": {");
		builder.append("	\"title\":\""+title+"\",");
		builder.append("	\"description\":\""+description+"\",");
		builder.append("	\"url\":\""+url+"\"");
		builder.append("}");
		builder.append("}");
		return builder.toString();
	}
	
	@Override
	public List<WorkApp> listWorkApp() throws ServiceException {
		List<WXWorkApp> list = workAppRepository.findActivateAll();
		return toWorkApps(list);
	}
	
	List<WorkApp> toWorkApps(List<WXWorkApp> wxWorkApps){
		List<WorkApp> result = new ArrayList<>();
		for (WXWorkApp workApp : wxWorkApps) {
			result.add(toWorkApp(workApp));
		}
		return result;
	}
	
	WorkApp toWorkApp(WXWorkApp wxWorkApp) {
		WorkApp app = new WorkApp();
		app.setAgentId(wxWorkApp.getAgentId());
		app.setAgentName(wxWorkApp.getAgentName());
		app.setCorpid(wxWorkApp.getCorpid());
		app.setCorpsecret(wxWorkApp.getCorpsecret());
		app.setEncodingAESKey(wxWorkApp.getEncodingAESKey());
		app.setToken(wxWorkApp.getToken());
		app.setEnabled(wxWorkApp.getEnabled());
		return app;
	}
}
