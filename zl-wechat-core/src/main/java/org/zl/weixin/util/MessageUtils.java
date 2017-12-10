/**
 * 
 * Create on 2016年11月1日
 */
package org.zl.weixin.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.zl.weixin.message.ClickEvent;
import org.zl.weixin.message.EventMessage;
import org.zl.weixin.message.ImageMessage;
import org.zl.weixin.message.LinkMessage;
import org.zl.weixin.message.LocationEvent;
import org.zl.weixin.message.LocationMessage;
import org.zl.weixin.message.ScanEvent;
import org.zl.weixin.message.ShortVideoMessage;
import org.zl.weixin.message.SubscribeEvent;
import org.zl.weixin.message.TextMessage;
import org.zl.weixin.message.UnsubscribeEvent;
import org.zl.weixin.message.VideoMessage;
import org.zl.weixin.message.ViewEvent;
import org.zl.weixin.message.VoiceMessage;
import org.zl.weixin.message.WXMessage;

/**
 * @author Leo
 * @version 0.0.1
 */
public class MessageUtils {

	private static String autoReply;
	
	/**
	 * 获取微信消息对象
	 * 支持类型:text
	 * @param xmlcontent
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static WXMessage getWinxinMessage(String xmlcontent) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(xmlcontent);
		InputSource is = new InputSource(sr);
		Document document = db.parse(is);
		Element root = document.getDocumentElement();
		
		String ToUserName = getContent("ToUserName",root);
		String FromUserName = getContent("FromUserName",root);
		String CreateTime = getContent("CreateTime",root);
		String MsgType = getContent("MsgType",root);
		String AgentID = getContent("AgentID",root);
		
		WXMessage result = null;
		MsgType = StringUtils.upperCase(MsgType);
		switch(MsgType){
		case EventMessage.TYPE:
			result = procEvent(ToUserName,FromUserName,CreateTime,MsgType,AgentID,root);
			break;
		case TextMessage.TYPE:
			String MsgId = getContent("MsgId",root);
			String Content = getContent("Content",root);
			result =  new TextMessage(ToUserName,FromUserName,CreateTime,MsgType,MsgId,AgentID,Content);
			break;
		case ImageMessage.TYPE:
			result =  new ImageMessage(ToUserName,FromUserName,CreateTime,MsgType,getContent("MsgId",root),AgentID,getContent("PicUrl",root),getContent("MediaId",root));
			break;
		case VoiceMessage.TYPE:
			result =  new VoiceMessage(ToUserName,FromUserName,CreateTime,MsgType,getContent("MsgId",root),AgentID,getContent("MediaId",root),getContent("Format",root),getContent("Recognition",root));
			break;
		case VideoMessage.TYPE:
			result =  new VideoMessage(ToUserName,FromUserName,CreateTime,MsgType,getContent("MsgId",root),AgentID,getContent("MediaId",root),getContent("ThumbMediaId",root));
			break;
		case ShortVideoMessage.TYPE:
			result =  new ShortVideoMessage(ToUserName,FromUserName,CreateTime,MsgType,getContent("MsgId",root),AgentID,getContent("MediaId",root),getContent("ThumbMediaId",root));
			break;
		case LocationMessage.TYPE:
			result =  new LocationMessage(ToUserName,FromUserName,CreateTime,MsgType,getContent("MsgId",root),AgentID,getDouble("Location_X",root),getDouble("Location_Y",root),getDouble("Scale",root),getContent("Label",root));
			break;
		case LinkMessage.TYPE:
			result =  new LinkMessage(ToUserName,FromUserName,CreateTime,MsgType,getContent("MsgId",root),AgentID,getContent("Title",root),getContent("Description",root),getContent("Url",root));
			break;
		default:
			throw new SAXException("类型未知");
		}
		
		return result;
	}

	private static WXMessage procEvent(String ToUserName,String FromUserName,String CreateTime,String MsgType,String AgentID,Element root) throws SAXException{
		String Event = getContent("Event",root);
		Event= StringUtils.upperCase(Event);
		
		WXMessage result = null;
		switch(Event){
		case LocationEvent.EVENT_TYPE:
			result =   new LocationEvent(ToUserName,FromUserName,CreateTime,MsgType,AgentID,Event,getDouble("Latitude",root),getDouble("Longitude",root),getDouble("Precision",root));
			break;
		case ClickEvent.EVENT_TYPE:
			result =  new ClickEvent(ToUserName,FromUserName,CreateTime,MsgType,AgentID,Event,getContent("EventKey",root));
			break;
		case SubscribeEvent.EVENT_TYPE:
			result =  new SubscribeEvent(ToUserName,FromUserName,CreateTime,MsgType,AgentID,Event,getContent("EventKey",root),getContent("Ticket",root));
			break;
		case UnsubscribeEvent.EVENT_TYPE:
			result =  new UnsubscribeEvent(ToUserName,FromUserName,CreateTime,MsgType,AgentID,Event);
			break;
		case ViewEvent.EVENT_TYPE:
			result =  new ViewEvent(ToUserName,FromUserName,CreateTime,MsgType,AgentID,Event,getContent("EventKey",root));
			break;
		case ScanEvent.EVENT_TYPE:
			result =  new ScanEvent(ToUserName,FromUserName,CreateTime,MsgType,AgentID,Event,getContent("EventKey",root),getContent("Ticket",root));
			break;
		default:
			throw new SAXException("未知事件类型");
		}
		return result;
	}
	public static String getContent(String key,Element root){
		NodeList nodelist1 = root.getElementsByTagName(key);
		if(nodelist1.getLength()<1){
			return "";
		}
		String content = nodelist1.item(0).getTextContent();
		return content;
	}
	
	public static double getDouble(String key,Element root){
		NodeList nodelist1 = root.getElementsByTagName(key);
		if(nodelist1.getLength()<1){
			return 0;
		}
		String content = nodelist1.item(0).getTextContent();
		double r = NumberUtils.createDouble(content);
		return r;
	}

	/**
	 * @return the autoReply
	 */
	public static String getAutoReply() {
		return autoReply;
	}

	/**
	 * @param autoReply the autoReply to set
	 */
	public static void setAutoReply(String autoReply) {
		MessageUtils.autoReply = autoReply;
	}
	
	public static String getNoReplyMsg(){
		return "success";
	}
}
