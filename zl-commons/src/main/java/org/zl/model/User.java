/**
 * 
 * Create on 2016年11月2日
 */
package org.zl.model;

import java.io.Serializable;

/**
 * @author Leo
 * @version 0.0.1
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6165409383379492661L;
	
	private String realname;

	private String userid;
	
	private String openid;
	
	private String idcard;
	
	private String tel;
	
	private String mobile;
	
	private String group;
	
	private String carid;
	
	private String cartype;
	
	private String runroute;
	
	private String email;
	
	private String sex;
	
	private String unionid;
	
	/**
	 * @return the realname
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * @param realname the realname to set
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [realname=");
		builder.append(realname);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", openid=");
		builder.append(openid);
		builder.append(", idcard=");
		builder.append(idcard);
		builder.append(", tel=");
		builder.append(tel);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", group=");
		builder.append(group);
		builder.append(", carid=");
		builder.append(carid);
		builder.append(", cartype=");
		builder.append(cartype);
		builder.append(", runroute=");
		builder.append(runroute);
		builder.append(", email=");
		builder.append(email);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", unionid=");
		builder.append(unionid);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the idcard
	 */
	public String getIdcard() {
		return idcard;
	}

	/**
	 * @param idcard the idcard to set
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
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
