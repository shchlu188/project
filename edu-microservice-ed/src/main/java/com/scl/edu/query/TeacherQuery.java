package com.scl.edu.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/17
 * Description:
 */
@Data
@ApiModel(value = "Teacher查询对象",description = "讲师查询对象封装")
public class TeacherQuery implements Serializable {
    private static final Long serialVersionUID=1l;
    @ApiModelProperty(value = "讲师姓名")
    private String name;
    @ApiModelProperty("讲师头衔")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间",example = "2020-01-18 00:00:00")
    private String begin;
    @ApiModelProperty(value = "查询结束时间",example = "2020-01-21 00:00:00")
    private String end;
}
