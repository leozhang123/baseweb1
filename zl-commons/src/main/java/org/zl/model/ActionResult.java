/**
 * 
 * Create on 2016年11月3日
 */
package org.zl.model;

import java.io.Serializable;

/**
 * REST服务结果反馈
 * @author Leo
 * @version 0.0.1
 */
public class ActionResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4273898488261256068L;

	private int returnCode = 0;
	private String returnMessage = "success";
	/**
	 * @return the returnCode
	 */
	public int getReturnCode() {
		return returnCode;
	}
	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	/**
	 * @return the returnMessage
	 */
	public String getReturnMessage() {
		return returnMessage;
	}
	/**
	 * @param returnMessage the returnMessage to set
	 */
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	
}
