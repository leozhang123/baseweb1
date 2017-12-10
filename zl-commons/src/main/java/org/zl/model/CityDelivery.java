/**
 * 
 * Create on 2017年7月11日
 */
package org.zl.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 同城货运模型
 * 
 * @author Leo
 * @version 0.0.1
 */
public class CityDelivery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5400499554334713116L;
	
	// 收货人信息
	private String consigneeName;
	private String consigneeTel;
	private String consigneeAddress;
	private String consigneeLatitude;
	private String consigneeLongitude;
	// private String consigneeCoordinate;
	// 发货人信息
	private String consignorName;
	private String consignorTel;
	private String consignorAddress;
	private String consignorLatitude;
	private String consignorLongitude;
	private String consignorCoordinate;
	
	private Long id;
	private Long logisticsCentreId;
	private Long orderId;
	
	private Integer deliveryStatus;
	
	/**
	 * 预约提货时间
	 */
	private String orderDtime;
	/**
	 * 司机
	 */
	private String driver;
	/**
	 * 司机id
	 */
	private String driverID;
	/**
	 * 车id
	 */
	private String carid;
	/**
	 * 司机微信id
	 */
	private String driverWID;
	
	/**
	 * 运费
	 */
	private BigDecimal realPrice;
	/**
	 * 备注
	 */
	private String desc;
	
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
	 * @return the consignorCoordinate
	 */
	public String getConsignorCoordinate() {
		return consignorCoordinate;
	}
	/**
	 * @param consignorCoordinate the consignorCoordinate to set
	 */
	public void setConsignorCoordinate(String consignorCoordinate) {
		this.consignorCoordinate = consignorCoordinate;
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
	 * @return the logisticsCentreId
	 */
	public Long getLogisticsCentreId() {
		return logisticsCentreId;
	}
	/**
	 * @param logisticsCentreId the logisticsCentreId to set
	 */
	public void setLogisticsCentreId(Long logisticsCentreId) {
		this.logisticsCentreId = logisticsCentreId;
	}
	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CityDelivery [consigneeName=");
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
		builder.append(", consignorCoordinate=");
		builder.append(consignorCoordinate);
		builder.append(", id=");
		builder.append(id);
		builder.append(", logisticsCentreId=");
		builder.append(logisticsCentreId);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", deliveryStatus=");
		builder.append(deliveryStatus);
		builder.append(", orderDtime=");
		builder.append(orderDtime);
		builder.append(", driver=");
		builder.append(driver);
		builder.append(", driverID=");
		builder.append(driverID);
		builder.append(", carid=");
		builder.append(carid);
		builder.append(", driverWID=");
		builder.append(driverWID);
		builder.append(", realPrice=");
		builder.append(realPrice);
		builder.append(", desc=");
		builder.append(desc);
		builder.append("]");
		return builder.toString();
	}
	/**
	 * @return the deliveryStatus
	 */
	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}
	/**
	 * @param deliveryStatus the deliveryStatus to set
	 */
	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
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
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}
	/**
	 * @param driver the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}
	/**
	 * @return the realPrice
	 */
	public BigDecimal getRealPrice() {
		return realPrice;
	}
	/**
	 * @param realPrice the realPrice to set
	 */
	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
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
	 * @return the driverID
	 */
	public String getDriverID() {
		return driverID;
	}
	/**
	 * @param driverID the driverID to set
	 */
	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}
	/**
	 * @return the driverWID
	 */
	public String getDriverWID() {
		return driverWID;
	}
	/**
	 * @param driverWID the driverWID to set
	 */
	public void setDriverWID(String driverWID) {
		this.driverWID = driverWID;
	}
	
}
