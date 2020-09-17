package com.yidu.format;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * 类的描述:Layui格式类
 *
 * @author wh
 * @since 2020/8/28 20:41
 */
@Component
public class LayuiFormat {
    private Integer code;
    private String msg;
    private Long count;
    private Object data;

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
