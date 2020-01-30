package com.scl.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/17
 * Description:
 */
@AllArgsConstructor
@Getter
public enum ResultCodeEnum {
    SUCCESS(true, 20000, "成功"),
    UNKNOW_REASON(false, 20001, "未知错误"),
    BAD_SQL_GRAMMAR(false, 21001, "sql语法错误"),
    JSON_PARSE_ERROR(false, 21002, "json解析异常"),
    PARAM_ERROR(false, 21003, "参数错误"),
    FILE_UPLOAD_ERROR(false, 21004, "文件上传错误"),
    EXCEL_DATA_IMPORT_ERROR(false, 21005, "excel导入错误");

    private Boolean success;

    private Integer code;

    private String message;


}
