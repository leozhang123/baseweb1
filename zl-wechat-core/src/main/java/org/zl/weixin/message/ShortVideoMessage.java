/**
 * 
 * Create on 2016年11月22日
 */
package org.zl.weixin.message;

/**
 * @author Leo
 * @version 0.0.1
 */
public class ShortVideoMessage extends VideoMessage {

	public static final String TYPE = "SHORTVIDEO";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6999874038480374684L;

	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param msgId
	 * @param agentID
	 * @param mediaId
	 * @param thumbMediaId
	 */
	public ShortVideoMessage(String toUserName, String fromUserName, String createTime, String msgType, String msgId,
			String agentID, String mediaId, String thumbMediaId) {
		super(toUserName, fromUserName, createTime, msgType, msgId, agentID, mediaId, thumbMediaId);
	}

}
