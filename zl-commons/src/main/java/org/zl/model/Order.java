/**
 * 
 * Create on 2017年6月2日
 */
package org.zl.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Leo
 * @version 0.0.1
 */
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8779366160855926136L;

	//收货人信息
	private String consigneeName;
	private String consigneeTel;
	private String consigneeAddress;
	private String consigneeLatitude;
	private String consigneeLongitude;
	//private String consigneeCoordinate;
	//发货人信息
	private String consignorName;
	private String consignorTel;
	private String consignorAddress;
	private String consignorLatitude;
	private String consignorLongitude;
	//private String consignorCoordinate;
	
	//订单信息
	private String orderWeight;
	private String orderMeasurement;
	private String orderPcs;
	private String orderDescription;
	private String orderDtime;
	private Integer orderStatus;
	private BigDecimal orderRealPrice;
	private BigDecimal orderOffer;
	private Long id;
	private String orderOpinion;
	
	private String owner;
	
	/**
	 * @return the consigneeName
	 */
	public String getConsigneeName() {
		return consigneeName;
	}
	/**
	 * @param consigneeName the consigneeName to set
	 */
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	/**
	 * @return the consigneeTel
	 */
	public String getConsigneeTel() {
		return consigneeTel;
	}
	/**
	 * @param consigneeTel the consigneeTel to set
	 */
	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}
	/**
	 * @return the consigneeAddress
	 */
	public String getConsigneeAddress() {
		return consigneeAddress;
	}
	/**
	 * @param consigneeAddress the consigneeAddress to set
	 */
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	/**
	 * @return the consigneeLatitude
	 */
	public String getConsigneeLatitude() {
		return consigneeLatitude;
	}
	/**
	 * @param consigneeLatitude the consigneeLatitude to set
	 */
	public void setConsigneeLatitude(String consigneeLatitude) {
		this.consigneeLatitude = consigneeLatitude;
	}
	/**
	 * @return the consigneeLongitude
	 */
	public String getConsigneeLongitude() {
		return consigneeLongitude;
	}
	/**
	 * @param consigneeLongitude the consigneeLongitude to set
	 */
	public void setConsigneeLongitude(String consigneeLongitude) {
		this.consigneeLongitude = consigneeLongitude;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [consigneeName=");
		builder.append(consigneeName);
		builder.append(", consigneeTel=");
		builder.append(consigneeTel);
		builder.append(", consigneeAddress=");
		builder.append(consigneeAddress);
		builder.append(", consigneeLatitude=");
		builder.append(consigneeLatitude);
		builder.append(", consigneeLongitude=");
		builder.append(consigneeLongitude);
		builder.append(", consignorName=");
		builder.append(consignorName);
		builder.append(", consignorTel=");
		builder.append(consignorTel);
		builder.append(", consignorAddress=");
		builder.append(consignorAddress);
		builder.append(", consignorLatitude=");
		builder.append(consignorLatitude);
		builder.append(", consignorLongitude=");
		builder.append(consignorLongitude);
		builder.append(", orderWeight=");
		builder.append(orderWeight);
		builder.append(", orderMeasurement=");
		builder.append(orderMeasurement);
		builder.append(", orderPcs=");
		builder.append(orderPcs);
		builder.append(", orderDescription=");
		builder.append(orderDescription);
		builder.append(", orderDtime=");
		builder.append(orderDtime);
		builder.append(", orderStatus=");
		builder.append(orderStatus);
		builder.append(", orderRealPrice=");
		builder.append(orderRealPrice);
		builder.append(", orderOffer=");
		builder.append(orderOffer);
		builder.append(", id=");
		builder.append(id);
		builder.append(", orderOpinion=");
		builder.append(orderOpinion);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the consignorName
	 */
	public String getConsignorName() {
		return consignorName;
	}
	/**
	 * @param consignorName the consignorName to set
	 */
	public void setConsignorName(String consignorName) {
		this.consignorName = consignorName;
	}
	/**
	 * @return the consignorTel
	 */
	public String getConsignorTel() {
		return consignorTel;
	}
	/**
	 * @param consignorTel the consignorTel to set
	 */
	public void setConsignorTel(String consignorTel) {
		this.consignorTel = consignorTel;
	}
	/**
	 * @return the consignorAddress
	 */
	public String getConsignorAddress() {
		return consignorAddress;
	}
	/**
	 * @param consignorAddress the consignorAddress to set
	 */
	public void setConsignorAddress(String consignorAddress) {
		this.consignorAddress = consignorAddress;
	}
	/**
	 * @return the consignorLatitude
	 */
	public String getConsignorLatitude() {
		return consignorLatitude;
	}
	/**
	 * @param consignorLatitude the consignorLatitude to set
	 */
	public void setConsignorLatitude(String consignorLatitude) {
		this.consignorLatitude = consignorLatitude;
	}
	/**
	 * @return the consignorLongitude
	 */
	public String getConsignorLongitude() {
		return consignorLongitude;
	}
	/**
	 * @param consignorLongitude the consignorLongitude to set
	 */
	public void setConsignorLongitude(String consignorLongitude) {
		this.consignorLongitude = consignorLongitude;
	}

	/**
	 * @return the orderWeight
	 */
	public String getOrderWeight() {
		return orderWeight;
	}
	/**
	 * @param orderWeight the orderWeight to set
	 */
	public void setOrderWeight(String orderWeight) {
		this.orderWeight = orderWeight;
	}
	/**
	 * @return the orderMeasurement
	 */
	public String getOrderMeasurement() {
		return orderMeasurement;
	}
	/**
	 * @param orderMeasurement the orderMeasurement to set
	 */
	public void setOrderMeasurement(String orderMeasurement) {
		this.orderMeasurement = orderMeasurement;
	}
	/**
	 * @return the orderPcs
	 */
	public String getOrderPcs() {
		return orderPcs;
	}
	/**
	 * @param orderPcs the orderPcs to set
	 */
	public void setOrderPcs(String orderPcs) {
		this.orderPcs = orderPcs;
	}
	/**
	 * @return the orderDescription
	 */
	public String getOrderDescription() {
		return orderDescription;
	}
	/**
	 * @param orderDescription the orderDescription to set
	 */
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	/**
	 * @return the orderDtime
	 */
	public String getOrderDtime() {
		return orderDtime;
	}
	/**
	 * @param orderDtime the orderDtime to set
	 */
	public void setOrderDtime(String orderDtime) {
		this.orderDtime = orderDtime;
	}
	/**
	 * @return the orderStatus
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return the orderRealPrice
	 */
	public BigDecimal getOrderRealPrice() {
		return orderRealPrice;
	}
	/**
	 * @param orderRealPrice the orderRealPrice to set
	 */
	public void setOrderRealPrice(BigDecimal orderRealPrice) {
		this.orderRealPrice = orderRealPrice;
	}
	/**
	 * @return the orderOffer
	 */
	public BigDecimal getOrderOffer() {
		return orderOffer;
	}
	/**
	 * @param orderOffer the orderOffer to set
	 */
	public void setOrderOffer(BigDecimal orderOffer) {
		this.orderOffer = orderOffer;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the orderOpinion
	 */
	public String getOrderOpinion() {
		return orderOpinion;
	}
	/**
	 * @param orderOpinion the orderOpinion to set
	 */
	public void setOrderOpinion(String orderOpinion) {
		this.orderOpinion = orderOpinion;
	}
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
