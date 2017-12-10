/**
 * 
 * Create on 2016年11月5日
 */
package org.zl.weixin.user;

import org.zl.weixin.ResultBase;

/**
 * @author Leo
 * @version 0.0.1
 */
public class UserInfo extends ResultBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -526941100996683448L;
	private String userId;
	private String deviceId;
	private String openId;
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}
	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserInfo [userId=");
		builder.append(userId);
		builder.append(", deviceId=");
		builder.append(deviceId);
		builder.append(", openId=");
		builder.append(openId);
		builder.append("]");
		return builder.toString();
	}
}
