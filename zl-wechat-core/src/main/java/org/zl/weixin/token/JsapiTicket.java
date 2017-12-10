/**
 * 
 * Create on 2016年11月2日
 */
package org.zl.weixin.token;


/**
 * @author Leo
 * @version 0.0.1
 */
public class JsapiTicket extends Token {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3350971517315408680L;
	
	private String ticket;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JsapiTicket [ticket=");
		builder.append(ticket);
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
