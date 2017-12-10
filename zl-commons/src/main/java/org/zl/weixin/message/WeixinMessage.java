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
public abstract class WeixinMessage extends WXMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8547343565800564517L;
	/**
	 *   消息id，64位整型  
	 */
	private String msgId;

	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param msgId
	 * @param agentID
	 */
	public WeixinMessage(String toUserName, String fromUserName, String createTime, String msgType, String msgId,
			String agentID) {
		super(toUserName, fromUserName, createTime, msgType, agentID);
		this.msgId = msgId;
	}

	public String getReplyMessage(String msg){
		return "success";
	}

	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
