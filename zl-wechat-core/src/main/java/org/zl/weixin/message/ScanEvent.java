/**
 * 
 * Create on 2016年11月24日
 */
package org.zl.weixin.message;

/**
 * @author Leo
 * @version 0.0.1
 */
public class ScanEvent extends EventMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2388207950498813771L;

	public static final String EVENT_TYPE = "SCAN";
	
	private String eventKey;
	private String ticket;
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param agentID
	 * @param event
	 */
	public ScanEvent(String toUserName, String fromUserName, String createTime, String msgType, String agentID,
			String event, String eventKey, String ticket) {
		super(toUserName, fromUserName, createTime, msgType, agentID, event);
		this.eventKey=eventKey;
		this.ticket=ticket;
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
	/**
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}
	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
