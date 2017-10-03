package com.common.system.util;

import java.util.HashMap;
import java.util.Map;

public class MsgUtil {

	/**
	 * 返回消息用于json
	 * @param flag
	 * @param msg
	 * @return
	 */
	public static Map<String, String> getMsgMap(boolean flag, String msg) {
		Map<String, String> map = new HashMap<String, String>();
		if(flag) {
			map.put("SUCCESS", "true");
			map.put("MSG", msg+"成功!");
		} else {
			map.put("SUCCESS", "false");
			map.put("MSG", msg+"失败!");
		}
		return map;
	}
}
