package com.sgcc.zj.core.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:
 * @Desc 
 * @Date Created in   2017/12/11 .
 */
public enum ResponseCode {

    CODE_SUCCESS(1000, "成功"),
    CODE_LOGIN_SUCCESS(1001,"登录成功这里属于你"),
    CODE_REGISTER_SUCCESS(1002,"注册成功这里属于你"),
    CODE_SEND_SUCCESS(1003,"验证码发送成功"),
    CODE_TRADE_PASSWORD_SUCCESS(1004,"交易密码设置成功喽!"),
    CODE_TRADE_PASSWORD_CHECK_SUCCESS(1005,"交易密码验证成功"),




    NOT_LOGGED_IN_ERROR(8000, "亲,您还未登录或已失效,请去登录吧!"),
    CODE_REGISTERED_USER_ERROR(8001, "已注册，请登录"),
    CODE_UNREGISTERED_USER_ERROR(8002, "未注册请先注册"),
    CODE_VERIFY_CODE_OLD_ERROR(8003, "验证码太老了"),
    CODE_INCORRECT_ERROR(8004, "验证码错误"),
    CODE_ILLEGAL_MOBILE_ERROR(8005, "手机号码无效"),
    CODE_ILLEGAL_PARAMETER_ERROR(8006, "非法参数"),
    CODE_FREQUENTLY_VERIFY_REQUEST_ERROR(8007, "验证码请求频繁"),
    CODE_VERIFY_ENOUGH_TIMES_ERROR(8008, "验证码次数已上限"),

    CODE_SYSTEM_ERROR(9000, "系统错误"),
    CODE_LOGIN_ERROR(9001, "用户登录异常"),
    CODE_SET_AVATAR_ERROR(9002, "修改头像异常"),
    CODE_PARAMS_ERROR(9003, "参数错误"),
    CODE_REGISTER_ERROR(9004, "用户注册异常"),
    CODE_USER_IS_NOT_EXIST_ERROR(9005, "用户不存在"),
    VERIFY_CODE_SEND_FAIL(9006, "请求验证码异常"),
    CODE_TRADE_PASSWORD_FAIL(9007, "交易密码设置失败了,稍后再试吧！"),
    INSERT_LOAN_ORDER_FAIL(9008, "添加订单失败！"),
	CODE_ILLEGAL_CLIENT(9009, "非法的客户端,appId错误"),
    CODE_NEED_PARAMS_ERROR(9010, "缺少必要参数"),
	CODE_SIGN_ERROR(9011, "签名错误");




    private int code;
    private String defaultDesc;
    public static Map<Integer,String> descMap = new HashMap<Integer,String>();
    public static Map<Integer,String> successMap = new HashMap<>();
    static {
        for(ResponseCode code1 : ResponseCode.values()){
            descMap.put(code1.code,code1.defaultDesc);
        }
        successMap.put(CODE_LOGIN_SUCCESS.getCode(),CODE_LOGIN_SUCCESS.getDefaultDesc());
        successMap.put(CODE_REGISTER_SUCCESS.getCode(),CODE_REGISTER_SUCCESS.getDefaultDesc());
        successMap.put(CODE_SEND_SUCCESS.getCode(),CODE_SEND_SUCCESS.getDefaultDesc());
        successMap.put(CODE_TRADE_PASSWORD_SUCCESS.getCode(),CODE_TRADE_PASSWORD_SUCCESS.getDefaultDesc());
        successMap.put(CODE_TRADE_PASSWORD_CHECK_SUCCESS.getCode(),CODE_TRADE_PASSWORD_CHECK_SUCCESS.getDefaultDesc());
    }
    ResponseCode(int code, String desc) {
        this.defaultDesc = desc;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getDefaultDesc() {
        return defaultDesc;
    }

    public static String getDefaultDescByCode (int code){
        return descMap.get(code);
    }
    public static String getSuccessDesc (int code){
        return  successMap.get(code);
    }
}
