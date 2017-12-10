/**
 * 
 * Create on 2016年11月20日
 */
package org.zl.weixin.message;

/**
 * @author Leo
 * @version 0.0.1
 */
public class ImageMessage extends WeixinMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8319769677713350385L;

	public static final String TYPE = "IMAGE";

	private String picUrl;
	
	private String mediaId;
	
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param msgId
	 * @param agentID
	 */
	public ImageMessage(String toUserName, String fromUserName, String createTime, String msgType, String msgId,
			String agentID,String picUrl,String mediaId) {
		super(toUserName, fromUserName, createTime, msgType, msgId, agentID);
		this.picUrl = picUrl;
		this.mediaId = mediaId;
	}

	/* (non-Javadoc)
	 * @see org.zl.service.weixin.WinxinMessage#getReplyMessage(java.lang.String)
	 */
	@Override
	public String getReplyMessage(String msg) {
		return "success";
	}

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * @return the mediaId
	 */
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImageMessage [picUrl=");
		builder.append(picUrl);
		builder.append(", mediaId=");
		builder.append(mediaId);
		builder.append(", getMsgId()=");
		builder.append(getMsgId());
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
