/**
 * 
 * Create on 2016年10月31日
 */
package org.zl.weixin;

import java.io.Serializable;

/**
 * @author Leo
 * @version 0.0.1
 */
public class ResultBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9064378505228960202L;
	
	private int errcode;
	private String errmsg;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResultBase [errcode=");
		builder.append(errcode);
		builder.append(", errmsg=");
		builder.append(errmsg);
		builder.append("]");
		return builder.toString();
	}
	
}
