package com.spring.springstudy.response;

/**
 * 异常代码
 * http 协议规范为主
 *
 * @author
 */
public enum ResponseCode {

    /**
     * 请求成功
     */
    CODE_200(200, "请求成功"),
    /**
     * 系统未捕获异常
     */
    CODE_400(400, "请求错误,请稍后重试"),
    CODE_90000(90000, "请选择正确的类型");


    private int code;
    private String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public static ResponseCode changeMethod(int code) {
        return Enum.valueOf(ResponseCode.class, "CODE_" + code);
    }
}
