/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zl.service.weixin;

import org.zl.exception.ServiceException;

/**
 * 微信素材服务
 * 功能:上传,下载
 * @author Leo
 * @version 0.0.1
 */
public interface MediaService {
	
	/**
	 * 公众号类别
	 */
	static final String MP_CATEGORY = "mp";
	
	/**
	 * 企业号类别
	 */
	static final String WORK_CATEGORY = "work";
	
	/**
	 * 下载媒体文件
	 * @param category 类别
	 * @param url 网络地址
	 * @throws ServiceException
	 */
	void download(String category,String url)throws ServiceException;
}
