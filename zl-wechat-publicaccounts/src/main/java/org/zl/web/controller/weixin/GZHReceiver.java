/**
 * 
 * Create on 2016年10月31日
 */
package org.zl.web.controller.weixin;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zl.exception.ServiceException;
import org.zl.service.weixin.ReceiveService;
import org.zl.weixin.MPBase;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * 公众号接口的加密解密很乱，注意区分
 * 
 * @author Leo
 * @version 0.0.2
 */
@RestController
@RequestMapping("/gzh")
public class GZHReceiver extends MPBase implements InitializingBean {

	private final Logger logger = LoggerFactory.getLogger(GZHReceiver.class);
	
	WXBizMsgCrypt wxcpt;
	
	@Autowired
	@Qualifier("zlGZHService")
	ReceiveService myService;
	
	/**
	 * 验证回调URL
	 * @param msg_signature 消息体签名
	 * @param timestamp 时间戳
	 * @param nonce 随机数字串
	 * @param echostr 随机加密字符串
	 */
	@RequestMapping(method=RequestMethod.GET,produces ="text/plain;charset=UTF-8")
	public @ResponseBody String verifyURL(String signature,String timestamp,String nonce,String echostr) throws ServiceException{
		wxRecord.info("GZH verifyURL[signature:"+signature+",timestamp="+timestamp+",nonce="+nonce+",echostr="+echostr+"]");
		String sEchoStr = StringUtils.EMPTY;
		if(StringUtils.isEmpty(signature)) {
			return "signature is not empty";
		}
		try {
			sEchoStr  = wxcpt.verifyUrl(signature, timestamp,nonce, "");
			weixinlog.debug("GZH 解密之后的echostr:"+sEchoStr);
			sEchoStr  = echostr;
		} catch (AesException e) {
			logger.error("[token="+token+",encodingAESKey="+encodingAESKey+",appID="+appID+"]");
			logger.error("[signature:"+signature+",timestamp="+timestamp+",nonce="+nonce+",echostr="+echostr+"]");
			logger.error("解密失敗:"+e.getMessage(),e);
			weixinlog.error("解密失敗:"+e.getMessage());
			throw new ServiceException("解密失败");
		}

		return sEchoStr;
	}
	
	/**
	 * 消息接收
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param reqData
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,produces ="text/plain;charset=UTF-8")
	public @ResponseBody String receiveMsg(String signature,String timestamp,String nonce,@RequestBody String reqData ){
		weixinlog.trace("GZH receiveMsg[msg_signature="+signature+",timestamp="+timestamp+",nonce="+nonce+"] data:"+reqData);
		wxRecord.info("GZH receiveMsg[msg_signature="+signature+",timestamp="+timestamp+",nonce="+nonce+"] data:"+reqData);
		String sEncryptMsg = StringUtils.EMPTY;
		boolean isEncrypt = StringUtils.isNotEmpty(signature) && StringUtils.indexOf(reqData, "Encrypt")>-1;
		try {
			String sMsg = reqData;
			if(isEncrypt){
			//对用户回复的消息解密
			sMsg = wxcpt.decryptMsg(signature, timestamp, nonce, sMsg);
			weixinlog.debug("解密之后的msg:"+sMsg);
			}
			sEncryptMsg = myService.processMessage(sMsg);
			weixinlog.debug("服务调用回馈信息:"+sEncryptMsg);
			if(isEncrypt){
				sEncryptMsg = wxcpt.encryptMsg(sEncryptMsg, "", nonce);
			}
		} catch (AesException e) {
			weixinlog.error("解密失敗:"+e.getMessage());
			logger.error("解密失敗:"+e.getMessage(),e);
		} catch (ServiceException e) {
			weixinlog.error("服务调用失敗:"+e.getMessage());
			logger.error("服务调用失敗:"+e.getMessage(),e);
		}
		wxRecord.info("GZH return Message:"+sEncryptMsg);
		return sEncryptMsg;
	}
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.debug("initial GZHReceiver");
		if(wxcpt == null){
			wxcpt = new WXBizMsgCrypt(token, encodingAESKey, appID);
		}
	}
}
