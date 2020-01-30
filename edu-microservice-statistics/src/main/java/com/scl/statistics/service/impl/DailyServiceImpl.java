package com.scl.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scl.statistics.client.UcenterClient;
import com.scl.statistics.entity.Daily;
import com.scl.statistics.mapper.DailyMapper;
import com.scl.statistics.service.DailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-01-28
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {
    @Autowired
    private UcenterClient ucenterClient;
    @Override
    public void createStatisticsByDay(String day) {
        QueryWrapper<Daily> queryWrapper = new QueryWrapper<>();
        // 删除已存在的统计对象
        queryWrapper.eq("date_calculated", day);
        baseMapper.delete(queryWrapper);
        //获取统计信息 TODO
        Integer registerNum = (Integer) ucenterClient.registerCount(day).getData().get("countRegister");
        Integer loginNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(100, 200);//TODO
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO
        //创建统计对象 TODO
        Daily daily = new Daily();
        daily.setDateCalculated(day);
        daily.setRegisterNum(registerNum);
        daily.setLoginNum(loginNum);
        daily.setCourseNum(courseNum);
        daily.setVideoViewNum(videoViewNum);

        baseMapper.insert(daily);

    }

    @Override
    public Map<String, Object> getChartData(String begin, String end, String type) {
        QueryWrapper<Daily> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(type, "date_calculated");
        queryWrapper.between("date_calculated", begin, end);
        List<Daily> dayList = baseMapper.selectList(queryWrapper);

        Map<String, Object> map = new HashMap<>();

        List<Integer> dataList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();

        map.put("dataList",dataList);
        map.put("dateList",dateList);

        for (Daily daily : dayList) {
            dateList.add(daily.getDateCalculated());
            switch (type){
                case "register_num":
                    dataList.add(daily.getRegisterNum());
                    break;
                case "login_num":
                    dataList.add(daily.getLoginNum());
                    break;
                case "video_view_num":
                    dataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    dataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        return map;
    }
}
