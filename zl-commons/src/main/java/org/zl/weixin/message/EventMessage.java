/**
 * 
 * Create on 2016年11月1日
 */
package org.zl.weixin.message;

/**
 * @author Leo
 * @version 0.0.1
 */
public class EventMessage extends WXMessage {

	public static final String TYPE = "EVENT";
	/**
	 * 
	 */
	private static final long serialVersionUID = -376493545333950245L;

	private String event;
	
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param agentID
	 */
	public EventMessage(String toUserName, String fromUserName, String createTime, String msgType, String agentID,String event) {
		super(toUserName, fromUserName, createTime, msgType, agentID);
		this.event=event;
	}

	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventMessage [event=");
		builder.append(event);
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
