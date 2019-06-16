package com.redsheep.wxlogin.util;

/**
 * @author redsheep
 * @date 2019/6/16
 */
public class JSONResultUtil {
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    private String ok;	// 不使用

    public static JSONResultUtil build(Integer status, String msg, Object data) {
        return new JSONResultUtil(status, msg, data);
    }

    public static JSONResultUtil ok(Object data) {
        return new JSONResultUtil(data);
    }

    public static JSONResultUtil ok() {
        return new JSONResultUtil(null);
    }

    public static JSONResultUtil errorMsg(String msg) {
        return new JSONResultUtil(500, msg, null);
    }

    public static JSONResultUtil errorMap(Object data) {
        return new JSONResultUtil(501, "error", data);
    }

    public static JSONResultUtil errorTokenMsg(String msg) {
        return new JSONResultUtil(502, msg, null);
    }

    public static JSONResultUtil errorException(String msg) {
        return new JSONResultUtil(555, msg, null);
    }

    public JSONResultUtil() {

    }

    public JSONResultUtil(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public JSONResultUtil(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
}
