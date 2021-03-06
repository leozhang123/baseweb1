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
package org.zl.dao.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 企业号应用数据模型
 * 
 * @author Leo
 * @version 0.0.1
 */
@Entity
@Table(name="WX_WORK_APP")
public class WXWorkApp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -697755842049279565L;

	@Id
	@Column(name="CORPSECRET",length=80)
	private String corpsecret;
	
	@Column(name="AGENTID",length=10)
	private String agentId;
	
	@Column(name="CORPID",length=80)
	private String corpid;
	
	@Column(name="TOKEN",length=80)
	private String token;
	
	@Column(name="ENCODINGAESKEY",length=80)
	private String encodingAESKey;

	@Column(name="AGENTNAME",length=80)
	private String agentName;
	
	@Column(name="IS_ENABLED")
	private Boolean enabled;

	/**
	 * @return the corpsecret
	 */
	public String getCorpsecret() {
		return corpsecret;
	}

	/**
	 * @param corpsecret the corpsecret to set
	 */
	public void setCorpsecret(String corpsecret) {
		this.corpsecret = corpsecret;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the encodingAESKey
	 */
	public String getEncodingAESKey() {
		return encodingAESKey;
	}

	/**
	 * @param encodingAESKey the encodingAESKey to set
	 */
	public void setEncodingAESKey(String encodingAESKey) {
		this.encodingAESKey = encodingAESKey;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WXWorkApp [corpsecret=");
		builder.append(corpsecret);
		builder.append(", agentId=");
		builder.append(agentId);
		builder.append(", corpid=");
		builder.append(corpid);
		builder.append(", token=");
		builder.append(token);
		builder.append(", encodingAESKey=");
		builder.append(encodingAESKey);
		builder.append(", agentName=");
		builder.append(agentName);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the agentName
	 */
	public String getAgentName() {
		return agentName;
	}

	/**
	 * @param agentName the agentName to set
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	/**
	 * @return the agentId
	 */
	public String getAgentId() {
		return agentId;
	}

	/**
	 * @param agentId the agentId to set
	 */
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	/**
	 * @return the corpid
	 */
	public String getCorpid() {
		return corpid;
	}

	/**
	 * @param corpid the corpid to set
	 */
	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
