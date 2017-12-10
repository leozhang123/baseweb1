/**
 * 
 * Create on 2017年6月1日
 */
package org.zl.dao.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Leo
 * @version 0.0.1
 */
@Entity
@Table(name="WX_APP_SKEY")
public class WXAPPSessionKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2185841665433684795L;

	
	@Column(name="MY_ID")
	private String myId;
	
	@Id
	@Column(name="OPENID")
	private String openid;
	
	@Column(name="SESSION_KEY")
	private String sessionKey;
	
	@Column(name="UNIONID")
	private String unionid;

	/**
	 * 
	 */
	public WXAPPSessionKey() {
	}

	/**
	 * @param myId
	 * @param openid
	 * @param sessionKey
	 */
	public WXAPPSessionKey(String myId, String openid, String sessionKey) {
		this.myId = myId;
		this.openid = openid;
		this.sessionKey = sessionKey;
	}
	

	/**
	 * @param myId
	 * @param openid
	 * @param sessionKey
	 * @param unionid
	 */
	public WXAPPSessionKey(String myId, String openid, String sessionKey, String unionid) {
		super();
		this.myId = myId;
		this.openid = openid;
		this.sessionKey = sessionKey;
		this.unionid = unionid;
	}
	
	/**
	 * @return the myId
	 */
	public String getMyId() {
		return myId;
	}

	/**
	 * @param myId the myId to set
	 */
	public void setMyId(String myId) {
		this.myId = myId;
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
	 * @return the sessionKey
	 */
	public String getSessionKey() {
		return sessionKey;
	}

	/**
	 * @param sessionKey the sessionKey to set
	 */
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WXAPPSessionKey [myId=");
		builder.append(myId);
		builder.append(", openid=");
		builder.append(openid);
		builder.append(", sessionKey=");
		builder.append(sessionKey);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the unionid
	 */
	public String getUnionid() {
		return unionid;
	}

	/**
	 * @param unionid the unionid to set
	 */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	
}
