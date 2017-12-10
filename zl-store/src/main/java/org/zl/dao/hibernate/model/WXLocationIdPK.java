/**
 * 
 * Create on 2016年11月14日
 */
package org.zl.dao.hibernate.model;

import java.io.Serializable;

/**
 * @author Leo
 * @version 0.0.1
 */
public class WXLocationIdPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4096774052869738316L;

	/*@Id
	@Column(name="Z_ID",length=36)*/
	private String id;
	
	/*@Id
	@Column(name="OPENID",length=50)*/
	private String openid;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((openid == null) ? 0 : openid.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WXLocationIdPK other = (WXLocationIdPK) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (openid == null) {
			if (other.openid != null)
				return false;
		} else if (!openid.equals(other.openid))
			return false;
		return true;
	}
}
