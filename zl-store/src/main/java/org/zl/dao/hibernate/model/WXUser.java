/**
 * 
 * Create on 2016年11月6日
 */
package org.zl.dao.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 微信用户基本信息
 * 
 * @author Leo
 * @version 0.0.1
 */
@Entity
@Table(name="WX_USER")
public class WXUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4183649621808541925L;
	
	@Id
	@Column(name="OPENID",length=50)
	private String openid;// 用户的标识，对当前公众号唯一 
	
	@Column(name="SUBSCRIBE",length=1)
	private int subscribe;// 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	
	@Column(name="NICKNAME",length=80)
	private String nickname;// 用户的昵称
	
	@Column(name="SEX",length=1)
	private int sex;// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	
	@Column(name="CITY",length=50)
	private String city;// 用户所在城市
	
	@Column(name="COUNTRY",length=50)
	private String country;// 用户所在国家
	
	@Column(name="PROVINCE",length=50)
	private String province;// 用户所在省份
	
	@Column(name="LANGUAGE",length=20)
	private String language;// 用户的语言，简体中文为zh_CN 
	
	@Column(name="HEADIMGURL",length=250)
	private String headimgurl;// 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 
	
	@Column(name="SUBSCRIBE_TIME")
	private int subscribe_time;// 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 
	
	@Column(name="UNIONID",length=50)
	private String unionid;// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。 
	
	@Column(name="REMARK",length=500)
	private String remark;// 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
	
	@Column(name="GROUPID")
	private int groupid;// 用户所在的分组ID（兼容旧的用户分组接口） 
	
	@Column(name="TAGID_LIST",length=200)
	private String tagid_list;// 用户被打上的标签ID列表
	
	@ManyToOne()
	@JoinColumn(name="Z_USERID")
	private ZUser user;

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
	 * @return the subscribe
	 */
	public int getSubscribe() {
		return subscribe;
	}

	/**
	 * @param subscribe the subscribe to set
	 */
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the headimgurl
	 */
	public String getHeadimgurl() {
		return headimgurl;
	}

	/**
	 * @param headimgurl the headimgurl to set
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	/**
	 * @return the subscribe_time
	 */
	public int getSubscribe_time() {
		return subscribe_time;
	}

	/**
	 * @param subscribe_time the subscribe_time to set
	 */
	public void setSubscribe_time(int subscribe_time) {
		this.subscribe_time = subscribe_time;
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

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the groupid
	 */
	public int getGroupid() {
		return groupid;
	}

	/**
	 * @param groupid the groupid to set
	 */
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	/**
	 * @return the tagid_list
	 */
	public String getTagid_list() {
		return tagid_list;
	}

	/**
	 * @param tagid_list the tagid_list to set
	 */
	public void setTagid_list(String tagid_list) {
		this.tagid_list = tagid_list;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WXUser [openid=");
		builder.append(openid);
		builder.append(", subscribe=");
		builder.append(subscribe);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", city=");
		builder.append(city);
		builder.append(", country=");
		builder.append(country);
		builder.append(", province=");
		builder.append(province);
		builder.append(", language=");
		builder.append(language);
		builder.append(", subscribe_time=");
		builder.append(subscribe_time);
		builder.append(", unionid=");
		builder.append(unionid);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", groupid=");
		builder.append(groupid);
		builder.append(", tagid_list=");
		builder.append(tagid_list);
		builder.append(", headimgurl=");
		builder.append(headimgurl);
		builder.append("]");
		return builder.toString();
	}
	
	
}
