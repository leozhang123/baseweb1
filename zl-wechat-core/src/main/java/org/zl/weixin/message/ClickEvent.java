/**
 * 
 * Create on 2016年11月1日
 */
package org.zl.weixin.message;

/**
 * @author Leo
 * @version 0.0.1
 */
public class ClickEvent extends MenuEventMessage {

	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param agentID
	 * @param event
	 * @param eventKey
	 */
	public ClickEvent(String toUserName, String fromUserName, String createTime, String msgType, String agentID,
			String event, String eventKey) {
		super(toUserName, fromUserName, createTime, msgType, agentID, event, eventKey);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1494446144361856663L;

	public static final String EVENT_TYPE = "CLICK";


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClickEvent [eventKey=");
		builder.append(getEventKey());
		builder.append(", getEventKey()=");
		builder.append(getEventKey());
		builder.append(", getEvent()=");
		builder.append(getEvent());
		builder.append(", getToUserName()=");
		builder.append(getToUserName());
		builder.append(", getFromUserName()=");
		builder.append(getFromUserName());
		builder.append(", getCreateTime()=");
		builder.append(getCreateTime());
		builder.append(", getMsgType()=");
		builder.append(getMsgType());
		builder.append(", getAgentID()=");
		builder.append(getAgentID());
		builder.append("]");
		return builder.toString();
	}
	/* (non-Javadoc)
	 * @see org.zl.weixin.message.EventMessage#getReplyMessage()
	 */
	@Override
	public String getReplyMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append("<xml>");
		builder.append("   <ToUserName><![CDATA["+getFromUserName()+"]]></ToUserName>");
		builder.append("   <FromUserName><![CDATA["+getToUserName()+"]]></FromUserName> ");
		builder.append("   <CreateTime>"+System.currentTimeMillis()+"</CreateTime>");
		builder.append("   <MsgType><![CDATA[text]]></MsgType>");
		builder.append("   <Content><![CDATA[]]></Content>");
		builder.append("</xml>");

		return builder.toString();
	}

}
