package com.scl.common.exception;

import com.scl.common.constants.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.core.annotation.Order;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/18
 * Description: 自定义异常类
 */
@Data
@ApiModel("自定义异常")
public class SclException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;

    public SclException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SclException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "SclException{" +
                "code=" + code +
                ",message" + this.getMessage() +
                '}';

    }
}
