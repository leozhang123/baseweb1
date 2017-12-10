/**
 * 
 * Create on 2016年11月22日
 */
package org.zl.weixin.message;

/**
 * @author Leo
 * @version 0.0.1
 */
public class VoiceMessage  extends WeixinMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2512678898296260351L;

	public static final String TYPE = "VOICE";
	
	private String mediaId;
	private String format;
	private String recognition;
	
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param msgId
	 * @param agentID
	 */
	public VoiceMessage(String toUserName, String fromUserName, String createTime, String msgType, String msgId,
			String agentID, String mediaId,String format) {
		super(toUserName, fromUserName, createTime, msgType, msgId, agentID);
		this.mediaId=mediaId;
		this.format=format;
	}

	public VoiceMessage(String toUserName, String fromUserName, String createTime, String msgType, String msgId,
			String agentID, String mediaId,String format,String recognition) {
		this(toUserName, fromUserName, createTime, msgType, msgId, agentID,mediaId,format);
		this.recognition=recognition;
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
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the recognition
	 */
	public String getRecognition() {
		return recognition;
	}

	/**
	 * @param recognition the recognition to set
	 */
	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

}
