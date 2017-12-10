/**
 * 
 * Create on 2016年11月22日
 */
package org.zl.weixin.message;

/**
 * @author Leo
 * @version 0.0.1
 */
public class VideoMessage extends WeixinMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4749549841012250580L;

	public static final String TYPE = "VIDEO";
	
	private String mediaId;
	private String thumbMediaId;
	
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param msgId
	 * @param agentID
	 */
	public VideoMessage(String toUserName, String fromUserName, String createTime, String msgType, String msgId,
			String agentID,String mediaId,String thumbMediaId) {
		super(toUserName, fromUserName, createTime, msgType, msgId, agentID);
		this.mediaId=mediaId;
		this.thumbMediaId=thumbMediaId;
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

	/**
	 * @return the thumbMediaId
	 */
	public String getThumbMediaId() {
		return thumbMediaId;
	}

	/**
	 * @param thumbMediaId the thumbMediaId to set
	 */
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

}
