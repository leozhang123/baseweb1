/**
 * 
 * Create on 2016年11月1日
 */
package org.zl.weixin.message;

/**
 * @author Leo
 * @version 0.0.1
 */
public class UnsubscribeEvent extends EventMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7085848167799797788L;

	public static final String EVENT_TYPE = "UNSUBSCRIBE";
	
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param agentID
	 * @param event
	 */
	public UnsubscribeEvent(String toUserName, String fromUserName, String createTime, String msgType, String agentID,
			String event) {
		super(toUserName, fromUserName, createTime, msgType, agentID, event);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnsubscribeEvent [getEvent()=");
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

}
