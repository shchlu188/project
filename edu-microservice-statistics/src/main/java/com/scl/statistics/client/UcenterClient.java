package com.scl.statistics.client;

import com.baomidou.mybatisplus.extension.api.R;
import com.scl.common.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/28
 * Description:
 */
@Component
@FeignClient("scl-ucenter")
public interface UcenterClient {
    /**
     * 注意：一定要写成 @PathVariable("day")，圆括号中的"day"不能少
     * @param day
     * @return
     */
    @GetMapping(value = "/admin/ucenter/member/count-register/{day}")
    public ResultVO registerCount(@PathVariable("day") String day);
}
