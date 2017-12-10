/**
 * 
 * Create on 2016年11月6日
 */
package org.zl.dao.hibernate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Leo
 * @version 0.0.1
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="Z_USER")
public class ZUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3098863086240417822L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name="Z_USERID",length=36)
	private String id;
	
	@Column(name="Z_NAME",length=50,nullable=false)
	private String name;
	@Column(name="Z_LOGINNAME",length=30)
	private String loginName;
	@Column(name="Z_PWD",length=20)
	private String password;
	@Column(name="Z_EMAIL",length=50)
	private String email;
	@Column(name="Z_DESC", length=512)
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Z_CREATETIME",nullable = true)
	private Date createTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Z_LASTMODIFIED",nullable = true)
	private Date lastModified;

	@Column(name="Z_SEX")
	private int sex;
	@Column(name="Z_IDCARD")
	private String idcard;
	@Column(name="Z_TEL")
	private String tel;
	@Column(name="Z_MOBILE")
	private String mobile;
	@Column(name="Z_ADDRESS")
	private String address;
	@Column(name="Z_GROUP")
	private String group;
	
	@ManyToMany
	@JoinTable(name="Z_PRINCIPAL_PERMISSION", joinColumns={ @JoinColumn(name="Z_USERID")},inverseJoinColumns={ @JoinColumn(name = "Z_ROLEID") })
	private List<UserRole> roles = new ArrayList<>();
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY,cascade=CascadeType.ALL,targetEntity=WXUser.class)
	private List<WXUser> wxUsers = new ArrayList<>();
	
	/*@OneToMany(mappedBy="user",fetch=FetchType.LAZY,cascade=CascadeType.ALL,targetEntity=Car.class)
	private List<Car> cars = new ArrayList<>();*/
	
	@OneToOne(mappedBy="user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Car.class)
	private Car car;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the lastModified
	 */
	public Date getLastModified() {
		return lastModified;
	}

	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * @return the roles
	 */
	public List<UserRole> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	/**
	 * @return the wxUsers
	 */
	public List<WXUser> getWxUsers() {
		return wxUsers;
	}

	/**
	 * @param wxUsers the wxUsers to set
	 */
	public void setWxUsers(List<WXUser> wxUsers) {
		this.wxUsers = wxUsers;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * @param car the car to set
	 */
	public void setCar(Car car) {
		this.car = car;
	}

	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

}
