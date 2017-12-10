/**
 * 
 * Create on 2016年11月14日
 */
package org.zl.dao.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author Leo
 * @version 0.0.1
 */
@Entity
@Table(name="WX_LOCATION")
@IdClass(org.zl.dao.hibernate.model.WXLocationIdPK.class)
public class WXLocation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1347852917005707168L;

	@Id
	@Column(name="Z_ID",length=36)
	private String id;
	
	@Id
	@Column(name="WX_ID",length=64)
	private String openid;
	
	@Column(name="WX_LATITUDE")
	private double latitude;
	
	@Column(name="WX_LONGITUDE")
	private double longitude;
	
	@Column(name="WX_PRECISION")
	private double precision;

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

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the precision
	 */
	public double getPrecision() {
		return precision;
	}

	/**
	 * @param precision the precision to set
	 */
	public void setPrecision(double precision) {
		this.precision = precision;
	}
	
}
