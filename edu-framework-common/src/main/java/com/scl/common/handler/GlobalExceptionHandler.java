package com.scl.common.handler;

import com.scl.common.constants.ResultCodeEnum;
import com.scl.common.exception.SclException;
import com.scl.common.util.ExceptionUtil;
import com.scl.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/18
 * Description: 统一异常处理器
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 通用异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO error(Exception e) {
//        e.printStackTrace();
//        log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return ResultVO.error();
    }

    // 处理特定异常
    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public ResultVO error(BadSqlGrammarException e) {
//        e.printStackTrace();
//        log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return ResultVO.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResultVO error(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return ResultVO.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }

    @ExceptionHandler(SclException.class)
    @ResponseBody
    public ResultVO error(SclException e) {
        e.printStackTrace();
        return ResultVO
                .error()
                .message(e.getMessage())
                .code(e.getCode());
    }



}
