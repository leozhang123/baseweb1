/**
 * 
 * Create on 2016年11月1日
 */
package org.zl.weixin.message;

/**
 * @author Leo
 * @version 0.0.1
 */
public class SubscribeEvent extends EventMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4329308525827695214L;

	public static final String EVENT_TYPE = "SUBSCRIBE";
	
	/**
	 * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
	 */
	private String eventKey;
	/**
	 * 二维码的ticket，可用来换取二维码图片
	 */
	private String ticket;
	
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param agentID
	 * @param event
	 */
	public SubscribeEvent(String toUserName, String fromUserName, String createTime, String msgType, String agentID,
			String event,String eventKey,String ticket) {
		super(toUserName, fromUserName, createTime, msgType, agentID, event);
		this.eventKey=eventKey;
		this.ticket=ticket;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubscribeEvent [eventKey=");
		builder.append(eventKey);
		builder.append(", ticket=");
		builder.append(ticket);
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
	

	public String getReplyMessage(String msg) {
		if(msg==null || msg.length()==0){
			return "";
		}
		StringBuilder builder = new StringBuilder();
		builder.append("<xml>");
		builder.append("   <ToUserName><![CDATA["+getFromUserName()+"]]></ToUserName>");
		builder.append("   <FromUserName><![CDATA["+getToUserName()+"]]></FromUserName> ");
		builder.append("   <CreateTime>"+System.currentTimeMillis()+"</CreateTime>");
		builder.append("   <MsgType><![CDATA[text]]></MsgType>");
		builder.append("   <Content><![CDATA["+msg+"]]></Content>");
		builder.append("</xml>");
		return builder.toString();
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
