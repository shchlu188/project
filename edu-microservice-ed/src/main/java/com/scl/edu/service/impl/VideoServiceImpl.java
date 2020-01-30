package com.scl.edu.service.impl;

import com.scl.edu.entity.Video;
import com.scl.edu.mapper.VideoMapper;
import com.scl.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-01-17
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
