/**
 * 
 * Create on 2016年11月4日
 */
package org.zl.web.controller.user;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zl.exception.ServiceException;
import org.zl.model.User;
import org.zl.model.WXUser;
import org.zl.service.UserService;
import org.zl.weixin.MPBase;
import org.zl.weixin.token.AccessToken;
import org.zl.weixin.util.TokenUtils;

import com.alibaba.fastjson.JSON;

/**
 * @author Leo
 * @version 0.0.1
 */
@Controller
@RequestMapping("/user")
public class UserAction extends MPBase implements InitializingBean{

	private final Logger logger = LoggerFactory.getLogger(UserAction.class);

	
	@Autowired
	UserService userService;
	
	private HttpHeaders requestHeaders;
	
	/*@RequestMapping("/reguser")
	public String regUser(@CookieValue(name= "mywxid",defaultValue="") String userid,@CookieValue(name= "zlDeviceId",defaultValue="") String deviceId) throws UnsupportedEncodingException{
		String url = "/web/user/edit";
		if(StringUtils.isEmpty(userid)){
			url = StringUtils.replace(qyAuthorizeURI, "CORPID", corpID);
			url = StringUtils.replace(url, "REDIRECT_URI", URLEncoder.encode(regURI,"UTF-8"));
			url = StringUtils.replace(url, "STATE", "reguser");
		}
		logger.debug("注册用户跳转地址:"+url);
		return "redirect:"+url; 
	}*/
	
	/**
	 * 编辑用户
	 * 
	 * @param userid
	 * @param code
	 * @param model
	 * @param locale
	 * @param response
	 * @return
	 */
	@RequestMapping("/edit")
	public String editUser(@CookieValue(name= "mywxid",required=false) String userid,@RequestParam(required=false) String code, ModelMap model,Locale locale,HttpServletResponse response){
		logger.trace("edit user:"+userid+","+code);
		
		String id = userid;
		if(StringUtils.isNotEmpty(code)){
			id=getUserId(code);
			Assert.hasText(id,"获取用户信息失败");
			response.addCookie(new Cookie("mywxid", id));
		}
		User user;
		try {
			user = userService.getWXUser(id);
		} catch (ServiceException e) {
			logger.error("查询用户错误:"+e.getMessage());
			user = null;
		}
		logger.debug("查詢到的["+id+"]用戶:"+user);
		if(user == null){
			user = new User();
			user.setOpenid(id);
			user.setSex("1");
		}
		
		model.addAttribute("code", code);
		model.addAttribute("user", user);
		
		Map<String, String> map = TokenUtils.sign(TokenUtils.getAccessToken(JSAPI_TICKET+id),regURI);
		model.addAttribute("appId", appID); // 必填，公众号的唯一标识
		model.addAttribute("timestamp", map.get("timestamp"));// 必填，生成签名的时间戳
		model.addAttribute("nonceStr", map.get("nonceStr"));// 必填，生成签名的随机串
		model.addAttribute("signature", map.get("signature"));// 必填，签名，见附录1
		return "/wx/user";
	}
	
	/**
	 * 获取用户id(openid)和用户访问凭证(access_token)
	 * @param code code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。 
	 * @return
	 */
	String getUserId(String code){
		
		String result = restTemplate.getForObject(getAccessTokenURL(code), String.class);
		weixinlog.debug("网页授权access_token:"+result);
		AccessToken token = JSON.parseObject(result, AccessToken.class);
		logger.debug("网页授权access_token对象"+token);
		TokenUtils.addAccessToken(JSAPI_TICKET+token.getOpenid(), token.getAccess_token());
		getWXUser(token);
		
		return token.getOpenid();
	}
		
	/**
	 * 通过code换取网页授权access_token
	 * @param code
	 * @return
	 */
	protected String getAccessTokenURL(String code){
		String url = StringUtils.replace(accessTokenURI, "APPID",appID);
		url = StringUtils.replace(url, "SECRET", appSecret);
		url = StringUtils.replace(url, "CODE", code);
		logger.debug("AccessToken url:"+url);
		return url;
	}
	
	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 * 
	 * @param token
	 * @param openId
	 * @return
	 */
	protected String getUserinfoURL(String token,String openId){
		String url = StringUtils.replace(userinfoURI, "ACCESS_TOKEN",token);
		url = StringUtils.replace(url, "OPENID", openId);
		logger.debug("userinfo url:"+url);
		return url;
	}
	
	/**
	 * 获取微信用户信息,并入库保存
	 * 
	 * @param token
	 */
	void getWXUser(AccessToken token) {
		
		//ListenableFuture<ResponseEntity<String>> futureEntity = ansyRestTemplate.getForEntity(getUserinfoURL(token.getAccess_token(),token.getOpenid()), String.class);
		HttpEntity<?> requestEntity = new HttpEntity<String>(null,requestHeaders);
		ListenableFuture<ResponseEntity<String>> futureEntity = asyncRestTemplate.exchange(getUserinfoURL(token.getAccess_token(),token.getOpenid()),HttpMethod.GET,requestEntity, String.class);
			// register a callback
			futureEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
			    @Override
			    public void onSuccess(ResponseEntity<String> entity) {
			    	String body = entity.getBody();
			    	logger.debug("获取用户信息成功:"+entity);
			    	WXUser user = JSON.parseObject(body, WXUser.class);
			    	logger.debug("获取用户信息:"+user);
			    	try {
						userService.saveOrUpdateWXUser(user);
					} catch (ServiceException e) {
						logger.error("保存微信用户数据错误:"+e.getMessage(),e);
					}
			    }
			    @Override
			    public void onFailure(Throwable t) {
			        logger.debug("获取用户信息失败:"+t.getMessage());
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
		
		List<HttpMessageConverter<?>> converterList =asyncRestTemplate.getMessageConverters();
		HttpMessageConverter<?> converterTarget = null;
		for (HttpMessageConverter<?> httpMessageConverter : converterList) {
			if(httpMessageConverter instanceof StringHttpMessageConverter){
				converterTarget = httpMessageConverter;
				break;
			}
		}
		if (converterTarget != null) {
			logger.debug("移除"+converterTarget);
            converterList.remove(converterTarget);
        }

		HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
		converterList.add(converter);
	}
}
