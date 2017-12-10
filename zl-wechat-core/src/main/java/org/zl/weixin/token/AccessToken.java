/**
 * 
 * Create on 2016年11月2日
 */
package org.zl.weixin.token;


/**
 * @author Leo
 * @version 0.0.1
 */
public class AccessToken extends Token{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6356536704318324614L;
	private String refresh_token;
	private String openid;
	private String access_token;
	/**
	 * @return the refresh_token
	 */
	public String getRefresh_token() {
		return refresh_token;
	}
	/**
	 * @param refresh_token the refresh_token to set
	 */
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	/**
	 * @return the openid
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * @param openid the openid to set
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}
	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccessToken [refresh_token=");
		builder.append(refresh_token);
		builder.append(", openid=");
		builder.append(openid);
		builder.append(", access_token=");
		builder.append(access_token);
		builder.append(", getExpires_in()=");
		builder.append(getExpires_in());
		builder.append(", getErrcode()=");
		builder.append(getErrcode());
		builder.append(", getErrmsg()=");
		builder.append(getErrmsg());
		builder.append("]");
		return builder.toString();
	}
	
}
