/**
 * 
 * Create on 2016年11月22日
 */
package org.zl.weixin.message;

/**
 * @author Leo
 * @version 0.0.1
 */
public class LocationMessage extends WeixinMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8320921049696757683L;

	public static final String TYPE = "LOCATION";
	
	private double location_X;
	private double location_Y;
	private double scale;
	private String label;

	/**
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param msgId
	 * @param agentID
	 * @param Location_X
	 * @param Location_Y
	 * @param Scale
	 * @param Label
	 */
	public LocationMessage(String toUserName, String fromUserName, String createTime, String msgType, String msgId,
			String agentID,double Location_X,double Location_Y,double Scale,String Label) {
		super(toUserName, fromUserName, createTime, msgType, msgId, agentID);
		
		this.location_X=Location_X;
		this.location_Y=Location_Y;
		this.scale=Scale;
		this.label=Label;
	}

	/**
	 * @return the location_X
	 */
	public double getLocation_X() {
		return location_X;
	}

	/**
	 * @param location_X the location_X to set
	 */
	public void setLocation_X(double location_X) {
		this.location_X = location_X;
	}

	/**
	 * @return the location_Y
	 */
	public double getLocation_Y() {
		return location_Y;
	}

	/**
	 * @param location_Y the location_Y to set
	 */
	public void setLocation_Y(double location_Y) {
		this.location_Y = location_Y;
	}

	/**
	 * @return the scale
	 */
	public double getScale() {
		return scale;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(double scale) {
		this.scale = scale;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

}
