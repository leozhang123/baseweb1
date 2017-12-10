/**
 * 
 * Create on 2016年10月31日
 */
package org.zl.weixin.token;

import java.io.Serializable;

/**
 * @author Leo
 * @version 0.0.1
 */
public class Token implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1082786344632067633L;

	private int expires_in;
	
	private int errcode;
	
	private String errmsg;

	/**
	 * @return the expires_in
	 */
	public int getExpires_in() {
		return expires_in;
	}

	/**
	 * @param expires_in the expires_in to set
	 */
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	/**
	 * @return the errcode
	 */
	public int getErrcode() {
		return errcode;
	}

	/**
	 * @param errcode the errcode to set
	 */
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	/**
	 * @return the errmsg
	 */
	public String getErrmsg() {
		return errmsg;
	}

	/**
	 * @param errmsg the errmsg to set
	 */
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
