/**
 * 
 * Create on 2016年10月31日
 */
package org.zl.weixin;

import java.io.IOException;
import java.util.Locale;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;
import org.zl.exception.ServiceException;
import org.zl.weixin.message.ClickEvent;
import org.zl.weixin.message.EventMessage;
import org.zl.weixin.message.ImageMessage;
import org.zl.weixin.message.LinkMessage;
import org.zl.weixin.message.LocationEvent;
import org.zl.weixin.message.LocationMessage;
import org.zl.weixin.message.ScanEvent;
import org.zl.weixin.message.ShortVideoMessage;
import org.zl.weixin.message.SubscribeEvent;
import org.zl.weixin.message.TextMessage;
import org.zl.weixin.message.UnsubscribeEvent;
import org.zl.weixin.message.VideoMessage;
import org.zl.weixin.message.ViewEvent;
import org.zl.weixin.message.VoiceMessage;
import org.zl.weixin.message.WXMessage;
import org.zl.weixin.message.WeixinMessage;
import org.zl.weixin.util.MessageUtils;

import com.alibaba.fastjson.JSON;

/**
 * 微信基础类
 * 
 * @author Leo
 * @version 0.0.2
 */
public abstract class WeixinBase {

	public static final String JSAPI_TICKET = "jsapi_ticket";
	
	protected static final Logger weixinlog = LoggerFactory.getLogger("weixin");
	protected static final Logger wxRecord = LoggerFactory.getLogger("weixinrecord");
	
	
	@Autowired
	protected MessageSource messages;
	
	private HttpHeaders requestHeaders;
	
	
	/**
	 * 挂起标志
	 */
	private static boolean suspend = false;
	
	/**
	 * 同步调用器
	 */
	@Autowired
	protected RestTemplate restTemplate;// = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	
	/**
	 * 异步调用器
	 */
	@Autowired
	protected AsyncRestTemplate asyncRestTemplate;// = new AsyncRestTemplate(new HttpComponentsAsyncClientHttpRequestFactory());
	
	public WeixinBase() {
		requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
	}
	
	
	protected String getMessage(String key){
		return getMessage(key,null);
	}
	
	protected String getMessage(String key,Object[] args){
		return messages.getMessage(key,  args, Locale.getDefault());
	}

