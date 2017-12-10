/**
 * 
 * Create on 2016年11月8日
 */
package org.zl.dao.hibernate.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Leo
 * @version 0.0.1
 */
@Entity
@Table(name="Z_CAR")
public class Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6720425893830275813L;
	@Id
	@Column(name="Z_CARID")
	private String carid;
	@Column(name="Z_CARTYPE")
	private String cartype;
	@Column(name="Z_RUNROUTE")
	private String runroute;
	
	//@ManyToOne(cascade=CascadeType.ALL)
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Z_USERID")
	private ZUser user;

	/**
	 * @return the user
	 */
	public ZUser getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(ZUser user) {
		this.user = user;
	}

	/**
	 * @return the carid
	 */
	public String getCarid() {
		return carid;
	}

	/**
	 * @param carid the carid to set
	 */
	public void setCarid(String carid) {
		this.carid = carid;
	}

	/**
	 * @return the cartype
	 */
	public String getCartype() {
		return cartype;
	}

	/**
	 * @param cartype the cartype to set
	 */
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	/**
	 * @return the runroute
	 */
	public String getRunroute() {
		return runroute;
	}

	/**
	 * @param runroute the runroute to set
	 */
	public void setRunroute(String runroute) {
		this.runroute = runroute;
	}
}
