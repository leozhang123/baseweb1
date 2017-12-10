/**
 * 
 * Create on 2016年11月24日
 */
package org.zl.test.weixin;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.zl.weixin.message.EventMessage;
import org.zl.weixin.message.WXMessage;
import org.zl.weixin.message.WeixinMessage;
import org.zl.weixin.util.MessageUtils;

/**
 * @author Leo
 * @version 0.0.1
 */
public class TestMessage {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	String event;
	String message;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		event="<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[qrscene_123123]]></EventKey><Ticket><![CDATA[TICKET]]></Ticket></xml>";
		message="<xml> <ToUserName><![CDATA[toUser]]></ToUserName> <FromUserName><![CDATA[fromUser]]></FromUserName> <CreateTime>1348831860</CreateTime> <MsgType><![CDATA[text]]></MsgType> <Content><![CDATA[this is a test]]></Content> <MsgId>1234567890123456</MsgId> </xml>";
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws ParserConfigurationException, SAXException, IOException {
		WXMessage msg = MessageUtils.getWinxinMessage(event);
		System.out.println(msg instanceof EventMessage);
		 msg = MessageUtils.getWinxinMessage(message);
		System.out.println(msg instanceof WeixinMessage);
	}

}