	/**
	 * 处理接收的微信消息
	 * 目前,微信公众号和企业号消息格式差别不大，使用同一方法解析消息
	 * @param wxMessage 接收到的消息文本
	 * @return 反馈消息
	 * @throws ServiceException
	 */
	protected String processWeixinMessage(String wxMessage) throws ServiceException{
		String replyMsg = StringUtils.EMPTY;
		try {
			WXMessage wxMsg = MessageUtils.getWinxinMessage(wxMessage);
			weixinlog.debug("处理消息对象类型:"+(wxMsg==null?null:wxMsg.getClass().getName()));
			weixinlog.debug("处理消息对象:"+wxMsg);
			
			if(wxMsg instanceof EventMessage){
				replyMsg = processWeixinEvent( wxMsg);
			}else if(wxMsg instanceof WeixinMessage){
				replyMsg = processWeixinMessage( wxMsg);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			weixinlog.error("解析失败:"+e.getMessage(),e);
			throw new ServiceException("解析失败-->"+wxMessage);
		}
		return replyMsg;
	}
	
	/**
	 * 处理事件型消息
	 * @param wxMsg
	 * @return
	 * @throws ServiceException
	 */
	private String processWeixinEvent(WXMessage wxMsg) throws ServiceException{
		weixinlog.debug("处理事件型消息对象:"+wxMsg);
		String replyMsg = StringUtils.EMPTY;
		if(wxMsg instanceof SubscribeEvent){
			replyMsg = procSubscribeEvent((SubscribeEvent) wxMsg);
		}else if(wxMsg instanceof UnsubscribeEvent){
			replyMsg = procUnsubscribeEvent((UnsubscribeEvent) wxMsg);
		}else if(wxMsg instanceof LocationEvent){
			replyMsg = procLocationEvent((LocationEvent) wxMsg);
		}else if(wxMsg instanceof ViewEvent){
			replyMsg = procViewEvent((ViewEvent) wxMsg);
		}else if(wxMsg instanceof ClickEvent){
			replyMsg = procClick((ClickEvent) wxMsg);
		}else if(wxMsg instanceof ScanEvent){
			replyMsg = procScanEvent((ScanEvent) wxMsg);
		}else{
			weixinlog.error("不能处理的事件型消息:"+wxMsg);
			replyMsg = MessageUtils.getNoReplyMsg();
		}
		return replyMsg;
	}
	
	/**
	 * 处理消息型消息
	 * @param wxMsg
	 * @return
	 * @throws ServiceException
	 */
	private String processWeixinMessage(WXMessage wxMsg) throws ServiceException{
		weixinlog.debug("处理消息型消息对象:"+wxMsg);
		String replyMsg = StringUtils.EMPTY;
		if(wxMsg instanceof TextMessage){
			replyMsg = procText((TextMessage) wxMsg);
		}else if(wxMsg instanceof ImageMessage){
			replyMsg = procImage((ImageMessage) wxMsg);
		}else if(wxMsg instanceof VoiceMessage){
			replyMsg = procVoiceMessage((VoiceMessage) wxMsg);
		}else if(wxMsg instanceof LocationMessage){
			replyMsg = procLocationMessage((LocationMessage) wxMsg);
		}else if(wxMsg instanceof VideoMessage){
			replyMsg = procVideoMessage((VideoMessage) wxMsg);
		}else if(wxMsg instanceof ShortVideoMessage){
			replyMsg = procShortVideoMessage((ShortVideoMessage) wxMsg);
		}else if(wxMsg instanceof LinkMessage){
			replyMsg = procLinkMessage((LinkMessage) wxMsg);
		}else{
			weixinlog.error("不能处理的消息型信息:"+wxMsg);
			replyMsg = MessageUtils.getNoReplyMsg();
		}
		return replyMsg;
	}
	/**
	 * 处理文本消息
	 * @param wxMsg
	 * @return
	 * @throws ServiceException
	 */
	public String procText(TextMessage wxMsg) throws ServiceException {
		wxRecord.info("文本消息:"+wxMsg);
		return wxMsg.getReplyMessage();
	}
	/**
	 * 处理图片消息
	 * @param wxMsg
	 * @return
	 * @throws ServiceException
	 */
	public String procImage(ImageMessage wxMsg) throws ServiceException {
		wxRecord.info("图片消息:"+wxMsg);
		return wxMsg.getReplyMessage();
	}

	/**
	 * 处理点击事件
	 * @param event
	 * @return
	 * @throws ServiceException
	 */
	public String procClick(ClickEvent event) throws ServiceException {
		wxRecord.info("点击事件:"+event);
		return event.getReplyMessage();
	}
	
	/**
	 * 处理关注事件
	 * @param event
	 * @return
	 * @throws ServiceException
	 */
	public String procSubscribeEvent(SubscribeEvent event) throws ServiceException {
		wxRecord.info("关注事件:"+event);
		return event.getReplyMessage();
	}
	/**
	 * 处理取消关注事件
	 * @param event
	 * @return
	 * @throws ServiceException
	 */
	public String procUnsubscribeEvent(UnsubscribeEvent event) throws ServiceException {
		wxRecord.info("取消关注事件:"+event);
		return event.getReplyMessage();
	}
	/**
	 * 处理查看事件
	 * @param event
	 * @return
	 * @throws ServiceException
	 */
	public String procViewEvent(ViewEvent event) throws ServiceException {
		wxRecord.info("查看事件:"+event);
		return event.getReplyMessage();
	}
	/**
	 * 处理上报地理位置事件
	 * @param event
	 * @return
	 * @throws ServiceException
	 */
	public String procLocationEvent(LocationEvent event) throws ServiceException {
		wxRecord.info("上报地理位置事件:"+event);
		return event.getReplyMessage();
	}
	
	/**
	 * 处理 用户已关注时的事件推送
	 * @param event
	 * @return
	 * @throws ServiceException
	 */
	public String procScanEvent(ScanEvent event) throws ServiceException {
		wxRecord.info("用户已关注时的事件推送:"+event);
		return event.getReplyMessage();
	}
	
	/**
	 * 处理语音消息
	 * @param wxMsg
	 * @return
	 * @throws ServiceException
	 */
	public String procVoiceMessage(VoiceMessage wxMsg) throws ServiceException {
		wxRecord.info("语音消息:"+wxMsg);
		return wxMsg.getReplyMessage();
	}
	/**
	 * 处理地理位置消息
	 * @param wxMsg
	 * @return
	 * @throws ServiceException
	 */
	public String procLocationMessage(LocationMessage wxMsg) throws ServiceException {
		wxRecord.info("地理位置消息:"+wxMsg);
		return wxMsg.getReplyMessage();
	}
	/**
	 * 处理视频消息
	 * @param wxMsg
	 * @return
	 * @throws ServiceException
	 */
	public String procVideoMessage(VideoMessage wxMsg) throws ServiceException {
		wxRecord.info("视频消息:"+wxMsg);
		return wxMsg.getReplyMessage();
	}
	/**
	 * 处理小视频消息
	 * @param wxMsg
	 * @return
	 * @throws ServiceException
	 */
	public String procShortVideoMessage(ShortVideoMessage wxMsg) throws ServiceException {
		wxRecord.info("小视频消息:"+wxMsg);
		return wxMsg.getReplyMessage();
	}
	/**
	 * 处理链接消息
	 * @param wxMsg
	 * @return
	 * @throws ServiceException
	 */
	public String procLinkMessage(LinkMessage wxMsg) throws ServiceException {
		wxRecord.info("链接消息:"+wxMsg);
		return wxMsg.getReplyMessage();
	}

	/**
	 * 是否挂起
	 * @return the suspend
	 */
	public final boolean isSuspend() {
		return suspend;
	}

	/**
	 * 设置挂起标志
	 * @param suspend the suspend to set
	 */
	public final void setSuspend(boolean suspend) {
		WeixinBase.suspend = suspend;
	}
	
	/**
	 * 微信接口调用
	 * 异步方式
	 * @param wxMessage 构造好的微信消息
	 * @param secret 应用的凭证密钥
	 * @param url 微信接口URL
	 * @throws ServiceException
	 */
	public void send(final String wxMessage,final String secret,final String url) throws ServiceException {
		send(wxMessage, secret, url, StringUtils.EMPTY);
	}
	
	/**
	 * 微信接口调用
	 * 异步方式
	 * @param wxMessage 构造好的微信消息
	 * @param secret 应用的凭证密钥
	 * @param url 微信接口URL
	 * @param title 操作标题,目前用于日志标识
	 * @throws ServiceException
	 */
	public void send(final String wxMessage,final String secret,final String url,String title) throws ServiceException {
		weixinlog.trace(getMessage("I170816001",new String[] {title,secret,url,wxMessage}));
		wxRecord.info(getMessage("I170816002",new String[] {title,wxMessage}));
		if(StringUtils.isEmpty(wxMessage)) {
			return;
		}
		HttpEntity<?> requestEntity = new HttpEntity<String>(wxMessage,requestHeaders);
		
		ListenableFuture<ResponseEntity<String>> futureEntity = asyncRestTemplate.postForEntity(url,requestEntity, String.class);
			// register a callback
			futureEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
			    @Override
			    public void onSuccess(ResponseEntity<String> entity) {
			    	weixinlog.trace(getMessage("I170816003",new String[] {title,entity.getBody()}));
			    }
			    @Override
			    public void onFailure(Throwable t) {
			    	weixinlog.warn(getMessage("W170816001",new String[] {title,t.getMessage(),wxMessage}),t);
			    }
		});
	}
	
	/**
	 * 发送微信消息
	 * 
	 * @param wxMessage 构造好的微信消息
	 * @param secret 应用的凭证密钥
	 * @throws ServiceException
	 */
	public void sendMessage(final String wxMessage,final String secret) throws ServiceException {
		send(wxMessage, secret, getSendMessageURL(secret),"发送微信信息");
	}
	/**
	 * 获取发送消息URL
	 * @param secret 应用的凭证密钥
	 * @return 可用的URL
	 */
	abstract String getSendMessageURL(String secret);
	
	/**
	 * 调用微信接口
	 * 
	 * @param url 接口url
	 * @param clazz 结果对象类型
	 * @param title 操作标题
	 * @return 结果对象
	 */
	protected <T>T callWeixin(String url,Class<T> clazz,String title){
		weixinlog.trace(getMessage("I170907001",new String[] {title,url,clazz.toString()}));
		HttpEntity<?> requestEntity = new HttpEntity<String>(requestHeaders);
		ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class,requestEntity);
		String body = entity.getBody();
		weixinlog.debug(getMessage("I170907002",new String[] {title,url,body}));
		T key = JSON.parseObject(body,clazz);
		return key;
	}
}
