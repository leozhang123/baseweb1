/**
 * 
 * Create on 2016年11月15日
 */
package org.zl.web.controller.user;

import java.io.IOException;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Leo
 * @version 0.0.1
 */
@Controller
@RequestMapping("/customizemenu")
public class CustomizeMenuAction {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/edit")
	public String editMenu(ModelMap model,Locale locale) throws IOException{
		Resource resource = new ClassPathResource("gzhmenu.json");
		if (resource.isReadable()) {
			String msg = IOUtils.toString(resource.getInputStream(), "UTF-8");
			logger.debug("读取的自动回复信息:"+msg);
			model.addAttribute("menucentect",msg );
		}
		
		return "menu/menu";
	}
}
