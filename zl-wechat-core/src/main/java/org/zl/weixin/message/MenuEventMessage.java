/**
 * 
 * Create on 2016年11月1日
 */
package org.zl.weixin.message;

/**
 * @author Leo
 * @version 0.0.1
 */
public class MenuEventMessage extends EventMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -376493545333950245L;

	private String eventKey;

	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param agentID
	 * @param event
	 */
	public MenuEventMessage(String toUserName, String fromUserName, String createTime, String msgType, String agentID,
			String event,String eventKey) {
		super(toUserName, fromUserName, createTime, msgType, agentID, event);
		this.eventKey=eventKey;
	}

	/**
	 * @return the eventKey
	 */
	public String getEventKey() {
		return eventKey;
	}

	/**
	 * @param eventKey the eventKey to set
	 */
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

}
