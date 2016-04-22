package com.qiguan.grab.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**  
 * <pre>
 * Description	存储请求头header参数(Builder模式)
 * Copyright:	Copyright (c)2016
 * Company:		杭州启冠网络技术有限公司
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2016年4月20日 上午9:49:16  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
public final class StrMap {
	
	/**
	 * Map
	 */
	private Map<String, Object> map;

	/**
	 * 初始化
	 */
	public StrMap() {
		this(new ConcurrentHashMap<String, Object>());
	}

	/**
	 * 传递值
	 * 
	 * @param map
	 */
	public StrMap(Map<String, Object> map) {
		this.map = map;
	}

	/**
	 * 添加header(name, value)
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public StrMap put(String key, Object value) {
		map.put(key, value);
		return this;
	}

	/**
	 * 添加header(name, value)
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public StrMap putNotEmpty(String key, String value) {
		if (null != value && value.trim().length() > 0) {
			map.put(key, value);
		}
		return this;
	}

	/**
	 * 添加header(name, value)
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public StrMap putNotNull(String key, Object value) {
		if (value != null) {
			map.put(key, value);
		}
		return this;
	}

	/**
	 * 是否添加
	 * 
	 * @param key
	 * @param val
	 * @param when
	 * @return
	 */
	public StrMap putWhen(String key, Object val, boolean when) {
		if (when) {
			map.put(key, val);
		}
		return this;
	}

	/**
	 * Map复制
	 * 
	 * @param map
	 * @return
	 */
	public StrMap putAll(Map<String, Object> map) {
		this.map.putAll(map);
		return this;
	}

	/**
	 * @param map
	 * @return
	 */
	public StrMap putAll(StrMap map) {
		this.map.putAll(map.map);
		return this;
	}

	/**
	 * 循环遍历存储数据
	 * 
	 * @param imp
	 */
	public void forEach(Consumer imp) {
		for (Map.Entry<String, Object> i : map.entrySet()) {
			imp.accept(i.getKey(), i.getValue());
		}
	}

	/**
	 * 集合大小
	 * 
	 * @return
	 */
	public int size() {
		return map.size();
	}

	/**
	 * @return
	 */
	public Map<String, Object> map() {
		return this.map;
	}

	/**
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return map.get(key);
	}

	/**
	 * Map格式化成表单格式数据
	 * 
	 * @return
	 */
	public String formString() {
		final StringBuilder b = new StringBuilder();
		forEach(new Consumer() {
			private boolean notStart = false;
			/* (non-Javadoc)
			 * @see com.qiguan.grab.http.StrMap.Consumer#accept(java.lang.String, java.lang.Object)
			 */
			@Override
			public void accept(String key, Object value) {
				if (notStart) {
					b.append("&");
				}
				try {
					b.append(URLEncoder.encode(key, "UTF-8")).append('=').append(URLEncoder.encode(value.toString(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					throw new AssertionError(e);
				}
				notStart = true;
			}
		});
		return b.toString();
	}

	/**  
	 * <pre>
	 * Description	数据接口
	 * Copyright:	Copyright (c)2016
	 * Company:		杭州启冠网络技术有限公司
	 * Author:		Administrator
	 * Version: 	1.0
	 * Create at:	2016年4月22日 上午9:24:55  
	 *  
	 * Modification History:  
	 * Date         Author      Version     Description 
	 * ------------------------------------------------------------------  
	 * 
	 * </pre>
	 */  
	public interface Consumer {
		void accept(String key, Object value);
	}
}
