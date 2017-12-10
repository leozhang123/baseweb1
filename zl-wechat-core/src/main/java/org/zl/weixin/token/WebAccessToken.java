/**
 * 
 * Create on 2016年11月2日
 */
package org.zl.weixin.token;


/**
 * @author Leo
 * @version 0.0.1
 */
public class WebAccessToken extends Token{

	/**
	 * 
	 */
	private static final long serialVersionUID = -832012095907495337L;
	
	private String access_token;
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
		builder.append("AccessToken [access_token=");
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
