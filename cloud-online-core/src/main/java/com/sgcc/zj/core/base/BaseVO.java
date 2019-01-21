package com.sgcc.zj.core.base;

/**
 * @author liyingjie
 */
public interface BaseVO {
	
	/**
	 * 转换PO(Persistent Object5)到VO(Value Object),以便于只传输需要的数据到界面端
	 * @param poObj
	 */
	void convertPOToVO(Object poObj);
}
