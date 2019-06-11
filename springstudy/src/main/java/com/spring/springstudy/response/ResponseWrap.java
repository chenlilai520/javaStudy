package com.spring.springstudy.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.regex.Matcher;


/**
 * 接口回复基础类
 *
 * @author ccq
 * @date 2016年7月23日
 */
@ApiModel(value = "标准回复包装类")
public class ResponseWrap<T> implements Serializable {
    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码", example = "200", required = true)
    private int code;

    /**
     * 描述信息
     */
    @ApiModelProperty(value = "返回描述", example = "请求成功", required = true, position = 1)
    private String desc;
    /**
     * 数据域
     */
    @ApiModelProperty(value = "数据", position = 2)
    private T data;


    /**
     * 新建成功响应对象
     *
     * @return 响应对象
     */
    public static <T> ResponseWrap<T> success() {
        return success(null);
    }

    /**
     * 新建成功响应对象
     *
     * @param response 结果
     * @return 响应对象
     */
    public static <T> ResponseWrap<T> success(T response) {
        return new ResponseWrap<>(ResponseCode.CODE_200, response);
    }


    /**
     * 新建分页成功响应对象
     *
     * @param pageInfo pagehelper分页插件的pageInfo
     * @return 响应对象
     */
    public static <T> ResponseWrap<PageResponse<T>> successPage(PageInfo<T> pageInfo) {
        if (pageInfo == null) {
            return success();
        }
        PageResponse<T> pageResponse = new PageResponse<>();
        BeanUtils.copyProperties(pageInfo, pageResponse);
        return success(pageResponse);
    }


    /**
     * 校验是否请求成功
     *
     * @return true 请求失败 false 请求成功
     */
    @JsonIgnore
    public boolean isFailure() {
        return code != ResponseCode.CODE_200.getCode();
    }


    /**
     * @return 新建失败响应对象
     */
    public static <T> ResponseWrap<T> failure() {
        return new ResponseWrap<>(ResponseCode.CODE_400, null);
    }


    /**
     * 新建失败响应对象
     *
     * @param code 编码
     * @return 响应对象
     */
    public static <T> ResponseWrap<T> failure(ResponseCode code) {
        return new ResponseWrap<>(code, null);
    }


    /**
     * 新建失败响应对象
     *
     * @param code 编码
     * @param data 返回数据
     * @return 响应对象
     */
    public static <T> ResponseWrap<T> failure(ResponseCode code, T data) {
        return new ResponseWrap<>(code, data);
    }


    /**
     * 响应对象
     *
     * @param code 编码
     * @param data 返回数据
     * @return 响应对象
     */
    public static <T> ResponseWrap<T> result(ResponseCode code, T data) {
        return new ResponseWrap<>(code, data);
    }


    public static <T> ResponseWrap<T> result(Integer code, String data, T date) {
        ResponseWrap<T> responseWrap = new ResponseWrap<>();
        responseWrap.setDesc(data);
        responseWrap.setCode(code);
        responseWrap.setData(date);
        return responseWrap;
    }




    public ResponseWrap() {
        this.code = ResponseCode.CODE_200.getCode();
        this.desc = ResponseCode.CODE_200.getDesc();
    }

    public ResponseWrap(ResponseCode code) {
        this.code = code.getCode();
        this.desc = code.getDesc();
    }

    public ResponseWrap(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.desc = responseCode.getDesc();
        this.data = data;
    }


    public static ResponseCode changeMethod(int code) {
        return Enum.valueOf(ResponseCode.class, "CODE_" + code);
    }




    public int getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return (T) data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseWrap{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }
}
