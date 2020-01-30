package com.scl.statistics.controller.admin;


import com.scl.common.vo.ResultVO;
import com.scl.statistics.service.DailyService;
import com.scl.statistics.service.impl.DailyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author cl
 * @since 2020-01-28
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/statistics/daily")
public class DailyAdminController {
    @Autowired
    private DailyService dailyService ;

    @GetMapping("{day}")
    public ResultVO createStatisticsByDate(@PathVariable("day")
                                                   String day) {

        dailyService.createStatisticsByDay(day);
        return ResultVO.ok();
    }

    @GetMapping("chart/{begin}/{end}/{type}")
    public ResultVO showChart(
            @PathVariable("begin")
            String begin,
            @PathVariable("end")
            String end,
            @PathVariable("type")
            String type) {

        Map<String, Object> data = dailyService.getChartData(begin, end, type);

        return ResultVO.ok().data(data);
    }
}

