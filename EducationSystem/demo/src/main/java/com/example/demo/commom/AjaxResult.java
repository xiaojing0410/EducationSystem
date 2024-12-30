package com.example.demo.commom;



/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:龙宝
 * Date:2023-10-08
 * Time:14:50
 * 统一返回
 */

public class AjaxResult {
    private Integer code;//状态码
    private String msg;//状态码描述
    private Object data;//返回数据

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    /*
        操作成功返回结果
         */
    public static AjaxResult success(Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(0);
        ajaxResult.setMsg("ok");
        ajaxResult.setData(data);
        return ajaxResult;
    }
    public static AjaxResult success(Integer code,Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(code);
        ajaxResult.setMsg("");
        ajaxResult.setData(data);
        return ajaxResult;
    }

    public static AjaxResult success(Integer code,String msg,Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(code);
        ajaxResult.setMsg(msg);
        ajaxResult.setData(data);
        return ajaxResult;
    }

    /*
    错误返回结果
     */
    public static AjaxResult fail(Object msg, Integer code) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(code);
        ajaxResult.setMsg((String)msg);
        ajaxResult.setData("");
        return ajaxResult;
    }
    public static AjaxResult fail(Object msg) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(-1);
        ajaxResult.setMsg((String) msg);
        ajaxResult.setData("");
        return ajaxResult;
    }

}
