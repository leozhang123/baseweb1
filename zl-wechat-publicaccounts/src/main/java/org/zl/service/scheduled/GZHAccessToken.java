/**
 * 
 * Create on 2016年11月2日
 */
package org.zl.service.scheduled;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zl.weixin.MPBase;
import org.zl.weixin.token.AccessToken;
import org.zl.weixin.util.TokenUtils;

import com.alibaba.fastjson.JSON;

/**
 * 定时获取公众号访问令牌
 * 
 * @author Leo
 * @version 0.0.1
 */
@Component
public class GZHAccessToken extends MPBase {

	
	@Scheduled(fixedDelay=7130000)
	public void getAccessToken(){
		if(isSuspend()) {
			weixinlog.info("获取公众号访问令牌被挂起");
			return;
		}
		weixinlog.trace("定时获取公众号访问令牌");
		String result = restTemplate.getForObject(getURL(), String.class);
		weixinlog.debug("GZH获取AccessToken结果:"+result);
		AccessToken token = JSON.parseObject(result, AccessToken.class);
		weixinlog.debug("GZH获取的token对象"+token);
		if(token.getErrcode()==0 || token.getAccess_token()!=null){
			TokenUtils.addAccessToken(appSecret, token.getAccess_token());
		}else{
			weixinlog.error("GZH获取访问令牌错误["+token.getErrcode()+"]:"+token.getErrmsg());
		}

	}
	
	String getURL(){
		String url = StringUtils.replace(gettokenURI, "APPID", appID);
		url = StringUtils.replace(url, "APPSECRET", appSecret);
		weixinlog.debug("GZH微信AccessToken接口:"+url);
		
		return url;
	}

}
