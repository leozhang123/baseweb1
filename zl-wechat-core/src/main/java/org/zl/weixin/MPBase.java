/**
 * 
 * Create on 2017年7月16日
 */
package org.zl.weixin;

import org.springframework.beans.factory.annotation.Value;

/**
 * 服务号基类
 * 
 * @author Leo
 * @version 0.0.1
 */
public class MPBase extends WeixinBase {

	@Value("${weixin.appID}")
	protected String appID;
	
	@Value("${weixin.appSecret}")
	protected String appSecret;
	
	@Value("${weixin.token.geturi}")
	protected String gettokenURI;
	
	@Value("${weixin.token}")
	protected String token;
	
	@Value("${weixin.encodingAESKey}")
	protected String encodingAESKey;
	
	@Value("${weixin.customizemenu.createuri}")
	protected String createURI;
	
	@Value("${user.register.url}")
	protected String regURI;
	
	@Value("${weixin.accesstoken.geturi}")
	protected String accessTokenURI;
	
	@Value("${weixin.userinfo.geturi}")
	protected String userinfoURI;
	
	@Override
	String getSendMessageURL(String secret) {
		return appSecret;
	}

}
