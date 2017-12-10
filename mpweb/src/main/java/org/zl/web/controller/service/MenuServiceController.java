/**
 * 
 * Create on 2016年11月15日
 */
package org.zl.web.controller.service;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zl.model.ActionResult;
import org.zl.weixin.MPBase;
import org.zl.weixin.ResultBase;
import org.zl.weixin.util.MessageUtils;
import org.zl.weixin.util.TokenUtils;

/**
 * 公众号菜单
 * 
 * @author Leo
 * @version 0.0.1
 */
@RestController
@RequestMapping("/customizemenu")
public class MenuServiceController extends MPBase {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	public void init(){
		logger.debug("初始化MenuServiceController");
	}
	
	@RequestMapping(path="/save",produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ActionResult saveMenu(String menucentect,Locale locale){
		logger.debug("自定义菜单保存:"+menucentect);
		ActionResult actionResult = new ActionResult();
		
		try {
			Resource resource = new ClassPathResource("gzhmenu.json");
			File file = resource.getFile();
			FileUtils.writeStringToFile(file, menucentect, "UTF-8");
			
			//JSONObject centect = JSON.parseObject(menucentect);
			
			/*HttpEntity<?> requestEntity = new HttpEntity<String>(menucentect,requestHeaders);
			String result = restTemplate.postForObject(getURL(),requestEntity, String.class);
			ResultBase token = JSON.parseObject(result, ResultBase.class);*/
			ResultBase token = callWeixin(getURL(), ResultBase.class, "微信创建自定义菜单");
			logger.debug("微信创建自定义菜单响应对象"+token);
			
			if(token.getErrcode()==0){
				logger.debug("更新当前公众号自动回复信息");
				MessageUtils.setAutoReply(menucentect);
			}
			actionResult.setReturnCode(token.getErrcode());
			actionResult.setReturnMessage(token.getErrmsg());
		} catch (IOException e) {
			String msg = messages.getMessage("service.exception", new String[]{e.getMessage()}, locale);
			logger.error(msg,e);
			actionResult.setReturnCode(1);
			actionResult.setReturnMessage(msg);
		}
		
		return actionResult;
	}
	
	String getURL(){
		String url = StringUtils.replace(createURI, "ACCESS_TOKEN", TokenUtils.getAccessToken(appSecret));
		logger.debug("微信创建自定义菜单接口:"+url);
		
		return url;
	}
}
