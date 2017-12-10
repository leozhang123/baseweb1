/**
 * 
 * Create on 2016年11月1日
 */
package org.zl.weixin.message;

import java.io.Serializable;

/**
 * @author Leo
 * @version 0.0.1
 */
public abstract class WXMessage implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4428298146650046979L;
	/**
	 *   企业号CorpID /开发者微信号
	 */
	private String toUserName;
	/**
	 *   成员UserID  /发送方帐号（一个OpenID）
	 */
	private String fromUserName;
	/**
	 *   消息创建时间（整型）  
	 */
	private String createTime;
	/**
	 *   消息类型
	 */
	private String msgType;

	/**
	 *   企业应用的id，整型。可在应用的设置页面查看 
	 */
	private String agentID;
	
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param msgId
	 * @param agentID
	 */
	public WXMessage(String toUserName, String fromUserName, String createTime, String msgType, String agentID) {
		super();
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.agentID = agentID;
	}
	/**
	 * @return the toUserName
	 */
	public String getToUserName() {
		return toUserName;
	}
	/**
	 * @param toUserName the toUserName to set
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	/**
	 * @return the fromUserName
	 */
	public String getFromUserName() {
		return fromUserName;
	}
	/**
	 * @param fromUserName the fromUserName to set
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the msgType
	 */
	public String getMsgType() {
		return msgType;
	}
	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	/**
	 * @return the agentID
	 */
	public String getAgentID() {
		return agentID;
	}
	/**
	 * @param agentID the agentID to set
	 */
	public void setAgentID(String agentID) {
		this.agentID = agentID;
	} 

	/**
	 * 默认反馈信息
	 * @return
	 */
	public String getReplyMessage(){
		return "success";
	}
}
