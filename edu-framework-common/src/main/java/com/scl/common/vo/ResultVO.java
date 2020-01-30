package com.scl.common.vo;

import com.scl.common.constants.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/17
 * Description:
 */
@ApiModel(value = "结果视图数据")
@Data
@NoArgsConstructor
public class ResultVO {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    public static ResultVO ok() {
        ResultVO resultVO = new ResultVO();
        resultVO.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        resultVO.setCode(ResultCodeEnum.SUCCESS.getCode());
        resultVO.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return resultVO;
    }


    public static ResultVO error() {
        ResultVO resultVO = new ResultVO();
        resultVO.setSuccess(ResultCodeEnum.UNKNOW_REASON.getSuccess());
        resultVO.setCode(ResultCodeEnum.UNKNOW_REASON.getCode());
        resultVO.setMessage(ResultCodeEnum.UNKNOW_REASON.getMessage());
        return resultVO;
    }

    /*public ResultVO data(Map<String,Object> map){
        this.setData(map);
        return this;
    }*/

    public ResultVO data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ResultVO data(Map<String, Object> data) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            this.data.put(entry.getKey(), entry.getValue());
        }
        return this;

    }

    public ResultVO message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResultVO code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResultVO success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public static ResultVO setResult(ResultCodeEnum resultCodeEnum) {
        return new ResultVO()
                .success(resultCodeEnum.getSuccess())
                .code(resultCodeEnum.getCode())
                .message(resultCodeEnum.getMessage());
    }


}
