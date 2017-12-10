/**
 * 
 * Create on 2016年11月9日
 */
package org.zl.test.weixin;

import java.net.URLEncoder;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * @author Leo
 * @version 0.0.1
 */
public class TVerifyURL {

	/**
	 * @param args
	 * @throws AesException 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(URLEncoder.encode("http://www.topone56.com/appweb/web/user/edit","UTF-8"));
		
		String encodingAesKey = "upcBVBFxdMurtzsqHF2YJ8vwUaHaF91HSBXjoFcjPuH";
		String token = "topONE5656ZL";
		String appId = "wx525c7b5c32278c06";

		WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
		
		System.out.println(pc.verifyUrl("5ca7d653e4780bba0c9caf99ee4e323555aa0cb7", "1479124638", "473428959", "8064500191668584095"));

	}

}
