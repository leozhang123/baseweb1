/**
 * 
 * Create on 2016年11月14日
 */
package org.zl.weixin.message;

/**
 * @author Leo
 * @version 0.0.1
 */
public class LocationEvent extends EventMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1440420860878225661L;

	public static final String EVENT_TYPE = "LOCATION";
	
	private double latitude;
	private double longitude;
	private double precision;
	
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param agentID
	 * @param event
	 */
	public LocationEvent(String toUserName, String fromUserName, String createTime, String msgType, String agentID,
			String event,double latitude,double longitude,double precision) {
		super(toUserName, fromUserName, createTime, msgType, agentID, event);
		this.latitude=latitude;
		this.longitude=longitude;
		this.precision=precision;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LocationEvent [latitude=");
		builder.append(latitude);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", precision=");
		builder.append(precision);
		builder.append(", getEvent()=");
		builder.append(getEvent());
		builder.append(", getToUserName()=");
		builder.append(getToUserName());
		builder.append(", getFromUserName()=");
		builder.append(getFromUserName());
		builder.append(", getCreateTime()=");
		builder.append(getCreateTime());
		builder.append(", getMsgType()=");
		builder.append(getMsgType());
		builder.append(", getAgentID()=");
		builder.append(getAgentID());
		builder.append("]");
		return builder.toString();
	}

}
