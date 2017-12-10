/**
 * 
 * Create on 2016年10月31日
 */
package org.zl.service.impl;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.zl.exception.ServiceException;
import org.zl.service.LocationService;
import org.zl.service.weixin.ReceiveService;
import org.zl.service.weixin.SendService;
import org.zl.weixin.MPBase;
import org.zl.weixin.message.LocationEvent;
import org.zl.weixin.message.SubscribeEvent;
import org.zl.weixin.util.MessageUtils;
import org.zl.weixin.util.TokenUtils;

/**
 * 公众号服务
 * 
 * @author Leo
 * @version 0.0.1
 */
@Service("zlGZHService")
public class GZHServiceImpl extends MPBase implements ReceiveService ,SendService, InitializingBean {

	@Value("${weixin.qy.sendmsg.uri}")
	private String sendMsgURI;

	private HttpHeaders requestHeaders;
	
	@Autowired
	LocationService locationService;
	
	/* (non-Javadoc)
	 * @see org.zl.web.service.DispatchService#processMessage(java.lang.String)
	 */
	@Override
	public String processMessage(String wxMessage) throws ServiceException {
		weixinlog.debug("GZH process weixin message:"+wxMessage);

		return processWeixinMessage(wxMessage);
	}

	
	public String procSubscribeEvent(SubscribeEvent event) throws ServiceException {
		wxRecord.info("关注:"+event);
		String msg = event.getReplyMessage(MessageUtils.getAutoReply());
		weixinlog.debug("关注响应消息:"+msg);
		return msg;
	}
	
	public String procLocationEvent(LocationEvent event) throws ServiceException {
		wxRecord.info("上报地理位置事件:"+event);
		locationService.saveOrUpdate(event);
		return event.getReplyMessage();
	}

	/* (non-Javadoc)
	 * @see org.zl.web.service.SendService#sendTextMessage(java.lang.String)
	 */
	@Override
	public void sendTextMessage(String wxMessage) throws ServiceException {
		sendTextMessage(wxMessage,"@all");
	}
	
	String getURL(){
		//FIXME secret
		//String url = StringUtils.replace(sendMsgURI, "ACCESS_TOKEN", TokenUtils.getAccessToken(secret));
		String url = StringUtils.replace(sendMsgURI, "ACCESS_TOKEN", TokenUtils.getAccessToken(appSecret));
		weixinlog.debug("微信消息接口:"+url);
		return url;
	}
	
	String sendText(String userid,String party,String tag,String content,int safe){
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
		String content = sendText(receiver,"","",wxMessage,0);
		HttpEntity<?> requestEntity = new HttpEntity<String>(content,requestHeaders);
		
		ListenableFuture<ResponseEntity<String>> futureEntity = asyncRestTemplate.postForEntity(getURL(),requestEntity, String.class);
			// register a callback
			futureEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
			    @Override
			    public void onSuccess(ResponseEntity<String> entity) {
			    	weixinlog.info("GZH发送消息成功:"+entity.getBody());
			    	
			    }
			    @Override
			    public void onFailure(Throwable t) {
			    	weixinlog.warn("GZH发送消息失败:"+t.getMessage());
			    }
		});
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		Resource resource = new ClassPathResource("autoreply.txt");
		if (resource.isReadable()) {
			String msg = IOUtils.toString(resource.getInputStream(), "UTF-8");
			weixinlog.debug("读取的公众号自动回复信息:"+msg);
			MessageUtils.setAutoReply(msg);
		}
	}
}
