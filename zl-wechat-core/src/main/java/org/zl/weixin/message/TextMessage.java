/**
 * 
 * Create on 2016年11月1日
 */
package org.zl.weixin.message;

/**
 * 文本消息
 * @author Leo
 * @version 0.0.1
 */
public class TextMessage extends WeixinMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2061598323398705275L;
	
	public static final String TYPE = "TEXT";
	/**
	 * 文本消息内容 
	 */
	private String content;

	
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param msgId
	 * @param agentID
	 * @param content
	 */
	public TextMessage(String toUserName, String fromUserName, String createTime, String msgType, String msgId,
			String agentID,String content) {
		super(toUserName, fromUserName, createTime, msgType, msgId, agentID);
		this.content = content;
	}
	
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TextMessage [content=");
		builder.append(content);
		builder.append(", getToUserName()=");
		builder.append(getToUserName());
		builder.append(", getFromUserName()=");
		builder.append(getFromUserName());
		builder.append(", getCreateTime()=");
		builder.append(getCreateTime());
		builder.append(", getMsgType()=");
		builder.append(getMsgType());
		builder.append(", getMsgId()=");
		builder.append(getMsgId());
		builder.append(", getAgentID()=");
		builder.append(getAgentID());
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see org.zl.weixin.message.WinxinMessage#getReplyMessage(java.lang.String)
	 */
	@Override
	public String getReplyMessage(String msg) {
		StringBuilder builder = new StringBuilder();
		builder.append("<xml>");
		builder.append("   <ToUserName><![CDATA["+getFromUserName()+"]]></ToUserName>");
		builder.append("   <FromUserName><![CDATA["+getToUserName()+"]]></FromUserName> ");
		builder.append("   <CreateTime>"+System.currentTimeMillis()+"</CreateTime>");
		builder.append("   <MsgType><![CDATA["+TYPE+"]]></MsgType>");
		builder.append("   <Content><![CDATA["+msg+"]]></Content>");
		builder.append("</xml>");

		return builder.toString();
	}
	
}
