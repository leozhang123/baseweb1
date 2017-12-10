/**
 * 
 * Create on 2016年11月1日
 */
package org.zl.weixin.message;

/**
 * @author Leo
 * @version 0.0.1
 */
public class ViewEvent extends MenuEventMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1269853801082574981L;

	public static final String EVENT_TYPE = "VIEW";
	
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param agentID
	 * @param event
	 * @param eventKey
	 */
	public ViewEvent(String toUserName, String fromUserName, String createTime, String msgType, String agentID,
			String event, String eventKey) {
		super(toUserName, fromUserName, createTime, msgType, agentID, event, eventKey);
	}


}
