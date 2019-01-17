package com.sgcc.zj.common.base;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

/**
 * @author liyingjie
 * @Title: BaseController
 * @Description: controller基础类
 */
public class BaseController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    private static final String RESPONSE_FLAG = "success";
    private static final String RESPONSE_MSG = "returnMsg";
    private static final String RESPONSE_DATA = "data";
    private static final String RESPONSE_CODE = "returnCode";
    private static final String RESPONSE_USERID = "userId";

    /**
     * 组装返回给客户端的响应
     * @param code
     * @param msg
     * @return
     */
    protected ModelMap buildResponseModelMap(int code, String msg) {
        return getModelMap(null, code, msg, null);

    }

    protected ModelMap buildResponseModelMap(String userId, int code, String msg) {
        return getModelMap(userId, code, msg, null);
    }

    /**
     * 组装返回给客户端的响应
     * @param userId
     * @param code
     * @param msg
     * @param flag
     * @param str
     * @return
     */
    protected ModelMap buildResponseModelMap(String userId, int code, String msg, boolean flag, String str) {
        String successMsg = ResponseCode.getSuccessDesc(code);
        if (StringUtils.isNotBlank(successMsg)) {
            code = ResponseCode.CODE_SUCCESS.getCode();
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute(RESPONSE_CODE, code);
        if (flag) {
            msg = String.format(msg, str);
            modelMap.addAttribute(RESPONSE_MSG, msg);
        } else {
            modelMap.addAttribute(RESPONSE_MSG, msg);
        }
        modelMap.addAttribute(RESPONSE_USERID, userId);
        LOGGER.info(JSONObject.toJSONString(modelMap));

        return modelMap;
    }

    /**
     *  组装返回给客户端的响应
     * @param code
     * @param responseMsg
     * @param vo
     * @return
     */
    protected ModelMap buildResponseModelMap(int code, String responseMsg, Object vo) {
        return getModelMap(null, code, responseMsg, vo);

    }

    protected ModelMap buildResponseModelMap(String userId, int code, String responseMsg, Object vo) {
        return getModelMap(userId, code, responseMsg, vo);
    }

    private ModelMap getModelMap(String userId, int code, String responseMsg, Object vo) {
        String successMsg = ResponseCode.getSuccessDesc(code);
        if (StringUtils.isNotBlank(successMsg)) {
            responseMsg = successMsg;
            code = ResponseCode.CODE_SUCCESS.getCode();
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute(RESPONSE_CODE, code);
        if (StringUtils.isNotBlank(responseMsg)){
            modelMap.addAttribute(RESPONSE_MSG, responseMsg);
        }
        if(StringUtils.isNotBlank(userId)){
            modelMap.addAttribute(RESPONSE_USERID, userId);
        }
        if(null!=vo){
            modelMap.addAttribute(RESPONSE_DATA, vo);
        }
        LOGGER.info(JSONObject.toJSONString(modelMap));
        return modelMap;
    }

}
